package Almacen.Fabricacion;

import ConnectionMySQL.ConnectionMYSQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class ModelTableAlmacen {
PreparedStatement pst;


    String Marca,Tipo,Modelo,Plazas;
    double Costo;


    int Id;
    String Producto;
    int Cantidad;
    String Proveedor,Estado;
    Timestamp Fecha;


    double Total;


    public ModelTableAlmacen(String producto, int cantidad, String proveedor, String estado) {
        Producto = producto;
        Cantidad = cantidad;
        Proveedor = proveedor;
        Estado = estado;
    }

    public ModelTableAlmacen(int id, String producto, String marca, String tipo, String modelo, String plazas, int cantidad, double costo, String proveedor, String estado, Timestamp fecha) {
        Id = id;
        Producto = producto;
        Marca = marca;
        Tipo = tipo;
        Modelo = modelo;
        Plazas = plazas;
        Cantidad = cantidad;
        Costo = costo;
        Proveedor = proveedor;
        Estado = estado;
        Fecha=fecha;
    }



    public ModelTableAlmacen() {

    }


//Constructor de la tabla2 ,muestra de manera resumida la informacion de compra

public ModelTableAlmacen(int id, String producto, int cantidad, double total, String proveedor, Timestamp fecha) {
    Id = id;
    Producto = producto;
    Cantidad = cantidad;
    Total=total;
    Proveedor = proveedor;
    Fecha=fecha;
}
    //Buscador te la tabla2
    public ModelTableAlmacen(int id, String producto, String marca, String tipo, int cantidad, double total, String proveedor, Timestamp fecha) {
        Id = id;
        Producto = producto;
        Marca=marca;
        Tipo=tipo;
        Cantidad = cantidad;
        Total=total;
        Proveedor = proveedor;
        Fecha=fecha;

    }


    //Completador de combobox de la tabla productos
    public ModelTableAlmacen(String marca, double costo, String tipo, String modelo, String plazas) {

        Marca = marca;
        Costo = costo;
        Tipo = tipo;
        Modelo = modelo;
        Plazas = plazas;


    }


//    public ModelTableAlmacen( Timestamp fecha) {
//  Fecha=fecha;
//
//    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
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

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public double getCosto() {
        return Costo;
    }

    public void setCosto(double costo) {
        Costo = costo;
    }

    public String getProveedor() {
        return Proveedor;
    }

    public void setProveedor(String proveedor) {
        Proveedor = proveedor;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public Timestamp getFecha() {
        return Fecha;
    }

    public void setFecha(Timestamp fecha) {
        Fecha = fecha;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    //Observable list de la tabla 1 donde muestra los productos de la base de datos
    public ObservableList<ModelTableAlmacen> getAlmacen() throws ClassNotFoundException {
        ObservableList<ModelTableAlmacen> obs = FXCollections.observableArrayList();

        try {

            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst= connection.prepareStatement("select almacen.idAlmacen,productos.producto,productos.marca,categoria.catNombre,productos.modelo,productos.plazas,almacen.cantidad,productos.costo,proveedor.proNombre,almacen.estado,almacen.fecha from almacen inner join  proveedor on proveedor.proNombre=almacen.codProveedor inner join productos on productos.producto=almacen.codProducto inner join categoria on categoria.catNombre=productos.codTipo order by idAlmacen asc");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Id=rs.getInt("idAlmacen");
                Producto=rs.getString("producto");
                Marca=rs.getString("marca");
                Tipo=rs.getString("catNombre");
                Modelo=rs.getString("modelo");
                Plazas=rs.getString("plazas");
                Cantidad=rs.getInt("cantidad");
                Costo=rs.getDouble("costo");
                Proveedor=rs.getString("proNombre");
                Estado=rs.getString("estado");
                Fecha=rs.getTimestamp("fecha");

                ModelTableAlmacen c=new ModelTableAlmacen(Id,Producto,Marca,Tipo,Modelo,Plazas,Cantidad,Costo,Proveedor,Estado,Fecha);

//                ModelTableAlmacen c=new ModelTableAlmacen(Fecha);
                obs.add(c);
            }
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
        return obs;
    }

//    public ObservableList<ModelTableAlmacen> getCategoria() throws ClassNotFoundException {
//        ObservableList<ModelTableAlmacen> obs = FXCollections.observableArrayList();
//
//        try {
//
//            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
//            Connection connection = ConnectionClass.getConnection();
//            pst= connection.prepareStatement("select categoria.catNombre from categoria");
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//                String CatNombre=rs.getString("catNombre");
//                ModelTableAlmacen e=new ModelTableAlmacen(CatNombre);
//
////                ModelTableAlmacen c=new ModelTableAlmacen(Fecha);
//                obs.add(e);
//            }
//        } catch (Exception ex) {
//            System.out.println("" + ex);
//        }
//        return obs;
//    }

    //Buscador
    public ObservableList<ModelTableAlmacen> getBuscadors (String valor) throws ClassNotFoundException {

        ObservableList<ModelTableAlmacen> obs = FXCollections.observableArrayList();

        try {

            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst = connection.prepareStatement("select almacen.idAlmacen,productos.producto,productos.marca,categoria.catNombre,productos.modelo,productos.plazas,almacen.cantidad,productos.costo,proveedor.proNombre,almacen.estado,almacen.fecha from almacen inner join  proveedor on proveedor.proNombre=almacen.codProveedor inner join productos on productos.producto=almacen.codProducto inner join categoria on categoria.catNombre=productos.codTipo    where producto like '%"+valor+"%' or marca like '%"+valor+"%' or catNombre like '%"+valor+"%' or fecha like '%"+valor+"%'or proNombre like '%"+valor+"%' order by idAlmacen asc");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                    Id = rs.getInt("idAlmacen");
                    Producto = rs.getString("producto");
                    Marca = rs.getString("marca");
                    Tipo = rs.getString("catNombre");
                    Modelo = rs.getString("modelo");
                    Plazas = rs.getString("plazas");
                    Cantidad = rs.getInt("cantidad");
                    Costo = rs.getDouble("costo");
                    Proveedor = rs.getString("proNombre");
                    Estado = rs.getString("estado");
                    Fecha = rs.getTimestamp("fecha");

                ModelTableAlmacen c = new ModelTableAlmacen(Id, Producto, Marca, Tipo, Modelo, Plazas, Cantidad,Costo, Proveedor, Estado, Fecha);

//                ModelTableAlmacen c=new ModelTableAlmacen(Fecha);
                obs.add(c);
            }
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
        return obs;
    }


    //Observable list de la tabla2 Resumen de un balance de compras

    public ObservableList<ModelTableAlmacen> getTotalAlmacen () throws ClassNotFoundException {


        ObservableList<ModelTableAlmacen> obs = FXCollections.observableArrayList();

        try {

            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst = connection.prepareStatement("select almacen.idAlmacen,productos.producto,almacen.cantidad,(costo*cantidad) as total,proveedor.proNombre,almacen.fecha from almacen inner join  proveedor on proveedor.proNombre=almacen.codProveedor inner join productos on productos.producto=almacen.codProducto  order by idAlmacen asc;");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Id = rs.getInt("idAlmacen");
                Producto = rs.getString("producto");
                Cantidad = rs.getInt("cantidad");
                Total=rs.getDouble("total");
                Proveedor = rs.getString("proNombre");
                Fecha=rs.getTimestamp("Fecha");


                ModelTableAlmacen c = new ModelTableAlmacen(Id, Producto,Cantidad,Total,Proveedor,Fecha);

//                ModelTableAlmacen c=new ModelTableAlmacen(Fecha);
                obs.add(c);
            }
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
        return obs;
    }

    //Buscador de la tabla2
    public ObservableList<ModelTableAlmacen> getBuscador2 (String valor) throws ClassNotFoundException {

        ObservableList<ModelTableAlmacen> obs = FXCollections.observableArrayList();

        try {

            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst = connection.prepareStatement("select almacen.idAlmacen,productos.producto,productos.marca,categoria.catNombre,almacen.cantidad,(costo*cantidad) as total,proveedor.proNombre,almacen.fecha from almacen inner join  proveedor on proveedor.proNombre=almacen.codProveedor inner join productos on productos.producto=almacen.codProducto inner join categoria on categoria.catNombre=productos.codTipo where producto like '%"+valor+"%' or proNombre like '%"+valor+"%' or fecha like '%"+valor+"%' or marca like '%"+valor+"%' or catNombre like '%"+valor+"%'  order by idAlmacen asc");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Id = rs.getInt("idAlmacen");

                Producto = rs.getString("producto");
                Marca=rs.getString("marca");
                Tipo=rs.getString("catNombre");
                Cantidad = rs.getInt("cantidad");
                Total=rs.getDouble("total");
                Proveedor = rs.getString("proNombre");
                Fecha = rs.getTimestamp("fecha");

                ModelTableAlmacen c = new ModelTableAlmacen(Id,Producto,Marca,Tipo,Cantidad,Total,Proveedor,Fecha);

//                ModelTableAlmacen c=new ModelTableAlmacen(Fecha);
                obs.add(c);
            }
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
        return obs;
    }

//Observable list de consultas combobox
//    public ObservableList<ModelTableAlmacen> getConsultar () throws ClassNotFoundException {
//
//        ObservableList<ModelTableAlmacen> obs = FXCollections.observableArrayList();
//
//        try {
//
//            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
//            Connection connection = ConnectionClass.getConnection();
//            pst = connection.prepareStatement("select productos.marca,productos.precio,productos.codTipo,productos.modelo,productos.plazas from productos");
//
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//
//                Marca=rs.getString("marca");
//                Costo=rs.getDouble("costo");
//                Tipo=rs.getString("catNombre");
//                Modelo=rs.getString("modelo");
//                Plazas=rs.getString("plazas");
//
//
//                ModelTableAlmacen c = new ModelTableAlmacen(Marca,Costo,Tipo,Modelo,Plazas);
//
////                ModelTableAlmacen c=new ModelTableAlmacen(Fecha);
//                obs.add(c);
//            }
//        } catch (Exception ex) {
//            System.out.println("" + ex);
//        }
//        return obs;
//    }




}