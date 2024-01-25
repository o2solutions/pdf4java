@ECHO OFF
ECHO Running PDF2GrayImage sample ...

javac -cp .;../../../../out/PDF4Java.jar;../../../../out/PDF4Java.Render.jar PDF2GrayImage.java --release 8

java.exe -Djava.library.path=../../../../out -classpath .;../../../../out/PDF4Java.jar;../../../../out/PDF4Java.Render.jar PDF2GrayImage