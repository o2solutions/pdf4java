@ECHO OFF
ECHO Running Invoice sample ...

javac -cp .;../../../out/PDF4Java.jar Invoice.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar Invoice