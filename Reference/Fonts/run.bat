@ECHO OFF
ECHO Running Fonts sample ...

javac -cp .;../../../out/PDF4Java.jar Fonts.java --release 8 -encoding utf8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar Fonts