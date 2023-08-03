@ECHO OFF
ECHO Running FormFill sample ...

javac -cp .;../../../out/PDF4Java.jar FormFill.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar FormFill