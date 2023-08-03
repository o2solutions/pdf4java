import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.graphics.PDFBrush;
import com.o2sol.pdf4java.graphics.PDFCanvas;
import com.o2sol.pdf4java.graphics.PDFFlipDirection;
import com.o2sol.pdf4java.graphics.colors.PDFRgbColor;
import com.o2sol.pdf4java.graphics.fonts.PDFFont;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFont;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFontFace;
import com.o2sol.pdf4java.graphics.images.*;
import com.o2sol.pdf4java.graphics.images.masks.*;

public class Images {
    public static void main(String[] args) {
        try {
            PDFFixedDocument document = new PDFFixedDocument();
            PDFStandardFont helveticaBoldTitle = new PDFStandardFont(PDFStandardFontFace.HELVETICABOLD, 16);
            PDFStandardFont helveticaSection = new PDFStandardFont(PDFStandardFontFace.HELVETICA, 10);

            PDFPage page = document.addPage();
            drawImages(page, helveticaBoldTitle, helveticaSection);

            page = page = document.addPage();
            drawImageMasks(page, helveticaBoldTitle, helveticaSection);

            page = page = document.addPage();
            drawCmykTiff(page, helveticaBoldTitle);


            document.save("Images.pdf");

            System.out.println("PDF document has been saved with success");
        } catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }


    private static void drawImages(PDFPage page, PDFFont titleFont, PDFFont sectionFont)
    {
        PDFBrush brush = new PDFBrush();

        PDFJpegImage jpeg = new PDFJpegImage("..\\..\\SupportFiles\\image.jpg");

        PDFCanvas canvas = page.getCanvas();
        
        canvas.drawString("Images", titleFont, brush, 20, 50);

        canvas.drawString("Scaling:", sectionFont, brush, 20, 70);

        // Draw the image 3 times on the page at different sizes.
        canvas.drawImage(jpeg, 3, 90, 100, 0);
        canvas.drawImage(jpeg, 106, 90, 200, 0);
        canvas.drawImage(jpeg, 309, 90, 300, 0);

        canvas.drawString("Flipping:", sectionFont, brush, 20, 320);
        canvas.drawImage(jpeg, 20, 340, 260, 0);
        canvas.drawImage(jpeg, 310, 340, 260, 0, 0, PDFFlipDirection.VERTICAL_FLIP);
        canvas.drawImage(jpeg, 20, 550, 260, 0, 0, PDFFlipDirection.HORIZONTAL_FLIP);
        canvas.drawImage(jpeg, 310, 550, 260, 0, 0, PDFFlipDirection.HORIZONTAL_AND_VERTICAL_FLIP);

        canvas.compressAndClose();
    }


    private static void drawImageMasks(PDFPage page, PDFFont titleFont, PDFFont sectionFont)
    {
        PDFBrush brush = new PDFBrush();
        PDFCanvas canvas = page.getCanvas();

        canvas.drawString("Images Masks", titleFont, brush, 20, 50);

        canvas.drawString("Soft mask:", sectionFont, brush, 20, 70);
        PDFPngImage softMaskImage = new PDFPngImage("..\\..\\SupportFiles\\softmask.png");
        PDFSoftMask softMask = new PDFSoftMask(softMaskImage);
        PDFJpegImage softMaskJpeg = new PDFJpegImage("..\\..\\SupportFiles\\image.jpg");
        softMaskJpeg.setMask(softMask);
        // Draw the image with a soft mask.
        canvas.drawImage(softMaskJpeg, 20, 90, 280, 0);

        canvas.drawString("Stencil mask:", sectionFont, brush, 320, 70);
        PDFPngImage stencilMaskImage = new PDFPngImage("..\\..\\SupportFiles\\stencilmask.png");
        PDFStencilMask stencilMask = new PDFStencilMask(stencilMaskImage);
        PDFJpegImage stencilMaskJpeg = new PDFJpegImage("..\\..\\SupportFiles\\image.jpg");
        stencilMaskJpeg.setMask(stencilMask);
        // Draw the image with a stencil mask.
        canvas.drawImage(stencilMaskJpeg, 320, 90, 280, 0);

        canvas.drawString("Stencil mask painting:", sectionFont, brush, 20, 320);
        PDFBrush redBrush = new PDFBrush(PDFRgbColor.DARK_RED);
        canvas.drawStencilMask(stencilMask, redBrush, 20, 340, 280, 0);
        PDFBrush blueBrush = new PDFBrush(PDFRgbColor.DARK_BLUE);
        canvas.drawStencilMask(stencilMask, blueBrush, 320, 340, 280, 0);
        PDFBrush greenBrush = new PDFBrush(PDFRgbColor.DARK_GREEN);
        canvas.drawStencilMask(stencilMask, greenBrush, 20, 550, 280, 0);
        PDFBrush yellowBrush = new PDFBrush(PDFRgbColor.YELLOW_GREEN);
        canvas.drawStencilMask(stencilMask, yellowBrush, 320, 550, 280, 0);

        canvas.compressAndClose();
    }

    private static void drawCmykTiff(PDFPage page, PDFFont titleFont)
    {
        PDFBrush brush = new PDFBrush();
        PDFCanvas canvas = page.getCanvas();

        canvas.drawString("CMYK TIFF", titleFont, brush, 20, 50);

        PDFTiffImage cmykTiff = new PDFTiffImage("..\\..\\SupportFiles\\cmyk.tif");
        canvas.drawImage(cmykTiff, 20, 90, 570, 0);

        canvas.compressAndClose();
    }

}
