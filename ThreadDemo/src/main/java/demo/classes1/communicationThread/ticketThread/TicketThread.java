package demo.classes1.communicationThread.ticketThread;

import demo.classes1.communicationThread.WaitNotifyThreadDemo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.timer.Timer;


// 经典抢票
public class TicketThread extends Thread{
    private final static Logger LOGGER = LogManager.getLogger(TicketThread.class);
    @Override
    public void run() {
        while(true){
            try{
                sleep(Timer.ONE_SECOND * 2);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            if(WaitNotifyThreadDemo.position.get() == 0){
                return;
            }
            synchronized (WaitNotifyThreadDemo.position){
                int value = WaitNotifyThreadDemo.position.decrementAndGet();
                WaitNotifyThreadDemo.position.set(value);
                LOGGER.info("剩余票数" + WaitNotifyThreadDemo.position.get());
            }

        }

    }
}
