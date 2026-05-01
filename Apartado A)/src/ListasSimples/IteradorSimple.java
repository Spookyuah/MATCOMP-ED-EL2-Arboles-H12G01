package ListasSimples;

public class IteradorSimple<T> {
    private NodoSimple<T> actual;

    public IteradorSimple(NodoSimple<T> comienzo) {
        this.actual = comienzo;
    }

    public boolean hasNext() {
        return actual != null;
    }

    public T next() {
        if (!hasNext()) return null;
        T dato = actual.dato;
        actual = actual.siguiente;
        return dato;
    }
}
