@ECHO OFF
ECHO Running Images sample ...

javac -cp .;../../../out/PDF4Java.jar Images.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar Images