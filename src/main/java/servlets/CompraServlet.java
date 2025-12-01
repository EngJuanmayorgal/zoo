/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import datos.CompraDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Producto;

/**
 *
 * @author jeide
 */
@WebServlet(name = "CompraServlet", urlPatterns = {"/CompraServlet"})
public class CompraServlet extends HttpServlet {

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
        int usuarioId = Integer.parseInt(request.getParameter("usuarioId"));
        List<Producto> compras = new CompraDAO().obtenerComprasDeUsuario(usuarioId);
        response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print("[");
        for (int i = 0; i < compras.size(); i++) {
            Producto p = compras.get(i);
            out.print("{");
            out.print("\"id\": " + p.getIdProducto() + ",");
            out.print("\"nombre\": \"" + p.getNombre() + "\",");
            out.print("\"descripcion\": \"" + p.getDescripcion() + "\",");
            out.print("\"precio\": \"" + p.getPrecio() + "\",");
            out.print("\"imagen\": \"" + p.getImagenURL() + "\"");
            out.print("}");
            if (i < compras.size() - 1) {
                out.print(",");
            }
        }
        out.print("]");
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
        int usuarioId = Integer.parseInt(request.getParameter("usuarioId"));
        int productoId = Integer.parseInt(request.getParameter("productoId"));
        boolean resultado = new CompraDAO().registrarCompra(usuarioId, productoId);
        response.setContentType("application/json");
        response.getWriter().write("{\"exito\": " + resultado + "}");
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
