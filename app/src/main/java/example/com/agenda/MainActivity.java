package example.com.agenda;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.os.Bundle;

import example.com.agenda.ui.list.RecyclerFragment;

public class MainActivity extends FragmentActivity implements RecyclerFragment.OnFragmentInteractionListener{
    private RecyclerFragment listFragment;

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
}
