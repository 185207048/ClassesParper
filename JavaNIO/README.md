# Java IO/NIO
《2024年JAVA面试核心知识点整理(283页).pdf》P35
- 阻塞IO模型：最传统的一种IO模型，即在读写数据过程中会发生阻塞现象。
  - 特点：请求时一直阻塞
- 非阻塞IO模型：
  非阻塞IO模型允许程序在进行IO操作时，如果IO操作无法立即完成，程序不会等待IO操作完成，
  而是立即返回一个状态值给调用者，这个状态值通常表示IO操作尚未完成或发生错误。
  调用者可以根据这个状态值决定是继续尝试IO操作、执行其他任务还是进行错误处理。
  非阻塞IO模型中分为`采用轮询`或`选择机制`,轮询机制是程序不断地询问内核IO操作是否完成,
  选择机制则是通过系统提供的函数（如select、poll或epoll）来同时监视多个文件描述符（sockets）的状态，
  当任何一个文件描述符就绪时，程序就会被通知进行相应的IO操作。
  - 特点：`轮询`需要一直循环，循环会导致cpu过高，`选择机制`使用使用`Selector`和`Channel`这两个类，
  他的特点就是不阻塞，可以同时处理多个链接，见`NonBlockingServer.java`
- 多路复用IO模型：
  - 在多路复用 IO 模型中，会有一个线程不断去轮询多个 socket 的状态，在多路复用 IO 模型中，只需要使用一个线程就可以管理多个
  socket，系统不需要建立新的进程或者线程，也不必维护这些线程和进程，并且只有在真正有socket 读写事件进行时，才会使用 IO 资源，所以它大大减少了资源占用。
  - 另外多路复用 IO 为何比非阻塞 IO 模型的效率高是因为在非阻塞 IO 中，不断地询问 socket 状态
    时通过用户线程去进行的，而在多路复用 IO 中，轮询每个 socket 状态是内核在进行的，这个效
    率要比用户线程要高的多。
- 信号复用IO模型：
- 异步IO模型：
  
  
  
  
