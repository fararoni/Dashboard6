package tese.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tese.dao.ClienteDAO;
import tese.pojo.Cliente;
import tese.pojo.Producto;

@WebServlet(name = "TestServlet", urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            ClienteDAO dao = new ClienteDAO();
            Cliente cliente = new Cliente(0, 
                    "Guadalupe", 
                    "Reyes", 
                    5512345678L, 
                    "Conocido",
                    12345, 
                    21,
                    "M" );
            List la = dao.selectAll();
            out.print("Select all..");
            out.println(la);
            
            dao.insert(cliente);
            List lb = dao.selectAll();
            out.print("Select all 2..");
            out.println(lb);
            //--
            out.print("Update..");
            cliente.setNombre("Lupita");
            cliente.setId(1);
            dao.update(cliente);
            
            out.print("Delete..");
            dao.delete(1);
            out.print("Ok");
            
        } catch (SQLException ex) {
            Logger.getLogger(TestServlet.class.getName()).log(Level.SEVERE, null, ex);
            out.print(ex);
        } finally {
            out.close();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
