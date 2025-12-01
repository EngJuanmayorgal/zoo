package servlets;

import datos.UsuarioDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Persona;

/**
 *
 * @author jeide
 */
@WebServlet(name = "Ingresar", urlPatterns = {"/Ingresar"})
public class Ingresar extends HttpServlet {

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
        processRequest(request, response);
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
        String name = request.getParameter("nombre");
        String pass = request.getParameter("password");
        String rol = request.getParameter("rol");
        HttpSession session = request.getSession();

        boolean accesoPermitido = new UsuarioDAO().entrarUser(rol, name, pass);

        if (accesoPermitido) {
            Persona usuario = new UsuarioDAO().traerUser(name, pass);
            session.setAttribute("nombreUsuario", name);
            session.setAttribute("usuarioId", usuario.getIdUser());
            session.setAttribute("usuario", usuario);

            if ("user".equals(rol)) {
                System.out.println("hh");
                response.sendRedirect("usuario.jsp");
            } else if ("admin".equals(rol)) {
                response.sendRedirect("admin.jsp");
            }
        } else {
            // Redirigir al login con error
            response.sendRedirect("index.jsp?error=1");
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
