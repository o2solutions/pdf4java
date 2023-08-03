import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.graphics.svg.PDFSvgDrawing;

public class SVGToPDF {
    public static void main(String[] args) {
        try {
            PDFFixedDocument document = new PDFFixedDocument();
            PDFPage page = document.addPage();

            PDFSvgDrawing svg = new PDFSvgDrawing("..\\..\\SupportFiles\\tiger.svg");
            page.getCanvas().drawFormXObject(svg, 20, 20, page.getWidth() - 40, page.getWidth() - 40);

            document.save("SVGToPDF.pdf");

            System.out.println("PDF document has been saved with success");
        }
        catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
