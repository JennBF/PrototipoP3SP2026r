/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.UsuarioDAO;
import Modelo.VendedoresDAO;
import java.util.List;

/**
 *
 * @author 50240
 */
public class clsVendedores {
    
    private String codigo_vendedor;
    private String nombre_vendedor;
    private String direccion_vendedor;
    private String telefono_vendedor;
    private String nit_vendedor;
    private String estatus_vendedor;

    public clsVendedores() {
    }

    public clsVendedores(String nombre_vendedor, String direccion_vendedor, String telefono_vendedor, String nit_vendedor, String estatus_vendedor) {
        this.nombre_vendedor = nombre_vendedor;
        this.direccion_vendedor = direccion_vendedor;
        this.telefono_vendedor = telefono_vendedor;
        this.nit_vendedor = nit_vendedor;
        this.estatus_vendedor = estatus_vendedor;
    }

    public clsVendedores(String codigo_vendedor, String nombre_vendedor, String direccion_vendedor, String telefono_vendedor, String nit_vendedor, String estatus_vendedor) {
        this.codigo_vendedor = codigo_vendedor;
        this.nombre_vendedor = nombre_vendedor;
        this.direccion_vendedor = direccion_vendedor;
        this.telefono_vendedor = telefono_vendedor;
        this.nit_vendedor = nit_vendedor;
        this.estatus_vendedor = estatus_vendedor;
    }

    public String getCodigo_vendedor() {
        return codigo_vendedor;
    }

    public void setCodigo_vendedor(String codigo_vendedor) {
        this.codigo_vendedor = codigo_vendedor;
    }

    public String getNombre_vendedor() {
        return nombre_vendedor;
    }

    public void setNombre_vendedor(String nombre_vendedor) {
        this.nombre_vendedor = nombre_vendedor;
    }

    public String getDireccion_vendedor() {
        return direccion_vendedor;
    }

    public void setDireccion_vendedor(String direccion_vendedor) {
        this.direccion_vendedor = direccion_vendedor;
    }

    public String getTelefono_vendedor() {
        return telefono_vendedor;
    }

    public void setTelefono_vendedor(String telefono_vendedor) {
        this.telefono_vendedor = telefono_vendedor;
    }

    public String getNit_vendedor() {
        return nit_vendedor;
    }

    public void setNit_vendedor(String nit_vendedor) {
        this.nit_vendedor = nit_vendedor;
    }

    public String getEstatus_vendedor() {
        return estatus_vendedor;
    }

    public void setEstatus_vendedor(String estatus_vendedor) {
        this.estatus_vendedor = estatus_vendedor;
    }

    @Override
    public String toString() {
        return "clsVendedores{" + "codigo_vendedor=" + codigo_vendedor + ", nombre_vendedor=" + nombre_vendedor + ", direccion_vendedor=" + direccion_vendedor + ", telefono_vendedor=" + telefono_vendedor + ", nit_vendedor=" + nit_vendedor + ", estatus_vendedor=" + estatus_vendedor + '}';
    }

    public clsVendedores getBuscarInformacionVendedorPorNombre(clsVendedores vendedor) {
        VendedoresDAO daovendedor = new VendedoresDAO();
        return daovendedor.consultaVendedoresPorNombre(vendedor);
    }

    public clsVendedores getBuscarInformacionVendedorPorId(clsVendedores vendedor) {
        VendedoresDAO daovendedor = new VendedoresDAO();
        return daovendedor.consultaVendedoresId(vendedor);
    }    

    public List<clsVendedores> getListadoVendedores() {
        VendedoresDAO daovendedor = new VendedoresDAO();
        List<clsVendedores> listadoVendedores = daovendedor.consultaVendedores();
        return listadoVendedores;
    }

    public int setBorrarVendedor(clsUsuario usuario,clsVendedores vendedor) {
        VendedoresDAO daovendedor = new VendedoresDAO();
        UsuarioDAO daousuario = new UsuarioDAO();
        return daovendedor.borrarVendedores(usuario,vendedor);
    }          

    public int setIngresarVendedor(clsVendedores vendedor) {
        VendedoresDAO daovendedor = new VendedoresDAO();
        
        return daovendedor.ingresaVendedores(vendedor);
    }              

    public int setModificarVendedor(clsUsuario usuario,clsVendedores vendedor) {
        VendedoresDAO daovendedor = new VendedoresDAO();
        UsuarioDAO daousuario = new UsuarioDAO();
        return daovendedor.actualizaVendedores(usuario,vendedor);
    }
}
   
