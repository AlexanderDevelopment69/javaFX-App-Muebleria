package Almacen.Producto;

import ConnectionMySQL.ConnectionMYSQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class ModelTableAlmacen {
PreparedStatement pst;

    String Id;
    String Producto,Marca,Tipo,Modelo,Plazas;
    int Cantidad;
    Double Precio;
    String Proveedor,Estado;
    Timestamp Fecha;


    public ModelTableAlmacen(String id, String producto, String marca, String tipo, String modelo, String plazas, int cantidad, Double precio, String proveedor, String estado) {
        Id = id;
        Producto = producto;
        Marca = marca;
        Tipo = tipo;
        Modelo = modelo;
        Plazas = plazas;
        Cantidad = cantidad;
        Precio = precio;
        Proveedor = proveedor;
        Estado = estado;
    }







    public ModelTableAlmacen(String id, String producto, String marca, String tipo, String modelo, String plazas, int cantidad, double precio, String proveedor, String estado, Timestamp fecha) {
        Id = id;
        Producto = producto;
        Marca = marca;
        Tipo = tipo;
        Modelo = modelo;
        Plazas = plazas;
        Cantidad = cantidad;
        Precio = precio;
        Proveedor = proveedor;
        Estado = estado;
        Fecha=fecha;
    }



    public ModelTableAlmacen() {

    }

//    public ModelTableAlmacen( Timestamp fecha) {
//  Fecha=fecha;
//
//    }


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

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public Double getPrecio() {
        return Precio;
    }

    public void setPrecio(Double precio) {
        Precio = precio;
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



    public ObservableList<ModelTableAlmacen> getAlmacen() throws ClassNotFoundException {
        ObservableList<ModelTableAlmacen> obs = FXCollections.observableArrayList();

        try {

            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst= connection.prepareStatement("select producto.codProducto,producto.producto,producto.marca,categoria.catNombre,producto.modelo,producto.plazas,producto.cantidad,producto.precio,proveedor.proNombre,producto.estado,producto.fecha from producto inner join categoria on categoria.catNombre=producto.codTipo join proveedor on proveedor.proNombre=producto.codProveedor order by codProducto asc");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String Id=rs.getString("codProducto");
                String Producto=rs.getString("producto");
                String Marca=rs.getString("marca");
                String Tipo=rs.getString("catNombre");
                String Modelo=rs.getString("modelo");
                String Plazas=rs.getString("plazas");
                int Cantidad=rs.getInt("cantidad");
                double Precio=rs.getDouble("precio");
                String Proveedor=rs.getString("proNombre");
                String Estado=rs.getString("estado");
                Timestamp Fecha=rs.getTimestamp("fecha");

                ModelTableAlmacen c=new ModelTableAlmacen(Id,Producto,Marca,Tipo,Modelo,Plazas,Cantidad,Precio,Proveedor,Estado,Fecha);

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

    public ObservableList<ModelTableAlmacen> getBuscadors (String valor) throws ClassNotFoundException {

        ObservableList<ModelTableAlmacen> obs = FXCollections.observableArrayList();

        try {

            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst = connection.prepareStatement("select producto.codProducto,producto.producto,producto.marca,categoria.catNombre,producto.modelo,producto.plazas,producto.cantidad,producto.precio,proveedor.proNombre,producto.estado,producto.fecha from producto inner join categoria on categoria.catNombre=producto.codTipo join proveedor on proveedor.proNombre=producto.codProveedor where producto like '%"+valor+"%' or codProducto like '%"+valor+"%' or marca like '%"+valor+"%' order by codProducto asc");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String Id = rs.getString("codProducto");
                String Producto = rs.getString("producto");
                String Marca = rs.getString("marca");
                String Tipo = rs.getString("catNombre");
                String Modelo = rs.getString("modelo");
                String Plazas = rs.getString("plazas");
                int Cantidad = rs.getInt("cantidad");
                double Precio = rs.getDouble("precio");
                String Proveedor = rs.getString("proNombre");
                String Estado = rs.getString("estado");
                Timestamp Fecha = rs.getTimestamp("fecha");

                ModelTableAlmacen c = new ModelTableAlmacen(Id, Producto, Marca, Tipo, Modelo, Plazas, Cantidad, Precio, Proveedor, Estado, Fecha);

//                ModelTableAlmacen c=new ModelTableAlmacen(Fecha);
                obs.add(c);
            }
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
        return obs;
    }


}