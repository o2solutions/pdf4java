import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.core.io.FileMode;
import com.o2sol.pdf4java.core.io.FileStream;
import com.o2sol.pdf4java.render.PDFLayerRenderTarget;
import com.o2sol.pdf4java.render.PDFPageImageFormat;
import com.o2sol.pdf4java.render.PDFPageRenderer;
import com.o2sol.pdf4java.render.PDFRendererSettings;
import com.o2sol.pdf4java.render.imaging.PDFFloydSteinbergDitheringFilter;
import com.o2sol.pdf4java.render.imaging.PDFThresholdFilter;
import com.o2sol.pdf4java.render.renderingsurfaces.PDFBlackWhiteBlockRenderingSurface;
import com.o2sol.pdf4java.render.renderingsurfaces.PDFRenderingSurfaceType;

import java.util.Random;

public class PDF2BlackAndWhiteImage {
    public static void main(String[] args) {

        try {
            PDFFixedDocument document = new PDFFixedDocument("..\\..\\..\\SupportFiles\\Ocean.pdf");

            PDFPageRenderer pageRenderer = new PDFPageRenderer(document.getPage(0));

            PDFRendererSettings settings = new PDFRendererSettings(144, 144);
            PDFBlackWhiteBlockRenderingSurface bwSurface = (PDFBlackWhiteBlockRenderingSurface)pageRenderer.createRenderingSurface(PDFRenderingSurfaceType.BLACK_WHITE_BLOCK, settings.getDpiX(), settings.getDpiY());

            // Use a simple threshold filter for conversion to B/W
            // Everything below 128 becomes black, everything above 128 becomes white
            bwSurface.setBinarizationFilter(new PDFThresholdFilter(128));
            settings.setRenderingSurface(bwSurface);

            // Output will be a 1bit B/W PNG
            FileStream pngStream = new FileStream("Ocean.Page.0.Dithering-Off.png", FileMode.OPEN_READ_WRITE);
            pageRenderer.convertPageToImage(pngStream, PDFPageImageFormat.PNG, settings);
            pngStream.flush();
            pngStream.close();

            // Use a dithering filter for conversion to B/W
            bwSurface.setBinarizationFilter(new PDFFloydSteinbergDitheringFilter());

            // Output will be a 1bit B/W PNG
            pngStream = new FileStream("Ocean.Page.0.Dithering-On.png", FileMode.OPEN_READ_WRITE);
            pageRenderer.convertPageToImage(pngStream, PDFPageImageFormat.PNG, settings);
            pngStream.flush();
            pngStream.close();

            System.out.println("PDF2BlackAndWhiteImage sample completed.");
        }
        catch (PDFException ex) {
            System.out.println("Error procesing PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
