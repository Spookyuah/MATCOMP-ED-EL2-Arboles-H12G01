package ListasSimples;

public class NodoSimple<T> {
    T dato;
    NodoSimple<T> siguiente;

    NodoSimple(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}
