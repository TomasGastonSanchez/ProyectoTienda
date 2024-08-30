package Entidades;

import jakarta.persistence.*;

@Entity
public class Detalle_venta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int Id_detalle_venta;
    @Column
    private int Id_venta;
    @JoinColumn(name = "Id_producto")
    @ManyToOne
    private Producto Producto;
    @Column
    private int cantidad_producto;
    @Column
    private Double Precio ;

    public int GetId_detalle_venta(){ return Id_detalle_venta;}
    public void SetId_detalle_venta(){this.Id_detalle_venta=Id_detalle_venta;}

    public int GetId_venta(){ return Id_venta;}
    public void SetId_venta(){this.Id_venta=Id_venta;}

    public Producto GetProducto(){ return Producto;}
    public void SetProducto(){this.Producto=Producto;}

    public int GetCantidad_producto(){ return cantidad_producto;}
    public void SetCantidad_producto(){this.cantidad_producto=cantidad_producto;}

    public Double GetPrecio(){ return Precio;}
    public void SetPrecio(){this.Precio=Precio;}

    //@Id
    //private int Id_detalle_venta;
    //@Column
    //private int Id_venta;
    //@Column
    //private int id_producto;
    //@Column
    //private int cantidad_producto;
    //@Column
    //private Double Precio ;


    //@OneToOne
    //@JoinColumn(name = "book_id")
    //private Book book;

}
