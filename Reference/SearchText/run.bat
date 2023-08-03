@ECHO OFF
ECHO Running SearchText sample ...

javac -cp .;../../../out/PDF4Java.jar SearchText.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar SearchText