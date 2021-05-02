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

    int Total;

    int Stock;

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

    //Para dashboardAlmacen Tabla total de almacen con stock
    public ModelTableProducto(String id, String producto, String marca, String tipo, String modelo, String plazas, double costo, double precio, int stock,int total) {
        Id = id;
        Producto = producto;
        Marca = marca;
        Tipo = tipo;
        Modelo = modelo;
        Plazas = plazas;
        Costo=costo;
        Precio = precio;
        Stock=stock;
        Total=total;
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

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
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
            connection.close();
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
            connection.close();
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
        return obs;
    }


    //Mostrar total de producto con el stock
    public ObservableList<ModelTableProducto> getAlmacenTotal() throws ClassNotFoundException {
        ObservableList<ModelTableProducto> obs = FXCollections.observableArrayList();

        try {

            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
//            pst= connection.prepareStatement("select productos.codProducto,productos.producto,productos.marca,categoria.catNombre,productos.modelo,productos.plazas,productos.costo,productos.precio,productos.stock from productos inner join categoria on categoria.catNombre=productos.codTipo  order by codProducto asc");
            pst=connection.prepareStatement("select productos.codProducto,productos.producto,productos.marca,categoria.catNombre,productos.modelo,productos.plazas,productos.costo,productos.precio,productos.stock, sum(almacen.cantidad) as Total  from productos inner join categoria on categoria.catNombre=productos.codTipo inner join almacen on almacen.codProducto=productos.producto  group by codProducto order by codProducto asc") ;
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
                Stock = rs.getInt("stock");
                Total = rs.getInt("Total");
                ModelTableProducto c=new ModelTableProducto(Id,Producto,Marca,Tipo,Modelo,Plazas,Costo,Precio,Stock,Total);
                obs.add(c);
            }
            connection.close();
        } catch (Exception ex) {
            System.out.println("" + ex);
        }


        return obs;
    }



}
