package examen1;

public class funciones {

    public static void main(String[] args) {
        System.out.println("Ejecutando pruebas...");

        // Llamar a los métodos de prueba
        testProductoImpares();
        testSumaGeometricaAlternada();
        testCombinatorioSinMultiplosDeTres();
    }

    public static int productoImpares(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("El parámetro n debe ser positivo y mayor que 0");
        }

        int producto = 1;
        int numeroImpar = 1;

        for (int i = 0; i < n; i++) {
            producto *= numeroImpar;
            numeroImpar += 2;
        }

        return producto;
    }

    public static double sumaGeometricaAlternada(double a1, double r, int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("k debe ser mayor que 0");
        }
        if (a1 <= 0 || r <= 0) {
            throw new IllegalArgumentException("a1 y r deben ser positivos");
        }

        double suma = 0.0;
        for (int n = 1; n <= k; n++) {
            double termino = Math.pow(-1, n) * a1 * Math.pow(r, n - 1);
            suma += termino;
        }

        return suma;
    }

    public static int factorialSinMultiplosDeTres(int num) {
        int resultado = 1;
        for (int i = 1; i <= num; i++) {
            if (i % 3 != 0) {
                resultado *= i;
            }
        }
        return resultado;
    }

    public static int combinatorioSinMultiplosDeTres(int n, int k) {
        if (n < k) {
            throw new IllegalArgumentException("n debe ser mayor o igual a k");
        }
        if (n < 0 || k < 0) {
            throw new IllegalArgumentException("n y k deben ser positivos");
        }

        int numerador = factorialSinMultiplosDeTres(n);
        int denominadorK = factorialSinMultiplosDeTres(k);
        int denominadorNK = factorialSinMultiplosDeTres(n - k);

        return numerador / (denominadorK * denominadorNK);
    }

    private static void testProductoImpares() {
        System.out.println("Test productoImpares:");
        try {
            System.out.println(productoImpares(3) == 15);
            System.out.println(productoImpares(5) == 945);
            System.out.println(productoImpares(1) == 1);
            try {
                productoImpares(0);
            } catch (IllegalArgumentException e) {
                System.out.println("Correcto: Error esperado para n=0");
            }
        } catch (Exception e) {
            System.out.println("Error inesperado en productoImpares: " + e.getMessage());
        }
        System.out.println();
    }

    private static void testSumaGeometricaAlternada() {
        System.out.println("Test sumaGeometricaAlternada:");
        try {
            System.out.println(sumaGeometricaAlternada(2, 3, 4) == (2 - 6 + 18 - 54));
            System.out.println(sumaGeometricaAlternada(1, 2, 3) == (1 - 2 + 4));
            try {
                sumaGeometricaAlternada(2, 3, 0);
            } catch (IllegalArgumentException e) {
                System.out.println("Correcto: Error esperado para k=0");
            }
        } catch (Exception e) {
            System.out.println("Error inesperado en sumaGeometricaAlternada: " + e.getMessage());
        }
        System.out.println();
    }

    private static void testCombinatorioSinMultiplosDeTres() {
        System.out.println("Test combinatorioSinMultiplosDeTres:");
        try {
            System.out.println(combinatorioSinMultiplosDeTres(5, 2));
            try {
                combinatorioSinMultiplosDeTres(4, 5);
            } catch (IllegalArgumentException e) {
                System.out.println("Correcto: Error esperado para n < k");
            }
        } catch (Exception e) {
            System.out.println("Error inesperado en combinatorioSinMultiplosDeTres: " + e.getMessage());
        }
        System.out.println();
    }
}


 