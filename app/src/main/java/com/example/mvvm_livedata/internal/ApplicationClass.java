package com.example.mvvm_livedata.internal;

import android.os.Build;
import android.os.StrictMode;

import androidx.multidex.BuildConfig;
import androidx.multidex.MultiDexApplication;

import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import io.reactivex.internal.functions.Functions;
import io.reactivex.plugins.RxJavaPlugins;
import okhttp3.CertificatePinner;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gagandeeps on 3/2/20.
 */

public class ApplicationClass extends MultiDexApplication
{

	//    private static Context  context;
	private static Retrofit retrofit;
	private static String   language    = "en";
	private static String   customerId  = "";
	private static String   accessToken = "";

	private static String           longitude = "";
	private static String           latitude  = "";
	private static ApplicationClass sInstance = null;//unused for now ,as no extra functionality required here.

	public static Retrofit getRetrofit()
	{
		if (retrofit == null) {
			initRetrofitModule();
		}
		return retrofit;
	}

	private static void initRetrofitModule()
	{

		retrofit = new Retrofit.Builder()
				.baseUrl("base url")
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.client(okClient())
				.build();


		//handle Gson error
		try {
			RxJavaPlugins.setErrorHandler(Functions.<Throwable>emptyConsumer());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static OkHttpClient okClient()
	{

		OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

//		// TODO: 9/10/19 encryption test
//		httpClient.addNetworkInterceptor(new EncryptionInterceptor());

		if (BuildConfig.DEBUG) {
			HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
			logging.setLevel(HttpLoggingInterceptor.Level.BODY);
			httpClient.addInterceptor(logging);
		} else {

			ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
					.tlsVersions(TlsVersion.TLS_1_2)
					.cipherSuites(
							CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
							CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
							CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
					.build();
			httpClient.connectionSpecs(Collections.singletonList(spec)).certificatePinner(new CertificatePinner.Builder()
					.add("xcashapp.com", SharedPrefHelper.getSHA256keys()).build());
		}

//        httpsSupport(httpClient);  //as we are using sha256 for now, so comment this line.


		httpClient.connectTimeout(35, TimeUnit.SECONDS);
		httpClient.writeTimeout(60, TimeUnit.SECONDS);
		httpClient.readTimeout(30, TimeUnit.SECONDS);
		httpClient.addInterceptor(chain -> {
			Request original    = chain.request();
			String  deviceToken = FirebaseInstanceId.getInstance().getToken();
			if (deviceToken == null) {
				deviceToken = "";
			}
			// Request customization: add request headers
			Request.Builder requestBuilder = original.newBuilder()
					.header("Accept-Language", getLanguage())
					.header("app-version", BuildConfig.VERSION_NAME)
					.header("device-type", "android")
					.header("device-token", deviceToken);

			if (!getAccessToken().isEmpty()) {
				requestBuilder.header("access-token", getAccessToken());
			}
			if (!getCustomerId().isEmpty()) {
				requestBuilder.header("customer-id", getCustomerId());
			}

			if (!getLatitude().isEmpty()) {
				requestBuilder.header("latitude", getLatitude());
			}

			if (!getLongitude().isEmpty()) {
				requestBuilder.header("longitude", getLongitude());
			}

			return chain.proceed(requestBuilder.build());
		});


		return httpClient.build();
	}

	public static String getLanguage()
	{
		return language;
	}

	public static String getAccessToken()
	{
		return accessToken == null ? "" : accessToken;
	}

	public static void setAccessToken(String accessToken)
	{
		ApplicationClass.accessToken = accessToken;
	}

	private static String getCustomerId()
	{
		return customerId == null ? "" : customerId;
	}

	public static void setCustomerId(String customerId)
	{
		if (customerId != null) {
			if (customerId.isEmpty()) {
				return;
			}
		}
		ApplicationClass.customerId = customerId;
	}

	public static String getLatitude()
	{
		return latitude;
	}

	public static String getLongitude()
	{
		return longitude;
	}

	public static void setLongitude(String longitude)
	{
		ApplicationClass.longitude = longitude;
	}

	public static void setLatitude(String latitude)
	{
		ApplicationClass.latitude = latitude;
	}

	public static void setLanguage(String language)
	{
		if (language == null) {
			return;
		}
		if (language.isEmpty()) {
			return;
		}
		ApplicationClass.language = language;
	}

	//    ====================mix panel====================

	public static void resetAll(SharedPrefHelper getLocalData)
	{
		ApplicationClass.setCustomerId(getLocalData.getId());
		ApplicationClass.setAccessToken(getLocalData.getApiToken());
		ApplicationClass.setLatitude(getLocalData.getLatitude());
		ApplicationClass.setLongitude(getLocalData.getLongitude());
		ApplicationClass.resetRetrofit();
	}

	public static void resetRetrofit()
	{
		retrofit = null;
	}

	// Getter to access Singleton instance
	public static ApplicationClass getInstance()
	{
		return sInstance;
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
//        context = getApplicationContext();
		sInstance = this;
//        MultiDex.install(getApplicationContext());
		//for camera
		FirebaseApp.initializeApp(getApplicationContext());
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
			StrictMode.setVmPolicy(builder.build());
		}

		initRetrofitModule();
	}
}
