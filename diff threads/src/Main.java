import java.util.ArrayList;
import java.util.List;

class Callme {
    synchronized void call(String msg) {
        System.out.print("<" + msg);
        try {
            Thread.sleep(1000);
        } catch(InterruptedException е) {
            System.out.println("Interrupted");
        }
        System.out.println (">");
    }
}
class Caller implements Runnable {
    String msg;
    Callme target;
    Thread t;
    public Caller(Callme targ, String s) {
        target=targ;
        msg=s;
        t=new Thread(this);
    }
    public void start(){
        this.t.start();
    }
    public void run() {
        target.call(msg);
    }
}
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Callme target = new Callme();
        List<Caller> ob = new ArrayList<Caller>();
        ob.add(new Caller(target,"2006"));
        ob.add(new Caller(target,"PP-33"));
        ob.add(new Caller(target,"170"));
        ob.add(new Caller(target,"idk"));
        ob.add(new Caller(target,"A. Sakharov, 23"));
        ob.add(new Caller(target,"095*****45"));
        ob.add(new Caller(target,"olha.ledovskykh.pp.2023@lpnu.ua"));
        ob.add(new Caller(target,"Lviv"));

        for (Caller r: ob) {
            r.start();
            try {
                r.t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
