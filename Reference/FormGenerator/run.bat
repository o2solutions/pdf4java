@ECHO OFF
ECHO Running FormGenerator sample ...

javac -cp .;../../../out/PDF4Java.jar FormGenerator.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar FormGenerator