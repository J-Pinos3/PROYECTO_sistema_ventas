package Vista;

import javax.swing.*;

public class Login extends JFrame{
    private JPanel panel_Login;
    private JTextField txtCorreo;
    private JPasswordField txtPass;
    private JButton iniciarSesiónButton;

    public Login(){
        setContentPane(panel_Login);
        setVisible(true);
        setTitle("Login");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800,500);
    }


}
