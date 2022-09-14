import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SubsekvensRegister {
    ArrayList<HashMap<String, Subsekvens>> hashBeholder = new ArrayList<HashMap<String, Subsekvens>>();

    public SubsekvensRegister() {
        hashBeholder = new ArrayList<HashMap<String, Subsekvens>>();
    }

    public void settInn(HashMap<String, Subsekvens> map) {
        hashBeholder.add(map);
    }

    public HashMap<String, Subsekvens> taUt() {
        Random tilfeldig = new Random();
        return hashBeholder.remove(tilfeldig.nextInt(0, hashBeholder.size()));
    }

    public int størelse() {
        return hashBeholder.size();
    }
    // Oppg3
    public static HashMap<String, Subsekvens> lesFraFil(String filnavn) throws FileNotFoundException {
        File fil = new File(filnavn);
        HashMap<String, Subsekvens> sykdomPasienter;
        try (Scanner lesFil = new Scanner(fil)) {
            sykdomPasienter = new HashMap<>();
            Subsekvens sub;
            while (lesFil.hasNextLine()) {

                String linje = lesFil.nextLine();

                if (linje.length() >= 3) {
                    for (int i = 0; linje.length() - 2 > i; i++) {
                        sub = new Subsekvens(linje.substring(i, i + 3));
                        sykdomPasienter.put(linje.substring(i, i + 3), sub);
                    }
                } else {
                    System.out.println("Feil sekvens");
                }
                // System.out.println(sykdomPasienter);
            }
            // hashBeholder.settInn(sykdomPasienter);
            lesFil.close();
            return sykdomPasienter;
        }

    }

    // Oppg4
    public static HashMap<String, Subsekvens> flettSammen(HashMap<String, Subsekvens> map1,HashMap<String, Subsekvens> map2) {
        HashMap<String, Subsekvens> map3 = new HashMap<>();
        map3.putAll(map1);
        // System.out.println(map1);
        // System.out.println(map2);
        // System.out.println(map3 + "\n");

        for (Subsekvens sub : map2.values()) {
            // System.out.println(sub + "\n");
            for (Subsekvens subStr : map3.values()) {
                // System.out.println("SubStr: " + subStr);
                if (map3.containsKey(sub.hentSubsekvens())) {
                    map3.get(sub.hentSubsekvens())
                            .endreAntForekomster(map3.get(sub.hentSubsekvens()).hentAntForekomster()
                                    + map2.get(sub.hentSubsekvens()).hentAntForekomster());
                    break;
                }
            }
        }
        for (Subsekvens sub : map2.values()) {
            // System.out.println(sub);
            map3.putIfAbsent(sub.hentSubsekvens(), sub);
        }
        // System.out.println(map3.size());
        return map3;
    }

    public HashMap<String, Subsekvens> flettAlle(){
        HashMap<String, Subsekvens> returMap = new HashMap<>();
        try {
            returMap = flettSammen(hashBeholder.get(0), hashBeholder.get(1));
        } catch (Exception e) {
            System.out.println("Map finnes ikke");
        }
        for (int i = 2; i < hashBeholder.size(); i++){
            returMap = flettSammen(returMap, hashBeholder.get(i));
        }
        return returMap;
    }

    public String finnForekomst(HashMap<String, Subsekvens> map){
        int tmp = 0;
        String subs = "";

        for (Subsekvens sub : map.values()){
            if(tmp < sub.hentAntForekomster()){
                tmp = sub.hentAntForekomster();
                subs = sub.SUBSEKVENS;
            }
        }
        return "Sekvens som forekommer flest: (" + subs + ":" + tmp + ")"; 
    }
  
    public String toString() {
        String str = " ";
        for (HashMap<String, Subsekvens> map : hashBeholder) {
            for (Subsekvens sub : map.values()) {
                str += " " + sub;
            }
        }
        return str;
    }
}