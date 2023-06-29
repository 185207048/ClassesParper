package classes.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class InvokeDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Callable<String>> tasks = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++){
            final int index = i;
            tasks.add(() -> "Task" + index + "completed");
        }
        List<Future<String>> futures = executorService.invokeAll(tasks);
        for(Future<String> future: futures){
            System.out.println(future.get());
        }
        executorService.shutdown();

    }
}
