@ECHO OFF
ECHO Running PDF2ColorImage sample ...

javac -cp .;../../../../out/PDF4Java.jar;../../../../out/PDF4Java.Render.jar PDF2ColorImage.java --release 8

java.exe -Djava.library.path=../../../../out -classpath .;../../../../out/PDF4Java.jar;../../../../out/PDF4Java.Render.jar PDF2ColorImage