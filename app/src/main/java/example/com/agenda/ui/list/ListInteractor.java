package example.com.agenda.ui.list;

import java.util.ArrayList;

import example.com.agenda.data.db.pojo.Contacto;

public interface ListInteractor {
    void getContactos();

    void deleteContacto(Contacto contacto);

    interface onLoadListener{
        void onDatabaseError();
        void onSuccess(ArrayList<Contacto> agenda);
        void onSuccess();
    }
}
