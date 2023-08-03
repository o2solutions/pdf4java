import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.content.PDFContentExtractor;
import com.o2sol.pdf4java.content.text.*;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.graphics.PDFPath;
import com.o2sol.pdf4java.graphics.PDFPen;
import com.o2sol.pdf4java.graphics.colors.*;

public class SearchText {
    public static void main(String[] args) {
        try {
            PDFFixedDocument document = new PDFFixedDocument("..\\..\\SupportFiles\\content.pdf");
            PDFContentExtractor ce = new PDFContentExtractor(document.getPage(0));

            // Simple search.
            PDFTextSearchResultCollection searchResults = ce.searchText("at");
            highlightSearchResults(document.getPage(0), searchResults, PDFRgbColor.RED);

            // Whole words search.
            searchResults = ce.searchText("at", PDFTextSearchOptions.WHOLE_WORD_SEARCH);
            highlightSearchResults(document.getPage(0), searchResults, PDFRgbColor.GREEN);

            // Regular expression search, find all words that start with uppercase.
            searchResults = ce.searchText("[A-Z][a-z]*", PDFTextSearchOptions.REGEX_SEARCH);
            highlightSearchResults(document.getPage(0), searchResults, PDFRgbColor.BLUE);

            document.save("SearchText.pdf");

            System.out.println("PDF document has been saved with success");
        }
        catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }

    private static void highlightSearchResults(PDFPage page, PDFTextSearchResultCollection searchResults, PDFColor color) {
        PDFPen pen = new PDFPen(color, 0.5);

        for (int i = 0; i < searchResults.size(); i++) {
            PDFTextRunCollection trc = searchResults.get(i).getTextRuns();
            for (int j = 0; j < trc.size(); j++) {
                PDFPath path = new PDFPath();
                PDFTextRun tr = trc.get(j);

                path.startSubpath(tr.getCorners()[0].getX(), tr.getCorners()[0].getY());
                path.addPolygon(tr.getCorners());

                page.getCanvas().drawPath(pen, path);
            }
        }
    }

}
