import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.document.*;
import com.o2sol.pdf4java.core.exceptions.PDFException;

import java.time.LocalDateTime;

public class DocumentProperties {
    public static void main(String[] args) {
        try {
            PDFFixedDocument document = new PDFFixedDocument();
            document.addPage();
            // Display the document in full screen mode.
            document.setDisplayMode(PDFDisplayMode.FULL_SCREEN);

            // Fill the document information.
            PDFDocumentInformation docInfo = new PDFDocumentInformation();
            docInfo.setAuthor("O2 Solutions");
            docInfo.setCreationDate(LocalDateTime.now());
            docInfo.setModifyDate(LocalDateTime.now());
            docInfo.setCreator("com.o2sol.pdf4java DocumentProperties sample");
            docInfo.setProducer("com.o2sol.pdf4java");
            docInfo.setTitle("com.o2sol.pdf4java DocumentProperties sample");
            docInfo.setSubject("com.o2sol.pdf4java sample code");
            docInfo.setKeywords("pdf4java,pdf,sample");
            document.setDocumentInformation(docInfo);

            // Set custom metadata in the XMP metadata.
            document.setXmpMetadata(new PDFXmpMetadata());
            // This custom metadata will appear as a child of 'xmpmeta' root node.
            document.getXmpMetadata().setMetadata("<custom>Custom metadata</custom>");

            // Set the viewer preferences.
            PDFViewerPreferences viewerPreferences = new PDFViewerPreferences();
            viewerPreferences.setCenterWindow(true);
            viewerPreferences.setIsDocumentTitleDisplayed(true);
            viewerPreferences.setIsMenubarHidden(true);
            viewerPreferences.setIsToolbarHidden(true);
            viewerPreferences.setIsWindowUIHidden(true);
            viewerPreferences.setPrintScaling(PDFPrintScaling.NONE);
            document.setViewerPreferences(viewerPreferences);

            // Set the PDF version.
            document.setPDFVersion(PDFVersion.VERSION_1_5);
            
            document.save("DocumentProperties.pdf");

            System.out.println("PDF document has been saved with success");
        } catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
