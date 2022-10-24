package demo.classes1.createThread;

class RunnableDemo implements Runnable{
    @Override
    public void run() {
        System.out.println("RunnableDemo");
    }
}

class ThreadDemo extends Thread{
    @Override
    public void run() {
        System.out.println("ThreadDemo");
    }
}

public class CreateThreadDemo {
    public static void main(String[] args) {
        RunnableDemo runnableDemo = new RunnableDemo();
        new Thread(runnableDemo).start();
        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.start();
    }
}
