package example.com.agenda.ui.addedit;

import java.util.Date;

import example.com.agenda.data.db.pojo.Contacto;

public interface AddEditInteractor {
    void addContacto(String nombre, String telefono, Date fechaNac);
    void editContacto(Contacto contacto, onAddEditFinishedListener listener);
    void validateContacto(String nombre, String telefono, Date fechaNac, onAddEditFinishedListener listener);
    void onError(Error error);

    interface onAddEditFinishedListener{
        void onNameEmptyError();
        void onTelefonoEmptyError();
        void onFechaNacInvalidError();
        void onSuccess();
        void onDatabaseError(Error error);
    }
}
