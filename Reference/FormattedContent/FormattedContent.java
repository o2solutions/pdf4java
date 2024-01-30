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
            String pdf4netText = "PDF4NET";
            String paragraph1Block2Text = " library is a .NET/Xamarin library for cross-platform PDF development. Code written for ";
            String paragraph1Block4Text = " can be compiled on all supported platforms without changes. The library features a " +
                    "wide range of capabilities, for both beginers and advanced PDF developers.";
            String paragraph2Block1Text = "The development style is based on fixed document model, where each page is created as needed " +
                    "and content is placed at fixed position using a grid based layout.\r\n" +
                    "This gives you access to all PDF features, whether you want to create a simple file " +
                    "or you want to create a transparency knockout group at COS level, and lets you build more complex models on top of the library.";
            String paragraph3Block2Text = " has been developed entirely in C# and it is 100% managed code.";
            String paragraph4Block1Text = "With ";
            String paragraph4Block3Text = " you can port your PDF application logic to other platforms with zero effort which means faster time to market.";
            String paragraph5Block1Text = "Simple licensing per developer with royalty free distribution helps you keep your costs under control.";
            String paragraph6Block1Text = "SUPPORTED PLATFORMS";
            String paragraph7Block1Text = "NET 2.0 to .NET 4.5";
            String paragraph8Block1Text = "Windows Forms";
            String paragraph9Block1Text = "ASP.NET Webforms and MVC";
            String paragraph10Block1Text = "Console applications";
            String paragraph11Block1Text = "Windows services";
            String paragraph12Block1Text = "Mono";
            String paragraph13Block1Text = "WPF 4.0 & 4.5";
            String paragraph15Block1Text = "UWP";
            String paragraph17Block1Text = "Xamarin.iOS";
            String paragraph18Block1Text = "Xamarin.Android";

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
