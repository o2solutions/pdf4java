import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.actions.PDFUriAction;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.graphics.PDFBrush;
import com.o2sol.pdf4java.graphics.PDFStringHorizontalAlign;
import com.o2sol.pdf4java.graphics.colors.PDFRgbColor;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFont;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFontFace;
import com.o2sol.pdf4java.graphics.formattedcontent.PDFFormattedContent;
import com.o2sol.pdf4java.graphics.formattedcontent.PDFFormattedParagraph;
import com.o2sol.pdf4java.graphics.formattedcontent.PDFFormattedParagraphLineSpacing;
import com.o2sol.pdf4java.graphics.formattedcontent.PDFFormattedTextBlock;

public class FormattedContent {
    public static void main(String[] args) {
        try {
            String pdf4netText = "PDF4Java";
            String paragraph1Block2Text = " library is a Java library for cross-platform PDF development. Code written for ";
            String paragraph1Block4Text = " can be compiled on all supported platforms without changes. The library features a " +
                    "wide range of capabilities, for both beginers and advanced PDF developers.";
            String paragraph2Block1Text = "The development style is based on fixed document model, where each page is created as needed " +
                    "and content is placed at fixed position using a grid based layout.\r\n" +
                    "This gives you access to all PDF features, whether you want to create a simple file " +
                    "or you want to create a transparency knockout group at COS level, and lets you build more complex models on top of the library.";
            String paragraph3Block2Text = " has been developed entirely in Java.";
            String paragraph4Block1Text = "With ";
            String paragraph4Block3Text = " you can port your PDF application logic to other platforms with zero effort which means faster time to market.";
            String paragraph5Block1Text = "Simple licensing per developer with royalty free distribution helps you keep your costs under control.";
            String paragraph6Block1Text = "Main Features:";
            String paragraph7Block1Text = "Create PDF files with support for grid/flow layout";
            String paragraph8Block1Text = "Digitally sign PDF files with CMS/PAdES signatures and LTV support";
            String paragraph9Block1Text = "Native support for 1D and 2D barcodes";
            String paragraph10Block1Text = "Create, fill and flatten PDF e-forms";
            String paragraph11Block1Text = "Merge, append, mix and split PDF documents";
            String paragraph12Block1Text = "Page content transformation";
            String paragraph13Block1Text = "Translate, rotate and scale multiple pages to a single page";
            String paragraph15Block1Text = "Stamp existing PDF documents";
            String paragraph17Block1Text = "Change security settings, encrypt and decrypt PDF documents";
            String paragraph18Block1Text = "Redact text and images on PDF pages";
            String paragraph19Block1Text = "Extract images, text and vector graphics from PDF";
            String paragraph20Block1Text = "Create PDF/A-1, PDF/A-2, PDF/A-3 files";
            String paragraph21Block1Text = "Create accesible PDF files with PDF/UA support";
            String paragraph22Block1Text = "Convert PDF pages and documents to color/gray/b&w images";
            String paragraph23Block1Text = "Easy to learn and use";
            String paragraph24Block1Text = "Supports Java 8 and higher";

            PDFStandardFont textFont = new PDFStandardFont(PDFStandardFontFace.HELVETICA, 10);
            PDFFormattedTextBlock pdf4netLinkBlock = new PDFFormattedTextBlock(pdf4netText);
            pdf4netLinkBlock.setFont(new PDFStandardFont(PDFStandardFontFace.HELVETICA_BOLD, 10));
            pdf4netLinkBlock.getFont().setUnderline(true);
            pdf4netLinkBlock.setTextColor(new PDFBrush(PDFRgbColor.BLUE));
            pdf4netLinkBlock.setAction(new PDFUriAction("http://www.o2sol.com/"));

            PDFFormattedParagraph paragraph1 = new PDFFormattedParagraph();
            paragraph1.setLineSpacingMode(PDFFormattedParagraphLineSpacing.MULTIPLE);
            paragraph1.setLineSpacing(1.2);
            paragraph1.setSpacingAfter(3);
            paragraph1.setFirstLineIndent(10);
            paragraph1.setHorizontalAlign(PDFStringHorizontalAlign.JUSTIFIED);
            paragraph1.addBlock(pdf4netLinkBlock);
            paragraph1.addBlock(new PDFFormattedTextBlock(paragraph1Block2Text, textFont));
            paragraph1.addBlock(pdf4netLinkBlock);
            paragraph1.addBlock(new PDFFormattedTextBlock(paragraph1Block4Text, textFont));

            PDFFormattedParagraph paragraph2 = new PDFFormattedParagraph();
            paragraph2.setSpacingAfter(3);
            paragraph2.setFirstLineIndent(10);
            paragraph2.setHorizontalAlign(PDFStringHorizontalAlign.JUSTIFIED);
            paragraph2.setLineSpacingMode(PDFFormattedParagraphLineSpacing.MULTIPLE);
            paragraph2.setLineSpacing(1.2);
            paragraph2.addBlock(new PDFFormattedTextBlock(paragraph2Block1Text, textFont));

            PDFFormattedParagraph paragraph3 = new PDFFormattedParagraph();
            paragraph3.setLineSpacingMode(PDFFormattedParagraphLineSpacing.MULTIPLE);
            paragraph3.setLineSpacing(1.2);
            paragraph3.setSpacingAfter(3);
            paragraph3.setFirstLineIndent(10);
            paragraph3.setHorizontalAlign(PDFStringHorizontalAlign.JUSTIFIED);
            paragraph3.addBlock(pdf4netLinkBlock);
            paragraph3.addBlock(new PDFFormattedTextBlock(paragraph3Block2Text, textFont));

            PDFFormattedParagraph paragraph4 = new PDFFormattedParagraph();
            paragraph4.setLineSpacingMode(PDFFormattedParagraphLineSpacing.MULTIPLE);
            paragraph4.setLineSpacing(1.2);
            paragraph4.setSpacingAfter(3);
            paragraph4.setFirstLineIndent(10);
            paragraph4.setHorizontalAlign(PDFStringHorizontalAlign.JUSTIFIED);
            paragraph4.addBlock(new PDFFormattedTextBlock(paragraph4Block1Text, textFont));
            paragraph4.addBlock(pdf4netLinkBlock);
            paragraph4.addBlock(new PDFFormattedTextBlock(paragraph4Block3Text, textFont));

            PDFFormattedParagraph paragraph5 = new PDFFormattedParagraph();
            paragraph5.setFirstLineIndent(10);
            paragraph5.setHorizontalAlign(PDFStringHorizontalAlign.JUSTIFIED);
            paragraph5.addBlock(new PDFFormattedTextBlock(paragraph5Block1Text, textFont));

            PDFFormattedParagraph paragraph6 = new PDFFormattedParagraph();
            paragraph6.setSpacingBefore(10);
            paragraph6.addBlock(new PDFFormattedTextBlock(paragraph6Block1Text, textFont));

            PDFFormattedTextBlock bulletBlock = new PDFFormattedTextBlock("\u0076 ", new PDFStandardFont(PDFStandardFontFace.ZAPFDINGBATS, 10));

            PDFFormattedParagraph paragraph7 = new PDFFormattedParagraph();
            paragraph7.setSpacingBefore(3);
            paragraph7.setBullet(bulletBlock);
            paragraph7.setLeftIndentation(10);
            paragraph7.addBlock(new PDFFormattedTextBlock(paragraph7Block1Text, textFont));

            PDFFormattedParagraph paragraph8 = new PDFFormattedParagraph();
            paragraph8.setSpacingBefore(3);
            paragraph8.setBullet(bulletBlock);
            paragraph8.setLeftIndentation(10);
            paragraph8.addBlock(new PDFFormattedTextBlock(paragraph8Block1Text, textFont));

            PDFFormattedParagraph paragraph9 = new PDFFormattedParagraph();
            paragraph9.setSpacingBefore(3);
            paragraph9.setBullet(bulletBlock);
            paragraph9.setLeftIndentation(10);
            paragraph9.addBlock(new PDFFormattedTextBlock(paragraph9Block1Text, textFont));

            PDFFormattedParagraph paragraph10 = new PDFFormattedParagraph();
            paragraph10.setSpacingBefore(3);
            paragraph10.setBullet(bulletBlock);
            paragraph10.setLeftIndentation(10);
            paragraph10.addBlock(new PDFFormattedTextBlock(paragraph10Block1Text, textFont));

            PDFFormattedParagraph paragraph11 = new PDFFormattedParagraph();
            paragraph11.setSpacingBefore(3);
            paragraph11.setBullet(bulletBlock);
            paragraph11.setLeftIndentation(10);
            paragraph11.addBlock(new PDFFormattedTextBlock(paragraph11Block1Text, textFont));

            PDFFormattedParagraph paragraph12 = new PDFFormattedParagraph();
            paragraph12.setSpacingBefore(3);
            paragraph12.setBullet(bulletBlock);
            paragraph12.setLeftIndentation(10);
            paragraph12.addBlock(new PDFFormattedTextBlock(paragraph12Block1Text, textFont));

            PDFFormattedParagraph paragraph13 = new PDFFormattedParagraph();
            paragraph13.setSpacingBefore(3);
            paragraph13.setBullet(bulletBlock);
            paragraph13.setLeftIndentation(10);
            paragraph13.addBlock(new PDFFormattedTextBlock(paragraph13Block1Text, textFont));

            PDFFormattedParagraph paragraph15 = new PDFFormattedParagraph();
            paragraph15.setSpacingBefore(3);
            paragraph15.setBullet(bulletBlock);
            paragraph15.setLeftIndentation(10);
            paragraph15.addBlock(new PDFFormattedTextBlock(paragraph15Block1Text, textFont));

            PDFFormattedParagraph paragraph17 = new PDFFormattedParagraph();
            paragraph17.setSpacingBefore(3);
            paragraph17.setBullet(bulletBlock);
            paragraph17.setLeftIndentation(10);
            paragraph17.addBlock(new PDFFormattedTextBlock(paragraph17Block1Text, textFont));

            PDFFormattedParagraph paragraph18 = new PDFFormattedParagraph();
            paragraph18.setSpacingBefore(3);
            paragraph18.setBullet(bulletBlock);
            paragraph18.setLeftIndentation(10);
            paragraph18.addBlock(new PDFFormattedTextBlock(paragraph18Block1Text, textFont));

            PDFFormattedParagraph paragraph19 = new PDFFormattedParagraph();
            paragraph19.setSpacingBefore(3);
            paragraph19.setBullet(bulletBlock);
            paragraph19.setLeftIndentation(10);
            paragraph19.addBlock(new PDFFormattedTextBlock(paragraph19Block1Text, textFont));

            PDFFormattedParagraph paragraph20 = new PDFFormattedParagraph();
            paragraph20.setSpacingBefore(3);
            paragraph20.setBullet(bulletBlock);
            paragraph20.setLeftIndentation(10);
            paragraph20.addBlock(new PDFFormattedTextBlock(paragraph20Block1Text, textFont));

            PDFFormattedParagraph paragraph21 = new PDFFormattedParagraph();
            paragraph21.setSpacingBefore(3);
            paragraph21.setBullet(bulletBlock);
            paragraph21.setLeftIndentation(10);
            paragraph21.addBlock(new PDFFormattedTextBlock(paragraph21Block1Text, textFont));

            PDFFormattedParagraph paragraph22 = new PDFFormattedParagraph();
            paragraph22.setSpacingBefore(3);
            paragraph22.setBullet(bulletBlock);
            paragraph22.setLeftIndentation(10);
            paragraph22.addBlock(new PDFFormattedTextBlock(paragraph22Block1Text, textFont));

            PDFFormattedParagraph paragraph23 = new PDFFormattedParagraph();
            paragraph23.setSpacingBefore(3);
            paragraph23.setBullet(bulletBlock);
            paragraph23.setLeftIndentation(10);
            paragraph23.addBlock(new PDFFormattedTextBlock(paragraph23Block1Text, textFont));

            PDFFormattedParagraph paragraph24 = new PDFFormattedParagraph();
            paragraph24.setSpacingBefore(3);
            paragraph24.setBullet(bulletBlock);
            paragraph24.setLeftIndentation(10);
            paragraph24.addBlock(new PDFFormattedTextBlock(paragraph24Block1Text, textFont));

            PDFFormattedContent formattedContent = new PDFFormattedContent();
            formattedContent.addParagraph(paragraph1);
            formattedContent.addParagraph(paragraph2);
            formattedContent.addParagraph(paragraph3);
            formattedContent.addParagraph(paragraph4);
            formattedContent.addParagraph(paragraph5);
            formattedContent.addParagraph(paragraph6);
            formattedContent.addParagraph(paragraph7);
            formattedContent.addParagraph(paragraph8);
            formattedContent.addParagraph(paragraph9);
            formattedContent.addParagraph(paragraph10);
            formattedContent.addParagraph(paragraph11);
            formattedContent.addParagraph(paragraph12);
            formattedContent.addParagraph(paragraph13);
            formattedContent.addParagraph(paragraph15);
            formattedContent.addParagraph(paragraph17);
            formattedContent.addParagraph(paragraph18);
            formattedContent.addParagraph(paragraph19);
            formattedContent.addParagraph(paragraph20);
            formattedContent.addParagraph(paragraph21);
            formattedContent.addParagraph(paragraph22);
            formattedContent.addParagraph(paragraph23);
            formattedContent.addParagraph(paragraph24);

            PDFFixedDocument document = new PDFFixedDocument();
            PDFPage page = document.getPages().add();

            page.getCanvas().drawFormattedContent(formattedContent, 50, 50, 500, 700);
            page.getCanvas().compressAndClose();

            document.save("FormattedContent.pdf");

            System.out.println("PDF document has been saved with success");
        } catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
