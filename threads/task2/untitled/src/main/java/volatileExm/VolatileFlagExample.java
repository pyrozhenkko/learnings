package volatileExm;

public class VolatileFlagExample {

    private static volatile boolean running = true;// потоки не бачать звичайны типи, необхыдний флажок "volatile", щоби потоки бачали останні зміни в даних

    public static void main(String[] args) {

        Thread worker = new Thread(() -> {
            System.out.println("Worker started");
            while(running){
                int x =0;
                  for(int i=0;i<1000;i++){
                      x++;
                  }
            }
            System.out.println("Worker finished");

        });
        worker.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Main: request to stop worker");
        running = false;

        try {
            worker.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Main: finish");
    }
}
