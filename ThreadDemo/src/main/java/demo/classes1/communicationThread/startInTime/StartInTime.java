package demo.classes1.communicationThread.startInTime;

import demo.classes1.communicationThread.WaitNotifyThreadDemo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class StartInTime extends Thread{
    private final static Logger LOGGER = LogManager.getLogger(StartInTime.class);
    private FlagThread flagThread = null;

    public StartInTime(FlagThread flagThread) {
        this.flagThread = flagThread;
    }

    @Override
    public void run() {
        this.flagThread.waitFire();

        LOGGER.info(this.getName() + "运行完毕!");
    }
}
