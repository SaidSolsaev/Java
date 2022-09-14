public class SortRute extends Rute {

    public SortRute(int rad, int kolonne, Labyrint labyrint) {
        super(rad, kolonne, labyrint);
    }

    @Override
    public void finn(Rute fra) {
        return;
    }

    public char sjekkTegn() {
        return '#';
    }

    @Override
    public String toString() {
        String s = "Sort rute: (" + kolonne + ", " + rad + ")";
        return s;
    }
}