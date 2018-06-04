package example.com.agenda.ui.addedit;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import example.com.agenda.R;
import example.com.agenda.ui.AgendaApplication;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddEditFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddEditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddEditFragment extends Fragment implements AddEditContract.View{

    private OnFragmentInteractionListener mListener;
    private FloatingActionButton fabGuardarContacto;
    private AddEditContract.Presenter presenter;
    private TextInputLayout tilNombreContacto;
    private TextInputLayout tilTelefonoContacto;
    private TextInputLayout tilFechaNac;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");



    public static AddEditFragment newInstance(Bundle bundle) {
        AddEditFragment fragment = new AddEditFragment();
        if (bundle != null){
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new AddEditPresenter(this);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add_edit, container, false);
        fabGuardarContacto = root.findViewById(R.id.fabGuardarContacto);
        tilNombreContacto = root.findViewById(R.id.tilNombreContacto);
        tilTelefonoContacto = root.findViewById(R.id.tilTelefonoContacto);
        tilFechaNac = root.findViewById(R.id.tilFechaNac);
        fabGuardarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    presenter.addContacto(tilNombreContacto.getEditText().getText().toString(), tilTelefonoContacto.getEditText().getText().toString(),
                            sdf.parse(tilFechaNac.getEditText().getText().toString()));
                    } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        return root;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().getLocalClassName() + " must implements ListDepedencyListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void navigateToList() {
        Toast.makeText(AgendaApplication.getContext(), "Insertado con exito", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNameEmptyError() {
        Toast.makeText(AgendaApplication.getContext(), "El nombre no puede estar vacio", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTelefonoEmptyError() {
        Toast.makeText(AgendaApplication.getContext(), "El telefono no puede estar vacio", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFechaNacInvalidError() {
        Toast.makeText(AgendaApplication.getContext(), "La fecha de nacimiento no es valida", Toast.LENGTH_LONG).show();
    }

    @Override
    public void validateContactError() {
        Toast.makeText(AgendaApplication.getContext(), "Error al comprobar los datos del contacto", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDatabaseError(Error error) {
        Toast.makeText(AgendaApplication.getContext(), "Error al insertar en la base de datos", Toast.LENGTH_LONG).show();
    }

    public interface OnFragmentInteractionListener {
        void showRecyclerFragment();
    }
}
