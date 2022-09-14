public class Vanlig extends Legemiddel {

    public Vanlig(String navn, int pris, double virkestoff) {
        super(navn, pris, virkestoff);
    }
    
    // Ekstra metode for skrive til fil
    @Override
    public String lmType(){
        return "Vanlig";
    }
    @Override
    public int hentStyrke() {
        return 0;
    }
}
