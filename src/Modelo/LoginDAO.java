package Modelo;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

    public login log(String correo, String pass){
        login l = new login();
        String sql = "SELECT * FROM usuarios WHERE correous = ? AND passus = ?";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,correo);
            ps.setString(2,pass);

            rs = ps.executeQuery();

            while (rs.next()){
                /*
                JOptionPane.showMessageDialog(null,rs.getInt("id")+
                "\n"+rs.getString("nombre") + "\n" + rs.getString("correous")+
                "\n" + rs.getString("passus") );
                */


                l.setId( rs.getInt("id") );
                l.setNombre( rs.getString("nombre") );
                l.setCorreo( rs.getString("correous") );
                l.setPass( rs.getString("passus") );
                l.setRol(rs.getString("rol"));


            }


        }catch (HeadlessException | SQLException e){
            System.out.println("ERROR: " + e.toString());
        }
        return l;
    }


    public boolean Registrar(login reg){
        String sql = "INSERT INTO usuarios (nombre, correous, passus, rol) VALUES (?,?,?,?)";

        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, reg.getNombre());
            ps.setString(2, reg.getCorreo());
            ps.setString(3, reg.getPass());
            ps.setString(4, reg.getRol());

            int res = ps.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null, "Usuario Registrado Exitosamente");
            }else{
                JOptionPane.showMessageDialog(null, "Error al registrar el Usuario");
            }
            return true;
        }catch (SQLException e){
            System.out.println(e.toString());
            return false;
        }

    }

}//FIN DE LA CLASE LOGINDAO
