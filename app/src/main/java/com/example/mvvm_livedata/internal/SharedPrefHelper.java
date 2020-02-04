package com.example.mvvm_livedata.internal;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * class responsible for local storage.
 */
public class SharedPrefHelper
{

    private static final SharedPrefHelper         ourInstance = new SharedPrefHelper();
    private static       SharedPreferences        sharedPreferences;
    private              SharedPreferences.Editor edit;

    public SharedPrefHelper(Context context) {
        if (context == null) {
            return;
        }
        sharedPreferences = context.getSharedPreferences("xCash", Context.MODE_PRIVATE);
    }

    public SharedPrefHelper() {
    }

    public static SharedPrefHelper getInstance(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("xCash", Context.MODE_PRIVATE);
        }
        return ourInstance;
    }

    public static String[] getSHA256keys() {
        return new String[]{"sha256/IplSrchzJ7eWutgaMoIgpvp1BYcQr6Xlh7r15ZBTuDI=", "sha256" +
                "/klO23nT2ehFDXCfx3eHTDRESMz3asj1muO+4aIdjiuY=", "sha256" +
                "/grX4Ta9HpZx6tSHkmCrvpApTQGo67CYDnvprLg5yRME="};
    }


    public void setLanguage(String language) {
        edit = sharedPreferences.edit();
        edit.putString("language", language);
        edit.apply();
    }

    public void setLatitude(String latitude) {
        edit = sharedPreferences.edit();
        edit.putString("latitude", latitude);
        edit.apply();
    }

    public String getLatitude() {
        return sharedPreferences.getString("latitude", "13.7563");
    }

    public void setLongitude(String longitude) {
        edit = sharedPreferences.edit();
        edit.putString("longitude", longitude);
        edit.apply();
    }

    public String getLongitude() {
        return sharedPreferences.getString("longitude", "100.5018");
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return;
        }
        edit = sharedPreferences.edit();
        edit.putString("phoneNumber", phoneNumber);
        edit.apply();
    }

    public String getPhoneNumber() {
        String phoneNumber = sharedPreferences.getString("phoneNumber", "");
        return phoneNumber == null ? "" : phoneNumber;
    }

    public void setCountryCode(String CountryCode) {
        edit = sharedPreferences.edit();
        edit.putString("CountryCode", CountryCode);
        edit.apply();
    }

    public String getCountryCode() {
        return sharedPreferences.getString("CountryCode", "");
    }

    public void setEmail(String Email) {
        if (Email == null) {
            return;
        }
        edit = sharedPreferences.edit();
        edit.putString("Email", Email);
        edit.apply();
    }

    public String getEmail() {
        String email = sharedPreferences.getString("Email", "");
        return email == null ? "" : email;
    }

    public void setFirstName(String FirstName) {
        edit = sharedPreferences.edit();
        edit.putString("FirstName", FirstName);
        edit.apply();
    }

    public String getFirstName() {
        return sharedPreferences.getString("FirstName", "");
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    public void setLastName(String LastName) {
        edit = sharedPreferences.edit();
        edit.putString("LastName", LastName);
        edit.apply();
    }

    public String getLastName() {
        return sharedPreferences.getString("LastName", "");
    }

    public void setId(String id) {
        edit = sharedPreferences.edit();
        edit.putString("id", id);
        edit.apply();
    }

    public String getId() {
        return sharedPreferences.getString("id", "");
    }



    public boolean isLogInSkipped() {
        return getId().isEmpty();
    }


    public void setApiToken(String api_token) {
        edit = sharedPreferences.edit();
        edit.putString("api_token", api_token);
        edit.apply();
    }

    public String getApiToken() {
        return sharedPreferences.getString("api_token", "");
    }


    public void setEmailVerified(boolean emailVerified) {
        edit = sharedPreferences.edit();
        edit.putBoolean("EmailVerified", emailVerified);
        edit.apply();
    }

    public boolean isEmailVerified() {
        return sharedPreferences.getBoolean("EmailVerified", false);
    }

    public void setPhoneVerified(boolean phoneVerified) {
        edit = sharedPreferences.edit();
        edit.putBoolean("PhoneVerified", phoneVerified);
        edit.apply();
    }

    public boolean isPhoneVerified() {
        return sharedPreferences.getBoolean("PhoneVerified", false);
    }

    public void setisVibrate(boolean isVibrate) {
        edit = sharedPreferences.edit();
        edit.putBoolean("isVibrate", isVibrate);
        edit.apply();
    }

    public boolean isVibrate() {
        return sharedPreferences.getBoolean("isVibrate", true);
    }


    public void setFacebookId(String facebook_id) {
        edit = sharedPreferences.edit();
        edit.putString("facebook_id", facebook_id);
        edit.apply();
    }

    public String getFacebookId() {
        String facebook_id = sharedPreferences.getString("facebook_id", "");
        if (facebook_id.isEmpty()) {

        }
        return facebook_id;
    }


    public void setisFingerPrint(Boolean isFingerPrint) {
        edit = sharedPreferences.edit();
        edit.putBoolean("isFingerPrint", isFingerPrint);
        edit.apply();
    }

    public boolean isFingerPrint() {
        return sharedPreferences.getBoolean("isFingerPrint", true);
    }

    public void setGender(String Gender) {
        edit = sharedPreferences.edit();
        edit.putString("Gender", Gender);
        edit.apply();
    }

    public void setAge(String Age) {
        edit = sharedPreferences.edit();
        edit.putString("Age", Age);
        edit.apply();
    }

    public String getAge() {
        return sharedPreferences.getString("Age", "--");
    }

    public void setProfileImage(String ProfileImage) {
        edit = sharedPreferences.edit();
        edit.putString("ProfileImage", ProfileImage);
        edit.apply();
    }

    public String getProfileImage() {
        return sharedPreferences.getString("ProfileImage", "");
    }

    public String getDOB() {
        return sharedPreferences.getString("dob", "");
    }

    public void setDOB(String dob) {
        edit = sharedPreferences.edit();
        edit.putString("dob", dob);
        edit.apply();
    }
}
