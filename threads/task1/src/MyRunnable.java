public class MyRunnable implements Runnable {
    protected final String threadNum;
    public MyRunnable(String threadNum) {
        this.threadNum = threadNum;
    }
    public void run(){
        for (int i=0;i<10;i++){
            System.out.println(i + " from thread " + threadNum);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
