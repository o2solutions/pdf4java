@ECHO OFF
ECHO Running Text sample ...

javac -cp .;../../../out/PDF4Java.jar Text.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar Text