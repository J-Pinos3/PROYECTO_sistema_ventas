package Modelo;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class VentaDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r = 0;

    public int RegistrarVenta(Venta v){
        String sql = "INSERT INTO ventas(cliente, Vendedor, total) VALUES(?,?,?)";

        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,v.getCliente());
            ps.setString(2,v.getVendedor());
            ps.setDouble(3, v.getTotal());

            int res = ps.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Venta Registrada Exitosamente");
            }else{
                JOptionPane.showMessageDialog(null, "Error al Registrar la Venta");
            }

        }catch (HeadlessException | SQLException e){
            System.out.println(e.toString());
        }finally {
            try{
                con.close();
            }catch (SQLException e1){
                System.out.println("Error: " + e1.toString());
            }
        }

        return r;
    }










}
