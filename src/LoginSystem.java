import java.io.*;
import java.util.*;

public class LoginSystem {
    private static final String DB_DIR = "db";
    private static final String USERS_FILE = DB_DIR + "/usuarios.csv";
    private static final String DOCTORES_FILE = DB_DIR + "/doctores.csv";
    private static final String PACIENTES_FILE = DB_DIR + "/pacientes.csv";  // Nuevo archivo para pacientes
    public static Map<String, String> usuarios = new HashMap<>();
    public static List<Doctor> doctores = new ArrayList<>();
    public static List<Paciente> pacientes = new ArrayList<>();  // Lista de pacientes

    public void iniciarLogin() {
        // Asegurar que la carpeta 'db' exista
        File dbDir = new File(DB_DIR);
        if (!dbDir.exists()) {
            if (dbDir.mkdir()) {
                System.out.println(">>> Carpeta 'db' creada.");
            } else {
                System.out.println(">>> Error al crear la carpeta 'db'.");
                return; // Terminar el programa si no se puede crear la carpeta
            }
        }

        // Cargar usuarios, doctores y pacientes
        cargarUsuarios();
        cargarDoctores();
        cargarPacientes();

        // Solicitar login hasta que el usuario y la contraseña sean correctos
        Scanner scanner = new Scanner(System.in);
        boolean accesoValido = false;

        while (!accesoValido) {
            System.out.println("Hola, para ingresar teclea tu");
            System.out.print(">>> Usuario: ");
            String username = scanner.nextLine();
            System.out.print(">>> Contraseña: ");
            String password = scanner.nextLine();

            // Validar usuario y contraseña
            if (validarLogin(username, password)) {
                System.out.println(">> ¡Bienvenido! <<");
                accesoValido = true;
            } else {
                System.out.println(">> Usuario y/o contraseña incorrectos, intente de nuevo <<");
            }
        }
    }

    private static void cargarUsuarios() {
        File usuariosFile = new File(USERS_FILE);
        // Si no existe el archivo, crearlo con el usuario predeterminado
        if (!usuariosFile.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE))) {
                writer.write("admin,admin\n");
                System.out.println("> Archivo usuarios.csv creado con usuario predeterminado.");
            } catch (IOException e) {
                System.out.println("> Error al crear el archivo de usuarios.");
            }
        }

        // Leer el archivo y cargar los usuarios en el mapa
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    usuarios.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("> Error al leer el archivo de usuarios.");
        }
    }

    private static void cargarDoctores() {
        File doctoresFile = new File(DOCTORES_FILE);
        // Si no existe el archivo, crearlo
        if (!doctoresFile.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(DOCTORES_FILE))) {
                System.out.println("> Archivo doctores.csv creado.");
            } catch (IOException e) {
                System.out.println("> Error al crear el archivo de doctores.");
            }
        }

        // Leer el archivo y cargar los doctores en la lista
        try (BufferedReader reader = new BufferedReader(new FileReader(DOCTORES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0]);
                    String nombre = parts[1];
                    String especialidad = parts[2];
                    doctores.add(new Doctor(parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("> Error al leer el archivo de doctores.");
        }
    }

    private static void cargarPacientes() {
        File pacientesFile = new File(PACIENTES_FILE);
        // Si no existe el archivo, crearlo
        if (!pacientesFile.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(PACIENTES_FILE))) {
                System.out.println("> Archivo pacientes.csv creado.");
            } catch (IOException e) {
                System.out.println("> Error al crear el archivo de pacientes.");
            }
        }

        // Leer el archivo y cargar los pacientes en la lista
        try (BufferedReader reader = new BufferedReader(new FileReader(PACIENTES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String nombre = parts[1];
                    pacientes.add(new Paciente(nombre));  // Crear paciente con nombre
                }
            }
        } catch (IOException e) {
            System.out.println("> Error al leer el archivo de pacientes.");
        }
    }

    private static boolean validarLogin(String username, String password) {
        return usuarios.containsKey(username) && usuarios.get(username).equals(password);
    }

    public static void guardarDoctor(Doctor doctor) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DOCTORES_FILE, true))) {
            writer.write(doctor.getId() + "," + doctor.getNombre() + "," + doctor.getEspecialidad() + "\n");
            doctores.add(doctor);
            System.out.println("> Doctor registrado correctamente.");
        } catch (IOException e) {
            System.out.println("> Error al guardar el doctor.");
        }
    }

    // Guardar pacientes en el archivo CSV
    public static void guardarPaciente(Paciente paciente) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PACIENTES_FILE, true))) {
            writer.write(paciente.getId() + "," + paciente.getNombre() + "\n");
            pacientes.add(paciente);
            System.out.println("> Paciente registrado correctamente.");
        } catch (IOException e) {
            System.out.println("> Error al guardar el paciente.");
        }
    }

    public static void actualizarDoctores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DOCTORES_FILE))) {
            for (Doctor doctor : doctores) {
                writer.write(doctor.getId() + "," + doctor.getNombre() + "," + doctor.getEspecialidad() + "\n");
            }
            System.out.println("> Archivo de doctores actualizado.");
        } catch (IOException e) {
            System.out.println("> Error al actualizar el archivo de doctores.");
        }
    }

    // Actualizar pacientes
    public static void actualizarPacientes() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PACIENTES_FILE))) {
            for (Paciente paciente : pacientes) {
                writer.write(paciente.getId() + "," + paciente.getNombre() + "\n");
            }
            System.out.println("> Archivo de pacientes actualizado.");
        } catch (IOException e) {
            System.out.println("> Error al actualizar el archivo de pacientes.");
        }
    }
}
