package Almacen.AgregarProducto;


import ConnectionMySQL.ConnectionMYSQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ModelTableProducto {
    PreparedStatement pst;
    String Id,Producto, Marca;
    String Tipo;
    String  Modelo, Plazas;
    double Costo;
    double Precio;

    public ModelTableProducto(String id, String producto, String marca, String tipo, String modelo, String plazas,double costo, double precio) {
        Id = id;
        Producto = producto;
        Marca = marca;
        Tipo = tipo;
        Modelo = modelo;
        Plazas = plazas;
        Costo=costo;
        Precio = precio;
    }

    public ModelTableProducto() {

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getProducto() {
        return Producto;
    }

    public void setProducto(String producto) {
        Producto = producto;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public String getPlazas() {
        return Plazas;
    }

    public void setPlazas(String plazas) {
        Plazas = plazas;
    }

    public double getCosto() {
        return Costo;
    }

    public void setCosto(double costo) {
        Costo = costo;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double precio) {
        Precio = precio;
    }

    public ObservableList<ModelTableProducto> getProductos() throws ClassNotFoundException {
        ObservableList<ModelTableProducto> obs = FXCollections.observableArrayList();

        try {

            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst= connection.prepareStatement("select productos.codProducto,productos.producto,productos.marca,categoria.catNombre,productos.modelo,productos.plazas,productos.costo,productos.precio from productos inner join categoria on categoria.catNombre=productos.codTipo  order by codProducto asc");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                 Id=rs.getString("codProducto");
                 Producto=rs.getString("producto");
                 Marca=rs.getString("marca");
                 Tipo=rs.getString("catNombre");
                 Modelo=rs.getString("modelo");
                 Plazas=rs.getString("plazas");
                  Costo=rs.getDouble("costo");
                  Precio=rs.getDouble("precio");

                ModelTableProducto c=new ModelTableProducto(Id,Producto,Marca,Tipo,Modelo,Plazas,Costo,Precio);

//                ModelTableAlmacen c=new ModelTableAlmacen(Fecha);
                obs.add(c);
            }
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
        return obs;
    }








    public ObservableList<ModelTableProducto> getBuscadors (String valor) throws ClassNotFoundException {

        ObservableList<ModelTableProducto> obs = FXCollections.observableArrayList();

        try {

            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst = connection.prepareStatement("select productos.codProducto,productos.producto,productos.marca,categoria.catNombre,productos.modelo,productos.plazas,productos.costo,productos.precio from productos inner join categoria on categoria.catNombre=productos.codTipo  where producto like '%"+valor+"%' or codProducto like '%"+valor+"%' or marca like '%"+valor+"%' order by codProducto asc");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Id = rs.getString("codProducto");
                 Producto = rs.getString("producto");
                 Marca = rs.getString("marca");
                 Tipo = rs.getString("catNombre");
                 Modelo = rs.getString("modelo");
                 Plazas = rs.getString("plazas");
                 Costo = rs.getDouble("costo");
                 Precio = rs.getDouble("precio");



                ModelTableProducto c = new ModelTableProducto(Id, Producto, Marca, Tipo, Modelo, Plazas,Costo, Precio);

//                ModelTableAlmacen c=new ModelTableAlmacen(Fecha);
                obs.add(c);
            }
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
        return obs;
    }




}
