import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.core.io.MemoryStream;
import com.o2sol.pdf4java.graphics.colors.PDFGrayColorSpace;
import com.o2sol.pdf4java.graphics.images.PDFRawImage;
import com.o2sol.pdf4java.transforms.PDFReplaceImageListener;
import com.o2sol.pdf4java.transforms.PDFPageTransformer;
import com.o2sol.pdf4java.transforms.PDFReplaceImageEvent;
import com.o2sol.pdf4java.transforms.PDFReplaceImageTransform;

public class RemoveAndReplaceImages implements PDFReplaceImageListener {
    public static void main(String[] args) {
        try {
            // Load the input file.
            PDFFixedDocument document = new PDFFixedDocument("..\\..\\SupportFiles\\content.pdf");

            RemoveAndReplaceImages replacer = new RemoveAndReplaceImages();
            PDFReplaceImageTransform replaceImageTransform = new PDFReplaceImageTransform();
            replaceImageTransform.addReplaceImageListener(replacer);
            PDFPageTransformer pageTransformer = new PDFPageTransformer(document.getPage(2));
            pageTransformer.applyTransform(replaceImageTransform);
            replaceImageTransform.removeReplaceImageListener(replacer);

            document.save("RemoveAndReplaceImages.pdf");

            System.out.println("PDF document has been saved with success");
        }
        catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void replaceImage(PDFReplaceImageTransform sender, PDFReplaceImageEvent e) {
        if (e.getOldImageID().getValue().equals("/Im1")) {
            // Replace the existing image with a checkers board.
            MemoryStream checkers = new MemoryStream(new byte[] {
                    0, (byte)255, 0, (byte)255, 0, (byte)255, 0, (byte)255, 0, (byte)255, 0, (byte)255,
                    0, (byte)255, 0, (byte)255, 0, (byte)255, 0, (byte)255, 0, (byte)255, 0, (byte)255, 0
            });
            PDFRawImage rawImage = new PDFRawImage(checkers);
            rawImage.setWidth(5);
            rawImage.setHeight(5);
            rawImage.setBitsPerComponent(8);
            rawImage.setColorSpace(new PDFGrayColorSpace());

            e.setNewImage(rawImage);
        }
        else {
            if (e.getOldImageID().getValue().equals("/Im2"))
            {
                // Remove the image from the page by setting the new image (the replacement image) to null.
                e.setNewImage(null);
            }
        }

    }
}
