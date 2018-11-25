package planit.sinha.ankur.com.planit;

/**
 * Created by ankur sinha on 25-11-2018.
 */

import android.app.Application;

import planit.sinha.ankur.com.planit.AppExecutors;

/**
 * Android Application class. Used for accessing singletons.
 */
public class PlanItApp extends Application {

    private AppExecutors mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppExecutors = new AppExecutors();
    }

}
