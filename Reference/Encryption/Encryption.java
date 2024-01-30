import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.core.security.PDFAesSecurityHandler;
import com.o2sol.pdf4java.core.security.PDFRc4SecurityHandler;
import com.o2sol.pdf4java.graphics.PDFBrush;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFont;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFontFace;

public class Encryption {
    public static void main(String[] args) {
        try {
            aesEncryptWith128bitKeySize();
            aesEncryptWith256bitKeySize();
            aesEncryptWith256bitKeySizeEnhanced();

            rc4EncryptWith40bitKeySize();
            rc4EncryptWith128bitKeySize();

            decrypt();

            System.out.println("PDF documents have been saved with success");
        } catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }

    public static void aesEncryptWith128bitKeySize() {
        // Create the pdf document
        PDFFixedDocument document = new PDFFixedDocument();
        // Create a new page in the document
        PDFPage page = document.getPages().add();

        PDFStandardFont helvetica = new PDFStandardFont(PDFStandardFontFace.HELVETICA_BOLD_ITALIC, 16);
        PDFBrush blackBrush = new PDFBrush();
        page.getCanvas().drawString("Encryption: AES 128 bit", helvetica, blackBrush, 50, 100);

        PDFAesSecurityHandler aes = new PDFAesSecurityHandler();
        aes.setEnableContentExtractionForAccessibility(false);
        aes.setEnableDocumentAssembly(false);
        aes.setEnableDocumentChange(false);
        aes.setEnableContentExtraction(false);
        aes.setEnableFormsFill(false);
        aes.setEnableAnnotationsAndFieldsEdit(false);
        aes.setEnablePrint(false);
        aes.setEncryptMetadata(true);
        aes.setKeySize(128);
        aes.setUserPassword("userpass");
        aes.setOwnerPassword("ownerpass");

        document.save("Encryption_AesEncryptWith128bitKeySize.pdf", aes);
    }

    public static void aesEncryptWith256bitKeySize() {
        // Create the pdf document
        PDFFixedDocument document = new PDFFixedDocument();
        // Create a new page in the document
        PDFPage page = document.getPages().add();

        PDFStandardFont helvetica = new PDFStandardFont(PDFStandardFontFace.HELVETICA_BOLD_ITALIC, 16);
        PDFBrush blackBrush = new PDFBrush();
        page.getCanvas().drawString("Encryption: AES 256 bit", helvetica, blackBrush, 50, 100);

        PDFAesSecurityHandler aes = new PDFAesSecurityHandler();
        aes.setEnableContentExtractionForAccessibility(false);
        aes.setEnableDocumentAssembly(false);
        aes.setEnableDocumentChange(false);
        aes.setEnableContentExtraction(false);
        aes.setEnableFormsFill(false);
        aes.setEnableAnnotationsAndFieldsEdit(false);
        aes.setEnablePrint(false);
        aes.setEncryptMetadata(true);
        aes.setKeySize(256);
        aes.setUserPassword("userpass");
        aes.setOwnerPassword("ownerpass");

        document.save("Encryption_AesEncryptWith256bitKeySize.pdf", aes);
    }

    public static void aesEncryptWith256bitKeySizeEnhanced() {
        // Create the pdf document
        PDFFixedDocument document = new PDFFixedDocument();
        // Create a new page in the document
        PDFPage page = document.getPages().add();

        PDFStandardFont helvetica = new PDFStandardFont(PDFStandardFontFace.HELVETICA_BOLD_ITALIC, 16);
        PDFBrush blackBrush = new PDFBrush();
        page.getCanvas().drawString("Encryption: AES 256 bit enhanced", helvetica, blackBrush, 50, 100);

        PDFAesSecurityHandler aes = new PDFAesSecurityHandler();
        aes.setEnableContentExtractionForAccessibility(false);
        aes.setEnableDocumentAssembly(false);
        aes.setEnableDocumentChange(false);
        aes.setEnableContentExtraction(false);
        aes.setEnableFormsFill(false);
        aes.setEnableAnnotationsAndFieldsEdit(false);
        aes.setEnablePrint(false);
        aes.setEncryptMetadata(true);
        aes.setKeySize(256);
        aes.setUserPassword("userpass");
        aes.setOwnerPassword("ownerpass");

        document.save("Encryption_AesEncryptWith256bitKeySizeEnhanced.pdf", aes);
    }

    public static void rc4EncryptWith40bitKeySize() {
        // Create the pdf document
        PDFFixedDocument document = new PDFFixedDocument();
        // Create a new page in the document
        PDFPage page = document.getPages().add();

        PDFStandardFont helvetica = new PDFStandardFont(PDFStandardFontFace.HELVETICA_BOLD_ITALIC, 16);
        PDFBrush blackBrush = new PDFBrush();
        page.getCanvas().drawString("Encryption: RC4 40 bit", helvetica, blackBrush, 50, 100);

        PDFRc4SecurityHandler rc4 = new PDFRc4SecurityHandler();
        rc4.setEnableContentExtractionForAccessibility(false);
        rc4.setEnableDocumentAssembly(false);
        rc4.setEnableDocumentChange(false);
        rc4.setEnableContentExtraction(false);
        rc4.setEnableFormsFill(false);
        rc4.setEnableAnnotationsAndFieldsEdit(false);
        rc4.setEnablePrint(false);
        rc4.setEncryptMetadata(true);
        rc4.setKeySize(40);
        rc4.setUserPassword("userpass");
        rc4.setOwnerPassword("ownerpass");

        document.save("Encryption_Rc4EncryptWith40bitKeySize.pdf", rc4);
    }

    public static void rc4EncryptWith128bitKeySize() {
        // Create the pdf document
        PDFFixedDocument document = new PDFFixedDocument();
        // Create a new page in the document
        PDFPage page = document.getPages().add();

        PDFStandardFont helvetica = new PDFStandardFont(PDFStandardFontFace.HELVETICA_BOLD_ITALIC, 16);
        PDFBrush blackBrush = new PDFBrush();
        page.getCanvas().drawString("Encryption: RC4 128 bit", helvetica, blackBrush, 50, 100);

        PDFRc4SecurityHandler rc4 = new PDFRc4SecurityHandler();
        rc4.setEnableContentExtractionForAccessibility(false);
        rc4.setEnableDocumentAssembly(false);
        rc4.setEnableDocumentChange(false);
        rc4.setEnableContentExtraction(false);
        rc4.setEnableFormsFill(false);
        rc4.setEnableAnnotationsAndFieldsEdit(false);
        rc4.setEnablePrint(false);
        rc4.setEncryptMetadata(true);
        rc4.setKeySize(128);
        rc4.setUserPassword("userpass");
        rc4.setOwnerPassword("ownerpass");

        document.save("Encryption_Rc4EncryptWith128bitKeySize.pdf", rc4);
    }


    /// <summary>
    /// Decrypts a PDF file
    /// </summary>
    /// <param name="input">Input stream.</param>
    /// <returns></returns>
    private static void decrypt() {
        PDFFixedDocument document = new PDFFixedDocument("..\\..\\SupportFiles\\encrypted.pdf", "pdf4java");

        PDFPage page = document.getPage(0);
        PDFStandardFont helvetica = new PDFStandardFont(PDFStandardFontFace.HELVETICA_BOLD, 16);
        PDFBrush blackBrush = new PDFBrush();
        page.getCanvas().drawString("Decrypted document", helvetica, blackBrush, 5, 5);

        document.save("Decryption.pdf");
    }

}
