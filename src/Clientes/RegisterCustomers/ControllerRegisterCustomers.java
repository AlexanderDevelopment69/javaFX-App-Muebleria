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

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Objects;
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
    private JFXTextField fecha;
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
    private TableColumn<?, ?> tbFecha;
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
//        Edad=edad.getText();
        String Fecha= fecha.getText();
        Correo=correo.getText();

        if(Nombres.isBlank()||Apellidos.isBlank()||Direccion.isBlank()||Sexo.isBlank()||Dni.isBlank()){

        JOptionPane.showMessageDialog(null,"Completar");

        }else{

//            ModelTableCliente p=new ModelTableCliente(Nombres,Apellidos,Direccion,Sexo,Dni,Ruc,Celular,Edad,Correo);
            ModelTableCliente p=new ModelTableCliente(Nombres,Apellidos,Direccion,Sexo,Dni,Ruc,Celular,Fecha,Correo);
            this.IngresarTableCliente.add(p);
            this.TableCliente.setItems(IngresarTableCliente);

            try {
                ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
                Connection connection = ConnectionClass.getConnection();
                pst = connection.prepareStatement("insert into cliente (cliNombres,cliApellidos,cliDireccion,cliSexo,cliDni,cliRuc,cliCelular,cliFecha,cliCorreo) values (?,?,?,?,?,?,?,STR_TO_DATE(?,'%d/%m/%Y'),?)");
                pst.setString(1,Nombres);
                pst.setString(2,Apellidos);
                pst.setString(3, Direccion);
                pst.setString(4, Sexo);
                pst.setString(5, Dni);
                pst.setString(6, Ruc);
                pst.setString(7,Celular);
//                pst.setString(8,Edad);
                pst.setString(8, String.valueOf(Fecha));
                pst.setString(9,Correo);
                pst.executeUpdate();
                Nuevo();
                MostrarValidAltert();
                connection.close();

            } catch(SQLException ioe) {
                ioe.printStackTrace();
            }

            MostrarClienteEnTabla();


        }



    }


    @FXML
    void Buscar(MouseEvent event) {

    }
    @FXML
    void Eliminar(MouseEvent event)  {

        ModelTableCliente p=TableCliente.getSelectionModel().getSelectedItem();

            if (p.equals(null)) {

            } else {

                try {
                ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
                Connection connection = ConnectionClass.getConnection();
                pst = connection.prepareStatement("delete from cliente where cliCodigo=?");
                pst.setInt(1, p.getId());
                pst.executeUpdate();
                connection.close();
                MostrarValirAlertDeleted();
                Nuevo();
                MostrarClienteEnTabla();
                this.TableCliente.refresh();
                    connection.close();

            }catch (SQLException e){
                JOptionPane.showMessageDialog(null,"Cliente no puede ser Eliminado por que efectua una venta");
            }

            }

    }


    @FXML
    public  void Seleccionar(MouseEvent event) {
        ModelTableCliente p=TableCliente.getSelectionModel().getSelectedItem();
        if(p==null){


        }
        else {

            nombre.setText(p.getNombres());
            apellidos.setText(p.getApellidos());
            direccion.setText(p.getDireccion());
//            edad.setText(p.getEdad());
            fecha.setText(String.valueOf(p.getFecha()));
            cmSexo.setValue(p.getSexo());
            dni.setText(p.getDni());
            ruc.setText(p.getRuc());
            celular.setText(p.getCelular());
            correo.setText(p.getCorreo());


        }
    }

    @FXML
    void Buscador(KeyEvent event) {
        String Buscar=this.browser.getText();
        CargarDatosBuscador(Buscar);
    }


    public void CargarDatosBuscador(String valor) {
        try {

            ModelTableCliente a=new ModelTableCliente();
            ObservableList<ModelTableCliente> items=a.getBuscadors(valor);
            this.TableCliente.setItems(items);



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    @FXML
    void Actualizar(MouseEvent event) throws SQLException {



        //Selecionar items de la tabla
        ModelTableCliente p = TableCliente.getSelectionModel().getSelectedItem();
        if (p == null) {
            JOptionPane.showMessageDialog(null,"Selecciona algo");

        } else {

            //Conexion a la base de datos;
            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst = connection.prepareStatement("update cliente set cliNombres=? ,cliApellidos=?,cliDNI=?,cliRUC=? where cliCodigo=?");
            pst.setString(1, String.valueOf(nombre.getText()));
            pst.setString(2, String.valueOf(apellidos.getText()));
            pst.setString(3, String.valueOf(dni.getText()));
            pst.setString(4, String.valueOf(ruc.getText()));
            pst.setInt(5, p.getId());
            pst.executeUpdate();
            connection.close();
            Nuevo();
            MostrarValidAlertUpdate();
            MostrarClienteEnTabla();
            this.TableCliente.refresh();
            connection.close();
        }
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
            validPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Clientes/AlertRegisterCustomers/ValidAlert/ValidAlert.fxml")));

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        validDialog = new JFXDialog(n, validPane, JFXDialog.DialogTransition.LEFT);

        validDialog.show();

    }
    public void  MostrarValidAlertUpdate(){
        //Alert que verifica si ya se registro la categoria
        try {
            validPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Clientes/AlertRegisterCustomers/ValidAlertUpdate/ValidAlertUpdate.fxml")));
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
    public void  MostrarValirAlertDeleted(){
        //Alert que verifica si ya se registro la categoria
        try {
            validPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Clientes/AlertRegisterCustomers/ValidAlertDeleted/ValidAlertDeleted.fxml")));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        validDialog = new JFXDialog(n, validPane, JFXDialog.DialogTransition.LEFT);

        validDialog.show();

    }

    @FXML
    void Buscar(KeyEvent event) {

    }
            void Nuevo(){
                nombre.setText("");
                apellidos.setText("");
                direccion.setText("");
                cmSexo.setValue(null);
//                edad.setText("");
                fecha.setText("");
                dni.setText("");
                ruc.setText("");
                celular.setText("");
                correo.setText("");
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
//        tbEdad.setCellValueFactory(new PropertyValueFactory<>("Edad"));
        tbFecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
        tbCorreo.setCellValueFactory(new PropertyValueFactory<>("Correo"));
        MostrarClienteEnTabla();

        ObservableList<String> c = FXCollections.observableArrayList("M","F");
        cmSexo.setItems(c);



    }

}
