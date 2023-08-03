@ECHO OFF
ECHO Running PDFUA sample ...

javac -cp .;../../../out/PDF4Java.jar PDFUA.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar PDFUA