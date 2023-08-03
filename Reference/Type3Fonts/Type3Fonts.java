import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.graphics.PDFBrush;
import com.o2sol.pdf4java.graphics.PDFMatrix;
import com.o2sol.pdf4java.graphics.PDFPen;
import com.o2sol.pdf4java.graphics.PDFSize;
import com.o2sol.pdf4java.graphics.colors.PDFRgbColor;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFont;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFontFace;
import com.o2sol.pdf4java.graphics.fonts.type3.PDFType3Font;
import com.o2sol.pdf4java.graphics.fonts.type3.PDFType3Glyph;

public class Type3Fonts {
    public static void main(String[] args) {
        try {
            PDFFixedDocument document = new PDFFixedDocument();
            PDFPage page = document.addPage();

            PDFStandardFont helvetica = new PDFStandardFont(PDFStandardFontFace.HELVETICA, 20);
            PDFBrush blackBrush = new PDFBrush(PDFRgbColor.BLACK);
            page.getCanvas().drawString("The digits below, from 0 to 9, are drawn using a Type3 font.", helvetica, blackBrush, 50, 100);

            PDFType3Font t3 = new PDFType3Font("DemoT3");
            t3.setSize(24);
            t3.setFirstChar(' ');
            t3.setLastChar('9');
            t3.setFontMatrix(new PDFMatrix(0.01, 0, 0, 0.01, 0, 0));
            double[] widths = new double[] { 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100 };
            t3.setWidths(widths);

            PDFPen hollowPen = new PDFPen(null, 8);
            PDFBrush hollowBrush = new PDFBrush(null);
            // space
            PDFType3Glyph t3s = new PDFType3Glyph(0x20, new PDFSize(100, 100));
            t3.getGlyphs().add(t3s);
            // 0
            PDFType3Glyph t30 = new PDFType3Glyph(0x30, new PDFSize(100, 100));
            t30.getCanvas().drawRectangle(hollowPen, 5, 5, 90, 90);
            t30.getCanvas().compressAndClose();
            t3.getGlyphs().add(t30);
            // 1
            PDFType3Glyph t31 = new PDFType3Glyph(0x31, new PDFSize(100, 100));
            t31.getCanvas().drawRectangle(hollowPen, 5, 5, 90, 90);
            t31.getCanvas().drawEllipse(hollowBrush, 40, 40, 20, 20);
            t31.getCanvas().compressAndClose();
            t3.getGlyphs().add(t31);
            // 2
            PDFType3Glyph t32 = new PDFType3Glyph(0x32, new PDFSize(100, 100));
            t32.getCanvas().drawRectangle(hollowPen, 5, 5, 90, 90);
            t32.getCanvas().drawEllipse(hollowBrush, 15, 15, 20, 20);
            t32.getCanvas().drawEllipse(hollowBrush, 65, 65, 20, 20);
            t32.getCanvas().compressAndClose();
            t3.getGlyphs().add(t32);
            // 3
            PDFType3Glyph t33 = new PDFType3Glyph(0x33, new PDFSize(100, 100));
            t33.getCanvas().drawRectangle(hollowPen, 5, 5, 90, 90);
            t33.getCanvas().drawEllipse(hollowBrush, 15, 15, 20, 20);
            t33.getCanvas().drawEllipse(hollowBrush, 40, 40, 20, 20);
            t33.getCanvas().drawEllipse(hollowBrush, 65, 65, 20, 20);
            t33.getCanvas().compressAndClose();
            t3.getGlyphs().add(t33);
            // 4
            PDFType3Glyph t34 = new PDFType3Glyph(0x34, new PDFSize(100, 100));
            t34.getCanvas().drawRectangle(hollowPen, 5, 5, 90, 90);
            t34.getCanvas().drawEllipse(hollowBrush, 15, 15, 20, 20);
            t34.getCanvas().drawEllipse(hollowBrush, 65, 15, 20, 20);
            t34.getCanvas().drawEllipse(hollowBrush, 15, 65, 20, 20);
            t34.getCanvas().drawEllipse(hollowBrush, 65, 65, 20, 20);
            t34.getCanvas().compressAndClose();
            t3.getGlyphs().add(t34);
            // 5
            PDFType3Glyph t35 = new PDFType3Glyph(0x35, new PDFSize(100, 100));
            t35.getCanvas().drawRectangle(hollowPen, 5, 5, 90, 90);
            t35.getCanvas().drawEllipse(hollowBrush, 15, 15, 20, 20);
            t35.getCanvas().drawEllipse(hollowBrush, 65, 15, 20, 20);
            t35.getCanvas().drawEllipse(hollowBrush, 40, 40, 20, 20);
            t35.getCanvas().drawEllipse(hollowBrush, 15, 65, 20, 20);
            t35.getCanvas().drawEllipse(hollowBrush, 65, 65, 20, 20);
            t35.getCanvas().compressAndClose();
            t3.getGlyphs().add(t35);
            // 6
            PDFType3Glyph t36 = new PDFType3Glyph(0x36, new PDFSize(100, 100));
            t36.getCanvas().drawRectangle(hollowPen, 5, 5, 90, 90);
            t36.getCanvas().drawEllipse(hollowBrush, 15, 15, 20, 20);
            t36.getCanvas().drawEllipse(hollowBrush, 65, 15, 20, 20);
            t36.getCanvas().drawEllipse(hollowBrush, 15, 40, 20, 20);
            t36.getCanvas().drawEllipse(hollowBrush, 65, 40, 20, 20);
            t36.getCanvas().drawEllipse(hollowBrush, 15, 65, 20, 20);
            t36.getCanvas().drawEllipse(hollowBrush, 65, 65, 20, 20);
            t36.getCanvas().compressAndClose();
            t3.getGlyphs().add(t36);
            // 7
            PDFType3Glyph t37 = new PDFType3Glyph(0x37, new PDFSize(100, 100));
            t37.getCanvas().drawRectangle(hollowPen, 5, 5, 90, 90);
            t37.getCanvas().drawEllipse(hollowBrush, 15, 15, 20, 20);
            t37.getCanvas().drawEllipse(hollowBrush, 65, 15, 20, 20);
            t37.getCanvas().drawEllipse(hollowBrush, 15, 40, 20, 20);
            t37.getCanvas().drawEllipse(hollowBrush, 40, 40, 20, 20);
            t37.getCanvas().drawEllipse(hollowBrush, 65, 40, 20, 20);
            t37.getCanvas().drawEllipse(hollowBrush, 15, 65, 20, 20);
            t37.getCanvas().drawEllipse(hollowBrush, 65, 65, 20, 20);
            t37.getCanvas().compressAndClose();
            t3.getGlyphs().add(t37);
            // 8
            PDFType3Glyph t38 = new PDFType3Glyph(0x38, new PDFSize(100, 100));
            t38.getCanvas().drawRectangle(hollowPen, 5, 5, 90, 90);
            t38.getCanvas().drawEllipse(hollowBrush, 15, 15, 20, 20);
            t38.getCanvas().drawEllipse(hollowBrush, 40, 15, 20, 20);
            t38.getCanvas().drawEllipse(hollowBrush, 65, 15, 20, 20);
            t38.getCanvas().drawEllipse(hollowBrush, 15, 40, 20, 20);
            t38.getCanvas().drawEllipse(hollowBrush, 65, 40, 20, 20);
            t38.getCanvas().drawEllipse(hollowBrush, 15, 65, 20, 20);
            t38.getCanvas().drawEllipse(hollowBrush, 40, 65, 20, 20);
            t38.getCanvas().drawEllipse(hollowBrush, 65, 65, 20, 20);
            t38.getCanvas().compressAndClose();
            t3.getGlyphs().add(t38);
            // 9
            PDFType3Glyph t39 = new PDFType3Glyph(0x39, new PDFSize(100, 100));
            t39.getCanvas().drawRectangle(hollowPen, 5, 5, 90, 90);
            t39.getCanvas().drawEllipse(hollowBrush, 15, 15, 20, 20);
            t39.getCanvas().drawEllipse(hollowBrush, 40, 15, 20, 20);
            t39.getCanvas().drawEllipse(hollowBrush, 65, 15, 20, 20);
            t39.getCanvas().drawEllipse(hollowBrush, 15, 40, 20, 20);
            t39.getCanvas().drawEllipse(hollowBrush, 40, 40, 20, 20);
            t39.getCanvas().drawEllipse(hollowBrush, 65, 40, 20, 20);
            t39.getCanvas().drawEllipse(hollowBrush, 15, 65, 20, 20);
            t39.getCanvas().drawEllipse(hollowBrush, 40, 65, 20, 20);
            t39.getCanvas().drawEllipse(hollowBrush, 65, 65, 20, 20);
            t39.getCanvas().compressAndClose();
            t3.getGlyphs().add(t39);

            PDFBrush paleVioletRedbrush = new PDFBrush(PDFRgbColor.PALE_VIOLET_RED);
            page.getCanvas().drawString("0 1 2 3 4 5 6 7 8 9", t3, paleVioletRedbrush, 50, 150);
            PDFBrush midnightBluebrush = new PDFBrush(PDFRgbColor.MIDNIGHT_BLUE);
            page.getCanvas().drawString("0 1 2 3 4 5 6 7 8 9", t3, midnightBluebrush, 50, 200);

            document.save("Type3Fonts.pdf");

            System.out.println("PDF document has been saved with success");
        }
        catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
