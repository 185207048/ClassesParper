package demo.classes1.communicationThread.assemblePoint;

public class Assemble {
    private int initPoint = 3;
    private Object obj = null;

    public Assemble(Object obj){
        this.obj = obj;
    }

    public int getInitPoint() {
        return initPoint;
    }

    public void setInitPoint(int point){
        this.initPoint = point;
    }

    public synchronized void reduce(){
        this.initPoint --;
        if(this.initPoint != 0){
            while(this.initPoint != 0){
                try{
                    wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }else{
            notifyAll();
        }
    }
}
