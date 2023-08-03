import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.core.PDFFile;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.core.io.FileMode;
import com.o2sol.pdf4java.core.io.FileStream;
import com.o2sol.pdf4java.document.*;

import java.time.LocalDateTime;

public class DocumentSplit {
    public static void main(String[] args) {
        try {
            // The input file is split by extracting pages from source file and inserting them in new empty PDF documents.
            FileStream inputStream = new FileStream("..\\..\\SupportFiles\\content.pdf", FileMode.OPEN_READ);
            PDFFile file = new PDFFile(inputStream);

            for (int i = 0; i < file.getPageCount(); i++)
            {
                PDFFixedDocument document = new PDFFixedDocument();
                PDFPage page = file.extractPage(i);
                document.getPages().add(page);

                document.save(String.format("DocumentSplit-page.%d.pdf", i));
            }

            System.out.println("PDF documents have been saved with success");
        } catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
