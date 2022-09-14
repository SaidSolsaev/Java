import java.io.*;
import java.util.Scanner;

public class Labyrint {
    protected Rute[][] ruteArr;
    protected int kolonner;
    protected int rader;

    public Labyrint(String filnavn) throws FileNotFoundException {
        File fil = new File(filnavn);
        try (Scanner sc = new Scanner(fil)) {
            String[] linjerDeltr = sc.nextLine().strip().split(" ");
            rader = Integer.parseInt(linjerDeltr[0]);
            kolonner = Integer.parseInt(linjerDeltr[1]);
            ruteArr = new Rute[rader][kolonner];
            int kolonne = 0;
            int tmp = 1;
            Rute rute;

            while (sc.hasNextLine()) {
                String[] linjerDelt = sc.nextLine().strip().split("");
                for (int i = 0; i < linjerDelt.length; i++) {
                    rute = null;

                    if (tmp == 1 || tmp == rader + 1) {
                        if (linjerDelt[i].equals("#")) {
                            rute = new SortRute(i, kolonne, this);
                        } else if (linjerDelt[i].equals(".")) {
                            rute = new Åpning(i, kolonne, this);
                        }
                    } else {
                        if (linjerDelt[i].equals(".") && (i == linjerDelt.length - 1 || i == 0)) {
                            rute = new Åpning(i, kolonne, this);
                        } else if (linjerDelt[i].equals("#")) {
                            rute = new SortRute(i, kolonne, this);
                        } else if (linjerDelt[i].equals(".")) {
                            rute = new HvitRute(i, kolonne, this);
                        }
                    }
                    ruteArr[kolonne][i] = rute;
                }
                tmp++;
                kolonne++;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < rader; i++) {
            for (int a = 0; a < kolonner; a++) {
                Rute nord = null;
                Rute oest = null;
                Rute syd = null;
                Rute vest = null;

                if (i - 1 >= 0) {
                    nord = ruteArr[i - 1][a];
                }
                if (a + 1 <= kolonner - 1) {
                    oest = ruteArr[i][a + 1];
                }
                if (i + 1 <= rader - 1) {
                    syd = ruteArr[i + 1][a];
                }
                if (a - 1 >= 0) {
                    vest = ruteArr[i][a - 1];
                }

                ruteArr[i][a].fyllInnNaboer(nord, oest, syd, vest);
            }
        }
    }

    public Rute hentRute(int kolonne, int x) {
        return ruteArr[kolonne][x];
    }

    public int hentKolonner() {
        return kolonner;
    }

    public int hentRader() {
        return rader;
    }

    public Rute[][] hentRuter() {
        return ruteArr;
    }

    public void finnUtveiFra(int rad, int kol) {
        if (ruteArr[rad][kol] instanceof SortRute) {
            System.out.println("Kan ikke starte i sort rute.");
            return;
        }
        ruteArr[rad][kol].finn(null);
    }

    @Override
    public String toString() {
        String utskrift = "";
        for (int i = 0; i < rader; i++) {
            for (int a = 0; a < kolonner; a++) {
                char c = ruteArr[i][a].sjekkTegn();
                utskrift += Character.toString(c);
            }
            if (i < rader - 1) {
                utskrift += "\n";
            }
        }
        return utskrift;
    }
}