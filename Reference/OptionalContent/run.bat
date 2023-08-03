@ECHO OFF
ECHO Running OptionalContent sample ...

javac -cp .;../../../out/PDF4Java.jar OptionalContent.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar OptionalContent