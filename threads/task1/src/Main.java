import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        MyRunnable runnable1 = new MyRunnable("num1");
//        MyRunnable runnable2 = new MyRunnable("num2");
//
//        new Thread(runnable1).start();
//        new Thread(runnable2).start();
//
            for (int i = 0; i < 10; i++) {
                int threadNum = i;

                Runnable runnable3 = new Runnable() {
                @Override
                public void run() {
                    if(threadNum % 2 == 0) {//
                        throw new RuntimeException();
                    }
                    for (int j = 0; j < 10; j++) {
                        System.out.println(j + " (LOOP) from thread " + threadNum);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            };

            Thread thread = new Thread(runnable3);
            thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
//
//        MyRunnable runnable4 = new MyRunnable("num4") {
//
//            public void run() {
//                System.out.println("Це текст виведення для потоку" + super.threadNum );
//            }
//        };
//        new Thread(runnable4).start();
//
//    Scanner scan = new Scanner(System.in);
//    System.out.println("enter number");
//    Integer number = scan.nextInt();
//    Runnable someRunnable  = new Runnable() {
//        public void run() {
//            Integer count = 0;
//            while (count < number) {
//                System.out.println("something running with  number " + number);
//                count++;
//            }
//        }
//    };
//    Thread thread = new Thread(someRunnable);
//    thread.start();


        for (int i = 0; i < 10; i++) {
            int threadNum = i; // локальна копія для lambda

            Runnable runnableLambda = () -> {
                if (threadNum % 2 == 0) { // кидаємо RuntimeException лише для парних потоків
                    throw new RuntimeException();
                }

                for (int j = 0; j < 10; j++) {
                    System.out.println(j + " (lambda LOOP) from thread " + threadNum);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            };

            Thread thread = new Thread(runnableLambda);
            thread.start();

        }

    }
}
