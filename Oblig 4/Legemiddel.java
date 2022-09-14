//Abstrakt klasse som oppretter et legemiddel, superklasse for legemidler. Klassen har metoder som returnerer bare
public abstract class Legemiddel {
    protected String navn;
    protected int pris;
    protected double virkestoff;
    private static int idTeller = 1;
    protected int id;

    public Legemiddel(String navn, int pris, double virkestoff) {
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
        id = idTeller;
        idTeller += 1;
    }

    public int hentId() {
        return id;
    }

    public String hentNavn() {
        return navn;
    }

    public int hentPris() {
        return pris;
    }

    public double hentVirkestoff() {
        return virkestoff;
    }

    public void settNyPris(int nyPris) {
        this.pris = nyPris;
    }

    @Override
    public String toString() {
        return ("Navnet til legemiddelet: " + navn + ". Pris: " + pris + ", virkestoff " + virkestoff + ", Legemiddel ID: " + id);
    }

    // Ekstra abstrakt klasse for skrive til fil
    public abstract String lmType();

    public abstract int hentStyrke();

}