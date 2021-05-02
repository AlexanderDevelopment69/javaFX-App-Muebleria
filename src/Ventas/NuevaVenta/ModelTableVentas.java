package Ventas.NuevaVenta;

import Almacen.AgregarProducto.ModelTableProducto;
import Almacen.Fabricacion.ModelTableAlmacen;
import ConnectionMySQL.ConnectionMYSQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;


public class ModelTableVentas {
PreparedStatement pst;
//    String Cliente;

    String Codigo;
    String Producto;
    int Cantidad;
//    String Vendedor;
    double Descuento;
    double Importe;

    String Fechas;


    double Precio;
    String Marca;
    String Modelo;
    String Plazas;

    //AGREGA A LA BASE DE DATOS LA NUEVA VENTA
    int Venta;



    String Nombre;
    String IdCliente;
    double Total;
    Timestamp Fecha;


//Constructor de la tabla 2
    public ModelTableVentas(int venta, String idCliente, String nombre, double total, double descuento, Timestamp fecha) {
    Venta =venta;
    IdCliente=idCliente;
    Nombre=nombre;
    Total=total;
    Descuento=descuento;
    Fecha=fecha;

    }


    public ModelTableVentas(String producto, int cantidad) {
    Producto=producto;
    Cantidad=cantidad;
    }

//Contructor de detalleventas
    public ModelTableVentas(int venta, String producto, int cantidad, double precio, double descuento, double importe) {
        Venta=venta;
        Producto=producto;
        Cantidad=cantidad;
        Precio=precio;
        Descuento=descuento;
        Importe=importe;
    }



    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(String idCliente) {
        IdCliente = idCliente;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public Timestamp getFecha() {
        return Fecha;
    }

    public void setFecha(Timestamp fecha) {
        Fecha = fecha;
    }

    public ModelTableVentas(String codigo , String producto, String marca, String modelo, String plazas, int cantidad, double precio, double descuento, double importe, String fechas) {
        Codigo=codigo;
        Producto = producto;
        Marca=marca;
        Modelo=modelo;
        Plazas=plazas;
        Cantidad = cantidad;
        Descuento = descuento;
        Precio=precio;
        Importe=importe;
        Fechas=fechas;
    }
    //Obtener Codigo de producto
    public ModelTableVentas() {

    }

    public ModelTableVentas(String codigo) {
        Codigo=codigo;
    }

    //Devuelve valores a la tabla ventas
//    public ModelTableVentas(double precio, String marca, String modelo, String plazas) {
//        Precio = precio;
//        Marca = marca;
//        Modelo = modelo;
//        Plazas = plazas;
//    }


//
//    public String getCliente() {
//        return Cliente;
//    }
//
//    public void setCliente(String cliente) {
//        Cliente = cliente;
//    }
//    public String getVendedor() {
//        return Vendedor;
//    }
//
//    public void setVendedor(String vendedor) {
//        Vendedor = vendedor;
//    }


    public String getFechas() {
        return Fechas;
    }

    public void setFechas(String fechas) {
        Fechas = fechas;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getProducto() {
        return Producto;
    }

    public void setProducto(String producto) {
        Producto = producto;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }



    public double getDescuento() {
        return Descuento;
    }

    public void setDescuento(double descuento) {
        Descuento = descuento;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double precio) {
        Precio = precio;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
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

    public double getImporte() {
        Importe=(Cantidad*Precio)-Descuento;
        return Importe;
    }

    public void setImporte(double importe) {
        Importe = importe;
    }

    public int getVenta() {
        return Venta;
    }

    public void setVenta(int venta) {
        Venta = venta;
    }

    //Obtener informe de ventas a la tabla 2
    public ObservableList<ModelTableVentas> getVentasTable() throws ClassNotFoundException {
        ObservableList<ModelTableVentas> obs = FXCollections.observableArrayList();

        try {

            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst= connection.prepareStatement("(select venta.idVenta,venta.idCliente,usuario.nombre,venta.total,venta.descuento,venta.fecha from venta inner join usuario on usuario.dni=venta.idVendedor order by idVenta desc limit 6 ) order by idVenta asc");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                Venta=rs.getInt("idVenta");
                IdCliente=rs.getString("idCliente");
                Nombre=rs.getString("nombre");
                Total=rs.getDouble("total");
                Descuento=rs.getDouble("descuento");
                Fecha=rs.getTimestamp("fecha");

                ModelTableVentas c=new ModelTableVentas(Venta,IdCliente,Nombre,Total,Descuento,Fecha);
                obs.add(c);
            }
            connection.close();
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
        return obs;
    }



    //Obtener informe de ventas a la tabla del dashboard PRINCIPAL
    public ObservableList<ModelTableVentas> getVentas() throws ClassNotFoundException {
        ObservableList<ModelTableVentas> obs = FXCollections.observableArrayList();

        try {

            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst= connection.prepareStatement("select venta.idVenta,venta.idCliente,usuario.nombre,venta.total,venta.descuento,venta.fecha from venta inner join usuario on usuario.dni=venta.idVendedor  order by idVenta asc");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                Venta=rs.getInt("idVenta");
                IdCliente=rs.getString("idCliente");
                Nombre=rs.getString("nombre");
                Total=rs.getDouble("total");
                Descuento=rs.getDouble("descuento");
                Fecha=rs.getTimestamp("fecha");

                ModelTableVentas c=new ModelTableVentas(Venta,IdCliente,Nombre,Total,Descuento,Fecha);
                obs.add(c);
            }
            connection.close();
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
        return obs;
    }

    //Obtener informe de detalle de ventas a la tabla del dashboard
    public ObservableList<ModelTableVentas> getDetalleVenta() throws ClassNotFoundException {
        ObservableList<ModelTableVentas> obs = FXCollections.observableArrayList();

        try {

            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst= connection.prepareStatement("select detalleventa.idVenta,detalleventa.producto,detalleventa.cantidad,detalleventa.precioVenta,detalleventa.descuento,detalleventa.importe from detalleventa  order by idVenta asc");
//            pst= connection.prepareStatement("select venta.idVenta,venta.idCliente,usuario.nombre,venta.total,venta.descuento,DATE_FORMAT(venta.fecha,'%d/%m/%Y %H:%i:%s') as fechaConvertida from venta inner join usuario on usuario.dni=venta.idVendedor  order by idVenta asc");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                Venta=rs.getInt("idVenta");
                Producto=rs.getString("producto");
                Cantidad=rs.getInt("cantidad");
                Precio=rs.getDouble("precioVenta");
                Descuento=rs.getDouble("descuento");
                Importe=rs.getInt("importe");

                ModelTableVentas c=new ModelTableVentas(Venta,Producto,Cantidad,Precio,Descuento,Importe);
                obs.add(c);

            }
            connection.close();
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
        return obs;
    }

    public ObservableList<ModelTableVentas> getBuscadors (String valor) throws ClassNotFoundException {

        ObservableList<ModelTableVentas> obs = FXCollections.observableArrayList();

        try {

            ConnectionMYSQL ConnectionClass = new ConnectionMYSQL();
            Connection connection = ConnectionClass.getConnection();
            pst = connection.prepareStatement("select * from venta inner join usuario on usuario.dni=venta.idVendedor where idVenta like '%"+valor+"%' or nombre like '%"+valor+"%' or idCliente like '%"+valor+"%' order by idVenta asc");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                Venta=rs.getInt("idVenta");
                IdCliente=rs.getString("idCliente");
                Nombre=rs.getString("nombre");
                Total=rs.getDouble("total");
                Descuento=rs.getDouble("descuento");
                Fecha=rs.getTimestamp("fecha");

                ModelTableVentas c=new ModelTableVentas(Venta,IdCliente,Nombre,Total,Descuento,Fecha);

//                ModelTableAlmacen c=new ModelTableAlmacen(Fecha);
                obs.add(c);
            }
            connection.close();
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
        return obs;
    }


}


