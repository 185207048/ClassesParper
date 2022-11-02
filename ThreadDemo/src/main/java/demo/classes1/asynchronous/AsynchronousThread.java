package demo.classes1.asynchronous;

// 异步
public class AsynchronousThread {
    public static void main(String[] args) {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                System.out.println("t1");
            }
        };
        t1.start();
        System.out.println("main");
    }
}
