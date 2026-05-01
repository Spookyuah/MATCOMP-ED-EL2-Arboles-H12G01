
import Colas.*;
import ListasSimples.*;

public class ArbolBinarioDeBusqueda<T extends Comparable<T>> {
    protected class Nodo {
        T dato;
        Nodo izq, der;

        Nodo(T dato) {
            this.dato = dato;
        }
    }

    protected Nodo raiz;

    //Operaciones:
    public void add(T dato) {
        raiz = addInterno(raiz, dato);
    }

    private Nodo addInterno(Nodo actual, T dato) {   //Funcion recursiva para añadir datos
        if (actual == null) {
            return new Nodo(dato);
        }           //Caso base

        if (dato.compareTo(actual.dato) < 0) {                 //Caso menor
            actual.izq = addInterno(actual.izq, dato);
        } else if (dato.compareTo(actual.dato) > 0) {            //Caso mayor
            actual.der = addInterno(actual.der, dato);
        }                                                   //Caso valor duplicado ignorado
        return actual;
    }

    public ArbolBinarioDeBusqueda<T> getSubarbolIzq() {
        ArbolBinarioDeBusqueda<T> Subarbol = new ArbolBinarioDeBusqueda<T>();
        Subarbol.raiz = (this.raiz != null) ? this.raiz.izq : null;     //Subarbol.raiz=    this.raiz.izq   si  raiz !=null,  null   si  raiz==null
        return Subarbol;
    }

    public ArbolBinarioDeBusqueda<T> getSubarbolDer() {
        ArbolBinarioDeBusqueda<T> Subarbol = new ArbolBinarioDeBusqueda<T>();
        Subarbol.raiz = (this.raiz != null) ? this.raiz.der : null;     //Subarbol.raiz=    this.raiz.der   si  raiz !=null,  null   si  raiz==null
        return Subarbol;
    }

    //Listas de datos:
    public ListaSimple<T> getListaPreOrden() {
        ListaSimple<T> lista = new ListaSimple<>();
        preOrden(raiz, lista);
        return lista;
    }

    private void preOrden(Nodo nodo, ListaSimple<T> lista) {    //Funcion recursiva para PreOrden
        if (nodo != null) { //Caso base, nodo nulo termina la recursividad
            lista.add(nodo.dato);
            preOrden(nodo.izq, lista);
            preOrden(nodo.der, lista);
        }
    }

    public ListaSimple<T> getListaPostOrden() {
        ListaSimple<T> lista = new ListaSimple<>();
        postOrden(raiz, lista);
        return lista;
    }

    private void postOrden(Nodo nodo, ListaSimple<T> lista) {//Funcion recursiva para PostOrden
        if (nodo != null) { //Caso base, nodo nulo termina la recursividad
            postOrden(nodo.izq, lista);
            postOrden(nodo.der, lista);
            lista.add(nodo.dato);
        }
    }

    public ListaSimple<T> getListaOrdenCentral() {
        ListaSimple<T> lista = new ListaSimple<>();
        centralOrder(raiz, lista);
        return lista;
    }

    private void centralOrder(Nodo nodo, ListaSimple<T> lista) {//Funcion recursiva para OrdenCentral
        if (nodo != null) { //Caso base, nodo nulo termina la recursividad
            centralOrder(nodo.izq, lista);
            lista.add(nodo.dato);
            centralOrder(nodo.der, lista);
        }
    }

    //Preguntas:
    public int getGrado() {
        return gradoInterno(raiz);
    }

    private int gradoInterno(Nodo nodo) {    //Funcion recursiva para obtener el grado del arbol
        if (nodo == null) return 0;

        int hijos = 0;            //hijos 0 = nodo hoja
        if (nodo.izq != null) hijos++;
        if (nodo.der != null) hijos++;

        int izq = gradoInterno(nodo.izq);
        int der = gradoInterno(nodo.der);   //grados de los subarboles

        int SubMayor = Math.max(izq, der);    //SubMayor toma izq o der, dependiendo del que mayor grado tenga
        return Math.max(hijos, SubMayor);   //Devuelve el mayor de entre los hijos del nodo actual, o el grado del subarbol de mayor grado
    }

    public int getAltura() {
        return alturaInterno(raiz);
    }

    private int alturaInterno(Nodo nodo) {
        if (nodo == null) return 0; //Caso base

        int izq = alturaInterno(nodo.izq);
        int der = alturaInterno(nodo.der);

        return 1 + Math.max(izq, der);
    }

    public ListaSimple<T> getListaDatosNivel(int nivel) {
        ListaSimple<T> result = new ListaSimple<>();
        if (raiz == null) return result; //Caso arbol nulo

        Cola<Nodo> cola = new Cola<>();
        cola.add(raiz); //Cola para llevar cuenta de los nodos por recorrer

        int nivelActual = 0;

        while (!cola.isEmpty()) {
            int size = cola.getSize();

            for (int i = 0; i < size; i++) {
                Nodo nodo = cola.remove();  //Eliminar los nodos ya visitados

                if (nivelActual == nivel) {
                    result.add(nodo.dato);  //Se añaden solo los nodos que se encuentren en el nivel dado
                }
                if (nodo.izq != null) cola.add(nodo.izq);
                if (nodo.der != null)
                    cola.add(nodo.der);  //Añadir los siguientes nodos a visitar, terminara cuando izq y der esten vacios
            }
            if (nivelActual == nivel) break;  //Finalizar tras tener los datos del nivel pedido guardados en result
            nivelActual++;
        }
        return result;
    }

    public boolean isArbolHomogeneo() {
        return isHomogeneoInterno(raiz);
    }

    private boolean isHomogeneoInterno(Nodo nodo) { //Funcion recursiva para comprobar homogeneidad
        if (nodo == null) return true; //Nodo vacio, es homogeneo

        if (nodo.izq == null && nodo.der == null) return true; //Nodo hoja, es homogeneo

        if (nodo.izq == null || nodo.der == null) return false; //Un solo hijo, no homogeneo

        return isHomogeneoInterno(nodo.izq) && isHomogeneoInterno(nodo.der); //Ninguno de los casos anteriores, se sigue comprobando
    }

    public boolean isArbolCompleto() {
        int altura = getAltura();
        return isCompletoInterno(raiz, 0, altura);
    } //Empieza con nivel actual=0, el primer nivel

    private boolean isCompletoInterno(Nodo nodo, int nivel, int altura) {    //Funcion recursiva para comprobar completitud
        if (nodo == null) return true; //Caso base

        if (nodo.izq == null && nodo.der == null) { //Nodo actual es hoja, si el arbol es completo el nivel del nodo actual debe ser el ultimo nivel, igual  a la altura
            return nivel == altura;
        }

        if (nodo.izq == null || nodo.der == null) { //Le falta un hijo al nodo actual, no puede ser completo
            return false;
        }

        return isCompletoInterno(nodo.izq, nivel + 1, altura) && isCompletoInterno(nodo.der, nivel + 1, altura);    //true si ambas funciones devuelven true, false si no
    }

    public boolean isArbolCasiCompleto() {
        if (raiz == null) return true; //Arbol vacio es casi completo

        Cola<Nodo> cola = new Cola<>();
        cola.add(raiz); //Cola para recorrer por niveles
        boolean hayHueco = false;

        while (!cola.isEmpty()) {
            Nodo actual = cola.remove();

            if (actual.izq != null) {  //Izquierda
                if (hayHueco) return false; //False si hay un elemento mas despues de haber encontrado un hueco
                cola.add(actual.izq);
            } else hayHueco = true;

            if (actual.der != null) {  //Derecha
                if (hayHueco) return false; //False si hay un elemento mas despues de haber encontrado un hueco
                cola.add(actual.der);
            } else hayHueco = true;
        }

        return true;
    }

    public ListaSimple<T> getCamino() { //Devuelve el camino mas largo del arbol
        ListaSimple<T> camino = new ListaSimple<>();
        getCaminoInterno(raiz, camino);
        return camino;}
    private void getCaminoInterno(Nodo actual, ListaSimple<T> camino) { //Funcion recursiva para calcular el camino mas largo
        if (actual == null) return;//Caso base
        camino.add(actual.dato);

        int alturaIzq = alturaInterno(actual.izq);
        int alturaDer = alturaInterno(actual.der);

        if (alturaIzq >= alturaDer) { //Tomar el camino con mayor altura
            getCaminoInterno(actual.izq, camino);
        } else {
            getCaminoInterno(actual.der, camino);
        }
    }

    public ListaSimple<T> getCamino(T objetivo){ //Devuelve el camino hacia el dato buscado (objetivo), o null si no lo encuentra
        ListaSimple<T>camino = new ListaSimple<>();
        Nodo actual=raiz;

        while (actual!= null) {
            camino.add(actual.dato);

            int comparison=objetivo.compareTo(actual.dato);

            if (comparison==0) return camino; //Encontrado
            else if (comparison<0) actual=actual.izq;   //Actual es menor que objetivo
            else  actual=actual.der;    //Actual es mayor que objetivo
        }
        return null; //No encontrado
    }
}
