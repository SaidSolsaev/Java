public class Lege implements Comparable<Lege> {
    protected String navn;
    IndeksertListe<Resept> nyResept;

    public Lege(String navn) {
        this.navn = navn;
        nyResept = new IndeksertListe<>();
    }

    public String hentKontrollID(){
        return "0";
    }

    public IndeksertListe<Resept> hentResepter() {
        return nyResept;
    }

    public String hentNavn() {
        return navn;
    }

    @Override
    public String toString() {
        return ("Navnet til legen: " + navn + ". ");
    }

    public int compareTo(Lege annLege) {
        return this.navn.toLowerCase().compareTo(annLege.navn.toLowerCase());
        /*
         * if (navn.compareTo(annLege.hentNavn()) < 0){
         * return -1;
         * } else if (navn.compareTo(annLege.hentNavn()) > 0){
         * return 1;
         * }
         * return 0;
         */
        // return navn.toLowerCase().compareTo(annLege.navn.toLowerCase());
    }

    public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        HvitResept hvitResept = new HvitResept(legemiddel, this, pasient, reit);
        pasient.leggTilNyResept(hvitResept);
        nyResept.leggTil((Resept) hvitResept);
        return hvitResept;
    }

    public Millitæresept skrivMilResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
        Millitæresept mili = new Millitæresept(legemiddel, this, pasient);
        pasient.leggTilNyResept(mili);
        nyResept.leggTil((Resept) mili);
        return mili;
    }

    public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        PResept pResept = new PResept(legemiddel, this, pasient, reit);
        pasient.leggTilNyResept(pResept);
        nyResept.leggTil((Resept) pResept);
        return pResept;
    }

    public BlåResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        BlåResept blaa = new BlåResept(legemiddel, this, pasient, reit);
        pasient.leggTilNyResept(blaa);
        nyResept.leggTil((Resept) blaa);
        return blaa;

    }

}
