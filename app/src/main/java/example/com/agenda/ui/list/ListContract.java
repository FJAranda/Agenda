package example.com.agenda.ui.list;

import java.util.ArrayList;

import example.com.agenda.data.db.pojo.Contacto;

public interface ListContract {
    interface View{
        void showAgenda(ArrayList<Contacto> agenda);
    }

    interface Presenter{
        void loadAgenda();
    }

}
