@ECHO OFF
ECHO Running RemoveAndReplaceImages sample ...

javac -cp .;../../../out/PDF4Java.jar RemoveAndReplaceImages.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar RemoveAndReplaceImages