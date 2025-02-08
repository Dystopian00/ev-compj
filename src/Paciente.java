import java.io.*;
import java.util.*;

public class Paciente {
    private static final String PACIENTES_FILE = "db/pacientes.csv";
    private static int nextId = 1;  // Para generar IDs secuenciales automáticamente
    private int id;
    private String nombre;

    // Constructor para crear un paciente nuevo
    public Paciente(String nombre) {
        this.id = nextId++;  // Asignar el ID automáticamente
        this.nombre = nombre;
    }

    // Constructor para cargar un paciente desde el CSV con ID ya asignado
    public Paciente(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        nextId = Math.max(nextId, id + 1);  // Actualizar el siguiente ID
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre;
    }

    // Método para guardar un paciente en el archivo CSV
    public static void guardarPaciente(Paciente paciente) {
        try {
            File dbDir = new File("db");
            if (!dbDir.exists()) {
                dbDir.mkdir();
            }

            File pacientesFile = new File(PACIENTES_FILE);
            boolean archivoNuevo = !pacientesFile.exists();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(PACIENTES_FILE, true))) {
                if (archivoNuevo) {
                    writer.write("id,nombre\n");  // Escribir encabezado si el archivo es nuevo
                }
                writer.write(paciente.getId() + "," + paciente.getNombre() + "\n");
            }

            System.out.println("Paciente guardado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar el paciente: " + e.getMessage());
        }
    }

    // Método para cargar todos los pacientes desde el archivo CSV
    public static List<Paciente> cargarPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        File pacientesFile = new File(PACIENTES_FILE);

        if (!pacientesFile.exists()) {
            return pacientes;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(PACIENTES_FILE))) {
            String line = reader.readLine(); // Leer primera línea

            // Verificar si la primera línea es un encabezado
            if (line != null && line.toLowerCase().startsWith("id,nombre")) {
                line = reader.readLine(); // Saltar el encabezado y leer el primer registro real
            }

            while (line != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    int id = Integer.parseInt(parts[0]);
                    String nombre = parts[1];
                    pacientes.add(new Paciente(id, nombre));
                }
                line = reader.readLine(); // Leer la siguiente línea
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de pacientes: " + e.getMessage());
        }
        return pacientes;
    }
}
