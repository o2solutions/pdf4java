@ECHO OFF
ECHO Running DocumentTimeStamp sample ...

javac -cp .;../../../out/PDF4Java.jar DocumentTimeStamp.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar DocumentTimeStamp