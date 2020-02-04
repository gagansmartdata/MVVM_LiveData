package com.example.mvvm_livedata.internal;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.mvvm_livedata.internal.constants.Constants;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Created by Onner Tech on 05 Jul 2017.
 */

public class UtillsG
{
    private static Toast toastG;

    /**
     * @param msg    -message to be displayed
     * @param center - true ,if toast is to be displayed in center,otherwise false.
     */
    public static void showToast(String msg, Context context, boolean center) {
        if (toastG != null) {
            toastG.cancel();
        }
        toastG = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        if (center) {
            toastG.setGravity(Gravity.CENTER, 0, 0);
        }
        toastG.show();
    }

    /**
     * finish all the activities from stack.(works only in higher versions).
     */
    public static void finishAll(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            ((Activity) context).finishAffinity();
        } else {
            ((Activity) context).finish();
        }
    }

    /**
     * @param i    -intent to be fired.
     * @param logo --shareable view. (used shared object for transitions ).
     */
    public static void startTransition(Activity activity, Intent i, View logo) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity,
                    Pair.create(logo, Constants.Extras.TRANSITION_NAME_1));
            activity.startActivity(i, options.toBundle());
        } else {
            activity.startActivity(i);
        }
    }

    /**
     * @param i     -intent to be fired.
     * @param view1 --1st shareable view. (used shared object for transitions ).
     * @param view2 --2nd shareable view. (used shared object for transitions ).
     */
    public static void startTransition(Activity activity, Intent i, View view1, View view2) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Pair<View, String> p1 = Pair.create(view1, Constants.Extras.TRANSITION_NAME_1);
            Pair<View, String> p2 = Pair.create(view2, Constants.Extras.TRANSITION_NAME_2);

            ActivityOptions options = ActivityOptions.
                    makeSceneTransitionAnimation(activity, p1, p2);
            activity.startActivity(i, options.toBundle());
        } else {
            activity.startActivity(i);
        }
    }

    /**
     * @return true, if app is running in foreground.
     */
    public static boolean isAppOnForeground(Context context) {
        ActivityManager                             activityManager =
                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses    =
                activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        final String packageName = "sdei.merchant.xCash";
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param view --current focused view
     */
    public static void hideKeyboard(Context context, View view) {
        if (view != null) {
            InputMethodManager imm =
                    (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public static void hideSoftKeyboard(Activity activity) {
        try {
            View currentFocus = activity.getCurrentFocus();
            if (currentFocus != null) {
                InputMethodManager inputMethodManager =
                        (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
            }
        } catch (Exception | Error e) {
            e.printStackTrace();
        }
    }


    private static Pattern regex = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]");
    private static Pattern digit = Pattern.compile("[0-9]");

    public static boolean containsSpecialChars(String string) {
        return regex.matcher(string).find() || digit.matcher(string).find();
    }


    /**
     * format string(number) in `0.00` format.
     *
     * @param number
     * @return {@link String} (Formatted)
     */
    public static String formatNumber(String number) {
        try {
            if (number.contains(",")) {
                return number;
            }
            if (Float.parseFloat(number) == 0) {
                return "0.00";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            return new DecimalFormat("0.00").format(Double.parseDouble(number));
        } catch (Exception e) {
            e.printStackTrace();
            return number;
        }
    }

    public static String formatCompletely(String number) {
        return getNumberWithComma(formatNumber(number));
    }


    public static String getNumberWithComma(String amount) {
        try {
            amount = formatNumber(amount);
            if (amount.contains(",")) {
                return amount;
            }

            if (amount.contains(".")) {

                return NumberFormat.getNumberInstance(Locale.US).format(Integer.parseInt(amount.substring(0, amount.indexOf(".")))) + amount.substring(amount.indexOf("."));

            }

            return NumberFormat.getNumberInstance(Locale.US).format(Integer.parseInt(amount));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return amount;
        }
    }

    public static String getNumberWithCommaWithoutDecimal(String amount) {

        if (amount.contains(".")) {
            amount = amount.substring(0, amount.indexOf("."));
        }
        if (amount.contains(",")) {
            return amount;
        }
        return NumberFormat.getNumberInstance(Locale.US).format(Integer.parseInt(amount));
    }

    /**
     * check weather FB installed or not and return Intent accordingly.
     *
     * @param context
     * @param profileID
     * @return {@link Intent}
     */
    public static Intent getOpenFacebookIntent(Context context, String profileID) {

        try {
            if (profileID.contains("https:")) {
                return new Intent(Intent.ACTION_VIEW, Uri.parse(profileID)); //catches and opens
                // a url to the desired page
            }
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0); //Checks if FB
            // is even installed.
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://facewebmodal/f?href=https://www" +
                    ".facebook.com/" + profileID)); //Trys to make intent with FB's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/n/?" + profileID)); //catches and opens a
            // url to the desired page
        }
    }

    /**
     * vibrate device , it will be used when scan is done successfully.
     *
     * @param context
     */
//    public static void vibrate(Context context) {
//        try {
//            Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
//            // Vibrate for 500 milliseconds
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                v.vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE));
//            } else {
//                //deprecated in API 26
//                v.vibrate(150);
//            }
//            ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
//            toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP, 150);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
