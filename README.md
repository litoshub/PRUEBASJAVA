# PRUEBASJAVA
#entrega 1
#modulo funciones
package entrega1;

public class funciones {
    // 1. Producto Π(n - i + 1) desde i=0 hasta k
    public static int producto(int n, int k) {
        int resultado = 1;
        for (int i = 0; i < k; i++) {
            resultado *= (n - i + 1);
        }
        return resultado;
    }

    // 2. Producto de una secuencia geométrica
    public static double productoGeometrico(double a1, double r, int k) {
        double producto = 1.0;
        for (int i = 0; i < k; i++) {
            producto *= a1 * Math.pow(r, i);
        }
        return producto;
    }

    // 3. Número combinatorio C(n, k)
    public static double combinatorio(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }
        double numerador = 1, denominador = 1;
        for (int i = 1; i <= k; i++) {
            numerador *= (n - i + 1);
            denominador *= i;
        }
        return numerador / denominador;
    }

    // 4. Cálculo del número S(n, k)
    public static double calcularS(int n, int k) {
        double sum = 0;
        for (int i = 0; i < k; i++) {
            sum += Math.pow(-1, i) * combinatorio(k + 1, i + 1) * Math.pow(k - i, n);
        }
        return sum / factorial(k);
    }

    // Función auxiliar para calcular factorial
    private static int factorial(int num) {
        int fact = 1;
        for (int i = 2; i <= num; i++) {
            fact *= i;
        }
        return fact;
    }

    // 5. Método de Newton para encontrar x0
    public static double metodoNewton(double a, double epsilon) {
        double x = a;
        while (Math.abs(funcion(x)) > epsilon) {
            x = x - funcion(x) / derivadaFuncion(x);
        }
        return x;
    }

    // Función f(x) = 2x^2
    private static double funcion(double x) {
        return 2 * Math.pow(x, 2);
    }

    // Derivada f'(x) = 4x
    private static double derivadaFuncion(double x) {
        return 4 * x;
    }

    public static void main(String[] args) {
        // Pruebas de cada función
        System.out.println("TEST DE LA FUNCIÓN 1:");
        System.out.println("El producto de 4 y 2 es: " + producto(4, 2));
        
        System.out.println("\nTEST DE LA FUNCIÓN 2:");
        System.out.println("El producto de la secuencia geométrica con a1 = 3, r = 5 y k = 2 es: " + productoGeometrico(3, 5, 2));
        
        System.out.println("\nTEST DE LA FUNCIÓN 3:");
        System.out.println("El número combinatorio de 4 y 2 es: " + combinatorio(4, 2));
        
        System.out.println("\nTEST DE LA FUNCIÓN 4:");
        System.out.println("El número S(n,k) siendo n = 4 y k = 2 es: " + calcularS(4, 2));
        
        System.out.println("\nTEST DE LA FUNCIÓN 5:");
        System.out.println("Resultado de la función 5 con a = 3 y e = 0.001, f(x) = 2x^2 y f'(x) = 4x: " + metodoNewton(3, 0.001));
    }
}

#modulo lecturas
package entrega1;

import java.io.*;
import java.util.*;


public class lecturas {
    
    // 6. Contar apariciones de una palabra en un archivo de texto
    public static int contarPalabraEnArchivo(String fichero, String sep, String cad) {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("resources/" + fichero))) {
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
    public static List<String> obtenerLineasConCadena(String fichero, String cad) {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("resources/" + fichero))) {
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
    public static Set<String> obtenerPalabrasUnicas(String fichero) {
        Set<String> palabrasUnicas = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader("resources/" + fichero))) {
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
    public static double calcularLongitudMediaLineasCSV(String fichero, String sep) {
        List<Integer> palabrasPorLinea = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("resources/" + fichero))) {
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


