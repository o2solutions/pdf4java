import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.core.cos.PDFCosBinaryString;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.graphics.PDFBrush;
import com.o2sol.pdf4java.graphics.PDFContentStream;
import com.o2sol.pdf4java.graphics.colors.PDFRgbColor;
import com.o2sol.pdf4java.graphics.colors.PDFRgbColorSpace;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFont;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFontFace;
import com.o2sol.pdf4java.graphics.fonts.PDFTextRenderingMode;

public class ContentStream {
    public static void main(String[] args) {
        try {
            // Create the pdf document
            PDFFixedDocument document = new PDFFixedDocument();
            // Create a new page in the document
            PDFPage page = document.addPage();

            PDFBrush brush = new PDFBrush(PDFRgbColor.DARK_RED);
            PDFStandardFont helvetica = new PDFStandardFont(PDFStandardFontFace.HELVETICA, 36);
            page.getCanvas().drawString("Hello World", helvetica, brush, 100, 100);

            PDFContentStream cs = new PDFContentStream(page.getCanvas());
            // Sets the stroke and fill colorspaces to DeviceRGB
            cs.setStrokeColorSpace(new PDFRgbColorSpace());
            cs.setFillColorSpace(new PDFRgbColorSpace());
            // Set stroke color to blue
            cs.setStrokeColor(new double[] { 0, 0, 1 });
            // Set fill color to green
            cs.setFillColor(new double[] { 0, 1, 0 });

            // Draw a line from (0, 0) to (page.Width/2, page.Height/2)
            // It will be drawn from top left corner to center of the page.
            cs.moveTo(0, 0);
            cs.lineTo(page.getWidth() / 2, page.getHeight() / 2);
            cs.strokePath();

            // Begin a text section
            cs.beginText();
            cs.setTextRendering(PDFTextRenderingMode.FILL_TEXT);
            cs.setTextMatrix(1, 0, 0, 1, 100, 150);
            cs.setTextFontAndSize(helvetica, helvetica.getSize());

            // This text will appear inverted because the coordinate system is in visual mode.
            byte[] binaryText = helvetica.encodeString("Hello World");
            cs.showText(new PDFCosBinaryString(binaryText));
            cs.endText();

            // Reset coordinate system and the current graphics state to default PDF
            cs.resetPDFCoordinateSystem();
            // Sets the stroke and fill colorspaces to DeviceRGB
            cs.setStrokeColorSpace(new PDFRgbColorSpace());
            cs.setFillColorSpace(new PDFRgbColorSpace());
            // Set stroke color to blue
            cs.setStrokeColor(new double[] { 0, 0, 1 });
            // Set fill color to green
            cs.setFillColor(new double[] { 0, 1, 0 });

            // Draw a line from (0, 0) to (page.Width/2, page.Height/2)
            // It will be drawn from bottom left corner to center of the page because the coordinate system has been reset to default PDF.
            cs.moveTo(0, 0);
            cs.lineTo(page.getWidth() / 2, page.getHeight() / 2);
            cs.strokePath();

            // Draw the text again
            cs.beginText();
            cs.setTextRendering(PDFTextRenderingMode.FILL_TEXT);
            cs.setTextMatrix(1, 0, 0, 1, 100, 150);
            cs.setTextFontAndSize(helvetica, helvetica.getSize());

            // This text will appear ok.
            binaryText = helvetica.encodeString("Hello World");
            cs.showText(new PDFCosBinaryString(binaryText));
            cs.endText();

            // Restore the visual coordinate system
            cs.restoreVisualCoordinateSystem();

            document.save("ContentStream.pdf");

            System.out.println("PDF document has been saved with success");
        }
        catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
