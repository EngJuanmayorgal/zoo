package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Factory.Animal;
import Factory.AnimalFactory;
import Factory.IAnimalFactory;

public class AnimalDAO {

    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    public AnimalDAO() {
        con = null;
        pst = null;
        rs = null;
    }

    public List<Animal> obtenerAnimalesPorZona(String zona) {
        List<Animal> animales = new ArrayList<>();
        try {
            con = Conexion.getInstancia().getConnection();

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
                String nombre = rs.getString("nombre");
                String especie = rs.getString("especie");
                String zonaBD = rs.getString("zona");

                String dieta = rs.getString("dieta");
                String descripcion = rs.getString("descripcion");
                String imagenURL = rs.getString("imagen_url");

                int id = rs.getInt("id_animal");
                // Obtener la fábrica correspondiente
                IAnimalFactory factory = AnimalFactory.getFactory(zonaBD);

                // Crear el animal con la fábrica: la fábrica rellenará los defaults si dieta/desc/img son nulls
                Animal animal = factory.createAnimal(nombre, especie, id, dieta, descripcion, imagenURL);

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

    public Animal animalPorId(int id) {
        String consulta = "SELECT * FROM animales WHERE id_animal = '" + id + "'";
        System.out.println("lll");
        try {
            con = Conexion.getInstancia().getConnection();
            pst = con.prepareStatement(consulta);
            rs = pst.executeQuery();
            if (rs.next()) {
                Animal animal = new Animal(
                        rs.getString("nombre"),
                        rs.getString("especie"),
                        rs.getString("zona"),
                        rs.getString("dieta"),
                        rs.getString("descripcion"),
                        rs.getString("imagen_url"),
                        rs.getInt("id_animal")
                );
                pst.close();
                Conexion.disconnected();
                return animal;
            } else {
                pst.close();
                Conexion.disconnected();
                return null;
            }
        } catch (SQLException ex) {
            return null;
        }

    }
}
