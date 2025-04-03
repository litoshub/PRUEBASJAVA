package entrega2;

public class Cola<E> extends AgregadoLineal<E> {


    // Constructor de la clase Cola
    public Cola() {
        super();
    }

    // Método estático para crear instancias de Cola.
    public static <E> Cola<E> of() {
        return new Cola<>();
    }

    // Método para añadir elementos al final de la cola.
    @Override
    public void add(E e) {
        elements.add(e);
    }

    // Método para remover el primer elemento de la cola.
    @Override
    public E remove() {
        if (!isEmpty()) {
            return elements.remove(0);
        }
        return null;  // O podría lanzarse una excepción para indicar que la cola está vacía.
    }
}
