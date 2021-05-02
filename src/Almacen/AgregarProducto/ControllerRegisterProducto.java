package Almacen.AgregarProducto;

import Almacen.AgregarProducto.CMCategoria.ModelCategoria;
import ConnectionMySQL.ConnectionMYSQL;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.adapter.JavaBeanObjectProperty;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerRegisterProducto implements Initializable {


    @FXML
    private StackPane n;

    @FXML
    private VBox validPane, invalidPane;
    @FXML
    public static JFXDialog validDialog, invalidDialog;

    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextField producto;

    @FXML
    private JFXTextField marca;

    @FXML
    private JFXComboBox<ModelCategoria> cmTipo;


    @FXML
    private JFXTextField modelo;

    @FXML
    private JFXTextField plazas;

    @FXML
    private JFXTextField precio;

    @FXML
    private JFXTextField costo;

    @FXML
    private JFXTextField browser;

    @FXML
    private TableView<ModelTableProducto> TableProducto;

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
    private TableColumn<?, ?> tbCosto;
    @FXML
    private TableColumn<?, ?> tbPrecio;

    PreparedStatement pst;
    private ObservableList<ModelTableProducto> IngresarTableProducto;



    @FXML
    void Agregar(MouseEvent event) {


        String Id, Producto, Marca;
        String Tipo;
        String Modelo, Plazas;
        double Costo;
        double Precio;


        Id = id.getText();
        Producto = producto.getText();
        Marca = marca.getText();
        Tipo = String.valueOf(cmTipo.getValue());
        Modelo = modelo.getText();
        Plazas = plazas.getText();
        //Validador de texfield
        String precioPrueba=precio.getText();
        String costoPrueba=costo.getText();
//        Precio = Double.parseDouble(prueba);


        if (id.getText().isBlank()||marca.getText().isBlank()||Tipo.isBlank()||modelo.getText().isBlank()||plazas.getText().isBlank()||precioPrueba.isBlank()||costoPrueba.isBlank()) {
            MostrarInValidAltertBlank();
        }
        else{


            try {

                ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
                Connection connection = ConnectionClass.getConnection();
                pst= connection.prepareStatement("select *from productos where producto=? or codProducto=?");
                pst.setString(1,Producto);
                pst.setString(2,Id);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    //Alert que verifica si ya se registro la categoria
                    MostrarInValidAltert();
                    connection.close();

                }
                else {


                    try {
                        Precio = Double.parseDouble(precioPrueba);
                        Costo = Double.parseDouble(costoPrueba);
                        ModelTableProducto p = new ModelTableProducto(Id, Producto, Marca, Tipo, Modelo, Plazas,Costo, Precio);
                        this.IngresarTableProducto.add(p);
                        this.TableProducto.setItems(IngresarTableProducto);

                        pst = connection.prepareStatement("insert into productos (codProducto,producto,marca,codTipo,modelo,plazas,costo,precio,stock) values (?,?,?,?,?,?,?,?,?)");
                        pst.setString(1, Id);
                        pst.setString(2, Producto);
                        pst.setString(3, Marca);
                        pst.setString(4, Tipo);
                        pst.setString(5, Modelo);
                        pst.setString(6, Plazas);
                        pst.setDouble(7, Costo);
                        pst.setDouble(8, Precio);
                        pst.setDouble(9, 0);
                        pst.executeUpdate();
                        connection.close();
                        MostrarValidAltert();
                        Nuevo();
                    } catch (SQLException ioe) {
                        ioe.printStackTrace();
                    }

                }
            } catch (Exception ex) {
                System.out.println("" + ex);
            }


        }
            MostrarProductosEnTabla();

    }

    @FXML
    void Buscador(KeyEvent event) {
        String Buscar=this.browser.getText();
        CargarDatosBuscador(Buscar);
    }

    @FXML
    void Actualizar(MouseEvent event) throws SQLException {
        double Costo;
        double Precio;
        Precio = Double.parseDouble(precio.getText());
        Costo = Double.parseDouble(costo.getText());


        //Selecionar items de la tabla
        ModelTableProducto p = TableProducto.getSelectionModel().getSelectedItem();
        if (p == null) {
            JOptionPane.showMessageDialog(null,"Selecciona algo");

        } else {
//
            //Conexion a la base de datos;


                ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
                Connection connection = ConnectionClass.getConnection();
                pst = connection.prepareStatement("update productos set costo=? ,precio=? where codProducto=?");
                pst.setString(1, String.valueOf(Costo));
                pst.setString(2, String.valueOf(Precio));
                pst.setString(3, p.getId());
                pst.executeUpdate();
                connection.close();
                JOptionPane.showMessageDialog(null,"Actualizado");

                MostrarProductosEnTabla();


                this.TableProducto.refresh();
        }
    }


    //Muestra los items seleccionados en las tablas
    @FXML
    public  void Seleccionar(MouseEvent event) {
        ModelTableProducto p=TableProducto.getSelectionModel().getSelectedItem();

        if(p==null){


        }
        else {

            String hola;
            id.setText(p.getId());
            producto.setText(p.getProducto());
            marca.setText(p.getMarca());
//            ModelCategoria s=new ModelCategoria();
//            ObservableList<ModelCategoria> items=s.getCategoria();
//            this.cmTipo.setItems(items);

//
//            cmTipo.getItems().add("3");




            modelo.setText(p.getModelo());
            plazas.setText(p.getPlazas());
            costo.setText(String.valueOf(p.getCosto()));
            precio.setText(String.valueOf(p.getPrecio()));



        }
    }




    @FXML
    void Eliminar(MouseEvent event)  {
        ModelTableProducto p = TableProducto.getSelectionModel().getSelectedItem();

        if(p==null) {

        }else {

            try {

                ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
                Connection connection = ConnectionClass.getConnection();
                pst = connection.prepareStatement("delete from productos where codProducto=?");
                pst.setString(1, p.getId());
                pst.executeUpdate();
                connection.close();
                MostrarValirAlertDeleted();
                MostrarProductosEnTabla();
                Nuevo();
                this.TableProducto.refresh();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Producto esta siendo usado en ventas o compras, no puede eliminarlo");

            }
        }

    }


    void Nuevo() {
        id.setText("");
        producto.setText("");
        marca.setText("");
        cmTipo.setValue(null);
        modelo.setText("");
        plazas.setText("");
        costo.setText("");
        precio.setText("");
        id.requestFocus();
    }

    public void MostrarProductosEnTabla(){
        try {

            ModelTableProducto s=new ModelTableProducto();
            ObservableList<ModelTableProducto> items=s.getProductos();
            this.TableProducto.setItems(items);



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public void initComboboxCategoria(){

        ModelCategoria s=new ModelCategoria();

        ObservableList<ModelCategoria> items=s.getCategoria();
        this.cmTipo.setItems(items);

    }

    public void CargarDatosBuscador(String valor) {
        try {

            ModelTableProducto a=new ModelTableProducto();
            ObservableList<ModelTableProducto> items=a.getBuscadors(valor);
            this.TableProducto.setItems(items);



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public void  MostrarValidAltert(){
        //Alert que verifica si ya se registro la categoria
        try {
            validPane = FXMLLoader.load(getClass().getResource("/Almacen/AgregarProducto/AlertRegisterProducto/ValidAlert/ValidAlert.fxml"));

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        validDialog = new JFXDialog(n, validPane, JFXDialog.DialogTransition.LEFT);

        validDialog.show();

    }

    public void  MostrarInValidAltert(){
        //Alert que verifica si ya se registro la categoria
        try {
            invalidPane = FXMLLoader.load(getClass().getResource("/Almacen/AgregarProducto/AlertRegisterProducto/InvalidAlert/InvalidAlert.fxml"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        invalidDialog = new JFXDialog(n, invalidPane, JFXDialog.DialogTransition.LEFT);
        invalidDialog.show();

    }
    public void  MostrarInValidAltertBlank(){
        //Alert que verifica si ya se registro la categoria
        try {
            invalidPane = FXMLLoader.load(getClass().getResource("/Almacen/AgregarProducto/AlertRegisterProducto/InvalidAlertBlank/InvalidBlank.fxml"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        invalidDialog = new JFXDialog(n, invalidPane, JFXDialog.DialogTransition.LEFT);
        invalidDialog.show();

    }
    public void  MostrarValirAlertDeleted(){
        //Alert que verifica si ya se registro la categoria
        try {
            validPane = FXMLLoader.load(getClass().getResource("/Almacen/AgregarProducto/AlertRegisterProducto/ValidAlertDeleted/ValidAlertDeleted.fxml"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        validDialog = new JFXDialog(n, validPane, JFXDialog.DialogTransition.LEFT);

        validDialog.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        IngresarTableProducto = FXCollections.observableArrayList();


//        //BuscarModelTableInventario= FXCollections.observableArrayList();
        tbId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tbProducto.setCellValueFactory(new PropertyValueFactory<>("Producto"));
        tbMarca.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        tbTipo.setCellValueFactory(new PropertyValueFactory<>("Tipo"));
        tbModelo.setCellValueFactory(new PropertyValueFactory<>("Modelo"));
        tbPlazas.setCellValueFactory(new PropertyValueFactory<>("Plazas"));
        tbCosto.setCellValueFactory(new PropertyValueFactory<>("Costo"));
        tbPrecio.setCellValueFactory(new PropertyValueFactory<>("Precio"));

        MostrarProductosEnTabla();
        initComboboxCategoria();




    }
}
