package entrega6.test;


import java.util.List;
import java.util.Map;

import entrega6.preguntas.PreguntasCentro;
public class TestPreguntasCentro 
{
	public static void main(String[] args) {
		        // Crear un objeto de la clase PreguntasCentro
		        PreguntasCentro pc = new PreguntasCentro();  // Esto cargará todos los datos de los archivos
		        System.out.println("TEST PROMEDIO EDAD PROFESORES" );
		        System.out.println( );
		        // Obtener los tres primeros alumnos
		        String dniAlumno1 = pc.alumnos.get(0)[2];  // DNI del primer alumno
		        String dniAlumno2 = pc.alumnos.get(1)[2];  // DNI del segundo alumno
		        String dniAlumno3 = pc.alumnos.get(2)[2];  // DNI del tercer alumno
		    

		        // Llamamos al método para obtener la media de edad de los profesores de cada alumno
		        double promedio1 = pc.promedioEdadProfesoresImperativo(dniAlumno1);
		        double promedio2 = pc.promedioEdadProfesoresImperativo(dniAlumno2);
		        double promedio3 = pc.promedioEdadProfesoresImperativo(dniAlumno3);
		       

		        // Imprimimos los resultados
		        System.out.println("probamos de modo imperativo " );
		        System.out.println("La media de edad de los profesores de " + dniAlumno1 + " es " + promedio1);
		        System.out.println("La media de edad de los profesores de " + dniAlumno2 + " es " + promedio2);
		        System.out.println("La media de edad de los profesores de " + dniAlumno3 + " es " + promedio3);
		      
		        
		        // Llamamos al método para obtener la media de edad de los profesores de cada alumno (funcional)
		        try {
		            double promedio1funcional = pc.promedioEdadProfesoresFuncional(dniAlumno1);
		            double promedio2funcional = pc.promedioEdadProfesoresFuncional(dniAlumno2);
		            double promedio3funcional = pc.promedioEdadProfesoresFuncional(dniAlumno3);
		            System.out.println( );
                    System.out.println("probamos de modo funcional " );
		            // Imprimimos los resultados
		            System.out.println("La media de edad de los profesores de " + dniAlumno1 + " es " + promedio1funcional);
		            System.out.println("La media de edad de los profesores de " + dniAlumno2 + " es " + promedio2funcional);
		            System.out.println("La media de edad de los profesores de " + dniAlumno3 + " es " + promedio3funcional);
		        } catch (IllegalArgumentException e) {
		            System.out.println(e.getMessage());  // Imprime el error si no se encuentran profesores
		        }
		        
		        System.out.println( );
		        System.out.println( "TEST MAYOR DIVERSIDAD EDAD");
		        System.out.println( );
		        System.out.println("imperativo" );
		        String grupoMayorDiversidadedad = pc.grupoMayorDiversidadEdad();  // Nos dará el grupo con mayor diversidad de edades		        
		        // Imprimimos los resultados
		        System.out.println("El grupo con mayor diversidad de edades es: " + grupoMayorDiversidadedad);
		        System.out.println( );
		        System.out.println("funcional" );
		        String grupoMayorDiversidadedadfunc = pc.grupoMayorDiversidadFuncional();
		        System.out.println("El grupo con mayor diversidad de edades es: " + grupoMayorDiversidadedadfunc);
		        System.out.println( );
		        System.out.println("TEST MAS MATRICULAS" );
		        System.out.println( );
		        System.out.println("imperativo" );
		        String dniAlumnoMasMatriculas = pc.alumnoMasMatriculasImperativo(); 
		        System.out.println(dniAlumnoMasMatriculas); // Nos dará el DNI del alumno con más matrículas );
		        String dniAlumnoMasMatriculasfunc = pc.alumnoMasMatriculasFuncional();
		        System.out.println("funcional" );
		        System.out.println(dniAlumnoMasMatriculasfunc  );
		       
		        // Nos dará el DNI del alumno con más matrículas
		       
		        
		      
		        
		        String rangos = "20 - 23, 24 - 26";
		        
		        System.out.println("TEST RANGOS EDAD" );
		        System.out.println( );
		        System.out.println("imperativo" );

		        // Llamamos al método imperativo
		        Map<String, List<String>> resultado = pc.rangosEdadAlumnosImperativo(rangos);

		        // Imprimir resultados para cada rango
		        for (Map.Entry<String, List<String>> entry : resultado.entrySet()) {
		            System.out.println("Alumnos con edades en el rango " + entry.getKey() + ":");
		            if (entry.getValue().isEmpty()) {
		                System.out.println("  No hay alumnos en este rango.");
		            } else {
		                for (String alumno : entry.getValue()) {
		                    System.out.println("  " + alumno);
		                }
		            }
		        }  
		        System.out.println();
	
		        System.out.println("funcional");
		        String rangosfunc = "20 - 23, 24 - 26";

		        Map<String, List<String>> resultadofunc = pc. rangosEdadAlumnosFuncional(rangosfunc);

		        for (Map.Entry<String, List<String>> entry : resultadofunc.entrySet()) {
		            System.out.println("Alumnos en rango " + entry.getKey() + ":");
		            if (entry.getValue().isEmpty()) {
		                System.out.println("  No hay alumnos en este rango.");
		            } else {
		                for (String alumno : entry.getValue()) {
		                    System.out.println("  " + alumno);
		                }
		            }
		        }
		        
	
		        
		        
		        
		        
		        
		        
		       System.out.println( );
		        System.out.println("TEST PROFESOR CON MAS GRUPOS" );      
		        System.out.println( );
		        System.out.println("imperativo" );
		                int edadMin = 22;
		                int edadMax = 40;

		                try {
		                    String profesor = pc.nombreProfesorMasGruposImperativo(edadMin, edadMax);
		                    System.out.println("Profesor con más grupos entre " + edadMin + " y " + edadMax + " años: " + profesor);
		                } catch (IllegalArgumentException e) {
		                    System.out.println("Error: " + e.getMessage());
		                }
		                
		                System.out.println( );
		                System.out.println("funcional" ); 

		                    try {
		                        String profesor = pc.nombreProfesorMasGruposFuncional(edadMin, edadMax);
		                        System.out.println("Profesor con más grupos entre " + edadMin + " y " + edadMax + " años: " + profesor);
		                    } catch (IllegalArgumentException e) {
		                        System.out.println("Error: " + e.getMessage());
		                    }
		      
		            // Carga los datos
		       System.out.println( );
		        System.out.println("TEST ALUMNOS MAYOR NOTA" );
		        System.out.println( );
		        System.out.println("imperativo" );
		            int n = 5;   // Queremos los 5 alumnos con mayor nota
		            int a = 1998; // Solo alumnos nacidos después de 1998

		            try {
		                List<String> alumnos = pc.nombresAlumnosMayorNotaImperativo(n, a);

		                System.out.println("Alumnos con mayor nota nacidos después de " + a + ":");
		                if (alumnos.isEmpty()) {
		                    System.out.println("No hay alumnos que cumplan los criterios.");
		                } else {
		                    for (String alumno : alumnos) {
		                        System.out.println(" - " + alumno);
		                    }
		                }
		            } catch (IllegalArgumentException e) {
		                System.out.println("Error: " + e.getMessage());
		            }
		            System.out.println( );
		            System.out.println("funcional" );
		            
		                try {
		                    List<String> alumnos = pc.nombresAlumnosMayorNotaFuncional(n, a);

		                    System.out.println("Alumnos con mayor nota nacidos después de " + a + ":");
		                    if (alumnos.isEmpty()) {
		                        System.out.println("No hay alumnos que cumplan los criterios.");
		                    } else {
		                        for (String alumno : alumnos) {
		                            System.out.println(" - " + alumno);
		                        }
		                    }
		                } catch (IllegalArgumentException e) {
		                    System.out.println("Error: " + e.getMessage());
		                }
	
	}
 
		}
	
		    
		    
		
 
