@ECHO OFF
ECHO Running Actions sample ...

javac -cp .;../../../out/PDF4Java.jar Actions.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar Actions