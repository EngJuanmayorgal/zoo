package servlets;

import datos.ProductoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import logica.Animal;

/**
 *
 * @author jeide
 */
@WebServlet(name = "AnimalesServlet", urlPatterns = {"/AnimalesServlet"})
@MultipartConfig
public class AnimalesServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String zona = request.getParameter("zona"); // 'todas', 'africa', 'amazonas', etc.
        List<Animal> animales = new ProductoDAO().obtenerAnimalesPorZona(zona);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        out.print("[");
        for (int i = 0; i < animales.size(); i++) {
            Animal a = animales.get(i);
            out.print("{");
            out.print("\"id\":" + a.getIdAnimal() + ",");
            out.print("\"nombre\":\"" + escapar(a.getNombre()) + "\",");
            out.print("\"especie\":\"" + escapar(a.getEspecie()) + "\",");
            out.print("\"zona\":\"" + escapar(a.getZona()) + "\",");
            out.print("\"descripcion\":\"" + escapar(a.getDescripcion()) + "\",");
            out.print("\"imagen\":\"" + escapar(p.getImagenURL()) + "\"");
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
                .replace("\n", "")
                .replace("\r", "")
                .replace("\\", "\\\\");
    }

    @Override
    public String getServletInfo() {
        return "Servlet para manejar animales del zoo por zonas";
    }

}
