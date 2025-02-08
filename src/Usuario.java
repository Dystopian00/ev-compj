import java.io.*;
import java.util.*;

public class Usuario {
    private String usuario;
    private String contrasena;

    public Usuario(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    // Método para cargar usuarios desde archivo CSV
    public static List<Usuario> cargarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("db/usuarios.csv"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                String usuario = datos[0];
                String contrasena = datos[1];
                usuarios.add(new Usuario(usuario, contrasena));
            }
        } catch (IOException e) {
            System.out.println("Error al cargar usuarios: " + e.getMessage());
        }
        return usuarios;
    }

    // Método para guardar un nuevo usuario en el archivo CSV
    public static void guardarUsuario(Usuario usuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("db/usuarios.csv", true))) {
            writer.write(usuario.getUsuario() + "," + usuario.getContrasena());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar el usuario: " + e.getMessage());
        }
    }

    // Método para cambiar la contraseña de un usuario
    public static void cambiarContrasena(String usuario, String nuevaContrasena) {
        List<Usuario> usuarios = cargarUsuarios();
        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(usuario)) {
                u.contrasena = nuevaContrasena;
                break;
            }
        }

        // Sobrescribir el archivo CSV con las contraseñas actualizadas
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("db/usuarios.csv"))) {
            for (Usuario u : usuarios) {
                writer.write(u.getUsuario() + "," + u.getContrasena());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al cambiar la contraseña: " + e.getMessage());
        }
    }
}
