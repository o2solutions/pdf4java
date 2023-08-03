@ECHO OFF
ECHO Running Outlines sample ...

javac -cp .;../../../out/PDF4Java.jar Outlines.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar Outlines