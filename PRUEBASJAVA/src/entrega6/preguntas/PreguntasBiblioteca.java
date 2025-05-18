package entrega6.preguntas;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

	public class PreguntasBiblioteca {

		public static String masVecesPrestadoImperativo() {
		    Map<String, Integer> conteoPrestamos = new HashMap<>();
		    int maxPrestamos = 0;

		    // Contar préstamos por ISBN
		    try (BufferedReader br = new BufferedReader(new FileReader("biblioteca/prestamos.txt"))) {
		        String linea;
		        while ((linea = br.readLine()) != null) {
		            String[] partes = linea.split(",");
		            String isbn = partes[0];
		            int nuevoConteo = conteoPrestamos.getOrDefault(isbn, 0) + 1;
		            conteoPrestamos.put(isbn, nuevoConteo);

		            if (nuevoConteo > maxPrestamos) {
		                maxPrestamos = nuevoConteo;
		            }
		        }
		    } catch (IOException e) {
		        e.printStackTrace();
		        return "Error leyendo el archivo de préstamos.";
		    }

		    // Buscar los ISBN con mayor número de préstamos
		    List<String> isbnsMaximos = new ArrayList<>();
		    for (Map.Entry<String, Integer> entry : conteoPrestamos.entrySet()) {
		        if (entry.getValue() == maxPrestamos) {
		            isbnsMaximos.add(entry.getKey());
		        }
		    }

		    // Buscar títulos en libros.txt
		    Map<String, String> titulosPorIsbn = new HashMap<>();
		    try (BufferedReader br = new BufferedReader(new FileReader("biblioteca/libros.txt"))) {
		        String linea;
		        while ((linea = br.readLine()) != null) {
		            String[] partes = linea.split(",");
		            String isbn = partes[0];
		            String titulo = partes[1];
		            if (isbnsMaximos.contains(isbn)) {
		                titulosPorIsbn.put(isbn, titulo);
		            }
		        }
		    } catch (IOException e) {
		        e.printStackTrace();
		        return "Error leyendo el archivo de libros.";
		    }

		    // Construir resultado
		    StringBuilder resultado = new StringBuilder("Libros más prestados (" + maxPrestamos + " veces):\n");
		    for (String isbn : isbnsMaximos) {
		        String titulo = titulosPorIsbn.getOrDefault(isbn, "Título desconocido");
		        resultado.append("- ").append(titulo).append("\n");
		    }

		    return resultado.toString().trim();
		}

		public static String masVecesPrestadoFuncional() {
		    try {
		        // Contar cantidad de préstamos por ISBN
		        Map<String, Long> conteo = Files.lines(Paths.get("biblioteca/prestamos.txt"))
		                .map(linea -> linea.split(",")[0])
		                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		        // Calcular valor máximo
		        long maxPrestamos = conteo.values().stream().mapToLong(Long::longValue).max().orElse(0);

		        // Filtrar los ISBN que tengan ese máximo
		        Set<String> isbnsMaximos = conteo.entrySet().stream()
		                .filter(entry -> entry.getValue() == maxPrestamos)
		                .map(Map.Entry::getKey)
		                .collect(Collectors.toSet());

		        // Buscar títulos de esos ISBN
		        List<String> resultados = Files.lines(Paths.get("biblioteca/libros.txt"))
		                .map(linea -> linea.split(","))
		                .filter(partes -> isbnsMaximos.contains(partes[0]))
		                .map(partes -> "- " + partes[1])
		                .collect(Collectors.toList());

		        if (resultados.isEmpty()) {
		            return "No se encontraron títulos para los ISBN más prestados.";
		        }

		        return "Libros más prestados (" + maxPrestamos + " veces):\n" + String.join("\n", resultados);

		    } catch (IOException e) {
		        return "Error leyendo los archivos.";
		    }
		}

	
	public static Map<String, Set<String>> librosPorAutorImperativo(List<String> nombres) {
	    Map<String, Set<String>> resultado = new HashMap<>();

	    try (BufferedReader br = new BufferedReader(new FileReader("biblioteca/libros.txt"))) {
	        String linea;
	        while ((linea = br.readLine()) != null) {
	            String[] partes = linea.split(",");
	            if (partes.length < 3) continue;

	            String isbn = partes[0];
	            String titulo = partes[1];
	            String autor = partes[2];

	            // Si nombres es null, aceptamos todos. Si no, solo si el autor está en la lista
	            if (nombres == null || nombres.contains(autor)) {
	                if (!resultado.containsKey(autor)) {
	                    resultado.put(autor, new HashSet<>());
	                }
	                resultado.get(autor).add(titulo);
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return resultado;
	}
	public static Map<String, Set<String>> librosPorAutorFuncional(List<String> nombres) {
	    try {
	        return Files.lines(Paths.get("biblioteca/libros.txt"))
	                .map(linea -> linea.split(","))
	                .filter(partes -> partes.length > 2)
	                .filter(partes -> nombres == null || nombres.contains(partes[2]))
	                .collect(Collectors.groupingBy(
	                        partes -> partes[2],
	                        Collectors.mapping(partes -> partes[1], Collectors.toSet())
	                ));
	    } catch (IOException e) {
	        e.printStackTrace();
	        return Collections.emptyMap();
	    }
	}

	}



