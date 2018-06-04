package example.com.agenda.ui.addedit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import example.com.agenda.R;
import example.com.agenda.ui.AgendaApplication;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddEditFragment extends Fragment implements AddEditContract.View{
    public static final String TAG = "addedit";

    public AddEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_edit, container, false);
    }

    @Override
    public void navigateToList() {

    }

    @Override
    public void onNameEmptyError() {
        Toast.makeText(AgendaApplication.getContext(),"El nombre no puede estar vacio", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTelefonoEmptyError() {
        Toast.makeText(AgendaApplication.getContext(),"El telefono no puede estar vacio", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFechaNacInvalidError() {
        Toast.makeText(AgendaApplication.getContext(),"La fecha de nacimiento es posterior a la actual", Toast.LENGTH_LONG).show();
    }

    @Override
    public void validateContactError() {
        Toast.makeText(AgendaApplication.getContext(),"Hubo algun fallo al crear el contacto", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDatabaseError(Error error) {
        Toast.makeText(AgendaApplication.getContext(),"Fallo con la base de datos", Toast.LENGTH_LONG).show();
    }
}
