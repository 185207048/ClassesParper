package demo.classes1.communicationThread.asynchronizedResult;

public class MyExecutor<V> {
    public MyFuture<V> exectue(final CallAble<V> task){
        final Object lock = new Object();
        final ExecuteThread<V> executeThread = new ExecuteThread(task,lock);
        executeThread.start();
        MyFuture<V> myFuture = new MyFuture<V>() {
            @Override
            public V get() throws Exception {
                synchronized (lock){
                    while(!executeThread.getDone()){
                        try{
                            lock.wait();
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                    if(executeThread.getException() != null){
                        throw executeThread.getException();
                    }
                    return executeThread.getResult();
                }
            }
        };
        return myFuture;
        
    }
    static class ExecuteThread<V> extends Thread{
        private V result = null;                //  返回结果
        private Exception exception = null;     //  捕获异常
        private boolean done = false;           //  完成标志
        private CallAble<V> task;               //  任务接口
        private Object lock;                    //  单一类锁
        public ExecuteThread(CallAble<V> task, Object lock){
            this.lock = lock;
            this.task = task;
        }

        @Override
        public void run() {
            try{
                result = this.task.call();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                synchronized (lock){
                    done = true;
                    lock.notifyAll();
                }
            }
        }

        public V getResult(){
            return result;
        }

        public boolean getDone(){
            return done;
        }

        public Exception getException(){
            return exception;
        }
    }
}


