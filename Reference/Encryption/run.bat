@ECHO OFF
ECHO Running Encryption sample ...

javac -cp .;../../../out/PDF4Java.jar Encryption.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar Encryption