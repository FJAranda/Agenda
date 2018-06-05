package example.com.agenda.ui.addedit;

import android.widget.Toast;

import java.util.Date;

import example.com.agenda.data.db.pojo.Contacto;

public class AddEditPresenter implements AddEditContract.Presenter, AddEditInteractor.onAddEditFinishedListener{
    private AddEditContract.View view;
    private AddEditInteractor interactor;

    public AddEditPresenter(AddEditContract.View view){
        this.view = view;
        this.interactor = new AddEditInteractorImpl(this);
    }

    @Override
    public void addContacto(String nombre, String telefono, Date fecha) {
        interactor.validateContacto(nombre, telefono, fecha, this);
    }

    @Override
    public void editContacto(Contacto contacto) {
        interactor.editContacto(contacto, this);
    }

    @Override
    public void onNameEmptyError() {
        view.onNameEmptyError();
    }

    @Override
    public void onTelefonoEmptyError() {
        view.onTelefonoEmptyError();
    }

    @Override
    public void onFechaNacInvalidError() {
        view.onFechaNacInvalidError();
    }

    @Override
    public void onSuccess() {
        view.navigateToList();
    }

    @Override
    public void onDatabaseError(Error error) {
        view.onDatabaseError(error);
    }
}
