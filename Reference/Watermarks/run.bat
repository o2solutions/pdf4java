@ECHO OFF
ECHO Running Watermarks sample ...

javac -cp .;../../../out/PDF4Java.jar Watermarks.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar Watermarks