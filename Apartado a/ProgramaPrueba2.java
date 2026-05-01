import java.util.Random;

public class ProgramaPrueba2 {

    public static void main(String[] args) {
        ArbolBinarioDeBusquedaEnteros arbol = new ArbolBinarioDeBusquedaEnteros();

        int[] numeros = new int[129];
        for (int i = 0; i <= 128; i++) {
            numeros[i] = i;
        }

        Random random = new Random();
        for (int i = 128; i > 0; i--) {
            int indice = random.nextInt(i + 1);
            int temp = numeros[indice];
            numeros[indice] = numeros[i];
            numeros[i] = temp;
        }

        for (int n : numeros) {
            arbol.add(n);
        }

        int suma = arbol.getSuma();
        System.out.println("=== PROGRAMA DE PRUEBA 2: Insercion aleatoria (0 a 128 sin repetir) ===");
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
        System.out.println("   -> Con insercion aleatoria, el arbol tiende a estar mas balanceado.");
        System.out.println("      La altura esperada es aproximadamente O(log n), en este caso alrededor de 14-20.");
        System.out.println("      Es mucho menor que 128 (arbol degenerado del Programa 1).");
        System.out.println();

        Lista<Integer> camino110 = arbol.getCamino(110);
        int longitudCamino = camino110.getTamaño() - 1;
        System.out.println("5. Camino hasta el valor 110:");
        System.out.println("   " + camino110);
        System.out.println("   Longitud del camino: " + longitudCamino);
        System.out.println("   -> El camino es mucho mas corto que en el Programa 1 gracias al balanceo del arbol.");
        System.out.println();

        System.out.println("6. Propiedades del arbol:");
        System.out.println("   Grado:                " + arbol.getGrado());
        System.out.println("   Es homogeneo:         " + arbol.isArbolHomogeneo());
        System.out.println("   Es completo:          " + arbol.isArbolCompleto());
        System.out.println("   Es casi completo:     " + arbol.isArbolCasiCompleto());
        System.out.println();

        System.out.println("=== DIFERENCIAS ENTRE PROGRAMA 1 Y PROGRAMA 2 ===");
        System.out.println();
        System.out.println("1. SUMA: Identica en ambos casos (8256) porque contienen los mismos elementos.");
        System.out.println("2. ALTURA: Programa 1 = 128 (degenerado) vs Programa 2 = ~14-20 (balanceado).");
        System.out.println("   Insercion ordenada produce un arbol degenerado (lista enlazada).");
        System.out.println("   Insercion aleatoria produce un arbol aproximadamente balanceado.");
        System.out.println("3. CAMINO A 110: Programa 1 = longitud 110 (recorre 0..110) vs Programa 2 = mucho menor.");
        System.out.println("4. PROPIEDADES: Programa 1 NO es homogeneo/completo/casi completo.");
        System.out.println("   Programa 2 tampoco lo sera con alta probabilidad (insercion aleatoria).");
        System.out.println();
        System.out.println("=== EJECUCIONES MULTIPLES ===");
        System.out.println();
        System.out.println("Programa 1: Siempre resultados identicos (insercion determinista).");
        System.out.println("Programa 2: La suma siempre es 8256, pero la altura y el camino a 110");
        System.out.println("            varian en cada ejecucion debido a la aleatoriedad del orden de insercion.");
    }
}
