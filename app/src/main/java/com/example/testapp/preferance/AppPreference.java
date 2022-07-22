package com.example.testapp.preferance;

import com.example.testapp.constants.Constant;

public class AppPreference {
    private  final PreferenceWrapper pWrapper=new PreferenceWrapper();

    public void saveUserEmailID(String email){
        pWrapper.putStringPref(Constant.PREF_EMAIL,email);
    }
    public  String getUserEmailID(String email){
        return pWrapper.getStringPref(Constant.PREF_EMAIL,null);
    }
    public void saveUserPassword(String password){
        pWrapper.putStringPref(Constant.PREF_EMAIL,password);
    }
    public  String getUserPassword(String password){
        return pWrapper.getStringPref(Constant.PREF_PASSWORD,null);
    }
}
