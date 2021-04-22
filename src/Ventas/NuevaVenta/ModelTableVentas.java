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
public ModelTableVentas(int venta,String nombre,String idcliente,double descuento,double total,Timestamp fecha){
    Venta=venta;
    Nombre=nombre;
    IdCliente=idcliente;
    Descuento = descuento;
    Total=total;
    Fecha=fecha;

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
            pst= connection.prepareStatement("(select venta.idVenta,venta.idCliente,usuario.nombre,venta.total,venta.descuento,venta.fecha from venta inner join usuario on usuario.nombre=venta.idVendedor order by idVenta desc limit 6 ) order by idVenta asc");

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
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
        return obs;
    }



}


