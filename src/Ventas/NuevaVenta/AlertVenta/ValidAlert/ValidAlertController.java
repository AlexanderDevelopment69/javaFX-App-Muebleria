package Ventas.NuevaVenta.AlertVenta.ValidAlert;


import Ventas.NuevaVenta.ControllerRegisterVenta;
import javafx.fxml.FXML;

import java.io.IOException;

public class ValidAlertController {

    @FXML
    private void onDone() throws IOException {
        ControllerRegisterVenta.validDialog.close();



    }

}

