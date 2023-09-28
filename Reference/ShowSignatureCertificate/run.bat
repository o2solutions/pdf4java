@ECHO OFF
ECHO Running ShowSignatureCertificate sample ...

javac -cp .;../../../out/PDF4Java.jar ShowSignatureCertificate.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar ShowSignatureCertificate