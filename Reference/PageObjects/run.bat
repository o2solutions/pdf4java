@ECHO OFF
ECHO Running PageObjects sample ...

javac -cp .;../../../out/PDF4Java.jar PageObjects.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar PageObjects