package demo.classes1.communicationThread.customerAndProducer;

import demo.classes1.communicationThread.WaitNotifyThreadDemo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.timer.Timer;

public class Producer extends Thread{
    private static final Logger LOGGER = LogManager.getLogger(Producer.class);
    private StoreHouse<String> stringStoreHouse = null; // 声明仓库
    public Producer(){
        stringStoreHouse = StoreHouse.getInstance();
    }
    @Override
    public void run() {
        while(true){
            synchronized(WaitNotifyThreadDemo.position){
                if(WaitNotifyThreadDemo.position.get() == 100){
                    break;
                }
                WaitNotifyThreadDemo.position.incrementAndGet();
                String s = "存放: Producer - " + Thread.currentThread().getName() + ": 产品" + WaitNotifyThreadDemo.position;
                stringStoreHouse.add(s);
                LOGGER.info(s);
            }
//            try {
//                sleep(Timer.ONE_SECOND * 3);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}
