import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.graphics.*;
import com.o2sol.pdf4java.graphics.colors.PDFRgbColor;
import com.o2sol.pdf4java.graphics.fonts.truetype.PDFUnicodeTrueTypeFont;
import com.o2sol.pdf4java.graphics.fonts.truetype.PDFTrueTypeFontFeatures;

public class AdvancedFontFeatures {
    public static void main(String[] args) {
        try {
            PDFTrueTypeFontFeatures fontFeatures = new PDFTrueTypeFontFeatures();
            fontFeatures.setEnableStandardLigatures(true);
            fontFeatures.setEnableVerticalGlyphs(true);
            fontFeatures.setEnableSmallCapsForLowercase(true);
            fontFeatures.setEnableSmallCapsForUppercase(true);
            fontFeatures.setEnableOldStyleFigures(true);
            PDFUnicodeTrueTypeFont ttf = new PDFUnicodeTrueTypeFont("..\\..\\SupportFiles\\calibri.ttf", 24, true, fontFeatures);
            PDFBrush blackBrush = new PDFBrush(PDFRgbColor.BLACK);

            PDFFixedDocument document = new PDFFixedDocument();

            PDFPage page = document.addPage();
            displayStandardLigatures(page, blackBrush, ttf);

            page = document.addPage();
            displayVerticalGlyphs(page, blackBrush, ttf);

            page = document.addPage();
            displaySmallCapitals(page, blackBrush);

            page = document.addPage();
            displayOldStyleFigures(page, blackBrush);

            document.save("AdvancedFontFeatures.pdf");

            System.out.println("PDF document has been saved with success");
        }
        catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }

    public static void displayStandardLigatures(PDFPage page, PDFBrush blackBrush, PDFUnicodeTrueTypeFont font) {
        font.getFontFeatures().setEnableStandardLigatures(true);
        page.getCanvas().drawString("Standard ligatures enabled:", font, blackBrush, 50, 50);
        page.getCanvas().drawString("f f i - ffi", font, blackBrush, 50, 75);
        page.getCanvas().drawString("f i - fi", font, blackBrush, 50, 100);
        page.getCanvas().drawString("f l - fl", font, blackBrush, 50, 125);

        font.getFontFeatures().setEnableStandardLigatures(false);
        page.getCanvas().drawString("Standard ligatures disabled:", font, blackBrush, 50, 200);
        page.getCanvas().drawString("f f i - ffi", font, blackBrush, 50, 225);
        page.getCanvas().drawString("f i - fi", font, blackBrush, 50, 250);
        page.getCanvas().drawString("f l - fl", font, blackBrush, 50, 275);
    }

    public static void displayVerticalGlyphs(PDFPage page, PDFBrush blackBrush, PDFUnicodeTrueTypeFont font)
    {
        PDFTrueTypeFontFeatures fontFeatures = new PDFTrueTypeFontFeatures();
        fontFeatures.setEnableVerticalGlyphs(true);
        // File NotoSansCJKjp-Regular.ttf is very large and it has not been included in the instal kit.
        // It can be downloaded here: https://o2sol.com/downoad/samples/NotoSansCJKjp-Regular.ttf
        PDFUnicodeTrueTypeFont ttf = new PDFUnicodeTrueTypeFont("..\\..\\SupportFiles\\NotoSansCJKjp-Regular.ttf", 48, true, fontFeatures);

        ttf.getFontFeatures().setEnableVerticalGlyphs(false);
        page.getCanvas().drawString("Horizontal text:", font, blackBrush, 50, 75);
        page.getCanvas().drawString("\uFF08\u3303\uFF09", ttf, blackBrush, 50, 100);

        ttf.getFontFeatures().setEnableVerticalGlyphs(true);
        PDFStringAppearanceOptions sao = new PDFStringAppearanceOptions();
        sao.setBrush(blackBrush);
        sao.setFont(ttf);
        PDFStringLayoutOptions slo = new PDFStringLayoutOptions();
        slo.setX(50);
        slo.setY(185);
        slo.setWidth(9999);
        slo.setHeight(9999);
        page.getCanvas().drawString("Vertical text (vertical glyphs enabled):", font, blackBrush, 50, 175);
        page.getCanvas().drawString("\uFF08\n\u3303\n\uFF09", sao, slo);

        ttf.getFontFeatures().setEnableVerticalGlyphs(false);
        slo.setY(375);
        page.getCanvas().drawString("Vertical text (vertical glyphs disabled):", font, blackBrush, 50, 350);
        page.getCanvas().drawString("\uFF08\n\u3303\n\uFF09", sao, slo);
    }

    public static void displaySmallCapitals(PDFPage page, PDFBrush blackBrush)
    {
        PDFTrueTypeFontFeatures fontFeatures = new PDFTrueTypeFontFeatures();
        fontFeatures.setEnableSmallCapsForLowercase(true);
        fontFeatures.setEnableSmallCapsForUppercase(true);
        PDFUnicodeTrueTypeFont font = new PDFUnicodeTrueTypeFont("..\\..\\SupportFiles\\arial.ttf", 24, true, fontFeatures);

        font.getFontFeatures().setEnableSmallCapsForUppercase(false);
        page.getCanvas().drawString("UPPERCASE - REGULAR", font, blackBrush, 50, 75);
        font.getFontFeatures().setEnableSmallCapsForUppercase(true);
        page.getCanvas().drawString("UPPERCASE - SMALL CAPS", font, blackBrush, 50, 105);

        font.getFontFeatures().setEnableSmallCapsForUppercase(false);
        font.getFontFeatures().setEnableSmallCapsForLowercase(false);
        page.getCanvas().drawString("Lowercase - Regular", font, blackBrush, 50, 150);
        font.getFontFeatures().setEnableSmallCapsForLowercase(true);
        page.getCanvas().drawString("Lowercase - Small Caps", font, blackBrush, 50, 180);

    }

    public static void displayOldStyleFigures(PDFPage page, PDFBrush blackBrush)
    {
        PDFTrueTypeFontFeatures fontFeatures = new PDFTrueTypeFontFeatures();
        fontFeatures.setEnableOldStyleFigures(true);
        PDFUnicodeTrueTypeFont font = new PDFUnicodeTrueTypeFont("..\\..\\SupportFiles\\arial.ttf", 24, true, fontFeatures);

        font.getFontFeatures().setEnableOldStyleFigures(true);
        page.getCanvas().drawString("0123456789 - old style figures", font, blackBrush, 50, 70);
        font.getFontFeatures().setEnableOldStyleFigures(false);
        page.getCanvas().drawString("0123456789 - default figures", font, blackBrush, 50, 105);
    }

}
