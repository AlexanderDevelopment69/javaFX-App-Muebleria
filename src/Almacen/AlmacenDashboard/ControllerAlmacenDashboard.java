package Almacen.AlmacenDashboard;

import Almacen.AgregarProducto.ModelTableProducto;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAlmacenDashboard implements Initializable {
    @FXML
    private BorderPane stackPrincipal;

    @FXML
    private JFXTextField browser;

    @FXML
    private TableView<Almacen.AgregarProducto.ModelTableProducto> TableAlmacenTotal;

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
    private TableColumn<?, ?> tbStock;

    @FXML
    private TableColumn<?, ?> tbTotal;



    @FXML
    void agregarProducto(MouseEvent event) throws IOException {
        FadeTransition ft = new FadeTransition(Duration.millis(600), stackPrincipal);
        ft.setFromValue(0);
        ft.setToValue(1);
        SequentialTransition pt = new SequentialTransition(ft);

        pt.play();
        Parent fxml= FXMLLoader.load(getClass().getResource("/Almacen/Fabricacion/registrarProducto13.fxml"));
        stackPrincipal.getChildren().removeAll();
        stackPrincipal.getChildren().setAll(fxml);
    }
    @FXML
    void agregarCategorias(MouseEvent event) throws IOException {
        FadeTransition ft = new FadeTransition(Duration.millis(600), stackPrincipal);
        ft.setFromValue(0);
        ft.setToValue(1);
        SequentialTransition pt = new SequentialTransition(ft);

        pt.play();
        Parent fxml= FXMLLoader.load(getClass().getResource("/Almacen/Categorias/registrarTM.fxml"));
        stackPrincipal.getChildren().removeAll();
        stackPrincipal.getChildren().setAll(fxml);
    }


    @FXML
    void agregarProveedores(MouseEvent event) throws IOException {
        FadeTransition ft = new FadeTransition(Duration.millis(600), stackPrincipal);
        ft.setFromValue(0);
        ft.setToValue(1);
        SequentialTransition pt = new SequentialTransition(ft);

        pt.play();
        Parent fxml= FXMLLoader.load(getClass().getResource("/Proveedores/registrarProveedor13.fxml"));
        stackPrincipal.getChildren().removeAll();
        stackPrincipal.getChildren().setAll(fxml);
    }
    @FXML
    void Productos(MouseEvent event) throws IOException {
        FadeTransition ft = new FadeTransition(Duration.millis(600), stackPrincipal);
        ft.setFromValue(0);
        ft.setToValue(1);
        SequentialTransition pt = new SequentialTransition(ft);

        pt.play();
        Parent fxml= FXMLLoader.load(getClass().getResource("/Almacen/AgregarProducto/registerProducto13.fxml"));
        stackPrincipal.getChildren().removeAll();
        stackPrincipal.getChildren().setAll(fxml);
    }

    //Extraendo de modeltableproducto
    public void MostrarProductosEnTabla(){
        try {

            Almacen.AgregarProducto.ModelTableProducto s=new Almacen.AgregarProducto.ModelTableProducto();
            ObservableList<Almacen.AgregarProducto.ModelTableProducto> items=s.getAlmacenTotal();
            this.TableAlmacenTotal.setItems(items);



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public void CargarDatosBuscador(String valor) {
        try {

            ModelTableProducto a = new ModelTableProducto();
            ObservableList<ModelTableProducto> items = a.getBuscadors(valor);
            this.TableAlmacenTotal.setItems(items);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void Buscador(KeyEvent event) {
        String Buscar=this.browser.getText();
        CargarDatosBuscador(Buscar);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//Mostrar en la tabla ventas TOtal con stock
        tbId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tbProducto.setCellValueFactory(new PropertyValueFactory<>("Producto"));
        tbMarca.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        tbTipo.setCellValueFactory(new PropertyValueFactory<>("Tipo"));
        tbModelo.setCellValueFactory(new PropertyValueFactory<>("Modelo"));
        tbPlazas.setCellValueFactory(new PropertyValueFactory<>("Plazas"));
        tbStock.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        tbTotal.setCellValueFactory(new PropertyValueFactory<>("Total"));
        MostrarProductosEnTabla();



    }





}
