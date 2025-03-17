@ECHO OFF
ECHO Running ComplexScriptsText sample ...

javac -encoding utf8 -cp .;../../../out/PDF4Java.jar ComplexScriptsText.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar ComplexScriptsText