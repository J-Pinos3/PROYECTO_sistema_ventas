package Vista;

import Modelo.*;
import Reportes.Excel;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
    private JLabel LabelVendedor;
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
    Venta ven = new Venta();
    VentaDAO venta_dao = new VentaDAO();
    Detalle Dven = new Detalle();

    int item = 0;
    double total_a_pagar = 0.0;
    //tabla venta
    String[] titulosVenta = {"CODIGO","DESCRIPCION","CANTIDAD","PRECIO","TOTAL"};
    DefaultTableModel modeloVenta = new DefaultTableModel(titulosVenta,0);


    public Sistema(){
        setContentPane(panel_Sistema);
        setVisible(true);
        setTitle("Sistema");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(1300,700);
        txtIdCliente.setVisible(false);

        pdf();

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


        btnEliminarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!"".equals(txtIdProducto.getText())){
                    int  pregunta = JOptionPane.showConfirmDialog(null,
                            "Está seguro de eliminar este producto?");
                    if(pregunta == 0){
                        int id = Integer.parseInt(txtIdProducto.getText());
                        Prod_dao.EliminarProducto(id);

                    }
                    JOptionPane.showMessageDialog(null,"Producto Eliminado");
                }else{
                    JOptionPane.showMessageDialog(null,"Los Campos están vacíos");
                }
                ListarProductos();
                LimpiarProductos();
            }
        });


        btnEditarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if("".equals(txtIdProducto.getText())){
                    JOptionPane.showMessageDialog(null,"Seleccione una fila");
                }else{
                    if( !"".equals(txtCodigoProducto.getText()) || !"".equals(txtDescripcionProducto.getText()) || !"".equals(cbxProveedorProducto.getSelectedItem())
                            || !"".equals(txtCantidadProducto.getText()) || !"".equals(txtPrecioProducto.getText()) || !"".equals(txtIdProducto.getText()) ){

                        prod.setCodigo( txtCodigoProducto.getText() );
                        prod.setNombre(txtDescripcionProducto.getText());
                        prod.setProveedor( (String)cbxProveedorProducto.getSelectedItem() );
                        prod.setStock( Integer.parseInt(txtCantidadProducto.getText()) );
                        prod.setPrecio( Double.parseDouble(txtPrecioProducto.getText()) );
                        prod.setId(Integer.parseInt(txtIdProducto.getText()));
                        Prod_dao.ModificarProducto(prod);

                        LimpiarProductos();
                        ListarProductos();
                    }else{
                        JOptionPane.showMessageDialog(null, "Los campos están vacíos");
                    }
                }
            }
        });


        btnNuevoProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LimpiarProductos();
            }
        });


        btnExcelProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Excel.reporte();
            }
        });

        //***********************************************************************************************************

        txtCodigoVenta.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    if( !"".equals(txtCodigoVenta.getText()) ){

                        String cod = txtCodigoVenta.getText();
                        prod = Prod_dao.BuscarPro(cod);
                        if(prod.getNombre() != null){
                            txtDescripcionVenta.setText(""+prod.getNombre());
                            txtStockDisponible.setText(""+prod.getStock());
                            txtPrecioVenta.setText(""+prod.getPrecio());
                            txtCantidadVenta.requestFocus();
                        }else{
                            txtDescripcionVenta.setText("");
                            txtStockDisponible.setText("");
                            txtPrecioVenta.setText("");
                            txtCodigoVenta.requestFocus();
                        }

                    }else{
                        JOptionPane.showMessageDialog(null, "Ingrese el código del producto");
                        txtCodigoVenta.requestFocus();
                    }
                }

            }
        });


        txtCantidadVenta.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    if(!"".equals(txtCantidadVenta.getText())){
                        String cod = txtCodigoVenta.getText();
                        String descripcion = txtDescripcionVenta.getText();
                        int cantidad =  Integer.parseInt(txtCantidadVenta.getText());
                        double precio = Double.parseDouble(txtPrecioVenta.getText());
                        double total = cantidad * precio;
                        int stock = Integer.parseInt(txtStockDisponible.getText());


                        if(stock >= cantidad){
                            item = item + 1;
                //EN EL PRIMER TAB. la tabla tiene columnas CODIGO, DESCRIPCION, CANTIDAD, PRECIO, TOTAL
                            //String[] titulos = {"CODIGO","DESCRIPCION","CANTIDAD","PRECIO","TOTAL"};
                            //DefaultTableModel modeloVenta = new DefaultTableModel(titulos,0);
                            for(int i = 0; i < TableVenta.getRowCount(); i++){
                                if(TableVenta.getValueAt(i,1).equals(txtDescripcionVenta.getText()) ){
                                    JOptionPane.showMessageDialog(null, "El producto ya está registrado");
                                    return ;
                                }
                            }
                            ArrayList lista = new ArrayList();
                            lista.add(item);
                            lista.add(cod);
                            lista.add(descripcion);
                            lista.add(cantidad);
                            lista.add(precio);
                            lista.add(total);

                            Object[] Ob = new Object[5];
                            Ob[0] = lista.get(1);
                            Ob[1] = lista.get(2);
                            Ob[2] = lista.get(3);
                            Ob[3] = lista.get(4);
                            Ob[4] = lista.get(5);
                            modeloVenta.addRow(Ob);
                            TableVenta.setModel(modeloVenta);
                            TotalPagar();
                            LimpiarVentas();
                            txtCodigoVenta.requestFocus();
                        }else{
                            JOptionPane.showMessageDialog(null,"Stock No Disponible");
                        }

                    }else{
                        JOptionPane.showMessageDialog(null,"Ingrese una cantidad válida");
                    }
                }
            }
        });


        btnEliminarVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modeloVenta = (DefaultTableModel) TableVenta.getModel();
                modeloVenta.removeRow(TableVenta.getSelectedRow());
                TotalPagar();
                txtCodigoVenta.requestFocus();
            }
        });


        txtRucVenta.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    if( !"".equals(txtRucVenta.getText()) ){

                        int dni = Integer.parseInt(txtRucVenta.getText());
                        cl = cliente.BuscarCliente( dni );

                        if(cl.getNombre() != null){
                            txtNombreClienteVenta.setText(""+cl.getNombre());
                            txtTelefonoCV.setText(""+cl.getTelefono());
                            txtDireccionCV.setText(""+cl.getDireccion());
                            txtRazonCV.setText(""+cl.getRazon());
                        }else{
                            txtRucVenta.setText("");
                            JOptionPane.showMessageDialog(null,"El cliente no existe");
                        }

                    }
                }

            }
        });


        btnGenerarVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrarVenta();
                RegistrarDetalle();
                Actualizar_stock();
                LimpiarTableventa();
                //despues de cada venta se deben limpiar los campos de cliente
            }
        });


        nuevaVentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane1.setSelectedIndex(0);
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
        DefaultTableModel modelo = new DefaultTableModel(titulos,0);

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


    private void TotalPagar(){
        total_a_pagar = 0.0;
        int fila =  TableVenta.getRowCount();
        double calcular = 0;
        for(int i = 0; i < fila; i++){
            calcular = Double.parseDouble( String.valueOf(TableVenta.getModel().getValueAt(i,4)) ) ;
            total_a_pagar += calcular;
        }

        //LabelTotal.setText( String.valueOf(total_a_pagar) );
        LabelTotal.setText( String.format("%.2f", total_a_pagar) );
    }


    private void LimpiarVentas(){

        txtCodigoVenta.setText("");
        txtDescripcionVenta.setText("");
        txtCantidadVenta.setText("");
        txtPrecioVenta.setText("");
        txtStockDisponible.setText("");

    }


    private void RegistrarVenta(){

        String cliente = txtNombreClienteVenta.getText();
        String vendedor = LabelVendedor.getText();
        double monto = total_a_pagar;
        ven.setCliente(cliente);
        ven.setVendedor(vendedor);
        ven.setTotal(monto);

        venta_dao.RegistrarVenta(ven);
    }


    private void RegistrarDetalle(){
        //String[] titulosVenta = {"CODIGO","DESCRIPCION","CANTIDAD","PRECIO","TOTAL"};
        int id = venta_dao.IdVenta();
        //int id = 1;
        System.out.println("id venta (sistema registrar detalle)" + id);

        for (int i = 0; i < TableVenta.getRowCount(); i++) {
            String cod = TableVenta.getValueAt(i,0).toString();
            int cant = Integer.parseInt( TableVenta.getValueAt(i, 2).toString() );
            double precio = Double.parseDouble( TableVenta.getValueAt(i,3).toString() );

            Dven.setCod_pro(cod);
            Dven.setCantidad(cant);
            Dven.setPrecio(precio);
            Dven.setId_venta(id);
            venta_dao.RegistrarDetalle(Dven);

        }

    }


    private void Actualizar_stock(){
        for(int i = 0; i < TableVenta.getRowCount(); i++){
            String cod = TableVenta.getValueAt(i,0).toString();
            int canti =   Integer.parseInt( TableVenta.getValueAt(i, 2).toString() );

            prod = Prod_dao.BuscarPro(cod);

            int stockActual = prod.getStock() - canti;

            venta_dao.ActualizarStock(stockActual, cod);
        }

    }


    private void LimpiarTableventa(){
        modeloVenta= (DefaultTableModel) TableVenta.getModel();
        int filas = TableVenta.getRowCount();
        for(int i = 0; i < filas; i++){
            modeloVenta.removeRow(0);
        }

    }


    /*MÉTODO LIMPIAR CLIENTE-VENTA*/

    private void pdf(){
        try{
            FileOutputStream archivo;
            File file = new File("src/pdf/venta.pdf");
            archivo = new FileOutputStream(file);

            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);

            doc.open();

            Image img = Image.getInstance("src/Img/logo_pdf.png");

            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN,12,Font.BOLD, BaseColor.BLUE);
            fecha.add(Chunk.NEWLINE);
            Date date = new Date();
            fecha.add( "Factura: 1"+"\nfecha: " + new SimpleDateFormat("dd-MM-yyyy").format(date) + "\n\n" );

            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);
            float[] ColumnaEncabezado = new float[]{20f, 30f, 70f, 40f};

            Encabezado.setWidths(ColumnaEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);

            Encabezado.addCell(img);
            String ruc = "123456789";
            String nom = "Super Compu Mundo Hiper-Mega RED";
            String telf = "0962151117";
            String direc = "Quito-Ecuador";
            String razon = "Super Compu Mundo Hiper-Mega RED";

            Encabezado.addCell("");
            Encabezado.addCell("Ruc: " + ruc + "\nNombre: " + nom + "\nTeléfono: " + telf +
                                    "\nDirección: " + direc + "\nRazón Social: " + razon );

            Encabezado.addCell(fecha);

            doc.add(Encabezado);
            doc.close();
            archivo.close();
        }catch (IOException | SecurityException | DocumentException e){

        }
    }

}  //FIN DE LA CLASE SISTEMA
