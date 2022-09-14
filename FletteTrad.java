import java.util.*;
import java.util.concurrent.CountDownLatch;


public class FletteTrad implements Runnable {
    private Monitor2 M;
    private CountDownLatch nedteller; 

    public FletteTrad(Monitor2 M, CountDownLatch nedteller){
        this.M = M;
        this.nedteller = nedteller;

    }


    @Override
    public void run() {
        try{
            ArrayList<HashMap<String, Subsekvens>> hms = null;
            while (M.størelse() > 1){
                System.out.println(Thread.currentThread().getName() + ": Flettetrad Starter");
                hms = M.taUtTo();
                //System.out.println();
                //hms = M.taUtTo();
                HashMap<String, Subsekvens> hm = SubsekvensRegister.flettSammen(hms.get(0), hms.get(1));
                M.settInnFlett(hm);
                //hms = M.taUtTo();
                //nedteller.countDown();
                System.out.println(hms.size());
            }
            nedteller.countDown();
        }finally{
            System.out.println(Thread.currentThread().getName() + ": Flettetrad Vellykka\n");
        }
    }

}
  

