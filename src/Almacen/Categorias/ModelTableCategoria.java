package Almacen.Categorias;


import ConnectionMySQL.ConnectionMYSQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ModelTableCategoria {
PreparedStatement pst;
        String Categoria,Descripcion;

        int Id;

    public ModelTableCategoria(String categoria, String descripcion) {
        Categoria = categoria;
        Descripcion = descripcion;
    }

    public ModelTableCategoria() {

    }


    public ModelTableCategoria(int id , String categoria, String descripcion) {
        Id=id;
        Categoria=categoria;
        Descripcion=descripcion;
    }



    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public ObservableList<ModelTableCategoria> getModelCategoria() throws ClassNotFoundException {
        ObservableList<ModelTableCategoria> obs = FXCollections.observableArrayList();

        try {

            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst= connection.prepareStatement("select *from categoria order by catId asc");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Id=rs.getInt("catId");
                Categoria=rs.getString("catNombre");
                Descripcion=rs.getString("descripcion");
                ModelTableCategoria c=new ModelTableCategoria(Id,Categoria,Descripcion);
                obs.add(c);

            }

        } catch (Exception ex) {
            System.out.println("" + ex);
        }
        return obs;
    }

    public ObservableList<ModelTableCategoria> getBuscadors (String valor) throws ClassNotFoundException {

        ObservableList<ModelTableCategoria> obs = FXCollections.observableArrayList();

        try {

            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst = connection.prepareStatement("select categoria.catId,categoria.catNombre,categoria.descripcion from categoria  where catId like '%"+valor+"%' or catNombre like '%"+valor+"%'  order by catId asc");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Id=rs.getInt("catId");
                Categoria=rs.getString("catNombre");
                Descripcion=rs.getString("descripcion");
                ModelTableCategoria c = new ModelTableCategoria(Id,Categoria,Descripcion);
                
                obs.add(c);
            }
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
        return obs;
    }

}
