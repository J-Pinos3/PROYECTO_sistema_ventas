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



            }


        }catch (HeadlessException | SQLException e){
            System.out.println("ERROR: " + e.toString());
        }
        return l;
    }
}
