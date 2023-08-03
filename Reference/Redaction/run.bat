@ECHO OFF
ECHO Running Redaction sample ...

javac -cp .;../../../out/PDF4Java.jar Redaction.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar Redaction