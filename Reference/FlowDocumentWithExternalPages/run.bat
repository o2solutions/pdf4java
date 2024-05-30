@ECHO OFF
ECHO Running FlowDocumentWithExternalPages sample ...

javac -cp .;../../../out/PDF4Java.jar FlowDocumentWithExternalPages.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar FlowDocumentWithExternalPages