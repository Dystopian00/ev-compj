public class Main {
    public static void main(String[] args) {
        // Crear una instancia del sistema de login
        LoginSystem loginSystem = new LoginSystem();

        // Ejecutar el login
        loginSystem.iniciarLogin();

        // Si el login es exitoso, se muestra el menú
        Menu menu = new Menu();
        menu.mostrarMenu();
    }
}
