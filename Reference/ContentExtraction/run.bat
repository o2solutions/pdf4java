@ECHO OFF
ECHO Running ContentExtraction sample ...

javac -cp .;../../../out/PDF4Java.jar ContentExtraction.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar ContentExtraction