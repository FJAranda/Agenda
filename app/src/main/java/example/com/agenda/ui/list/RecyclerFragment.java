package example.com.agenda.ui.list;

import android.app.AlertDialog;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import example.com.agenda.MainActivity;
import example.com.agenda.R;
import example.com.agenda.adapter.AgendaAdapter;
import example.com.agenda.data.db.pojo.Contacto;
import example.com.agenda.ui.AgendaApplication;
import example.com.agenda.ui.addedit.AddEditFragment;

public class RecyclerFragment extends Fragment implements ListContract.View, AgendaAdapter.OnItemClickListener, AgendaAdapter.OnItemLongClickListener{
    public static final String TAG = "listagenda";

    private ListContract.Presenter presenter;
    private AgendaAdapter adapter;
    private FloatingActionButton fabRecyclerActivity;
    private RecyclerView rvAgenda;

    private OnFragmentInteractionListener mListener;

    public static RecyclerFragment newInstance(Bundle bundle) {
        RecyclerFragment fragment = new RecyclerFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Añadidos para menu y fragments
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_list, container, false);
        rvAgenda = root.findViewById(R.id.rvListFragment);
        rvAgenda.setLayoutManager(new LinearLayoutManager(AgendaApplication.getContext()));
        //adapter = new AgendaAdapter();
        presenter = new ListPresenter(this);
        fabRecyclerActivity = root.findViewById(R.id.fabMainActivity);
        fabRecyclerActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.addNewContacto(null);
            }
        });
        return root;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.loadAgenda();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showAgenda(ArrayList<Contacto> agenda) {
        adapter = new AgendaAdapter(agenda, this, this);
        rvAgenda.setAdapter(adapter);
    }

    @Override
    public void showDeleteMessage() {
        Toast.makeText(AgendaApplication.getContext(), "Contacto eliminado con exito", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(Contacto contacto) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putParcelable(AddEditFragment.KEY_EDIT, contacto);
        AddEditFragment addEditFragment = AddEditFragment.newInstance(bundle);
        transaction.addToBackStack(null);
        transaction.replace(R.id.flMainActivity, addEditFragment);
        transaction.commit();
    }

    @Override
    public void onItemLongClick(final Contacto contacto) {
        String[] items = {"borrar"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Elige la opcion: ");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                presenter.deleteContacto(contacto);
            }
        });
        builder.show();
    }

    public interface OnFragmentInteractionListener {
        void addNewContacto(Bundle bundle);
    }
}
