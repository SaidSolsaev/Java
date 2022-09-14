public class HvitRute extends Rute {
    

    public HvitRute(int rad, int kolonne, Labyrint labyrint) {
        super(rad, kolonne, labyrint);
    }

    @Override
    public void finn(Rute fra) {
        if (fra == null) {
            naboer[0].finn(this);
            naboer[1].finn(this);
            naboer[2].finn(this);
            naboer[3].finn(this);
        } else {
            if (naboer[0] != null && naboer[0] != fra) {
                naboer[0].finn(this);
            }
            if (naboer[1] != null && naboer[1] != fra) {
                naboer[1].finn(this);
            }
            if (naboer[2] != null && naboer[2] != fra) {
                naboer[2].finn(this);
            }
            if (naboer[3] != null && naboer[3] != fra) {
                naboer[3].finn(this);
            }
        }
        return;
    }

    public char sjekkTegn() {
        return '.';
    }

    @Override
    public String toString(){
        String s = "Hvit rute: (" + kolonne + ", " + rad +")";
        return s;
    }
}