package udacityproject.jokeandroidlibrary;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by hp on 11/2/2015.
 */
public class JokeAndroidLibraryActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joke_android_activity);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        if (savedInstanceState == null) {

            /*Bundle b = new Bundle();
            b.putString("joke", getIntent().getStringExtra("joke"));*/
            PlaceHolderFragment frag = new PlaceHolderFragment();
            /*frag.setArguments(b);*/
            getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, frag).commit();

        }
    }


    static public class PlaceHolderFragment extends Fragment implements EndpointsCompletionStatusListener{

        private TextView mShowJokeText;
        private ProgressDialog progress;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.joke_android_activity_fragment, container, false);
            mShowJokeText = (TextView) v.findViewById(R.id.showJoke);

            /*mShowJokeText.setText(getArguments().getString("joke"));*/

            return v;


        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            progress = ProgressDialog.show(getActivity(), "Getting joke of the day!", null, true, true);
            new EndpointsAsyncTask(PlaceHolderFragment.this).execute();
        }

        @Override
        public void OnCompleted(String result) {
            progress.dismiss();
            mShowJokeText.setText(result);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
