import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.core.io.FileStream;
import com.o2sol.pdf4java.graphics.PDFSize;
import com.o2sol.pdf4java.render.PDFPageImageFormat;
import com.o2sol.pdf4java.render.PDFPageRenderer;
import com.o2sol.pdf4java.render.PDFRendererSettings;

public class PDF2FixedImageSize {
    public static void main(String[] args) {
        try {
        PDFFixedDocument document = new PDFFixedDocument("..\\..\\..\\SupportFiles\\Ocean.pdf");

        PDFPageRenderer pageRenderer = new PDFPageRenderer(document.getPage(0));

        // Convert PDF page to fixed image size 1920x1080
        try (FileStream pngStream = FileStream.openWrite("PDF4Java.Render-1920x1080.png")) {
            PDFRendererSettings settings = new PDFRendererSettings(96, 96);
            settings.setOutputImageSize(new PDFSize(1920, 1080));
            pageRenderer.convertPageToImage(pngStream, PDFPageImageFormat.PNG, settings);
            pngStream.flush();
        }

        // Convert PDF page to image size 1920xProportionalHeight
        try (FileStream pngStream = FileStream.openWrite("PDF4Java.Render-1920xProportionalHeight.png")) {
            PDFRendererSettings settings = new PDFRendererSettings(96, 96);
            settings.setOutputImageSize(new PDFSize(1920, 0));
            pageRenderer.convertPageToImage(pngStream, PDFPageImageFormat.PNG, settings);
            pngStream.flush();
        }

        // Convert PDF page to image size ProportionalWidth x 1080
        try (FileStream pngStream = FileStream.openWrite("PDF4Java.Render-ProportionalWidthx1080.png")) {
            PDFRendererSettings settings = new PDFRendererSettings(96, 96);
            settings.setOutputImageSize(new PDFSize(0, 1080));
            pageRenderer.convertPageToImage(pngStream, PDFPageImageFormat.PNG, settings);
            pngStream.flush();
        }

            System.out.println("PDF2FixedImageSize sample completed with success.");
        }
        catch (PDFException ex) {
            System.out.println("Error procesing PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
