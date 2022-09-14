import java.util.ArrayList;

abstract class Rute {
    public int rad;
    public int kolonne;
    public Labyrint labyrint;
    public Rute[] naboer = new Rute[4];

    public Rute(int rad, int kolonne, Labyrint labyrint) {
        this.kolonne = kolonne;
        this.rad = rad;
        this.labyrint = labyrint;
    }

    public void fyllInnNaboer(Rute nord, Rute øst, Rute sør, Rute vest) {
        naboer[0] = nord;
        naboer[1] = øst;
        naboer[2] = sør;
        naboer[3] = vest;
    }

    public KordinatTupple hentKordinater() {
        return new KordinatTupple(kolonne, rad);
    }

    public Rute hentRute(int rad, int kolonne) {
        try {
            Rute retur = labyrint.ruteArr[rad - 1][kolonne - 1];
            return retur;
        } catch (Exception e) {
            System.out.println("Feil pga: " + e);
            return null;
        }
    }

    public String hentNaboer() {
        String SNord = "tom";
        String SVest = "tom";
        String SOst = "tom";
        String SSor = "tom";
        String finalS = "";
        ArrayList<String> tmpList = new ArrayList<>();

        tmpList.add(SNord);
        tmpList.add(SVest);
        tmpList.add(SOst);
        tmpList.add(SSor);

        for (String s : tmpList) {
            s = hentRute(rad, kolonne).toString();
            finalS = s.toString();
        }
        return finalS;
    }

    public void sjekkNabo() {
        for (Rute nabo : naboer) {
            String utskrift = nabo != null
                    ? Character.toString(nabo.sjekkTegn())
                    : "null";
            System.out.println(utskrift);
        }
    }

    public void finn(Rute fra){
        return;
    }

    abstract char sjekkTegn();
}