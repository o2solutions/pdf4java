@ECHO OFF
ECHO Running SVGFont sample ...

javac -cp .;../../../out/PDF4Java.jar SVGFont.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar SVGFont