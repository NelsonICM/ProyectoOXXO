package modelos;

public class Sucursal {
    private String id;
    private String nombre;

    public Sucursal(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Sucursal{id='" + id + "', nombre='" + nombre + "'}";
    }
}
