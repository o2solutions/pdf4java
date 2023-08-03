import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.utilities.PDFMerger;

public class SimpleMerge {
    public static void main(String[] args) {
        try {

            String supportPath = "..\\..\\SupportFiles\\";

            PDFMerger.mergeFiles("SimpleMerge.pdf",
                    new String[] { supportPath + "content.pdf", supportPath + "formfill.pdf" });

            System.out.println("PDF file has been saved with success");
        }
        catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
