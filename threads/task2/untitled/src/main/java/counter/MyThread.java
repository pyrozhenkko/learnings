package counter;

public class MyThread extends Thread{

    private final SimpleCounter counter;
    private final int threadNumber;

    public MyThread(SimpleCounter counter, int threadNumber) {
        this.counter = counter;
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        System.out.println("Thread " + threadNumber + " started");
        for(int i = 0; i < 5; i++){
            counter.increment();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Thread " + threadNumber + " finished");
    }
}
