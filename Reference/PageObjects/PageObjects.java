import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.content.PDFContentExtractor;
import com.o2sol.pdf4java.content.visualobjects.*;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.graphics.*;
import com.o2sol.pdf4java.graphics.colors.PDFRgbColor;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFont;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFontFace;

public class PageObjects {
    public static void main(String[] args) {
        try {
            PDFBrush brush = new PDFBrush();
            PDFPen redPen = new PDFPen(PDFRgbColor.RED, 1);
            PDFStandardFont helvetica = new PDFStandardFont(PDFStandardFontFace.HELVETICA, 10);

            PDFFixedDocument document = new PDFFixedDocument("..\\..\\SupportFiles\\pageobjects.pdf");
            PDFCanvas pageCanvas = document.getPage(0).getCanvas();

            PDFContentExtractor ce = new PDFContentExtractor(document.getPage(0));
            PDFVisualObjectCollection voc = ce.extractVisualObjects(false);

            PDFPath contour = null;
            for (int i = 0; i < voc.size(); i++)
            {
                switch (voc.get(i).getType())
                {
                    case IMAGE:
                        PDFImageVisualObject ivo = (PDFImageVisualObject)voc.get(i);
                        contour = new PDFPath();
                        contour.startSubpath(ivo.getImage().getImageCorners()[0].getX() - 5, ivo.getImage().getImageCorners()[0].getY() + 5);
                        contour.addLineTo(ivo.getImage().getImageCorners()[1].getX() + 5, ivo.getImage().getImageCorners()[1].getY() + 5);
                        contour.addLineTo(ivo.getImage().getImageCorners()[2].getX() + 5, ivo.getImage().getImageCorners()[2].getY() - 5);
                        contour.addLineTo(ivo.getImage().getImageCorners()[3].getX() - 5, ivo.getImage().getImageCorners()[3].getY() - 5);
                        contour.closeSubpath();
                        pageCanvas.drawPath(redPen, contour);

                        pageCanvas.drawString("Image", helvetica, brush,
                                ivo.getImage().getImageCorners()[0].getX() - 5, ivo.getImage().getImageCorners()[0].getY() + 5);
                        break;
                    case TEXT:
                        PDFTextVisualObject tvo = (PDFTextVisualObject)voc.get(i);
                        contour = new PDFPath();
                        contour.startSubpath(tvo.getTextRun().getCorners()[0].getX() - 5, tvo.getTextRun().getCorners()[0].getY() + 5);
                        contour.addLineTo(tvo.getTextRun().getCorners()[1].getX() + 5, tvo.getTextRun().getCorners()[1].getY() + 5);
                        contour.addLineTo(tvo.getTextRun().getCorners()[2].getX() + 5, tvo.getTextRun().getCorners()[2].getY() - 5);
                        contour.addLineTo(tvo.getTextRun().getCorners()[3].getX() - 5, tvo.getTextRun().getCorners()[3].getY() - 5);
                        contour.closeSubpath();
                        pageCanvas.drawPath(redPen, contour);

                        pageCanvas.drawString("Text", helvetica, brush,
                                tvo.getTextRun().getCorners()[0].getX() - 5, tvo.getTextRun().getCorners()[0].getY() + 5);
                        break;
                    case PATH:
                        PDFPathVisualObject pvo = (PDFPathVisualObject)voc.get(i);
                        // Examine all the path points and determine the minimum rectangle that bounds the path.
                        double minX = 999999, minY = 999999, maxX = -999999, maxY = -999999;
                        for (int j = 0; j < pvo.getPathItems().size(); j++)
                        {
                            PDFPathItem pi = pvo.getPathItems().get(j);
                            PDFPoint[] points = pi.getPoints(); 
                            if (points != null)
                            {
                                for (int k = 0; k < points.length; k++)
                                {
                                    if (minX >= points[k].getX())
                                    {
                                        minX = points[k].getX();
                                    }
                                    if (minY >= points[k].getY())
                                    {
                                        minY = points[k].getY();
                                    }
                                    if (maxX <= points[k].getX())
                                    {
                                        maxX = points[k].getX();
                                    }
                                    if (maxY <= points[k].getY())
                                    {
                                        maxY = points[k].getY();
                                    }
                                }
                            }
                        }

                        contour = new PDFPath();
                        contour.startSubpath(minX - 5, minY - 5);
                        contour.addLineTo(maxX + 5, minY - 5);
                        contour.addLineTo(maxX + 5, maxY + 5);
                        contour.addLineTo(minX - 5, maxY + 5);
                        contour.closeSubpath();
                        pageCanvas.drawPath(redPen, contour);

                        pageCanvas.drawString("Path", helvetica, brush, minX - 5, maxY + 5);
                        break;
                }
            }

            document.save("PageObjects.pdf");

            System.out.println("PDF document has been saved with success");
        } catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
