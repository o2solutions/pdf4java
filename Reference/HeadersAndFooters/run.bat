@ECHO OFF
ECHO Running HeadersAndFooters sample ...

javac -cp .;../../../out/PDF4Java.jar HeadersAndFooters.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar HeadersAndFooters