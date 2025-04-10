package exmaen2;
import java.util.List;
import java.util.stream.Collectors;
import entrega2.Cola;

public class ColaComprasPendientes extends Cola<Compra> {

    // Método buscarCompraPorDescripcion: busca la primera compra cuya descripción contenga la cadena indicada
    public Compra buscarCompraPorDescripcion(String desc) {
        for (Compra compra : elements()) {
            if (compra.getDescripcion().contains(desc)) {
                return compra;
            }
        }
        return null;
    }

    // Método filtrarPorClienteYProducto: devuelve todas las compras pendientes de un cliente que contengan el producto indicado en su descripción
    public List<Compra> filtrarPorClienteYProducto(Cliente cliente, String producto) {
        return elements().stream()
            .filter(compra -> compra.getCliente().equals(cliente) && compra.getDescripcion().contains(producto))
            .collect(Collectors.toList());
    }
}
