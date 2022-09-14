public class Prioritetskoe<T extends Comparable<T>> extends Lenkeliste<T> {
    Node bak = new Node(null); //Node som skal starte å peke på null, altså bakerste i lista

    // Skrive om leggTil metoden sånn at den legger til elementer fra minst til
    // størst.
    // Altså sortert rekkefølge
    @Override
    public void leggTil(T x) {
        Node nyNode = new Node(x); // Opretter en nyNode
        Node startNode = start; // startNode som peker på start
        Node nodeBak = bak;
        Boolean sattInn = false; // En boolean som er false helt til node er satt inn i lista
        nodeBak.neste = start; // Setter noden bak sin neste til å peke på start.

        // Så lenge node ikke er satt inn kjører while løkken
        while (sattInn == false) {
            if (startNode == null || startNode.dataLagret.compareTo(nyNode.dataLagret) >= 0) {
                nodeBak.neste = nyNode;
                nyNode.neste = startNode;
                sattInn = true;
                størelse += 1;
                if (start == startNode) {
                    start = nyNode;
                }
                break;
            }
            nodeBak = startNode;
            startNode = startNode.neste;    
        }  
    }

    //Metode som henter det minste elemnetet. Siden jeg vet at det minste elementet er første så kaller jeg på hent metoden bare
    @Override
    public T hent() {
        return super.hent();

    }

    //Fjerner og returnerer det minste elementet, altså det første elemenetet i lista.
    @Override
    public T fjern() {
        return super.fjern();

    }

}
