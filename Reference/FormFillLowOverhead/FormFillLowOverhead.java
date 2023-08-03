import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.core.PDFFileEx;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.core.io.FileStream;

public class FormFillLowOverhead {
    public static void main(String[] args) {
        try {
            PDFFileEx pdfFile = new PDFFileEx("..\\..\\SupportFiles\\formfill.pdf", null);

            // For text fields the value is the field text			
            pdfFile.setFieldValue("firstname", "John");
            pdfFile.setFieldValue("lastname", "Doe");

            // For radio buttons the value is the export value of the selected widget
            pdfFile.setFieldValue("sex", "M");

            // For drop down lists and listboxes the value is the index of the selected item
            pdfFile.setFieldValue("firstcar", 0);
            pdfFile.setFieldValue("secondcar", 1);

            // For checkboxes the value is true for checked and false for unchecked
            pdfFile.setFieldValue("agree", true);

            pdfFile.flattenFormFields();
            pdfFile.save("FormFillLowOverhead.pdf", null, false);

            System.out.println("PDF document has been saved with success");
        } catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
