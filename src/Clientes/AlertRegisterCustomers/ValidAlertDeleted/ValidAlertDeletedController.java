package Clientes.AlertRegisterCustomers.ValidAlertDeleted;


import Clientes.RegisterCustomers.ControllerRegisterCustomers;

import javafx.fxml.FXML;

import java.io.IOException;

public class ValidAlertDeletedController {


    @FXML
    private void onDone() throws IOException {
        ControllerRegisterCustomers.validDialog.close();



    }

}

