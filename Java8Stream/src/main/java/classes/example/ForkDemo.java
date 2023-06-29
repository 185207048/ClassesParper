package classes.example;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkDemo extends RecursiveTask<Long> {
    static final int THRESHOLD = 10000;
    long[] array;
    int start;
    int end;

    ForkDemo(long[] array, int start , int end){
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if(end - start <= THRESHOLD){
            // 如果任务已经足够小了就直接计算
            long sum = 0 ;
            for(int i = start ; i < end ; i++){
                sum += array[i];
            }
            return sum;
        }

        // 任务太大，拆分为两个子任务
        int middle = (end + start) / 2;
        ForkDemo forkDemo1 = new ForkDemo(array, start, middle);
        ForkDemo forkDemo2 = new ForkDemo(array, middle, end);
        invokeAll(forkDemo1,forkDemo2);

        Long forkresult1 = forkDemo1.join();
        Long forkresult2 = forkDemo2.join();
        return forkresult1 + forkresult2;
    }

    public static void main(String[] args) {
        // 创建10000个元素， 每个元素都是1
        long[] array = new long[100000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i ;
        }

        // 创建ForkJoinPool
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 创建一个Fork任务
        ForkJoinTask<Long> task = new ForkDemo(array, 0, array.length);
        long startTime = System.currentTimeMillis();
        // 通过invoke获取返回数据
        Long result = forkJoinPool.invoke(task);
        long endTime = System.currentTimeMillis();
        System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");
    }
}
