import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.content.PDFContentExtractor;
import com.o2sol.pdf4java.content.images.PDFVisualImage;
import com.o2sol.pdf4java.content.images.PDFVisualImageCollection;
import com.o2sol.pdf4java.content.text.PDFTextRun;
import com.o2sol.pdf4java.content.text.PDFTextRunCollection;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.graphics.*;
import com.o2sol.pdf4java.graphics.colors.PDFRgbColor;
import com.o2sol.pdf4java.graphics.fonts.*;

import java.util.Random;

public class ContentExtraction {
    public static void main(String[] args) {
        try {
            // Load the input file.
            PDFFixedDocument document = new PDFFixedDocument("..\\..\\SupportFiles\\content.pdf");

            extractTextAndHighlight(document);

            extractTextAndHighlightGlyphs(document);

            extractImagesAndHighlight(document);

            // Compress the page graphic content.
            for (int i = 0; i < document.getPages().size(); i++)
            {
                document.getPage(i).getCanvas().compressAndClose();
            }

            document.save("ContentExtraction.pdf");

            System.out.println("PDF document has been saved with success");
        }
        catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }

    private static void extractTextAndHighlight(PDFFixedDocument document) {
        PDFRgbColor penColor = new PDFRgbColor();
        PDFPen pen = new PDFPen(penColor, 0.5);
        Random rnd = new Random();

        PDFContentExtractor ce = new PDFContentExtractor(document.getPage(0));
        PDFTextRunCollection trc = ce.extractTextRuns();
        for (int i = 0; i < trc.size(); i++) {
            penColor.setR(rnd.nextInt(256));
            penColor.setG(rnd.nextInt(256));
            penColor.setB(rnd.nextInt(256));

            PDFPath boundingPath = new PDFPath();
            PDFPoint[] textRunCorners = trc.get(i).getCorners();
            boundingPath.startSubpath(textRunCorners[0].getX(), textRunCorners[0].getY());
            boundingPath.addLineTo(textRunCorners[1].getX(), textRunCorners[1].getY());
            boundingPath.addLineTo(textRunCorners[2].getX(), textRunCorners[2].getY());
            boundingPath.addLineTo(textRunCorners[3].getX(), textRunCorners[3].getY());
            boundingPath.closeSubpath();

            document.getPage(0).getCanvas().drawPath(pen, boundingPath);
        }
    }

    private static void extractTextAndHighlightGlyphs(PDFFixedDocument document) {
        PDFRgbColor penColor = new PDFRgbColor();
        PDFPen pen = new PDFPen(penColor, 0.5);
        Random rnd = new Random();
        byte[] rgb = new byte[3];

        PDFContentExtractor ce = new PDFContentExtractor(document.getPage(1));
        PDFTextRunCollection trc = ce.extractTextRuns();
        PDFTextRun tr = trc.get(1);
        for (int i = 0; i < tr.getGlyphs().size(); i++) {
            penColor.setR(rnd.nextInt(256));
            penColor.setG(rnd.nextInt(256));
            penColor.setB(rnd.nextInt(256));

            PDFPath boundingPath = new PDFPath();
            PDFPoint[] glyphCorners = tr.getGlyph(i).getGlyphCorners();
            boundingPath.startSubpath(glyphCorners[0].getX(), glyphCorners[0].getY());
            boundingPath.addLineTo(glyphCorners[1].getX(), glyphCorners[1].getY());
            boundingPath.addLineTo(glyphCorners[2].getX(), glyphCorners[2].getY());
            boundingPath.addLineTo(glyphCorners[3].getX(), glyphCorners[3].getY());
            boundingPath.closeSubpath();

            document.getPage(1).getCanvas().drawPath(pen, boundingPath);
        }
    }

    private static void extractImagesAndHighlight(PDFFixedDocument document) {
        PDFPen pen = new PDFPen(new PDFRgbColor(255, 0, 192), 0.5);
        PDFBrush brush = new PDFBrush(new PDFRgbColor(0, 0, 0));
        PDFStandardFont helvetica = new PDFStandardFont(PDFStandardFontFace.HELVETICA, 8);
        PDFStringAppearanceOptions sao = new PDFStringAppearanceOptions();
        sao.setBrush(brush);
        sao.setFont(helvetica);
        PDFStringLayoutOptions slo = new PDFStringLayoutOptions();
        slo.setWidth(1000);

        PDFContentExtractor ce = new PDFContentExtractor(document.getPage(2));
        PDFVisualImageCollection vic = ce.extractImages(false);
        for (int i = 0; i < vic.size(); i++) {
            PDFVisualImage vi = vic.get(i);

            PDFPoint[] imageCorners = vi.getImageCorners();
            PDFPath boundingPath = new PDFPath();
            boundingPath.startSubpath(imageCorners[0].getX(), imageCorners[0].getY());
            boundingPath.addLineTo(imageCorners[1].getX(), imageCorners[1].getY());
            boundingPath.addLineTo(imageCorners[2].getX(), imageCorners[2].getY());
            boundingPath.addLineTo(imageCorners[3].getX(), imageCorners[3].getY());
            boundingPath.closeSubpath();

            document.getPage(2).getCanvas().drawPath(pen, boundingPath);

            slo.setX(imageCorners[3].getX() + 1);
            slo.setY(imageCorners[3].getY() + 1);
            String imageProperties = String.format("Image ID: %s\nPixel width: %d pixels\nPixel height: %d pixels\n" +
                            "Display width: %f points\nDisplay height: %f points\nHorizonal Resolution: %f dpi\nVertical Resolution: %f dpi",
                    vi.getImageID(), vi.getWidth(), vi.getHeight(), vi.getDisplayWidth(), vi.getDisplayHeight(), vi.getDpiX(), vi.getDpiY());
            document.getPage(2).getCanvas().drawString(imageProperties, sao, slo);
        }
    }
}
