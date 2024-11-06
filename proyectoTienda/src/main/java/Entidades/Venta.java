package Entidades;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int Id_venta;
    @Column
    private Date fecha;
    @Column
    private int total;
    @JoinColumn(name = "Id_detalle_venta")
    @OneToMany
    private List<Detalle_venta> Detalle;

    public int GetId(){ return Id_venta;}
    public void SetId(){this.Id_venta=Id_venta;}

    public Date GetFecha(){ return fecha;}
    public void SetFecha(){this.fecha=fecha;}

    public int Gettotal(){ return total;}
    public void Settotal(){this.total=total;}

    public  List<Detalle_venta> GetDetalle(){ return Detalle;}
    public void SetDetalle(){this. Detalle=Detalle;}
    //@JoinColumn(name = "book_id")
    //private Book book;

}
