public interface Liste<T> extends Iterable<T> {
    int stoerrelse();

    void leggTilStart(T x);

    void leggTil(T x);

    T hent();

    T fjern();
}
