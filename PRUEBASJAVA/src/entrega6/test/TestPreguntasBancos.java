package entrega6.test;

import java.time.LocalDate;
import java.util.Map;

import entrega6.preguntas.PreguntasBancos;

public class TestPreguntasBancos {
	public static void main(String[] args) {
        PreguntasBancos preguntas = new PreguntasBancos();
        Map<String, Double> resultado = preguntas.valorTotalPrestamosImperativo(19, 20.0, 100000.0, LocalDate.of(2015, 12, 31));

        System.out.println("Resultados de valorTotalPrestamosImperativo:");
        // Mostrar resultados por consola
        for (Map.Entry<String, Double> entry : resultado.entrySet()) {
            System.out.println("DNI cliente: " + entry.getKey() + " → Total préstamos: " + entry.getValue());
            
            
        }
        Map<String, Double> resultado1 = preguntas.valorTotalPrestamosFuncional(
        		80, 20.0, 100000.0, LocalDate.of(2015, 12, 31)
        );
        System.out.println();
        // Mostrar resultados por consola
        System.out.println("Resultados de valorTotalPrestamosFuncional:");
        resultado1.forEach((dni, total) -> 
            System.out.println("DNI cliente: " + dni + " → Total préstamos válidos: " + total)
        );
    }
    }

