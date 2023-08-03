import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.core.PDFFile;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.core.io.FileMode;
import com.o2sol.pdf4java.core.io.FileStream;
import com.o2sol.pdf4java.graphics.PDFCanvas;
import com.o2sol.pdf4java.graphics.PDFPageContent;
import com.o2sol.pdf4java.graphics.PDFFlipDirection;

public class PageImposition {
    public static void main(String[] args) {
        try (FileStream fs = new FileStream("..\\..\\SupportFiles\\content.pdf", FileMode.OPEN_READ)) {
            PDFFile file = new PDFFile(fs);
            PDFPageContent[] content = file.extractPageContent(0, file.getPageCount() - 1, false, false);

            PDFFixedDocument document = new PDFFixedDocument();
            PDFPage page1 = document.addPage();
            PDFCanvas pageCanvas = page1.getCanvas();
            // Draw the same page content 4 times on the new page, the content is scaled to half and flipped.
            pageCanvas.drawFormXObject(content[0],
                    0, 0, page1.getWidth() / 2, page1.getHeight() / 2);
            pageCanvas.drawFormXObject(content[0],
                    page1.getWidth() / 2, 0, page1.getWidth() / 2, page1.getHeight() / 2, 0, PDFFlipDirection.VERTICAL_FLIP);
            pageCanvas.drawFormXObject(content[0],
                    0, page1.getHeight() / 2, page1.getWidth() / 2, page1.getHeight() / 2, 0, PDFFlipDirection.HORIZONTAL_FLIP);
            pageCanvas.drawFormXObject(content[0],
                    page1.getWidth() / 2, page1.getHeight() / 2, page1.getWidth() / 2, page1.getHeight() / 2,
                    0, PDFFlipDirection.HORIZONTAL_AND_VERTICAL_FLIP);

            PDFPage page2 = document.addPage();
            pageCanvas = page2.getCanvas();
            // Draw 3 pages on the new page.
            pageCanvas.drawFormXObject(content[0],
                    0, 0, page2.getWidth() / 2, page2.getHeight() / 2);
            pageCanvas.drawFormXObject(content[1],
                    page2.getWidth() / 2, 0, page2.getWidth() / 2, page2.getHeight() / 2);
            pageCanvas.drawFormXObject(content[2],
                    0, page2.getHeight(), page2.getHeight() / 2, page2.getWidth(), 90);

            document.save("PageImposition.pdf");

            System.out.println("PDF document has been saved with success");
        } catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
