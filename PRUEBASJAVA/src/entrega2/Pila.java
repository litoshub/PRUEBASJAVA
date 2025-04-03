package entrega2;
public class Pila<E> extends AgregadoLineal<E> {
    
    // Método para añadir elementos al principio de la pila.
    @Override
    public void add(E e) {
        elements.add(0, e);
    }

    // Método para obtener el elemento en el tope de la pila sin eliminarlo.
    public E top() {
        if (!isEmpty()) {
            return elements.get(0);
        }
        return null;  // Podría lanzarse una excepción para indicar que la pila está vacía.
    }
}
