package Proveedores.AlertRegisterProveedor.ValidAlertDeleted;


import Proveedores.ControllerRegisterProveedor;

import javafx.fxml.FXML;

import java.io.IOException;

public class ValidAlertDeletedController {


    @FXML
    private void onDone() throws IOException {
        ControllerRegisterProveedor.validDialog.close();



    }

}

