package demo.classes1.communicationThread.asynchronizedResult;

public interface MyFuture<V> {
    public V get() throws Exception;
}
