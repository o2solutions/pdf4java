@ECHO OFF
ECHO Running SearchText sample ...

javac -cp .;../../../Lib/PDF4Java-1.0.0.0-beta.jar;../../../Lib/PDF4Java.Cmaps-1.0.0.0-beta.jar SearchText.java --release 8

java.exe -Djava.library.path=../../../Lib -classpath .;../../../Lib/PDF4Java-1.0.0.0-beta.jar;../../../Lib/PDF4Java.Cmaps-1.0.0.0-beta.jar SearchText