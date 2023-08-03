import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.core.PDFFile;
import com.o2sol.pdf4java.core.PDFResourceOptimizer;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.core.io.FileMode;
import com.o2sol.pdf4java.core.io.FileStream;

import java.io.File;

public class ResourceOptimization {
    public static void main(String[] args) {
        try {
            pdfMergeWithoutResourceOptimization();
            pdfMergeWithResourceOptimization();
        }
        catch (PDFException ex) {
            System.out.println("Error creating PDF files");
            System.out.println(ex.getMessage());
        }
    }

    private static void pdfMergeWithoutResourceOptimization() {
        PDFFixedDocument document = new PDFFixedDocument();

        for (int i = 0; i < 5; i++) {
            try (FileStream pdfStream = new FileStream("..\\..\\SupportFiles\\content.pdf", FileMode.OPEN_READ)) {
                PDFFile pdfFile = new PDFFile(pdfStream);

                PDFPage[] pages = pdfFile.extractPages(0, 4);
                for (int j = 0; j < pages.length; j++) {
                    document.getPages().add(pages[j]);
                }
            }
        }

        document.save("PDFMergeWithoutResourceOptimization.pdf");

        File fileInfo = new File("PDFMergeWithoutResourceOptimization.pdf");
        System.out.printf("PDF merge without resource optimization - output file size: %d\r\n", fileInfo.length());
    }

    private static void pdfMergeWithResourceOptimization() {
        PDFFixedDocument document = new PDFFixedDocument();

        for (int i = 0; i < 5; i++) {
            try (FileStream pdfStream = new FileStream("..\\..\\SupportFiles\\content.pdf", FileMode.OPEN_READ)) {
                PDFFile pdfFile = new PDFFile(pdfStream);

                PDFPage[] pages = pdfFile.extractPages(0, 4);
                for (int j = 0; j < pages.length; j++) {
                    document.getPages().add(pages[j]);
                }
            }
        }

        PDFResourceOptimizer resourceOptimizer = new PDFResourceOptimizer(document);
        resourceOptimizer.mergeFonts();
        resourceOptimizer.mergeImages();
        document.save("PDFMergeWithResourceOptimization.pdf");

        File fileInfo = new File("PDFMergeWithResourceOptimization.pdf");
        System.out.printf("PDF merge with resource optimization - output file size: %d\r\n", fileInfo.length());
    }
}
