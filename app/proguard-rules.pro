# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Android\Android-SDK/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

    #指定压缩级别
    -optimizationpasses 5

    #不跳过非公共的库的类成员
    -dontskipnonpubliclibraryclassmembers
        #不做预校验的操作
    -dontpreverify
        # 混淆时不记录日志
    -verbose

    #混淆时采用的算法
    -optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

    #把混淆类中的方法名也混淆了
    -useuniqueclassmembernames

    #优化时允许访问并修改有修饰符的类和类的成员
    -allowaccessmodification

    #将文件来源重命名为“SourceFile”字符串
    -renamesourcefileattribute SourceFile
    #保留行号
    -keepattributes SourceFile,LineNumberTable
    #保持泛型
    -keepattributes Signature


    # 系统类不需要混淆
    -keepattributes *Annotation*
    -keep class * extends java.lang.annotation.Annotation { *; }
    -keepattributes Signature
    -keep public class * extends android.app.Fragment
    -keep public class * extends android.app.Activity
    -keep public class * extends android.app.Application
    -keep public class * extends android.app.Service
    -keep public class * extends android.content.BroadcastReceiver
    -keep public class * extends android.content.ContentProvider
    -keep public class * extends android.app.backup.BackupAgentHelper
    -keep public class * extends android.preference.Preference
    -keep public class * extends android.support.v4.**
    -keep public class * extends android.support.v7.**
    #保持下面的V4兼容包的类不被混淆
    -keep class android.support.v4.**{*;}
    #混淆NDK
    -keepclasseswithmembernames class * {
            native <methods>;
    }
#保持自定义View的get和set相关方法
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

#保持Activity中View及其子类入参的方法
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}
# 对于带有回调函数的onXXEvent、**On*Listener的，不能被混淆
-keepclassmembers class * {
    void *(**On*Event);
    void *(**On*Listener);
}
    #混淆枚举
     -keepclassmembers enum * {
            public static **[] values();
            public static ** valueOf(java.lang.String);
    }

    #不混淆Parcelable和它的实现子类，还有Creator成员变量
    -keep class * implements android.os.Parcelable {
      public static final android.os.Parcelable$Creator *;
    }
    #保持所有实现 Serializable 接口的类成员
    -keepclassmembers class * implements java.io.Serializable {
        static final long serialVersionUID;
        private static final java.io.ObjectStreamField[] serialPersistentFields;
        private void writeObject(java.io.ObjectOutputStream);
        private void readObject(java.io.ObjectInputStream);
        java.lang.Object writeReplace();
        java.lang.Object readResolve();
    }

   #避免混淆内部类
    -keepattributes InnerClasses

        # 保持测试相关的代码
        -dontnote junit.framework.**
        -dontnote junit.runner.**
        -dontwarn android.test.**
        -dontwarn android.support.test.**
        -dontwarn org.junit.**

    #避免混淆Rxjava/RxAndroid
    -dontwarn sun.misc.**
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

    #阿里 vlayout
    -keepattributes InnerClasses
    -keep class com.alibaba.android.vlayout.ExposeLinearLayoutManagerEx { *; }
    -keep class android.support.v7.widget.RecyclerView$LayoutParams { *; }
    -keep class android.support.v7.widget.RecyclerView$ViewHolder { *; }
    -keep class android.support.v7.widget.ChildHelper { *; }
    -keep class android.support.v7.widget.ChildHelper$Bucket { *; }
    -keep class android.support.v7.widget.RecyclerView$LayoutManager { *; }

    # butterknife
    -keep class butterknife.** { *; }
    -dontwarn butterknife.internal.**
    -keep class **$$ViewBinder { *; }
    -keepclasseswithmembernames class * {
        @butterknife.* <fields>;
    }
    -keepclasseswithmembernames class * {
        @butterknife.* <methods>;
    }

  # Gson
  #-keepattributes Signature-keepattributes *Annotation*
   -keep class sun.misc.Unsafe { *; }
   -keep class com.google.gson.stream.** { *; }
   # Application classes that will be serialized/deserialized over Gson 下面替换成自己的实体类
   -keep class com.example.bean.** { *; }

# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions




# OkHttp3
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# Okio
-dontwarn com.squareup.**
-dontwarn okio.**
-keep public class org.codehaus.* { *; }
-keep public class java.nio.* { *; }
# 对于带有回调函数的onXXEvent、**On*Listener的，不能被混淆
-keepclassmembers class * {
    void *(**On*Event);
    void *(**On*Listener);
}

    #glide
    -keep public class * implements com.bumptech.glide.module.GlideModule
    -keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
      **[] $VALUES;
      public *;
    }

    -keep class com.zmy.laosiji.** { *; }




