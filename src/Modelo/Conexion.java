package Modelo;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    Connection con;
    public Connection getConnection(){
        try{
            String myDB = "jdbc:mysql://localhost:3307/sistemaventa";
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(myDB,"root","");
        }catch (ClassNotFoundException | SQLException e){
            System.out.println("Error: " + e.toString());
        }
        return con;
    }
}
