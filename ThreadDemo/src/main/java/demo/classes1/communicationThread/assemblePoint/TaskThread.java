package demo.classes1.communicationThread.assemblePoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.timer.Timer;

public class TaskThread extends Thread{
    private static final Logger LOGGER = LogManager.getLogger(TaskThread.class);
    private Assemble assemble = null;
    private Object obj = null;
    public TaskThread(Assemble assemble, Object obj){
        this.assemble = assemble;
        this.obj = obj;
    }
    @Override
    public void run() {
        try {
            int result = (int) Math.floor(Math.random() * Timer.ONE_SECOND * 10);
            sleep(result);
            LOGGER.info(Thread.currentThread().getName() + " 运行了" + result + "ms,到达集合点");
            assemble.reduce();
            LOGGER.info(Thread.currentThread().getName() + "运行完毕");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
