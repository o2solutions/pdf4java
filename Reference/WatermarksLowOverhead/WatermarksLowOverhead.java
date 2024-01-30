import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.core.*;
import com.o2sol.pdf4java.graphics.*;
import com.o2sol.pdf4java.graphics.colors.PDFRgbColor;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFont;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFontFace;

public class WatermarksLowOverhead {
    public static void main(String[] args) {
        try {
            PDFFileEx pdfFile = new PDFFileEx("..\\..\\SupportFiles\\content.pdf", null);

            PDFPageCanvas pageCanvas = pdfFile.getPageCanvas(0, PDFPageCanvasPosition.UNDER_EXISTING_PAGE_CONTENT);
            DrawWatermarkUnderPageContent(pageCanvas);
            pageCanvas.compressAndClose();

            pageCanvas = pdfFile.getPageCanvas(1, PDFPageCanvasPosition.OVER_EXISTING_PAGE_CONTENT);
            DrawWatermarkOverPageContent(pageCanvas);
            pageCanvas.compressAndClose();

            pageCanvas = pdfFile.getPageCanvas(2, PDFPageCanvasPosition.OVER_EXISTING_PAGE_CONTENT);
            DrawWatermarkWithTransparency(pageCanvas);
            pageCanvas.compressAndClose();

            pdfFile.save("WatermarksLowOverhead.pdf", null, false);

            System.out.println("PDF document has been saved with success");
        }
        catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }

    private static void DrawWatermarkUnderPageContent(PDFPageCanvas pageCanvas)
    {
        PDFBrush redBrush = new PDFBrush(new PDFRgbColor(192, 0, 0));
        PDFStandardFont helvetica = new PDFStandardFont(PDFStandardFontFace.HELVETICA_BOLD, 36);

        PDFStringAppearanceOptions sao = new PDFStringAppearanceOptions();
        sao.setBrush(redBrush);
        sao.setFont(helvetica);
        PDFStringLayoutOptions slo = new PDFStringLayoutOptions();
        slo.setX(130);
        slo.setY(670);
        slo.setRotation(60);
        pageCanvas.drawString("Sample watermark under page content", sao, slo);
    }

    /// <summary>
    ///
    /// </summary>
    /// <param name="pageCanvas"></param>
    private static void DrawWatermarkOverPageContent(PDFPageCanvas pageCanvas)
    {
        PDFBrush redBrush = new PDFBrush(new PDFRgbColor(192, 0, 0));
        PDFStandardFont helvetica = new PDFStandardFont(PDFStandardFontFace.HELVETICA_BOLD, 32);

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

    /// <summary>
    ///
    /// </summary>
    /// <param name="pageCanvas"></param>
    private static void DrawWatermarkWithTransparency(PDFPageCanvas pageCanvas)
    {
        PDFBrush redBrush = new PDFBrush(new PDFRgbColor(192, 0, 0));
        PDFStandardFont helvetica = new PDFStandardFont(PDFStandardFontFace.HELVETICA_BOLD, 36);

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
