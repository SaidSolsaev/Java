public class KordinatTupple {
    private int rad;
    private int kolonne;

    public KordinatTupple(int rad, int kolonne) {
        this.rad = rad;
        this.kolonne = kolonne;
    }

    public int hentRad(){
        return rad;
    }

    public int hentKolonne(){ 
        return kolonne; 
    }
}