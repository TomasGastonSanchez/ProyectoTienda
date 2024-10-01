package Entidades;
import jakarta.persistence.*;

@Entity
public class Vendedor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int Id ;
    @Column
    private String Nombre;
    @Column
    private String Apellido ;
    @Column
    private String Localidad;
    @Column
    private int Telefono;

    public int GetId(){ return Id;}
    public String GetNombre(){ return Nombre;}
    public String GetApellido(){ return Apellido;}
    public String GetLocalidad(){ return Localidad;}
    public int GetTelefono(){ return Telefono;}

    public void SetId(){this.Id=Id;}
    public void SetNombre(String s){this.Nombre= Nombre; }
    public void SetApellido(String s){this.Apellido= Apellido; }
    public void SetLocalidad(String s) {this.Localidad= Localidad;}
    public void SetTelefono(int i){ this.Telefono = Telefono;}
}
