@ECHO OFF
ECHO Running Portfolios sample ...

javac -cp .;../../../out/PDF4Java.jar Portfolios.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar Portfolios