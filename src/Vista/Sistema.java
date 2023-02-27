package Vista;

import Modelo.Cliente;
import Modelo.ClienteDAO;

import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sistema extends JFrame{
    private JPanel panel_Sistema;
    private JTabbedPane tabbedPane1;
    private JButton nuevaVentaButton;
    private JButton productosButton;
    private JButton clientesButton;
    private JButton proveedoresButton;
    private JButton ventasButton;
    private JButton configButton;
    private JButton btnEliminarVenta;
    private JTextField txtCodigoVenta;
    private JTextField txtDescripcionVenta;
    private JTextField txtCantidadVenta;
    private JTextField txtPrecioVenta;
    private JTextField txtStockDisponible;
    private JTable TableVenta;
    private JTextField txtRucVenta;
    private JTextField txtNombreClienteVenta;
    private JButton btnGenerarVenta;
    private JTextField txtDniCliente;
    private JTextField txtNombreCliente;
    private JTextField txtTelefonoCliente;
    private JTextField txtDireccionCliente;
    private JTextField txtRazonCliente;
    private JTable TableCliente;
    private JButton btnGuardarCliente;
    private JButton btnEditarCliente;
    private JButton btnEliminarCliente;
    private JButton btnNuevoCliente;
    private JTextField txtRucProveedor;
    private JTextField txtNombreProveedor;
    private JTextField txtTelefonoProveedor;
    private JTextField txtDireccionProveedor;
    private JTextField txtRazonProveedor;
    private JTable TableProveedor;
    private JButton btnGuardarProveedor;
    private JButton btnEditarProveedor;
    private JButton btnEliminarProveedor;
    private JButton btnNuevoProveedor;
    private JTextField txtCodigoProducto;
    private JTextField txtDescripcionProducto;
    private JTextField txtCantidadProducto;
    private JTextField txtPrecioProducto;
    private JTable TableProducto;
    private JButton btnGuardarProducto;
    private JButton btnEditarProducto;
    private JButton btnEliminarProducto;
    private JButton btnNuevoProducto;
    private JComboBox cbxProveedorProducto;
    private JButton btnExcelProducto;
    private JTable TableVentasTodas;
    private JButton btnPdfVentas;
    private JTextField textField22;
    private JTextField textField23;
    private JTextField textField24;
    private JTextField textField25;
    private JTextField textField26;
    private JButton ACTUALIZARButton;
    private JLabel LabelTotal;
    private JTextField txtTelefonoCV;
    private JTextField txtDireccionCV;
    private JTextField txtRazonCV;
    private JTextField txtIdCliente;
    private JTextField txtIdProveedor;
    private JTextField txtIdProducto;
    private JTextField txtIdVenta;
    private JTextField txtIDpro;
    //EN EL PRIMER TAB. la tabla tiene columnas CODIGO, DESCRIPCION, CANTIDAD, PRECIO, TOTAL
    //EN EL SEGUNDO TAB. la tabla tiene columnas Cédula/RUC, Nombre, Teléfono, Dirección, Razón Social
    //EN EL TERCER TAB, la tabla tiene las columnas RUC, NOMBRE, TELÉFONO, DIRECCIÓN, RAZÓN SOCIAL
    //En EL CUARTO TAB, la tabla tiene las columnas CÓDIGO, DESCRIPCIÓN, STOCK, PRECIO, PROVEEDOR
    //EN EL QUINTO TAB,  la tabla tiene las columnas ID, CLIENTE, VENDEDOR, TOTAL
    Cliente cl = new Cliente();
    ClienteDAO cliente = new ClienteDAO();
    DefaultTableModel modelo;

    public Sistema(){
        setContentPane(panel_Sistema);
        setVisible(true);
        setTitle("Sistema");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800,500);


        btnGuardarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(  !"".equals(txtDniCliente.getText())  || !"".equals(txtNombreCliente.getText()) || !"".equals(txtTelefonoCliente.getText())
                 || !"".equals(txtDireccionCliente.getText())  || !"".equals(txtRazonCliente.getText()) ){

                    cl.setDni( Integer.parseInt(txtDniCliente.getText()) );
                    cl.setNombre( txtNombreCliente.getText() );
                    cl.setTelefono( Integer.parseInt(txtTelefonoCliente.getText()) );
                    cl.setDireccion( txtRazonCliente.getText() );
                    cl.setRazon( txtRazonCliente.getText() );

                    cliente.RegistrarCliente(cl);

                }else{
                    JOptionPane.showMessageDialog(null,"Los campos están vacíos");
                }
                ListarClientes();
            }
        });


        clientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListarClientes();
                tabbedPane1.setSelectedIndex(1);
            }
        });
    }

    public void ListarClientes(){
        List<Cliente> ListarCl = cliente.ListarCliente();
        String[] titulos = {"Cédula/RUC","Nombre","Teléfono","Dirección","Razón Social"};
        //modelo = (DefaultTableModel) TableCliente.getModel();
        modelo = new DefaultTableModel(null, titulos);

        Object[] obj = new Object[6];
        for(int i = 0; i < ListarCl.size(); i++){
            obj[0] = ListarCl.get(i).getId();
            obj[1] = ListarCl.get(i).getDni();
            obj[2] = ListarCl.get(i).getNombre();
            obj[3] = ListarCl.get(i).getTelefono();
            obj[4] = ListarCl.get(i).getDireccion();
            obj[5] = ListarCl.get(i).getRazon();

            modelo.addRow(obj);
        }
        TableCliente.setModel(modelo);
    }

    //Video7 conexion y login
}
