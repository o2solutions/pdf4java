@ECHO OFF
ECHO Running Layers sample ...

javac -cp .;../../../../out/PDF4Java.jar;../../../../out/PDF4Java.Render.jar Layers.java --release 8

java.exe -Djava.library.path=../../../../out -classpath .;../../../../out/PDF4Java.jar;../../../../out/PDF4Java.Render.jar Layers