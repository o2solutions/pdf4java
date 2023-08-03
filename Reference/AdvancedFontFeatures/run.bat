@ECHO OFF
ECHO Running AdvancedFontFeatures sample ...

javac -cp .;../../../out/PDF4Java.jar AdvancedFontFeatures.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar AdvancedFontFeatures