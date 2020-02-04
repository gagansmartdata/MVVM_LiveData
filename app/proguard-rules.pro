# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /merchant/gagandeep/Android/Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}
-ignorewarnings
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
## Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
## RxJava
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
     long producerIndex;
     long consumerIndex;
 }
 -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
     rx.internal.util.atomic.LinkedQueueNode producerNode;
 }
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
-dontwarn sun.misc.Unsafe
-keepclassmembernames interface * {
  @retrofit.http.* <methods>;
                           }
#-keep public class * implements java.io.Serializable


# Keep serializable classes & fields
-keep class ** implements java.io.Serializable {
    <fields>;
}

# Keep Android classes
#-keep class ** extends android.** {
#    <fields>;
#    <methods>;
#}
#-keep class ** { *; }
#-keepclassmembers class * {
#    private <fields>;
#    public <fields>;
#    <fields>;
#}

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}


-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keep public @interface * {
 <fields>;
}

-keep class customer.dlc.xcash.R$raw { *; }
-dontwarn com.android.installreferrer
-keep public class com.google.firebase.iid.FirebaseInstanceId {
  public *;
}