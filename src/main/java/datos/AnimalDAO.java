package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logica.Animal;

public class AnimalDAO {

    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    public List<Animal> obtenerAnimalesPorZona(String zona) {
        List<Animal> animales = new ArrayList<>();

        try {
            con = Conexion.connection();

            String sql;
            if ("todas".equals(zona)) {
                sql = "SELECT * FROM animales ORDER BY zona, nombre";
                pst = con.prepareStatement(sql);
            } else {
                sql = "SELECT * FROM animales WHERE zona = ? ORDER BY nombre";
                pst = con.prepareStatement(sql);
                pst.setString(1, zona);
            }

            rs = pst.executeQuery();

            while (rs.next()) {
                Animal animal = new Animal(
                        rs.getString("nombre"),
                        rs.getString("especie"),
                        rs.getString("zona"),
                        rs.getString("dieta"),
                        rs.getString("descripcion"),
                        rs.getString("imagen_url"),
                        rs.getInt("id_animal")
                );
                animales.add(animal);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return animales;
    }
}
