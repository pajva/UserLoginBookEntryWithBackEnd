package com.example.testapp.app;

import android.app.Application;

import com.example.testapp.entity.response.ApiClient;
import com.example.testapp.entity.response.ServiceGenerator;
import com.example.testapp.preferance.AppPreference;


/**
 *
 * @author dev.Cobb
 * @version 1.0
 * @since 8 oct 2016
 *
 * The Class MyApp.
*/
public class MyApp extends Application {

    /**
     * The Constant TAG.
     */
    public static final String TAG = MyApp.class.getSimpleName();
    private static AppPreference appPreference = null;
    private static ApiClient apiClient = null;

    /**
     * The application instance.
     */
    private static MyApp instance = null;


    /*
     * (non-Javadoc)
     *
     * @see android.app.Application#onCreate()
     */
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();

        instance = this;

    }

    /**
     * Gets the context.
     *
     * @return the context
     */
    public static synchronized MyApp getContext() {
        if (instance == null) {
            synchronized (MyApp.class) {
                if (instance == null) {
                    instance = new MyApp();
                }
            }
        }
        return instance;
    }
    public AppPreference getPreference() {
        return appPreference;
    }
    public static ApiClient getApiClient() {
        if(apiClient==null)
        {
            apiClient= ServiceGenerator.createService(ApiClient.class);
        }
        return apiClient;
    }



}

