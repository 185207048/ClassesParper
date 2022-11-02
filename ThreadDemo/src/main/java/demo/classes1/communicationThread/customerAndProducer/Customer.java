package demo.classes1.communicationThread.customerAndProducer;

import demo.classes1.communicationThread.WaitNotifyThreadDemo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.timer.Timer;

public class Customer extends Thread{
    private StoreHouse<String> stringStoreHouse = null;
    private static final Logger LOGGER = LogManager.getLogger(Customer.class);
    public Customer(){
        stringStoreHouse = StoreHouse.getInstance();
    }

    @Override
    public void run() {
        while(true){
            String result = stringStoreHouse.get();
            if(result == null){
                LOGGER.info("当前队列为空");
            }else{
                LOGGER.info("取出: " + result);
            }
        }
    }
}
