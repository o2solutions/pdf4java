@ECHO OFF
ECHO Running SimpleMerge sample ...

javac -cp .;../../../out/PDF4Java.jar SimpleMerge.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar SimpleMerge