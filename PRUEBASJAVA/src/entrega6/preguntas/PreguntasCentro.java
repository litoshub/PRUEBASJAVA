package entrega6.preguntas;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.*;
		 
public class PreguntasCentro {

    // Listas para almacenar los datos cargados de los archivos
    public List<String[]> matriculas = new ArrayList<>();
    public List<String[]> profesores = new ArrayList<>();
    public List<String[]> alumnos = new ArrayList<>();
    public List<String[]> asignaturas = new ArrayList<>();
    public List<String[]> asignaciones = new ArrayList<>();

    // Constructor para cargar los datos
    public PreguntasCentro() {
        cargarDatos();  // Llamamos al método que carga todos los archivos
    }

    // Método para cargar todos los archivos
    public void cargarDatos() {
        cargarArchivo("centro/profesores.txt", profesores, "Profesores");
        cargarArchivo("centro/alumnos.txt", alumnos, "Alumnos");
        cargarArchivo("centro/asignaturas.txt", asignaturas, "Asignaturas");
        cargarArchivo("centro/matriculas.txt", matriculas, "Matrículas");
        cargarArchivo("centro/asignaciones.txt", asignaciones, "Asignaciones");
    }

    // Método general para cargar archivos y almacenarlos en las listas correspondientes
    private void cargarArchivo(String rutaArchivo, List<String[]> listaDestino, String nombreArchivo) {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(rutaArchivo))) {
            System.out.println("--- Leyendo archivo: " + nombreArchivo + " ---");
            
            String linea;
            while ((linea = br.readLine()) != null) {
                listaDestino.add(linea.split(","));  // Guardamos cada línea del archivo como un array en la lista
            }

            System.out.println("\n--- Fin de archivo: " + nombreArchivo + " ---\n");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + rutaArchivo);
            e.printStackTrace();
        }
    }

    public double promedioEdadProfesoresImperativo(String dniAlumno) {
        List<String[]> profesoresDelAlumno = new ArrayList<>();  // Lista para almacenar profesores

        // Paso 1: Encontrar las asignaturas y grupos del alumno
        List<Integer> asignaturasGrupos = new ArrayList<>();
        for (String[] matricula : matriculas) {
            if (matricula[0].equals(dniAlumno)) {  // Si el DNI del alumno coincide
                int idAsignatura = Integer.parseInt(matricula[1]);  // Asignatura del alumno
                int grupo = Integer.parseInt(matricula[2]);  // Grupo de la asignatura
                asignaturasGrupos.add(idAsignatura);  // Guardamos las asignaturas en la lista
            }
        }

        // Paso 2: Encontrar los profesores que enseñan esas asignaturas y grupos
        for (String[] asignacion : asignaciones) {
            int idAsignatura = Integer.parseInt(asignacion[1]);
            int grupo = Integer.parseInt(asignacion[2]);
            
            // Si la asignatura y grupo del profesor coinciden con los del alumno
            if (asignaturasGrupos.contains(idAsignatura)) {
                for (String[] profesor : profesores) {
                    if (profesor[2].equals(asignacion[0])) {  // Si el DNI del profesor coincide
                        profesoresDelAlumno.add(profesor);  // Añadimos el profesor a la lista
                    }
                }
            }
        }

        // Paso 3: Calcular el promedio de edades de los profesores encontrados
        if (profesoresDelAlumno.isEmpty()) {
            throw new IllegalArgumentException("El alumno no tiene profesores asignados.");
        }

        int sumaEdades = 0;
        for (String[] profesor : profesoresDelAlumno) {
            int edad = calcularEdad(profesor[3]);  // Calculamos la edad del profesor a partir de su fecha de nacimiento
            sumaEdades += edad;  // Sumamos la edad
        }

        // Paso 4: Calcular el promedio
        return (double) sumaEdades / profesoresDelAlumno.size();  // Promedio de edades
    }

    // Método auxiliar para calcular la edad a partir de la fecha de nacimiento
    private int calcularEdad(String fechaNacimiento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDate fecha = LocalDate.parse(fechaNacimiento, formatter);
        return Period.between(fecha, LocalDate.now()).getYears();
    }
    public double promedioEdadProfesoresFuncional(String dniAlumno) {
        // Paso 1: Encontrar las asignaturas y grupos del alumno
        List<Integer> asignaturasGrupos = matriculas.stream()
            .filter(matricula -> matricula[0].equals(dniAlumno))  // Filtra las matriculas por el DNI del alumno
            .map(matricula -> Integer.parseInt(matricula[1]))  // Obtiene el ID de la asignatura
            .collect(Collectors.toList());  // Recolecta el resultado en una lista

        // Paso 2: Filtrar los profesores que enseñan esas asignaturas y grupos
        List<String[]> profesoresDelAlumno = asignaciones.stream()
            .filter(asignacion -> asignaturasGrupos.contains(Integer.parseInt(asignacion[1])))  // Filtra por asignatura
            .map(asignacion -> profesores.stream()
                .filter(profesor -> profesor[2].equals(asignacion[0]))  // Filtra por el DNI del profesor
                .findFirst().orElse(null))  // Obtiene el primer profesor que coincide o null si no existe
            .collect(Collectors.toList());  // Recolecta los resultados en una lista

        // Paso 3: Calcular el promedio de edades de los profesores encontrados
        return profesoresDelAlumno.stream()
            .filter(Objects::nonNull)  // Filtra los valores nulos (en caso de que no se haya encontrado el profesor)
            .mapToInt(profesor -> calcularEdad(profesor[3]))  // Convierte a edad de cada profesor
            .average()  // Calcula el promedio de las edades
            .orElse(0);  // Si no hay profesores, retorna 0
    }
    public String grupoMayorDiversidadEdad() {
        String grupoMayorDiversidad = "";
        int mayorDiversidad = 0;

        // Paso 1: Recorrer todas las asignaturas
        for (String[] asignatura : asignaturas) {
            // Paso 2: Recorrer todos los grupos de la asignatura
            int idAsignatura = Integer.parseInt(asignatura[0].trim());  // ID de la asignatura
            int numeroGrupos = Integer.parseInt(asignatura[3].trim());  // Número de grupos de esa asignatura

            for (int grupo = 0; grupo < numeroGrupos; grupo++) {
                // Paso 3: Encontrar las edades de los alumnos en ese grupo
                List<Integer> edades = new ArrayList<>();
                for (String[] matricula : matriculas) {
                    if (Integer.parseInt(matricula[1].trim()) == idAsignatura && Integer.parseInt(matricula[2].trim()) == grupo) {
                        // Buscar el alumno en la lista de alumnos por su DNI
                        for (String[] alumno : alumnos) {
                            if (alumno[2].equals(matricula[0])) {  // Si el DNI del alumno coincide
                                int edad = calcularEdad(alumno[3]);  // Calculamos la edad del alumno
                                edades.add(edad);  // Añadimos la edad a la lista
                            }
                        }
                    }
                }

                // Paso 4: Calcular la diferencia entre la edad máxima y mínima en este grupo
                if (!edades.isEmpty()) {
                    int edadMaxima = Collections.max(edades);  // Edad máxima en el grupo
                    int edadMinima = Collections.min(edades);  // Edad mínima en el grupo
                    int diversidadEdad = edadMaxima - edadMinima;

                    // Si la diversidad de este grupo es mayor, lo actualizamos
                    if (diversidadEdad > mayorDiversidad) {
                        mayorDiversidad = diversidadEdad;
                        grupoMayorDiversidad = "Grupo " + grupo;  // Solo nos interesa el grupo
                    }
                }
            }
        }

        return grupoMayorDiversidad;  // Retornamos el grupo con mayor diversidad
    }
    public String grupoMayorDiversidadFuncional() {
        // Paso 1: Recorrer todas las asignaturas y grupos usando Streams
        return asignaturas.stream()
            .flatMap(asignatura -> {
                // Obtener el ID de la asignatura y el número de grupos
                int idAsignatura = Integer.parseInt(asignatura[0].trim());
                int numeroGrupos = Integer.parseInt(asignatura[3].trim());

                // Paso 2: Recorrer cada grupo de la asignatura
                return IntStream.range(0, numeroGrupos)
                    .mapToObj(grupo -> {
                        // Paso 3: Encontrar las edades de los alumnos en ese grupo
                        List<Integer> edades = matriculas.stream()
                            .filter(matricula -> Integer.parseInt(matricula[1].trim()) == idAsignatura && Integer.parseInt(matricula[2].trim()) == grupo)
                            .flatMap(matricula -> alumnos.stream()
                                .filter(alumno -> alumno[2].equals(matricula[0]))  // Verificar DNI del alumno
                                .map(alumno -> calcularEdad(alumno[3])))  // Obtener la edad del alumno
                            .collect(Collectors.toList());

                        // Paso 4: Calcular la diversidad de edades (diferencia entre la edad máxima y mínima)
                        if (!edades.isEmpty()) {
                            int edadMaxima = Collections.max(edades);
                            int edadMinima = Collections.min(edades);
                            int diversidadEdad = edadMaxima - edadMinima;
                            
                            // Devolver el grupo con su diversidad de edades
                            return new AbstractMap.SimpleEntry<>(  "Grupo " + grupo, diversidadEdad);
                        } else {
                            return null;  // Si no hay alumnos, no devolvemos nada
                        }
                    })
                    .filter(Objects::nonNull);  // Filtrar grupos que no tengan alumnos
            })
            // Paso 5: Ordenar por la diversidad de edades y devolver el grupo con mayor diversidad
            .max(Comparator.comparingInt(Map.Entry::getValue))
            .map(Map.Entry::getKey)
            .orElse("No se encontró ningún grupo con diversidad de edades");
    }
    public String alumnoMasMatriculasImperativo() {
        Map<String, Integer> matriculasPorAlumno = new HashMap<>();  // Mapa para contar las matrículas de cada alumno

        // Paso 1: Recorrer todas las matrículas
        for (String[] matricula : matriculas) {
            String dniAlumno = matricula[0];  // El DNI del alumno
            matriculasPorAlumno.put(dniAlumno, matriculasPorAlumno.getOrDefault(dniAlumno, 0) + 1);  // Aumentamos el contador de matrículas
        }

        // Paso 2: Encontrar el alumno con más matrículas
        String alumnoConMasMatriculas = "";
        int maxMatriculas = 0;

        for (Map.Entry<String, Integer> entry : matriculasPorAlumno.entrySet()) {
            String alumno = entry.getKey();
            int cantidadMatriculas = entry.getValue();

            // Si el alumno tiene más matrículas que el actual, lo actualizamos
            if (cantidadMatriculas > maxMatriculas) {
                maxMatriculas = cantidadMatriculas;
                alumnoConMasMatriculas = alumno;
            }
        }

        // Paso 3: Devolver el resultado
        return "El alumno con más matrículas es: " + alumnoConMasMatriculas + " con " + maxMatriculas + " matrículas.";
    }
    public String alumnoMasMatriculasFuncional() {
        // Paso 1: Contar las matrículas por alumno (DNI)
        Map<String, Long> conteoMatriculas = matriculas.stream()
            .collect(Collectors.groupingBy(matricula -> matricula[0], Collectors.counting()));

        // Paso 2: Encontrar el alumno con más matrículas
        Optional<Map.Entry<String, Long>> alumnoMaxMatriculas = conteoMatriculas.entrySet().stream()
            .max(Map.Entry.comparingByValue());

        // Paso 3: Devolver resultado o mensaje si no hay alumnos
        return alumnoMaxMatriculas
            .map(entry -> "El alumno con más matrículas es: " + entry.getKey() + " con " + entry.getValue() + " matrículas.")
            .orElse("No hay alumnos matriculados.");
    }
    public Map<String, List<String>> rangosEdadAlumnosImperativo(String rangos) {
        // Paso 1: Validar que la cadena de rangos no esté vacía ni sea nula
        if (rangos == null || rangos.trim().isEmpty()) {
            throw new IllegalArgumentException("La cadena de rangos no puede estar vacía");
        }

        // Paso 2: Separar la cadena por comas para obtener los rangos individuales
        String[] rangosSeparados = rangos.split(",");

        // Paso 3: Validar formato de cada rango y parsear a enteros (min y max)
        List<int[]> rangosValidos = new ArrayList<>();
        for (String rango : rangosSeparados) {
            String rangoLimpio = rango.trim();
            if (!rangoLimpio.matches("\\d+\\s*-\\s*\\d+")) {
                throw new IllegalArgumentException("Formato incorrecto en rango: " + rangoLimpio);
            }
            String[] partes = rangoLimpio.split("-");
            int min = Integer.parseInt(partes[0].trim());
            int max = Integer.parseInt(partes[1].trim());

            if (min > max) {
                throw new IllegalArgumentException("El valor mínimo no puede ser mayor que el máximo en el rango: " + rangoLimpio);
            }
            rangosValidos.add(new int[] {min, max});
        }

        // Paso 4: Verificar que no haya solapamientos en los rangos
        rangosValidos.sort(Comparator.comparingInt(a -> a[0]));
        for (int i = 1; i < rangosValidos.size(); i++) {
            if (rangosValidos.get(i)[0] <= rangosValidos.get(i - 1)[1]) {
                throw new IllegalArgumentException("Los rangos se solapan: " + rangosSeparados[i-1] + " y " + rangosSeparados[i]);
            }
        }

        // Paso 5: Crear el mapa resultado donde cada rango es clave y lista vacía de alumnos es valor
        Map<String, List<String>> resultado = new HashMap<>();
        for (String rango : rangosSeparados) {
            resultado.put(rango.trim(), new ArrayList<>());
        }

        // Paso 6: Recorrer todos los alumnos, calcular su edad y asignarlos al rango correcto
        for (String[] alumno : alumnos) {
            int edad = calcularEdad(alumno[3]);  // Fecha de nacimiento en alumno[3]
            String nombreCompleto = alumno[0] + " " + alumno[1];  // Apellidos + nombre

            // Buscar en qué rango de edad cae el alumno
            for (int i = 0; i < rangosValidos.size(); i++) {
                int min = rangosValidos.get(i)[0];
                int max = rangosValidos.get(i)[1];
                if (edad >= min && edad <= max) {
                    String rangoTexto = rangosSeparados[i].trim();
                    resultado.get(rangoTexto).add(nombreCompleto + " (" + edad + " años)");
                    break;  // Una vez asignado, salimos del bucle de rangos
                }
            }
        }

        // Paso 7: Devolver el mapa con los rangos y los alumnos asignados
        return resultado;
    }
    public Map<String, List<String>> rangosEdadAlumnosFuncional(String rangos) {
        if (rangos == null || rangos.trim().isEmpty()) {
            throw new IllegalArgumentException("La cadena de rangos no puede estar vacía");
        }

        // Parsear y validar rangos
        List<int[]> rangosValidos = Arrays.stream(rangos.split(","))
            .map(String::trim)
            .peek(rango -> {
                if (!rango.matches("\\d+\\s*-\\s*\\d+")) {
                    throw new IllegalArgumentException("Formato incorrecto en rango: " + rango);
                }
            })
            .map(rango -> {
                String[] partes = rango.split("-");
                int min = Integer.parseInt(partes[0].trim());
                int max = Integer.parseInt(partes[1].trim());
                if (min > max) throw new IllegalArgumentException("Min mayor que max en rango: " + rango);
                return new int[]{min, max};
            })
            .sorted(Comparator.comparingInt(a -> a[0]))
            .collect(Collectors.toList());

        // Validar solapamientos
        IntStream.range(1, rangosValidos.size())
            .forEach(i -> {
                if (rangosValidos.get(i)[0] <= rangosValidos.get(i-1)[1])
                    throw new IllegalArgumentException("Solapamiento en rangos.");
            });

        // Crear mapa con rangos vacíos
        Map<String, List<String>> resultado = Arrays.stream(rangos.split(","))
            .map(String::trim)
            .collect(Collectors.toMap(r -> r, r -> new ArrayList<>()));

        // Procesar alumnos asignándolos a rangos
        alumnos.stream()
            .map(alumno -> {
                int edad = calcularEdad(alumno[3]);
                String nombreCompleto = alumno[0] + " " + alumno[1];
                return new AbstractMap.SimpleEntry<>(nombreCompleto, edad);
            })
            // Para cada alumno, buscamos en qué rangos cae y emitimos pares (rango, alumno)
            .flatMap(entry -> IntStream.range(0, rangosValidos.size())
                .filter(i -> entry.getValue() >= rangosValidos.get(i)[0] && entry.getValue() <= rangosValidos.get(i)[1])
                .mapToObj(i -> new AbstractMap.SimpleEntry<>(Arrays.stream(rangos.split(",")).map(String::trim).toArray(String[]::new)[i],
                                                              entry.getKey() + " (" + entry.getValue() + " años)")))
            // Agrupar por rango
            .collect(Collectors.groupingBy(Map.Entry::getKey,
                   Collectors.mapping(Map.Entry::getValue, Collectors.toList())))
            .forEach((rango, alumnosLista) -> resultado.put(rango, alumnosLista));

        return resultado;
    }


    public String nombreProfesorMasGruposImperativo(int edadMinima, int edadMaxima) {
        // Paso 1: Validar que edadMinima sea menor que edadMaxima
        if (edadMinima >= edadMaxima) {
            throw new IllegalArgumentException("La edad mínima debe ser menor que la máxima.");
        }

        // Paso 2: Crear mapa para contar grupos por profesor que cumplen rango de edad
        Map<String, Integer> gruposPorProfesor = new HashMap<>();

        // Paso 3: Recorrer la lista de profesores para filtrar por edad
        for (String[] profesor : profesores) {
            int edadProfesor = calcularEdad(profesor[3]);  // profesor[3] es la fecha de nacimiento
            if (edadProfesor >= edadMinima && edadProfesor <= edadMaxima) {
                String dniProfesor = profesor[2];
                gruposPorProfesor.put(dniProfesor, 0);  // Inicializar contador en 0
            }
        }

        // Paso 4: Recorrer las asignaciones y contar grupos para profesores filtrados
        for (String[] asignacion : asignaciones) {
            String dniProfesor = asignacion[0];
            if (gruposPorProfesor.containsKey(dniProfesor)) {
                gruposPorProfesor.put(dniProfesor, gruposPorProfesor.get(dniProfesor) + 1);
            }
        }

        // Paso 5: Encontrar el profesor con más grupos
        String profesorMaxGrupos = null;
        int maxGrupos = 0;

        for (Map.Entry<String, Integer> entry : gruposPorProfesor.entrySet()) {
            if (entry.getValue() > maxGrupos) {
                maxGrupos = entry.getValue();
                profesorMaxGrupos = entry.getKey();
            }
        }

        // Paso 6: Obtener el nombre completo del profesor con más grupos
        if (profesorMaxGrupos == null) {
            return "No hay profesores en el rango de edad especificado.";
        }

        for (String[] profesor : profesores) {
            if (profesor[2].equals(profesorMaxGrupos)) {
                return profesor[0] + " " + profesor[1];  // Apellidos + Nombre
            }
        }

        return "Profesor no encontrado.";
    }
    public String nombreProfesorMasGruposFuncional(int edadMinima, int edadMaxima) {
        if (edadMinima >= edadMaxima) {
            throw new IllegalArgumentException("La edad mínima debe ser menor que la máxima.");
        }

        // Filtrar profesores por edad, mapear a DNI, contar grupos y obtener el máximo
        return profesores.stream()
            .filter(profesor -> {
                int edad = calcularEdad(profesor[3]);
                return edad >= edadMinima && edad <= edadMaxima;
            })
            .collect(Collectors.groupingBy(
                profesor -> profesor[2],  // Clave: DNI del profesor
                Collectors.counting()     // Valor: número de veces que aparece (asignaciones directas no usadas aquí)
            ))
            .entrySet()
            .stream()
            .map(entry -> {
                long numGrupos = asignaciones.stream()
                    .filter(a -> a[0].equals(entry.getKey()))
                    .count();
                return new AbstractMap.SimpleEntry<>(entry.getKey(), numGrupos);
            })
            .max(Comparator.comparingLong(Map.Entry::getValue))
            .flatMap(maxEntry -> profesores.stream()
                .filter(p -> p[2].equals(maxEntry.getKey()))
                .findFirst()
                .map(p -> p[0] + " " + p[1]))
            .orElse("No hay profesores en el rango de edad especificado.");
    }

    public List<String> nombresAlumnosMayorNotaImperativo(Integer n, Integer a) {
        // Paso 1: Validar parámetros de entrada
        if (n == null || a == null) {
            throw new IllegalArgumentException("Los parámetros 'n' y 'a' no pueden ser null.");
        }
        if (n < 1 || n > 10) {
            throw new IllegalArgumentException("El parámetro 'n' debe estar entre 1 y 10 inclusive.");
        }

        // Paso 2: Filtrar alumnos nacidos después del año 'a'
        List<String[]> alumnosFiltrados = new ArrayList<>();
        for (String[] alumno : alumnos) {
            String fechaNacimiento = alumno[3];  // Fecha en formato yyyy-MM-dd HH:mm o similar
            int anioNacimiento = Integer.parseInt(fechaNacimiento.substring(0, 4));  // Extraemos el año

            if (anioNacimiento > a) {
                alumnosFiltrados.add(alumno);
            }
        }

        // Paso 3: Ordenar alumnos filtrados por nota media descendente
        alumnosFiltrados.sort((al1, al2) -> {
            // alumno[6] es la nota media en formato String, convertimos a Double
            Double nota1 = Double.parseDouble(al1[6]);
            Double nota2 = Double.parseDouble(al2[6]);
            return nota2.compareTo(nota1);  // Orden descendente
        });

        // Paso 4: Construir la lista de nombres completos de los primeros n alumnos
        List<String> resultado = new ArrayList<>();
        for (int i = 0; i < n && i < alumnosFiltrados.size(); i++) {
            String[] alumno = alumnosFiltrados.get(i);
            String nombreCompleto = alumno[0] + " " + alumno[1];  // Apellidos + nombre
            resultado.add(nombreCompleto);
        }

        // Paso 5: Devolver la lista de nombres
        return resultado;
    }
    public List<String> nombresAlumnosMayorNotaFuncional(Integer n, Integer a) {
        // Validar parámetros
        if (n == null || a == null) {
            throw new IllegalArgumentException("Los parámetros 'n' y 'a' no pueden ser null.");
        }
        if (n < 1 || n > 10) {
            throw new IllegalArgumentException("El parámetro 'n' debe estar entre 1 y 10 inclusive.");
        }

        return alumnos.stream()
            // Filtrar alumnos nacidos después del año 'a'
            .filter(alumno -> {
                int anioNacimiento = Integer.parseInt(alumno[3].substring(0, 4));
                return anioNacimiento > a;
            })
            // Ordenar por nota media descendente
            .sorted((a1, a2) -> {
                Double nota1 = Double.parseDouble(a1[6]);
                Double nota2 = Double.parseDouble(a2[6]);
                return nota2.compareTo(nota1);
            })
            // Limitar a los n primeros
            .limit(n)
            // Mapear a nombre completo (apellidos + nombre)
            .map(alumno -> alumno[0] + " " + alumno[1])
            .collect(Collectors.toList());
    }


	
	}

    


   
    





