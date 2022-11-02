package demo.classes1.communicationThread.customerAndProducer;

import java.util.ArrayDeque;
import java.util.Queue;

/*
    用于生产者/消费者存放数据
 */
public class StoreHouse<T> {
    // 存放队列
    // 存放思路：
    private volatile Queue<T> stroeList;
    private static StoreHouse instance = null;

    private StoreHouse() {
        this.stroeList = new ArrayDeque<>();
    }

    public static synchronized StoreHouse getInstance(){
        if(instance == null){
            synchronized (StoreHouse.class){
                instance = new StoreHouse();
            }
        }else{
            return instance;
        }
        return instance;
    }

    public synchronized boolean add(T value){
        boolean flag = this.stroeList.add(value);
        notifyAll();
        return flag;
    }

    public synchronized T get(){
        while(this.stroeList.isEmpty()){
            try {
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        T t = this.stroeList.poll();
        notifyAll();
        return t;
    }

}
