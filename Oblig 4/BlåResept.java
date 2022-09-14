public class BlåResept extends Resept {
    String farge = "Blå";

    public BlåResept(Legemiddel legemiddel, Lege utSkrevetLege, Pasient pasient, int reit) {
        super(legemiddel, utSkrevetLege, pasient, reit);
    }

    @Override
    public String toString() {
        return super.toString() + "\nFargen på resept: " + farge;
    }

    @Override
    public String farge() {
        return farge;
    }

    // Kan den overides for å bli gjort om til double siden det er 75% avlsag
    // kanskje det er desimaltall som blir returnert.
    @Override
    public int prisÅBetale() {
        int pris = legemiddel.hentPris();
        int rabbatertPris = pris / 100 * 25;
        return rabbatertPris;
    }

}
