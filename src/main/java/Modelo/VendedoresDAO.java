/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


import Controlador.clsVendedores;
import Controlador.clsUsuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Modelo.BitacoraDAO;
import Controlador.clsUsuarioConectado;

/**
 *
 * @author JENNIFER BARRIOS
 */

public class VendedoresDAO {

    private static final String SQL_SELECT = "SELECT codigo_vendedor, nombre_vendedor, direccion_vendedor, telefono_vendedor FROM vendedores";
    private static final String SQL_INSERT = "INSERT INTO vendedores(codigo_vendedor, nombre_vendedor, direccion_vendedor, telefono_vendedor) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE vendedores SET nombre_vendedor=?, direccion_vendedor=?, telefono_vendedor=? WHERE codigo_vendedor=?";
    private static final String SQL_DELETE = "DELETE FROM vendedores WHERE codigo_vendedor=?";
    private static final String SQL_SELECT_NOMBRE = "SELECT codigo_vendedor, nombre_vendedor, direccion_vendedor, telefono_vendedor FROM vendedores WHERE nombre_vendedor = ?";
    private static final String SQL_SELECT_ID = "SELECT codigo_vendedor, nombre_vendedor, direccion_vendedor, telefono_vendedor FROM usuario WHERE codigo_vendedor = ?";     

    public List<clsVendedores> consultaVendedores() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<clsVendedores> vendedores = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                
                String codigo = rs.getString("codigo_vendedor");
                String nombre = rs.getString("nombre_vendedor");
                String direccion = rs.getString("direccion_vendedor");
		String telefono = rs.getString("telefono_vendedor");
                String nit= rs.getString("nit_vendedor");
                String estatus= rs.getString("estatus_vendedor");
                
		clsVendedores vendedor = new clsVendedores();
                vendedor.setCodigo_vendedor(codigo);
                vendedor.setNombre_vendedor(nombre);
                vendedor.setDireccion_vendedor(direccion);
                vendedor.setTelefono_vendedor(telefono);
                vendedor.setNit_vendedor(nit);
                vendedor.setEstatus_vendedor(estatus);
               
              
                vendedores.add(vendedor);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return vendedores;
    }

   public int ingresaVendedores(clsVendedores vendedor) {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    int rows = 0;

    try {
        conn = Conexion.getConnection();

        //obtener ID generado
        stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

        stmt.setString(1, vendedor.getCodigo_vendedor());
        stmt.setString(2, vendedor.getNombre_vendedor());
        stmt.setString(3, vendedor.getDireccion_vendedor());
        stmt.setString(4, vendedor.getTelefono_vendedor());
        stmt.setString(5, vendedor.getNit_vendedor());
        stmt.setString(6, vendedor.getEstatus_vendedor());
    

        System.out.println("ejecutando query:" + SQL_INSERT);

        rows = stmt.executeUpdate();

        int idGenerado = 0;

        //ID real
        rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            idGenerado = rs.getInt(1);
        }

        // BITACORA
        if (rows > 0 && idGenerado > 0) {
            BitacoraDAO bitacora = new BitacoraDAO();

            int usuarioBitacora = clsUsuarioConectado.getUsuId();

            if (usuarioBitacora == 0) {
                usuarioBitacora = idGenerado;
            }

            bitacora.insert(usuarioBitacora, 1, "INSERT usuario: " + vendedor.getNombre_vendedor());
        }

        System.out.println("Registros afectados:" + rows);

    } catch (SQLException ex) {
        ex.printStackTrace(System.out);
    } finally {
        Conexion.close(rs);
        Conexion.close(stmt);
        Conexion.close(conn);
    }

    return rows;
}

    public int actualizaVendedores(clsUsuario usuario,clsVendedores vendedor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            //cambiado abajo
            stmt = conn.prepareStatement(SQL_UPDATE);
            //
           stmt.setString(1, vendedor.getCodigo_vendedor());
        stmt.setString(2, vendedor.getNombre_vendedor());
        stmt.setString(3, vendedor.getDireccion_vendedor());
        stmt.setString(4, vendedor.getTelefono_vendedor());
        stmt.setString(5, vendedor.getNit_vendedor());
        stmt.setString(6, vendedor.getEstatus_vendedor());
            
            rows = stmt.executeUpdate();
            
            if (rows > 0) {
    BitacoraDAO bitacora = new BitacoraDAO();

    int usuarioBitacora = clsUsuarioConectado.getUsuId();
    if (usuarioBitacora == 0) {
        usuarioBitacora = usuario.getUsuId();
    }

    bitacora.insert(usuarioBitacora, 1, "UPDATE usuario: " + vendedor.getNombre_vendedor());
}
            
            
            System.out.println("Registros actualizado:" + rows);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int borrarVendedores(clsUsuario usuario,clsVendedores vendedor) {
    Connection conn = null;
    PreparedStatement stmt = null;
    int rows = 0;

    try {
        conn = Conexion.getConnection();
        System.out.println("Ejecutando query:" + SQL_DELETE);

        // MOSTRAR USUARIO CONECTADO
        System.out.println("Usuario conectado antes de eliminar: " + clsUsuarioConectado.getUsuId());

        // BITACORA ANTES DEL DELETE
        int usuarioBitacora = clsUsuarioConectado.getUsuId();

if (usuarioBitacora == 0) {
    usuarioBitacora = usuario.getUsuId();
}

BitacoraDAO bitacora = new BitacoraDAO();
bitacora.insert(usuarioBitacora,1,"DELETE usuario ID: " + usuario.getUsuId()
);

        // DELETE
        stmt = conn.prepareStatement(SQL_DELETE);
        stmt.setInt(1, usuario.getUsuId());
        rows = stmt.executeUpdate();

        System.out.println("Registros eliminados: " + rows);

    } catch (SQLException ex) {
        ex.printStackTrace(System.out);
    } finally {
        Conexion.close(stmt);
        Conexion.close(conn);
    }

    return rows;
}

    public clsVendedores consultaVendedoresPorNombre(clsVendedores vendedor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_SELECT_NOMBRE + " objeto recibido: " + vendedor);
            stmt = conn.prepareStatement(SQL_SELECT_NOMBRE);
            //stmt.setInt(1, usuario.getIdUsuario());            
            stmt.setString(1, vendedor.getNombre_vendedor());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("usuid");
                String codigo = rs.getString("codigo_vendedor");
                String nombre = rs.getString("nombre_vendedor");
                String direccion = rs.getString("direccion_vendedor");
		String telefono = rs.getString("telefono_vendedor");
                String nit= rs.getString("nit_vendedor");
                String estatus= rs.getString("estatus_vendedor");
                
                //usuario = new ClsUsuario();
            
                vendedor.setCodigo_vendedor(codigo);
                vendedor.setNombre_vendedor(nombre);
                vendedor.setDireccion_vendedor(direccion);
                vendedor.setTelefono_vendedor(telefono);
                vendedor.setNit_vendedor(nit);
                vendedor.setEstatus_vendedor(estatus);
                
                System.out.println(" registro consultado: " + vendedor);                
            }
            //System.out.println("Registros buscado:" + persona);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        //return personas;  // Si se utiliza un ArrayList
        return vendedor;
    }
    public clsVendedores consultaVendedoresId(clsVendedores vendedores) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_SELECT_NOMBRE + " objeto recibido: " + vendedores);
            stmt = conn.prepareStatement(SQL_SELECT_ID);
            stmt.setInt(1,Integer.parseInt(vendedores.getCodigo_vendedor()));            
            //stmt.setString(1, usuario.getNombreUsuario());
            rs = stmt.executeQuery();
            while (rs.next()) {
                
                String codigo = rs.getString("codigo_vendedor");
                String nombre = rs.getString("nombre_vendedor");
                String direccion = rs.getString("direccion_vendedor");
		String telefono = rs.getString("telefono_vendedor");
                String nit= rs.getString("nit_vendedor");
                String estatus= rs.getString("estatus_vendedor");
		
                //usuario = new ClsUsuario();
                vendedores.setCodigo_vendedor(codigo);
                vendedores.setNombre_vendedor(nombre);
                vendedores.setDireccion_vendedor(direccion);
                vendedores.setTelefono_vendedor(telefono);
                vendedores.setNit_vendedor(nit);
                vendedores.setEstatus_vendedor(estatus);
                
                System.out.println(" registro consultado: " + vendedores);                
            }
            //System.out.println("Registros buscado:" + persona);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        //return personas;  // Si se utiliza un ArrayList
        return vendedores;
    }    

}