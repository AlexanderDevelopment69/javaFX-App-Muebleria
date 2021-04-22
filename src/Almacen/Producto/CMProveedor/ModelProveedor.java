package Almacen.Producto.CMProveedor;

import ConnectionMySQL.ConnectionMYSQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ModelProveedor {
    PreparedStatement pst;
    String ProRuc;

    public ModelProveedor(String proRuc) {
        ProRuc = proRuc;
    }

    public ModelProveedor() {

    }

    public String getProRuc() {
        return ProRuc;
    }

    public void setProRuc(String proRuc) {
        ProRuc = proRuc;
    }

    @Override
    public String toString() {
        return ProRuc;
    }

    public ObservableList<ModelProveedor> getProveedor()  {
        ObservableList<ModelProveedor> obs = FXCollections.observableArrayList();

        try {

            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst= connection.prepareStatement("select proveedor.proNombre from proveedor");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
//                int CatId=rs.getInt("catId");

                ProRuc=rs.getString("proNombre");
                ModelProveedor e=new ModelProveedor(ProRuc);


//                ModelTableAlmacen c=new ModelTableAlmacen(Fecha);
                obs.add(e);
            }
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
        return obs;
    }
}
