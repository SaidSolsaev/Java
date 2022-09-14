public abstract class Resept {
    protected int reseptId;
    protected Lege utSkrevetLege;
    protected Legemiddel legemiddel;
    protected Pasient pasient;
    protected int reit;
    private static int reseptIdTeller = 1;

    public Resept(Legemiddel legemiddel, Lege utSkrevetLege, Pasient pasient, int reit) {
        this.legemiddel = legemiddel;
        this.utSkrevetLege = utSkrevetLege;
        this.pasient = pasient;
        this.reit = reit;
        reseptId = reseptIdTeller;
        reseptIdTeller +=1;
    }

    public boolean bruk() {
        if (reit > 0) {
            reit -= 1;
            return true;
        } else {
            return false;
        }
    }

    abstract public String farge();

    abstract public int prisÅBetale();

    // Hent metodene:
    public int hentId() {
        return reseptId;
    }

    public Legemiddel hentLegemiddel() {
        return legemiddel;
    }

    public Lege hentLege() {
        return utSkrevetLege;
    }

    public Pasient hentPasient() {
        return pasient;
    }

    public int hentReit() {
        if (reit <= 0){
            System.out.println("Kompis du har minus reit, gå til fastlegen.");
        }
        return reit;
    }

    @Override
    public String toString() {
        return "Resept id: " + reseptId + "\n" + legemiddel + "\nSkrevet ut av lege: " + utSkrevetLege
                + "\n" + pasient + "\nAntall ganger igjen på respet: " + reit;
    }
}
