package servlets;

import Alimentacion.GestorAlimentacion;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.AnimalDAO;
import Factory.Animal;

@WebServlet(name = "AnimalesServlet", urlPatterns = {"/AnimalesServlet"})
@MultipartConfig
public class AnimalesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String zona = request.getParameter("zona");
        List<Animal> animales = new AnimalDAO().obtenerAnimalesPorZona(zona);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print("[");
        for (int i = 0; i < animales.size(); i++) {
            Animal a = animales.get(i);
            GestorAlimentacion estrategiaA = GestorAlimentacion.crearPara(a);
            out.print("{");
            out.print("\"id\":" + a.getIdAnimal() + ",");
            out.print("\"estrategia\":\"" + escapar(estrategiaA.obtenerInfoEstrategia(a)) + "\",");
            out.print("\"nombre\":\"" + escapar(a.getNombre()) + "\",");
            out.print("\"especie\":\"" + escapar(a.getEspecie()) + "\",");
            out.print("\"zona\":\"" + escapar(a.getZona()) + "\",");
            out.print("\"dieta\":\"" + escapar(a.getDieta()) + "\",");
            out.print("\"descripcion\":\"" + escapar(a.getDescripcion()) + "\",");
            out.print("\"imagenUrl\":\"" + escapar(a.getImagenURL()) + "\"");
            out.print("}");
            if (i < animales.size() - 1) {
                out.print(",");
            }
        }
        out.print("]");
        out.flush();
    }

    private String escapar(String texto) {
        if (texto == null) {
            return "";
        }
        return texto.replace("\"", "\\\"")
                .replace("\n", "<br>")
                .replace("\r", "")
                .replace("\\", "\\\\");
    }

    @Override
    public String getServletInfo() {
        return "Servlet para manejar animales del zoo por zonas";
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        Animal animal = new AnimalDAO().animalPorId(Integer.parseInt(id));
        GestorAlimentacion estrategiaA = GestorAlimentacion.crearPara(animal);
        String lo = estrategiaA.ejecutarAlimentacion(animal);
    }
}
