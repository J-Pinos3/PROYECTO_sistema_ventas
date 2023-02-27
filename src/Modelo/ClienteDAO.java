package Modelo;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ClienteDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;


    public boolean RegistrarCliente(Cliente cl){
        String sql = "INSERT INTO clientes (dni, nombre, telefono, direccion, razon) VALUES (?,?,?,?,?)";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cl.getDni());
            ps.setString(2,cl.getNombre());
            ps.setInt(3,cl.getTelefono());
            ps.setString(4,cl.getDireccion());
            ps.setString(5,cl.getRazon());

            int res = ps.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Cliente Registrado Exitosamente");
            }else{
                JOptionPane.showMessageDialog(null, "Error al Registrar el Cliente");
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

    }//fin del método registrarCliente

}//FIN DE LA CLASE CLIENTE DAO
