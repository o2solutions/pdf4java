import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.core.io.FileMode;
import com.o2sol.pdf4java.core.io.FileStream;
import com.o2sol.pdf4java.render.PDFPageImageFormat;
import com.o2sol.pdf4java.render.PDFPageRenderer;
import com.o2sol.pdf4java.render.PDFRendererSettings;
import com.o2sol.pdf4java.render.renderingsurfaces.PDFRenderingSurfaceType;

public class HighDpiPDF2Image {
    public static void main(String[] args) {

        try {
            PDFFixedDocument document = new PDFFixedDocument("..\\..\\..\\SupportFiles\\content.pdf");

            PDFPageRenderer pageRenderer = new PDFPageRenderer(document.getPage(4));

            PDFRendererSettings settings = new PDFRendererSettings(4800, 4800);
            settings.setRenderingSurface(pageRenderer.createRenderingSurface(PDFRenderingSurfaceType.ARGB_INT_STRIP, settings.getDpiX(), settings.getDpiY()));

            // Output will be a 32bit 40800*52800 pixels RGBA TIFF
            FileStream pngStream = new FileStream("PDF4Java.Page.0.tiff", FileMode.OPEN_READ_WRITE);
            pageRenderer.convertPageToImage(pngStream, PDFPageImageFormat.TIFF, settings);
            pngStream.flush();
            pngStream.close();

            System.out.println("Page has been converted to image with success.");
        }
        catch (PDFException ex) {
            System.out.println("Error procesing PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
