package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import logica.Producto;

public class ProductoDAO {

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

    public ProductoDAO() {
        con = null;
        st = null;
        rs = null;
    }

    public List<Producto> obtenerProductos(String tipo) {
        List<Producto> lista = new ArrayList<>();
        String query;
        switch (tipo) {
            case "bicicletas":
                query = "SELECT * FROM productos WHERE tipo='bicicletas'";
                break;
            case "accesorios":
                query = "SELECT * FROM productos WHERE tipo='accesorios'";
                break;
            case "vestimenta":
                query = "SELECT * FROM productos WHERE tipo='vestimenta'";
                break;
            case "todas":
                query = "SELECT * FROM productos";
                break;
            default:
                query = "SELECT * FROM compras WHERE user_id=1"; // ejemplo de compras
        }

        try {
            con = Conexion.connection();
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                if (rs.getString("estado").equals("valid")) {
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Producto> obtenerProductosEliminados() {
        List<Producto> lista = new ArrayList<>();
        String query = "SELECT * FROM productos WHERE estado='invalid'"; // ejemplo de compras

        try {
            con = Conexion.connection();
            st = con.createStatement();
            rs = st.executeQuery(query);
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean actualizarProducto(int productoId, String nombre, String desc, String precio) {
        String sql = "UPDATE productos "
                + "SET nombre = '" + nombre + "', "
                + "descripcion = '" + desc + "', "
                + "precio = '" + precio + "' "
                + "WHERE (idproductos = " + productoId + ");";
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

    public void marcarComoEliminado(int id) {
        String sql = "UPDATE productos SET estado = 'invalid' WHERE idproductos ="+id;
        try {
            con = Conexion.connection();
            st = con.createStatement();
            st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void restaurarProducto(int id) {
        String sql = "UPDATE productos SET estado = 'valid' WHERE idproductos ="+id;
        try {
            con = Conexion.connection();
            st = con.createStatement();
            st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean crearProducto(String producto) {

        String sql = "INSERT INTO productos (nombre, tipo, descripcion, imagen,  precio)"
                + " VALUES (" + producto + ");";
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

}
