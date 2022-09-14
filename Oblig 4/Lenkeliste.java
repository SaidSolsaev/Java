import java.util.Iterator;

public abstract class Lenkeliste<T> implements Liste<T> {

    class Node {
        Node neste = null;
        Node forgie = null;
        protected T dataLagret; // Peker til data som er lagret

        Node(T x) {
            this.dataLagret = x;
        }
    }

    class LenkelisteIterator implements Iterator<T> {
        private Node tmp = start;

        @Override
        public boolean hasNext() {
            return tmp != null;
        }

        @Override
        public T next() {
            T data = tmp.dataLagret;
            tmp = tmp.neste;
            return data;
        }
    }

    public Iterator<T> iterator() {
        return new LenkelisteIterator();
    }

    protected int størelse = 0;
    protected Node start = null;
    protected Node bakerste = null;

    public int stoerrelse() {
        return størelse;
    }

    // Metode som legger til på slutten i lista
    @Override
    public void leggTil(T x) {
        Node nyNode = new Node(x);
        størelse++;
        if (bakerste == null) {
            start = nyNode;
            bakerste = nyNode;
        } else {
            bakerste.neste = nyNode;
            nyNode.forgie = bakerste;
            bakerste = nyNode;
        }
    }

    // Metode for å legge til på starten av liste.
    @Override
    public void leggTilStart(T x) {
        Node nyNode = new Node(x);

        if (start == null) {
            bakerste = nyNode;
            start = nyNode;

            størelse++;
            return;
        } else {
            start.forgie = nyNode; 
        }
        nyNode.neste = start;
        start = nyNode;
        størelse++;
    }

    // Metode som henter det første elementet i lista
    @Override
    public T hent() {
        /*
         * Node tmp = start;
         * while (tmp != null){
         * tmp = tmp.neste;
         * }
         */

        if (start == null) {
            return null;
        } else {
            return start.dataLagret;
        }
    }

    // Metode som fjerner det første elementet og returnerer det.
    @Override
    public T fjern() throws UgyldigListeindeks {
        if (størelse == 0) {
            throw new UgyldigListeindeks(0);
        }

        T returVerdi = start.dataLagret;
        if (størelse == 1) {
            start = null;
            bakerste = null;
        } else {
            start = start.neste;
        }
        størelse--;
        return returVerdi;
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
