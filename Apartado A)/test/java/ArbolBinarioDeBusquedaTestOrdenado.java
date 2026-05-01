import ListasSimples.*;

public class ArbolBinarioDeBusquedaTestOrdenado {
    public static int sumaLista(ListaSimple<Integer> lista) {
        int suma = 0;

        for (int i = 1; i <= lista.getSize(); i++) {
            Integer valor = lista.get(i);
            if (valor != null) {
                suma += valor;
            }
        }
        return suma;
    }

    public static void imprimirCamino(ListaSimple<Integer> camino) {
        for (int i = 0; i < camino.getSize(); i++) {
            System.out.print(camino.get(i));
            if (i < camino.getSize() - 1) System.out.print(" -> ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArbolBinarioDeBusquedaEnteros arbol = new ArbolBinarioDeBusquedaEnteros();

        for (int i = 0; i <= 128; i++) {
            arbol.add(i);
        }

        int suma = arbol.getSuma();
        System.out.println("Suma total (getSuma): " + suma);

        int sumaPre = sumaLista(arbol.getListaPreOrden());
        int sumaPost = sumaLista(arbol.getListaPostOrden());
        int sumaCentral = sumaLista(arbol.getListaOrdenCentral());

        System.out.println("Suma PreOrden: " + sumaPre);
        System.out.println("Suma PostOrden: " + sumaPost);
        System.out.println("Suma OrdenCentral: " + sumaCentral);

        ArbolBinarioDeBusqueda<Integer> izq = arbol.getSubarbolIzq();
        ArbolBinarioDeBusqueda<Integer> der = arbol.getSubarbolDer();

        int sumaIzq = sumaLista(izq.getListaPreOrden());
        int sumaDer = sumaLista(der.getListaPreOrden());

        System.out.println("Suma raíz + subárboles = " + (sumaIzq + sumaDer));

        System.out.println("Altura del árbol: " + arbol.getAltura());

        ListaSimple<Integer> camino = arbol.getCamino(110);
        System.out.print("Camino hasta 110: ");
        imprimirCamino(camino);
        System.out.println("Longitud del camino: " + camino.getSize());
    }
}