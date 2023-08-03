@ECHO OFF
ECHO Running HelloJavaWorld sample ...

javac -cp .;../../../out/PDF4Java.jar HelloJavaWorld.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar HelloJavaWorld