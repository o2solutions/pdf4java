import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.graphics.*;
import com.o2sol.pdf4java.graphics.colors.PDFRgbColor;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFont;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFontFace;

public class Watermarks {
    public static void main(String[] args) {
        try {
            // Load the input file.
            PDFFixedDocument document = new PDFFixedDocument("..\\..\\SupportFiles\\content.pdf");

            drawWatermarkUnderPageContent(document.getPage(0));

            drawWatermarkOverPageContent(document.getPage(1));

            drawWatermarkWithTransparency(document.getPage(2));

            // Compress the page graphic content.
            for (int i = 0; i < document.getPages().size(); i++)
            {
                document.getPage(i).getCanvas().compressAndClose();
            }

            document.save("Watermarks.pdf");

            System.out.println("PDF document has been saved with success");
        }
        catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }

    private static void drawWatermarkUnderPageContent(PDFPage page) {
        PDFBrush redBrush = new PDFBrush(new PDFRgbColor(192, 0, 0));
        PDFStandardFont helvetica = new PDFStandardFont(PDFStandardFontFace.HELVETICA_BOLD, 36);

        // Set the page canvas to be located under existing page content.
        page.setCanvasPosition(PDFPageCanvasPosition.UNDER_EXISTING_PAGE_CONTENT);

        PDFStringAppearanceOptions sao = new PDFStringAppearanceOptions();
        sao.setBrush(redBrush);
        sao.setFont(helvetica);
        PDFStringLayoutOptions slo = new PDFStringLayoutOptions();
        slo.setX(130);
        slo.setY(670);
        slo.setRotation(60);
        page.getCanvas().drawString("Sample watermark under page content", sao, slo);
    }

    private static void drawWatermarkOverPageContent(PDFPage page) {
        PDFBrush redBrush = new PDFBrush(new PDFRgbColor(192, 0, 0));
        PDFStandardFont helvetica = new PDFStandardFont(PDFStandardFontFace.HELVETICA_BOLD, 32);
        PDFPageCanvas pageCanvas = page.getCanvas();

        // The page canvas is located by default on top of existing page content.
        //page.SetCanvasPosition(PDFPageCanvasPosition.OverExistingPageContent);

        // Draw the watermark over page content.
        // Page content under the watermark will be masked.
        pageCanvas.drawString("Sample watermark over page content", helvetica, redBrush, 20, 335);

        pageCanvas.saveGraphicsState();

        // Draw the watermark over page content but using the Multiply blend mode.
        // The watermak will appear as if drawn under the page content, useful when watermarking scanned documents.
        // If the watermark is drawn under page content for scanned documents, it will not be visible because the scanned image will block it.
        PDFExtendedGraphicState gs1 = new PDFExtendedGraphicState();
        gs1.setBlendMode(PDFBlendMode.MULTIPLY);
        pageCanvas.setExtendedGraphicState(gs1);
        pageCanvas.drawString("Sample watermark over page content", helvetica, redBrush, 20, 385);

        // Draw the watermark over page content but using the Luminosity blend mode.
        // Both the page content and the watermark will be visible.
        PDFExtendedGraphicState gs2 = new PDFExtendedGraphicState();
        gs2.setBlendMode(PDFBlendMode.LUMINOSITY);
        pageCanvas.setExtendedGraphicState(gs2);
        pageCanvas.drawString("Sample watermark over page content", helvetica, redBrush, 20, 435);

        pageCanvas.restoreGraphicsState();
    }

    private static void drawWatermarkWithTransparency(PDFPage page) {
        PDFBrush redBrush = new PDFBrush(new PDFRgbColor(192, 0, 0));
        PDFStandardFont helvetica = new PDFStandardFont(PDFStandardFontFace.HELVETICA_BOLD, 36);
        PDFPageCanvas pageCanvas = page.getCanvas();

        // The page graphics is located by default on top of existing page content.
        //page.SetGraphicsPosition(PDFPageGraphicsPosition.OverExistingPageContent);

        pageCanvas.saveGraphicsState();

        PDFStringAppearanceOptions sao = new PDFStringAppearanceOptions();
        sao.setBrush(redBrush);
        sao.setFont(helvetica);
        PDFStringLayoutOptions slo = new PDFStringLayoutOptions();
        slo.setX(130);
        slo.setY(670);
        slo.setRotation(60);

        // Draw the watermark over page content but setting the transparency to a value lower than 1.
        // The page content will be partially visible through the watermark.
        PDFExtendedGraphicState gs1 = new PDFExtendedGraphicState();
        gs1.setFillAlpha(0.3);
        pageCanvas.setExtendedGraphicState(gs1);
        pageCanvas.drawString("Sample watermark over page content", sao, slo);

        pageCanvas.restoreGraphicsState();
    }

}
