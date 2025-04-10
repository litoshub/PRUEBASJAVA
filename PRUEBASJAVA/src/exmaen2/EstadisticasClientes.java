package exmaen2;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;



public class EstadisticasClientes {

    // Versión funcional: agrupar compras por cliente
    public static Map<Cliente, List<Compra>> agruparComprasPorCliente(List<Compra> compras) {
        return compras.stream()
            .collect(Collectors.groupingBy(Compra::getCliente));
    }

    // Versión imperativa: agrupar compras por cliente
    public static Map<Cliente, List<Compra>> agruparComprasPorClienteImperativo(List<Compra> compras) {
        Map<Cliente, List<Compra>> result = new HashMap<>();
        for (Compra compra : compras) {
            result.computeIfAbsent(compra.getCliente(), k -> new ArrayList<>()).add(compra);
        }
        return result;
    }
}

