package example.com.agenda.ui.list;

import java.util.ArrayList;

import example.com.agenda.data.db.pojo.Contacto;

public interface ListContract {
    interface View{
        void showAgenda(ArrayList<Contacto> agenda);

        void showDeleteMessage();
    }

    interface Presenter{
        void loadAgenda();

        void deleteContacto(Contacto contacto);
    }

}
