@ECHO OFF
ECHO Running Type3Fonts sample ...

javac -cp .;../../../out/PDF4Java.jar Type3Fonts.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar Type3Fonts