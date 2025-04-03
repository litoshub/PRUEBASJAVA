package entrega2;
import java.util.ArrayList;
import java.util.List;

public abstract class AgregadoLineal<E> {
    protected ArrayList<E> elements = new ArrayList<>();

    public int size() {
        return elements.size();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public List<E> elements() {
        return new ArrayList<>(elements);
    }

    public abstract void add(E e);

    public void addAll(List<E> list) {
        for (E element : list) {
            add(element);
        }
    }

    public E remove() {
        if (!isEmpty()) {
            return elements.remove(0);
        }
        return null;  // Considerar lanzar una excepción si la lista está vacía
    }

    public List<E> removeAll() {
        List<E> removed = new ArrayList<>(elements);
        elements.clear();
        return removed;
    }
}

