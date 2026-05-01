package ListasSimples;

public class ListaSimple<T extends Comparable<T>> {
    protected NodoSimple<T> primero;
    protected int size = 0;

    public void add(T dato) {
        NodoSimple<T> nuevo = new NodoSimple<>(dato);
        if (primero == null) {
            primero = nuevo;
        } else {
            NodoSimple<T> aux = primero;
            while (aux.siguiente != null) aux = aux.siguiente;
            aux.siguiente = nuevo;
        }
        size++;
    }

    public T get(T dato) {
        NodoSimple<T> aux = primero;
        while (aux != null) {
            if (aux.dato.compareTo(dato) == 0) return aux.dato;
            aux = aux.siguiente;
        }
        return null;
    }

    public T del(T dato) {
        NodoSimple<T> act = primero, ant = null;
        while (act != null) {
            if (act.dato.compareTo(dato) == 0) {
                if (ant == null) primero = act.siguiente;
                else ant.siguiente = act.siguiente;
                size--;
                return act.dato;
            }
            ant = act;
            act = act.siguiente;
        }
        return null;
    }

    public boolean isEmtpy() {
        return primero == null;
    }

    public int getSize() {
        return size;
    }

    public IteradorSimple<T> getIterador() {
        return new IteradorSimple<>(this.primero);
    }
}

