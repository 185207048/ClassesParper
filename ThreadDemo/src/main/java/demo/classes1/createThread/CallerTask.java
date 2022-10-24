package demo.classes1.createThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class CallerTask implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "CallerTask";
    }

    public static void main(String[] args) {
        // 创建异步任务
        FutureTask<String> future = new FutureTask<>(new CallerTask());
        // 启动线程
        new Thread(future).start();
        try{
            // 等待任务执行完毕，并返回结果
            System.out.println(future.get());
        }catch (ExecutionException | InterruptedException e){
            e.printStackTrace();
        }
    }
}
