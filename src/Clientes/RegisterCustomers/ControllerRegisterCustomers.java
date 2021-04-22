package Clientes.RegisterCustomers;

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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerRegisterCustomers implements Initializable {

    @FXML
    private JFXTextField browser;


    @FXML
    private StackPane n;

    @FXML
    private VBox validPane, invalidPane;
    @FXML
    public static JFXDialog validDialog, invalidDialog;


    @FXML
    private JFXTextField nombre;

    @FXML
    private JFXTextField apellidos;

    @FXML
    private JFXTextField dni;

    @FXML
    private JFXComboBox<String> cmSexo;

    @FXML
    private JFXTextField ruc;

    @FXML
    private JFXTextField direccion;

    @FXML
    private JFXTextField celular;
    @FXML
    private JFXTextField edad;
    @FXML
    private JFXTextField correo;
    @FXML
    private TableView<ModelTableCliente> TableCliente;

    @FXML
    private TableColumn<?, ?> tbCodigo;

    @FXML
    private TableColumn<?, ?> tbNombre;

    @FXML
    private TableColumn<?, ?> tbApellidos;

    @FXML
    private TableColumn<?, ?> tbDni;

    @FXML
    private TableColumn<?, ?> tbRuc;

    @FXML
    private TableColumn<?, ?> tbSexo;

    @FXML
    private TableColumn<?, ?> tbDireccion;

    @FXML
    private TableColumn<?, ?> tbCelular;

    @FXML
    private TableColumn<?, ?> tbEdad;

    @FXML
    private TableColumn<?, ?> tbCorreo;

    PreparedStatement pst;
    private ObservableList<ModelTableCliente> IngresarTableCliente;



    @FXML
    void Agregar(MouseEvent event) {


       String Nombres,Apellidos,Direccion,Sexo,Dni,Ruc,Celular,Edad,Correo;


        Nombres=nombre.getText();
        Apellidos=apellidos.getText();
        Direccion=direccion.getText();
        Sexo= String.valueOf(cmSexo.getValue());
        Dni=dni.getText();
        Ruc=ruc.getText();
        Celular=celular.getText();
        Edad=edad.getText();
        Correo=correo.getText();



        ModelTableCliente p=new ModelTableCliente(Nombres,Apellidos,Direccion,Sexo,Dni,Ruc,Celular,Edad,Correo);
        this.IngresarTableCliente.add(p);
        this.TableCliente.setItems(IngresarTableCliente);

        try {
            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst = connection.prepareStatement("insert into cliente (cliNombres,cliApellidos,cliDireccion,cliSexo,cliDni,cliRuc,cliCelular,cliEdad,cliCorreo) values (?,?,?,?,?,?,?,?,?)");
            pst.setString(1,Nombres);
            pst.setString(2,Apellidos);
            pst.setString(3, Direccion);
            pst.setString(4, Sexo);
            pst.setString(5, Dni);
            pst.setString(6, Ruc);
            pst.setString(7,Celular);
            pst.setString(8,Edad);
            pst.setString(9,Correo);
            pst.executeUpdate();

            MostrarValidAltert();

        } catch(SQLException ioe) {
            ioe.printStackTrace();
        }

        MostrarClienteEnTabla();

    }


    @FXML
    void Buscar(MouseEvent event) {

    }

    @FXML
    void Nuevo(MouseEvent event) {

    }
    public void MostrarClienteEnTabla(){
        try {

            ModelTableCliente s=new ModelTableCliente();
            ObservableList<ModelTableCliente> items=s.getCliente();
            this.TableCliente.setItems(items);



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void  MostrarValidAltert(){
        //Alert que verifica si ya se registro la categoria
        try {
            validPane = FXMLLoader.load(getClass().getResource("/Clientes/AlertRegisterCustomers/ValidAlert/ValidAlert.fxml"));

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        validDialog = new JFXDialog(n, validPane, JFXDialog.DialogTransition.LEFT);

        validDialog.show();

    }

//    public void  MostrarInValidAltert(){
//        //Alert que verifica si ya se registro la categoria
//        try {
//            invalidPane = FXMLLoader.load(getClass().getResource("/Clientes/Categorias/AlertRegisterCustomers/InvalidAlert/InvalidAlert.fxml"));
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//        invalidDialog = new JFXDialog(n, invalidPane, JFXDialog.DialogTransition.LEFT);
//        invalidDialog.show();
//
//    }
//    public void  MostrarInValidAltertBlank(){
//        //Alert que verifica si ya se registro la categoria
//        try {
//            invalidPane = FXMLLoader.load(getClass().getResource("/Clientes/Categorias/AlertRegisterCustomers/InvalidAlertBlank/InvalidBlank.fxml"));
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//        invalidDialog = new JFXDialog(n, invalidPane, JFXDialog.DialogTransition.LEFT);
//        invalidDialog.show();
//
//    }
//    public void  MostrarValirAlertDeleted(){
//        //Alert que verifica si ya se registro la categoria
//        try {
//            validPane = FXMLLoader.load(getClass().getResource("/Clientes/Categorias/AlertRegisterCustomers/ValidAlertDeleted/ValidAlertDeleted.fxml"));
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//        validDialog = new JFXDialog(n, validPane, JFXDialog.DialogTransition.LEFT);
//
//        validDialog.show();
//
//    }

    @FXML
    void Buscar(KeyEvent event) {

    }
            void Nuevo(){
                nombre.setText("");
                apellidos.setText("");
                direccion.setText("");
                cmSexo.setValue(null);
                dni.setText("");
                ruc.setText("");
                celular.setText("");
                nombre.requestFocus();
            }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        IngresarTableCliente = FXCollections.observableArrayList();

        tbCodigo.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tbNombre.setCellValueFactory(new PropertyValueFactory<>("Nombres"));
        tbApellidos.setCellValueFactory(new PropertyValueFactory<>("Apellidos"));
        tbDireccion.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
        tbSexo.setCellValueFactory(new PropertyValueFactory<>("Sexo"));
        tbDni.setCellValueFactory(new PropertyValueFactory<>("Dni"));
        tbRuc.setCellValueFactory(new PropertyValueFactory<>("Ruc"));
        tbCelular.setCellValueFactory(new PropertyValueFactory<>("Celular"));
        tbEdad.setCellValueFactory(new PropertyValueFactory<>("Ruc"));
        tbCorreo.setCellValueFactory(new PropertyValueFactory<>("Celular"));
        MostrarClienteEnTabla();

        ObservableList<String> c = FXCollections.observableArrayList("M","F");
        cmSexo.setItems(c);

    }

}
