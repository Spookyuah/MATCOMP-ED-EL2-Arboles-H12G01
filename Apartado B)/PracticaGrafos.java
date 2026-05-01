import java.nio.file.*;
import java.util.*;

// Estructura de Nodo para la cola manual
class NodoG {
    Object v;
    NodoG next;

    NodoG(Object x) {
        v = x;
    }
}

// Implementación de cola propia para cumplir con los requisitos de la práctica
class ColaG {
    NodoG head;
    NodoG tail;

    void push(Object x) {
        NodoG n = new NodoG(x);
        if (tail != null) {
            tail.next = n;
        }
        tail = n;
        if (head == null) {
            head = n;
        }
    }

    Object pop() {
        if (head == null) return null;
        Object tmp = head.v;
        head = head.next;
        if (head == null) tail = null;
        return tmp;
    }

    boolean isEmpty() {
        return head == null;
    }
}

public class PracticaGrafos {

    // Mapa de adyacencia para representar el grafo de conocimiento
    Map<String, List<Enlace>> grafo = new HashMap<>();

    static class Enlace {
        String r; // Relación / Predicado
        String d; // Nodo destino / Objeto

        Enlace(String r, String d) {
            this.r = r;
            this.d = d;
        }
    }

    // Método para procesar el archivo JSON y construir el grafo bidireccional
    public void leerArchivo(String file) throws Exception {
        String contenido = new String(Files.readAllBytes(Paths.get(file)));
        String[] partes = contenido.split("\\{");

        for (String parte : partes) {
            if (!parte.contains("\"s\"")) continue;

            String s = sacar(parte, "s");
            String p = sacar(parte, "p");
            String o = sacar(parte, "o");

            if (!grafo.containsKey(s)) grafo.put(s, new ArrayList<>());
            if (!grafo.containsKey(o)) grafo.put(o, new ArrayList<>());

            // Se añaden ambos sentidos para permitir la navegación completa
            grafo.get(s).add(new Enlace(p, o));
            grafo.get(o).add(new Enlace(p, s));
        }
    }

    // Extrae el valor de una clave JSON ignorando espacios o saltos de línea
    private String sacar(String txt, String k) {
        int i = txt.indexOf("\"" + k + "\"");
        if (i < 0) return "";
        int inicioValor = txt.indexOf("\"", i + k.length() + 2) + 1;
        int finValor = txt.indexOf("\"", inicioValor);
        if (inicioValor <= 0 || finValor <= 0) return "";
        return txt.substring(inicioValor, finValor).trim();
    }

    // Búsqueda en anchura (BFS) para encontrar el camino más corto entre dos nodos
    public List<String> buscar(String a, String b) {
        if (!grafo.containsKey(a) || !grafo.containsKey(b)) {
            return null;
        }

        ColaG c = new ColaG();
        Set<String> visit = new HashSet<>();

        List<String> ini = new ArrayList<>();
        ini.add(a);
        c.push(ini);
        visit.add(a);

        while (!c.isEmpty()) {
            List<String> camino = (List<String>) c.pop();
            String last = camino.get(camino.size() - 1);

            if (last.equals(b)) {
                return camino;
            }

            List<Enlace> lista = grafo.get(last);
            if (lista == null) continue;

            for (Enlace e : lista) {
                if (!visit.contains(e.d)) {
                    visit.add(e.d);
                    List<String> nuevo = new ArrayList<>(camino);
                    nuevo.add(e.d);
                    c.push(nuevo);
                }
            }
        }

        // Si no hay camino, el grafo es DISJUNTO para este par de nodos
        return null;
    }

    public static void main(String[] args) {
        PracticaGrafos pr = new PracticaGrafos();
        try {
            // Carga de datos
            pr.leerArchivo("datos.json");

            // Ejemplo de búsqueda genérica (puedes cambiar los nombres según tu JSON)
            String origen = "persona:Albert Einstein";
            String destino = "persona:Marie Curie";

            List<String> resultado = pr.buscar(origen, destino);

            if (resultado != null) {
                System.out.println("Camino encontrado: " + resultado);
            } else {
                System.out.println("No existe conexión entre los nodos o el grafo es disjunto.");
            }

        } catch (Exception e) {
            System.out.println("Error: Asegúrese de que 'datos.json' existe y tiene el formato correcto.");
        }
    }
}