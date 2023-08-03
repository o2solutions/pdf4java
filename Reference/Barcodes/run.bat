@ECHO OFF
ECHO Running Barcodes sample ...

javac -cp .;../../../out/PDF4Java.jar Barcodes.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar Barcodes