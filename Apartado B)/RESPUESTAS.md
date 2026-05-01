# Análisis de Grafos de Conocimiento
## Informe Técnico de Práctica de Programación

### 1. Camino Mínimo entre Entidades
El camino mínimo entre dos entidades A y B en el grafo proporcionado se determina mediante el algoritmo de **Búsqueda en Anchura (BFS)**[cite: 1].

En la implementación de `PracticaGrafos.java`, este proceso se realiza en el método `buscar(String a, String b)`[cite: 1]:
*   Utiliza una cola manual (`ColaG`) para explorar los nodos nivel por nivel[cite: 1].
*   Garantiza que el primer camino encontrado hacia el destino sea el más corto en términos de número de enlaces (aristas)[cite: 1].
*   Mantiene un registro de nodos visitados para evitar ciclos infinitos[cite: 1].

### 2. Grafos Conexos vs. Disjuntos
Un grafo es **disjunto** si existen al menos dos nodos entre los cuales no hay ruta posible[cite: 1]. El archivo de ejemplo `datos.json` genera un grafo disjunto, ya que no hay conexión entre la "isla" de Einstein y la de Marie Curie[cite: 1].

*   **Archivo disjunto (ejemplo):** Cuando existen nodos como persona:A y persona:C que no comparten ninguna relación directa o indirecta[cite: 1].
*   **Archivo conexo (ejemplo):** Cuando existe una secuencia de tripletas que une todos los nodos (A conoce a B, B conoce a C)[cite: 1].

### 3. Consulta de Información Específica
Para responder a la pregunta *"¿Qué físico famoso nació en la misma ciudad que Einstein?"*, el grafo debe contener datos sobre la profesión y el lugar de nacimiento[cite: 1].

**Lógica de verificación:**
1.  **Obtener ciudad:** Se identifica el lugar de nacimiento del sujeto de referencia (ej: "lugar: Ulm") mediante la relación `nace_en`[cite: 1].
2.  **Buscar coincidencias:** Se exploran otros nodos conectados a dicha ciudad que posean la relación `es` con el objeto `profesion: Fisico`[cite: 1].

### 4. Navegación de Tripleta y Alcance
Al añadir a **Antonio** (nacido en Villarrubia de los Caballeros), el sistema no lo incluiría automáticamente en una lista de "Lugares de nacimiento de Premios Nobel" a menos que se verifique que posee dicho premio[cite: 1].

*   **Ruta lógica:** El algoritmo debe navegar desde el nodo premio hacia la persona, y de ahí hacia el lugar: `[Premio] <-(gana_premio) [Persona] -(nace_en)-> [Lugar]`[cite: 1].

### 5. Tipos de Nodos
El grafo maneja una estructura de etiquetas para distinguir entidades[cite: 1]:
*   **persona:** Entidades biográficas (Einstein, Curie, Antonio)[cite: 1].
*   **lugar:** Entidades geográficas (Ulm, Varsovia)[cite: 1].
*   **premio:** Logros específicos (Nobel_Fisica, Nobel_Quimica)[cite: 1].

### 6. Ontología y Grafos
*   **Ontología:** Es el modelo formal que define las clases, relaciones y reglas del dominio. Mientras el grafo contiene los datos (instancias), la ontología define el esquema (ej: una Persona debe tener un lugar de nacimiento)[cite: 1].
*   **Aplicación:** Permite realizar inferencias (ej: si alguien gana el Nobel de Física, inferir que es Físico) y validaciones (ej: evitar que un Lugar gane un Premio)[cite: 1].

---
*Documento generado automáticamente - Análisis de PracticaGrafos.java*[cite: 1]