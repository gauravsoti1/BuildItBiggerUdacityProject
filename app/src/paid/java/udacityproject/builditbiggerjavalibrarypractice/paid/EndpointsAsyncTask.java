package udacityproject.builditbiggerjavalibrarypractice.paid;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.hp.mysamplejokesapplication.jokesbackend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

import udacityproject.jokeandroidlibrary.JokeAndroidLibraryActivity;

/**
 * Created by hp on 11/2/2015.
 */
class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private ProgressDialog progress;

    public EndpointsAsyncTask(Context context){
        setContext(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    public void setContext(Context context) {
        this.context = context;
        progress = ProgressDialog.show(context,"Getting joke of the day!",null,true,true);
    }

    @Override
    protected String doInBackground(Void... params) {
        if (myApiService == null) {  // Only do this once
            //Used in case of debugging with an emulator
            /*MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });*/
            // end options for devappserver

            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://oval-terrain-111620.appspot.com/_ah/api/");

            myApiService = builder.build();
        }

       /* context = params[0].first;
        String name = params[0].second;*/

        try {
            return myApiService.tellAJoke(1).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        Intent startNewActivity = new Intent(context, JokeAndroidLibraryActivity.class);
        startNewActivity.putExtra("joke", result);
        context.startActivity(startNewActivity);
        progress.dismiss();
    }
}
