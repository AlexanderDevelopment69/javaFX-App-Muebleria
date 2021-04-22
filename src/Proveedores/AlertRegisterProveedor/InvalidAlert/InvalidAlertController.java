package Proveedores.AlertRegisterProveedor.InvalidAlert;

import Proveedores.ControllerRegisterProveedor;
import javafx.fxml.FXML;


public class InvalidAlertController {

    @FXML
    private void onGoBack() {
        ControllerRegisterProveedor.invalidDialog.close();

    }

}
