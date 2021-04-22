package Proveedores;

import Almacen.AgregarProducto.ModelTableProducto;
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


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ControllerRegisterProveedor implements Initializable {
    @FXML
    private JFXTextField browser;
    @FXML
    private StackPane n;

    @FXML
    private VBox validPane, invalidPane;
    @FXML
    public static JFXDialog validDialog, invalidDialog;
    @FXML
    private JFXTextField ruc;

    @FXML
    private JFXTextField empresa;
    @FXML
    private JFXTextField correo;
    @FXML
    private TableView<ModelTableProveedor> TableProveedor;

    @FXML
    private TableColumn<?, ?> tbRuc;

    @FXML
    private TableColumn<?, ?> tbEmpresa;

    @FXML
    private TableColumn<?, ?> tbDireccion;

    @FXML
    private TableColumn<?, ?> tbCelular;
    @FXML
    private TableColumn<?, ?> tbCorreo;
    @FXML
    private JFXTextField direccion;

    @FXML
    private JFXTextField celular;

    PreparedStatement pst;
    private ObservableList<ModelTableProveedor> IngresarTableProveedor;

    @FXML
    void agregarProveedor() {


        String Ruc;
        String Empresa;
        String Direccion;
        String Celular;
        String Correo;
        Ruc= ruc.getText();
        Empresa=empresa.getText();
        Direccion=direccion.getText();
        Celular=celular.getText();
        Correo=correo.getText();


        ModelTableProveedor p=new ModelTableProveedor(Ruc,Empresa,Direccion,Celular,Correo);
        this.IngresarTableProveedor.add(p);
        this.TableProveedor.setItems(IngresarTableProveedor);

        if(ruc.getText().isBlank()||empresa.getText().isBlank()||direccion.getText().isBlank()||celular.getText().isBlank()|| correo.getText().isBlank()){

                        MostrarInvalidAlertBlank();
        }
        else{


            //Validacion de datos de la base de datos//
            try {

                ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
                Connection connection = ConnectionClass.getConnection();
                pst= connection.prepareStatement("select *from proveedor where proRuc=? or proNombre=? ");
                pst.setString(1,Ruc);
                pst.setString(2,Empresa);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {

                 MostrarInvalidAlter();


                }
                else{
                    //Ingreso de datos a la base de datos
                    try {
                        pst = connection.prepareStatement("insert into proveedor values (?,?,?,?,?)");
                        pst.setString(1,Ruc);
                        pst.setString(2,Empresa);
                        pst.setString(3,Direccion);
                        pst.setString(4,Celular);
                        pst.setString(5,Correo);
                        pst.executeUpdate();
                        Nuevo();
                        //Alert que verifica si ya se registro la categoria
                        MostrarValidAltert();

                    } catch (SQLException ioe) {
                        ioe.printStackTrace();
                    }



                }

            } catch (Exception ex) {
                System.out.println("" + ex);
            }


    }


        MostrarProveedorEnEnTabla();



    }

    @FXML
    void Eliminar(MouseEvent event) throws SQLException {
        ModelTableProveedor p=TableProveedor.getSelectionModel().getSelectedItem();
        ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
        Connection connection = ConnectionClass.getConnection();
        pst= connection.prepareStatement("delete from proveedor where proRuc=?");
        pst.setString(1,p.getRuc());
        pst.executeUpdate();
        MostrarValirAlertDeleted();


        MostrarProveedorEnEnTabla();
        this.TableProveedor.refresh();
    }
    public void CargarDatosBuscador(String valor) {
        try {

            ModelTableProveedor a=new ModelTableProveedor();
            ObservableList<ModelTableProveedor> items=a.getBuscadors(valor);
            this.TableProveedor.setItems(items);



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    void Nuevo() {
        ruc.setText("");
        empresa.setText("");
        direccion.setText("");
        celular.setText("");
        correo.setText("");
        ruc.requestFocus();

    }
    @FXML
    void Buscador(KeyEvent event) {
        String Buscar=this.browser.getText();
        CargarDatosBuscador(Buscar);
    }
    public void  MostrarValidAltert(){
        //Alert que verifica si ya se registro el proveedor
        try {
            validPane = FXMLLoader.load(getClass().getResource("/Proveedores/AlertRegisterProveedor/ValidAlert/ValidAlert.fxml"));

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        validDialog = new JFXDialog(n, validPane, JFXDialog.DialogTransition.LEFT);

        validDialog.show();

    }
public void MostrarProveedorEnEnTabla(){
    try {

        ModelTableProveedor s=new ModelTableProveedor();
        ObservableList<ModelTableProveedor> items=s.getModelProveedor();
        this.TableProveedor.setItems(items);



    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
}

public void MostrarInvalidAlertBlank(){
    try {
        invalidPane = FXMLLoader.load(getClass().getResource("/Proveedores/AlertRegisterProveedor/InvalidAlertBlank/InvalidBlank.fxml"));
    } catch (IOException ioe) {
        ioe.printStackTrace();
    }
    invalidDialog = new JFXDialog(n, invalidPane, JFXDialog.DialogTransition.LEFT);
    invalidDialog.show();
}

public void MostrarInvalidAlter(){
    //Alert que verifica si ya se registro la categoria
    try {
        invalidPane = FXMLLoader.load(getClass().getResource("/Proveedores/AlertRegisterProveedor/InvalidAlert/InvalidAlert.fxml"));
    } catch (IOException ioe) {
        ioe.printStackTrace();
    }
    invalidDialog = new JFXDialog(n, invalidPane, JFXDialog.DialogTransition.LEFT);
    invalidDialog.show();

}
    public void  MostrarValirAlertDeleted(){
        //Alert que verifica si ya se registro la categoria
        try {
            validPane = FXMLLoader.load(getClass().getResource("/Proveedores/AlertRegisterProveedor/ValidAlertDeleted/ValidAlertDeleted.fxml"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        validDialog = new JFXDialog(n, validPane, JFXDialog.DialogTransition.LEFT);

        validDialog.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        IngresarTableProveedor= FXCollections.observableArrayList();
        tbRuc.setCellValueFactory(new PropertyValueFactory<>("Ruc"));
        tbEmpresa.setCellValueFactory(new PropertyValueFactory<>("Empresa"));
        tbDireccion.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
        tbCelular.setCellValueFactory(new PropertyValueFactory<>("Celular"));
        tbCorreo.setCellValueFactory(new PropertyValueFactory<>("Correo"));

        MostrarProveedorEnEnTabla();
    }
}
