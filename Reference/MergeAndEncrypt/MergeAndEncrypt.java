import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.core.security.PDFAesSecurityHandler;
import com.o2sol.pdf4java.utilities.PDFMerger;

public class MergeAndEncrypt {
    public static void main(String[] args) {
        try {
            String supportPath = "..\\..\\SupportFiles\\";

            PDFMerger merger = new PDFMerger();
            merger.appendFile(supportPath + "content.pdf", null);
            merger.appendFile(supportPath + "formfill.pdf", null);
            merger.appendFile(supportPath + "encrypted.pdf", "pdf4java");

            PDFAesSecurityHandler aes256e = new PDFAesSecurityHandler();
            aes256e.setKeySize(256);
            aes256e.setUseEnhancedPasswordValidation(true);
            aes256e.setUserPassword("pdfmerger");
            merger.save("MergeAndEncrypt.pdf", aes256e, false);

            System.out.println("PDF document has been saved with success");
        } catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
