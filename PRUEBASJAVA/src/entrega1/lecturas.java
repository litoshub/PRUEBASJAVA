package entrega1;

import java.io.*;
import java.util.*;


public class lecturas {
    
    // 6. Contar apariciones de una palabra en un archivo de texto
    public static int contarPalabraEnArchivo(String lin_quijote, String sep, String cad) {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("resources/" + lin_quijote))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] palabras = linea.split(sep);
                count += Arrays.stream(palabras).filter(p -> p.equalsIgnoreCase(cad)).count();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
    
    // 7. Obtener líneas que contienen una cadena específica
    public static List<String> obtenerLineasConCadena(String lin_quijote, String cad) {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("resources/" + lin_quijote))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.toLowerCase().contains(cad.toLowerCase())) {
                    lineas.add(linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineas;
    }
    
    // 8. Obtener palabras únicas de un archivo de texto
    public static Set<String> obtenerPalabrasUnicas(String archivo_palabras) {
        Set<String> palabrasUnicas = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader("resources/" + archivo_palabras))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] palabras = linea.toLowerCase().split(" ");
                palabrasUnicas.addAll(Arrays.asList(palabras));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return palabrasUnicas;
    }
    
    // 9. Calcular la media de palabras por línea en un archivo CSV
    public static double calcularLongitudMediaLineasCSV(String palabras_random, String sep) {
        List<Integer> palabrasPorLinea = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("resources/" + palabras_random))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] palabras = linea.split(sep);
                int numPalabras = (int) Arrays.stream(palabras).filter(p -> !p.trim().isEmpty()).count();
                palabrasPorLinea.add(numPalabras);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return palabrasPorLinea.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }
    
    public static void main(String[] args) {
        // Pruebas de las funciones
        System.out.println("TEST DE LA FUNCIÓN 6:");
        System.out.println("El número de veces que aparece la palabra quijote en el fichero resources/lin_quijote.txt es: " + contarPalabraEnArchivo("lin_quijote.txt", " ", "quijote"));
        
        System.out.println("\nTEST DE LA FUNCIÓN 7:");
        System.out.println("Las líneas en las que aparece la palabra quijote son: " + obtenerLineasConCadena("lin_quijote.txt", "quijote"));
        
        System.out.println("\nTEST DE LA FUNCIÓN 8:");
        System.out.println("Las palabras únicas en el fichero resources/archivo_palabras.txt son: " + obtenerPalabrasUnicas("archivo_palabras.txt"));
        
        System.out.println("\nTEST DE LA FUNCIÓN 9:");
        System.out.println("La longitud promedio de las líneas del fichero resources/palabras_random.csv es: " + calcularLongitudMediaLineasCSV("palabras_random.csv", ","));
    }
}
//los outputs de los test son los correctos


