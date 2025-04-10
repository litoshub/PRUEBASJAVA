package entrega2;
import java.util.ArrayList;
import java.util.List;

//Clase abstracta AgregadoLineal que define una estructura de datos lineal genérica.
public abstract class AgregadoLineal<E> {
 
 // Lista que almacena los elementos del tipo genérico E.
 // La palabra "protected" hace que esta lista sea accesible en la clase actual y en sus subclases.
 protected ArrayList<E> elements = new ArrayList<>();
 
 // Método que devuelve el número de elementos en la lista.
 // Utiliza el método size() de ArrayList para obtener el tamaño actual.
 public int size() {
     return elements.size();
 }

 // Método que verifica si la lista está vacía.
 // Usa el método isEmpty() de ArrayList para determinar si la lista tiene elementos o no.
 public boolean isEmpty() {
     return elements.isEmpty();
 }

 // Método que devuelve una copia de la lista "elements".
 // Se crea una nueva ArrayList para asegurar que no se modifica directamente la lista interna.
 public List<E> elements() {
     return new ArrayList<>(elements);
 }

 // Método abstracto que debe ser implementado por las subclases.
 // Se espera que cada subclase defina cómo agregar un elemento al AgregadoLineal.
 public abstract void add(E e);

 // Método que agrega todos los elementos de una lista pasada como parámetro.
 // Recorre la lista y agrega cada elemento a la lista interna usando el método add.
 public void addAll(List<E> list) {
     for (E element : list) {
         add(element);  // Utiliza el método add() para agregar cada elemento individualmente.
     }
 }

 // Método que elimina el primer elemento de la lista "elements" y lo devuelve.
 // Si la lista está vacía, devuelve null.
 // Puede ser útil lanzar una excepción si la lista está vacía en lugar de devolver null.
 public E remove() {
     if (!isEmpty()) {
         return elements.remove(0);  // Elimina el primer elemento de la lista y lo devuelve.
     }
     return null;  // Si la lista está vacía, devuelve null.
 }

 // Método que elimina todos los elementos de la lista "elements".
 // Devuelve una nueva lista que contiene los elementos eliminados.
 public List<E> removeAll() {
     List<E> removed = new ArrayList<>(elements);  // Crea una copia de los elementos antes de eliminarlos.
     elements.clear();  // Elimina todos los elementos de la lista interna.
     return removed;  // Devuelve la lista de elementos eliminados.
 }
}
