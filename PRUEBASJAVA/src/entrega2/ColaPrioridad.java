package entrega2;

import java.util.ArrayList;
import java.util.List;



public class ColaPrioridad<E, P extends Comparable<P>> extends Cola<PriorityElement<E, P>> {

    public static <E, P extends Comparable<P>> ColaPrioridad<E, P> ofPriority() {
        return new ColaPrioridad<>();
    }

    @Override
    public void add(PriorityElement<E, P> element) {
        int index = 0;
        for (; index < elements.size(); index++) {
            if (elements.get(index).getPriority().compareTo(element.getPriority()) > 0) {
                break;
            }
        }
        elements.add(index, element);
    }

    public void add(E value, P priority) {
        add(new PriorityElement<>(value, priority));
    }

    public List<E> valuesAsList() {
        List<E> values = new ArrayList<>();
        for (PriorityElement<E, P> element : elements) {
            values.add(element.getValue());
        }
        return values;
    }

    public void decreasePriority(E value, P newPriority) {
        for (int i = 0; i < elements.size(); i++) {
            PriorityElement<E, P> currentElement = elements.get(i);
            if (currentElement.getValue().equals(value)) {
                elements.remove(i);
                add(new PriorityElement<>(value, newPriority));
                break;
            }
        }
    }

    public E removeValue() {
        return isEmpty() ? null : remove().getValue();
    }

    public void addAllValues(List<E> values, P priority) {
        for (E value : values) {
            add(new PriorityElement<>(value, priority));
        }
    }
}
