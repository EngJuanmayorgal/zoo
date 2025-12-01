package servlets;

import datos.ProductoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import logica.Producto;

/**
 *
 * @author jeide
 */
@WebServlet(name = "ProductosServlet", urlPatterns = {"/ProductosServlet"})
@MultipartConfig
public class ProductosServlet extends HttpServlet {

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
        String opcion = request.getParameter("tipo"); // 'todas', 'bicicletas', etc.
        List<Producto> productos = new ProductoDAO().obtenerProductos(opcion);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        out.print("[");
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            out.print("{");
            out.print("\"id\":" + p.getIdProducto() + ",");
            out.print("\"nombre\":\"" + escapar(p.getNombre()) + "\",");
            out.print("\"descripcion\":\"" + escapar(p.getDescripcion()) + "\",");
            out.print("\"tipo\":\"" + escapar(p.getTipo()) + "\",");
            out.print("\"precio\":\"" + escapar(p.getPrecio()) + "\",");
            out.print("\"imagen\":\"" + escapar(p.getImagenURL()) + "\"");
            out.print("}");
            if (i < productos.size() - 1) {
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

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("prod-id"));
        String name = request.getParameter("prod-nombre");
        String desc = request.getParameter("prod-descripcion");
        String precio = request.getParameter("prod-precio");
        if (new ProductoDAO().actualizarProducto(id, name, desc, precio)) {
            response.sendRedirect("admin.jsp");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
