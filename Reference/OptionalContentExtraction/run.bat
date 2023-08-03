@ECHO OFF
ECHO Running OptionalContentExtraction sample ...

javac -cp .;../../../out/PDF4Java.jar OptionalContentExtraction.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar OptionalContentExtraction