package Almacen.Fabricacion;


import Almacen.AgregarProducto.ModelTableProducto;
import Almacen.Categorias.ModelTableCategoria;
import Almacen.Fabricacion.CMProducto.ModelProducto;
import ConnectionMySQL.ConnectionMYSQL;

import Almacen.Fabricacion.CMProveedor.ModelProveedor;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;



import javax.swing.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;

public class ControllerRegistrarProducto implements Initializable {


    @FXML
    private JFXTextField browser;


    @FXML
    private JFXTextField marca;
    @FXML
    private JFXTextField tipo;

    @FXML
    private JFXComboBox<ModelProducto> cmNombreProducto;

    @FXML
    private JFXComboBox<ModelProveedor> cmProveedor;

    @FXML
    private JFXTextField modelo;

    @FXML
    private JFXTextField plazas;

    @FXML
    private JFXTextField cantidad;

    @FXML
    private JFXTextField costo;

    @FXML
    private JFXTextField codProducto;
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
    private TableColumn<?, ?> tbCosto;

    @FXML
    private TableColumn<?, ?> tbProveedor;

    @FXML
    private TableColumn<?, ?> tbEstado;

    @FXML
    private TableColumn<?, ?> tbFecha;


    @FXML
    private TableView<ModelTableAlmacen> TableTotal;

    @FXML
    private TableColumn<?, ?> tbId2;

    @FXML
    private TableColumn<?, ?> tbProducto2;

    @FXML
    private TableColumn<?, ?> tbCantidad2;

    @FXML
    private TableColumn<?, ?> tbTotal;

    @FXML
    private TableColumn<?, ?> tbProveedor2;

    @FXML
    private TableColumn<?, ?> tbFecha2;

    PreparedStatement pst;

    private ObservableList<ModelTableAlmacen> IngresarTableAlmacen;


    @FXML
    void Agregar(MouseEvent event) {

        String Producto;
        int Cantidad;
        String  Proveedor, Estado;



        Producto = String.valueOf(cmNombreProducto.getValue());
        String prueba=cantidad.getText();
        Proveedor = String.valueOf(cmProveedor.getValue());
        Estado = estado.getText();



        if(Producto.isBlank()||prueba.isBlank()||Proveedor.isBlank()||estado.getText().isBlank() ) {
            JOptionPane.showMessageDialog(null, "Completar espacios");
        }

        else {
            try {
                Cantidad = Integer.parseInt(prueba);
                ModelTableAlmacen p = new ModelTableAlmacen(Producto, Cantidad, Proveedor, Estado);
                this.IngresarTableAlmacen.add(p);
                this.TableAlmacen.setItems(IngresarTableAlmacen);

                ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
                Connection connection = ConnectionClass.getConnection();
                pst = connection.prepareStatement("insert into almacen(codProducto,cantidad,codProveedor,estado) values (?,?,?,?)");
                pst.setString(1, Producto);
                pst.setInt(2, Cantidad);
                pst.setString(3, Proveedor);
                pst.setString(4, Estado);
                pst.executeUpdate();
                Nuevo();
                connection.close();


            } catch (SQLException ioe) {
                ioe.printStackTrace();
            }

//Cantidad de stock de productos
            try {

                Cantidad = Integer.parseInt(prueba);
                ModelTableAlmacen p = new ModelTableAlmacen(Producto, Cantidad, Proveedor, Estado);


//                this.IngresarTableAlmacen.add(p);
//                this.TableAlmacen.setItems(IngresarTableAlmacen);

                ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
                Connection connection = ConnectionClass.getConnection();
                pst = connection.prepareStatement("update productos set stock=? + stock where producto=?");
                pst.setInt(1,Cantidad);
                pst.setString(2, p.getProducto());
                pst.executeUpdate();
                connection.close();
//                Nuevo();


            } catch (SQLException ioe) {
                ioe.printStackTrace();
            }




        }



            MostrarProductoEnTabla();
            MostrarProductoEnTablaTotal();

    }





    @FXML
    void Buscador(KeyEvent event) throws ClassNotFoundException {


        String Buscar=this.browser.getText();
        CargarDatosBuscador(Buscar);

//        CompletarInfo(Buscar);
        }

    void Nuevo(){

        codProducto.setText("");
        cmNombreProducto.setValue(null);
        marca.setText("");
        modelo.setText("");
        tipo.setText("");
        plazas.setText("");
        cantidad.setText("");
        costo.setText("");
        cmProveedor.setValue(null);
        estado.setText("");

}



    @FXML
    void Eliminar(MouseEvent event) throws SQLException {

        ModelTableAlmacen p = TableAlmacen.getSelectionModel().getSelectedItem();
        if(p==null){



        }else {
            try {

                ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
                Connection connection = ConnectionClass.getConnection();
                pst = connection.prepareStatement("delete from almacen where idAlmacen=?");
                pst.setInt(1, p.getId());
                pst.executeUpdate();
                connection.close();
                JOptionPane.showMessageDialog(null, "Eliminado");


                MostrarProductoEnTabla();
                MostrarProductoEnTablaTotal();
                this.TableAlmacen.refresh();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }


    public void initComboboxProveedor(){

        ModelProveedor s=new ModelProveedor();
        ObservableList<ModelProveedor> items=s.getProveedor();
        this.cmProveedor.setItems(items);

    }
    public void initComboboxProducto(){

        ModelProducto s=new ModelProducto();
        ObservableList<ModelProducto> items=s.getCMProducto();
        this.cmNombreProducto.setItems(items);

    }
    //Carga y Muestra un resumen de la tabla1
    public void MostrarProductoEnTabla(){
        try {

            ModelTableAlmacen s=new ModelTableAlmacen();
            ObservableList<ModelTableAlmacen> items=s.getAlmacen();
            this.TableAlmacen.setItems(items);



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    //Carga y Muestra un resumen de la tabla2
    public void MostrarProductoEnTablaTotal(){
        try {

            ModelTableAlmacen a=new ModelTableAlmacen();
            ObservableList<ModelTableAlmacen> items=a.getTotalAlmacen();
            this.TableTotal.setItems(items);



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

        try {

            ModelTableAlmacen a=new ModelTableAlmacen();
            ObservableList<ModelTableAlmacen> items=a.getBuscador2(valor);
            this.TableTotal.setItems(items);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }




    @FXML
    public void consultaCMProducto(ActionEvent event) {

        String Producto;
        Producto = String.valueOf(cmNombreProducto.getSelectionModel().getSelectedItem());
        if(Producto.isBlank()){
            codProducto.setText("");
            marca.setText("");
            modelo.setText("");
            tipo.setText("");
            plazas.setText("");
            costo.setText("");
        }
        else{
            CompletarInfo(Producto);

        }

    }

    public void CompletarInfo(String valor){

        try {

            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst = connection.prepareStatement("select productos.codProducto,productos.marca,productos.costo,categoria.catNombre,productos.modelo,productos.plazas from productos inner join categoria on categoria.catNombre=productos.codTipo  where producto like '%"+valor+"%'");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                codProducto.setText(String.valueOf(rs.getString("codProducto")));
                marca.setText(rs.getString("marca"));
                costo.setText(String.valueOf(rs.getDouble("costo")));
                tipo.setText(rs.getString("catNombre"));
                modelo.setText(rs.getString("modelo"));
                plazas.setText(rs.getString("plazas"));

            }
            connection.close();
            codProducto.setStyle("-fx-text-fill:#644EFF");
            marca.setStyle("-fx-text-fill:#644EFF ");
            costo.setStyle("-fx-text-fill:#644EFF ");
            tipo.setStyle("-fx-text-fill:#644EFF ");
            modelo.setStyle("-fx-text-fill:#644EFF ");
            plazas.setStyle("-fx-text-fill:#644EFF ");

        } catch (Exception ex) {
            System.out.println("" + ex);
        }


}





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        IngresarTableAlmacen = FXCollections.observableArrayList();

        //tabla1 de almacen
        tbId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tbProducto.setCellValueFactory(new PropertyValueFactory<>("Producto"));
        tbMarca.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        tbTipo.setCellValueFactory(new PropertyValueFactory<>("Tipo"));
        tbModelo.setCellValueFactory(new PropertyValueFactory<>("Modelo"));
        tbPlazas.setCellValueFactory(new PropertyValueFactory<>("Plazas"));
        tbCantidad.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
        tbCosto.setCellValueFactory(new PropertyValueFactory<>("Costo"));
        tbProveedor.setCellValueFactory(new PropertyValueFactory<>("Proveedor"));
        tbEstado.setCellValueFactory(new PropertyValueFactory<>("Estado"));
        tbFecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));

//Tabla2 de almacen
        tbId2.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tbProducto2.setCellValueFactory(new PropertyValueFactory<>("Producto"));
        tbCantidad2.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
        tbTotal.setCellValueFactory(new PropertyValueFactory<>("Total"));
        tbFecha2.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
        tbProveedor2.setCellValueFactory(new PropertyValueFactory<>("Proveedor"));


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
        MostrarProductoEnTablaTotal();
        /*    Devoler valores de la base de datos al combobox   */
//        initComboboxCategoria();
        initComboboxProveedor();
        initComboboxProducto();
//        ObservableList<String> obs = FXCollections.observableArrayList("java","hola");
//        cmTipo.setItems(obs);



    }




}
