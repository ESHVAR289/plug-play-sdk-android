<b> Pro-Guard Changes </b>

#Add Following pro-guard rules for SDK


    -keep,allowobfuscation @interface com.facebook.proguard.annotations.DoNotStrip

    -keep,allowobfuscation @interface com.facebook.proguard.annotations.KeepGettersAndSetters

    -keep @com.facebook.proguard.annotations.DoNotStrip class *

    -keepclassmembers class * {
        @com.facebook.proguard.annotations.DoNotStrip *;
    }

    -keepclassmembers @com.facebook.proguard.annotations.KeepGettersAndSetters class * {
        void set*(***);
        *** get*();
    }

    -keep class  com.citrus.sdk.ui.**{ *; }
    -keep class  com.citrus.**{ *; }
    -keep class  com.citrus.sdk.**{ *; }

    -keepattributes *Annotation*
    -keepclassmembers class ** {
        @org.greenrobot.eventbus.Subscribe <methods>;
    }
    -keep enum org.greenrobot.eventbus.ThreadMode { *; }

    # Only required if you use AsyncExecutor
    -keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
        <init>(java.lang.Throwable);
    }
