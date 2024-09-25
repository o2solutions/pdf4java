import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.content.analyzers.PDFPageContentAnalyzer;

public class PageAnalyzer {
    public static void main(String[] args) {
        String fileName = "..\\..\\SupportFiles\\content.pdf";

        PDFFixedDocument document = new PDFFixedDocument(fileName);

        JsonExporter jsonExporter = new JsonExporter("page.json");
        PDFPageContentAnalyzer pageContentAnalyzer = new PDFPageContentAnalyzer(document.getPage(3));
        pageContentAnalyzer.runAnalysis(jsonExporter);
    }
}
