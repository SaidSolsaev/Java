import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public class LeseTrad implements Runnable {
    private Monitor1 monitor;
    private final String FIL;
    private CountDownLatch nedtelling;

    public LeseTrad(String fil, Monitor1 monitor, CountDownLatch nedtelling) {
        this.monitor = monitor;
        this.FIL = fil;
        this.nedtelling = nedtelling;

    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " Lesetråd starter ");
            HashMap<String, Subsekvens> map = SubsekvensRegister.lesFraFil(FIL);
            monitor.settInn(map);
            nedtelling.countDown();
            System.out.println(Thread.currentThread().getName() + " Tråd er ferdig lest\n");
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke filen!");
        }
    }
}
