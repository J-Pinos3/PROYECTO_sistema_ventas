package Vista;

import Modelo.LoginDAO;
import Modelo.login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registro extends JFrame{
    login lg = new login();
    LoginDAO login = new LoginDAO();
    private JPanel panel_registro;
    private JTextField txt_correo;
    private JPasswordField txt_pass;
    private JTextField txt_nombre;
    private JComboBox cmb_rol;
    private JButton INICIARButton;


    public Registro() {
        setContentPane(panel_registro);
        setVisible(true);
        setTitle("Registro");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800,500);



        INICIARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validar();
            }
        });

    }


    public void validar(){
        String correo = txt_correo.getText();
        String pass = String.valueOf(txt_pass.getPassword());
        String nom = txt_nombre.getText();
        String rol = cmb_rol.getSelectedItem().toString();

        if(!"".equals(correo) || !"".equals(pass) || !"".equals(nom)){

            lg.setNombre(nom);
            lg.setCorreo(correo);
            lg.setPass(pass);
            lg.setRol(rol);

            login.Registrar(lg);

            this.dispose();
            /*
            Login iniciar = new Login();
            iniciar.setVisible(true);
            this.dispose();
            */
        }
    }



}//FIN DE LA CLASE REGISTRO
