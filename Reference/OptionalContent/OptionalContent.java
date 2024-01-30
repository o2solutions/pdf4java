import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.graphics.PDFBrush;
import com.o2sol.pdf4java.graphics.PDFPageCanvas;
import com.o2sol.pdf4java.graphics.PDFPen;
import com.o2sol.pdf4java.graphics.colors.PDFRgbColor;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFont;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFontFace;
import com.o2sol.pdf4java.graphics.optionalcontent.PDFOptionalContentDisplayTree;
import com.o2sol.pdf4java.graphics.optionalcontent.PDFOptionalContentDisplayTreeNode;
import com.o2sol.pdf4java.graphics.optionalcontent.PDFOptionalContentGroup;
import com.o2sol.pdf4java.graphics.optionalcontent.PDFOptionalContentProperties;

public class OptionalContent {
    public static void main(String[] args) {
        try {
            PDFFixedDocument document = new PDFFixedDocument();
            document.setOptionalContentProperties(new PDFOptionalContentProperties());

            PDFStandardFont helveticaBold  = new PDFStandardFont(PDFStandardFontFace.HELVETICA_BOLD, 18);
            PDFBrush blackBrush = new PDFBrush();
            PDFBrush greenBrush = new PDFBrush(PDFRgbColor.DARK_GREEN);
            PDFBrush yellowBrush = new PDFBrush(PDFRgbColor.YELLOW);
            PDFPen bluePen = new PDFPen(PDFRgbColor.DARK_BLUE, 5);
            PDFPen redPen = new PDFPen(PDFRgbColor.DARK_RED, 5);

            PDFPage page = document.addPage();
            PDFPageCanvas pageCanvas = page.getCanvas();
            pageCanvas.drawString("Simple optional content: the green rectangle", helveticaBold, blackBrush, 20, 50);

            PDFOptionalContentGroup ocgPage1 = new PDFOptionalContentGroup();
            ocgPage1.setName("Page 1 - Green Rectangle");
            pageCanvas.beginOptionalContentGroup(ocgPage1);
            pageCanvas.drawRectangle(bluePen, greenBrush, 20, 100, 570, 400);
            pageCanvas.endOptionalContentGroup();

            page = document.addPage();
            pageCanvas = page.getCanvas();
            pageCanvas.drawString("Multipart optional content: the green rectangles", helveticaBold, blackBrush, 20, 50);

            PDFOptionalContentGroup ocgPage2 = new PDFOptionalContentGroup();
            ocgPage2.setName("Page 2 - Green Rectangles");
            pageCanvas.beginOptionalContentGroup(ocgPage2);
            pageCanvas.drawRectangle(bluePen, greenBrush, 20, 200, 570, 200);
            pageCanvas.endOptionalContentGroup();

            pageCanvas.drawRectangle(redPen, yellowBrush, 250, 90, 110, 680);

            pageCanvas.beginOptionalContentGroup(ocgPage2);
            pageCanvas.drawRectangle(bluePen, greenBrush, 20, 500, 570, 200);
            pageCanvas.endOptionalContentGroup();

            page = document.addPage();
            pageCanvas = page.getCanvas();
            pageCanvas.drawString("Nested optional content: the green and yellow rectangles", helveticaBold, blackBrush, 20, 50);

            PDFOptionalContentGroup ocgPage31 = new PDFOptionalContentGroup();
            ocgPage31.setName("Page 3 - Green Rectangle");
            pageCanvas.beginOptionalContentGroup(ocgPage31);
            pageCanvas.drawRectangle(bluePen, greenBrush, 20, 100, 570, 600);

            PDFOptionalContentGroup ocgPage32 = new PDFOptionalContentGroup();
            ocgPage32.setName("Page 3 - Yellow Rectangle");
            pageCanvas.beginOptionalContentGroup(ocgPage32);
            pageCanvas.drawRectangle(redPen, yellowBrush, 100, 200, 400, 300);
            pageCanvas.endOptionalContentGroup(); // ocgPage32

            pageCanvas.endOptionalContentGroup(); // ocgPage31

            page = document.addPage();
            pageCanvas = page.getCanvas();
            pageCanvas.drawString("Multipage optional content: the green rectangles on page 4 & 5", helveticaBold, blackBrush, 20, 50);

            PDFOptionalContentGroup ocgPage45 = new PDFOptionalContentGroup();
            ocgPage45.setName("Page 4 & 5 - Green Rectangles");
            pageCanvas.beginOptionalContentGroup(ocgPage45);
            pageCanvas.drawRectangle(bluePen, greenBrush, 20, 200, 570, 200);
            pageCanvas.endOptionalContentGroup();

            pageCanvas.drawRectangle(redPen, yellowBrush, 250, 90, 110, 680);

            pageCanvas.beginOptionalContentGroup(ocgPage45);
            pageCanvas.drawRectangle(bluePen, greenBrush, 20, 500, 570, 200);
            pageCanvas.endOptionalContentGroup();

            page = document.addPage();
            pageCanvas = page.getCanvas();
            pageCanvas.drawString("Multipage optional content: continued", helveticaBold, blackBrush, 20, 50);

            pageCanvas.beginOptionalContentGroup(ocgPage45);
            pageCanvas.drawRectangle(bluePen, greenBrush, 20, 200, 570, 200);
            pageCanvas.endOptionalContentGroup();

            pageCanvas.drawRectangle(redPen, yellowBrush, 250, 90, 110, 680);

            pageCanvas.beginOptionalContentGroup(ocgPage45);
            pageCanvas.drawRectangle(bluePen, greenBrush, 20, 500, 570, 200);
            pageCanvas.endOptionalContentGroup();

            // Build the display tree for the optional content,
            // how its structure and relationships between optional content groups are presented to the user.
            PDFOptionalContentDisplayTree optionalContentDisplayTree = document.getOptionalContentProperties().getDisplayTree();
            PDFOptionalContentDisplayTreeNode ocgPage1Node = new PDFOptionalContentDisplayTreeNode(ocgPage1);
            optionalContentDisplayTree.getNodes().add(ocgPage1Node);
            PDFOptionalContentDisplayTreeNode ocgPage2Node = new PDFOptionalContentDisplayTreeNode(ocgPage2);
            optionalContentDisplayTree.getNodes().add(ocgPage2Node);
            PDFOptionalContentDisplayTreeNode ocgPage31Node = new PDFOptionalContentDisplayTreeNode(ocgPage31);
            optionalContentDisplayTree.getNodes().add(ocgPage31Node);
            PDFOptionalContentDisplayTreeNode ocgPage32Node = new PDFOptionalContentDisplayTreeNode(ocgPage32);
            ocgPage31Node.getNodes().add(ocgPage32Node);
            PDFOptionalContentDisplayTreeNode ocgPage45Node = new PDFOptionalContentDisplayTreeNode(ocgPage45);
            optionalContentDisplayTree.getNodes().add(ocgPage45Node);

            document.save("OptionalContent.pdf");

            System.out.println("PDF document has been saved with success");
        } catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
