import java.io.FileNotFoundException;
import java.util.Scanner;

public class Oblig6 {

    public static void main(String[] args) throws FileNotFoundException {
        String fil = null;
        Labyrint lab = null;
        if (args.length > 0) {
            fil = args[0]; 
        } else {
            System.out.println("Skriv inn riktig labyrintfil");
        }
        try {
            lab = new Labyrint("labyrinter/" + fil);
        } catch (FileNotFoundException e) {
            System.out.println("Kan ikke lese fra fil: " + fil);
            System.exit(0);
        }

        System.out.println(lab);

        try (Scanner sc = new Scanner(System.in)) {
            boolean kjør = true;

            while (kjør) {
                System.out.println("Skriv inn koordinater <rad> <kolonne> ('-1' for å avslutte).");

                String valg = sc.nextLine();

                switch (valg) {
                    case "-1":
                        kjør = false;
                        break;
                    default:
                        String[] koordinater = valg.split(" ");
                        System.out.println("Åpninger:\n");
                        lab.finnUtveiFra(Integer.parseInt(koordinater[0]), Integer.parseInt(koordinater[1]));
                        break;
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }

}