package entrega6.preguntas;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

public class PreguntasAeropuertos {
	public static String ciudadAeropuertoMayorFacturacionImperativo(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
	    if (fechaInicio == null || fechaFin == null || !fechaInicio.isBefore(fechaFin)
	            || Duration.between(fechaInicio, fechaFin).toDays() < 1) {
	        throw new IllegalArgumentException("Las fechas deben tener más de un día de diferencia y estar en orden.");
	    }

	    Map<String, Double> preciosPorCodigoVuelo = new HashMap<>(); // Ej: TP0705 → precio
	    Map<String, String> destinoPorCodigoVuelo = new HashMap<>(); // Ej: TP0705 → BER

	    try (BufferedReader br = new BufferedReader(new FileReader("aeropuertos/vuelos.csv"))) {
	        String linea;
	        while ((linea = br.readLine()) != null) {
	            String[] partes = linea.split(",");
	            if (partes.length < 5) continue;
	            String codigoVuelo = partes[0] + partes[1];
	            String codigoDestino = partes[2];
	            double precio = Double.parseDouble(partes[4]);
	            preciosPorCodigoVuelo.put(codigoVuelo, precio);
	            destinoPorCodigoVuelo.put(codigoVuelo, codigoDestino);
	        }
	    } catch (IOException e) {
	        System.out.println("⚠️ Error leyendo vuelos.csv: " + e.getMessage());
	        e.printStackTrace();
	        return "Error leyendo vuelos.csv";
	    }


	    Map<String, Double> facturacionPorDestino = new HashMap<>();

	    try (BufferedReader br = new BufferedReader(new FileReader("aeropuertos/ocupacionesVuelos.csv"))) {
	        String linea;
	        while ((linea = br.readLine()) != null) {
	            String[] partes = linea.split(",");
	            if (partes.length < 3) continue;

	            String codigoVuelo = partes[0];
	            LocalDateTime fecha = LocalDateTime.parse(partes[1].trim().replace(" ", "T"));
	            int pasajeros = Integer.parseInt(partes[2]);

	            if (!fecha.isBefore(fechaInicio) && !fecha.isAfter(fechaFin)) {
	                Double precio = preciosPorCodigoVuelo.get(codigoVuelo);
	                String destino = destinoPorCodigoVuelo.get(codigoVuelo);
	                if (precio != null && destino != null) {
	                    double ingreso = precio * pasajeros;
	                    facturacionPorDestino.put(destino,
	                            facturacionPorDestino.getOrDefault(destino, 0.0) + ingreso);
	                }
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        return "Error leyendo ocupacionesVuelos.csv";
	    }

	    // Mapeamos código aeropuerto → ciudad
	    Map<String, String> ciudadPorCodigo = new HashMap<>();
	    try (BufferedReader br = new BufferedReader(new FileReader("aeropuertos/aeropuertos.csv"))) {
	        String linea;
	        while ((linea = br.readLine()) != null) {
	            String[] partes = linea.split(",");
	            if (partes.length < 4) continue;
	            String codigo = partes[2];
	            String ciudad = partes[3];
	            ciudadPorCodigo.put(codigo, ciudad);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        return "Error leyendo aeropuertos.csv";
	    }

	    // Buscar código de aeropuerto con mayor facturación
	    String codigoMax = null;
	    double max = 0.0;
	    for (Map.Entry<String, Double> entry : facturacionPorDestino.entrySet()) {
	        if (entry.getValue() > max) {
	            max = entry.getValue();
	            codigoMax = entry.getKey();
	        }
	    }

	    if (codigoMax == null || !ciudadPorCodigo.containsKey(codigoMax)) {
	        return "No se pudo determinar la ciudad con mayor facturación.";
	    }

	    return "Ciudad con mayor facturación: " + ciudadPorCodigo.get(codigoMax) + " (" + max + "€)";
	}
	public static String ciudadAeropuertoMayorFacturacionFuncional(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
	    if (fechaInicio == null || fechaFin == null || !fechaInicio.isBefore(fechaFin)
	            || Duration.between(fechaInicio, fechaFin).toDays() < 1) {
	        throw new IllegalArgumentException("Las fechas deben tener más de un día de diferencia y estar en orden.");
	    }

	    try (
	        BufferedReader readerVuelos = new BufferedReader(
	                new InputStreamReader(new FileInputStream("aeropuertos/vuelos.csv"), StandardCharsets.ISO_8859_1));
	        BufferedReader readerAeropuertos = new BufferedReader(
	                new InputStreamReader(new FileInputStream("aeropuertos/aeropuertos.csv"), StandardCharsets.ISO_8859_1));
	        BufferedReader readerOcupaciones = new BufferedReader(
	                new InputStreamReader(new FileInputStream("aeropuertos/ocupacionesVuelos.csv"), StandardCharsets.ISO_8859_1))
	    ) {
	        // Map: códigoVuelo → [precio, destino]
	        Map<String, String[]> vuelosInfo = readerVuelos.lines()
	                .map(l -> l.split(","))
	                .filter(p -> p.length >= 5)
	                .collect(Collectors.toMap(
	                        p -> p[0] + p[1],         // códigoVuelo = códigoAerolinea + número
	                        p -> new String[]{p[4], p[2]}  // precio, destino
	                ));

	        // Map: código aeropuerto → ciudad
	        Map<String, String> codigoToCiudad = readerAeropuertos.lines()
	                .map(l -> l.split(","))
	                .filter(p -> p.length >= 4)
	                .collect(Collectors.toMap(
	                        p -> p[2],  // código aeropuerto
	                        p -> p[3]   // ciudad
	                ));

	        // Map: código aeropuerto destino → facturación total
	        Map<String, Double> facturacionPorDestino = readerOcupaciones.lines()
	                .map(l -> l.split(","))
	                .filter(p -> p.length >= 3)
	                .map(p -> {
	                    String codigoVuelo = p[0];
	                    LocalDateTime fecha = LocalDateTime.parse(p[1].trim().replace(" ", "T"));
	                    int pasajeros = Integer.parseInt(p[2]);
	                    return new Object[]{codigoVuelo, fecha, pasajeros};
	                })
	                .filter(arr -> {
	                    LocalDateTime f = (LocalDateTime) arr[1];
	                    return !f.isBefore(fechaInicio) && !f.isAfter(fechaFin);
	                })
	                .filter(arr -> vuelosInfo.containsKey((String) arr[0]))
	                .collect(Collectors.groupingBy(
	                        arr -> vuelosInfo.get((String) arr[0])[1], // códigoDestino
	                        Collectors.summingDouble(arr -> {
	                            double precio = Double.parseDouble(vuelosInfo.get((String) arr[0])[0]);
	                            return precio * (int) arr[2];
	                        })
	                ));

	        // Encontrar la ciudad con mayor facturación
	        return facturacionPorDestino.entrySet().stream()
	                .max(Map.Entry.comparingByValue())
	                .map(e -> {
	                    String codigoDestino = e.getKey();
	                    double total = e.getValue();
	                    String ciudad = codigoToCiudad.getOrDefault(codigoDestino, "Ciudad desconocida");
	                    return "Ciudad con mayor facturación: " + ciudad + " (" + total + "€)";
	                })
	                .orElse("No se encontró ninguna ciudad.");

	    } catch (IOException e) {
	        return "Error leyendo archivos: " + e.getMessage();
	    }
	

	}


}
