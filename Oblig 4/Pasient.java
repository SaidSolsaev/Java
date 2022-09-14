public class Pasient {
    protected String navn;
    protected String fodselsnummer;
    protected int id;
    static int IdTeller = 1;
    protected Stabel<Resept> reseptListe = new Stabel<>();

    public Pasient(String navn, String fodselsnummer) {
        this.navn = navn;
        this.fodselsnummer = fodselsnummer;
        id = IdTeller;
        IdTeller++;
    }

    public void leggTilNyResept(Resept resept) {// sjekk
        reseptListe.leggPå(resept);
    }

    public Stabel<Resept> hentresepter() {
        return reseptListe;
    }

    public String hentnavn() {
        return navn;
    }

    public String hentfodselsnummer() {
        return fodselsnummer;

    }

    public int hentid() {
        return id;
    }

    public String toString() {
        return "Navnet til pasient: " + navn + " fodselsummer: " + fodselsnummer + " Pasientens ID: " + id;
    }
}
