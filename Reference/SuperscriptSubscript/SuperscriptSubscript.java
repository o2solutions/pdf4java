import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.flowdocument.*;
import com.o2sol.pdf4java.graphics.PDFStringHorizontalAlign;
import com.o2sol.pdf4java.graphics.fonts.truetype.PDFAnsiTrueTypeFont;
import com.o2sol.pdf4java.graphics.formattedcontent.*;

public class SuperscriptSubscript {
    public static void main(String[] args) {
        try {
            PDFAnsiTrueTypeFont verdana = new PDFAnsiTrueTypeFont("..\\..\\SupportFiles\\verdana.ttf", 36, true);

            PDFFlowDocument document = new PDFFlowDocument();

            PDFFlowContent superscriptSection = buildSuperscript(verdana);
            document.addContent(superscriptSection);

            PDFFlowContent subscriptSection = buildSubscript(verdana);
            document.addContent(subscriptSection);


            document.save("SuperscriptSubscript.pdf");

            System.out.println("PDF document has been saved with success");
        }
        catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }

    private static PDFFlowContent buildSuperscript(PDFAnsiTrueTypeFont font) {
        PDFAnsiTrueTypeFont fontSuperscript = new PDFAnsiTrueTypeFont(font, 18);
        PDFAnsiTrueTypeFont fontRegular = new PDFAnsiTrueTypeFont(font, 12);

        PDFFormattedContent content = new PDFFormattedContent();
        PDFFlowTextContent flowText = new PDFFlowTextContent(content);

        PDFFormattedTextBlock titleBlock = new PDFFormattedTextBlock("Superscript text", fontRegular);
        content.addParagraph(new PDFFormattedParagraph(new PDFFormattedBlock[]{ titleBlock }));
        content.addParagraph(new PDFFormattedParagraph(" "));

        PDFFormattedTextBlock xBlock = new PDFFormattedTextBlock("X", font);
        xBlock.getSuperscript().add(new PDFFormattedTextBlock("2", fontSuperscript));
        PDFFormattedTextBlock yBlock = new PDFFormattedTextBlock(" + Y", font);
        yBlock.getSuperscript().add(new PDFFormattedTextBlock("2", fontSuperscript));
        PDFFormattedTextBlock zBlock = new PDFFormattedTextBlock(" = Z", font);
        zBlock.getSuperscript().add(new PDFFormattedTextBlock("2", fontSuperscript));

        PDFFormattedParagraph paragraph = new PDFFormattedParagraph(new PDFFormattedBlock[] { xBlock, yBlock, zBlock } );
        paragraph.setHorizontalAlign(PDFStringHorizontalAlign.CENTER);
        content.addParagraph(paragraph);

        return flowText;
    }

    private static PDFFlowContent buildSubscript(PDFAnsiTrueTypeFont font) {
        PDFAnsiTrueTypeFont fontSubscript = new PDFAnsiTrueTypeFont(font, 18);
        PDFAnsiTrueTypeFont fontRegular = new PDFAnsiTrueTypeFont(font, 12);

        PDFFormattedContent content = new PDFFormattedContent();
        PDFFlowTextContent flowText = new PDFFlowTextContent(content);
        flowText.setOuterMargins(new PDFFlowContentMargins(0, 0, 36, 0));

        PDFFormattedTextBlock titleBlock = new PDFFormattedTextBlock("Subscript text", fontRegular);
        content.addParagraph(new PDFFormattedParagraph(new PDFFormattedBlock[] { titleBlock }));
        content.addParagraph(new PDFFormattedParagraph(" "));

        PDFFormattedTextBlock block1 = new PDFFormattedTextBlock("Y = X", font);
        block1.getSubscript().add(new PDFFormattedTextBlock("1", fontSubscript));
        PDFFormattedTextBlock block2 = new PDFFormattedTextBlock(" + X", font);
        block2.getSubscript().add(new PDFFormattedTextBlock("2", fontSubscript));
        PDFFormattedTextBlock block3 = new PDFFormattedTextBlock(" + X", font);
        block3.getSubscript().add(new PDFFormattedTextBlock("3", fontSubscript));
        PDFFormattedTextBlock blockn = new PDFFormattedTextBlock(" + ... + X", font);
        blockn.getSubscript().add(new PDFFormattedTextBlock("n", fontSubscript));

        PDFFormattedParagraph paragraph = new PDFFormattedParagraph(new PDFFormattedBlock[] { block1, block2, block3, blockn });
        paragraph.setHorizontalAlign(PDFStringHorizontalAlign.CENTER);
        content.addParagraph(paragraph);

        return flowText;
    }

}
