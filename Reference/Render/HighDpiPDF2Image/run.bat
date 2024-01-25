@ECHO OFF
ECHO Running HighDpiPDF2Image sample ...

javac -cp .;../../../../out/PDF4Java.jar;../../../../out/PDF4Java.Render.jar HighDpiPDF2Image.java --release 8

java.exe -Djava.library.path=../../../../out -classpath .;../../../../out/PDF4Java.jar;../../../../out/PDF4Java.Render.jar HighDpiPDF2Image