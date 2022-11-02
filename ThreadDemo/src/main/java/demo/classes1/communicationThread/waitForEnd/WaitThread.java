package demo.classes1.communicationThread.waitForEnd;

import demo.classes1.communicationThread.WaitNotifyThreadDemo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WaitThread extends Thread{
    private final static Logger LOGGER = LogManager.getLogger(WaitThread.class);
    private Flag flag = null;

    public WaitThread(Flag flag){
        this.flag = flag;
    }

    @Override
    public void run() {
        System.out.println(this.getName() + "运行结束");
        flag.getFlag();
    }
}
