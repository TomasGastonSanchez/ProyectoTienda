package Entidades;
import jakarta.persistence.*;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Cambiado a IDENTITY
    private Long Id; // Cambiado a Long

    @Column
    private String Nombre;

    @Column
    private String Apellido;

    @Column
    private String Localidad;

    @Column
    private int Telefono;

    // Constructor con parámetros (opcional, si lo necesitas)
    public Cliente(String nombre, String apellido, String localidad, int telefono) {
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.Localidad = localidad;
        this.Telefono = telefono;
    }

    // Constructor por defecto
    public Cliente() {
    }

    // Getters
    public Long getId() { return Id; }
    public String getNombre() { return Nombre; }
    public String getApellido() { return Apellido; }
    public String getLocalidad() { return Localidad; }
    public int getTelefono() { return Telefono; }

    // Setters
    public void setId(Long id) { this.Id = id; } // Asignar correctamente
    public void setNombre(String nombre) { this.Nombre = nombre; } // Asignar correctamente
    public void setApellido(String apellido) { this.Apellido = apellido; } // Asignar correctamente
    public void setLocalidad(String localidad) { this.Localidad = localidad; } // Asignar correctamente
    public void setTelefono(int telefono) { this.Telefono = telefono; } // Asignar correctamente
}
