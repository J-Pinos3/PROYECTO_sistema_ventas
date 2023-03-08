package Modelo;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductosDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;


    public void RellenarComboProveedores(JComboBox cmb){
        String sql = "SELECT nombre from proveedore";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                cmb.addItem( rs.getString("nombre") );
            }
        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudieron cargar los Proveedores");
        }finally {
            try{
                con.close();
            }catch (SQLException e1){
                System.out.println("Error: " + e1.toString());
            }
        }
    }


    public boolean RegistrarProducto(Productos pr){
        String sql = "INSERT INTO productos (codigo, nombre, proveedor, stock, precio) VALUES (?,?,?,?,?)";

        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, pr.getCodigo());
            ps.setString(2, pr.getNombre());
            ps.setString(3, pr.getProveedor());
            ps.setInt(4, pr.getStock());
            ps.setDouble(5, pr.getPrecio());

            int res = ps.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Producto Registrado Exitosamente");
            }else{
                JOptionPane.showMessageDialog(null, "Error al registrar el Producto");
            }
            return true;
        }catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }finally {
            try{
                con.close();
            }catch (SQLException e1){
                System.out.println("Error: " + e1.toString());
            }
        }


    }


    public List ListarProductos(){

        List<Productos> Listaprods = new ArrayList();
        String sql = "SELECT * FROM productos";

        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while( rs.next() ){
                Productos prods = new Productos();
                prods.setId(rs.getInt("id"));
                prods.setCodigo(rs.getString("codigo"));
                prods.setNombre(rs.getString("nombre"));
                prods.setProveedor(rs.getString("proveedor"));
                prods.setStock(rs.getInt("stock"));
                prods.setPrecio(rs.getDouble("precio"));
                Listaprods.add(prods);

            }

        }catch (HeadlessException | SQLException e){
            System.out.println("Error: " + e.toString());
        }
        return Listaprods;
    }


}
