package demo.classes0;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(8080));
            System.out.println("server socket is running... the port is :" + serverSocket.getLocalPort());
            Socket socket = serverSocket.accept();
            OutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());
            InputStream inputStream = new BufferedInputStream(socket.getInputStream());
            String line = null;
            int contentLenght = 0;
            do{
                line = readLine(inputStream , 0);
                // 如果有Content-Length
                if(line.startsWith("Content-Length")){
                    contentLenght = Integer.parseInt(line.split(":")[1].trim());
                }
                // 打印请求信息
                System.out.println(line);
            }while (!line.equals("\r\n"));
            System.out.println(readLine(inputStream, contentLenght));
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readLine(InputStream inputStream, int contentLe) throws IOException {
        ArrayList arrayList = new ArrayList();
        byte readByte;
        int total = 0;
        if(contentLe != 0){
            do{
                readByte = (byte) inputStream.read();
                arrayList.add(Byte.valueOf(readByte));
                total ++;
            }while (total < contentLe);
        } else {
            do{
                readByte = (byte) inputStream.read();
                arrayList.add(Byte.valueOf(readByte));
            } while (readByte != 10);
        }

        byte[] tmpByteArr = new byte[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            tmpByteArr[i] = ((Byte) arrayList.get(i)).byteValue();
        }
        arrayList.clear();
        return new String(tmpByteArr);
    }
}
