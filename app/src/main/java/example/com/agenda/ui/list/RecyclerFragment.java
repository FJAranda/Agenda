package example.com.agenda.ui.list;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
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

import java.util.ArrayList;

import example.com.agenda.R;
import example.com.agenda.adapter.AgendaAdapter;
import example.com.agenda.data.db.pojo.Contacto;
import example.com.agenda.ui.AgendaApplication;
import example.com.agenda.ui.addedit.AddEditFragment;

public class RecyclerFragment extends Fragment implements ListContract.View {
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
                mListener.addNewContacto();
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
        adapter = new AgendaAdapter(agenda);
        rvAgenda.setAdapter(adapter);
    }

    public interface OnFragmentInteractionListener {
        void addNewContacto();
    }
}
