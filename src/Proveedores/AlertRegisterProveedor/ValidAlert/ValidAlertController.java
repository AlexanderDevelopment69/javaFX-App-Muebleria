package Proveedores.AlertRegisterProveedor.ValidAlert;

import Proveedores.ControllerRegisterProveedor;

import javafx.fxml.FXML;

import java.io.IOException;

public class ValidAlertController {

    @FXML
    private void onDone() throws IOException {
        ControllerRegisterProveedor.validDialog.close();



    }

}

