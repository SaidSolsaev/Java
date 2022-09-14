public class PResept extends HvitResept {
    protected static int rabbat;

    public PResept(Legemiddel legemiddel, Lege utSkrevetLege, Pasient pasient, int reit) {
        super(legemiddel, utSkrevetLege, pasient, reit);
    }

    public int prisÅBetale() {
        int pris = legemiddel.hentPris();
        int rabbat = 108;
        if (pris >= 108) {
            return pris - rabbat;
        } else {
            return 0;
        }
    }
}
