package example.com.agenda.data.db.pojo;

import java.util.Date;

public class Contacto {

    private int _ID;
    private String nombre;
    private Date fecha;
    private String telefono;

    public int get_ID() {
        return _ID;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Contacto(int id, String nombre, Date fecha, String telefono) {
        this._ID = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.telefono = telefono;
    }
}
