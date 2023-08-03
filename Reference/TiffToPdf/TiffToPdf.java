import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.graphics.images.PDFTiffImage;

public class TiffToPdf {
    public static void main(String[] args) {
        try {
            PDFFixedDocument document = new PDFFixedDocument();
            PDFTiffImage tiff = new PDFTiffImage("..\\..\\SupportFiles\\sample.tif");

            for (int i = 0; i < tiff.getFrameCount(); i++)
            {
                tiff.setActiveFrame(i);
                PDFPage page = document.addPage();
                page.getCanvas().drawImage(tiff, 0, 0, page.getWidth(), page.getHeight());
            }

            document.save("TiffToPdf.pdf");

            System.out.println("PDF document has been saved with success");
        }
        catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
