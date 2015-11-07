package udacityproject.builditbiggerjavalibrarypractice;

import android.test.InstrumentationTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import udacityproject.jokeandroidlibrary.EndpointsAsyncTask;
import udacityproject.jokeandroidlibrary.EndpointsCompletionStatusListener;

/**
 * Created by hp on 11/7/2015.
 */
public class EndpointsAsyncTaskTest extends InstrumentationTestCase {

    private static boolean success;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        success = false;
    }

    public void testSuccessfulFetch() throws Throwable{

        final CountDownLatch signal = new CountDownLatch(1);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                new EndpointsAsyncTask(new EndpointsCompletionStatusListener() {
                    @Override
                    public void OnCompleted(String result) {


                        if (result != null) {
                            success = true;
                        }
                        signal.countDown();
                    }
                }).execute();
            }
        });

        signal.await(10, TimeUnit.SECONDS);
        assertTrue(success);

    }

}
