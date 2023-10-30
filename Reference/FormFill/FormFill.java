import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.forms.*;

public class FormFill {
    public static void main(String[] args) {
        try {
            PDFFixedDocument document = new PDFFixedDocument("..\\..\\SupportFiles\\formfill.pdf");
            PDFForm form = document.getForm();

            ((PDFTextBoxField)form.getField("firstname")).setText("John");
            form.getField("lastname").setValue("Doe");

            ((PDFRadioButtonWidget)form.getField("sex").getWidget(0)).setIsChecked(true);

            ((PDFComboBoxField)form.getField("firstcar")).setSelectedIndex(0);

            ((PDFListBoxField)form.getField("secondcar")).setSelectedIndex(1);

            ((PDFCheckBoxField)form.getField("agree")).setIsChecked(true);
            form.flattenFields();

            document.save("FormFill.pdf");

            System.out.println("PDF document has been saved with success");
        } catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
