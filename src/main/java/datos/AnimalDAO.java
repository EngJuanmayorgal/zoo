package datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import logica.Animal;

public class AnimalDAO {
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

    public AnimalDAO() {
        con = null;
        st = null;
        rs = null;
    }

    public List<Animal> obtenerAnimalesPorZona(String zona) {
        List<Animal> animales = new ArrayList<>();

        try {
            con = Conexion.connection();

            String sql;
            if ("todas".equals(zona)) {
                sql = "SELECT * FROM animales ORDER BY zona, nombre";
                stmt = conn.prepareStatement(sql);
            } else {
                sql = "SELECT * FROM animales WHERE zona = ? ORDER BY nombre";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, zona);
            }

            rs = stmt.executeQuery();

            while (rs.next()) {
                Animal animal = new Animal();
                animal.setIdAnimal(rs.getInt("id_animal"));
                animal.setNombre(rs.getString("nombre"));
                animal.setEspecie(rs.getString("especie"));
                animal.setZona(rs.getString("zona"));
                animal.setDieta(rs.getString("dieta"));
                animal.setDescripcion(rs.getString("descripcion"));
                animal.setImagenURL(rs.getString("imagen_url"));
                animales.add(animal);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return animales;
    }
}