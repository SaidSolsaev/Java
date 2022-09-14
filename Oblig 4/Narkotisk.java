public class Narkotisk extends Legemiddel {
    protected int narkotiskStyrke;

    public Narkotisk(String navn, int pris, double virkestoff, int styrke) {
        super(navn, pris, virkestoff);
        this.narkotiskStyrke = styrke;
    }

    @Override
    public int hentStyrke() {
        return narkotiskStyrke;
    }

    @Override
    public String toString() {
        return super.toString() + " Narkotisk styrke: " + narkotiskStyrke;
    }
    // Ekstra metode for skrive til fil
    @Override
    public String lmType(){
        return "Narkotisk";
    }
}
