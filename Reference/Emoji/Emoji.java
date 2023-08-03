import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.graphics.PDFStringHorizontalAlign;
import com.o2sol.pdf4java.graphics.fonts.PDFFont;
import com.o2sol.pdf4java.graphics.fonts.truetype.PDFTrueTypeFontFeatures;
import com.o2sol.pdf4java.graphics.fonts.truetype.PDFUnicodeTrueTypeFont;
import com.o2sol.pdf4java.graphics.fonts.type3.PDFType3Font;
import com.o2sol.pdf4java.graphics.formattedcontent.PDFFormattedContent;
import com.o2sol.pdf4java.graphics.formattedcontent.PDFFormattedParagraph;
import com.o2sol.pdf4java.graphics.formattedcontent.PDFFormattedTextBlock;

public class Emoji {
    public static void main(String[] args) {
        try {
            PDFTrueTypeFontFeatures fontFeatures = new PDFTrueTypeFontFeatures();
            fontFeatures.setEnableColorGlyphs(true);
            PDFUnicodeTrueTypeFont emojiTtf = new PDFUnicodeTrueTypeFont("..\\..\\SupportFiles\\SegoeUIEmoji.ttf", 24, true, fontFeatures);

            PDFFixedDocument document = new PDFFixedDocument();
            PDFPage page = document.addPage();

            PDFType3Font emojiType3 = new PDFType3Font(emojiTtf);
            emojiType3.setSize(24);
            emojiType3.createGlyphFromUnicodeCodePoint('A', 0x1F382); // Birthday cake
            emojiType3.createGlyphFromUnicodeCodePoint('B', 0x1F389); // Party Popper
            emojiType3.createGlyphFromUnicodeCodePoint('C', 0x1F973); // Face With Party Horn And Party Hat
            emojiType3.createGlyphFromUnicodeCodePoint('D', 0x1F37E); // Bottle With Popping Cork

            // Full emoji appearance
            PDFFormattedContent fc1 = BuildTextContent(emojiTtf, emojiType3, "A", "BCD");
            page.getCanvas().drawFormattedContent(fc1, 0, 50, page.getWidth(), page.getHeight());

            // Standard TrueType emoji appearance
            PDFFormattedContent fc2 = BuildTextContent(emojiTtf, emojiTtf,
                    String.valueOf(Character.toChars(0x1F382)),
                    String.valueOf(Character.toChars(0x1F389)) + String.valueOf(Character.toChars(0x1F973)) + String.valueOf(Character.toChars(0x1F37E)));
            page.getCanvas().drawFormattedContent(fc2, 0, 200, page.getWidth(), page.getHeight());

            document.save("Emoji.pdf");

            System.out.println("PDF document has been saved with success");
        } catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }

    private static PDFFormattedContent BuildTextContent(PDFFont standardTextFont, PDFFont emojiFont, String emojiText1, String emojiText2) {
        PDFFormattedContent fc = new PDFFormattedContent();
        PDFFormattedParagraph paragraph1 = new PDFFormattedParagraph();
        paragraph1.addBlock(new PDFFormattedTextBlock(emojiText1, emojiFont));
        fc.addParagraph(paragraph1);
        paragraph1.setHorizontalAlign(PDFStringHorizontalAlign.CENTER);
        paragraph1.setSpacingAfter(6);

        PDFFormattedParagraph paragraph2 = new PDFFormattedParagraph();
        paragraph2.addBlock(new PDFFormattedTextBlock("Happy Birthday!", standardTextFont));
        fc.addParagraph(paragraph2);
        paragraph2.setHorizontalAlign(PDFStringHorizontalAlign.CENTER);
        paragraph2.setSpacingAfter(6);

        PDFFormattedParagraph paragraph3 = new PDFFormattedParagraph();
        paragraph3.addBlock(new PDFFormattedTextBlock(emojiText2, emojiFont));
        fc.addParagraph(paragraph3);
        paragraph3.setHorizontalAlign(PDFStringHorizontalAlign.CENTER);

        return fc;
    }

}
