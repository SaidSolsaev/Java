import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Oblig5Del1 {
    public static void main(String[] args) {
        SubsekvensRegister reg1 = new SubsekvensRegister();
        HashMap<String, Subsekvens> map = new HashMap<>();
        File fil = new File(args[0] + "/metadata.csv");
        Scanner sc = null;

        try {
            sc = new Scanner(fil);
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke filen!");
        }

        while (sc.hasNextLine()) {
            String filer = sc.nextLine();
            try {
                map = SubsekvensRegister.lesFraFil(args[0] + "/" + filer);
            } catch (FileNotFoundException e) {
                System.out.println("Fant ikke filen!");
            }
            reg1.settInn(map);
        }
        //Fletter alle maps i Arraylisten.
        HashMap<String, Subsekvens> map2 = new HashMap<>();
        map2 = reg1.flettAlle();

        //Lagde heller en metode som fletter alle sammen i SubsekvensRegisteret.
        /* 
        for (int i = 1; strl > i; i++) {
            map2 = SubsekvensRegister.flettSammen(reg1.taUt(), reg1.taUt());
            reg1.settInn(map2);
        }*/


        //Printer registeret etter at alle er flettet
        System.out.println(reg1);
        System.out.println(reg1.størelse());

        //Høyeste forekomst
        System.out.println( reg1.finnForekomst(map2));
    }

   
}