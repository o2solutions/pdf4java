import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.content.batesnumbers.*;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.graphics.PDFBrush;
import com.o2sol.pdf4java.graphics.colors.PDFRgbColor;

public class BatesNumbers {
    public static void main(String[] args) {
        try {
            // Load the input file.
            PDFFixedDocument document = new PDFFixedDocument("..\\..\\SupportFiles\\content.pdf");

            PDFBatesNumberAppearance bna = new PDFBatesNumberAppearance();
            bna.setPosition(25, 5);
            bna.setBrush(new PDFBrush(PDFRgbColor.DARK_RED));

            PDFBatesNumberProvider bnp = new PDFBatesNumberProvider();
            bnp.setPrefix("O2S");
            bnp.setSuffix("PDF4Java");
            bnp.setStartNumber(1);

            PDFBatesNumber.writeBatesNumber(document, bnp, bna);

            document.save("BatesNumbers.pdf");

            System.out.println("PDF document has been saved with success");
        }
        catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
