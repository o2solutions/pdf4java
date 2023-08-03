@ECHO OFF
ECHO Running PageImposition sample ...

javac -cp .;../../../out/PDF4Java.jar PageImposition.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar PageImposition