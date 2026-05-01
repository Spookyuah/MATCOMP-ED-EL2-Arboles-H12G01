public class Lista<T> {

    private T[] elementos;
    private int tamaño;
    private static final int CAPACIDAD_INICIAL = 10;

    @SuppressWarnings("unchecked")
    public Lista() {
        elementos = (T[]) new Object[CAPACIDAD_INICIAL];
        tamaño = 0;
    }

    public void añadir(T elemento) {
        if (tamaño == elementos.length) {
            redimensionar(elementos.length * 2);
        }
        elementos[tamaño++] = elemento;
    }

    public T obtener(int indice) {
        if (indice < 0 || indice >= tamaño) {
            throw new IndexOutOfBoundsException("Indice: " + indice + ", tamaño: " + tamaño);
        }
        return elementos[indice];
    }

    public int getTamaño() {
        return tamaño;
    }

    public boolean estaVacia() {
        return tamaño == 0;
    }

    public boolean contiene(T elemento) {
        for (int i = 0; i < tamaño; i++) {
            if (elementos[i].equals(elemento)) {
                return true;
            }
        }
        return false;
    }

    public void eliminar(int indice) {
        if (indice < 0 || indice >= tamaño) {
            throw new IndexOutOfBoundsException("Indice: " + indice + ", tamaño: " + tamaño);
        }
        for (int i = indice; i < tamaño - 1; i++) {
            elementos[i] = elementos[i + 1];
        }
        elementos[--tamaño] = null;
    }

    @SuppressWarnings("unchecked")
    private void redimensionar(int nuevaCapacidad) {
        T[] nuevoArray = (T[]) new Object[nuevaCapacidad];
        System.arraycopy(elementos, 0, nuevoArray, 0, tamaño);
        elementos = nuevoArray;
    }

    @Override
    public String toString() {
        if (estaVacia()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < tamaño; i++) {
            sb.append(elementos[i]);
            if (i < tamaño - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
