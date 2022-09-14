import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor2 extends SubsekvensRegister {
    //private SubsekvensRegister hashBeholder = new SubsekvensRegister();
    private Lock lås = new ReentrantLock();    

    @Override
    public void settInn(HashMap<String, Subsekvens> map){
        lås.lock();
        try{
            System.out.println("Setter inn");
            hashBeholder.add(map);
            
        } catch (Exception e){
            System.out.println("Klarte ikke sette inn");
        }
         finally{
            System.out.println("Satt inn");
            lås.unlock();
        }
    }

    public void settInnFlett(HashMap<String, Subsekvens> map){
        lås.lock();
        try{
            settInn(map);
        } catch(Exception e){System.out.println("FEIL");}
        finally{
            lås.unlock();
        }
    }

    @Override
    public HashMap<String, Subsekvens> taUt(){
        lås.lock();
        try{
            Random tilfeldig = new Random();
        return hashBeholder.remove(tilfeldig.nextInt(0, hashBeholder.size()));
        } finally{
            lås.unlock();
        }
    }

    public ArrayList<HashMap<String, Subsekvens>> taUtTo() {
        ArrayList<HashMap<String, Subsekvens>> tmpBeholder;
        HashMap<String, Subsekvens> hMap1 = null;
        HashMap<String, Subsekvens> hMap2 = null;
        lås.lock();
        
        try{
            System.out.println("Tar ut to hashmaps. ");
            if(størelse() < 2){
                System.out.println("Det er ikke 2 maps i lista");
                return null;
            } 
            tmpBeholder = new ArrayList<>();
            hMap1 = this.taUt();
            hMap2 = this.taUt();
            tmpBeholder.add(hMap1);
            tmpBeholder.add(hMap2);
            return tmpBeholder;
    
        } finally {
            System.out.println("TO Hashmaps velykket tatt ut.");
            lås.unlock();
        }  
    }  
}

