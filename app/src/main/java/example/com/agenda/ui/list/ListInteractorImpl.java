package example.com.agenda.ui.list;

import java.util.ArrayList;

import example.com.agenda.data.db.pojo.Contacto;
import example.com.agenda.data.db.repository.ContactosRepository;

public class ListInteractorImpl implements ListInteractor{
    private ListInteractor.onLoadListener listener;

    public ListInteractorImpl(ListInteractor.onLoadListener listener){
        this.listener = listener;
    }

    @Override
    public void getContactos() {
        ArrayList<Contacto> agenda = ContactosRepository.getInstance().getContactos();
        listener.onSuccess(agenda);
    }

    @Override
    public void deleteContacto(Contacto contacto) {
        ContactosRepository.getInstance().deleteContacto(contacto);
        listener.onSuccess();
    }
}
