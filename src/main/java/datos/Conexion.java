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

    /**
     * Constructor de la clase. Establece la conexión a la base de datos al ser
     * instanciado.
     */
    public Conexion() {
        connection();
    }

    /**
     * Cierra la conexión con la base de datos asignando null al objeto
     * Connection.
     */
    public static void disconnected() {
        db = null;
    }

    /**
     * Establece una conexión con la base de datos utilizando los parámetros
     * definidos.
     *
     * @return Objeto Connection activo si la conexión fue exitosa; de lo
     * contrario, null.
     */
    public static Connection connection() {
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
            }
            db = DriverManager.getConnection(url + dbName, user, pass);
        } catch (SQLException ex) {
        }
        return db;
    }

}
