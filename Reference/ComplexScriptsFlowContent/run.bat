@ECHO OFF
ECHO Running ComplexScriptsFlowContent sample ...

javac -encoding utf8 -cp .;../../../out/PDF4Java.jar ComplexScriptsFlowContent.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar ComplexScriptsFlowContent