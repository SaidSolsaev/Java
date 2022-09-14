public class HvitResept extends Resept {
    String farge = "Hvit";

    public HvitResept(Legemiddel legemiddel, Lege utSkrevetLege, Pasient pasient, int reit) {
        super(legemiddel, utSkrevetLege, pasient, reit);
    }

    @Override
    public String toString() {
        return super.toString() + "\nFargen på resept: " + farge;
    }

    @Override
    public int prisÅBetale() {
        return legemiddel.hentPris();
    }

    @Override
    public String farge() {
        return farge;
    }
}
