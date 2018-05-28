package example.com.agenda.data.db.repository;

import java.util.ArrayList;

import example.com.agenda.data.db.dao.AgendaDAO;
import example.com.agenda.data.db.pojo.Contacto;

public class ContactosRepository {

    private static ContactosRepository repository;
    private AgendaDAO dao;

    private ContactosRepository(){
        this.dao = new AgendaDAO();
    }

    public static ContactosRepository getInstance(){
        if (repository == null){
            repository = new ContactosRepository();
        }
        return repository;
    }

    public ArrayList<Contacto> getContactos(){
        return dao.loadAll();
    }

    /*public long addContacto(String nombre, String numero, String fecha){
        return dao.addContacto(nombre, numero, fecha);
    }*/

}
