package counter;

import org.w3c.dom.css.Counter;

import java.util.List;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {

        List<Thread> threadList = new ArrayList<>();
        var counter = new SimpleCounter();

        for (int i = 0; i < 5; i++) {
            Thread thread = new MyThread(counter, i);
            thread.start();
            threadList.add(thread);
        }
        System.out.println("loop finished");

        for(Thread thread : threadList){
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Counter = " + counter.getCounter());
    }
}
