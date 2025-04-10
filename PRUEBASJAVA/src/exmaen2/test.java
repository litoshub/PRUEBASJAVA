package exmaen2;

import java.util.List;
import java.util.Map;

public class test {

    public static void main(String[] args) {
        // Crear clientes y compras
        Cliente ana = Cliente.of("Ana", 5);
        Cliente juan = Cliente.of("Juan", 2);
        Cliente luis = Cliente.of("Luis", 7);

        Compra c1 = Compra.of(ana, "Agenda personalizada", 25.5);
        Compra c2 = Compra.of(juan, "Camiseta estampada", 60.0);
        Compra c3 = Compra.of(ana, "Taza con foto", 15.0);
        Compra c4 = Compra.of(luis, "Poster gigante", 80.0);

        // Ejecutar las pruebas
        testCliente(ana, juan, luis);
        testCompra(c1, c2, c3, c4);
        testClientesPorAntiguedad();
        testHistorialCompras(ana, juan, luis, c1, c2, c3, c4);
        testColaComprasPendientes(c1, c2, c3, c4);
        testEstadisticasClientes(c1, c2, c3, c4);
    }

    private static void testCliente(Cliente ana, Cliente juan, Cliente luis) {
    	System.out.println();
        System.out.println(
        		"----- Prueba de Cliente -----");
        
        // Test: Crear cliente
        System.out.println("Cliente creado: " + ana);

        // Test: Método toString
        String expectedString = "Cliente[nombre=Ana, antigüedad=5]";
        System.out.println("toString() esperado: " + expectedString);
        System.out.println("toString() real: " + ana.toString());

        // Test: Método equals
        Cliente cliente2 = Cliente.of("Ana", 5);
        System.out.println("¿Clientes son iguales? " + ana.equals(cliente2)); // Debería ser true

        // Test: Método hashCode
        System.out.println("hashCode de cliente: " + ana.hashCode());
    }

    private static void testCompra(Compra c1, Compra c2, Compra c3, Compra c4) {
    	System.out.println();
        System.out.println(
        		""
        		+ "----- Prueba de Compra -----");

        // Test: Crear compra
        System.out.println("Compra creada: " + c1);

        // Test: Método toString
        String expectedString = "Compra[Nombre de cliente= Ana, descripción= Agenda personalizada, importe= 25.5 €]";
        System.out.println("toString() esperado: " + expectedString);
        System.out.println("toString() real: " + c1.toString());
    }

    private static void testClientesPorAntiguedad() {
    	System.out.println();
        System.out.println("----- Prueba de ClientesPorAntiguedad -----");
        
        // Crear la lista de clientes por antigüedad
        ClientesPorAntiguedad clientesPorAntiguedad = new ClientesPorAntiguedad();
        
        // Añadir los clientes a la lista ordenada
        clientesPorAntiguedad.add(new Cliente("Ana", 5));
        clientesPorAntiguedad.add(new Cliente("Juan", 2));
        clientesPorAntiguedad.add(new Cliente("Luis", 7));
        
        // Top 2 clientes con más antigüedad
        List<Cliente> topClientes = clientesPorAntiguedad.topClientes(2);
        
        System.out.println("Top 2 clientes con más antigüedad: " + topClientes);
    }

    private static void testHistorialCompras(Cliente ana, Cliente juan, Cliente luis, Compra c1, Compra c2, Compra c3, Compra c4) {
    	System.out.println();
    	System.out.println("----- Prueba de HistorialCompras -----");

        // Crear historial de compras
        HistorialCompras historial = new HistorialCompras();

        // Añadir compras al historial
        historial.add(c1);
        historial.add(c2);
        historial.add(c3);
        historial.add(c4);

        // Test: Total gastado por cliente "Ana"
        double totalGastado = historial.totalGastadoPor(ana);
        System.out.println("Total gastado por Ana: " + totalGastado); // Esperado: 40.5

        // Test: Filtrar compras mayores a 20
        List<Compra> comprasMayores = historial.comprasMayoresA(20);
        System.out.println("Compras mayores a 20: " + comprasMayores);
    }

    private static void testColaComprasPendientes(Compra c1, Compra c2, Compra c3, Compra c4) {
    	System.out.println();
    	System.out.println(
        		"----- Prueba de ColaComprasPendientes -----");

        // Crear cola de compras pendientes
        ColaComprasPendientes colaCompras = new ColaComprasPendientes();

        // Añadir compras pendientes
        colaCompras.add(c1);
        colaCompras.add(c2);
        colaCompras.add(c3);
        colaCompras.add(c4);

        // Test: Buscar compra por descripción
        Compra compraBuscada = colaCompras.buscarCompraPorDescripcion("Taza");
        System.out.println("Compra encontrada por descripción: " + compraBuscada);

        Cliente ana = null;
		// Test: Filtrar compras de un cliente por producto
        List<Compra> comprasFiltradas = colaCompras.filtrarPorClienteYProducto(ana, "Taza");
        System.out.println("Compras filtradas por cliente y producto: " + comprasFiltradas);
    }

    private static void testEstadisticasClientes(Compra c1, Compra c2, Compra c3, Compra c4) {
    	System.out.println();
    	System.out.println(
        		"----- Prueba de EstadisticasClientes -----");

        // Crear lista de compras
        List<Compra> compras = List.of(c1, c2, c3, c4);

        // Test: Agrupar compras por cliente (funcional)
        Map<Cliente, List<Compra>> comprasPorClienteFuncional = EstadisticasClientes.agruparComprasPorCliente(compras);
        System.out.println("Compras agrupadas (funcional): " + comprasPorClienteFuncional);

        // Test: Agrupar compras por cliente (imperativa)
        Map<Cliente, List<Compra>> comprasPorClienteImperativo = EstadisticasClientes.agruparComprasPorClienteImperativo(compras);
        System.out.println("Compras agrupadas (imperativa): " + comprasPorClienteImperativo);
    }
}
