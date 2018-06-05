package example.com.agenda.data.db.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Contacto implements Parcelable {

    private int _ID;
    private String nombre;
    private Date fecha;
    private String telefono;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    protected Contacto(Parcel in) {
        _ID = in.readInt();
        nombre = in.readString();
        telefono = in.readString();
        try {
            fecha = sdf.parse(in.readString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static final Creator<Contacto> CREATOR = new Creator<Contacto>() {
        @Override
        public Contacto createFromParcel(Parcel in) {
            return new Contacto(in);
        }

        @Override
        public Contacto[] newArray(int size) {
            return new Contacto[size];
        }
    };

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

    public Contacto(String nombre, Date fecha, String telefono) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.telefono = telefono;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(_ID);
        parcel.writeString(nombre);
        parcel.writeString(telefono);
        parcel.writeString(sdf.format(fecha));
    }
}
