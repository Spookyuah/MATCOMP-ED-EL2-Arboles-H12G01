public class ProgramaPrueba1 {

    public static void main(String[] args) {
        ArbolBinarioDeBusquedaEnteros arbol = new ArbolBinarioDeBusquedaEnteros();

        for (int i = 0; i <= 128; i++) {
            arbol.add(i);
        }

        int suma = arbol.getSuma();
        System.out.println("=== PROGRAMA DE PRUEBA 1: Inserción ordenada (0 a 128) ===");
        System.out.println();

        System.out.println("1. Suma de los elementos del arbol: " + suma);
        System.out.println();

        Lista<Integer> preOrden = arbol.getListaPreOrden();
        Lista<Integer> postOrden = arbol.getListaPostOrden();
        Lista<Integer> ordenCentral = arbol.getListaOrdenCentral();

        int sumaPreOrden = 0;
        for (int i = 0; i < preOrden.getTamaño(); i++) {
            sumaPreOrden += preOrden.obtener(i);
        }

        int sumaPostOrden = 0;
        for (int i = 0; i < postOrden.getTamaño(); i++) {
            sumaPostOrden += postOrden.obtener(i);
        }

        int sumaOrdenCentral = 0;
        for (int i = 0; i < ordenCentral.getTamaño(); i++) {
            sumaOrdenCentral += ordenCentral.obtener(i);
        }

        System.out.println("2. Verificacion de sumas por recorrido:");
        System.out.println("   Preorden:       " + sumaPreOrden + " -> " + (sumaPreOrden == suma ? "CORRECTO" : "INCORRECTO"));
        System.out.println("   Postorden:      " + sumaPostOrden + " -> " + (sumaPostOrden == suma ? "CORRECTO" : "INCORRECTO"));
        System.out.println("   Orden central:  " + sumaOrdenCentral + " -> " + (sumaOrdenCentral == suma ? "CORRECTO" : "INCORRECTO"));
        System.out.println();

        int sumaSubIzq = arbol.getSubArbolIzquierda().getSuma();
        int sumaSubDer = arbol.getSubArbolDerecha().getSuma();
        int raizValor = arbol.raiz.dato;
        int sumaSubArboles = sumaSubIzq + sumaSubDer + raizValor;

        System.out.println("3. Verificacion de suma por subarboles:");
        System.out.println("   Raiz:           " + raizValor);
        System.out.println("   Suma subarbol izquierdo:  " + sumaSubIzq);
        System.out.println("   Suma subarbol derecho:    " + sumaSubDer);
        System.out.println("   Suma total (raiz + izq + der): " + sumaSubArboles);
        System.out.println("   Coincide con getSuma(): " + (sumaSubArboles == suma ? "CORRECTO" : "INCORRECTO"));
        System.out.println("   -> Por que: La suma total es igual porque los subarboles izquierdo y derecho");
        System.out.println("      junto con la raiz contienen TODOS los elementos del arbol, distribuidos de");
        System.out.println("      forma disjunta. Cada elemento pertenece a exactamente uno de estos conjuntos.");
        System.out.println();

        int altura = arbol.getAltura();
        System.out.println("4. Altura del arbol: " + altura);
        System.out.println("   -> El arbol insertado en orden degenera en una lista enlazada hacia la derecha.");
        System.out.println("      Cada nodo solo tiene hijo derecho, por lo que la altura es n (128 para 129 nodos).");
        System.out.println();

        Lista<Integer> camino110 = arbol.getCamino(110);
        int longitudCamino = camino110.getTamaño() - 1;
        System.out.println("5. Camino hasta el valor 110:");
        System.out.println("   " + camino110);
        System.out.println("   Longitud del camino: " + longitudCamino);
        System.out.println("   -> Dado que es un arbol degenerado, el camino recorre todos los nodos desde 0 hasta 110.");
        System.out.println();

        System.out.println("6. Propiedades del arbol:");
        System.out.println("   Grado:                " + arbol.getGrado());
        System.out.println("   Es homogeneo:         " + arbol.isArbolHomogeneo());
        System.out.println("   Es completo:          " + arbol.isArbolCompleto());
        System.out.println("   Es casi completo:     " + arbol.isArbolCasiCompleto());
    }
}
