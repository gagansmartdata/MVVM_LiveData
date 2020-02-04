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


//=======================referral code and link====================================================
    public void setReferralInviteCode(String inviteLink) {
        edit = sharedPreferences.edit();
        edit.putString("InviteCode", inviteLink);
        edit.apply();
    }

    public String getReferralCode() {
        return sharedPreferences.getString("InviteCode", "");
    }

    public String getReferralInviteLink() {
        return sharedPreferences.getString("InviteLink", "");
    }

 public void setReferralInviteLink(String inviteLink) {
        edit = sharedPreferences.edit();
        edit.putString("InviteLink", inviteLink);
        edit.apply();
    }

//=======================referral code and link====================================================


    public void setFirstLoginDone(Context context) {
        setFirstLogin(context, false); //true==not done
    }

    public void setFirstLogin(Context context, boolean FirstLogin) {
        SharedPreferences.Editor xCash = context.getSharedPreferences("xCashFirstLogin",
                Context.MODE_PRIVATE).edit();
        xCash.putBoolean("FirstLogin", FirstLogin);
        xCash.apply();
    }

    public boolean isFirstLogin(Context context) {
        return context.getSharedPreferences("xCashFirstLogin", Context.MODE_PRIVATE).getBoolean(
                "FirstLogin", true);
    }

    public void setFirstLoginAppGuideDone(Context context) {
        setFirstLoginAppGuide(context, false); //true==not done
    }

    public void setFirstLoginAppGuide(Context context, boolean FirstLogin) {
        SharedPreferences.Editor xCash = context.getSharedPreferences("xCashFirstLogin",
                Context.MODE_PRIVATE).edit();
        xCash.putBoolean("FirstLoginAppGuide", FirstLogin);
        xCash.apply();
    }

    public boolean isFirstLoginAppGuide(Context context) {
        return context.getSharedPreferences("xCashFirstLogin", Context.MODE_PRIVATE).getBoolean(
                "FirstLoginAppGuide", true);
    }


    //show indicator on scan screen on options button. start
    public void setScanTutorialDone(Context context) {
        setscanTutorial(context, false); //true==not done
    }

    public void setscanTutorial(Context context, boolean FirstLogin) {
        SharedPreferences.Editor xCash = context.getSharedPreferences("xCashFirstLogin",
                Context.MODE_PRIVATE).edit();
        xCash.putBoolean("scanTutorial", FirstLogin);
        xCash.apply();
    }

    public boolean isScanTutorialNotWatched(Context context) {
        return context.getSharedPreferences("xCashFirstLogin", Context.MODE_PRIVATE).getBoolean(
                "scanTutorial", true);
    }


    //show indicator on scan screen on options button. end


    public boolean isLogInSkipped() {
        return getId().isEmpty();
    }


    //    mixpanel distinct id
    public void setDistinctId(Context context, String DistinctId) {
        SharedPreferences.Editor xCash = context.getSharedPreferences("xCashDistinctId",
                Context.MODE_PRIVATE).edit();
        xCash.putString("DistinctId", DistinctId);
        xCash.apply();
    }

    public String getDistinctId(Context context) {
        return context.getSharedPreferences("xCashDistinctId", Context.MODE_PRIVATE).getString(
                "DistinctId", System.currentTimeMillis() + "");
    }
//    mixpanel distinct id


    public void setIdToShow(String IdToShow) {
        edit = sharedPreferences.edit();
        edit.putString("IdToShow", IdToShow);
        edit.apply();
    }

    public String getIdToShow() {
        return sharedPreferences.getString("IdToShow", "");
    }

    public void setTempId(String TempId) {
        edit = sharedPreferences.edit();
        edit.putString("TempId", TempId);
        edit.apply();
    }

    public String getTempId() {
        return sharedPreferences.getString("TempId", getId());
    }

    public void setApiToken(String api_token) {
        edit = sharedPreferences.edit();
        edit.putString("api_token", api_token);
        edit.apply();
    }

    public String getApiToken() {
        return sharedPreferences.getString("api_token", "");
    }


    public String getTotalPointsOrignal() {
        return sharedPreferences.getString("total_points", "0.00");
    }

    public String getTotalPointsFormatted() {
        return UtillsG.formatCompletely(getTotalPointsOrignal());
    }


    public boolean isPointsAvailable(String requiredPoints) {
        try {
            requiredPoints = requiredPoints.replaceAll(",", "");
            return Float.parseFloat(getTotalPoints()) >= Float.parseFloat(requiredPoints);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean isAvailable_AvailableOnly(String requiredPoints) {
        try {
            requiredPoints = requiredPoints.replaceAll(",", "");
            return Float.parseFloat(getAvailablePointsUnformatted()) >= Float.parseFloat(requiredPoints);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return true;
    }

    public String getTotalPoints() {
        String totalPointsOrignal = getTotalPointsOrignal();
        if (totalPointsOrignal.contains(",")) {
            totalPointsOrignal = totalPointsOrignal.replace(",", "");
        }
        return totalPointsOrignal;
    }


    public String getPendingPoints() {
        return sharedPreferences.getString("Pending_points", "0.00");
    }

    public void setPendingPoints(String Pending_points) {
        if (Pending_points == null) {
            return;
        }
        edit = sharedPreferences.edit();
        edit.putString("Pending_points", Pending_points);
        edit.apply();
    }

    public String getBonusPointsUnformatted() {
        String bonusPoints = getBonusPoints();
        if (bonusPoints.contains(",")) {
            bonusPoints = bonusPoints.replaceAll(",", "");
        }
        return bonusPoints;
    }

    public String getBonusPoints() {
        return sharedPreferences.getString("BonusPoints", "0.00");
    }

    public void setBonusPoints(String BonusPoints) {
        if (BonusPoints == null) {
            return;
        }
        edit = sharedPreferences.edit();
        edit.putString("BonusPoints", BonusPoints);
        edit.apply();
    }

    public String getAvailablePointsUnformatted() {
        String availablePoints = getAvailablePoints();
        if (availablePoints.contains(",")) {
            availablePoints = availablePoints.replaceAll(",", "");
        }
        return availablePoints;
    }

    public String getAvailablePoints() {
        return sharedPreferences.getString("available", "0.00");
    }

    public void setAvailablePoints(String available) {
        if (available == null) {
            return;
        }
        edit = sharedPreferences.edit();
        edit.putString("available", available);
        edit.apply();
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

    public void setShowReferralTab(boolean ShowReferralTab) {
        edit = sharedPreferences.edit();
        edit.putBoolean("ShowReferralTab", ShowReferralTab);
        edit.apply();
    }

    public boolean showReferralTab() {
        return sharedPreferences.getBoolean("ShowReferralTab", false);
    }


    public void setTotalPoints(String total_points) {
        if (total_points == null) {
            return;
        }
        edit = sharedPreferences.edit();
        try {
            total_points = UtillsG.formatNumber(total_points);
        } catch (Exception e) {
            e.printStackTrace();
        }
        edit.putString("total_points", total_points);
        edit.apply();
    }


    public void setisVibrate(boolean isVibrate) {
        edit = sharedPreferences.edit();
        edit.putBoolean("isVibrate", isVibrate);
        edit.apply();
    }

    public boolean isVibrate() {
        return sharedPreferences.getBoolean("isVibrate", true);
    }


    public void setisxCashNotification(boolean isxCashNotification) {
        edit = sharedPreferences.edit();
        edit.putBoolean("isxCashNotification", isxCashNotification);
        edit.apply();
    }

    public boolean isxCashNotification() {
        return sharedPreferences.getBoolean("isxCashNotification", true);
    }


    public void setisTransactionalNotification(boolean isTransactionalNotification) {
        edit = sharedPreferences.edit();
        edit.putBoolean("isTransactionalNotification", isTransactionalNotification);
        edit.apply();
    }

    public boolean isTransactionalNotification() {
        return sharedPreferences.getBoolean("isTransactionalNotification", true);
    }


    public void setfbLink(String fbLink) {
        edit = sharedPreferences.edit();
        edit.putString("fbLink", fbLink);
        edit.apply();
    }

    public String getfbLink() {
        return sharedPreferences.getString("fbLink", "");
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


    public void setFacebookName(String facebook_id) {
        edit = sharedPreferences.edit();
        edit.putString("FacebookName", facebook_id);
        edit.apply();
    }

    public String getFacebookName() {
        return sharedPreferences.getString("FacebookName", getFirstName());
    }


    public void setCitizenId(String CitizenId) {
        edit = sharedPreferences.edit();
        edit.putString("CitizenId", CitizenId);
        edit.apply();
    }

    public String getCitizenId() {
        return sharedPreferences.getString("CitizenId", "");
    }

    public void setisFingerPrint(Boolean isFingerPrint) {
        edit = sharedPreferences.edit();
        edit.putBoolean("isFingerPrint", isFingerPrint);
        edit.apply();
    }

    public boolean isFingerPrint() {
        return sharedPreferences.getBoolean("isFingerPrint", true);
    }

    public void setTransactionPin(String TransactionPin) {
        edit = sharedPreferences.edit();
        edit.putString("TransactionPin", TransactionPin);
        edit.apply();
    }


    public String getTransactionPin() {
        return sharedPreferences.getString("TransactionPin", "");
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

    /**
     * Used with Event Bus to show red dot on vouchers main service if any new voucher is added.
     */
    public boolean isNewVoucherAvailable(String topVoucherId) {
        String lastTopVoucherId = sharedPreferences.getString("TopVoucherId", "");
        if (lastTopVoucherId.isEmpty()) {
            return false;
        }
        return !lastTopVoucherId.equals(topVoucherId);
    }

    public boolean showIndicator() {
        return sharedPreferences.getBoolean("ShowIndicator", false);
    }

    public void setShowIndicator(boolean ShowIndicator) {
        edit = sharedPreferences.edit();
        edit.putBoolean("ShowIndicator", ShowIndicator);
        edit.apply();
    }

    /**
     * Used with Event Bus to show red dot on vouchers main service if any new voucher is added.
     */


    public String getDeepLinkUrl(Context context) {
        SharedPreferences sharedPreferencesDeeplink = context.getSharedPreferences("xCashDeeplink"
                , Context.MODE_PRIVATE);
        return sharedPreferencesDeeplink.getString("deepLinkUrl", "");
    }

    public void setDeepLinkUrl(Context context, String deepLinkUrl) {
        SharedPreferences        sharedPreferencesDeeplink = context.getSharedPreferences(
                "xCashDeeplink", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit                      = sharedPreferencesDeeplink.edit();
        edit.putString("deepLinkUrl", deepLinkUrl);
        edit.apply();
    }

    public String getShortDeepLinkUrl(Context context) {
        SharedPreferences sharedPreferencesDeeplink = context.getSharedPreferences("xCashDeeplink"
                , Context.MODE_PRIVATE);
        return sharedPreferencesDeeplink.getString("ShortDeepLinkUrl", "");
    }

    public void setShortDeepLinkUrl(Context context, String ShortDeepLinkUrl) {
        SharedPreferences        sharedPreferencesDeeplink = context.getSharedPreferences(
                "xCashDeeplink", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit                      = sharedPreferencesDeeplink.edit();
        edit.putString("ShortDeepLinkUrl", ShortDeepLinkUrl);
        edit.apply();
    }

//    =======================Media Source=========================================================

    public void setMediaSource(Context context, String MediaSource) {
        SharedPreferences        sharedPreferencesDeeplink = context.getSharedPreferences(
                "xCashMediaSource", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit                      = sharedPreferencesDeeplink.edit();
        edit.putString("MediaSource", MediaSource);
        edit.apply();
    }

    public String getMediaSource(Context context) {
        SharedPreferences sharedPreferencesDeeplink = context.getSharedPreferences(
                "xCashMediaSource", Context.MODE_PRIVATE);
        return sharedPreferencesDeeplink.getString("MediaSource", "");
    }

//    =======================Media Source=========================================================

}
