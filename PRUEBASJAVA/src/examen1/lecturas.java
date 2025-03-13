package examen1;
import java.io.*;
import java.util.*;
import java.io.IOException;
import java.util.List;

public class lecturas{
    public static List<String> filtrarLineasConsecutivas(String nombreArchivo, List<String> palabrasClave) {
        List<String> lineasFiltradas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("resources/" + nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] palabras = linea.split("\\s+"); // Dividir en palabras
                for (int i = 0; i < palabras.length - 1; i++) {
                    if (palabrasClave.contains(palabras[i]) && palabrasClave.contains(palabras[i + 1])) {
                        lineasFiltradas.add(linea);
                        break; // Evitar agregar la misma línea varias veces
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return lineasFiltradas;
    }
    public static void main(String[] args) {
        testFiltrarLineasConsecutivas();
    }

    private static void testFiltrarLineasConsecutivas() {
        System.out.println("Test filtrarLineasConsecutivas:");
        try {
            List<String> resultado = lecturas.filtrarLineasConsecutivas("resources/lin_quijote.txt", List.of("Don", "Quijote"));
            System.out.println(!resultado.isEmpty()); // Verifica que haya resultados
            
            resultado = lecturas.filtrarLineasConsecutivas("resources/lin_quijote.txt", List.of("palabraInexistente"));
            System.out.println(resultado.isEmpty()); // Debe ser vacío
            
            try {
                lecturas.filtrarLineasConsecutivas("archivo_no_existente.txt", List.of("Don"));
            } catch (IOException e) {
                System.out.println("Correcto: Error esperado al leer archivo inexistente");
            }
        } catch (Exception e) {
            System.out.println("Error inesperado en filtrarLineasConsecutivas: " + e.getMessage());
        }
        System.out.println();
    }
}



