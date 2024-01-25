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

public class SelectiveContentRendering {
    public static void main(String[] args) {

        try {
            PDFFixedDocument document = new PDFFixedDocument("..\\..\\..\\SupportFiles\\PDF4Java.Features.pdf");

            PDFPageRenderer pageRenderer = new PDFPageRenderer(document.getPage(0));

            PDFRendererSettings settings = new PDFRendererSettings(144, 144);
            // Render only text and vector graphics, do not render images
            settings.setRenderAnnotations(true);
            settings.setRenderFormFields(true);
            settings.setRenderText(true);
            settings.setRenderGraphics(true);
            settings.setRenderImages(false);
            settings.setRenderingSurface(pageRenderer.createRenderingSurface(PDFRenderingSurfaceType.RGB_BYTE_BLOCK, settings.getDpiX(), settings.getDpiY()));

            FileStream pngStream = new FileStream("PDF4Java.Features.Page.0.NoImages.png", FileMode.OPEN_READ_WRITE);
            pageRenderer.convertPageToImage(pngStream, PDFPageImageFormat.PNG, settings);
            pngStream.flush();
            pngStream.close();

            // Render only images and vector graphics, do not render text
            settings.setRenderAnnotations(true);
            settings.setRenderFormFields(true);
            settings.setRenderText(false);
            settings.setRenderGraphics(true);
            settings.setRenderImages(true);
            pngStream = new FileStream("PDF4Java.Features.Page.0.NoText.png", FileMode.OPEN_READ_WRITE);
            pageRenderer.convertPageToImage(pngStream, PDFPageImageFormat.PNG, settings);
            pngStream.flush();
            pngStream.close();

            System.out.println("SelectiveContentRendering sample completed.");
        }
        catch (PDFException ex) {
            System.out.println("Error procesing PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
