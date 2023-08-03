@ECHO OFF
ECHO Running ContentStream sample ...

javac -cp .;../../../out/PDF4Java.jar ContentStream.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar ContentStream