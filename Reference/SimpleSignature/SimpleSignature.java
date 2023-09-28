import com.o2sol.pdf4java.Global;
import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.digitalsignatures.*;
import com.o2sol.pdf4java.forms.PDFSignatureField;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

public class SimpleSignature {
    public static void main(String[] args) throws IOException, java.security.KeyStoreException, java.security.NoSuchProviderException, java.security.NoSuchAlgorithmException, java.security.cert.CertificateException, java.security.UnrecoverableKeyException {
        PDFX509Certificate certificate = loadCertificate("..\\..\\SupportFiles\\O2SolutionsDemoCertificate.pfx", "P@ssw0rd!".toCharArray());

        try {
            PDFFixedDocument document = new PDFFixedDocument("..\\..\\SupportFiles\\formfill.pdf");

            PDFSignatureField signField = (PDFSignatureField)document.getForm().getField("signhere");
            PDFCmsDigitalSignature signature = new PDFCmsDigitalSignature();
            signature.setSignatureDigestAlgorithm(PDFDigitalSignatureDigestAlgorithm.SHA256);
            signature.setCertificate(certificate);
            signature.setContactInfo("techsupport@o2sol.com");
            signature.setLocation("Cluj Napoca");
            signature.setName("O2 Solutions Support");
            signature.setReason("Simple signature");
            signField.setSignature(signature);

            document.save("SimpleSignature.pdf");

            System.out.println("PDF document has been saved with success");
        }
        catch(PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }

    public static PDFX509Certificate loadCertificate(String fileName, char[] password) throws IOException, java.security.KeyStoreException, java.security.NoSuchProviderException, java.security.NoSuchAlgorithmException, java.security.cert.CertificateException, java.security.UnrecoverableKeyException {
        PDFJavaX509Certificate certificate = null;
        try (FileInputStream stream = new FileInputStream(fileName)) {
            KeyStore store = KeyStore.getInstance("pkcs12", "SunJSSE");
            store.load(stream, password);

            Enumeration<String> aliases = store.aliases();
            String alias = aliases.nextElement();

            X509Certificate x509cert = (X509Certificate)store.getCertificate(alias);
            PrivateKey key = (PrivateKey)store.getKey(alias, password);

            PDFJavaX509CertificatePrivateKey privateKey = new PDFJavaX509CertificatePrivateKey(key);
            certificate = new PDFJavaX509Certificate(x509cert, privateKey);
        }

        return certificate;
    }
}
