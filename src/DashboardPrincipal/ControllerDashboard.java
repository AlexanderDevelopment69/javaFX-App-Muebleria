package DashboardPrincipal;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerDashboard implements Initializable {


    @FXML
    private BorderPane stackPrincipal;

    @FXML
    private BarChart<?, ?> VentasChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        XYChart.Series set1=new XYChart.Series<>();
        set1.getData().add(new XYChart.Data<>("Enero",10));
        set1.getData().add(new XYChart.Data<>("Febrero",20));
        set1.getData().add(new XYChart.Data<>("Marzo",30));
        set1.getData().add(new XYChart.Data<>("Abril",40));
        set1.getData().add(new XYChart.Data<>("Mayo",50));
        VentasChart.getData().addAll(set1);
        set1.setName("Total de venta");
//        VentasChart.setLegendSide(Side.TOP );
//        VentasChart.setLegendSide(Side.RIGHT);
    }
}
