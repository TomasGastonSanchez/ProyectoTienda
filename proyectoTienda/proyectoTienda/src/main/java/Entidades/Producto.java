
package Entidades;

import jakarta.persistence.*;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  int Id_producto;
    @Column
    private String Nombre_producto;
    @Column
    private  Double Precio;
    @Column
    private int Stock;
    @Column
    private String Proveedor;

    public int GetId() { return Id_producto;}
    public String GetNombre_producto() { return Nombre_producto;}
    public Double GetPrecio(){ return Precio;}
    public int GetStock() { return Stock;}
    public String GetProveedor() { return Proveedor;}

    public void SetId() { this.Id_producto=Id_producto; }
    public void SetNombre_producto(){ this.Nombre_producto= Nombre_producto; }
    public void SetPrecio() { this.Precio= Precio; }
    public void SetStock(){ this.Stock = Stock; }
    public void SetProveedor(){ this.Proveedor= Proveedor; }

}


