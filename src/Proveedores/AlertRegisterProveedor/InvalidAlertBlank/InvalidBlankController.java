package Proveedores.AlertRegisterProveedor.InvalidAlertBlank;

import Proveedores.ControllerRegisterProveedor;
import javafx.fxml.FXML;


public class InvalidBlankController  {


    @FXML
    private void onGoBack() {
        ControllerRegisterProveedor.invalidDialog.close();

    }

}
