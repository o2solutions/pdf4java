@ECHO OFF
ECHO Running TableGroups sample ...

javac -cp .;../../../out/PDF4Java.jar TableGroups.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar TableGroups