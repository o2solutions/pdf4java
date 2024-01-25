import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.core.io.FileMode;
import com.o2sol.pdf4java.core.io.FileStream;
import com.o2sol.pdf4java.render.*;
import com.o2sol.pdf4java.render.imaging.PDFFloydSteinbergDitheringFilter;
import com.o2sol.pdf4java.render.imaging.PDFThresholdFilter;
import com.o2sol.pdf4java.render.renderingsurfaces.*;

import java.util.Random;

public class PDF2MultipageGrayTiff {
    public static void main(String[] args) {

        try {
            PDFFixedDocument document = new PDFFixedDocument("..\\..\\..\\SupportFiles\\content.pdf");

            PDFDocumentRenderer documentRenderer = new PDFDocumentRenderer(document);

            PDFRendererSettings settings = new PDFRendererSettings(144, 144);
            settings.setRenderingSurface(new PDFGray8BlockRenderingSurface());

            // Output will be a 1bit B/W CCIT G4 compressed multipage TIFF
            FileStream tiffStream = new FileStream("content.tif", FileMode.OPEN_READ_WRITE);
            documentRenderer.convertToMultipageImage("0-4", settings, PDFPageImageFormat.TIFF_LZW_COMPRESSED, tiffStream);
            tiffStream.flush();
            tiffStream.close();

            System.out.println("PDF2MultipageGrayTiff sample completed.");
        }
        catch (PDFException ex) {
            System.out.println("Error procesing PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
