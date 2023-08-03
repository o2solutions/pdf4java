import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.actions.PDFGoToAction;
import com.o2sol.pdf4java.actions.PDFUriAction;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.destinations.PDFPageDirectDestination;
import com.o2sol.pdf4java.document.PDFDisplayMode;
import com.o2sol.pdf4java.document.PDFOutlineItem;
import com.o2sol.pdf4java.document.PDFOutlineItemVisualStyle;
import com.o2sol.pdf4java.graphics.PDFBrush;
import com.o2sol.pdf4java.graphics.colors.PDFRgbColor;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFont;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFontFace;

public class Outlines {
    public static void main(String[] args) {
        try {
            PDFFixedDocument document = new PDFFixedDocument();
            document.setDisplayMode(PDFDisplayMode.USE_OUTLINES);

            PDFStandardFont helvetica = new PDFStandardFont(PDFStandardFontFace.HELVETICA, 216);
            PDFBrush blackBrush = new PDFBrush();
            for (int i = 0; i < 10; i++)
            {
                PDFPage page = document.addPage();
                page.getCanvas().drawString(String.format("%d", i + 1), helvetica, blackBrush, 50, 50);
            }

            PDFOutlineItem root = new PDFOutlineItem();
            root.setTitle("Contents");
            root.setVisualStyle(PDFOutlineItemVisualStyle.BOLD);
            root.setColor(new PDFRgbColor(255, 0, 0));
            document.getOutline().add(root);

            for (int i = 0; i < document.getPages().size(); i++)
            {
                // Create a destination to target page.
                PDFPageDirectDestination pageDestination = new PDFPageDirectDestination();
                pageDestination.setPage(document.getPage(i));
                pageDestination.setTop(0);
                pageDestination.setLeft(0);
                // Inherit current zoom
                pageDestination.setZoom(0);

                // Create a go to action to be executed when the outline is clicked,
                // the go to action goes to specified destination.
                PDFGoToAction gotoPage = new PDFGoToAction();
                gotoPage.setDestination(pageDestination);

                // Create the outline in the table of contents
                PDFOutlineItem outline = new PDFOutlineItem();
                outline.setTitle(String.format("Go to page %d", i + 1));
                outline.setVisualStyle(PDFOutlineItemVisualStyle.ITALIC);
                outline.setAction(gotoPage);
                root.getItems().add(outline);
            }
            root.setIsExpanded(true);

            // Create an outline that will launch a link in the browser.
            PDFUriAction uriAction = new PDFUriAction();
            uriAction.setURI("http://www.o2sol.com/");

            PDFOutlineItem webOutline = new PDFOutlineItem();
            webOutline.setTitle("http://www.o2sol.com/");
            webOutline.setColor(new PDFRgbColor(0, 0, 255));
            webOutline.setAction(uriAction);
            document.getOutline().add(webOutline);

            document.save("Outlines.pdf");

            System.out.println("PDF document has been saved with success");
        } catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
