package entrega6.test;

import java.util.*;

import entrega6.preguntas.PreguntasBiblioteca;

public class TestPreguntasBiblioteca {
    public static void main(String[] args) {
        System.out.println("Test de masVecesPrestadoImperativo:");
        String resultado = PreguntasBiblioteca.masVecesPrestadoImperativo();
        System.out.println(resultado);
        System.out.println();
        System.out.println("Test de masVecesPrestadoFuncional:");
        String resultadoFuncional = PreguntasBiblioteca.masVecesPrestadoFuncional();
        System.out.println(resultadoFuncional);
    
        System.out.println("🔹 Test librosPorAutorImperativo - Todos los autores (nombres = null):");
        Map<String, Set<String>> todosAutoresImp = PreguntasBiblioteca.librosPorAutorImperativo(null);
        todosAutoresImp.forEach((autor, titulos) -> {
            System.out.println("Autor: " + autor);
            titulos.forEach(titulo -> System.out.println("  - " + titulo));
        });

        System.out.println("\n🔹 Test librosPorAutorImperativo - Solo un autor:");
        Map<String, Set<String>> unAutorImp = PreguntasBiblioteca.librosPorAutorImperativo(
                Arrays.asList("Leoncio Esteban Casanovas Méndez")
        );
        unAutorImp.forEach((autor, titulos) -> {
            System.out.println("Autor: " + autor);
            titulos.forEach(titulo -> System.out.println("  - " + titulo));
        });

        System.out.println("\n🔹 Test librosPorAutorFuncional - Todos los autores (nombres = null):");
        Map<String, Set<String>> todosAutoresFun = PreguntasBiblioteca.librosPorAutorFuncional(null);
        todosAutoresFun.forEach((autor, titulos) -> {
            System.out.println("Autor: " + autor);
            titulos.forEach(titulo -> System.out.println("  - " + titulo));
        });

        System.out.println("\n🔹 Test librosPorAutorFuncional - Solo un autor:");
        Map<String, Set<String>> unAutorFun = PreguntasBiblioteca.librosPorAutorFuncional(
                Arrays.asList("Leoncio Esteban Casanovas Méndez")
        );
        unAutorFun.forEach((autor, titulos) -> {
            System.out.println("Autor: " + autor);
            titulos.forEach(titulo -> System.out.println("  - " + titulo));
        });



    }
}
