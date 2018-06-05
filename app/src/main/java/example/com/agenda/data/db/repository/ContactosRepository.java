package example.com.agenda.data.db.repository;

import java.util.ArrayList;

import example.com.agenda.data.db.dao.AgendaDAO;
import example.com.agenda.data.db.pojo.Contacto;
import example.com.agenda.ui.addedit.AddEditInteractor;

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

    public void addContacto(Contacto contacto, AddEditInteractor interactor) {
        long id = dao.add(contacto);
        Error error = new Error();

        if (id == -1){
            interactor.onError(error);
        }
    }

    public void updateContacto(Contacto contacto){
        dao.updateContacto(contacto);
    }

    public void deleteContacto(Contacto contacto) {
        dao.deleteContacto(contacto);

    }
}
