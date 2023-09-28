@ECHO OFF
ECHO Running PAdESSignature sample ...

javac -cp .;../../../out/PDF4Java.jar PAdESSignature.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar PAdESSignature