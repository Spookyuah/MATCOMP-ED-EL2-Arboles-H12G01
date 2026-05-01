import java.util.Random;
import ListasSimples.*;

public class ArbolBinarioDeBusquedaTestAleatorio {
    public static int sumaLista(ListaSimple<Integer> lista) {
        int suma = 0;
        IteradorSimple<Integer> it = lista.getIterador();
        while (it.hasNext()) {
            suma += it.next();
        }
        return suma;
    }

    public static void imprimirCamino(ListaSimple<Integer> camino) {
        if (camino == null) {
            System.out.println("No encontrado");
            return;
        }
        IteradorSimple<Integer> it = camino.getIterador();
        while (it.hasNext()) {
            Integer valor = it.next();
            System.out.print(valor);

            if (it.hasNext()) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    public static int longitudLista(ListaSimple<Integer> lista) {
        if (lista == null) return 0;
        int contador = 0;
        IteradorSimple<Integer> it = lista.getIterador();
        while (it.hasNext()) {
            it.next();
            contador++;
        }
        return contador;
    }

    public static void mezclar(int[] datos) {
        Random rnd = new Random();

        for (int i = datos.length - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);

            int aux = datos[i];
            datos[i] = datos[j];
            datos[j] = aux;
        }
    }

    public static void main(String[] args) {
        ArbolBinarioDeBusquedaEnteros arbol = new ArbolBinarioDeBusquedaEnteros();

        int[] datos = new int[129];
        for (int i = 0; i <= 128; i++) {
            datos[i] = i;
        }

        mezclar(datos);

        for (int i = 0; i < datos.length; i++) {
            arbol.add(datos[i]);
        }

        int suma = arbol.getSuma();
        System.out.println("Suma total (getSuma): " + suma);

        int sumaPre = sumaLista(arbol.getListaPreOrden());
        int sumaPost = sumaLista(arbol.getListaPostOrden());
        int sumaCentral = sumaLista(arbol.getListaOrdenCentral());

        System.out.println("Suma PreOrden: " + sumaPre);
        System.out.println("Suma PostOrden: " + sumaPost);
        System.out.println("Suma OrdenCentral: " + sumaCentral);

        IteradorSimple<Integer> itRaiz = arbol.getListaPreOrden().getIterador();
        int raiz = itRaiz.next();

        ArbolBinarioDeBusqueda<Integer> izq = arbol.getSubarbolIzq();
        ArbolBinarioDeBusqueda<Integer> der = arbol.getSubarbolDer();

        int sumaIzq = sumaLista(izq.getListaPreOrden());
        int sumaDer = sumaLista(der.getListaPreOrden());

        System.out.println("Suma raíz + subárboles = " + (raiz + sumaIzq + sumaDer));

        System.out.println("Altura del árbol: " + arbol.getAltura());

        ListaSimple<Integer> camino = arbol.getCamino(110);
        System.out.print("Camino hasta 110: ");
        imprimirCamino(camino);
        System.out.println("Longitud del camino: " + longitudLista(camino));
    }
}