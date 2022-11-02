package demo.classes1.communicationThread.startInTime;

import demo.classes1.communicationThread.WaitNotifyThreadDemo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.timer.Timer;

import static java.lang.Thread.sleep;

public class FlagThread{
    private static final Logger LOGGER = LogManager.getLogger(FlagThread.class);
    public synchronized void stateFire(){
        for (int i = 0 ; i < 10 ; i++){
            try {
                sleep(Timer.ONE_SECOND);
                LOGGER.info(i + "秒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        WaitNotifyThreadDemo.flag.set(true);
        notifyAll();
    }

    public synchronized void waitFire(){
        while(!WaitNotifyThreadDemo.flag.get()){
            try {
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

}
