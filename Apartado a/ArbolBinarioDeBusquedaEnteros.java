public class ArbolBinarioDeBusquedaEnteros extends ArbolBinarioDeBusqueda<Integer> {

    public int getSuma() {
        return getSumaRecursivo(raiz);
    }

    private int getSumaRecursivo(Nodo nodo) {
        if (nodo == null) return 0;
        return nodo.dato + getSumaRecursivo(nodo.izquierda) + getSumaRecursivo(nodo.derecha);
    }

    @Override
    public ArbolBinarioDeBusquedaEnteros getSubArbolIzquierda() {
        if (raiz == null || raiz.izquierda == null) {
            return new ArbolBinarioDeBusquedaEnteros();
        }
        ArbolBinarioDeBusquedaEnteros sub = new ArbolBinarioDeBusquedaEnteros();
        sub.raiz = raiz.izquierda;
        return sub;
    }

    @Override
    public ArbolBinarioDeBusquedaEnteros getSubArbolDerecha() {
        if (raiz == null || raiz.derecha == null) {
            return new ArbolBinarioDeBusquedaEnteros();
        }
        ArbolBinarioDeBusquedaEnteros sub = new ArbolBinarioDeBusquedaEnteros();
        sub.raiz = raiz.derecha;
        return sub;
    }
}
