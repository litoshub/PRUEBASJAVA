package entrega2;
import java.util.Comparator;

public class ListaOrdenadaSinRepeticion<E> extends ListaOrdenada<E> {

    // Constructor que recibe un Comparator y lo pasa al constructor de la clase base.
    public ListaOrdenadaSinRepeticion(Comparator<E> comparator) {
        super(comparator);
    }

    // Método estático para facilitar la creación de instancias con un Comparator específico.
    public static <E> ListaOrdenadaSinRepeticion<E> of(Comparator<E> comparator) {
        return new ListaOrdenadaSinRepeticion<>(comparator);
    }

    // Sobrescribe el método add para verificar duplicados antes de añadir elementos.
    @Override
    public void add(E e) {
        if (!contains(e)) {
            super.add(e);
        }
    }

    // Método privado para verificar si un elemento ya existe en la lista.
    private boolean contains(E e) {
        return elements.contains(e);
    }
}
