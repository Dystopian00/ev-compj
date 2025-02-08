import java.io.*;
import java.util.*;

public class Cita {
    private static final String CITAS_FILE = "db/citas.csv";
    private static int nextId = 1;  // Para generar IDs secuenciales automáticamente
    private int id;
    private int idDoctor;
    private int idPaciente;
    private String fecha;
    private String hora;
    private String motivo;

    public Cita(int idDoctor, int idPaciente, String fecha, String hora, String motivo) {
        this.id = nextId++;
        this.idDoctor = idDoctor;
        this.idPaciente = idPaciente;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
    }

    public Cita(int id, int idDoctor, int idPaciente, String fecha, String hora, String motivo) {
        this.id = id;
        this.idDoctor = idDoctor;
        this.idPaciente = idPaciente;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        nextId = Math.max(nextId, id + 1);
    }

    public int getId() {
        return id;
    }

    public int getIdDoctor() {
        return idDoctor;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getMotivo() {
        return motivo;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Doctor ID: " + idDoctor + " | Paciente ID: " + idPaciente +
                " | Fecha: " + fecha + " | Hora: " + hora + " | Motivo: " + motivo;
    }

    public static void guardarCita(Cita cita) {
        try {
            File dbDir = new File("db");
            if (!dbDir.exists()) {
                dbDir.mkdir();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CITAS_FILE, true))) {
                writer.write(cita.getId() + "," + cita.getIdDoctor() + "," + cita.getIdPaciente() + "," +
                        cita.getFecha() + "," + cita.getHora() + "," + cita.getMotivo() + "\n");
            }

            System.out.println(">>> Cita guardada correctamente.");
        } catch (IOException e) {
            System.out.println(">>> Error al guardar la cita: " + e.getMessage());
        }
    }

    public static List<Cita> cargarCitas() {
        List<Cita> citas = new ArrayList<>();
        File citasFile = new File(CITAS_FILE);

        if (!citasFile.exists()) {
            return citas;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(CITAS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    int id = Integer.parseInt(parts[0]);
                    int idDoctor = Integer.parseInt(parts[1]);
                    int idPaciente = Integer.parseInt(parts[2]);
                    String fecha = parts[3];
                    String hora = parts[4];
                    String motivo = parts[5];
                    citas.add(new Cita(id, idDoctor, idPaciente, fecha, hora, motivo));
                }
            }
        } catch (IOException e) {
            System.out.println(">>> Error al leer el archivo de citas: " + e.getMessage());
        }
        return citas;
    }
}
