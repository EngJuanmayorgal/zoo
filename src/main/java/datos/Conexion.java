package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    /**
     * Nombre de la base de datos utilizada
     */
    static String dbName = "zoologico";
    /**
     * URL base para la conexión JDBC
     */
    static String url = "jdbc:mysql://localhost:3306/";

    /**
     * Nombre de usuario de la base de datos
     */
    static String user = "root";

    /**
     * Contraseña de acceso a la base de datos
     */
    static String pass = "mysql";

    /**
     * Objeto Connection activo
     */
    static Connection db;

    // Variable de unica instancia
    private static Conexion instanciaUnica = null;

    // Conexion que se comparte
    private Connection conexion;

    /**
     * Constructor de la clase. Establece la conexión a la base de datos al ser
     * instanciado. public Conexion() { connection(); }
     *
     *
     * CAMBIO: Constuctor privado , no se pude hacer un New Conexion(),
     * obligando a usar el ya creado con getInstancia()
     */
    private Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conexion = DriverManager.getConnection(url + dbName, user, pass);
            System.out.println("Conexión a BD establecida (Singleton)");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver MySQL no encontrado");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error al conectar a la BD");
            e.printStackTrace();
        }
    }

    /**
     * Obtencion de la unica instancia de la clase Conexion
     *
     * @return La única instancia de Conexion
     */
    public static Conexion getInstancia() {
        if (instanciaUnica == null) {
            // Solo se puede ejecutar uno a la vez, evita 2 formas simultaneas
            synchronized (Conexion.class) {
                // Verificacion doble
                if (instanciaUnica == null) {
                    instanciaUnica = new Conexion();
                }
            }
        }
        return instanciaUnica;
    }

    /**
     * Método para obtener la conexión activa
     *
     * @return Connection activa
     */
    public Connection getConnection() {
        try {
            // Si la conexión está cerrada o es null, reconecta
            if (conexion == null || conexion.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = DriverManager.getConnection(url + dbName, user, pass);
                System.out.println("Reconexión a BD realizada");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error en getConnection()");
            e.printStackTrace();
        }
        return conexion;
    }

    /**
     * Cierra la conexión con la base de datos asignando null al objeto
     * Connection. public static void disconnected() { db = null; }
     *
     *
     * CAMBIO: Hace lo mismo pero maybe sea mejor
     */
    public void desconectar() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión cerrada correctamente");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar conexión");
            e.printStackTrace();
        }
    }

    /**
     * Se mantiene similar para no romper lo demas
     */
    public static Connection connection() {
        return getInstancia().getConnection();
    }

    public static void disconnected() {
        if (instanciaUnica != null) {
            instanciaUnica.desconectar();
        }
    }

}