package co.kruzr.bernoulli.app;

import android.app.Application;

import co.kruzr.bernoulli.Bernoulli;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        /*
          Needs to be called from the application class.
         */
        Bernoulli.startFlowing(this);
    }
}
