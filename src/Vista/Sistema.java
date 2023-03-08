package Vista;

import Modelo.*;
import org.jdesktop.swingx.autocomplete.AutoCompleteComboBoxEditor;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    //EN EL TERCER TAB, la tabla tiene las columnas ID, RUC, NOMBRE, TELÉFONO, DIRECCIÓN, RAZÓN SOCIAL
    //En EL CUARTO TAB, la tabla tiene las columnas CÓDIGO, DESCRIPCIÓN, STOCK, PRECIO, PROVEEDOR
    //EN EL QUINTO TAB,  la tabla tiene las columnas ID, CLIENTE, VENDEDOR, TOTAL
    Cliente cl = new Cliente();
    ClienteDAO cliente = new ClienteDAO();
    Proveedor pr = new Proveedor();
    ProveedorDAO PrDao = new ProveedorDAO();
    Productos prod = new Productos();
    ProductosDAO Prod_dao = new ProductosDAO();


    public Sistema(){
        setContentPane(panel_Sistema);
        setVisible(true);
        setTitle("Sistema");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(1300,700);
        txtIdCliente.setVisible(false);

        btnGuardarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(  !"".equals(txtDniCliente.getText())  || !"".equals(txtNombreCliente.getText()) || !"".equals(txtTelefonoCliente.getText())
                 || !"".equals(txtDireccionCliente.getText())  || !"".equals(txtRazonCliente.getText()) ){

                    cl.setDni( Integer.parseInt(txtDniCliente.getText()) );
                    cl.setNombre( txtNombreCliente.getText() );
                    cl.setTelefono( Integer.parseInt(txtTelefonoCliente.getText()) );
                    cl.setDireccion( txtDireccionCliente.getText() );
                    cl.setRazon( txtRazonCliente.getText() );

                    cliente.RegistrarCliente(cl);

                }else{
                    JOptionPane.showMessageDialog(null,"Los campos están vacíos");
                }
                ListarClientes();
                LimpiarCliente();
            }
        });


        clientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListarClientes();
                tabbedPane1.setSelectedIndex(1);
            }
        });


        TableCliente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int fila = TableCliente.rowAtPoint(e.getPoint());
                txtIdCliente.setText( TableCliente.getValueAt(fila,0).toString() );
                txtDniCliente.setText( TableCliente.getValueAt(fila,1).toString() );
                txtNombreCliente.setText( TableCliente.getValueAt(fila,2).toString() );
                txtTelefonoCliente.setText( TableCliente.getValueAt(fila,3).toString() );
                txtDireccionCliente.setText( TableCliente.getValueAt(fila,4).toString() );
                txtRazonCliente.setText( TableCliente.getValueAt(fila,5).toString() );
            }
        });


        btnEliminarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!"".equals(txtIdCliente.getText())){
                    int  pregunta = JOptionPane.showConfirmDialog(null,
                            "Está seguro de eliminar este cliente?");
                    if(pregunta == 0){
                        int id = Integer.parseInt(txtIdCliente.getText());
                        cliente.EliminarCliente(id);

                    }
                    JOptionPane.showMessageDialog(null,"Cliente Eliminado");
                }else{
                    JOptionPane.showMessageDialog(null,"Los Campos están vacíos");
                }
                ListarClientes();
                LimpiarCliente();
            }
        });


        btnEditarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if("".equals(txtIdCliente.getText())){
                    JOptionPane.showMessageDialog(null,"Seleccione una fila");
                }else{
                    if( !"".equals(txtDniCliente.getText()) || !"".equals(txtNombreCliente.getText()) || !"".equals(txtTelefonoCliente.getText())
                     || !"".equals(txtDireccionCliente.getText()) || !"".equals(txtRazonCliente.getText()) || !"".equals(txtIdCliente.getText()) ){

                        cl.setDni( Integer.parseInt(txtDniCliente.getText()) );
                        cl.setNombre(txtNombreCliente.getText());
                        cl.setTelefono(Integer.parseInt(txtTelefonoCliente.getText()));
                        cl.setDireccion(txtDireccionCliente.getText());
                        cl.setRazon(txtRazonCliente.getText());
                        cl.setId(Integer.parseInt(txtIdCliente.getText()));
                        cliente.ModificarCliente(cl);
                        LimpiarCliente();
                        ListarClientes();
                    }else{
                        JOptionPane.showMessageDialog(null, "Los campos están vacíos");
                    }
                }
            }
        });


        btnNuevoCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LimpiarCliente();
            }
        });

        /********************************************************************************************************************/


        btnGuardarProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if( !"".equals(txtRucProveedor.getText())  || !"".equals(txtNombreProveedor.getText()) || !"".equals(txtTelefonoProveedor.getText())
                        || !"".equals(txtDireccionProveedor.getText()) || !"".equals(txtRazonProveedor.getText()) ){

                    pr.setRuc(Integer.parseInt(txtRucProveedor.getText()));
                    pr.setNombre(txtNombreProveedor.getText());
                    pr.setTelefono(Integer.parseInt(txtTelefonoProveedor.getText()));
                    pr.setDireccion(txtDireccionProveedor.getText());
                    pr.setRazon(txtRazonProveedor.getText());

                    PrDao.RegistrarProveedor(pr);
                }else{
                    JOptionPane.showMessageDialog(null,"Los campos están vacíos");
                }
                ListarProveedores();
                LimpiarProveedor();
            }
        });


        proveedoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListarProveedores();
                tabbedPane1.setSelectedIndex(2);
            }
        });


        TableProveedor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int fila = TableProveedor.rowAtPoint(e.getPoint());
                txtIdProveedor.setText( TableProveedor.getValueAt(fila,0).toString() );
                txtRucProveedor.setText( TableProveedor.getValueAt(fila,1).toString() );
                txtNombreProveedor.setText( TableProveedor.getValueAt(fila,2).toString() );
                txtTelefonoProveedor.setText( TableProveedor.getValueAt(fila,3).toString() );
                txtDireccionProveedor.setText( TableProveedor.getValueAt(fila,4).toString() );
                txtRazonProveedor.setText( TableProveedor.getValueAt(fila,5).toString() );

            }
        });


        btnEliminarProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!"".equals(txtIdProveedor.getText())){
                    int  pregunta = JOptionPane.showConfirmDialog(null,
                            "Está seguro de eliminar este proveedor?");
                    if(pregunta == 0){
                        int id = Integer.parseInt(txtIdProveedor.getText());
                        PrDao.EliminarProveedor(id);

                    }
                    JOptionPane.showMessageDialog(null,"Proveedor Eliminado");
                }else{
                    JOptionPane.showMessageDialog(null,"Los Campos están vacíos");
                }
                ListarProveedores();
                LimpiarProveedor();
            }
        });


        btnEditarProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if("".equals(txtIdProveedor.getText())){
                    JOptionPane.showMessageDialog(null,"Seleccione una fila");
                }else{
                    if( !"".equals(txtRucProveedor.getText()) || !"".equals(txtNombreProveedor.getText()) || !"".equals(txtTelefonoProveedor.getText())
                            || !"".equals(txtDireccionProveedor.getText()) || !"".equals(txtRazonProveedor.getText()) || !"".equals(txtIdProveedor.getText()) ){

                        pr.setRuc( Integer.parseInt(txtRucProveedor.getText()) );
                        pr.setNombre(txtNombreProveedor.getText());
                        pr.setTelefono(Integer.parseInt(txtTelefonoProveedor.getText()));
                        pr.setDireccion(txtDireccionProveedor.getText());
                        pr.setRazon(txtRazonProveedor.getText());
                        pr.setId(Integer.parseInt(txtIdProveedor.getText()));
                        PrDao.ModificarProveedor(pr);
                        LimpiarProveedor();
                        ListarProveedores();
                    }else{
                        JOptionPane.showMessageDialog(null, "Los campos están vacíos");
                    }
                }
            }
        });


        btnNuevoProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LimpiarProveedor();
            }
        });

        //**********************************************************************************************************************************

        Prod_dao.RellenarComboProveedores(cbxProveedorProducto);
        AutoCompleteDecorator.decorate(cbxProveedorProducto);

        productosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //FALTA AGREGAR EL MÉTODO DE LISTAR
                tabbedPane1.setSelectedIndex(3);
                ListarProductos();

            }
        });


        btnGuardarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!"".equals(txtCodigoProducto.getText()) || !"".equals(txtDescripcionProducto.getText()) || !"".equals(cbxProveedorProducto.getSelectedItem()) || !"".equals(txtCantidadProducto.getText()) || !"".equals(txtPrecioProducto.getText()) ) {

                    prod.setCodigo(txtCodigoProducto.getText());
                    prod.setNombre(txtDescripcionProducto.getText());
                    prod.setProveedor((String)cbxProveedorProducto.getSelectedItem());
                    prod.setStock( Integer.parseInt(txtCantidadProducto.getText()) );
                    prod.setPrecio( Double.parseDouble(txtPrecioProducto.getText()) );

                    Prod_dao.RegistrarProducto(prod);
                }else{
                    JOptionPane.showMessageDialog(null,"Los campos están vacíos");
                }
                LimpiarProductos();
                ListarProductos();
            }
        });


        TableProducto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int fila = TableProducto.rowAtPoint(e.getPoint());
                txtIdProducto.setText( TableProducto.getValueAt(fila,0).toString() );
                txtCodigoProducto.setText( TableProducto.getValueAt(fila,1).toString() );
                txtDescripcionProducto.setText( TableProducto.getValueAt(fila,2).toString() );
                cbxProveedorProducto.setSelectedItem(TableProducto.getValueAt(fila,3).toString());
                txtCantidadProducto.setText( TableProducto.getValueAt(fila,4).toString() );
                txtPrecioProducto.setText( TableProducto.getValueAt(fila,5).toString() );
            }
        });

    }//FIN DEL CONSTRUCTOR DE LA CLASE SISTEMA

    public void ListarClientes(){
        List<Cliente> ListarCl = cliente.ListarCliente();
        String[] titulos = {"ID","Cédula/RUC","Nombre","Teléfono","Dirección","Razón Social"};
        //modelo = (DefaultTableModel) TableCliente.getModel();
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

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


    private void LimpiarCliente(){
        txtIdCliente.setText("");
        txtDniCliente.setText("");
        txtNombreCliente.setText("");
        txtTelefonoCliente.setText("");
        txtDireccionCliente.setText("");
        txtRazonCliente.setText("");
    }


    public void ListarProveedores(){
        List<Proveedor> ListarPr = PrDao.ListarProveedor();
        String[] titulos = {"ID","RUC","Nombre","Teléfono","Dirección","Razón Social"};
        //modelo = (DefaultTableModel) TableCliente.getModel();
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        Object[] obj = new Object[6];
        for(int i = 0; i < ListarPr.size(); i++){
            obj[0] = ListarPr.get(i).getId();
            obj[1] = ListarPr.get(i).getRuc();
            obj[2] = ListarPr.get(i).getNombre();
            obj[3] = ListarPr.get(i).getTelefono();
            obj[4] = ListarPr.get(i).getDireccion();
            obj[5] = ListarPr.get(i).getRazon();

            modelo.addRow(obj);
        }
        TableProveedor.setModel(modelo);
    }


    private void LimpiarProveedor(){
        txtIdProveedor.setText("");
        txtRucProveedor.setText("");
        txtNombreProveedor.setText("");
        txtTelefonoProveedor.setText("");
        txtDireccionProveedor.setText("");
        txtRazonProveedor.setText("");
    }



    public void ListarProductos(){
        List<Productos> ListarProds = Prod_dao.ListarProductos();
        String[] titulos = {"ID","CÓDIGO","DESCRIPCIÓN","PROVEEDOR","STOCK","PRECIO"};
        //modelo = (DefaultTableModel) TableCliente.getModel();
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);

        Object[] obj = new Object[6];
        for(int i = 0; i < ListarProds.size(); i++){
            obj[0] = ListarProds.get(i).getId();
            obj[1] = ListarProds.get(i).getCodigo();
            obj[2] = ListarProds.get(i).getNombre();
            obj[3] = ListarProds.get(i).getProveedor();
            obj[4] = ListarProds.get(i).getStock();
            obj[5] = ListarProds.get(i).getPrecio();

            modelo.addRow(obj);
        }
        TableProducto.setModel(modelo);
    }

    private void LimpiarProductos(){
        txtIdProducto.setText("");
        txtCodigoProducto.setText("");
        txtDescripcionProducto.setText("");
        txtCantidadProducto.setText("");
        txtPrecioProducto.setText("");
    }
}
