import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.graphics.*;
import com.o2sol.pdf4java.graphics.colors.PDFRgbColor;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFont;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFontFace;
import com.o2sol.pdf4java.graphics.fonts.PDFTextRenderingMode;

public class Text {
    public static void main(String[] args) {
        try {
            PDFFixedDocument document = new PDFFixedDocument();
            PDFStandardFont helveticaBold = new PDFStandardFont(PDFStandardFontFace.HELVETICA_BOLD, 16);

            PDFPage page = document.addPage();
            DrawTextLines(page, helveticaBold);

            page = document.addPage();
            DrawTextWrap(page, helveticaBold);

            page = document.addPage();
            DrawTextRenderingModes(page, helveticaBold);

            document.save("Text.pdf");

            System.out.println("PDF document has been saved with success");
        }
        catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }

    private static void DrawTextLines(PDFPage page, PDFStandardFont titleFont) {
        PDFBrush brush = new PDFBrush();
        PDFPen redPen = new PDFPen(PDFRgbColor.RED, 0.5);
        PDFStandardFont helvetica = new PDFStandardFont(PDFStandardFontFace.HELVETICA, 12);
        PDFPageCanvas pageCanvas = page.getCanvas();
        
        pageCanvas.drawString("Text lines", titleFont, brush, 20, 50);

        pageCanvas.drawLine(redPen, 20, 70, 150, 70);
        pageCanvas.drawLine(redPen, 20, 70, 20, 80);
        pageCanvas.drawString("Simple text line with default top left text alignment and no rotation", helvetica, brush, 20, 70);

        pageCanvas.drawString("Text align", helvetica, brush, 20, 110);

        redPen.setDashPattern(new double[] { 1, 1 });
        pageCanvas.drawLine(redPen, 20, 125, 590, 125);
        pageCanvas.drawLine(redPen, 20, 165, 590, 165);
        pageCanvas.drawLine(redPen, 20, 205, 590, 205);
        pageCanvas.drawLine(redPen, 20, 125, 20, 205);
        pageCanvas.drawLine(redPen, 305, 125, 305, 205);
        pageCanvas.drawLine(redPen, 590, 125, 590, 205);

        PDFStringAppearanceOptions sao = new PDFStringAppearanceOptions();
        sao.setBrush(brush);
        sao.setFont(helvetica);
        PDFStringLayoutOptions slo = new PDFStringLayoutOptions();

        // Top left aligned text
        slo.setHorizontalAlign(PDFStringHorizontalAlign.LEFT);
        slo.setVerticalAlign(PDFStringVerticalAlign.TOP);
        slo.setX(20);
        slo.setY(125);
        pageCanvas.drawString("Top Left", sao, slo);

        // Top center aligned text
        slo.setHorizontalAlign(PDFStringHorizontalAlign.CENTER);
        slo.setVerticalAlign(PDFStringVerticalAlign.TOP);
        slo.setX(305);
        slo.setY(125);
        pageCanvas.drawString("Top Center", sao, slo);

        // Top right aligned text
        slo.setHorizontalAlign(PDFStringHorizontalAlign.RIGHT);
        slo.setVerticalAlign(PDFStringVerticalAlign.TOP);
        slo.setX(590);
        slo.setY(125);
        pageCanvas.drawString("Top Right", sao, slo);

        // Middle left aligned text
        slo.setHorizontalAlign(PDFStringHorizontalAlign.LEFT);
        slo.setVerticalAlign(PDFStringVerticalAlign.MIDDLE);
        slo.setX(20);
        slo.setY(165);
        pageCanvas.drawString("Middle Left", sao, slo);

        // Middle center aligned text
        slo.setHorizontalAlign(PDFStringHorizontalAlign.CENTER);
        slo.setVerticalAlign(PDFStringVerticalAlign.MIDDLE);
        slo.setX(305);
        slo.setY(165);
        pageCanvas.drawString("Middle Center", sao, slo);

        // Middle right aligned text
        slo.setHorizontalAlign(PDFStringHorizontalAlign.RIGHT);
        slo.setVerticalAlign(PDFStringVerticalAlign.MIDDLE);
        slo.setX(590);
        slo.setY(165);
        pageCanvas.drawString("Middle Right", sao, slo);

        // Bottom left aligned text
        slo.setHorizontalAlign(PDFStringHorizontalAlign.LEFT);
        slo.setVerticalAlign(PDFStringVerticalAlign.BOTTOM);
        slo.setX(20);
        slo.setY(205);
        pageCanvas.drawString("Bottom Left", sao, slo);

        // Bottom center aligned text
        slo.setHorizontalAlign(PDFStringHorizontalAlign.CENTER);
        slo.setVerticalAlign(PDFStringVerticalAlign.BOTTOM);
        slo.setX(305);
        slo.setY(205);
        pageCanvas.drawString("Bottom Center", sao, slo);

        // Bottom right aligned text
        slo.setHorizontalAlign(PDFStringHorizontalAlign.RIGHT);
        slo.setVerticalAlign(PDFStringVerticalAlign.BOTTOM);
        slo.setX(590);
        slo.setY(205);
        pageCanvas.drawString("Bottom Right", sao, slo);

        pageCanvas.drawString("Text rotation", helvetica, brush, 20, 250);

        redPen.setDashPattern(new double[] { 1, 1 });
        pageCanvas.drawLine(redPen, 20, 265, 590, 265);
        pageCanvas.drawLine(redPen, 20, 305, 590, 305);
        pageCanvas.drawLine(redPen, 20, 345, 590, 345);
        pageCanvas.drawLine(redPen, 20, 265, 20, 345);
        pageCanvas.drawLine(redPen, 305, 265, 305, 345);
        pageCanvas.drawLine(redPen, 590, 265, 590, 345);

        slo.setRotation(30);
        // Top left aligned text
        slo.setHorizontalAlign(PDFStringHorizontalAlign.LEFT);
        slo.setVerticalAlign(PDFStringVerticalAlign.TOP);
        slo.setX(20);
        slo.setY(265);
        pageCanvas.drawString("Top Left", sao, slo);

        // Top center aligned text
        slo.setHorizontalAlign(PDFStringHorizontalAlign.CENTER);
        slo.setVerticalAlign(PDFStringVerticalAlign.TOP);
        slo.setX(305);
        slo.setY(265);
        pageCanvas.drawString("Top Center", sao, slo);

        // Top right aligned text
        slo.setHorizontalAlign(PDFStringHorizontalAlign.RIGHT);
        slo.setVerticalAlign(PDFStringVerticalAlign.TOP);
        slo.setX(590);
        slo.setY(265);
        pageCanvas.drawString("Top Right", sao, slo);

        // Middle left aligned text
        slo.setHorizontalAlign(PDFStringHorizontalAlign.LEFT);
        slo.setVerticalAlign(PDFStringVerticalAlign.MIDDLE);
        slo.setX(20);
        slo.setY(305);
        pageCanvas.drawString("Middle Left", sao, slo);

        // Middle center aligned text
        slo.setHorizontalAlign(PDFStringHorizontalAlign.CENTER);
        slo.setVerticalAlign(PDFStringVerticalAlign.MIDDLE);
        slo.setX(305);
        slo.setY(305);
        pageCanvas.drawString("Middle Center", sao, slo);

        // Middle right aligned text
        slo.setHorizontalAlign(PDFStringHorizontalAlign.RIGHT);
        slo.setVerticalAlign(PDFStringVerticalAlign.MIDDLE);
        slo.setX(590);
        slo.setY(305);
        pageCanvas.drawString("Middle Right", sao, slo);

        // Bottom left aligned text
        slo.setHorizontalAlign(PDFStringHorizontalAlign.LEFT);
        slo.setVerticalAlign(PDFStringVerticalAlign.BOTTOM);
        slo.setX(20);
        slo.setY(345);
        pageCanvas.drawString("Bottom Left", sao, slo);

        // Bottom center aligned text
        slo.setHorizontalAlign(PDFStringHorizontalAlign.CENTER);
        slo.setVerticalAlign(PDFStringVerticalAlign.BOTTOM);
        slo.setX(305);
        slo.setY(345);
        pageCanvas.drawString("Bottom Center", sao, slo);

        // Bottom right aligned text
        slo.setHorizontalAlign(PDFStringHorizontalAlign.RIGHT);
        slo.setVerticalAlign(PDFStringVerticalAlign.BOTTOM);
        slo.setX(590);
        slo.setY(345);
        pageCanvas.drawString("Bottom Right", sao, slo);

        pageCanvas.compressAndClose();
    }

    private static void DrawTextWrap(PDFPage page, PDFStandardFont titleFont)
    {
        PDFBrush brush = new PDFBrush();
        PDFPen redPen = new PDFPen(PDFRgbColor.RED, 0.5);
        PDFStandardFont helvetica = new PDFStandardFont(PDFStandardFontFace.HELVETICA, 12);
        PDFPageCanvas pageCanvas = page.getCanvas();

        pageCanvas.drawString("Text wrapping", titleFont, brush, 20, 50);

        pageCanvas.drawLine(redPen, 20, 70, 20, 150);
        pageCanvas.drawLine(redPen, 300, 70, 300, 150);
        pageCanvas.drawLine(redPen, 20, 70, 300, 70);

        PDFStringAppearanceOptions sao = new PDFStringAppearanceOptions();
        sao.setBrush(brush);
        sao.setFont(helvetica);

        // Height is not set, text has no vertical limit.
        PDFStringLayoutOptions slo = new PDFStringLayoutOptions();
        slo.setHorizontalAlign(PDFStringHorizontalAlign.JUSTIFIED);
        slo.setVerticalAlign(PDFStringVerticalAlign.TOP);
        slo.setX(20);
        slo.setY(70);
        slo.setWidth(280);
        String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Sed vel euismod risus. Fusce viverra, nisi auctor ullamcorper porttitor, " +
                "ipsum lacus lobortis metus, sit amet dictum lacus velit nec diam. " +
                "Morbi arcu diam, euismod a auctor nec, aliquam in lectus." +
                "Ut ultricies iaculis augue sit amet adipiscing. Aenean blandit tortor a nisi " +
                "dignissim fermentum id adipiscing mauris. Aenean libero turpis, varius nec ultricies " +
                "faucibus, pretium quis lectus. Morbi mollis lorem vel erat condimentum mattis mollis " +
                "nulla sollicitudin. Nunc ut massa id felis laoreet feugiat eget at eros.";
        pageCanvas.drawString(text, sao, slo);

        pageCanvas.drawLine(redPen, 310, 70, 310, 147);
        pageCanvas.drawLine(redPen, 590, 70, 590, 147);
        pageCanvas.drawLine(redPen, 310, 70, 590, 70);
        pageCanvas.drawLine(redPen, 310, 147, 590, 147);

        // Height is set, text is limited on vertical.
        slo.setX(310);
        slo.setY(70);
        slo.setWidth(280);
        slo.setHeight(77);
        pageCanvas.drawString(text, sao, slo);

        PDFPath clipPath = new PDFPath();
        clipPath.addRectangle(310, 160, 280, 77);
        pageCanvas.drawPath(redPen, clipPath);

        pageCanvas.saveGraphicsState();
        pageCanvas.setClip(clipPath);

        // Height is not set but text is cliped on vertical.
        slo.setX(310);
        slo.setY(160);
        slo.setWidth(280);
        slo.setHeight(0);
        pageCanvas.drawString(text, sao, slo);

        pageCanvas.restoreGraphicsState();

        pageCanvas.drawLine(redPen, 10, 400, 300, 400);
        pageCanvas.drawLine(redPen, 20, 300, 20, 500);
        // Wrapped text is always rotated around top left corner, no matter the text alignment
        pageCanvas.drawRectangle(redPen, 20, 400, 280, 80, 30);
        slo.setX(20);
        slo.setY(400);
        slo.setWidth(280);
        slo.setHeight(80);
        slo.setRotation(30);
        pageCanvas.drawString(text, sao, slo);

        pageCanvas.drawLine(redPen, 310, 600, 590, 600);
        pageCanvas.drawLine(redPen, 450, 450, 450, 750);

        // Rotation around the center of the box requires some affine transformations.
        pageCanvas.saveGraphicsState();
        pageCanvas.translateTransform(450, 600);
        pageCanvas.rotateTransform(30);
        pageCanvas.drawRectangle(redPen, -140, -40, 280, 80);
        slo.setX(-140);
        slo.setY(-40);
        slo.setWidth(280);
        slo.setHeight(80);
        slo.setRotation(0);
        pageCanvas.drawString(text, sao, slo);

        pageCanvas.restoreGraphicsState();
    }

    private static void DrawTextRenderingModes(PDFPage page, PDFStandardFont titleFont)
    {
        PDFBrush brush = new PDFBrush();
        PDFPen redPen = new PDFPen(PDFRgbColor.RED, 0.5);
        PDFStandardFont helvetica = new PDFStandardFont(PDFStandardFontFace.HELVETICA, 12);
        PDFStandardFont helveticaBold = new PDFStandardFont(PDFStandardFontFace.HELVETICA, 80);
        PDFPageCanvas pageCanvas = page.getCanvas();

        pageCanvas.drawString("Text rendering modes", titleFont, brush, 20, 50);

        pageCanvas.drawString("Fill text", helvetica, brush, 20, 90);
        pageCanvas.drawString("Stroke text", helvetica, brush, 20, 160);
        pageCanvas.drawString("Fill and stroke text", helvetica, brush, 20, 230);
        pageCanvas.drawString("Invisible text", helvetica, brush, 20, 300);
        pageCanvas.drawString("Fill and clip text", helvetica, brush, 20, 370);
        pageCanvas.drawString("Stroke and clip text", helvetica, brush, 20, 440);
        pageCanvas.drawString("Fill, stroke and clip text", helvetica, brush, 20, 510);
        pageCanvas.drawString("Clip text", helvetica, brush, 20, 580);

        // Fill text - text interior is filled because only the brush is available for drawing. 
        pageCanvas.drawString("A B C", helveticaBold, brush, 300, 90);

        // Stroke text - text outline is stroked becuase only the pen is available for drawing.
        PDFStringLayoutOptions slo = new PDFStringLayoutOptions();
        slo.setX(300);
        slo.setY(160);
        PDFStringAppearanceOptions sao = new PDFStringAppearanceOptions();
        sao.setFont(helveticaBold);
        sao.setPen(redPen);
        sao.setBrush(null);
        pageCanvas.drawString("A B C", sao, slo);

        // Fill and stroke text - text interior is filled and text outline is stroked 
        // because both pen and brush are available.
        slo.setY(230);
        sao.setPen(redPen);
        sao.setBrush(brush);
        pageCanvas.drawString("A B C", sao, slo);

        // Invisible text - text is not displayed because both pen and brush are not available.
        slo.setY(300);
        sao.setPen(null);
        sao.setBrush(null);
        pageCanvas.drawString("A B C", sao, slo);

        // Fill and clip text - text interior is filled and then text outline is added to current clipping path.
        pageCanvas.saveGraphicsState();
        helveticaBold.setTextRenderingMode(PDFTextRenderingMode.FILL_AND_CLIP_TEXT);
        slo.setY(370);
        sao.setPen(null);
        sao.setBrush(brush);
        pageCanvas.drawString("A B C", sao, slo);
        DrawHorizontalLines(pageCanvas, redPen, slo.getX(), slo.getY(), 250, 70);
        pageCanvas.restoreGraphicsState();

        // Stroke and clip text - text outline is stroked and then text outline is added to current clipping path.
        pageCanvas.saveGraphicsState();
        helveticaBold.setTextRenderingMode(PDFTextRenderingMode.STROKE_AND_CLIP_TEXT);
        slo.setY(440);
        sao.setPen(redPen);
        sao.setBrush(null);
        pageCanvas.drawString("A B C", sao, slo);
        DrawHorizontalLines(pageCanvas, redPen, slo.getX(), slo.getY(), 250, 70);
        pageCanvas.restoreGraphicsState();

        // Fill, Stroke and clip text - text interior is filled, text outline is stroked and then text outline is added to current clipping path.
        pageCanvas.saveGraphicsState();
        helveticaBold.setTextRenderingMode(PDFTextRenderingMode.FILL_STROKE_AND_CLIP_TEXT);
        slo.setY(510);
        sao.setPen(redPen);
        sao.setBrush(brush);
        pageCanvas.drawString("A B C", sao, slo);
        DrawHorizontalLines(pageCanvas, redPen, slo.getX(), slo.getY(), 250, 70);
        pageCanvas.restoreGraphicsState();

        // Clip text - text outline is added to current clipping path.
        pageCanvas.saveGraphicsState();
        helveticaBold.setTextRenderingMode(PDFTextRenderingMode.CLIP_TEXT);
        slo.setY(580);
        sao.setPen(redPen);
        sao.setBrush(brush);
        pageCanvas.drawString("A B C", sao, slo);
        DrawHorizontalLines(pageCanvas, redPen, slo.getX(), slo.getY(), 250, 70);
        pageCanvas.restoreGraphicsState();
    }

    private static void DrawHorizontalLines(PDFCanvas canvas, PDFPen pen, double x, double y, double width, double height)
    {
        for (double i = 0; i < height; i = i + 5)
        {
            canvas.drawLine(pen, x, y + i, x + width, y + i);
        }
    }

}
