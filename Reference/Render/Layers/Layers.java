import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.core.io.FileMode;
import com.o2sol.pdf4java.core.io.FileStream;
import com.o2sol.pdf4java.render.PDFLayerRenderTarget;
import com.o2sol.pdf4java.render.PDFPageImageFormat;
import com.o2sol.pdf4java.render.PDFPageRenderer;
import com.o2sol.pdf4java.render.PDFRendererSettings;
import com.o2sol.pdf4java.render.renderingsurfaces.PDFRenderingSurfaceType;

import java.util.Random;

public class Layers {
    public static void main(String[] args) {

        try {
            PDFFixedDocument document = new PDFFixedDocument("..\\..\\..\\SupportFiles\\layers.pdf");

            PDFPageRenderer pageRenderer = new PDFPageRenderer(document.getPage(0));

            PDFRendererSettings settings = new PDFRendererSettings(96, 96);
            settings.setRenderingSurface(pageRenderer.createRenderingSurface(PDFRenderingSurfaceType.ARGB_INT_BLOCK, settings.getDpiX(), settings.getDpiY()));

            FileStream imageStream = new FileStream("Layers.AllContent.tiff", FileMode.OPEN_READ_WRITE);
            // By default all page content is rendered, layers visibility is ignored.
            pageRenderer.convertPageToImage(imageStream, PDFPageImageFormat.TIFF, settings);
            imageStream.flush();
            imageStream.close();

            // Render only the layers that are displayed when the document is viewed.
            settings.setLayerRenderTarget(PDFLayerRenderTarget.VIEW);
            imageStream = new FileStream("Layers.View.tiff", FileMode.OPEN_READ_WRITE);
            pageRenderer.convertPageToImage(imageStream, PDFPageImageFormat.TIFF, settings);
            imageStream.flush();
            imageStream.close();

            // Render only the layers that are displayed when the document is printed.
            settings.setLayerRenderTarget(PDFLayerRenderTarget.PRINT);
            imageStream = new FileStream("Layers.Print.tiff");
            pageRenderer.convertPageToImage(imageStream, PDFPageImageFormat.TIFF, settings);
            imageStream.flush();
            imageStream.close();

            System.out.println("Layers sample completed.");
        }
        catch (PDFException ex) {
            System.out.println("Error procesing PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
