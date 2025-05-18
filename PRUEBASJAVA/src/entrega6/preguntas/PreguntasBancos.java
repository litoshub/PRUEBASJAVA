package entrega6.preguntas;

import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.util.*;
import java.util.stream.*;

public class PreguntasBancos {
	public Map<String, Double> valorTotalPrestamosImperativo(int edadLimite, double valorMinimo, double valorMaximo, LocalDate fechaLimite) {
	    // Validación de parámetros de entrada
	    if (edadLimite <= 18) 
	        throw new IllegalArgumentException("La edad debe ser mayor que 18");
	    if (valorMinimo <= 0 || valorMaximo <= 0) 
	        throw new IllegalArgumentException("Los valores mínimo y máximo deben ser positivos");
	    if (valorMinimo >= valorMaximo) 
	        throw new IllegalArgumentException("El valor mínimo debe ser menor que el máximo");

	    // Mapa para guardar la fecha de nacimiento de cada cliente por su DNI
	    Map<String, LocalDate> mapaFechasNacimiento = new HashMap<>();

	    // Mapa donde se acumulará el resultado final: DNI → suma total de préstamos válidos
	    Map<String, Double> resultado = new HashMap<>();

	    // ========================================
	    // Paso 1: Leer archivo personas.txt
	    // ========================================
	    try (BufferedReader br = new BufferedReader(new FileReader("bancos/personas.txt"))) {
	        String linea;
	        while ((linea = br.readLine()) != null) {
	            String[] partes = linea.split(",");

	            String dni = partes[2].trim(); // DNI de la persona
	            String fechaNacimientoTexto = partes[3].trim().split(" ")[0]; // Solo la fecha, sin hora

	            LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoTexto);
	            mapaFechasNacimiento.put(dni, fechaNacimiento); // Guardar en el mapa
	        }
	    } catch (IOException ex) {
	        throw new RuntimeException("Error leyendo el archivo personas.txt", ex);
	    }

	    // ========================================
	    // Paso 2: Leer archivo prestamos.txt
	    // ========================================
	    try (BufferedReader br = new BufferedReader(new FileReader("bancos/prestamos.txt"))) {
	        String linea;
	        while ((linea = br.readLine()) != null) {
	            String[] partes = linea.split(",");

	            String dniCliente = partes[1].trim(); // DNI del cliente del préstamo
	            String fechaPrestamoTexto = partes[3].trim().split(" ")[0]; // Fecha del préstamo
	            double cantidadPrestamo = Double.parseDouble(partes[5].trim()); // Cantidad solicitada

	            // Comprobar si el cliente existe en personas.txt
	            if (!mapaFechasNacimiento.containsKey(dniCliente)) continue;

	            // Calcular la edad actual del cliente
	            LocalDate fechaNacimiento = mapaFechasNacimiento.get(dniCliente);
	            int edadCliente = Period.between(fechaNacimiento, LocalDate.now()).getYears();

	            // Comprobamos todas las condiciones necesarias
	            boolean edadValida = edadCliente < edadLimite;
	            boolean cantidadValida = cantidadPrestamo >= valorMinimo && cantidadPrestamo <= valorMaximo;
	            boolean fechaValida = LocalDate.parse(fechaPrestamoTexto).isAfter(fechaLimite);

	            if (edadValida && cantidadValida && fechaValida) {
	                // Si ya hay una entrada, sumamos; si no, comenzamos con el primer préstamo
	                resultado.put(dniCliente, resultado.getOrDefault(dniCliente, 0.0) + cantidadPrestamo);
	            }
	        }
	    } catch (IOException ex) {
	        throw new RuntimeException("Error leyendo el archivo prestamos.txt", ex);
	    }

	    // Devolver el mapa con los resultados acumulados
	    return resultado;
	}


	    public Map<String, Double> valorTotalPrestamosFuncional(int edadLimitef, double valorMinimof, double valorMaximof, LocalDate fechaLimitef) {
	        // Validación de parámetros
	        if (edadLimitef <= 18)
	            throw new IllegalArgumentException("La edad debe ser mayor que 18");
	        if (valorMinimof <= 0 || valorMaximof <= 0)
	            throw new IllegalArgumentException("Los valores mínimo y máximo deben ser positivos");
	        if (valorMinimof >= valorMaximof)
	            throw new IllegalArgumentException("El valor mínimo debe ser menor que el máximo");

	        // ================================
	        // Paso 1: Cargar personas.txt en un Map<DNI, LocalDate>
	        // ================================
	        Map<String, LocalDate> fechasNacimiento = leerLineas("bancos/personas.txt").stream()
	            .map(linea -> linea.split(","))
	            .collect(Collectors.toMap(
	                partes -> partes[2].trim(), // clave = DNI
	                partes -> LocalDate.parse(partes[3].trim().split(" ")[0]) // valor = fecha de nacimiento
	            ));

	        // ================================
	        // Paso 2: Procesar prestamos.txt funcionalmente
	        // ================================
	        return leerLineas("bancos/prestamos.txt").stream()
	            .map(linea -> linea.split(","))
	            .filter(partes -> fechasNacimiento.containsKey(partes[1].trim())) // cliente existe
	            .map(partes -> {
	                String dni = partes[1].trim();
	                LocalDate fechaNacimiento = fechasNacimiento.get(dni);
	                int edadCliente = Period.between(fechaNacimiento, LocalDate.now()).getYears();
	                LocalDate fechaPrestamo = LocalDate.parse(partes[3].trim().split(" ")[0]);
	                double cantidad = Double.parseDouble(partes[5].trim());

	                return new PrestamoFiltrado(dni, edadCliente, fechaPrestamo, cantidad);
	            })
	            .filter(p -> p.edad < edadLimitef)
	            .filter(p -> p.fechaPrestamo.isAfter(fechaLimitef))
	            .filter(p -> p.cantidad >= valorMinimof && p.cantidad <= valorMaximof)
	            .collect(Collectors.groupingBy(
	                p -> p.dni,
	                Collectors.summingDouble(p -> p.cantidad)
	            ));
	    }

	    // Clase auxiliar para representar los datos del préstamo ya transformados
	    private static class PrestamoFiltrado {
	        String dni;
	        int edad;
	        LocalDate fechaPrestamo;
	        double cantidad;

	        PrestamoFiltrado(String dni, int edad, LocalDate fechaPrestamo, double cantidad) {
	            this.dni = dni;
	            this.edad = edad;
	            this.fechaPrestamo = fechaPrestamo;
	            this.cantidad = cantidad;
	        }
	    }

	    // Método auxiliar para leer todas las líneas de un archivo
	    private List<String> leerLineas(String ruta) {
	        try {
	            return Files.readAllLines(Paths.get(ruta));
	        } catch (IOException e) {
	            throw new RuntimeException("Error leyendo archivo: " + ruta, e);
	        }
	    }
	}



