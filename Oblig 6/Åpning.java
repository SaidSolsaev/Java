public class Åpning extends HvitRute {

    public Åpning(int rad, int kolonne, Labyrint labyrint) {
        super(rad, kolonne, labyrint);
    }

    @Override
    public void finn(Rute fra){
        System.out.println("(" + rad + ", " + kolonne + ")");
        return;
    }

    @Override
    public String toString(){
        String s = "Åpning: (" + kolonne + ", " + rad +")";
        return s;
    }
}
