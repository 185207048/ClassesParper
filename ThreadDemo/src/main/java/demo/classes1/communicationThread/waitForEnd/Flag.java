package demo.classes1.communicationThread.waitForEnd;

public class Flag {
    private int flag = 10;
    public synchronized int getFlag(){
        flag --;
        notifyAll();
        return this.flag;
    }

    public synchronized void judgeWait(){
        while (this.flag != 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
