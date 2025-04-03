package entrega2;
import java.util.Comparator;

public class ListaOrdenada<E> extends AgregadoLineal<E> {
    private Comparator<E> comparator;

    // Constructor que recibe un comparador.
    public ListaOrdenada(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    // Método estático para crear instancias de ListaOrdenada con un comparador.
    public static <E> ListaOrdenada<E> of(Comparator<E> comparator) {
        return new ListaOrdenada<>(comparator);
    }

    

    // Método para añadir un elemento en la posición correcta según el orden especificado por el comparador.
    @Override
    public void add(E e) {
        int index = indexOrder(e);
        elements.add(index, e);
    }

    // Método privado para encontrar la posición correcta del elemento en la lista ordenada.
    private int indexOrder(E e) {
        for (int i = 0; i < elements.size(); i++) {
            if (comparator.compare(elements.get(i), e) > 0) {
                return i;
            }
        }
        return elements.size();
    }
}

