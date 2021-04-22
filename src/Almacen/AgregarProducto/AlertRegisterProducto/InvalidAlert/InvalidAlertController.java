package Almacen.AgregarProducto.AlertRegisterProducto.InvalidAlert;

import Almacen.AgregarProducto.ControllerRegisterProducto;

import javafx.fxml.FXML;


public class InvalidAlertController {

    @FXML
    private void onGoBack() {
        ControllerRegisterProducto.invalidDialog.close();

    }

}
