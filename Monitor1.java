import java.util.*;
import java.util.concurrent.locks.*;

public class Monitor1 extends SubsekvensRegister{
    private Lock lås = new ReentrantLock();
    
    @Override
    public void settInn(HashMap<String, Subsekvens> map){
        lås.lock();
        
        try{
            System.out.println("Setter inn map");
            hashBeholder.add(map);
        } finally {
            lås.unlock();
            System.out.println("Satt inn vellykket");
        }
    }
}
