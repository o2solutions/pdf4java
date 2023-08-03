import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.graphics.*;
import com.o2sol.pdf4java.graphics.colors.PDFRgbColor;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFont;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFontFace;
import com.o2sol.pdf4java.spatial.*;

public class Measurements {
    public static void main(String[] args) {
        try {
            // Create the pdf document
            PDFFixedDocument document = new PDFFixedDocument();
            // Create a new page in the document
            PDFPage page = document.getPages().add();

            PDFPen blackPen = new PDFPen(PDFRgbColor.BLACK, 1);
            PDFPen redPen = new PDFPen(PDFRgbColor.RED, 4);
            PDFPen greenPen = new PDFPen(PDFRgbColor.GREEN, 2);
            PDFBrush blackBrush = new PDFBrush(PDFRgbColor.BLACK);
            PDFStandardFont helvetica = new PDFStandardFont(PDFStandardFontFace.HELVETICA, 16);
            PDFCanvas canvas = page.getCanvas();

            // Draw viewport border.
            canvas.drawRectangle(blackPen, 50, 50, 500, 500);
            // Draw the line to be measured.
            canvas.drawLine(greenPen, 70, 70, 530, 530);
            // Draw point A (line start) in the viewport.
            canvas.drawLine(redPen, 60, 70, 80, 70);
            canvas.drawLine(redPen, 70, 60, 70, 80);
            // Draw point B (line end) in the viewport.
            canvas.drawLine(redPen, 520, 530, 540, 530);
            canvas.drawLine(redPen, 530, 520, 530, 540);

            canvas.drawString("A", helvetica, blackBrush, 85, 65);
            canvas.drawString("B", helvetica, blackBrush, 505, 525);
            canvas.drawString("Viewport", helvetica, blackBrush, 50, 560);
            helvetica.setSize(10);
            canvas.drawString(
                    "Open the file with Adobe Acrobat and measure the distance from A to B using the Distance tool.",
                    helvetica, blackBrush, 50, 580);
            canvas.drawString("The measured distance should be 9 mi 186 ft 1 1/4 in.",
                    helvetica, blackBrush, 50, 590);

            // Create a viewport that matches the rectangle above.
            PDFViewport vp = new PDFViewport();
            vp.setName("Sample viewport");
            PDFPoint ll = page.convertDisplayPointToStandardPoint(new PDFPoint(50, 50));
            PDFPoint ur = page.convertDisplayPointToStandardPoint(new PDFPoint(550, 550));
            vp.setBounds(new PDFStandardRectangle(ll, ur));

            // Add the viewport to the page
            page.setViewports(new PDFViewportCollection());
            page.getViewports().add(vp);

            // Create a rectilinear measure for the viewport (CAD drawing for example).
            PDFRectilinearMeasure rlm = new PDFRectilinearMeasure();
            // Attach the measure to the viewport.
            vp.setMeasure(rlm);
            // Set the measure scale: 1 inch (72 points) in PDF corresponds to 1 mile
            rlm.setScaleRatio("1 in = 1 mi");

            // Create a number format that controls the display of units for X axis.
            PDFNumberFormat xNumberFormat = new PDFNumberFormat();
            xNumberFormat.setMeasureUnit("mi");
            xNumberFormat.setConversionFactor(1/72.0); // Conversion from user space units to miles
            xNumberFormat.setFractionDisplay(PDFFractionDisplay.DECIMAL);
            xNumberFormat.setPrecision(100000);
            rlm.setX(new PDFNumberFormatCollection());
            rlm.getX().add(xNumberFormat);

            // Create a chain of number formats that control the display of units for distance.
            rlm.setDistance(new PDFNumberFormatCollection());
            PDFNumberFormat miNumberFormat = new PDFNumberFormat();
            miNumberFormat.setMeasureUnit("mi");
            miNumberFormat.setConversionFactor(1); // Initial unit is miles; no conversion needed
            rlm.getDistance().add(miNumberFormat);
            PDFNumberFormat ftNumberFormat = new PDFNumberFormat();
            ftNumberFormat.setMeasureUnit("ft");
            ftNumberFormat.setConversionFactor(5280); // Conversion from miles to feet
            rlm.getDistance().add(ftNumberFormat);
            PDFNumberFormat inNumberFormat = new PDFNumberFormat();
            inNumberFormat.setMeasureUnit("in");
            inNumberFormat.setConversionFactor(12); // Conversion from feet to inches
            inNumberFormat.setFractionDisplay(PDFFractionDisplay.FRACTION);
            inNumberFormat.setDenominator(8); // Fractions of inches rounded to nearest 1/8
            rlm.getDistance().add(inNumberFormat);

            // Create a number format that controls the display of units area.
            PDFNumberFormat areaNumberFormat = new PDFNumberFormat();
            areaNumberFormat.setMeasureUnit("acres");
            areaNumberFormat.setConversionFactor(640); // Conversion from square miles to acres
            rlm.setArea(new PDFNumberFormatCollection());
            rlm.getArea().add(xNumberFormat);

            document.save("Measurements.pdf");

            System.out.println("PDF document has been saved with success");
        } catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
