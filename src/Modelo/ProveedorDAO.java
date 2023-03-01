package Modelo;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ProveedorDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public boolean RegistrarProveedor(Proveedor pr){
        String sql = "INSERT INTO proveedore (ruc, nombre, telefono, direccion, razon) VALUES(?,?,?,?,?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, pr.getRuc());
            ps.setString(2,pr.getNombre());
            ps.setInt(3,pr.getTelefono());
            ps.setString(4,pr.getDireccion());
            ps.setString(5,pr.getRazon());

            int res = ps.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Proveedor Registrado Exitosamente");
            }else{
                JOptionPane.showMessageDialog(null, "Error al registrar el proveedor");
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


}
