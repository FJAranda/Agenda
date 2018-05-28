package example.com.agenda;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Comprobacion de que el layout esta listo
        if (findViewById(R.id.flMainActivity) != null) {
            if (savedInstanceState != null) {
                return;
            }

            ListFragment listFragment = new ListFragment();

            listFragment.setArguments(getIntent().getExtras());


            getSupportFragmentManager().beginTransaction()
                    .add(R.id.flMainActivity, listFragment).commit();
        }
    }
}
