-optimizationpasses 5
-dontskipnonpubliclibraryclassmembers
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
-obfuscationdictionary proguard-s.txt
-classobfuscationdictionary proguard-s.txt
-packageobfuscationdictionary proguard-s.txt

-keep class com.gyf.immersionbar.* {*;}
-dontwarn com.gyf.immersionbar.**

-keep class org.litepal.** {*;}
-keep class * extends org.litepal.crud.LitePalSupport {*;}


