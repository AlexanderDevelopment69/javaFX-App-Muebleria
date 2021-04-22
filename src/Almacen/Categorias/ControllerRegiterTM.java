package Almacen.Categorias;

import Almacen.Fabricacion.ModelTableAlmacen;
import ConnectionMySQL.ConnectionMYSQL;
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

public class ControllerRegiterTM implements Initializable {
    @FXML
    private JFXTextField browser;


    @FXML
    private StackPane n;

    @FXML
    private VBox validPane, invalidPane;
    @FXML
    public static JFXDialog validDialog, invalidDialog;

    @FXML
    private JFXTextField categoria;
    @FXML
    private JFXTextField descripcion;

    @FXML
    private TableView<ModelTableCategoria> TableCategoria;

    @FXML
    private TableColumn<?, ?> tbId;

    @FXML
    private TableColumn<?, ?> tbCategoria;
    @FXML
    private TableColumn<?, ?> tbDescripcion;

    @FXML
    private JFXTextField id;


    private ObservableList<ModelTableCategoria> IngresarTableCategoria;
    PreparedStatement pst;

//    @FXML
//    void agregartipo(MouseEvent event) {
//
//        String Combo;
//
//        Combo= String.valueOf(cmTipo.getValue());
//        System.out.println(Combo);
//
//
//        ModelTableAlmacen p=new ModelTableAlmacen(Combo);
//        this.IngresarTableAlmacen.add(p);
//        this.TableAlmacen.setItems(IngresarTableAlmacen);
//
//        try {
//            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
//            Connection connection = ConnectionClass.getConnection();
//            pst = connection.prepareStatement("insert into categoria (catNombre) values (?)");
//            pst.setString(1,Combo);
//            pst.executeUpdate();
//
//        } catch(SQLException ioe) {
//            ioe.printStackTrace();
//        }
//
//    }


    @FXML
    void agregartipo(MouseEvent event) throws SQLException {

        String Categoria,Descripcion;

        Categoria = categoria.getText();
        Descripcion=descripcion.getText();

        ModelTableCategoria p = new ModelTableCategoria(Categoria,Descripcion);
        this.IngresarTableCategoria.add(p);
        this.TableCategoria.setItems(IngresarTableCategoria);

        if(categoria.getText().isBlank()){
            MostrarInValidAltertBlank();

        }

        else{
        //Validacion de datos de la base de datos//
            try {

                ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
                Connection connection = ConnectionClass.getConnection();
                pst= connection.prepareStatement("select *from categoria where catNombre=?");
                pst.setString(1,Categoria);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                //Alert que verifica si ya se registro la categoria
                    MostrarInValidAltert();

                }
                else{
                    //Ingreso de datos a la base de datos
                    try {
                        pst = connection.prepareStatement("insert into categoria (catNombre,descripcion) values (?,?)");
                        pst.setString(1, Categoria);
                        pst.setString(2, Descripcion);
                        pst.executeUpdate();

                        //Alert que verifica si ya se registro la categoria
                        MostrarValidAltert();

                        categoria.setText("");
                        descripcion.setText("");

                    } catch (SQLException ioe) {
                        ioe.printStackTrace();
                    }



                }

            } catch (Exception ex) {
                System.out.println("" + ex);
            }


        }


        MostrarCategoriaEnTabla();




    }
    @FXML
    void Eliminar() throws SQLException {

        ModelTableCategoria p=TableCategoria.getSelectionModel().getSelectedItem();
        if(p==null){

            JOptionPane.showMessageDialog(null,"Escoga algo");
        }
        else {
            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst = connection.prepareStatement("delete from categoria where catId=?");
            pst.setInt(1, p.getId());
            pst.executeUpdate();

            MostrarValidAlertDeleted();
            MostrarCategoriaEnTabla();
            this.TableCategoria.refresh();
        }
    }


    @FXML
    void Actualizar(MouseEvent event) throws SQLException {
        String Categoria, Descripcion;

        Categoria = categoria.getText();
        Descripcion = descripcion.getText();

        //Selecionar items de la tabla
        ModelTableCategoria p = TableCategoria.getSelectionModel().getSelectedItem();
        if (p == null) {
       categoria.setPromptText("Selecciona algo ");
       descripcion.setPromptText("Selecciona algo ");
       categoria.setStyle("-fx-prompt-text-fill:#01c192");
       descripcion.setStyle("-fx-prompt-text-fill:#01c192");



        } else {
            categoria.setStyle("-fx-prompt-text-fill:black");
            descripcion.setStyle("-fx-prompt-text-fill:black");
            categoria.setStyle("-fx-opacity: 0.80");
            descripcion.setStyle("-fx-opacity: 0.80");
            //Conexion a la base de datos;
            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst = connection.prepareStatement("update categoria set catNombre=? ,descripcion=? where catId=?");
            pst.setString(1, Categoria);
            pst.setString(2, Descripcion);
            pst.setInt(3, p.getId());
            pst.executeUpdate();
            MostrarValidAlertUpdate();
            categoria.setText("");
            descripcion.setText("");
            MostrarCategoriaEnTabla();


            this.TableCategoria.refresh();
        }
    }


    //Muestra los items seleccionados en las tablas
    @FXML
        public  void Seleccionar(MouseEvent event) {
        ModelTableCategoria p=TableCategoria.getSelectionModel().getSelectedItem();

          if (p == null) {

          }

else {
            categoria.setText(p.getCategoria());
            descripcion.setText(p.getDescripcion());
        }


    }







    @FXML
    void Buscador(KeyEvent event) {
        String Buscar=this.browser.getText();
        CargarDatosBuscador(Buscar);
    }
    public void CargarDatosBuscador(String valor) {
        try {

            ModelTableCategoria a=new ModelTableCategoria();
            ObservableList<ModelTableCategoria> items=a.getBuscadors(valor);
            this.TableCategoria.setItems(items);



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

public void  MostrarCategoriaEnTabla(){
    try {

        ModelTableCategoria s=new ModelTableCategoria();
        ObservableList<ModelTableCategoria> items=s.getModelCategoria();
        this.TableCategoria.setItems(items);



    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

}
    public void  MostrarValidAltert(){
        //Alert que verifica si ya se registro la categoria
        try {
            validPane = FXMLLoader.load(getClass().getResource("/Almacen/Categorias/AlertRegisterCategorias/ValidAlert/ValidAlert.fxml"));

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        validDialog = new JFXDialog(n, validPane, JFXDialog.DialogTransition.LEFT);

        validDialog.show();

    }

    public void  MostrarInValidAltert(){
        //Alert que verifica si ya se registro la categoria
        try {
            invalidPane = FXMLLoader.load(getClass().getResource("/Almacen/Categorias/AlertRegisterCategorias/InvalidAlert/InvalidAlert.fxml"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        invalidDialog = new JFXDialog(n, invalidPane, JFXDialog.DialogTransition.LEFT);
        invalidDialog.show();

    }
    public void  MostrarInValidAltertBlank(){
        //Alert que verifica si ya se registro la categoria
        try {
            invalidPane = FXMLLoader.load(getClass().getResource("/Almacen/Categorias/AlertRegisterCategorias/InvalidAlertBlank/InvalidBlank.fxml"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        invalidDialog = new JFXDialog(n, invalidPane, JFXDialog.DialogTransition.LEFT);
        invalidDialog.show();

    }
    public void  MostrarValidAlertDeleted(){
        //Alert que verifica si ya se registro la categoria
        try {
            validPane = FXMLLoader.load(getClass().getResource("/Almacen/Categorias/AlertRegisterCategorias/ValidAlertDeleted/ValidAlertDeleted.fxml"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        validDialog = new JFXDialog(n, validPane, JFXDialog.DialogTransition.LEFT);

        validDialog.show();

    }

    public void  MostrarValidAlertUpdate(){
        //Alert que verifica si ya se registro la categoria
        try {
            validPane = FXMLLoader.load(getClass().getResource("/Almacen/Categorias/AlertRegisterCategorias/ValidAlertUpdate/ValidAlertUpdate.fxml"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        validDialog = new JFXDialog(n, validPane, JFXDialog.DialogTransition.LEFT);

        validDialog.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        IngresarTableCategoria = FXCollections.observableArrayList();
        tbId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tbCategoria.setCellValueFactory(new PropertyValueFactory<>("Categoria"));
        tbDescripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
        MostrarCategoriaEnTabla();



    }
}
