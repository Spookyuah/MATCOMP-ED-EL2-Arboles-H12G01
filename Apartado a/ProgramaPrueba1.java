public class ProgramaPrueba1 {

    public static void main(String[] args) {

        ArbolBinarioDeBusquedaEnteros arbol = new ArbolBinarioDeBusquedaEnteros();

        for (int i = 0; i <= 128; i++) {
            arbol.add(i);
        }

        System.out.println("Suma: " + arbol.getSuma());

        Lista<Integer> pre = arbol.getListaPreOrden();
        Lista<Integer> post = arbol.getListaPostOrden();
        Lista<Integer> central = arbol.getListaOrdenCentral();

        int s1 = 0, s2 = 0, s3 = 0;
        for (int i = 0; i < pre.getTamaño(); i++) s1 += pre.obtener(i);
        for (int i = 0; i < post.getTamaño(); i++) s2 += post.obtener(i);
        for (int i = 0; i < central.getTamaño(); i++) s3 += central.obtener(i);

        System.out.println("Suma preorden: " + s1);
        System.out.println("Suma postorden: " + s2);
        System.out.println("Suma orden central: " + s3);

        int raiz = arbol.raiz.dato;
        int sumaIzq = arbol.getSubArbolIzquierda().getSuma();
        int sumaDer = arbol.getSubArbolDerecha().getSuma();
        System.out.println("Raiz: " + raiz);
        System.out.println("Suma subarbol izq: " + sumaIzq);
        System.out.println("Suma subarbol der: " + sumaDer);
        System.out.println("Suma total subarboles: " + (raiz + sumaIzq + sumaDer));

        System.out.println("Altura: " + arbol.getAltura());

        Lista<Integer> camino = arbol.getCamino(110);
        System.out.println("Camino hasta 110: " + camino);
        System.out.println("Longitud camino: " + (camino.getTamaño() - 1));

        System.out.println("Grado: " + arbol.getGrado());
        System.out.println("Homogeneo: " + arbol.isArbolHomogeneo());
        System.out.println("Completo: " + arbol.isArbolCompleto());
        System.out.println("Casi completo: " + arbol.isArbolCasiCompleto());
    }
}
