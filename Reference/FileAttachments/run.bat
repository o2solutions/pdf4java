@ECHO OFF
ECHO Running FileAttachments sample ...

javac -cp .;../../../out/PDF4Java.jar FileAttachments.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar FileAttachments