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

    -keepclassmembers class ** {
        public void onEvent*(***);
    }
    # Only required if you use AsyncExecutor
    -keepclassmembers class * extends de.greenrobot.event.util.ThrowableFailureEvent {
        <init>(java.lang.Throwable);
    }
