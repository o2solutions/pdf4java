@ECHO OFF
ECHO Running PageAnalyzer sample ...

javac -cp .;../../../out/PDF4Java.jar PageAnalyzer.java JsonExporter.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar PageAnalyzer