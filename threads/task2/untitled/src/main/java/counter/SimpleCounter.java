package counter;

import java.util.concurrent.atomic.AtomicInteger;

public class SimpleCounter {
    private volatile AtomicInteger counter;
    private Object object = new Object();

    public SimpleCounter() {
        counter = new AtomicInteger(0);
    }
    public  void increment() {
        counter.incrementAndGet();
    }
    public void decrement() {
        counter.decrementAndGet();
     }
    public int getCounter() {
        return counter.get();
    }
    public void setCounter(int counter) {
        this.counter.set(counter);
    }

}
