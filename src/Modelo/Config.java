package Modelo;

public class Config {
    private int id;
    private String nombre;
    private String ruc;
    private int telefono;
    private String direccion;
    private String razon;

    public Config() {
    }

    public Config(int id, String nombre, String ruc, int telefono, String direccion, String razon) {
        this.id = id;
        this.nombre = nombre;
        this.ruc = ruc;
        this.telefono = telefono;
        this.direccion = direccion;
        this.razon = razon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRucr() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }
}//FIN DE LA CLASE CONFIG
