package Vista;

import Modelo.LoginDAO;
import Modelo.login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame{
    login lg = new login();
    LoginDAO login = new LoginDAO();
    private JPanel panel_Login;
    public JTextField txtCorreo;
    public JPasswordField txtPass;
    private JButton btnIniciar;

    public Login(){
        setContentPane(panel_Login);
        setVisible(true);
        setTitle("Login");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800,500);



        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validar();
            }
        });

    }

    public void validar(){
        String correo = txtCorreo.getText();
        String pass = String.valueOf(txtPass.getPassword());

        if(!"".equals(correo) || !"".equals(pass)){

            lg = login.log(correo,pass);

            System.out.println("CorreoLogin: " + correo);
            System.out.println("PassLogin: " + pass);

            if(lg.getCorreo() != null && lg.getPass() != null){
                Sistema sis = new Sistema(lg);
                sis.setVisible(true);
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(null,"Correo o contraseña incorrectos");
            }
        }
    }

}//FIN DE LA CLASE LOGIN
