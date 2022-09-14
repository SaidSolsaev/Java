import java.util.ArrayList;

import javax.swing.JLabel;

public class Kontroller {

    SpillPanel spill;
    int startLengde;
    int matSpist = 0;
    Thread kjørerTråd;
    int fart = 350;
    char vei = 'H';
    boolean kjører = false;
    int hodeKolonne, haleKolonne, hodeRad, haleRad;
    ArrayList<JLabel> slangeRuter;

    Kontroller(SpillPanel spill) {
        this.spill = spill;
        vei = spill.vei;
        slangeRuter = new ArrayList<>();
        spill.leggTilSlangeHale(6, 6);
        
        hodeKolonne = 6;
        hodeRad = 6;
    }

    static int trekk(int a, int b) {
        return (int) (Math.random() * (b - a + 1)) + a;
    }

    public int hentPoeng(){
        System.out.println(matSpist);
        return matSpist;
    }

    public void leggTilNyMat() {
        int kolonne = trekk(0, 11);
        int rad = trekk(0, 11);
        spill.leggTilMat(rad, kolonne);
    }

    public void leggTilMatStart() {
        for (int i = 0; i < 6; i++) {
            int rad = trekk(0, 11);
            int kolonne = trekk(0, 11);
            spill.leggTilMat(rad, kolonne);
        }
    }

    /*public void velkomstMld(){
        kjørerTråd.interrupt();
    }*/

    int[] finnRute(JLabel tile) {
        int[] temp = new int[2];
        for (int rad = 0; rad < 12; rad++) {
            for (int kolonne = 0; kolonne < 12; kolonne++) {
                if (tile == spill.ruter[rad][kolonne]) {
                    temp[0] = rad;
                    temp[1] = kolonne;
                }
            }
        }
        return temp;
    }

    public void beveg() {
        slangeRuter = spill.slange; 
        if (vei == 'H' && spill.vei != 'V' || vei == 'V' && spill.vei != 'H' || vei == 'O' && spill.vei != 'N'
                || vei == 'N' && spill.vei != 'O') {
            vei = spill.vei;
        }
        if (spill.kollisjon(hodeRad, hodeKolonne, vei)) { 
            kjører = false;
            System.out.println("GAME OVER!");
            //velkomstMld();
            //new GameOver();
        } else {

            if (spill.kollisjonMat(hodeRad, hodeKolonne, vei)) { 
                if (vei == 'H') {
                    spill.fjernMat(hodeRad, hodeKolonne + 1);
                    leggTilNyMat();
                    matSpist++;
                    spill.leggTilSlangeHale(hodeRad, hodeKolonne + 1);
                    hodeKolonne++;
                } else if (vei == 'O') {
                    spill.fjernMat(hodeRad - 1, hodeKolonne);
                    leggTilNyMat();
                    matSpist++;
                    spill.leggTilSlangeHale(hodeRad - 1, hodeKolonne);
                    hodeRad--;
                } else if (vei == 'V') {
                    spill.fjernMat(hodeRad, hodeKolonne - 1);
                    leggTilNyMat();
                    matSpist++;
                    spill.leggTilSlangeHale(hodeRad, hodeKolonne - 1);
                    hodeKolonne--;
                } else if (vei == 'N') {
                    spill.fjernMat(hodeRad + 1, hodeKolonne);
                    leggTilNyMat();
                    matSpist++;
                    spill.leggTilSlangeHale(hodeRad + 1, hodeKolonne);
                    hodeRad++;
                }
                spill.oppdaterPoeng(matSpist);
                fart -= 100;
                System.out.println(fart);
            } else { 
                if (vei == 'H') {
                    spill.leggTilSlangeHale(hodeRad, hodeKolonne + 1);
                    spill.fjernSlange(haleRad, haleKolonne);
                    hodeKolonne++;
                    int[] temp = finnRute(slangeRuter.get(slangeRuter.size() - 1)); 
                    haleRad = temp[0];
                    haleKolonne = temp[1];
                } else if (vei == 'O') {
                    spill.leggTilSlangeHale(hodeRad - 1, hodeKolonne);
                    spill.fjernSlange(haleRad, haleKolonne);
                    hodeRad--;
                    int[] temp = finnRute(slangeRuter.get(slangeRuter.size() - 1));
                    haleRad = temp[0];
                    haleKolonne = temp[1];
                } else if (vei == 'V') {
                    spill.leggTilSlangeHale(hodeRad, hodeKolonne - 1);
                    spill.fjernSlange(haleRad, haleKolonne);
                    hodeKolonne--;
                    int[] temp = finnRute(slangeRuter.get(slangeRuter.size() - 1));
                    haleRad = temp[0];
                    haleKolonne = temp[1];
                } else if (vei == 'N') {
                    spill.leggTilSlangeHale(hodeRad + 1, hodeKolonne);
                    spill.fjernSlange(haleRad, haleKolonne);
                    hodeRad++;
                    int[] temp = finnRute(slangeRuter.get(slangeRuter.size() - 1));
                    haleRad = temp[0];
                    haleKolonne = temp[1];
                }
            }
        }
    }

}