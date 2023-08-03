import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.core.io.FileMode;
import com.o2sol.pdf4java.core.io.FileStream;
import com.o2sol.pdf4java.graphics.PDFBrush;
import com.o2sol.pdf4java.graphics.colors.PDFRgbColor;

import java.io.IOException;
import java.util.Random;

public class DocumentPageByPageSave {
    public static void main(String[] args) throws IOException {
        try {
            FileStream output = new FileStream("DocumentPageByPageSave.pdf", FileMode.CREATE_NEW);
            PDFFixedDocument document = new PDFFixedDocument();

            // Init the save operation
            document.beginSave(output);

            PDFRgbColor color = new PDFRgbColor();
            PDFBrush brush = new PDFBrush(color);
            Random rnd = new Random(0);

            for (int i = 0; i < 3; i++)
            {
                PDFPage page = document.getPages().add();
                page.setWidth(1000);
                page.setHeight(1000);

                for (int y = 1; y <= page.getHeight(); y++)
                {
                    for (int x = 0; x < page.getWidth(); x++)
                    {
                        color.setR(rnd.nextInt(256));
                        color.setG(rnd.nextInt(256));
                        color.setB(rnd.nextInt(256));

                        page.getCanvas().drawRectangle(brush, x, y - 1, 1, 1);
                    }

                    if ((y % 100) == 0)
                    {
                        // Compress the graphics that have been drawn so far and save them.
                        page.getCanvas().compress();
                        page.saveCanvas();
                    }
                }

                // Close the page graphics and save the page.
                page.getCanvas().compressAndClose();
                page.save();
            }

            // Finish the document.
            document.endSave();

            System.out.println("PDF document has been saved with success");
        } catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
