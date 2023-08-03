import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.graphics.PDFDisplayRectangle;
import com.o2sol.pdf4java.graphics.colors.PDFRgbColor;
import com.o2sol.pdf4java.redaction.PDFContentRedactor;

public class Redaction {
    public static void main(String[] args) {
        try {
            PDFFixedDocument document = new PDFFixedDocument("..\\..\\SupportFiles\\content.pdf");
            PDFContentRedactor crText = new PDFContentRedactor(document.getPage(0));
            // Redact a rectangular area of 200*100 points and leave the redacted area uncovered.
            crText.redactArea(new PDFDisplayRectangle(50, 50, 200, 100));
            // Redact a rectangular area of 200*100 points and mark the redacted area with red.
            crText.redactArea(new PDFDisplayRectangle(50, 350, 200, 100), PDFRgbColor.RED);

            PDFContentRedactor crImages = new PDFContentRedactor(document.getPage(2));
            // Initialize the bulk redaction.
            crImages.beginRedaction();
            // Prepare for redaction a rectangular area of 500*100 points and leave the redacted area uncovered.
            crImages.redactArea(new PDFDisplayRectangle(50, 50, 500, 100));
            // Prepare for redaction a rectangular area of 200*100 points and mark the redacted area with red.
            crImages.redactArea(new PDFDisplayRectangle(50, 350, 500, 100), PDFRgbColor.RED);
            // When images are redacted, the cleared pixels are set to 0. Depending on image colorspace the redacted area can appear black or colored.
            // Finish the redaction
            crImages.applyRedaction();

            document.save("Redaction.pdf");

            System.out.println("PDF document has been saved with success");
        }
        catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
