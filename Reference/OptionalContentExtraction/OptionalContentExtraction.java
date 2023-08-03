import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.content.optionalcontent.PDFPageOptionalContent;
import com.o2sol.pdf4java.core.PDFFile;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.core.io.FileMode;
import com.o2sol.pdf4java.core.io.FileStream;
import com.o2sol.pdf4java.graphics.PDFCanvas;

public class OptionalContentExtraction {
    public static void main(String[] args) {
        try (FileStream fs = new FileStream("..\\..\\SupportFiles\\content.pdf", FileMode.OPEN_READ)) {
            PDFFile file = new PDFFile(fs);

            PDFFixedDocument document = new PDFFixedDocument();
            PDFPage page = document.addPage();
            PDFCanvas pageCanvas = page.getCanvas();

            PDFPageOptionalContent oc1 = file.extractPageOptionalContentGroup(4, "1");
            pageCanvas.drawFormXObject(oc1, 0, 0, page.getWidth() / 2, page.getHeight() / 2);
            PDFPageOptionalContent oc2 = file.extractPageOptionalContentGroup(4, "2");
            pageCanvas.drawFormXObject(oc2, page.getWidth() / 2, 0, page.getWidth() / 2, page.getHeight() / 2);
            PDFPageOptionalContent oc3 = file.extractPageOptionalContentGroup(4, "3");
            pageCanvas.drawFormXObject(oc3, 0, page.getHeight() / 2, page.getWidth() / 2, page.getHeight() / 2);
            PDFPageOptionalContent oc4 = file.extractPageOptionalContentGroup(4, "4");
            pageCanvas.drawFormXObject(oc4, page.getWidth() / 2, page.getHeight() / 2, page.getWidth() / 2, page.getHeight() / 2);

            document.save("OptionalContentExtraction.pdf");

            System.out.println("PDF document has been saved with success");
        } catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
