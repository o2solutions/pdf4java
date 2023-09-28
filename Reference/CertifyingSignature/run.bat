@ECHO OFF
ECHO Running CertyfingSignature sample ...

javac -cp .;../../../out/PDF4Java.jar CertyfingSignature.java --release 8

java.exe -Djava.library.path=../../../out -classpath .;../../../out/PDF4Java.jar CertyfingSignature