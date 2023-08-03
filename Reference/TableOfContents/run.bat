@ECHO OFF
ECHO Running TableOfContents sample ...

javac -cp .;../../../out/PDF4Java.jar TableOfContents.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar TableOfContents