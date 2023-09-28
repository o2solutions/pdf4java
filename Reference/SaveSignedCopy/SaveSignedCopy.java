import com.o2sol.pdf4java.Global;
import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.core.io.FileMode;
import com.o2sol.pdf4java.core.io.FileStream;
import com.o2sol.pdf4java.digitalsignatures.PDFComputedDigitalSignature;
import com.o2sol.pdf4java.forms.PDFSignatureField;

import java.io.IOException;

public class SaveSignedCopy {
    public static void main(String[] args) {
        try (FileStream signedFile = new FileStream("..\\..\\SupportFiles\\PDF4NET.pdf", FileMode.OPEN_READ)) {
            PDFFixedDocument document = new PDFFixedDocument(signedFile);
            PDFSignatureField signature1Field = (PDFSignatureField) document.getForm().getField("Signature1");

            PDFComputedDigitalSignature signature1 = (PDFComputedDigitalSignature) signature1Field.getSignature();

            try (FileStream signedCopy = new FileStream("SaveSignedCopy.pdf", FileMode.OPEN_READ_WRITE)) {
                signature1.saveSignedCopy(signedFile, signedCopy);
                signedCopy.flush();
            }
        }
    }
}
