package example.com.agenda.ui.addedit;

import java.util.Calendar;
import java.util.Date;

import example.com.agenda.data.db.DBAgendaContract;
import example.com.agenda.data.db.pojo.Contacto;
import example.com.agenda.data.db.repository.ContactosRepository;

public class AddEditInteractorImpl implements AddEditInteractor{

    AddEditInteractor.onAddEditFinishedListener listener;


    public AddEditInteractorImpl(onAddEditFinishedListener listener) {
        listener = listener;
    }


    @Override
    public void addContacto(String nombre, String telefono, Date fechaNac) {

    }

    @Override
    public void editContacto(Contacto contacto, onAddEditFinishedListener listener) {
        ContactosRepository.getInstance().updateContacto(contacto);
        listener.onSuccess();
    }

    @Override
    public void validateContacto(String nombre, String telefono, Date fechaNac, onAddEditFinishedListener listener) {
        if (nombre.isEmpty()){
            listener.onNameEmptyError();
        }else if(telefono.isEmpty()){
            listener.onTelefonoEmptyError();
        }else if(fechaNac.after(Calendar.getInstance().getTime())){
            listener.onFechaNacInvalidError();
        }else {
            Contacto contacto = new Contacto(nombre,fechaNac,telefono);
            ContactosRepository.getInstance().addContacto(contacto, this);
            listener.onSuccess();
        }

    }

    @Override
    public void onError(Error error) {
        listener.onDatabaseError(error);
    }


}
