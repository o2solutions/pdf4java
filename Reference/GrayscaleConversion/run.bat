@ECHO OFF
ECHO Running GrayscaleConversion sample ...

javac -cp .;../../../out/PDF4Java.jar GrayscaleConversion.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar GrayscaleConversion