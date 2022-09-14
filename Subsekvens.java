public class Subsekvens{
    public final String SUBSEKVENS;
    private int antForekomster = 1;

    public Subsekvens(String Subsekvens){
        this.SUBSEKVENS = Subsekvens;
    }

    public String hentSubsekvens(){
        return SUBSEKVENS;
    }

    public int hentAntForekomster(){
        return antForekomster;
    }

    public void endreAntForekomster(int nyForekomst){
        this.antForekomster = nyForekomst;
    }

    public String toString(){
        return "(" + SUBSEKVENS + ", " + antForekomster + ")";
    }
}