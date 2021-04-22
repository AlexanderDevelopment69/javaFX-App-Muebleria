package Ventas.NuevaVenta;



import Almacen.Categorias.ModelTableCategoria;
import Almacen.Fabricacion.CMProducto.ModelProducto;

import ConnectionMySQL.ConnectionMYSQL;
import Proveedores.ModelTableProveedor;
import Ventas.NuevaVenta.CMVendedor.ModelVendedor;
import Ventas.NuevaVenta.ReleasedCliente.ModelCliente;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import com.mysql.cj.xdevapi.Table;
import com.sun.javafx.scene.control.TableColumnSortTypeWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Formattable;
import java.util.ResourceBundle;

public class ControllerRegisterVenta implements Initializable {

    @FXML
    private StackPane n;

    @FXML
    private VBox validPane, invalidPane;
    @FXML
    public static JFXDialog validDialog, invalidDialog;

    @FXML
    private JFXTextField idVenta;
    @FXML
    private JFXComboBox<Almacen.Fabricacion.CMProducto.ModelProducto> cmNombreProducto;

    @FXML
    private JFXTextField cantidad;

    @FXML
    private JFXComboBox<ModelVendedor> cmVendedor;

    @FXML
    private JFXTextField descuento;

    @FXML
    private JFXTextField codProducto;

    @FXML
    private JFXTextField cliente;
    @FXML
    private JFXTextField marca;

    @FXML
    private JFXTextField precio;

    @FXML
    private JFXTextField tipo;

    @FXML
    private JFXTextField modelo;

    @FXML
    private JFXTextField plazas;

    @FXML
    private TableView<ModelTableVentas> TableVentas;

    @FXML
    private TableColumn<?, ?> tbId;

    @FXML
    private TableColumn<?, ?> tbProducto;

    @FXML
    private TableColumn<?, ?> tbMarca;

    @FXML
    private TableColumn<?, ?> tbModelo;

    @FXML
    private TableColumn<?, ?> tbPlazas;

    @FXML
    private TableColumn<?, ?> tbCantidad;

    @FXML
    private TableColumn<?, ?> tbPrecio;

    @FXML
    private TableColumn<?, ?> tbDescuento;

    @FXML
    private TableColumn<?, ?> tbImporte;

    @FXML
    private TableColumn<?, ?> tbFecha;

    @FXML
    private TableView<ModelTableVentas> TableTotal;


    @FXML
    private TableColumn<?, ?> tbCodigo2;

    @FXML
    private TableColumn<?, ?> tbCliente2;

    @FXML
    private TableColumn<?, ?> tbVendedor2;

    @FXML
    private TableColumn<?, ?> tbDescuento2;

    @FXML
    private TableColumn<?, ?> tbTotal2;

    @FXML
    private TableColumn<?, ?> tbFecha2;



    @FXML
    private JFXTextField infTotal;

    @FXML
    private JFXTextField infDscuento;

    @FXML
    private JFXTextField infCantidad;

    @FXML
    private JFXTextField browser;
    @FXML
    private JFXTextField ReleasedNombre;

   PreparedStatement pst;
    private ObservableList<ModelTableVentas> IngresarTableVenta;

    @FXML
    void Agregar(MouseEvent event) throws SQLException {
//        String Cliente;
        String Codigo;
        String Producto;

        int Cantidad;

//        String Vendedor;
        double Precio;
        double Descuento;
        double Importe=0;

         String Fechas;
         Fechas= Fecha().toString();

//        Cliente = cliente.getText();


//        tbFecha.(String.valueOf(Fecha()));

        Codigo=codProducto.getText();
        Producto= String.valueOf(cmNombreProducto.getValue());

//        Vendedor=String.valueOf(cmVendedor.getValue());


//Solicita datos de la tabla producto y los muestra en la tabla ventas;
        String PrecioPrueba=precio.getText();
        String Marca=marca.getText();
        String Modelo=modelo.getText();
        String Plazas=plazas.getText();

        String CantidadPrueba=cantidad.getText();
        String DescuentoPrueba=descuento.getText();

//        Importe=(Cantidad*Precio)-Descuento;

        if(Producto.isBlank()||CantidadPrueba.isBlank()||DescuentoPrueba.isBlank()||Codigo.isBlank()||Marca.isBlank()||Modelo.isBlank()||Plazas.isBlank()||PrecioPrueba.isBlank()){
        JOptionPane.showMessageDialog(null,"Completa");

        }
        else {
            Cantidad = Integer.parseInt(CantidadPrueba);
            Descuento = Double.parseDouble(DescuentoPrueba);
            Precio= Double.parseDouble(PrecioPrueba);
            ModelTableVentas p = new ModelTableVentas(Codigo, Producto, Marca, Modelo, Plazas, Cantidad, Precio, Descuento, Importe, Fechas);
            this.IngresarTableVenta.add(p);
            this.TableVentas.setItems(IngresarTableVenta);
            System.out.println(TableVentas.getItems().size());

//        for (int i = 0; i < TableVentas.getItems().size(); i++) {
//
//            System.out.println(""+TableVentas.getItems().get(i));
//        }
//        for (int i = 0; i < TableVentas.getItems().size(); i++) {
//            for (TableColumn column : TableVentas.getVisibleLeafColumns())
//                System.out.println(" "+column.getCellData(i));
//
//        }
            TotalImporte();
            TotalProductos();
            TotalDescuento();

        }

    }

    public double TotalImporte(){
        double Total;
        Total = TableVentas.getItems().stream().mapToDouble(ModelTableVentas::getImporte).sum();
        infTotal.setText(String.valueOf(Total));

        return Total;
    }
    public void TotalProductos(){
        int Total;
        Total = (int) TableVentas.getItems().stream().mapToDouble(ModelTableVentas::getCantidad).sum();
        infCantidad.setText(String.valueOf(Total));

    }
    public double TotalDescuento(){
        double Total;
        Total = TableVentas.getItems().stream().mapToDouble(ModelTableVentas::getDescuento).sum();
        infDscuento.setText(String.valueOf(Total));
        return Total;
    }







    @FXML
    void Buscador(KeyEvent event) {

    }

    @FXML
    void Cancelar(MouseEvent event) {
        TableVentas.getItems().clear();
    }

    @FXML
    void Eliminar(MouseEvent event) {

    }



    @FXML
    void GenerarVenta(MouseEvent event) {

        String Cliente;
        String Vendedor;
        double Total;
        double DescuentoTotal;

        Cliente = cliente.getText();
        Vendedor=String.valueOf(cmVendedor.getValue());
        Total=TotalImporte();
        DescuentoTotal=TotalDescuento();


        if(cliente.getText().isBlank()||Vendedor.isBlank()) {
            JOptionPane.showMessageDialog(null,"completa");

        }else{


            try {
                ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
                Connection connection = ConnectionClass.getConnection();
                pst = connection.prepareStatement("insert into venta(idCliente,idVendedor,total,descuento) values (?,?,?,?)");
                pst.setString(1, Cliente);
                pst.setString(2, Vendedor);
                pst.setDouble(3, Total);
                pst.setDouble(4, DescuentoTotal);
                pst.executeUpdate();
                MostrarVentasTabla();
                MostrarValidAltert();
                Nuevo();
            } catch (SQLException ioe) {
                ioe.printStackTrace();
            }

            //Ingreso filas de la tabla hacia la base de datos
            try {
                int IdVenta;
                IdVenta = obtenerIdVenta()-1;

                ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
                Connection connection = ConnectionClass.getConnection();
                for (int i = 0; i < TableVentas.getItems().size(); i++) {
                    pst = connection.prepareStatement("insert into detalleVenta(idVenta,producto,cantidad,precioVenta,descuento,importe) values (?,?,?,?,?,?)");
                    pst.setInt(1,IdVenta);
                    pst.setString(2, TableVentas.getItems().get(i).getProducto());
                    pst.setInt(3, TableVentas.getItems().get(i).getCantidad());
                    pst.setDouble(4,TableVentas.getItems().get(i).getPrecio());
                    pst.setDouble(5, TableVentas.getItems().get(i).getDescuento());
                    pst.setDouble(6, TableVentas.getItems().get(i).getImporte());
                    pst.executeUpdate();
                    obtenerIdVenta();

//            MostrarValidAltert();
//            Nuevo()

                }
                TableVentas.getItems().clear();
            } catch (SQLException ioe) {
                ioe.printStackTrace();
            }




        }








    }


    public int obtenerIdVenta(){

        int Venta = 0;
        try {
            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst = connection.prepareStatement("select max(idVenta)+1 from venta");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               idVenta.setText(String.valueOf(rs.getInt(1)));
               Venta=rs.getInt(1);
            }


        } catch (SQLException ioe) {
            ioe.printStackTrace();
        }
        return Venta;

    }

    public void consultaCMProducto(ActionEvent event) {

        String Producto;
        Producto = String.valueOf(cmNombreProducto.getSelectionModel().getSelectedItem());
        if(Producto.isBlank()){
            codProducto.setText("");
            marca.setText("");
            modelo.setText("");
            tipo.setText("");
            plazas.setText("");
            precio.setText("");
        }
        else{
            CompletarInfo(Producto);


        }
    }


public void initComboboxProducto(){

    ModelProducto s=new ModelProducto();
    ObservableList<ModelProducto> items=s.getCMProducto();
    this.cmNombreProducto.setItems(items);

}


//Completa informacion en texfields provenientes del combobox
public void CompletarInfo(String valor){

        try {

            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst = connection.prepareStatement("select productos.codProducto,productos.marca,productos.precio,categoria.catNombre,productos.modelo,productos.plazas from productos inner join categoria on categoria.catNombre=productos.codTipo  where producto like '%"+valor+"%'");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                codProducto.setText(String.valueOf(rs.getString("codProducto")));
                marca.setText(rs.getString("marca"));
                precio.setText(String.valueOf(rs.getDouble("precio")));
                tipo.setText(rs.getString("catNombre"));
                modelo.setText(rs.getString("modelo"));
                plazas.setText(rs.getString("plazas"));

            }
            codProducto.setStyle("-fx-text-fill:#644EFF");
            marca.setStyle("-fx-text-fill:#644EFF ");
            precio.setStyle("-fx-text-fill:#644EFF ");
            tipo.setStyle("-fx-text-fill:#644EFF ");
            modelo.setStyle("-fx-text-fill:#644EFF ");
            plazas.setStyle("-fx-text-fill:#644EFF ");

        } catch (Exception ex) {
            System.out.println("" + ex);
        }


    }



    public void initComboboxVendedor(){

        ModelVendedor s=new ModelVendedor();
        ObservableList<ModelVendedor> items=s.getCMVendedor();
        this.cmVendedor.setItems(items);

    }

    @FXML
    void Vend(KeyEvent event) {
        String Buscar=this.cliente.getText();



        if(cliente.getText().isBlank()){
               ReleasedNombre.setText("");
        }
        else{
            CargarDatosBuscador(Buscar);

        }


//        CargarDatosBuscador(Buscar);


    }

    public void CargarDatosBuscador(String valor) {

        ModelCliente a=new ModelCliente();
        ObservableList<ModelCliente> items=a.getReleasedCliente(valor);
        this.ReleasedNombre.setText(String.valueOf(items).replace("[", "").replace("]", "").replace(",", ""));
        ReleasedNombre.setStyle("-fx-text-fill: #644EFF");
    }

        public Date Fecha() {
            // Crear una instancia de un objeto Date invocando su constructor
          Date  Fecha  = new Date();

            // Mostrar la fecha y la hora usando toString ()


            return Fecha;
        }

    public void MostrarVentasTabla(){
        try {

            ModelTableVentas s=new ModelTableVentas();
            ObservableList<ModelTableVentas> items=s.getVentasTable();
            this.TableTotal.setItems(items);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void Nuevo(){

        cliente.setText("");
        ReleasedNombre.setText("");
        cmNombreProducto.setValue(null);
        cantidad.setText("");
        descuento.setText("");
        cmVendedor.setValue(null);
        codProducto.setText("");
        marca.setText("");
        precio.setText("");
        tipo.setText("");
        modelo.setText("");
        plazas.setText("");
        infTotal.setText("");
        infDscuento.setText("");
        infCantidad.setText("");

    }

    public void  MostrarValidAltert(){
        //Alert que verifica si ya se registro la categoria
        try {
            validPane = FXMLLoader.load(getClass().getResource("/Ventas/NuevaVenta/AlertVenta/ValidAlert/ValidAlert.fxml"));

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        validDialog = new JFXDialog(n, validPane, JFXDialog.DialogTransition.LEFT);

        validDialog.show();

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        IngresarTableVenta= FXCollections.observableArrayList();

        //tabla1 de ventas
        tbId.setCellValueFactory(new PropertyValueFactory<>("Codigo"));
        tbProducto.setCellValueFactory(new PropertyValueFactory<>("Producto"));
        tbCantidad.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
        tbDescuento.setCellValueFactory(new PropertyValueFactory<>("Descuento"));
        tbImporte.setCellValueFactory(new PropertyValueFactory<>("Importe"));
        tbFecha.setCellValueFactory(new PropertyValueFactory<>("Fechas"));


        tbMarca.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        tbModelo.setCellValueFactory(new PropertyValueFactory<>("Modelo"));
        tbPlazas.setCellValueFactory(new PropertyValueFactory<>("Plazas"));
        tbPrecio.setCellValueFactory(new PropertyValueFactory<>("Precio"));


        //tabla2 de ventas
        tbCodigo2.setCellValueFactory(new PropertyValueFactory<>("Venta"));
        tbCliente2.setCellValueFactory(new PropertyValueFactory<>("IdCliente"));
        tbVendedor2.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        tbDescuento2.setCellValueFactory(new PropertyValueFactory<>("Descuento"));
        tbTotal2.setCellValueFactory(new PropertyValueFactory<>("Total"));
        tbFecha2.setCellValueFactory(new PropertyValueFactory<>("Fecha"));

        initComboboxProducto();
        initComboboxVendedor();
        MostrarVentasTabla();

//Obtine el Id de la ultima Venta
        obtenerIdVenta();
    }






}
