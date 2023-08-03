@ECHO OFF
ECHO Running BatesNumbers sample ...

javac -cp .;../../../out/PDF4Java.jar BatesNumbers.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar BatesNumbers