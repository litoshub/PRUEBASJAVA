package exmaen2;


import entrega2.Pila;

import java.util.List;
import java.util.stream.Collectors;

public class HistorialCompras extends Pila<Compra> {

    // Método totalGastadoPor: calcula el total gastado por un cliente específico
    public double totalGastadoPor(Cliente cliente) {
        return elements().stream()
            .filter(compra -> compra.getCliente().equals(cliente))
            .mapToDouble(Compra::getImporte)
            .sum();
    }

    // Método comprasMayoresA: devuelve las compras cuyo importe supera la cantidad indicada
    public List<Compra> comprasMayoresA(double cantidad) {
        return elements().stream()
            .filter(compra -> compra.getImporte() > cantidad)
            .collect(Collectors.toList());
    }
}
