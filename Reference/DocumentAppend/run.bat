@ECHO OFF
ECHO Running DocumentAppend sample ...

javac -cp .;../../../out/PDF4Java.jar DocumentAppend.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar DocumentAppend