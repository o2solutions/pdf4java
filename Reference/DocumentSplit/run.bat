@ECHO OFF
ECHO Running DocumentSplit sample ...

javac -cp .;../../../out/PDF4Java.jar DocumentSplit.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar DocumentSplit