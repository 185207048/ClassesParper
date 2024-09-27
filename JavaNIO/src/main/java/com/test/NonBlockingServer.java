package com.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 非阻塞IO
 * 在这个例子中，服务器首先创建一个非阻塞的ServerSocketChannel，并将其注册到一个Selector上，同时指定它对“连接就绪”事件感兴趣。然后，服务器进入一个无限循环，不断通过select方法检查是否有准备就绪的通道。一旦有通道准备就绪（即客户端连接进来或有数据可读），服务器就会处理这些事件。
 * 对于连接就绪事件，服务器会接受新的连接，并创建一个新的SocketChannel，然后将其注册到同一个Selector上，并指定它对“读就绪”事件感兴趣。
 */
public class NonBlockingServer {
    public static void main(String[] args) throws IOException {
        // 打开一个非阻塞的ServerSocketChannel
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        // 绑定到一个端口
        serverChannel.bind(new InetSocketAddress(9999));
        // 打开一个Selector
        Selector selector = Selector.open();
        // 将Channel注册到Selector上，并指定我们感兴趣的事件：连接事件
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        while(true){
            // 选择一组已经准备好的keys（即已经准备好的IO操作）

            selector.select();
            // 迭代选择到的keys
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
            while (keyIterator.hasNext()){
                SelectionKey key = keyIterator.next();
                if(key.isAcceptable()){
                    // 如果有新的连接，则接受连接
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel client = server.accept();
                    client.configureBlocking(false);
                    // 将新的连接注册到Selector上，并指定我们感兴趣的事件：读事件
                    /**
                     * OP_READ：读就绪事件。这表示通道中已经有了可读的数据，可以执行读操作了。当通道注册了读就绪事件后，如果通道中有数据可读，则在下一次选择器轮询时，该通道的SelectionKey会被选中，并且其isReadable()方法会返回true。
                     * OP_WRITE：写就绪事件。这表示已经可以向通道写数据了。当通道注册了写就绪事件后，如果通道当前可以用于写操作，则在下一次选择器轮询时，该通道的SelectionKey会被选中，并且其isWritable()方法会返回true。
                     * OP_CONNECT：（客户端使用）请求连接事件。这表示客户与服务器的连接已经建立成功。对于客户端的SocketChannel，当连接到服务器成功后，该通道的SelectionKey会被选中，并且其isConnectable()方法会返回true（注意，对于连接就绪事件，通常是先检查isConnectable()然后调用finishConnect()来完成连接过程）。
                     * OP_ACCEPT：（服务端使用）当服务端ServerSocketChannel收到一个客户端的连接请求时，该操作就绪。这是服务端准备接收新连接的状态。
                     * 这些事件类型可以通过按位或操作来组合使用，表示对多个事件感兴趣。例如，可以同时注册读就绪和写就绪事件，这样当通道中有数据可读或可写时，都能被选择器检测到。
                     * ⭐️ 选择就只就是通过这样区分读、写、请求连接和连接就绪四个事件了
                     */
                    client.register(selector, SelectionKey.OP_READ);
                    System.out.println("Accepted new connection from " + client);

                }else if (key.isReadable()) {
                    // 如果有数据可读，则读取数据
                    SocketChannel client = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int bytesRead = client.read(buffer);
                    if (bytesRead == -1) {
                        // 客户端断开连接
                        client.close();
                    } else {
                        // 处理读取到的数据...
                        buffer.flip(); // 切换为读模式
                        // 这里只是简单地将读取到的数据回显给客户端
                        client.write(buffer);
                    }
                }
            }
            // 移除已经处理过的key
            keyIterator.remove();
        }
    }
}
