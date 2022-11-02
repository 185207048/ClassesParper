package demo.classes1.communicationThread;

import demo.classes1.communicationThread.assemblePoint.Assemble;
import demo.classes1.communicationThread.assemblePoint.TaskThread;
import demo.classes1.communicationThread.asynchronizedResult.CallAble;
import demo.classes1.communicationThread.asynchronizedResult.MyExecutor;
import demo.classes1.communicationThread.asynchronizedResult.MyFuture;
import demo.classes1.communicationThread.customerAndProducer.Customer;
import demo.classes1.communicationThread.customerAndProducer.Producer;
import demo.classes1.communicationThread.startInTime.FlagThread;
import demo.classes1.communicationThread.startInTime.StartInTime;
import demo.classes1.communicationThread.ticketThread.TicketThread;
import demo.classes1.communicationThread.waitForEnd.Flag;
import demo.classes1.communicationThread.waitForEnd.WaitThread;

import javax.management.timer.Timer;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

// 线程间的通信与线程安全
public class WaitNotifyThreadDemo {
    public static AtomicInteger position =  new AtomicInteger(0) ;
    public static AtomicBoolean flag = new AtomicBoolean(false);
    // 在使用wait/notify之前，我们知道wait()函数用于阻塞一个线程
    // 而notify()和notifyAll()函数用于唤醒已经阻塞的线程
    // 所以我们能用notify和wait函数干啥呢？
    // 这里就举出了几个场景：
    // 1、生产者/消费者
    // 2、同时开始
    // 3、等待结束
    // 4、异步结果
    // 5、集合点
    // 然后现在我们对上面五个场景进行一个实现，实现目的主要是了解这5个场景的解决思路
    // 所以我们要对比没使用wait/notify的情况和使用了wait/notify的情况
    // 经典例子：抢票
    public static void main(String[] args) throws InterruptedException {
        // 1、生产者/消费者
        // 需求：当前队列为空时，停止队列，等有数据在取出队列
//        new WaitNotifyThreadDemo().openCustomerAndProducer();
        // 2、同时开始
//          new WaitNotifyThreadDemo().openStartIntime();
        // 3、等待结束
//          new WaitNotifyThreadDemo().openWaitForEnd();
        // 4、异步结果
//          new WaitNotifyThreadDemo().openAsynchronizedResult();
        // 5、集合点
            new WaitNotifyThreadDemo().openAssemblePoint();
        // 经典抢票
//        position = new AtomicInteger(100);
//        new WaitNotifyThreadDemo().openTicketThread();
    }

    public void openCustomerAndProducer() {
        // 启动两个构造者
        Producer producer1 = new Producer();
        Producer producer2 = new Producer();
        producer1.setName("producer1");
        producer2.setName("producer2");
        producer1.start();
//        sleep(Timer.ONE_SECOND * 3);
        producer2.start();
        // 启动一个消费者
        Customer customer1 = new Customer();
        customer1.setName("customer1");
        customer1.start();
//        sleep(Timer.ONE_SECOND);

        Customer customer2 = new Customer();
        customer2.setName("customer2");
        customer2.start();
//        sleep(Timer.ONE_SECOND);

        Customer customer3 = new Customer();
        customer3.setName("customer3");
        customer3.start();
//        sleep(Timer.ONE_SECOND);

        Customer customer4 = new Customer();
        customer4.setName("customer4");
        customer4.start();
    }

    public void openStartIntime() {
        // 一个主线程和N个子线程，每个子线程模拟一个运动员，主线程模拟裁判。他们的协作变量是开始信号。这里用的FIreFlag来表示协作对象。
        // 设置10个子线程
        Thread[] threads = new Thread[10];
        FlagThread flagThread = new FlagThread();
        for (int i = 0; i < 10; i++) {
            new StartInTime(flagThread).start();;
        }
        // 设置一个控制线程
        flagThread.stateFire();
    }

    public void openTicketThread(){
        try {
            new TicketThread().start();
            sleep(Timer.ONE_SECOND);
            new TicketThread().start();
            sleep(Timer.ONE_SECOND);
            new TicketThread().start();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    public void openWaitForEnd(){
        // 使用join方法让主线程等待子线程结束，join实际上就是调用了wait，
        // 然后这里用共享变量来表示未完成的线程个数，初始值为子线程个数，主线程等待该值变为0，
        // 而每个子线程结束后都将该值减1，当减为0时调用notifyAll。
        Flag flag = new Flag();
        // 等是个线程全部停止，主线程停止
        for (int i = 0; i < 10; i++) {
            Thread thread = new WaitThread(flag);
            thread.start();
        }
        flag.judgeWait();
        System.out.println(Thread.currentThread().getName() + "main函数运行结束");
    }

    public void openAsynchronizedResult(){
        // 初始化处理器
        // 执行子任务
        // 异步调用获取结果

        // 初始化处理器
        MyExecutor<Integer> myExecutor = new MyExecutor<>();
        // 子任务
        CallAble<Integer> callAble = new CallAble<Integer>() {
            @Override
            public Integer call() throws Exception {
                // 执行异步子任务
                int millis = (int)(Math.random()*1000);
                Thread.sleep(millis);
                return millis;
            }
        };
        // 异步调用返回myFutured对象
        MyFuture<Integer> myFuture = myExecutor.exectue(callAble);
        try {
            Integer result = myFuture.get();
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void openAssemblePoint(){
        // 所谓集合点的意思就是需要集合所有线程，交换数据，然后在进行下一步动作。
        // 这个功能模块的思路就是，他们的共享变量是一个表示到集合点线程的数字，初始值为应该到达的个数，然后线程每到达一个该值减1，最终变为0唤醒所有线程。
        Object obj = new Object();
        Assemble assemble = new Assemble(obj);
        TaskThread thread1 = new TaskThread(assemble,obj);
        TaskThread thread2 = new TaskThread(assemble,obj);
        TaskThread thread3 = new TaskThread(assemble,obj);

        thread1.start();
        thread2.start();
        thread3.start();

    }

}
