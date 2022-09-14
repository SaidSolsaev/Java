public class Millitæresept extends HvitResept {

    public Millitæresept(Legemiddel legemiddel, Lege utSkrevetLege, Pasient pasient) {
        super(legemiddel, utSkrevetLege, pasient, 3);
    }

    @Override
        public int prisÅBetale() {
        int pris = legemiddel.hentPris();
        int rabbatertPris = pris - pris / 100 * 100;
        return rabbatertPris;
    }
}
