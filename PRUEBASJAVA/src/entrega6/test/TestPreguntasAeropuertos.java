package entrega6.test;
import java.time.*;

import entrega6.preguntas.PreguntasAeropuertos;
public class TestPreguntasAeropuertos {
	public static void main(String[] args) {System.out.println("Test ciudadAeropuertoMayorFacturacionImperativo:");
	LocalDateTime inicio = LocalDateTime.of(2020, 1, 1, 0, 0);
	LocalDateTime fin = LocalDateTime.of(2020, 12, 31, 23, 59);
	String resultado = PreguntasAeropuertos.ciudadAeropuertoMayorFacturacionImperativo(inicio, fin);
	System.out.println(resultado);
	System.out.println();
	System.out.println("Test ciudadAeropuertoMayorFacturacionFuncional:");
	LocalDateTime inicio1 = LocalDateTime.of(2020, 1, 1, 0, 0);
	LocalDateTime fin1 = LocalDateTime.of(2020, 12, 31, 23, 59);
	String resultado1 = PreguntasAeropuertos.ciudadAeropuertoMayorFacturacionFuncional(inicio1, fin1);
	System.out.println(resultado1);



}
}
