@ECHO OFF
ECHO Running VectorGraphics sample ...

javac -cp .;../../../out/PDF4Java.jar VectorGraphics.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar VectorGraphics