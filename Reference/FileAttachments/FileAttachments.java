import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.document.PDFDocumentFileAttachment;
import com.o2sol.pdf4java.document.PDFDisplayMode;
import com.o2sol.pdf4java.graphics.PDFBrush;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFont;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFontFace;

import java.io.IOException;
import java.io.RandomAccessFile;

public class FileAttachments {
    public static void main(String[] args) throws IOException {
        try {
            PDFFixedDocument document = new PDFFixedDocument();

            document.setDisplayMode(PDFDisplayMode.USE_ATTACHMENTS);

            PDFStandardFont helvetica = new PDFStandardFont(PDFStandardFontFace.HELVETICA, 16);
            PDFBrush blackBrush = new PDFBrush();
            PDFPage page = document.addPage();
            page.getCanvas().drawString("This document contains 2 file attachments:", helvetica, blackBrush, 50, 50);
            page.getCanvas().drawString("1. fileattachments.java", helvetica, blackBrush, 50, 70);
            page.getCanvas().drawString("2. PDF4Java.Features.pdf", helvetica, blackBrush, 50, 90);

            RandomAccessFile javaFile = new RandomAccessFile("FileAttachments.java", "rwd");
            byte[] javaFileContent = new byte[(int)javaFile.length()];
            javaFile.read(javaFileContent, 0, javaFileContent.length);
            javaFile.close();

            PDFDocumentFileAttachment fileAttachment1 = new PDFDocumentFileAttachment();
            fileAttachment1.setContent(javaFileContent);
            fileAttachment1.setFileName("FileAttachments.java");
            fileAttachment1.setDescription("Java Source Code for FileAttachments sample");
            document.getFileAttachments().add(fileAttachment1);

            RandomAccessFile pdfFile = new RandomAccessFile("..\\..\\SupportFiles\\PDF4Java.Features.pdf", "rwd");
            byte[] pdfFileContent = new byte[(int)pdfFile.length()];
            pdfFile.read(pdfFileContent, 0, pdfFileContent.length);
            pdfFile.close();

            PDFDocumentFileAttachment fileAttachment2 = new PDFDocumentFileAttachment();
            fileAttachment2.setContent(pdfFileContent);
            fileAttachment2.setFileName("PDF4Java.Features.pdf");
            fileAttachment2.setDescription("PDF4Java features list");
            document.getFileAttachments().add(fileAttachment2);

            document.save("FileAttachments.pdf");

            System.out.println("PDF document has been saved with success");
        } catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
