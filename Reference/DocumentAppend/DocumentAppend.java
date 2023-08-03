import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.actions.PDFGoToAction;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.destinations.PDFPageDirectDestination;
import com.o2sol.pdf4java.document.PDFOutlineItem;
import com.o2sol.pdf4java.document.PDFOutlineItemVisualStyle;

public class DocumentAppend {
    public static void main(String[] args) {
        try {
            PDFFixedDocument document = new PDFFixedDocument();

            // The documents are merged by creating an empty PDF document and appending the file to it.
            // The outlines from the source file are also included in the merged file.
            document.appendFile("..\\..\\SupportFiles\\content.pdf");
            int count = document.getPages().size();
            document.appendFile("..\\..\\SupportFiles\\formfill.pdf");

            // Create outlines that point to each merged file.
            PDFOutlineItem file1Outline = createOutline("First file", document.getPage(0));
            document.getOutline().add(file1Outline);
            PDFOutlineItem file2Outline = createOutline("Second file", document.getPage(count));
            document.getOutline().add(file2Outline);

            // Optionally we can add a new page at the beginning of the merged document.
            PDFPage page = new PDFPage();
            document.getPages().add(0, page);

            PDFOutlineItem blankPageOutline = createOutline("Blank page", page);
            document.getOutline().add(0, blankPageOutline);


            document.save("DocumentAppend.pdf");

            System.out.println("PDF document has been saved with success");
        }
        catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }

    private static PDFOutlineItem createOutline(String title, PDFPage page) {
        PDFPageDirectDestination pageDestination = new PDFPageDirectDestination();
        pageDestination.setPage(page);
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
        outline.setTitle(title);
        outline.setVisualStyle(PDFOutlineItemVisualStyle.ITALIC);
        outline.setAction(gotoPage);

        return outline;
    }

}
