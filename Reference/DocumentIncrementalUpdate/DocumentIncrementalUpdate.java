import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.PDFFixedDocumentFeatures;
import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.graphics.PDFBrush;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFont;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFontFace;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class DocumentIncrementalUpdate {
    public static void main(String[] args) throws IOException {
        try {
            File source = new File("..\\..\\SupportFiles\\content.pdf");
            File dest = new File("DocumentIncrementalUpdate.pdf");

            Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);

            PDFFixedDocumentFeatures df = new PDFFixedDocumentFeatures();
            // Do not load file attachments, new file attachments cannot be added.
            df.setEnableDocumentFileAttachments(false);
            // Do not load form fields, form fields cannot be filled and new form fields cannot be added.
            df.setEnableDocumentFormFields(false);
            // Do not load embedded JavaScript code, new JavaScript code at document level cannot be added.
            df.setEnableDocumentJavaScriptBlocks(false);
            // Do not load the named destinations, new named destinations cannot be created.
            df.setEnableDocumentNamedDestinations(false);
            // Do not load the document outlines, new outlines cannot be created.
            df.setEnableDocumentOutline(false);
            // Do not load annotations, new annotations cannot be added to existing pages.
            df.setEnablePageAnnotations(false);
            // Do not load the page graphics, new graphics cannot be added to existing pages.
            df.setEnablePageGraphics(false);
            PDFFixedDocument document = new PDFFixedDocument("DocumentIncrementalUpdate.pdf", df);

            PDFStandardFont helvetica = new PDFStandardFont(PDFStandardFontFace.HELVETICABOLD, 24);
            PDFBrush brush = new PDFBrush();

            // Add a new page with some content on it.
            PDFPage page = document.addPage();
            page.getCanvas().drawString("New page added to an existing document.", helvetica, brush, 20, 50);

            // When document features have been specified at load time the document is automatically saved in incremental update mode.
            document.save("DocumentIncrementalUpdate.pdf");

            System.out.println("PDF document has been saved with success");
        } catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
