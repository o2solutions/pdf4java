@ECHO OFF
ECHO Running Annotations sample ...

javac -cp .;../../../out/PDF4Java.jar Annotations.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar Annotations