@ECHO OFF
ECHO Running SaveSignedCopy sample ...

javac -cp .;../../../out/PDF4Java.jar SaveSignedCopy.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar SaveSignedCopy