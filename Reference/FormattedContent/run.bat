@ECHO OFF
ECHO Running FormattedContent sample ...

javac -cp .;../../../out/PDF4Java.jar FormattedContent.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar FormattedContent