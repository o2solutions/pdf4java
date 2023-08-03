@ECHO OFF
ECHO Running Emoji sample ...

javac -cp .;../../../out/PDF4Java.jar Emoji.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar Emoji