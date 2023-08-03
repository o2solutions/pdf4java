@ECHO OFF
ECHO Running Measurements sample ...

javac -cp .;../../../out/PDF4Java.jar Measurements.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar Measurements