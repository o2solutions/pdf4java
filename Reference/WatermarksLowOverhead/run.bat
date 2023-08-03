@ECHO OFF
ECHO Running WatermarksLowOverhead sample ...

javac -cp .;../../../out/PDF4Java.jar WatermarksLowOverhead.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar WatermarksLowOverhead