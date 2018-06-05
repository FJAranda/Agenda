package example.com.agenda;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import example.com.agenda.ui.addedit.AddEditFragment;
import example.com.agenda.ui.list.RecyclerFragment;

public class MainActivity extends FragmentActivity implements RecyclerFragment.OnFragmentInteractionListener, AddEditFragment.OnFragmentInteractionListener{
    private RecyclerFragment listFragment;
    private AddEditFragment addEditFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listFragment = (RecyclerFragment) getSupportFragmentManager().findFragmentByTag(RecyclerFragment.TAG);

        //Comprobacion de que el layout esta listo
        if (findViewById(R.id.flMainActivity) != null) {
            if (listFragment == null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                listFragment = RecyclerFragment.newInstance(null);
                transaction.add(R.id.flMainActivity, listFragment, RecyclerFragment.TAG).commit();
            }
        }

    }

    @Override
    public void addNewContacto(Bundle bundle) {
        addEditFragment = (AddEditFragment)getSupportFragmentManager().findFragmentByTag(AddEditFragment.TAG);

        if (addEditFragment == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            addEditFragment = AddEditFragment.newInstance(bundle);
            transaction.addToBackStack(null);
            transaction.replace(R.id.flMainActivity, addEditFragment);
            transaction.commit();
        }
    }

    @Override
    public void showRecyclerFragment() {
        //Actualizar recyclerview al añadir item
    }
}
