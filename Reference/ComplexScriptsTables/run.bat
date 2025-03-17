@ECHO OFF
ECHO Running ComplexScriptsTables sample ...

javac -encoding utf8 -cp .;../../../out/PDF4Java.jar ComplexScriptsTables.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar ComplexScriptsTables