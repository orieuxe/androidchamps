package android.eservices.pogchamps;

import android.app.Application;
import android.eservices.pogchamps.data.di.FakeDependencyInjection;

import com.facebook.stetho.Stetho;

public class PogApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        FakeDependencyInjection.setContext(this);
    }
}
