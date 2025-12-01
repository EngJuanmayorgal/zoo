package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import logica.Persona;
import logica.Usuario;

public class UsuarioDAO {

    /**
     * Conexión con la base de datos
     */
    private java.sql.Connection con;

    /**
     * Objeto Statement para ejecutar sentencias SQL
     */
    private Statement st;

    /**
     * Objeto ResultSet para almacenar resultados de consultas
     */
    private ResultSet rs;

    /**
     * Constructor de la clase que inicializa los campos como nulos
     */
    public UsuarioDAO() {
        con = null;
        st = null;
        rs = null;
    }

    /**
     * Verifica si un lector puede iniciar sesión con las credenciales
     * proporcionadas.
     *
     * @param rol Rol del usuario
     * @param user Nombre de usuario
     * @param pass Contraseña del usuario
     * @return true si el lector existe, false en caso contrario
     */
    public boolean entrarUser(String rol, String user, String pass) {
        String consulta = "SELECT role FROM usuarios WHERE name = '" + user + "' AND pass = " + pass;
        try {
            con = Conexion.connection();
            st = con.createStatement();
            rs = st.executeQuery(consulta);
            if (rs.next()) {
                if (rs.getString("role").equalsIgnoreCase(rol)) {
                    st.close();
                    Conexion.disconnected();
                    return true;
                } else {
                    return false;
                }
            } else {
                st.close();
                Conexion.disconnected();
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
    }

    public Persona traerUser(String user, String pass) {
        String consulta = "SELECT * FROM usuarios WHERE name = '" + user + "' AND pass = " + pass;
        try {
            con = Conexion.connection();
            st = con.createStatement();
            rs = st.executeQuery(consulta);
            if (rs.next()) {
                Persona usuario = new Usuario(rs.getString("cod"), rs.getInt("iduser"),
                        rs.getString("name"), rs.getInt("id"), rs.getString("email"),
                        rs.getString("role"));
                st.close();
                Conexion.disconnected();
                return usuario;
            } else {
                st.close();
                Conexion.disconnected();
                return null;
            }
        } catch (SQLException ex) {
            return null;
        }
    }

    public boolean registrarUsuario(String usuario) {
        String sql = "INSERT INTO usuarios (name, email, pass, id, cod, role)"
                + " VALUES (" + usuario + ")";
        try {
            con = Conexion.connection();
            st = con.createStatement();
            System.out.println("sss");
            st.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
