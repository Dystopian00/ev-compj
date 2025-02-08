import java.util.*;
import java.io.*;

public class Menu {
    private Scanner scanner;
    private List<Doctor> doctores;
    private List<Paciente> pacientes;
    private List<Cita> citas;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.doctores = Doctor.cargarDoctores();
        this.pacientes = Paciente.cargarPacientes();  // Llamada sin parámetros
        this.citas = Cita.cargarCitas();
    }

    public void mostrarMenu() {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n>>> Menú:");
            System.out.println("1. Registrar Doctor");
            System.out.println("2. Mostrar Doctores");
            System.out.println("3. Registrar Paciente");
            System.out.println("4. Mostrar Pacientes");
            System.out.println("5. Crear Cita");
            System.out.println("6. Mostrar Citas");
            System.out.println("7. Crear Usuario");
            System.out.println("8. Cambiar Contraseña");
            System.out.println("9. Salir");

            System.out.print(">>> Seleccione una opción: ");
            int opcion = -1;

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea
            } catch (InputMismatchException e) {
                scanner.nextLine(); // Limpiar el buffer
                System.out.println("> Opción no válida. Por favor, ingrese un número del 1 al 9.");
                continue; // Volver al inicio del ciclo sin continuar con el código
            }

            switch (opcion) {
                case 1:
                    registrarDoctor();
                    break;
                case 2:
                    mostrarDoctores();
                    break;
                case 3:
                    registrarPaciente();
                    break;
                case 4:
                    mostrarPacientes();
                    break;
                case 5:
                    crearCita();
                    break;
                case 6:
                    mostrarCitas();
                    break;
                case 7:
                    crearUsuario();
                    break;
                case 8:
                    cambiarContrasena();
                    break;
                case 9:
                    salir = true;
                    break;
                default:
                    System.out.println("> Opción no válida.");
            }
        }
    }

    private void registrarPaciente() {
        System.out.print(">>> Nombre del Paciente: ");
        String nombre = scanner.nextLine();

        // Crear el nuevo paciente
        Paciente paciente = new Paciente(nombre);

        // Agregar el nuevo paciente a la lista
        pacientes.add(paciente);

        // Guardar la lista actualizada de pacientes en el archivo CSV
        Paciente.guardarPaciente(paciente);  // Guardamos solo el nuevo paciente

        System.out.println("> Paciente registrado exitosamente.");
    }

    private void mostrarPacientes() {
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
        } else {
            System.out.println("\nLista de Pacientes:");
            for (Paciente paciente : pacientes) {
                System.out.println("ID: " + paciente.getId() + " | Nombre: " + paciente.getNombre());
            }
        }
    }

    private void registrarDoctor() {
        System.out.print("Nombre del Doctor: ");
        String nombre = scanner.nextLine();
        System.out.print("Especialidad: ");
        String especialidad = scanner.nextLine();

        // Crear el nuevo doctor
        Doctor doctor = new Doctor(nombre, especialidad);

        // Guardar el doctor en el archivo
        Doctor.guardarDoctor(doctor);

        // Agregar el nuevo doctor a la lista
        doctores.add(doctor);

        System.out.println("Doctor registrado exitosamente.");
    }

    private void mostrarDoctores() {
        if (doctores.isEmpty()) {
            System.out.println("No hay doctores registrados.");
        } else {
            System.out.println("\nLista de Doctores:");
            for (Doctor doctor : doctores) {
                System.out.println("ID: " + doctor.getId() + " | Nombre: " + doctor.getNombre() + " | Especialidad: " + doctor.getEspecialidad());
            }
        }
    }

    private void crearCita() {
        if (doctores.isEmpty() || pacientes.isEmpty()) {
            System.out.println("Debe haber al menos un doctor y un paciente registrados para crear una cita.");
            return;
        }

        System.out.println("\nLista de Doctores:");
        for (Doctor doctor : doctores) {
            System.out.println("ID: " + doctor.getId() + " | Nombre: " + doctor.getNombre() + " | Especialidad: " + doctor.getEspecialidad());
        }
        System.out.print("Ingrese el ID del Doctor: ");
        int idDoctor = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nLista de Pacientes:");
        for (Paciente paciente : pacientes) {
            System.out.println("ID: " + paciente.getId() + " | Nombre: " + paciente.getNombre());
        }
        System.out.print("Ingrese el ID del Paciente: ");
        int idPaciente = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ingrese la fecha de la cita (DD/MM/AAAA): ");
        String fecha = scanner.nextLine();

        System.out.print("Ingrese la hora de la cita (HH:MM en formato 24 hrs): ");
        String hora = scanner.nextLine();

        System.out.print("Ingrese el motivo de la cita: ");
        String motivo = scanner.nextLine();

        Cita nuevaCita = new Cita(idDoctor, idPaciente, fecha, hora, motivo);
        citas.add(nuevaCita);
        Cita.guardarCita(nuevaCita);

        System.out.println("Cita creada exitosamente.");
    }

    private void mostrarCitas() {
        if (citas.isEmpty()) {
            System.out.println("No hay citas registradas.");
        } else {
            System.out.println("\nLista de Citas:");
            for (Cita cita : citas) {
                // Buscar el doctor correspondiente por ID
                String doctorNombre = "Desconocido";
                for (Doctor doctor : doctores) {
                    if (doctor.getId() == cita.getIdDoctor()) {
                        doctorNombre = doctor.getNombre();
                        break;
                    }
                }

                // Buscar el paciente correspondiente por ID
                String pacienteNombre = "Desconocido";
                for (Paciente paciente : pacientes) {
                    if (paciente.getId() == cita.getIdPaciente()) {
                        pacienteNombre = paciente.getNombre();
                        break;
                    }
                }

                // Mostrar los detalles de la cita con los nombres
                System.out.println("ID Cita: " + cita.getId() +
                        " | ID Doctor: " + cita.getIdDoctor() + " | Nombre Doctor: " + doctorNombre +
                        " | ID Paciente: " + cita.getIdPaciente() + " | Nombre Paciente: " + pacienteNombre +
                        " | Fecha: " + cita.getFecha() + " | Hora: " + cita.getHora() + " | Motivo: " + cita.getMotivo());
            }
        }
    }

    private void crearUsuario() {
        // Solicitar datos para el nuevo usuario
        System.out.print("Ingrese el nombre de usuario: ");
        String usuario = scanner.nextLine();

        System.out.print("Ingrese la contraseña del nuevo usuario: ");
        String contrasena = scanner.nextLine();

        // Crear el nuevo usuario
        Usuario nuevoUsuario = new Usuario(usuario, contrasena);

        // Guardar el nuevo usuario
        Usuario.guardarUsuario(nuevoUsuario);

        System.out.println("Usuario creado exitosamente.");
    }

    private void cambiarContrasena() {
        // Solicitar usuario y nueva contraseña
        System.out.print("Ingrese el nombre de usuario: ");
        String usuario = scanner.nextLine();

        System.out.print("Ingrese la nueva contraseña: ");
        String nuevaContrasena = scanner.nextLine();

        // Cambiar la contraseña del usuario
        Usuario.cambiarContrasena(usuario, nuevaContrasena);

        System.out.println("Contraseña cambiada exitosamente.");
    }
}
