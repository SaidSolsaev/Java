public class Vanedannende extends Legemiddel {
    protected int vanedannendeStyrke;

    public Vanedannende(String navn, int pris, double virkestoff, int styrke) {
        super(navn, pris, virkestoff);
        this.vanedannendeStyrke = styrke;
    }

    @Override
    public int hentStyrke() {
        return vanedannendeStyrke;
    }

    @Override
    public String toString() {
        return super.toString() + " Vanedannende styrke: " + vanedannendeStyrke;
    }
    // Ekstra metode for skrive til fil
    @Override
    public String lmType(){
        return "Vanedannende";
    }
}
