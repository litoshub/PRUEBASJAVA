package exmaen2;


import entrega2.ListaOrdenada;// Asegúrate de que la clase ArrayList está importada si la usas

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ClientesPorAntiguedad extends ListaOrdenada<Cliente> {

    // Constructor
    public ClientesPorAntiguedad() {
        super(Comparator.comparingInt(Cliente::getAntiguedad).reversed()); // Orden descendente por antigüedad
    }

    // Método topClientes: devuelve los n clientes con más antigüedad
    public List<Cliente> topClientes(int n) {
        List<Cliente> topClientes = new ArrayList<>();
        int count = 0;
        for (Cliente cliente : elements()) {
            if (count < n) {
                topClientes.add(cliente);
                count++;
            } else {
                break;
            }
        }
        return topClientes;
    }
}
