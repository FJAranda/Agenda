package example.com.agenda.ui.list;

import java.util.ArrayList;

import example.com.agenda.data.db.pojo.Contacto;

public class ListPresenter implements ListContract.Presenter, ListInteractor.onLoadListener{
    private ListContract.View view;
    private ListInteractor interactor;

    public ListPresenter(ListContract.View view){
        this.view = view;
        this.interactor = new ListInteractorImpl(this);
    }

    @Override
    public void loadAgenda() {
        interactor.getContactos();
    }

    @Override
    public void onDatabaseError() {

    }

    @Override
    public void onSuccess(ArrayList<Contacto> agenda) {
        view.showAgenda(agenda);
    }
}
