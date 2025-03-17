@ECHO OFF
ECHO Running ComplexScriptsFormFill sample ...

javac -encoding utf8 -cp .;../../../out/PDF4Java.jar ComplexScriptsFormFill.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar ComplexScriptsFormFill