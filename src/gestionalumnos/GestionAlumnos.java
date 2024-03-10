package gestionalumnos;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GestionAlumnos {
    private static Map<String, Alumno> alumnos = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static final Pattern CARNET_PATTERN = Pattern.compile("[A-Z]{2}\\d{6}");

    public static void main(String[] args) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Ingresar alumno");
            System.out.println("2. Buscar alumno");
            System.out.println("3. Eliminar alumno");
            System.out.println("4. Mostrar todos los alumnos");
            System.out.println("5. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después de nextInt()

            switch (opcion) {
                case 1:
                    ingresarAlumno();
                    break;
                case 2:
                    buscarAlumno();
                    break;
                case 3:
                    eliminarAlumno();
                    break;
                case 4:
                    mostrarTodosLosAlumnos();
                    break;
                case 5:
                    continuar = false;
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        }
    }

    private static void ingresarAlumno() {
        System.out.println("Ingrese el carnet del alumno:");
        String carnet = scanner.nextLine().toUpperCase();
        
        if (!validarFormatoCarnet(carnet)) {
            System.out.println("Formato de carnet incorrecto. El carnet debe contener dos letras mayúsculas seguidas de seis números.");
            return;
        }
        
        if (alumnos.containsKey(carnet)) {
            System.out.println("Usuario ya ingresado. Por favor, ingrese un nuevo y diferente usuario.");
            return;
        }
        
        System.out.println("Ingrese el nombre del alumno:");
        String nombre = scanner.nextLine();
        
        System.out.println("Ingrese el apellido del alumno:");
        String apellido = scanner.nextLine();
        
        System.out.println("Ingrese la carrera del alumno:");
        String carrera = scanner.nextLine();
        
        alumnos.put(carnet, new Alumno(nombre, apellido, carrera));
        System.out.println("Alumno ingresado exitosamente.");
    }

    private static boolean validarFormatoCarnet(String carnet) {
        Matcher matcher = CARNET_PATTERN.matcher(carnet);
        return matcher.matches();
    }

    private static void buscarAlumno() {
        System.out.println("Ingrese el carnet del alumno a buscar:");
        String carnet = scanner.nextLine().toUpperCase();
        Alumno alumno = alumnos.get(carnet);
        if (alumno != null) {
            System.out.println("Nombre del alumno: " + alumno.getNombre());
            System.out.println("Apellido del alumno: " + alumno.getApellido());
            System.out.println("Carrera del alumno: " + alumno.getCarrera());
        } else {
            System.out.println("Alumno no encontrado, no se puede mostrar.");
        }
    }

    private static void eliminarAlumno() {
        System.out.println("Ingrese el carnet del alumno a eliminar:");
        String carnet = scanner.nextLine().toUpperCase();
        Alumno alumno = alumnos.remove(carnet);
        if (alumno != null) {
            System.out.println("Alumno eliminado exitosamente.");
        } else {
            System.out.println("Alumno no encontrado, no se puede eliminar.");
        }
    }

    private static void mostrarTodosLosAlumnos() {
        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados.");
        } else {
            System.out.println("Lista de todos los alumnos:");
            for (Map.Entry<String, Alumno> entry : alumnos.entrySet()) {
                Alumno alumno = entry.getValue();
                System.out.println("Carnet: " + entry.getKey() + ", Nombre: " + alumno.getNombre() + ", Apellido: " + alumno.getApellido() + ", Carrera: " + alumno.getCarrera());
            }
        }
    }
}

class Alumno {
    private String nombre;
    private String apellido;
    private String carrera;

    public Alumno(String nombre, String apellido, String carrera) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.carrera = carrera;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCarrera() {
        return carrera;
    }
}
