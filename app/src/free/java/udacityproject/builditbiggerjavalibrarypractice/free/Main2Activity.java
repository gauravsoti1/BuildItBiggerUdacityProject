package udacityproject.builditbiggerjavalibrarypractice.free;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import udacityproject.builditbiggerjavalibrarypractice.R;
import udacityproject.jokeandroidlibrary.JokeAndroidLibraryActivity;

public class Main2Activity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.banner_ad_unit_id));
        final String device_id = Main2Activity.this.getResources().getString(R.string.test_device_id);
        Log.d("Device id = ", device_id);
        requestNewInterstitial(Main2Activity.this.getResources().getString(R.string.test_device_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {


            }

            @Override
            public void onAdClosed() {
                // this is loaded onAdClosed because once it is closed the ad is removed,
                // so we are making sure that when the user presses back and comes to this there is an add to show
                requestNewInterstitial(device_id);
                //new EndpointsAsyncTask(Main2Activity.this).execute();
                startActivity(new Intent(Main2Activity.this, JokeAndroidLibraryActivity.class));
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                Log.e("Add Failed To Load", "Error Code = " + errorCode);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                //String joke = new JokeWizard().getJoke();
                /*if(mInterstitialAd.isLoaded()){*/
                mInterstitialAd.show();
                /*}
                else{
                    new EndpointsAsyncTask(Main2Activity.this).execute();
                }*/

            }
        });
    }

    private void requestNewInterstitial(String deviceId) {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(deviceId)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

}
