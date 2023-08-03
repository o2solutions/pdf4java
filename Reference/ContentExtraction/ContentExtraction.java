import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.content.PDFContentExtractor;
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

    private static void extractTextAndHighlight(PDFFixedDocument document)
    {
        PDFRgbColor penColor = new PDFRgbColor();
        PDFPen pen = new PDFPen(penColor, 0.5);
        Random rnd = new Random();

        PDFContentExtractor ce = new PDFContentExtractor(document.getPage(0));
        PDFTextRunCollection trc = ce.extractTextRuns();
        for (int i = 0; i < trc.size(); i++)
        {
            penColor.setR(rnd.nextInt(256));
            penColor.setG(rnd.nextInt(256));
            penColor.setB(rnd.nextInt(256));

            PDFPath boundingPath = new PDFPath();
            boundingPath.startSubpath(trc.get(i).getCorners()[0].getX(), trc.get(i).getCorners()[0].getY());
            boundingPath.addLineTo(trc.get(i).getCorners()[1].getX(), trc.get(i).getCorners()[1].getY());
            boundingPath.addLineTo(trc.get(i).getCorners()[2].getX(), trc.get(i).getCorners()[2].getY());
            boundingPath.addLineTo(trc.get(i).getCorners()[3].getX(), trc.get(i).getCorners()[3].getY());
            boundingPath.closeSubpath();

            document.getPage(0).getCanvas().drawPath(pen, boundingPath);
        }
    }

    private static void extractTextAndHighlightGlyphs(PDFFixedDocument document)
    {
        PDFRgbColor penColor = new PDFRgbColor();
        PDFPen pen = new PDFPen(penColor, 0.5);
        Random rnd = new Random();
        byte[] rgb = new byte[3];

        PDFContentExtractor ce = new PDFContentExtractor(document.getPage(1));
        PDFTextRunCollection trc = ce.extractTextRuns();
        PDFTextRun tr = trc.get(1);
        for (int i = 0; i < tr.getGlyphs().size(); i++)
        {
            penColor.setR(rnd.nextInt(256));
            penColor.setG(rnd.nextInt(256));
            penColor.setB(rnd.nextInt(256));

            PDFPath boundingPath = new PDFPath();
            boundingPath.startSubpath(tr.getGlyph(i).getGlyphCorners()[0].getX(), tr.getGlyph(i).getGlyphCorners()[0].getY());
            boundingPath.addLineTo(tr.getGlyph(i).getGlyphCorners()[1].getX(), tr.getGlyph(i).getGlyphCorners()[1].getY());
            boundingPath.addLineTo(tr.getGlyph(i).getGlyphCorners()[2].getX(), tr.getGlyph(i).getGlyphCorners()[2].getY());
            boundingPath.addLineTo(tr.getGlyph(i).getGlyphCorners()[3].getX(), tr.getGlyph(i).getGlyphCorners()[3].getY());
            boundingPath.closeSubpath();

            document.getPage(1).getCanvas().drawPath(pen, boundingPath);
        }
    }

    private static void extractImagesAndHighlight(PDFFixedDocument document)
    {
        PDFPen pen = new PDFPen(new PDFRgbColor(255, 0, 192), 0.5);
        PDFBrush brush = new PDFBrush(new PDFRgbColor(0, 0, 0));
        PDFStandardFont helvetica = new PDFStandardFont(PDFStandardFontFace.HELVETICA, 8);
        PDFStringAppearanceOptions sao = new PDFStringAppearanceOptions();
        sao.setBrush(brush);
        sao.setFont(helvetica);
        PDFStringLayoutOptions slo = new PDFStringLayoutOptions();
        slo.setWidth(1000);

        PDFContentExtractor ce = new PDFContentExtractor(document.getPage(2));
        PDFVisualImageCollection eic = ce.extractImages(false);
        for (int i = 0; i < eic.size(); i++)
        {
            String imageProperties = String.format("Image ID: %s\nPixel width: %d pixels\nPixel height: %d pixels\n" +
                            "Display width: %f points\nDisplay height: %f points\nHorizonal Resolution: %f dpi\nVertical Resolution: %f dpi",
                    eic.get(i).getImageID(), eic.get(i).getWidth(), eic.get(i).getHeight(), eic.get(i).getDisplayWidth(), eic.get(i).getDisplayHeight(), eic.get(i).getDpiX(), eic.get(i).getDpiY());

            PDFPath boundingPath = new PDFPath();
            boundingPath.startSubpath(eic.get(i).getImageCorners()[0].getX(), eic.get(i).getImageCorners()[0].getY());
            boundingPath.addLineTo(eic.get(i).getImageCorners()[1].getX(), eic.get(i).getImageCorners()[1].getY());
            boundingPath.addLineTo(eic.get(i).getImageCorners()[2].getX(), eic.get(i).getImageCorners()[2].getY());
            boundingPath.addLineTo(eic.get(i).getImageCorners()[3].getX(), eic.get(i).getImageCorners()[3].getY());
            boundingPath.closeSubpath();

            document.getPage(2).getCanvas().drawPath(pen, boundingPath);
            slo.setX(eic.get(i).getImageCorners()[3].getX() + 1);
            slo.setY(eic.get(i).getImageCorners()[3].getY() + 1);
            
            document.getPage(2).getCanvas().drawPath(pen, boundingPath);
        }
    }
}
