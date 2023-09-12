@ECHO OFF
ECHO Running SearchText sample ...

javac -cp .;../../../Lib/PDF4Java.jar;../../../Lib/PDF4Java.Cmaps.jar SearchText.java --release 8

java.exe -Djava.library.path=../../../Lib -classpath .;../../../Lib/PDF4Java.jar;../../../Lib/PDF4Java.Cmaps.jar SearchText