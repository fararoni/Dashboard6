package tese.controllers;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tese.dao.ClienteDAO;
import tese.pojo.Cliente;

/*
            SQL                      Form            Action      
                                     GET             POST
    Create  Insert  /ClientesNew    showNewForm     actionInsert 
    Read    Select  /ClientesGrid   showAllGrid     N/A
    Update  Update  /ClientesUpdate showUpdateForm  actionUdate
    Delete  Delete  /ClientesDelete showDeleteForm  actionDelete
    ------  ------  ---------------- --------------  ------------

    1.- Los formularios y grid, se mostraran por el Método GET y 
        las acciones a B.D. por el método POST.
 */
@WebServlet(name = "ClientesServlet", urlPatterns = {
    "/ClientesNew",
    "/ClientesGrid",
    "/ClientesUpdate",
    "/ClientesDelete"
})
public class ClientesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ClienteDAO clienteDAO;

    public void init() {
        clienteDAO = new ClienteDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            if ("/ClientesNew".equals(action)) {
                showNewForm(request, response);
            }
            if ("/ClientesGrid".equals(action)) {
                showAllGrid(request, response);

            }
            if ("/ClientesUpdate".equals(action)) {
                showUpdateForm(request, response);
            }
            if ("/ClientesDelete".equals(action)) {
                showDeleteForm(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            if ("/ClientesNew".equals(action)) {
                actionInsert(request, response);
            }
            if ("/ClientesGrid".equals(action)) {
                showAllGrid(request, response);
            }
            if ("/ClientesUpdate".equals(action)) {
                actionUpdate(request, response);
            }
            if ("/ClientesDelete".equals(action)) {
                actionDelete(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //-- Create  Insert  /ProductosNew    showNewForm     actionInsert 
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("showNewForm");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/clientes/cliente-form.jsp");
        dispatcher.forward(request, response);
    }

    private void actionInsert(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {
               System.out.println("Id tiene valor nulo");
        }
            
        String nombre    = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        long telefono    = Long.parseLong(request.getParameter("telefono"));
        String domicilio = request.getParameter("domicilio");
        int codigo       = Integer.parseInt(request.getParameter("codigo"));
        int edad         = Integer.parseInt(request.getParameter("edad"));
        String sexo      = request.getParameter("sexo");

        Cliente newProd = new Cliente(id, nombre, apellidos, telefono, domicilio, codigo, edad, sexo);
        clienteDAO.insert(newProd);
        response.sendRedirect(request.getContextPath() + "/ClientesGrid");
    }

    //--Read    Select  /ProductosGrid   showAllGrid     N/A
    private void showAllGrid(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List< Cliente> listUser = clienteDAO.selectAll();
        
        request.setAttribute("listClientes", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/clientes/clientes-list.jsp");
        dispatcher.forward(request, response);
    }

    //-- Update  Update  /ProductosUpdate showUpdateForm  actionUdate
    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Cliente cliente = clienteDAO.select(id);
        
        request.setAttribute("cliente", cliente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/clientes/cliente-form.jsp");
        dispatcher.forward(request, response);
    }
     private void actionUpdate(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = -1;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {
               System.out.println("Id tiene valor nulo");
        }
        String nombre    = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        long telefono    = Long.parseLong(request.getParameter("telefono"));
        String domicilio = request.getParameter("domicilio");
        int codigo       = Integer.parseInt(request.getParameter("codigo"));
        int edad         = Integer.parseInt(request.getParameter("edad"));
        String sexo      = request.getParameter("sexo");

        Cliente updateCliente = new Cliente(id, nombre, apellidos, telefono, domicilio, codigo, edad, sexo);
        System.out.println(" actionUpdate:" + updateCliente );
        clienteDAO.update(updateCliente);
        response.sendRedirect(request.getContextPath() + "/ClientesGrid");
    }
    
    

    //-- Delete  Delete  /ProductosDelete showDeleteForm  actionDelete
    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Cliente cliente = clienteDAO.select(id);
        
        request.setAttribute("producto", cliente);
        request.setAttribute("accion", "borrar");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/clientes/cliente-form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void actionDelete(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = -1;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {
               System.out.println("Id tiene valor nulo");
        }
        System.out.println(" actionDelete:" + id );
        clienteDAO.delete(id);
        response.sendRedirect(request.getContextPath() + "/ClientesGrid");
    }
}
