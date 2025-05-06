import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.core.io.FileMode;
import com.o2sol.pdf4java.core.io.FileStream;
import com.o2sol.pdf4java.render.PDFPageImageFormat;
import com.o2sol.pdf4java.render.PDFPageRenderer;
import com.o2sol.pdf4java.render.PDFRendererSettings;
import com.o2sol.pdf4java.render.renderingsurfaces.PDFRenderingSurfaceType;

import java.util.Random;

public class PDF2ColorImage {
    public static void main(String[] args) {

        try {
            PDFFixedDocument document = new PDFFixedDocument("..\\..\\..\\SupportFiles\\Ocean.pdf");

            PDFPageRenderer pageRenderer = new PDFPageRenderer(document.getPage(0));

            PDFRendererSettings settings = new PDFRendererSettings(144, 144);
            settings.setRenderingSurface(pageRenderer.createRenderingSurface(PDFRenderingSurfaceType.RGB_BYTE_BLOCK, settings.getDpiX(), settings.getDpiY()));

            FileStream pngStream = new FileStream("Ocean.Page.0.png", FileMode.OPEN_READ_WRITE);
            pageRenderer.convertPageToImage(pngStream, PDFPageImageFormat.PNG, settings);
            pngStream.flush();
            pngStream.close();

            System.out.println("PDF2ColorImage sample completed.");
        }
        catch (PDFException ex) {
            System.out.println("Error procesing PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
