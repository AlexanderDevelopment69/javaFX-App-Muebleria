package Ventas.VentasDashboard;

import Almacen.AgregarProducto.ModelTableProducto;
import ConnectionMySQL.ConnectionMYSQL;
import Ventas.NuevaVenta.ModelTableVentas;
import Login.Controller;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ControllerVentasDashboard implements Initializable {

    @FXML
    private BorderPane stackPrincipal;

    @FXML
    private JFXTextField browser;

    @FXML
    private JFXTextField TotalVentas;
    @FXML
    private JFXTextField TotalDescuento;
    @FXML
    private JFXTextField TotalCantidadVentas;



    @FXML
    private TableView<Ventas.NuevaVenta.ModelTableVentas> TableVentas;

    @FXML
    private TableColumn<?, ?> tbIdVenta;

    @FXML
    private TableColumn<?, ?> tbCliente;

    @FXML
    private TableColumn<?, ?> tbVendedor;

    @FXML
    private TableColumn<?, ?> tbDescuento;

    @FXML
    private TableColumn<?, ?> tbTotal;

    @FXML
    private TableColumn<?, ?> tbFecha;
    PreparedStatement pst;

    @FXML
    void NuevaVenta(MouseEvent event) throws IOException {
        FadeTransition ft = new FadeTransition(Duration.millis(600), stackPrincipal);
        ft.setFromValue(0);
        ft.setToValue(1);
        SequentialTransition pt = new SequentialTransition(ft);

        pt.play();
        Parent fxml= FXMLLoader.load(getClass().getResource("/Ventas/NuevaVenta/registrarVenta13.fxml"));
        stackPrincipal.getChildren().removeAll();
        stackPrincipal.getChildren().setAll(fxml);
    }


    public void MostrarVentasTabla(){
        try {

            Ventas.NuevaVenta.ModelTableVentas s=new Ventas.NuevaVenta.ModelTableVentas();
            ObservableList<Ventas.NuevaVenta.ModelTableVentas> items=s.getVentas();
            this.TableVentas.setItems(items);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void DetalleVenta(MouseEvent event) throws IOException {

        FadeTransition ft = new FadeTransition(Duration.millis(600), stackPrincipal);
        ft.setFromValue(0);
        ft.setToValue(1);
        SequentialTransition pt = new SequentialTransition(ft);

        pt.play();
        Parent fxml= FXMLLoader.load(getClass().getResource("/Ventas/DetalleVenta/detalleVenta.fxml"));
        stackPrincipal.getChildren().removeAll();
        stackPrincipal.getChildren().setAll(fxml);

    }


    public void CompletarInfo(){

        try {

            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst = connection.prepareStatement("select sum(venta.total)as TotalVentas,sum(venta.descuento)as TotalDescuento, count(idVenta) as TotalCantidadVentas from venta ");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                TotalVentas.setText("S/. "+rs.getBigDecimal("TotalVentas"));
                TotalDescuento.setText("S/. "+rs.getBigDecimal("TotalDescuento"));
                TotalCantidadVentas.setText(String.valueOf(+rs.getInt("TotalCantidadVentas")));

            }
            connection.close();
//            codProducto.setStyle("-fx-text-fill:#644EFF");


        } catch (Exception ex) {
            System.out.println("" + ex);
        }


    }

    public void CargarDatosBuscador(String valor) {
        try {

            ModelTableVentas a=new ModelTableVentas();
            ObservableList<ModelTableVentas> items=a.getBuscadors(valor);
            this.TableVentas.setItems(items);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    @FXML
    void Buscador(KeyEvent event) {
        String Buscar=this.browser.getText();
        CargarDatosBuscador(Buscar);
    }
    @FXML
    void Salir(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {





        tbIdVenta.setCellValueFactory(new PropertyValueFactory<>("Venta"));
        tbCliente.setCellValueFactory(new PropertyValueFactory<>("IdCliente"));
        tbVendedor.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        tbDescuento.setCellValueFactory(new PropertyValueFactory<>("Descuento"));
        tbTotal.setCellValueFactory(new PropertyValueFactory<>("Total"));
        tbFecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
        MostrarVentasTabla();
        CompletarInfo();






    }





}
