@ECHO OFF
ECHO Running SVGToPDF sample ...

javac -cp .;../../../out/PDF4Java.jar SVGToPDF.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar SVGToPDF