package examen1;

public class funciones{

    public static int productoImpares(int n) {
        // Validar que n es positivo y mayor que 0
        if (n <= 0) {
            throw new IllegalArgumentException("El parámetro n debe ser positivo y mayor que 0");
        }

        int producto = 1;
        int numeroImpar = 1;

        for (int i = 0; i < n; i++) {
            producto *= numeroImpar;
            numeroImpar += 2; // Incrementar al siguiente número impar
        }

        return producto;
    }
    public static double sumaGeometricaAlternada(double a1, double r, int k) {
        // Validaciones
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
            if (i % 3 != 0) { // Ignorar múltiplos de 3
                resultado *= i;
            }
        }
        return resultado;
    }

    public static int combinatorioSinMultiplosDeTres(int n, int k) {
        // Validaciones
        if (n < k) {
            throw new IllegalArgumentException("n debe ser mayor o igual a k");
        }
        if (n < 0 || k < 0) {
            throw new IllegalArgumentException("n y k deben ser positivos");
        }

        // Calcular el número combinatorio sin los múltiplos de 3
        int numerador = factorialSinMultiplosDeTres(n);
        int denominadorK = factorialSinMultiplosDeTres(k);
        int denominadorNK = factorialSinMultiplosDeTres(n - k);

        return numerador / (denominadorK * denominadorNK);
    }
    private static void testProductoImpares() {
        System.out.println("Test productoImpares:");
        try {
            System.out.println(funciones.productoImpares(3) == 15);
            System.out.println(funciones.productoImpares(5) == 945);
            System.out.println(funciones.productoImpares(1) == 1);
            try {
                funciones.productoImpares(0);
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
            System.out.println(funciones.sumaGeometricaAlternada(2, 3, 4) == (2 - 6 + 18 - 54));
            System.out.println(funciones.sumaGeometricaAlternada(1, 2, 3) == (1 - 2 + 4));
            try {
                funciones.sumaGeometricaAlternada(2, 3, 0);
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
            System.out.println(funciones.combinatorioSinMultiplosDeTres(5, 2));
            try {
                funciones.combinatorioSinMultiplosDeTres(4, 5);
            } catch (IllegalArgumentException e) {
                System.out.println("Correcto: Error esperado para n < k");
            }
        } catch (Exception e) {
            System.out.println("Error inesperado en combinatorioSinMultiplosDeTres: " + e.getMessage());
        }
        System.out.println();
    }
}

}
 