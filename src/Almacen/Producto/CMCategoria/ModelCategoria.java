package Almacen.Producto.CMCategoria;

import ConnectionMySQL.ConnectionMYSQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ModelCategoria {
    PreparedStatement pst;

//    int catId;
    String Tipo;
    public ModelCategoria() {

    }

//    public ModelCategoria(int catId) {
//        this.catId = catId;
//    }
//
//    public int getCatId() {
//
//        return catId;
//    }
//
//    public void setCatId(int catId) {
//        this.catId = catId;
//    }
//
//    @Override
//    public String toString() {
//        return ""+ catId;
//    }


        public ModelCategoria(String tipo) {
        Tipo = tipo;
    }

    @Override
    public String toString() {
        return Tipo;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public ObservableList<ModelCategoria> getCategoria()  {
        ObservableList<ModelCategoria> obs = FXCollections.observableArrayList();

        try {

            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst= connection.prepareStatement("select categoria.catNombre from categoria");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
//                int CatId=rs.getInt("catId");

                String Tipo=rs.getString("catNombre");
                ModelCategoria e=new ModelCategoria(Tipo);




//                ModelTableAlmacen c=new ModelTableAlmacen(Fecha);
                obs.add(e);
            }
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
        return obs;
    }
}
