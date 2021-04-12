import java.util.Scanner;

public class MensajesService {
    public static void crearMensaje(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Escribe tu mensaje:\n");
        String mensaje = sc.nextLine();

        System.out.println("Escribe tu nombre:\n");
        String nombre = sc.nextLine();

        Mensajes registro = new Mensajes();
        registro.setMensaje(mensaje);
        registro.setAutor_mensaje(nombre);

        MensajesDAO.crearMensajeDB(registro);
    }

    public static void listarMensajes(){
        MensajesDAO.leerMensajesDB();
    }

    public static void borrarMensaje(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Escribe el ID del mensaje a borrar:\n");
        int id = sc.nextInt();

        MensajesDAO.borrarMensajeDB(id);
    }

    public static void editarMensaje(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Escribe tu nuevo mensaje:\n");
        String mensaje = sc.nextLine();

        System.out.println("Escribe el id del mensaje:\n");
        int id_mensaje = sc.nextInt();

        Mensajes actualizacion = new Mensajes();
        actualizacion.setMensaje(mensaje);
        actualizacion.setId_mensaje(id_mensaje);

        MensajesDAO.actualizarMensajesDB(actualizacion);
    }
}
