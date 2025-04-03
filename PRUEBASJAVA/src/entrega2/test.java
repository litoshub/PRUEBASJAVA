package entrega2;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class test {

    public static void main(String[] args) {
        System.out.println("===== INICIANDO PRUEBAS DE ESTRUCTURAS LINEALES =====\n");

        testListaOrdenada();
        testListaOrdenadaSinRepeticion();
        testCola();
        testPila();
        testColaPrioridad();

        System.out.println("===== TODAS LAS PRUEBAS COMPLETADAS =====");
    }

    private static void testListaOrdenada() {
        System.out.println("----- Prueba de ListaOrdenada -----");
        // Especifica explícitamente el tipo de datos para el Comparator.
        Comparator<Integer> integerComparator = Comparator.naturalOrder();
        ListaOrdenada<Integer> lista = ListaOrdenada.of(integerComparator);
        lista.addAll(Arrays.asList(5, 2, 8, 1, 3));
        System.out.println("Añadiendo elementos: 5, 2, 8, 1, 3");
        System.out.println("Elementos en la lista: " + lista.elements());
        System.out.println("Tamaño de la lista: " + lista.size());
        System.out.println("Eliminando el primer elemento: " + lista.remove());
        System.out.println("Elementos después de eliminar: " + lista.elements());
        lista.addAll(Arrays.asList(4, 6, 7));
        System.out.println("Añadiendo elementos en lote: 4, 6, 7");
        System.out.println("Elementos después de añadir lote: " + lista.elements());
        System.out.println("Eliminando todos los elementos: " + lista.removeAll());
        System.out.println("¿Está vacía? " + lista.isEmpty());
    }


    private static void testListaOrdenadaSinRepeticion() {
        System.out.println("----- Prueba de ListaOrdenadaSinRepeticion -----");
        // Especificar explícitamente el tipo de datos para el Comparator.
        Comparator<Integer> integerComparator = Comparator.naturalOrder();
        ListaOrdenadaSinRepeticion<Integer> lista = ListaOrdenadaSinRepeticion.of(integerComparator);
        lista.addAll(Arrays.asList(5, 2, 8, 1, 3, 5, 2));
        System.out.println("Añadiendo elementos: 5, 2, 8, 1, 3, 5, 2");
        System.out.println("Elementos en la lista: " + lista.elements());
        System.out.println("Tamaño de la lista: " + lista.size());
        System.out.println("Eliminando el primer elemento: " + lista.remove());
        System.out.println("Elementos después de eliminar: " + lista.elements());
        lista.addAll(Arrays.asList(4, 6, 7, 4));
        System.out.println("Añadiendo elementos en lote: 4, 6, 7, 4");
        System.out.println("Elementos después de añadir lote: " + lista.elements() + "\n");
    }


    private static void testCola() {
        System.out.println("----- Prueba de Cola (FIFO) -----");
        Cola<String> cola = Cola.of();
        cola.addAll(Arrays.asList("primero", "segundo", "tercero"));
        System.out.println("Añadiendo elementos: 'primero', 'segundo', 'tercero'");
        System.out.println("Elementos en la cola: " + cola.elements());
        System.out.println("Tamaño de la cola: " + cola.size());
        System.out.println("Desencolando elementos:");
        while (!cola.isEmpty()) {
            System.out.println("Desencolado: " + cola.remove());
            System.out.println("Cola restante: " + cola.elements());
        }
        System.out.println("¿Está vacía? " + cola.isEmpty());
        try {
            cola.remove();
        } catch (Exception e) {
            System.out.println("Excepción correctamente lanzada al intentar desencolar de una cola vacía: " + e.getMessage());
        }
    }

    private static void testPila() {
        System.out.println("----- Prueba de Pila (LIFO) -----");
        Pila<Double> pila = new Pila<>();
        pila.addAll(Arrays.asList(1.1, 2.2, 3.3));
        System.out.println("Añadiendo elementos: 1.1, 2.2, 3.3");
        System.out.println("Elementos en la pila: " + pila.elements());
        System.out.println("Tamaño de la pila: " + pila.size());
        System.out.println("Elemento en el tope: " + pila.top());
        while (!pila.isEmpty()) {
            System.out.println("Desapilado: " + pila.remove());
            System.out.println("Pila restante: " + pila.elements());
        }
        System.out.println("¿Está vacía? " + pila.isEmpty());
        try {
            pila.top();
        } catch (Exception e) {
            System.out.println("Excepción correctamente lanzada al intentar acceder al tope de una pila vacía: " + e.getMessage());
        }
    }

    private static void testColaPrioridad() {
        System.out.println("----- Prueba de ColaPrioridad -----");
        ColaPrioridad<String, Integer> cola = ColaPrioridad.ofPriority();
        cola.add("Crítico", 1);
        cola.add("Urgente", 2);
        cola.add("Normal", 3);
        cola.add("Bajo", 4);
        System.out.println("Añadiendo elementos con prioridad: 'Crítico' con prioridad 1, 'Normal' con prioridad 3, 'Urgente' con prioridad 2, 'Bajo' con prioridad 4");
        System.out.println("Elementos en la cola por prioridad: " + cola.valuesAsList());
        System.out.println("Elementos con sus prioridades: ");
        for (PriorityElement<String, Integer> elem : cola.elements()) {
            System.out.println("PriorityElement[value=" + elem.getValue() + ", priority=" + elem.getPriority() + "]");
        }
        System.out.println("Tamaño de la cola: " + cola.size());
        System.out.println("Cambiando la prioridad de 'Normal' de 3 a 1");
        cola.decreasePriority("Normal", 1);
        System.out.println("Elementos con prioridad actualizada: ");
        for (PriorityElement<String, Integer> elem : cola.elements()) {
            System.out.println("PriorityElement[value=" + elem.getValue() + ", priority=" + elem.getPriority() + "]");
        }
        while (!cola.isEmpty()) {
            System.out.println("Desencolado: " + cola.removeValue());
            System.out.println("Cola restante: " + cola.valuesAsList());
        }
        System.out.println("¿Está vacía? " + cola.isEmpty());
    }
}


