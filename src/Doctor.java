import java.io.*;
import java.util.*;

public class Doctor {
    private static final String DOCTORES_FILE = "db/doctores.csv";
    private static int nextId = 1;  // Para generar IDs secuenciales automáticamente
    private int id;
    private String nombre;
    private String especialidad;

    public Doctor(String nombre, String especialidad) {
        this.id = nextId++;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public Doctor(int id, String nombre, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        nextId = Math.max(nextId, id + 1);
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | Especialidad: " + especialidad;
    }

    public static void guardarDoctor(Doctor doctor) {
        try {
            File dbDir = new File("db");
            if (!dbDir.exists()) {
                dbDir.mkdir();
            }

            File doctoresFile = new File(DOCTORES_FILE);
            boolean archivoNuevo = !doctoresFile.exists();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(DOCTORES_FILE, true))) {
                if (archivoNuevo) {
                    writer.write("id,nombre,especialidad\n");  // Escribir encabezado si el archivo es nuevo
                }
                writer.write(doctor.getId() + "," + doctor.getNombre() + "," + doctor.getEspecialidad() + "\n");
            }

            System.out.println(">>> Doctor guardado correctamente.");
        } catch (IOException e) {
            System.out.println(">>> Error al guardar el doctor: " + e.getMessage());
        }
    }

    public static List<Doctor> cargarDoctores() {
        List<Doctor> doctores = new ArrayList<>();
        File doctoresFile = new File(DOCTORES_FILE);

        if (!doctoresFile.exists()) {
            return doctores;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(DOCTORES_FILE))) {
            String line = reader.readLine(); // Leer primera línea

            // Verificar si la primera línea es un encabezado
            if (line != null && line.toLowerCase().startsWith("id,nombre,especialidad")) {
                line = reader.readLine(); // Saltar el encabezado y leer el primer registro real
            }

            while (line != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0]);
                    String nombre = parts[1];
                    String especialidad = parts[2];
                    doctores.add(new Doctor(id, nombre, especialidad));
                }
                line = reader.readLine(); // Leer la siguiente línea
            }
        } catch (IOException e) {
            System.out.println(">>> Error al leer el archivo de doctores: " + e.getMessage());
        }
        return doctores;
    }
}
