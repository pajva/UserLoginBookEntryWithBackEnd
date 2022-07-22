package com.example.testapp.data;


import com.example.testapp.entity.response.ApiClient;
import com.example.testapp.entity.response.ServiceGenerator;
import com.example.testapp.preferance.AppPreference;


public class DataProcessController {
    private static volatile com.example.testapp.data.DataProcessController instance = null;
    private final AppPreference appPreference = new AppPreference();
    private ApiClient apiClient = null;




    public DataProcessController() {
        apiClient = ServiceGenerator.createService(ApiClient.class);
    }

    public static com.example.testapp.data.DataProcessController getDataProcessController() {
        if (instance == null) {
            // double check
            synchronized (com.example.testapp.data.DataProcessController.class) {
                if (instance == null) {
                    instance = new com.example.testapp.data.DataProcessController();
                }
            }
        }
        return instance;
    }
    public AppPreference getPreference() {
        return appPreference;
    }
    public ApiClient getApiClient() {
        return apiClient;
    }



}
