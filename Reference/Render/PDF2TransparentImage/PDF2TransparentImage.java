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
import com.o2sol.pdf4java.render.renderingsurfaces.PDFRenderingSurface;
import com.o2sol.pdf4java.render.renderingsurfaces.PDFRenderingSurfaceType;

import java.util.Random;

public class PDF2TransparentImage {
    public static void main(String[] args) {

        try {
            PDFFixedDocument document = new PDFFixedDocument("..\\..\\..\\SupportFiles\\content.pdf");

            PDFPageRenderer pageRenderer = new PDFPageRenderer(document.getPage(0));

            PDFRendererSettings settings = new PDFRendererSettings(144, 144);
            // Set background to transparent white (ARGB format)
            settings.setBackgroundColor(0x00FFFFFF);
            settings.setRenderingSurface(pageRenderer.createRenderingSurface(PDFRenderingSurfaceType.RGBA_BYTE_BLOCK, settings.getDpiX(), settings.getDpiY()));

            FileStream pngStream = new FileStream("Content.Page.0.png", FileMode.OPEN_READ_WRITE);
            pageRenderer.convertPageToImage(pngStream, PDFPageImageFormat.PNG, settings);
            pngStream.flush();
            pngStream.close();

            System.out.println("PDF2TransparentImage sample completed.");
        }
        catch (PDFException ex) {
            System.out.println("Error procesing PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
