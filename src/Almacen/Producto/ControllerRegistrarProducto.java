package Almacen.Producto;

import Almacen.Producto.CMCategoria.ModelCategoria;
import Almacen.Producto.CMProveedor.ModelProveedor;
import ConnectionMySQL.ConnectionMYSQL;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
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


import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ControllerRegistrarProducto implements Initializable {


    @FXML
    private JFXTextField browser;

    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextField producto;

    @FXML
    private JFXTextField marca;
    @FXML
    private JFXTextField tipo;

    @FXML
    private JFXComboBox<ModelCategoria> cmTipo;

    @FXML
    private JFXComboBox<ModelProveedor> cmProveedor;

    @FXML
    private JFXTextField modelo;

    @FXML
    private JFXTextField plazas;

    @FXML
    private JFXTextField cantidad;

    @FXML
    private JFXTextField precio;

    @FXML
    private JFXTextField proveedor;

    @FXML
    private JFXTextField estado;

    @FXML
    private TableView<ModelTableAlmacen> TableAlmacen;

    @FXML
    private TableColumn<?, ?> tbId;

    @FXML
    private TableColumn<?, ?> tbProducto;

    @FXML
    private TableColumn<?, ?> tbMarca;

    @FXML
    private TableColumn<?, ?> tbTipo;

    @FXML
    private TableColumn<?, ?> tbModelo;

    @FXML
    private TableColumn<?, ?> tbPlazas;

    @FXML
    private TableColumn<?, ?> tbCantidad;

    @FXML
    private TableColumn<?, ?> tbPrecio;

    @FXML
    private TableColumn<?, ?> tbProveedor;

    @FXML
    private TableColumn<?, ?> tbEstado;

    @FXML
    private TableColumn<?, ?> tbFecha;

//    @FXML
//    private TableView<ModelTableAlmacen2> TableAlmacen2;

//    @FXML
//    private TableColumn<?, ?> tbId2;
//    @FXML
//    private TableColumn<?, ?> tbProducto2;
//
//    @FXML
//    private TableColumn<?, ?> tbMarca2;
//
//    @FXML
//    private TableColumn<?, ?> tbTipo2;
//
//    @FXML
//    private TableColumn<?, ?> tbModelo2;
//
//    @FXML
//    private TableColumn<?, ?> tbPlazas2;
//
//    @FXML
//    private TableColumn<?, ?> tbCaracteristicas2;
//
//    @FXML
//    private TableColumn<?, ?> tbPrecio2;
//
//    @FXML
//    private TableColumn<?, ?> tbPrecioAdicional2;
//
//    @FXML
//    private TableColumn<?, ?> tbEstado2;

PreparedStatement pst;

    private ObservableList<ModelTableAlmacen> IngresarTableAlmacen;


    @FXML
    void Agregar(MouseEvent event) {

        int Cantidad;
        String Id,Producto, Marca;
        String Tipo;
        String  Modelo, Plazas,Proveedor, Estado;
        Double Precio;

        Id=id.getText();
        Producto = producto.getText();
        Marca = marca.getText();
         Tipo= String.valueOf(cmTipo.getValue());
        Modelo = modelo.getText();
        Plazas = plazas.getText();
        Cantidad=Integer.parseInt(cantidad.getText());
        Precio = Double.parseDouble(precio.getText());
        Proveedor = String.valueOf(cmProveedor.getValue());
        Estado = estado.getText();
        ModelTableAlmacen p=new ModelTableAlmacen(Id,Producto,Marca,Tipo,Modelo,Plazas,Cantidad,Precio,Proveedor,Estado);
        this.IngresarTableAlmacen.add(p);
        this.TableAlmacen.setItems(IngresarTableAlmacen);

        try {
            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst = connection.prepareStatement("insert into producto (codProducto,producto,marca,codTipo,modelo,plazas,cantidad,precio,codProveedor,estado) values (?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1,Id);
            pst.setString(2, Producto);
            pst.setString(3, Marca);
            pst.setString(4, Tipo);
            pst.setString(5, Modelo);
            pst.setString(6, Plazas);
            pst.setString(7, String.valueOf(Cantidad));
            pst.setString(8, String.valueOf(Precio));
            pst.setString(9,Proveedor);
            pst.setString(10, Estado);
            pst.executeUpdate();

        } catch(SQLException ioe) {
            ioe.printStackTrace();
        }

        MostrarProductoEnTabla();

    }



    @FXML
    void Buscador(KeyEvent event) throws ClassNotFoundException {


        String Buscar=this.browser.getText();
        CargarDatosBuscador(Buscar);

        }





    @FXML
    void Eliminar(MouseEvent event) throws SQLException {
        ModelTableAlmacen p=TableAlmacen.getSelectionModel().getSelectedItem();
        ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
        Connection connection = ConnectionClass.getConnection();
        pst= connection.prepareStatement("delete from producto where codProducto=?");
        pst.setString(1,p.getId());
        pst.executeUpdate();
        JOptionPane.showMessageDialog(null,"Eliminado");


        MostrarProductoEnTabla();
        this.TableAlmacen.refresh();

    }

    @FXML
    void Nuevo(MouseEvent event) {
        id.setText(null);
        producto.setText(null);
        marca.setText(null);
        cmTipo.setValue(null);
        modelo.setText(null);
        plazas.setText(null);
        cantidad.setText(null);
        precio.setText(null);
        cmProveedor.setValue(null);
        estado.setText(null);
        id.requestFocus();

    }


    public void initComboboxCategoria(){

        ModelCategoria s=new ModelCategoria();
        ObservableList<ModelCategoria> items=s.getCategoria();
        this.cmTipo.setItems(items);

    }
    public void initComboboxProveedor(){

        ModelProveedor s=new ModelProveedor();
        ObservableList<ModelProveedor> items=s.getProveedor();
        this.cmProveedor.setItems(items);

    }


    public void MostrarProductoEnTabla(){
        try {

            ModelTableAlmacen s=new ModelTableAlmacen();
            ObservableList<ModelTableAlmacen> items=s.getAlmacen();
            this.TableAlmacen.setItems(items);



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void CargarDatosBuscador(String valor) {
        try {

            ModelTableAlmacen a=new ModelTableAlmacen();
            ObservableList<ModelTableAlmacen> items=a.getBuscadors(valor);
            this.TableAlmacen.setItems(items);



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        IngresarTableAlmacen = FXCollections.observableArrayList();


//        //BuscarModelTableInventario= FXCollections.observableArrayList();
        tbId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tbProducto.setCellValueFactory(new PropertyValueFactory<>("Producto"));
        tbMarca.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        tbTipo.setCellValueFactory(new PropertyValueFactory<>("Tipo"));
        tbModelo.setCellValueFactory(new PropertyValueFactory<>("Modelo"));
        tbPlazas.setCellValueFactory(new PropertyValueFactory<>("Plazas"));
        tbCantidad.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
        tbPrecio.setCellValueFactory(new PropertyValueFactory<>("Precio"));
        tbProveedor.setCellValueFactory(new PropertyValueFactory<>("Proveedor"));
        tbEstado.setCellValueFactory(new PropertyValueFactory<>("Estado"));
        tbFecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));




//        tbId2.setCellValueFactory(new PropertyValueFactory<>("Id"));
//        tbProducto2.setCellValueFactory(new PropertyValueFactory<>("Producto"));
//        tbMarca2.setCellValueFactory(new PropertyValueFactory<>("Marca"));
//        tbTipo2.setCellValueFactory(new PropertyValueFactory<>("Tipo"));
//        tbModelo2.setCellValueFactory(new PropertyValueFactory<>("Modelo"));
//        tbPlazas2.setCellValueFactory(new PropertyValueFactory<>("plazas"));
//        tbCaracteristicas2.setCellValueFactory(new PropertyValueFactory<>("Caracteristicas"));
//        tbEstado2.setCellValueFactory(new PropertyValueFactory<>("Estado"));
//        tbPrecio2.setCellValueFactory(new PropertyValueFactory<>("Precio"));
//        tbPrecioAdicional2.setCellValueFactory(new PropertyValueFactory<>("PrecioAdicional"));

//        try {
//
//            ModelTableAlmacen2 s=new ModelTableAlmacen2();
//            ObservableList<ModelTableAlmacen2> items=s.getAlmacen();
//            this.TableAlmacen2.setItems(items);
//
//
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        /*    Devoler valores de la base de datos al table    */
        MostrarProductoEnTabla();
        /*    Devoler valores de la base de datos al combobox   */
        initComboboxCategoria();
        initComboboxProveedor();

//        ObservableList<String> obs = FXCollections.observableArrayList("java","hola");
//        cmTipo.setItems(obs);

    }




}
