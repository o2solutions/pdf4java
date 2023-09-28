import com.o2sol.pdf4java.Global;
import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.core.io.FileMode;
import com.o2sol.pdf4java.core.io.FileStream;
import com.o2sol.pdf4java.digitalsignatures.PDFComputedDigitalSignature;
import com.o2sol.pdf4java.digitalsignatures.PDFX509Certificate;
import com.o2sol.pdf4java.forms.PDFSignatureField;

import java.io.IOException;

public class ShowSignatureCertificate {
    public static void main(String[] args) {
        try (FileStream signedFile = new FileStream("..\\..\\SupportFiles\\PDF4NET.pdf", FileMode.OPEN_READ)) {
            PDFFixedDocument document = new PDFFixedDocument(signedFile);
            PDFSignatureField signature1Field = (PDFSignatureField) document.getForm().getField("Signature1");

            PDFComputedDigitalSignature signature1 = (PDFComputedDigitalSignature) signature1Field.getSignature();

            PDFX509Certificate certificate = signature1.getCertificate();

            //Print to console information contained in the certificate.
            System.out.println("Subject: " + certificate.getSubject());
            System.out.println("Issuer: " + certificate.getIssuer());
            System.out.println("Serial number: " + certificate.getSerialNumber());
            System.out.println("Version: " + certificate.getVersion());
            System.out.println("Valid from: " + certificate.getNotBefore());
            System.out.println("Expires on: " + certificate.getNotAfter());
            System.out.println("Certificate: " + certificate);
        }
    }
}
