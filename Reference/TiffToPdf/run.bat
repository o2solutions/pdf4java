@ECHO OFF
ECHO Running TiffToPdf sample ...

javac -cp .;../../../out/PDF4Java.jar TiffToPdf.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar TiffToPdf