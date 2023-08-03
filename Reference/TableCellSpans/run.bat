@ECHO OFF
ECHO Running TableCellSpans sample ...

javac -cp .;../../../out/PDF4Java.jar TableCellSpans.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar TableCellSpans