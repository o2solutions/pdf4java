import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.transforms.PDFConvertToGrayTransform;
import com.o2sol.pdf4java.transforms.PDFPageTransformer;

public class GrayscaleConversion {
    public static void main(String[] args) {
        try {
            // Load the input file.
            PDFFixedDocument document = new PDFFixedDocument("..\\..\\SupportFiles\\content.pdf");

            PDFConvertToGrayTransform grayTransform = new PDFConvertToGrayTransform();
            PDFPageTransformer pageTransformer = new PDFPageTransformer(document.getPage(3));
            pageTransformer.applyTransform(grayTransform);

            document.save("GrayscaleConversion.pdf");

            System.out.println("PDF document has been saved with success");
        } catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
