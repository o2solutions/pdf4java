@ECHO OFF
ECHO Running SuperscriptSubscript sample ...

javac -cp .;../../../out/PDF4Java.jar SuperscriptSubscript.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar SuperscriptSubscript