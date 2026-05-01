public class ArbolBinarioDeBusquedaEnteros extends  ArbolBinarioDeBusqueda<Integer> {   //Arbol binario que hereda de ArbolBinarioDeBusqueda, pero solo para valores Integer
    public int getSuma(){
        return sumaInterna(raiz);}
    private int sumaInterna(Nodo nodo){
        if (nodo == null) return 0;
        return nodo.dato+sumaInterna(nodo.izq)+sumaInterna(nodo.der);
    }
}
