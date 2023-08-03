
import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.graphics.PDFBrush;
import com.o2sol.pdf4java.graphics.colors.PDFRgbColor;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFont;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFontFace;

public class HelloJavaWorld {
    public static void main(String[] args) {
        try {
            PDFFixedDocument document = new PDFFixedDocument();
            PDFPage page = document.addPage();
            PDFStandardFont font = new PDFStandardFont(PDFStandardFontFace.HELVETICA, 24);
            PDFBrush redBrush = new PDFBrush(PDFRgbColor.RED);

            page.getCanvas().drawString("Hello Java World!", font, redBrush, 50, 50);

            document.save("hellojavaworld.pdf");

            System.out.println("PDF document has been saved with success");
        }
        catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }

}
