package example.com.agenda.ui.addedit;

import java.util.Date;

import example.com.agenda.data.db.pojo.Contacto;

public interface AddEditContract {
    interface View{
        void navigateToList();
        void onNameEmptyError();
        void onTelefonoEmptyError();
        void onFechaNacInvalidError();
        void validateContactError();
        void onDatabaseError(Error error);
    }

    interface Presenter{
        void addContacto(String nombre, String telefono, Date fecha);
        void editContacto(Contacto contacto);
    }

}
