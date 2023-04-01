package tese.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tese.pojo.Cliente;

public class ClienteDAO {

    static String sqlCrearTabla = "CREATE TABLE clientes (\n" +
                "    id INT NOT NULL AUTO_INCREMENT ,\n" +
                "    nombre VARCHAR(20) NOT NULL ,\n" +
                "    apellidos VARCHAR(40) NOT NULL ,\n" +
                "    telefono BIGINT NOT NULL ,\n" +
                "    domicilio VARCHAR(60) NOT NULL ,\n" +
                "    codigo_postal INT(5) NOT NULL ,\n" +
                "    edad INT(2) NOT NULL ,\n" +
                "    sexo VARCHAR(1) NOT NULL , \n" +
                "    PRIMARY KEY (id)) ;";

    String sqlInsert = "INSERT INTO  clientes (nombre, apellidos, telefono, domicilio,codigo_postal, edad, sexo ) VALUES ( ?, ?, ? , ?, ?, ?, ? )";
    String sqlUpdate = "UPDATE clientes set nombre= ?, apellidos = ?, telefono=?, domicilio =? , codigo_postal = ?, edad =?, sexo=? where id = ? ";
    String sqlDelete = "DELETE from clientes where id = ?";
    String sqlSelectById = "SELECT id, nombre, apellidos, telefono, domicilio,codigo_postal, edad, sexo from clientes where id =?";
    String sqlSelectByComodin = "SELECT nombre, apellidos, telefono, domicilio,codigo_postal, edad, sexo from clientes where (nombre like ? or apellidos like ?)";
    String sqlSelectAll = "select * from clientes";

    public boolean crearTabla() {
        return DatabaseDAO.crearTabla(sqlCrearTabla);

    }

    public int insert(Cliente p) throws SQLException {
        Connection conn = DatabaseDAO.openConnection();
        int cuantos;
        System.out.println("ClienteDAO.insert:" + p);
        PreparedStatement ps = conn.prepareStatement(sqlInsert);
        ps.setString(1, p.getNombre());
        ps.setString(2, p.getApellidos());
        ps.setLong(3, p.getTelefono());
        ps.setString(4, p.getDomicilio());
        ps.setInt(5, p.getCodigo());
        ps.setInt(6, p.getEdad());
        ps.setString(7, p.getSexo());
        cuantos = ps.executeUpdate();
        DatabaseDAO.closeConnection(conn);
        return cuantos;
    }

    public int update(Cliente p) throws SQLException {
       Connection conn = DatabaseDAO.openConnection();
        int cuantos;
        conn = DatabaseDAO.openConnection();
        PreparedStatement ps = conn.prepareStatement(sqlUpdate);
        ps.setString(1, p.getNombre());
        ps.setString(2, p.getApellidos());
        ps.setLong(3, p.getTelefono());
        ps.setString(4, p.getDomicilio());
        ps.setInt(5, p.getCodigo());
        ps.setInt(6, p.getEdad());
        ps.setString(7, p.getSexo());
        ps.setInt(8, p.getId());
        cuantos = ps.executeUpdate();
        System.out.println("update" + cuantos + " > " +  p);
        DatabaseDAO.closeConnection(conn);
        return cuantos;
    }

    public int delete(int id) throws SQLException {
        Connection conn = DatabaseDAO.openConnection();
        int cuantos;
        PreparedStatement ps = conn.prepareStatement(sqlDelete);
        ps.setInt(1, id);
        cuantos = ps.executeUpdate();
        DatabaseDAO.closeConnection(conn);
        return cuantos;
    }

    //---
    public Cliente select(int id) throws SQLException {
        Connection conn = DatabaseDAO.openConnection();
        Cliente cliente = null;
        PreparedStatement preparedStatement = conn.prepareStatement(sqlSelectById);
        preparedStatement.setInt(1, id);
        System.out.println(preparedStatement);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            cliente = new Cliente();
            cliente.setId(rs.getInt("id"));
            cliente.setNombre(rs.getString("nombre"));
            cliente.setApellidos(rs.getString("apellidos"));
            cliente.setTelefono(rs.getLong("telefono"));
            cliente.setDomicilio(rs.getString("domicilio"));
            cliente.setCodigo(rs.getInt("codigo_postal"));
            cliente.setEdad(rs.getInt("edad"));
            cliente.setSexo(rs.getString("sexo"));
        }
        DatabaseDAO.closeConnection(conn);
        return cliente;
    }

    public Cliente select(String comodin) throws SQLException {
        Connection conn = DatabaseDAO.openConnection();
        Cliente cliente = null;
        PreparedStatement preparedStatement = conn.prepareStatement(sqlSelectByComodin);
        preparedStatement.setString(1, comodin);
        preparedStatement.setString(2, comodin);
        System.out.println(preparedStatement);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            cliente = new Cliente();
            cliente.setId(rs.getInt("id"));
            cliente.setNombre(rs.getString("nombre"));
            cliente.setApellidos(rs.getString("apellidos"));
            cliente.setTelefono(rs.getLong("telefono"));
            cliente.setDomicilio(rs.getString("domicilio"));
            cliente.setCodigo(rs.getInt("codigo_postal"));
            cliente.setEdad(rs.getInt("edad"));
            cliente.setSexo(rs.getString("sexo"));
        }
        DatabaseDAO.closeConnection(conn);
        return cliente;
    }

    public List selectAll() throws SQLException {
        Connection conn = DatabaseDAO.openConnection();
        List clientes = new ArrayList();
        PreparedStatement ps = conn.prepareStatement(sqlSelectAll);
        System.out.println(ps);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setId(rs.getInt("id"));
            cliente.setNombre(rs.getString("nombre"));
            cliente.setApellidos(rs.getString("apellidos"));
            cliente.setTelefono(rs.getLong("telefono"));
            cliente.setDomicilio(rs.getString("domicilio"));
            cliente.setCodigo(rs.getInt("codigo_postal"));
            cliente.setEdad(rs.getInt("edad"));
            cliente.setSexo(rs.getString("sexo"));
            clientes.add(cliente);
        }
        DatabaseDAO.closeConnection(conn);
        return clientes;
    }
}
