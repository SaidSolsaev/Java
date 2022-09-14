public class Spesialist extends Lege implements Godkjenningsfritak {
    protected String kontrollID;

    public Spesialist (String navn, String kontrollID){
        super(navn);
        this.kontrollID = kontrollID;
    }
    @Override
    public String hentKontrollID(){
        return kontrollID;
    }

    @Override
    public String toString(){
        return "Navnet til spesialist: " + navn + " ID for godkjenningsfritak: " + kontrollID;
    }
}
