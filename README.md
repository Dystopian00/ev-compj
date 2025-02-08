.:: Instalacion y configuracion ::.

Requisitos Previos

    Java: Asegúrate de tener instalado Java (versión 8 o superior).
    Git (opcional): Para clonar el repositorio directamente.

Pasos de Instalación

    Clonar el repositorio:
    Abre una terminal o consola de comandos y ejecuta:
    git clone https://github.com/Dystopian00/ev-compj.git

Compilar el proyecto:

    En un IDE (como Eclipse, IntelliJ IDEA o NetBeans), importa el proyecto y compílalo desde allí.
    Al iniciar, se ejecutará el sistema de login. Si la carpeta db o los archivos CSV necesarios no existen, el programa los creará automáticamente.

Almacenamiento de Datos:

    El programa utiliza archivos CSV ubicados en la carpeta db para almacenar información de usuarios, doctores, pacientes y citas.

Usuario Inicial:

    En la primera ejecución, si el archivo usuarios.csv no existe, se creará con un usuario predeterminado:

    Usuario: admin
    Contraseña: admin


.:: Uso del programa ::.

Inicio de Sesión:
Al ejecutar el programa, se solicitarán el nombre de usuario y la contraseña. Introduce las credenciales correctas (por ejemplo, admin/admin en la primera ejecución).

Menú Principal:
Una vez autenticado, se muestra un menú con las siguientes opciones:

    Registrar Doctor:
    Permite agregar un nuevo doctor. Se solicitará el nombre y la especialidad.
    Mostrar Doctores:
    Visualiza la lista de doctores registrados.
    Registrar Paciente:
    Permite agregar un nuevo paciente introduciendo su nombre.
    Mostrar Pacientes:
    Muestra la lista de pacientes registrados.
    Crear Cita:
    Permite agendar una cita. Se selecciona el ID del doctor y del paciente, y se especifican la fecha, hora y el motivo de la cita.
    Mostrar Citas:
    Visualiza la lista de citas registradas, mostrando detalles como el nombre del doctor y del paciente.
    Crear Usuario:
    Permite registrar un nuevo usuario para el sistema. Se pide un nombre de usuario y una contraseña.
    Cambiar Contraseña:
    Permite actualizar la contraseña de un usuario existente.
    Salir:
    Finaliza la ejecución del programa.

Interacción:

    Entrada de Datos:
    El programa solicita información a través de la consola. Introduce los datos según se indiquen en cada paso.
    Persistencia:
    Cada vez que se registra o actualiza un dato (doctor, paciente, cita, usuario), la información se guarda en el archivo CSV correspondiente.


.:: Creditos ::.

    Elaborado Por:
    Samuel Moreno
    Para:
    Computación en Java, Evidencia Final

.:: Licencia ::.

    Uso libre