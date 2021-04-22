package Almacen.AgregarProducto.AlertRegisterProducto.InvalidAlertBlank;

import Almacen.AgregarProducto.ControllerRegisterProducto;

import javafx.fxml.FXML;


public class InvalidBlankController  {


    @FXML
    private void onGoBack() {
        ControllerRegisterProducto.invalidDialog.close();

    }

}
