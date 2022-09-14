public class IndeksertListe<T> extends Lenkeliste<T> {

    public void leggTil(int pos, T x) {
        Node tmp = new Node(x);

        if (pos == 0) {
            leggTilStart(x);
        } else if (pos == størelse) {
            leggTil(x);
        } else if (pos < 0 || pos > størelse) {
            throw new UgyldigListeindeks(pos);
        } else {
            Node nyNode = start;

            for (int i = 0; i < pos; i++) {
                nyNode = nyNode.neste;
            }

            Node før = nyNode.forgie;
            før.neste = tmp;
            tmp.forgie = før;
            tmp.neste = nyNode;
            nyNode.forgie = tmp;
            størelse += 1;
        }
    }

    public void sett(int pos, T x) {
        if (pos < 0 || pos >= størelse) {
            throw new UgyldigListeindeks(pos);
        }
        Node p = start;
        for (int i = 0; i < pos; i++) {
            p = p.neste;
        }
        p.dataLagret = x;
    }

    public T hent(int pos) throws UgyldigListeindeks {
        if (pos > 0 && pos < stoerrelse()) {
            Node p = start;
            for (int i = 0; i < pos; i++) {
                p = p.neste;
            }
            return p.dataLagret;
        } else if (pos == 0) { // hvis posisjon er 0
            if (stoerrelse() != 0) { // sjekker om det er tomt liste
                return start.dataLagret;
            }
        }
        throw new UgyldigListeindeks(pos);

    }

    public T fjern(int pos) {
        if (størelse <= 0 || pos >= størelse || 0 > pos) {
            throw new UgyldigListeindeks(pos);
        }

        if (pos == 0) {
            Node tmp = start;
            start = start.neste;
            return tmp.dataLagret;
            // return fjern();
        } else {
            Node tmp = start;
            for (int i = 1; i < pos; i++) {
                tmp = tmp.neste;
            }
            Node somFjernes = tmp.neste;
            tmp.neste = somFjernes.neste;
            størelse--;
            return somFjernes.dataLagret;
        }

        //Kan også skrive det sånn, men fant en kortere måte.
        /*Node tmp = start;
        for (int i = 0; i < pos; i++) {
            tmp = tmp.neste;
        }
        Node forrige = tmp.forgie;
        Node neste = tmp.neste;

        if (forrige != null)
            forrige.neste = tmp.neste;

        if (neste != null)
            neste.forgie = tmp.forgie;

        if (forrige.neste == null)
            bakerste = forrige;

        størelse--;
        return tmp.dataLagret;*/
    }

    @Override
    public String toString() {
        String str = "";

        Node tmp = start;
        while (tmp != null) {
            str += "Data: " + tmp.dataLagret + "\n";
            tmp = tmp.neste;
        }
        return str;
    }
}
