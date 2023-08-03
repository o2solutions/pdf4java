@ECHO OFF
ECHO Running SimpleTable sample ...

javac -cp .;../../../out/PDF4Java.jar SimpleTable.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar SimpleTable