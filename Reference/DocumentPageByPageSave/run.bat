@ECHO OFF
ECHO Running DocumentPageByPageSave sample ...

javac -cp .;../../../out/PDF4Java.jar DocumentPageByPageSave.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar DocumentPageByPageSave