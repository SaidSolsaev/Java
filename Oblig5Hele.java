import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.io.*;

/*
*Sette opp to monitorer med syke og friske pasienter
*holde rede på hvor mange friske og syke mennesker det er
*Filene har true eller false på om de er syke eller ikke (lese dette)
*Tråder skal lese og flette
*Avslutte med å finne dominante subsekvenser
*/
public class Oblig5Hele {
    private static Monitor2 sykeMonitor = new Monitor2();
    private static Monitor2 friskeMonitor = new Monitor2();
    private static ArrayList<Integer> sykePasienter = new ArrayList<Integer>();
    private static ArrayList<Integer> friskePasienter = new ArrayList<Integer>();
    private static long startTid;
    private static long sluttTid;

    public static void main(String[] args) throws FileNotFoundException, InterruptedException{
                            
                            //Oppstart lese fra mappe og metafil//
        String mappe = args[0] + "/" + args[1];
        //String metaFil = args[1];
        File fil = new File(mappe);
        Scanner sc = null;
        startTid = System.currentTimeMillis();

        try{
            sc = new Scanner(fil);
        } catch(FileNotFoundException e){
            System.out.println("Fant ikke filen: " + fil);
        }

        while (sc.hasNextLine()){
            int tempTeller = 1;
            CountDownLatch teller = new CountDownLatch(tempTeller);
            String lesLinje = sc.nextLine();
            String[] biter = lesLinje.trim().split(",");
            System.out.println(biter[0]);

            if (biter[1].equals("False")){
                friskePasienter.add(1);
                Runnable lese = new LeseTrad2(args[0] + "/" + biter[0], friskeMonitor, teller);
                new Thread(lese).start();
            } else if (biter[1].equals("True")){
                sykePasienter.add(1);
                Runnable lese = new LeseTrad2(args[0] + "/" + biter[0], sykeMonitor, teller);
                new Thread(lese).start();                
            }
            tempTeller++;
            teller.await();
        }
                            //LESEDEL MED TRÅDER FERDIG!!//


                            //FLETTEDEL MED TRÅDER START//
        int a = 1;
        CountDownLatch flettFriske = new CountDownLatch(a);
        
        int i = 0;
        while (i < friskeMonitor.størelse()-2){
            FletteTrad trad = new FletteTrad(friskeMonitor, flettFriske);
            new Thread(trad).start();;
            //tmpTråd.add(tråder);
            a++;
            flettFriske.await();
            //HashMap<String, Subsekvens> tmp = friskeMonitor.flettSammen(friskeMonitor.taUt(), friskeMonitor.taUt());
            //friskeMonitor.settInnFlett(tmp);
        }
        
        int b = 1;
        CountDownLatch flettSyke = new CountDownLatch(b);
        
        while (i < sykeMonitor.størelse()-1){
            FletteTrad trad = new FletteTrad(sykeMonitor, flettSyke);
            new Thread(trad).start();;
            //tmpTråd.add(tråder);
            a++;
            flettSyke.await();
            //HashMap<String, Subsekvens> tmp = sykeMonitor.flettSammen(sykeMonitor.taUt(), sykeMonitor.taUt());
            //sykeMonitor.settInnFlett(tmp);
        }
                            //FLETTEDEL MED TRÅDER FERDIG!//

        System.out.println("Syke pasienter: " + sykePasienter.size());
        System.out.println("Friske pasienter: " + friskePasienter.size());
        System.out.println("Størelse til liste med syke: " + sykeMonitor.størelse());
        System.out.println("Størelse til liste med friske: " + friskeMonitor.størelse() + "\n");       

                            //SEKVENSER SOM FOREKOMMER MEST//
        /*if (sykeMonitor.størelse() == 1){
            HashMap<String , Subsekvens> sykeMap = sykeMonitor.taUt();
            HashMap<String , Subsekvens> friskMap = friskeMonitor.taUt();
            ArrayList<Subsekvens> oftest = new ArrayList<>();
            ArrayList<Subsekvens> fjern = new ArrayList<>();
            int antall = 0;

            for (Subsekvens sub : sykeMap.values()){
                if (sub.hentAntForekomster() > antall){
                    antall = sub.hentAntForekomster();
                } else if(antall == sub.hentAntForekomster()){
                    oftest.add(sub);
                }
                
            }
            
            for (Subsekvens subs : oftest){
                for (Subsekvens sub : friskMap.values()){
                    if (subs.hentSubsekvens().equals(sub.hentSubsekvens())){
                        fjern.add(subs);
                    }
                }
            }
            oftest.removeAll(fjern); 
           
            int tmp = 0;
            String subs = "";

            for (Subsekvens sub : oftest){
                if(tmp < sub.hentAntForekomster()){
                    tmp = sub.hentAntForekomster();
                    subs = sub.SUBSEKVENS;
                }
            }
            System.out.println("De dominantene subsekvensene: " + subs + ", forekommer " + tmp + " ganger mer!");
        }*/
                            //FLEST FOREKOMSTER FERDIG!//

                            //SUBS SOM FOREKOMMER 7+ GANGER//
        HashMap<String, Subsekvens> friskeMap;
        HashMap<String, Subsekvens> sykeMap;
        if(friskeMonitor.størelse() == 1){
            friskeMap = friskeMonitor.taUt();
            sykeMap = sykeMonitor.taUt();
            for (Subsekvens sub : sykeMap.values()){
                for (Subsekvens subF : friskeMap.values()){
                    if (sub.hentSubsekvens().equals(subF.hentSubsekvens())){
                        if(7 < sub.hentAntForekomster() - subF.hentAntForekomster()){
                            System.out.println("Subsekvenser som forekommer 7 fler ganger: " + sub);
                        }
                    }
                }
            }
        } 
        else if(sykeMonitor.størelse() > 1) {System.out.println("Det er flere Hmaps i lista enn det som er lov. Programmet avsluttes"); System.exit(0);}
        else {System.out.println("Lista består av 0 hmaps. Program avslutter"); System.exit(0);}
        sluttTid = System.currentTimeMillis();
        System.out.println("Tid fra start til slutt, programmet kjørte: " + (sluttTid - startTid));
                            //FOREKOMMER 7+ GANGER FERDIG!//
    }
}