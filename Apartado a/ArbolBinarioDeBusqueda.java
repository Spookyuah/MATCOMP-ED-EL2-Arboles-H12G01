public class ArbolBinarioDeBusqueda<T extends Comparable<T>> {

    protected class Nodo {
        T dato;
        Nodo izquierda;
        Nodo derecha;

        Nodo(T dato) {
            this.dato = dato;
            this.izquierda = null;
            this.derecha = null;
        }
    }

    protected Nodo raiz;

    public ArbolBinarioDeBusqueda() {
        raiz = null;
    }

    protected ArbolBinarioDeBusqueda(Nodo raiz) {
        this.raiz = raiz;
    }

    public void add(T dato) {
        if (dato == null) return;
        if (raiz == null) {
            raiz = new Nodo(dato);
        } else {
            addRecursivo(raiz, dato);
        }
    }

    private void addRecursivo(Nodo actual, T dato) {
        int comparacion = dato.compareTo(actual.dato);
        if (comparacion < 0) {
            if (actual.izquierda == null) {
                actual.izquierda = new Nodo(dato);
            } else {
                addRecursivo(actual.izquierda, dato);
            }
        } else if (comparacion > 0) {
            if (actual.derecha == null) {
                actual.derecha = new Nodo(dato);
            } else {
                addRecursivo(actual.derecha, dato);
            }
        }
    }

    public ArbolBinarioDeBusqueda<T> getSubArbolIzquierda() {
        if (raiz == null || raiz.izquierda == null) {
            return new ArbolBinarioDeBusqueda<T>();
        }
        return new ArbolBinarioDeBusqueda<T>(raiz.izquierda);
    }

    public ArbolBinarioDeBusqueda<T> getSubArbolDerecha() {
        if (raiz == null || raiz.derecha == null) {
            return new ArbolBinarioDeBusqueda<T>();
        }
        return new ArbolBinarioDeBusqueda<T>(raiz.derecha);
    }

    public int getGrado() {
        if (raiz == null) return 0;
        return getGradoRecursivo(raiz);
    }

    private int getGradoRecursivo(Nodo nodo) {
        if (nodo == null) return 0;
        int gradoActual = 0;
        if (nodo.izquierda != null) gradoActual++;
        if (nodo.derecha != null) gradoActual++;
        int gradoIzq = getGradoRecursivo(nodo.izquierda);
        int gradoDer = getGradoRecursivo(nodo.derecha);
        return Math.max(gradoActual, Math.max(gradoIzq, gradoDer));
    }

    public int getAltura() {
        if (raiz == null) return 0;
        return getAlturaRecursivo(raiz);
    }

    private int getAlturaRecursivo(Nodo nodo) {
        if (nodo == null) return -1;
        if (nodo.izquierda == null && nodo.derecha == null) return 0;
        int alturaIzq = getAlturaRecursivo(nodo.izquierda);
        int alturaDer = getAlturaRecursivo(nodo.derecha);
        return 1 + Math.max(alturaIzq, alturaDer);
    }

    public Lista<T> getListaDatosNivel(int nivel) {
        Lista<T> lista = new Lista<T>();
        if (raiz == null || nivel < 0) return lista;
        getNivelRecursivo(raiz, nivel, lista);
        return lista;
    }

    private void getNivelRecursivo(Nodo nodo, int nivel, Lista<T> lista) {
        if (nodo == null) return;
        if (nivel == 0) {
            lista.añadir(nodo.dato);
        } else {
            getNivelRecursivo(nodo.izquierda, nivel - 1, lista);
            getNivelRecursivo(nodo.derecha, nivel - 1, lista);
        }
    }

    public Lista<T> getCamino(T valor) {
        Lista<T> camino = new Lista<T>();
        if (raiz == null) return camino;
        buscarCamino(raiz, valor, camino);
        return camino;
    }

    private boolean buscarCamino(Nodo nodo, T valor, Lista<T> camino) {
        if (nodo == null) return false;
        camino.añadir(nodo.dato);
        if (nodo.dato.equals(valor)) return true;
        if (valor.compareTo(nodo.dato) < 0) {
            if (buscarCamino(nodo.izquierda, valor, camino)) return true;
        } else {
            if (buscarCamino(nodo.derecha, valor, camino)) return true;
        }
        camino.eliminar(camino.getTamaño() - 1);
        return false;
    }

    public Lista<T> getListaPreOrden() {
        Lista<T> lista = new Lista<T>();
        preOrdenRecursivo(raiz, lista);
        return lista;
    }

    private void preOrdenRecursivo(Nodo nodo, Lista<T> lista) {
        if (nodo == null) return;
        lista.añadir(nodo.dato);
        preOrdenRecursivo(nodo.izquierda, lista);
        preOrdenRecursivo(nodo.derecha, lista);
    }

    public Lista<T> getListaPostOrden() {
        Lista<T> lista = new Lista<T>();
        postOrdenRecursivo(raiz, lista);
        return lista;
    }

    private void postOrdenRecursivo(Nodo nodo, Lista<T> lista) {
        if (nodo == null) return;
        postOrdenRecursivo(nodo.izquierda, lista);
        postOrdenRecursivo(nodo.derecha, lista);
        lista.añadir(nodo.dato);
    }

    public Lista<T> getListaOrdenCentral() {
        Lista<T> lista = new Lista<T>();
        ordenCentralRecursivo(raiz, lista);
        return lista;
    }

    private void ordenCentralRecursivo(Nodo nodo, Lista<T> lista) {
        if (nodo == null) return;
        ordenCentralRecursivo(nodo.izquierda, lista);
        lista.añadir(nodo.dato);
        ordenCentralRecursivo(nodo.derecha, lista);
    }

    public boolean isArbolHomogeneo() {
        if (raiz == null) return true;
        return esHomogeneoRecursivo(raiz);
    }

    private boolean esHomogeneoRecursivo(Nodo nodo) {
        if (nodo == null) return true;
        int hijos = 0;
        if (nodo.izquierda != null) hijos++;
        if (nodo.derecha != null) hijos++;
        if (hijos == 1) return false;
        return esHomogeneoRecursivo(nodo.izquierda) && esHomogeneoRecursivo(nodo.derecha);
    }

    public boolean isArbolCompleto() {
        if (raiz == null) return true;
        int altura = getAlturaRecursivo(raiz);
        return esCompletoRecursivo(raiz, altura, 0);
    }

    private boolean esCompletoRecursivo(Nodo nodo, int alturaTotal, int nivelActual) {
        if (nodo == null) return false;
        boolean esHoja = (nodo.izquierda == null && nodo.derecha == null);
        if (esHoja) return nivelActual == alturaTotal;
        if (nodo.izquierda == null || nodo.derecha == null) return false;
        return esCompletoRecursivo(nodo.izquierda, alturaTotal, nivelActual + 1)
            && esCompletoRecursivo(nodo.derecha, alturaTotal, nivelActual + 1);
    }

    public boolean isArbolCasiCompleto() {
        if (raiz == null) return true;
        Lista<Nodo> cola = new Lista<Nodo>();
        cola.añadir(raiz);
        boolean encontradoNulo = false;
        for (int i = 0; i < cola.getTamaño(); i++) {
            Nodo actual = cola.obtener(i);
            if (actual.izquierda != null) {
                if (encontradoNulo) return false;
                cola.añadir(actual.izquierda);
            } else {
                encontradoNulo = true;
            }
            if (actual.derecha != null) {
                if (encontradoNulo) return false;
                cola.añadir(actual.derecha);
            } else {
                encontradoNulo = true;
            }
        }
        return true;
    }
}
