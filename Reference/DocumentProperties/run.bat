@ECHO OFF
ECHO Running DocumentProperties sample ...

javac -cp .;../../../out/PDF4Java.jar DocumentProperties.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar DocumentProperties