package servlets;

import datos.ProductoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import logica.Producto;

@WebServlet(name = "AgregarServlet", urlPatterns = {"/AgregarServlet"})
@MultipartConfig
public class Agregar extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
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
        List<Producto> productos = new ProductoDAO().obtenerProductosEliminados();
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

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        new ProductoDAO().marcarComoEliminado(id);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private void restaurarProducto(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        new ProductoDAO().restaurarProducto(id);
        response.setStatus(HttpServletResponse.SC_OK);
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
        String accion = request.getParameter("accion");

        if ("eliminar".equalsIgnoreCase(accion)) {
            eliminarProducto(request, response);
        } else if ("restaurar".equalsIgnoreCase(accion)) {
            restaurarProducto(request, response);
        } else {

            String name = request.getParameter("Agregar-nombre");
            String tipo = request.getParameter("tipo");
            String desc = request.getParameter("Agregar-descripcion");
            String precio = request.getParameter("Agregar-precio");

            Part filePart = request.getPart("imagen-file"); // este es el input type="file"
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            System.out.println("Nombre: " + name);
            System.out.println("Tipo: " + tipo);
            System.out.println("Descripción: " + desc);
            System.out.println("Precio: " + precio);
            System.out.println("Nombre del archivo: " + fileName);
            String producto = "'" + name + "','" + tipo + "','" + desc + "','images/" + fileName + "','€ " + precio + "'";
            if (new ProductoDAO().crearProducto(producto)) {
                response.sendRedirect("admin.jsp");
            }
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
