import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.graphics.PDFBrush;
import com.o2sol.pdf4java.graphics.colors.PDFRgbColor;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFont;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFontFace;
import com.o2sol.pdf4java.graphics.fonts.truetype.PDFTrueTypeFontFeatures;
import com.o2sol.pdf4java.graphics.fonts.truetype.PDFUnicodeTrueTypeFont;
import com.o2sol.pdf4java.graphics.fonts.type3.PDFType3Font;

public class SVGFont {
    public static void main(String[] args) {
        try {
            PDFTrueTypeFontFeatures fontFeatures = new PDFTrueTypeFontFeatures();
            fontFeatures.setEnableColorGlyphs(true);
            PDFStandardFont titlefont = new PDFStandardFont(PDFStandardFontFace.HELVETICA, 12);
            PDFUnicodeTrueTypeFont svgTtf = new PDFUnicodeTrueTypeFont("..\\..\\SupportFiles\\DigitaltsOrange.ttf", 24, true, fontFeatures);
            PDFBrush blackBrush = new PDFBrush(PDFRgbColor.BLACK);
            PDFBrush darkRedBrush = new PDFBrush(PDFRgbColor.DARK_RED);

            PDFFixedDocument document = new PDFFixedDocument();
            PDFPage page = document.addPage();

            PDFType3Font svgType3 = new PDFType3Font(svgTtf);
            svgType3.setSize(24);
            svgType3.createGlyphFromUnicodeCodePoint((byte)'C', 'C');
            svgType3.createGlyphFromUnicodeCodePoint((byte)'r', 'r');
            svgType3.createGlyphFromUnicodeCodePoint((byte)'e', 'e');
            svgType3.createGlyphFromUnicodeCodePoint((byte)'a', 'a');
            svgType3.createGlyphFromUnicodeCodePoint((byte)'t', 't');
            svgType3.createGlyphFromUnicodeCodePoint((byte)'d', 'd');
            svgType3.createGlyphFromUnicodeCodePoint((byte)' ', ' ');
            svgType3.createGlyphFromUnicodeCodePoint((byte)'w', 'w');
            svgType3.createGlyphFromUnicodeCodePoint((byte)'i', 'i');
            svgType3.createGlyphFromUnicodeCodePoint((byte)'h', 'h');
            svgType3.createGlyphFromUnicodeCodePoint((byte)'P', 'P');
            svgType3.createGlyphFromUnicodeCodePoint((byte)'D', 'D');
            svgType3.createGlyphFromUnicodeCodePoint((byte)'F', 'F');
            svgType3.createGlyphFromUnicodeCodePoint((byte)'4', '4');
            svgType3.createGlyphFromUnicodeCodePoint((byte)'J', 'J');
            svgType3.createGlyphFromUnicodeCodePoint((byte)'v', 'v');

            // Full SVG glyph appearance
            page.getCanvas().drawString("Full SVG glyph appearance (text color is given in SVG, brush has no effect)", titlefont, blackBrush, 50, 75);
            page.getCanvas().drawString("Created with PDF4Java", svgType3, darkRedBrush, 50, 90);


            // Standard TrueType glyph appearance
            page.getCanvas().drawString("Standard TrueType glyph appearance (text color is given by the brush)", titlefont, blackBrush, 50, 150);
            page.getCanvas().drawString("Created with PDF4Java", svgTtf, darkRedBrush, 50, 165);

            document.save("SVGFont.pdf");

            System.out.println("PDF document has been saved with success");
        }
        catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
