package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import logica.Producto;

public class CompraDAO {

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

    public CompraDAO() {
        con = null;
        st = null;
        rs = null;
    }

    public boolean registrarCompra(int usuarioId, int productoId) {
        String sql = "INSERT INTO compras (usuarioid, idproducto)"
                + " VALUES (" + usuarioId + ", " + productoId + ")";
        try {
            con = Conexion.connection();
            st = con.createStatement();
            st.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Producto> obtenerComprasDeUsuario(int usuarioId) {
    List<Producto> lista = new ArrayList<>();
    String sql = "SELECT p.idproductos, p.tipo, p.nombre, p.descripcion, p.imagen, p.precio " +
                 "FROM productos p " +
                 "JOIN compras c ON p.idproductos = c.idproducto " +
                 "WHERE c.usuarioid = ?";

    try {
        con = Conexion.connection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, usuarioId);
        rs = ps.executeQuery();

        while (rs.next()) {
            Producto p = new Producto(
                rs.getString("nombre"),
                rs.getString("tipo"),
                rs.getString("descripcion"),
                rs.getString("precio"),
                rs.getString("imagen"),
                rs.getInt("idproductos")
            );
            lista.add(p);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return lista;
}

}
