import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.actions.PDFGoTo3DViewAction;
import com.o2sol.pdf4java.annotations.*;
import com.o2sol.pdf4java.annotations.a3d.PDF3DAnnotation;
import com.o2sol.pdf4java.annotations.a3d.PDF3DProjection;
import com.o2sol.pdf4java.annotations.a3d.PDF3DStream;
import com.o2sol.pdf4java.annotations.a3d.PDF3DView;
import com.o2sol.pdf4java.annotations.richmedia.PDFRichMediaActivationCondition;
import com.o2sol.pdf4java.annotations.richmedia.PDFRichMediaAnnotation;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.graphics.*;
import com.o2sol.pdf4java.graphics.colors.PDFRgbColor;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFont;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFontFace;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class Annotations {
    public static void main(String[] args) throws IOException {
        try {
            PDFFixedDocument document = new PDFFixedDocument();
            PDFStandardFont font = new PDFStandardFont(PDFStandardFontFace.HELVETICA, 12);

            createTextAnnotations(document, font);

            createSquareAndCircleAnnotations(document, font);

            createFileAttachmentAnnotations(document, font);

            createInkAnnotations(document, font);

            createLineAnnotations(document, font);

            createPolygonAnnotations(document, font);

            createPolylineAnnotations(document, font);

            createCloudAnnotations(document, font);

            createRubberStampAnnotations(document, font);

            createTextMarkupAnnotations(document, font);

            createRichMediaAnnotations(document, font);

            create3dAnnotations(document, font);

            createRedactionAnnotations(document, font);

            document.save("Annotations.pdf");

            System.out.println("PDF document has been saved with success");
        }
        catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }

    public static void createTextAnnotations(PDFFixedDocument document, PDFStandardFont font) {
        PDFPage page = document.addPage();

        PDFBrush blackBrush = new PDFBrush();

        String[] textAnnotationNames = new String[]
                {
                        "Comment", "Check", "Circle", "Cross", "Help", "Insert", "Key", "NewParagraph",
                        "Note", "Paragraph", "RightArrow", "RightPointer", "Star", "UpArrow", "UpLeftArrow"
                };

        page.getCanvas().drawString("B/W text annotations", font, blackBrush, 50, 50);
        for (int i = 0; i < textAnnotationNames.length; i++)
        {
            PDFTextAnnotation ta = new PDFTextAnnotation();
            ta.setAuthor("com.o2sol.pdf4java");
            ta.setContents("I am a " + textAnnotationNames[i] + " annotation.");
            ta.setIcon(textAnnotationNames[i]);
            page.addAnnotation(ta);
            ta.setPosition(50, 100 + 40 * i);
            page.getCanvas().drawString(textAnnotationNames[i], font, blackBrush, 90, 100 + 40 * i);
        }

        page.getCanvas().drawString("Color text annotations", font, blackBrush, 300, 50);
        for (int i = 0; i < textAnnotationNames.length; i++)
        {
            PDFTextAnnotation ta = new PDFTextAnnotation();
            ta.setAuthor("com.o2sol.pdf4java");
            ta.setContents("I am a " + textAnnotationNames[i] + " annotation.");
            ta.setIcon(textAnnotationNames[i]);
            ta.setOutlineColor(PDFRgbColor.DARK_BLUE);
            ta.setInteriorColor(PDFRgbColor.LIGHT_GREEN);
            page.addAnnotation(ta);
            ta.setPosition(300, 100 + 40 * i);
            page.getCanvas().drawString(textAnnotationNames[i], font, blackBrush, 340, 100 + 40 * i);
        }

        page.getCanvas().drawString("Text annotations with custom icons", font, blackBrush, 50, 700);
        PDFTextAnnotation customTextAnnotation = new PDFTextAnnotation();
        customTextAnnotation.setAuthor("com.o2sol.pdf4java");
        customTextAnnotation.setContents("Text annotation with custom icon.");
        page.addAnnotation(customTextAnnotation);
        customTextAnnotation.setIcon("Custom icon appearance");
        customTextAnnotation.setPosition(50, 720);

        double appearanceWidth = 50, appearanceHeight = 50;
        PDFAnnotationAppearance customAppearance = new PDFAnnotationAppearance(appearanceWidth, appearanceHeight);
        PDFPen redPen = new PDFPen(new PDFRgbColor(255, 0, 0), 10);
        PDFPen bluePen = new PDFPen(new PDFRgbColor(0, 0, 192), 10);
        customAppearance.getCanvas().drawRectangle(redPen, 5, 5, 40, 40);
        customAppearance.getCanvas().drawLine(bluePen, 0, 0, appearanceWidth, appearanceHeight);
        customAppearance.getCanvas().drawLine(bluePen, 0, appearanceHeight, appearanceWidth, 0);
        customAppearance.getCanvas().compressAndClose();
        customTextAnnotation.setNormalAppearance(customAppearance);

        page.getCanvas().compressAndClose();
    }

    public static void createSquareAndCircleAnnotations(PDFFixedDocument document, PDFStandardFont font) {
        PDFPage page = document.addPage();

        PDFBrush blackBrush = new PDFBrush();

        page.getCanvas().drawString("Square annotations", font, blackBrush, 50, 50);

        PDFSquareAnnotation square1 = new PDFSquareAnnotation();
        page.addAnnotation(square1);
        square1.setAuthor("com.o2sol.pdf4java");
        square1.setContents("Square annotation with red border");
        square1.setBorder(new PDFAnnotationBorder());
        square1.getBorder().setColor(new PDFRgbColor(255, 0, 0));
        square1.getBorder().setWidth(3);
        square1.setDisplayRectangle(50, 70, 250, 150);

        PDFSquareAnnotation square2 = new PDFSquareAnnotation();
        page.addAnnotation(square2);
        square2.setAuthor("com.o2sol.pdf4java");
        square2.setContents("Square annotation with blue interior");
        square2.setBorder(new PDFAnnotationBorder());
        square2.getBorder().setColor(null);
        square2.getBorder().setWidth(0);
        square2.setInteriorColor(new PDFRgbColor(0, 0, 192));
        square2.setDisplayRectangle(50, 270, 250, 150);

        PDFSquareAnnotation square3 = new PDFSquareAnnotation();
        page.addAnnotation(square3);
        square3.setAuthor("com.o2sol.pdf4java");
        square3.setContents("Square annotation with yellow border and green interior");
        square3.setBorder(new PDFAnnotationBorder());
        square3.getBorder().setColor(new PDFRgbColor(255, 255, 0));
        square3.getBorder().setWidth(3);
        square3.setInteriorColor(new PDFRgbColor(0, 192, 0));
        square3.setDisplayRectangle(50, 470, 250, 150);

        page.getCanvas().drawString("Circle annotations", font, blackBrush, 350, 50);

        PDFCircleAnnotation circle1 = new PDFCircleAnnotation();
        page.addAnnotation(circle1);
        circle1.setAuthor("com.o2sol.pdf4java");
        circle1.setContents("Circle annotation with red border");
        circle1.setBorder(new PDFAnnotationBorder());
        circle1.getBorder().setColor(new PDFRgbColor(255, 0, 0));
        circle1.getBorder().setWidth(3);
        circle1.setDisplayRectangle(350, 70, 250, 150);

        PDFCircleAnnotation circle2 = new PDFCircleAnnotation();
        page.addAnnotation(circle2);
        circle2.setAuthor("com.o2sol.pdf4java");
        circle2.setContents("Circle annotation with blue interior");
        circle2.setBorder(new PDFAnnotationBorder());
        circle2.getBorder().setColor(null);
        circle2.getBorder().setWidth(0);
        circle2.setInteriorColor(new PDFRgbColor(0, 0, 192));
        circle2.setDisplayRectangle(350, 270, 250, 150);

        PDFCircleAnnotation circle3 = new PDFCircleAnnotation();
        page.addAnnotation(circle3);
        circle3.setAuthor("com.o2sol.pdf4java");
        circle3.setContents("Circle annotation with yellow border and green interior");
        circle3.setBorder(new PDFAnnotationBorder());
        circle3.getBorder().setColor(new PDFRgbColor(255, 255, 0));
        circle3.getBorder().setWidth(3);
        circle3.setInteriorColor(new PDFRgbColor(0, 192, 0));
        circle3.setDisplayRectangle(350, 470, 250, 150);

        page.getCanvas().compressAndClose();
    }

    public static void createFileAttachmentAnnotations(PDFFixedDocument document, PDFStandardFont font) {
        PDFPage page = document.addPage();

        PDFBrush blackBrush = new PDFBrush();

        Random rnd = new Random();
        // Random binary data to be used a file content for file attachment annotations.
        byte[] fileData = new byte[256];

        String[] fileAttachmentAnnotationNames = new String[]
                {
                        "Graph", "Paperclip", "PushPin", "Tag"
                };

        page.getCanvas().drawString("B/W file attachment annotations", font, blackBrush, 50, 50);
        for (int i = 0; i < fileAttachmentAnnotationNames.length; i++)
        {
            rnd.nextBytes(fileData);

            PDFFileAttachmentAnnotation faa = new PDFFileAttachmentAnnotation();
            faa.setAuthor("com.o2sol.pdf4java");
            faa.setContents("I am a " + fileAttachmentAnnotationNames[i] + " annotation.");
            faa.setIcon(fileAttachmentAnnotationNames[i]);
            faa.setPayload(fileData);
            faa.setFileName("BlackAndWhite" + fileAttachmentAnnotationNames[i] + ".txt");
            page.addAnnotation(faa);
            faa.setPosition(50, 100 + 40 * i);
            page.getCanvas().drawString(fileAttachmentAnnotationNames[i], font, blackBrush, 90, 100 + 40 * i);
        }

        byte[] rgb = new byte[3];
        page.getCanvas().drawString("Color file attachment annotations", font, blackBrush, 300, 50);
        for (int i = 0; i < fileAttachmentAnnotationNames.length; i++)
        {
            rnd.nextBytes(fileData);

            PDFFileAttachmentAnnotation faa = new PDFFileAttachmentAnnotation();
            faa.setAuthor("com.o2sol.pdf4java");
            faa.setContents("I am a " + fileAttachmentAnnotationNames[i] + " annotation.");
            faa.setIcon(fileAttachmentAnnotationNames[i]);
            faa.setPayload(fileData);
            faa.setFileName("Color" + fileAttachmentAnnotationNames[i] + ".txt");
            rnd.nextBytes(rgb);
            faa.setOutlineColor(PDFRgbColor.DARK_RED);
            rnd.nextBytes(rgb);
            faa.setInteriorColor(PDFRgbColor.LIGHT_BLUE);
            page.addAnnotation(faa);
            faa.setPosition(300, 100 + 40 * i);
            page.getCanvas().drawString(fileAttachmentAnnotationNames[i], font, blackBrush, 340, 100 + 40 * i);
        }

        page.getCanvas().drawString("File attachment annotations with custom icons", font, blackBrush, 50, 700);
        PDFFileAttachmentAnnotation customFileAttachmentAnnotation = new PDFFileAttachmentAnnotation();
        customFileAttachmentAnnotation.setAuthor("com.o2sol.pdf4java");
        customFileAttachmentAnnotation.setContents("File attachment annotation with custom icon.");
        page.addAnnotation(customFileAttachmentAnnotation);
        customFileAttachmentAnnotation.setIcon("Custom icon appearance");
        customFileAttachmentAnnotation.setPosition(50, 720);

        double appearanceWidth = 50, appearanceHeight = 50;
        PDFAnnotationAppearance customAppearance = new PDFAnnotationAppearance(appearanceWidth, appearanceHeight);
        PDFPen redPen = new PDFPen(new PDFRgbColor(255, 0, 0), 10);
        PDFPen bluePen = new PDFPen(new PDFRgbColor(0, 0, 192), 10);
        customAppearance.getCanvas().drawRectangle(redPen, 5, 5, 40, 40);
        customAppearance.getCanvas().drawLine(bluePen, 0, 0, appearanceWidth, appearanceHeight);
        customAppearance.getCanvas().drawLine(bluePen, 0, appearanceHeight, appearanceWidth, 0);
        customAppearance.getCanvas().compressAndClose();
        customFileAttachmentAnnotation.setNormalAppearance(customAppearance);

        page.getCanvas().compressAndClose();
    }

    public static void createInkAnnotations(PDFFixedDocument document, PDFStandardFont font) {
        PDFPage page = document.addPage();

        PDFBrush blackBrush = new PDFBrush();

        page.getCanvas().drawString("Ink annotations", font, blackBrush, 50, 50);

        Random rnd = new Random();

        // The ink annotation will contain 3 lines, each one with 10 points.
        PDFPoint[][] points = new PDFPoint[3][];
        for (int i = 0; i < points.length; i++)
        {
            points[i] = new PDFPoint[10];
            for (int j = 0; j < points[i].length; j++)
            {
                points[i][j] = new PDFPoint(rnd.nextDouble() * page.getWidth(), rnd.nextDouble() * page.getHeight());
            }
        }

        PDFInkAnnotation ia = new PDFInkAnnotation();
        page.addAnnotation(ia);
        ia.setContents("I am an ink annotation.");
        ia.setInkColor(new PDFRgbColor(255, 0, 255));
        ia.setInkWidth(5);
        ia.setPoints(points);

        page.getCanvas().compressAndClose();
    }

    public static void createLineAnnotations(PDFFixedDocument document, PDFStandardFont font) {
        PDFPage page = document.addPage();

        PDFBrush blackBrush = new PDFBrush();

        page.getCanvas().drawString("Line annotations", font, blackBrush, 50, 50);

        PDFLineEndStyle[] les = new PDFLineEndStyle[] {
                PDFLineEndStyle.CIRCLE, PDFLineEndStyle.CLOSED_ARROW, PDFLineEndStyle.NONE, PDFLineEndStyle.OPEN_ARROW
        };

        for (int i = 0; i < les.length; i++)
        {
            PDFLineAnnotation la = new PDFLineAnnotation();
            page.addAnnotation(la);
            la.setAuthor("com.o2sol.pdf4java");
            la.setContents("I am a line annotation with " + les[i].toString().substring(1) + " ending.");
            la.setStartPoint(50, 100 + 40 * i);
            la.setEndPoint(250, 100 + 40 * i);
            la.setEndLineStyle(les[i]);
            page.getCanvas().drawString("Line end symbol: " + les[i].toString().substring(1), font, blackBrush, 270, 100 + 40 * i);
        }
    }

    public static void createPolygonAnnotations(PDFFixedDocument document, PDFStandardFont font) {
        PDFPage page = document.addPage();

        PDFBrush blackBrush = new PDFBrush();

        page.getCanvas().drawString("Polygon annotations", font, blackBrush, 50, 50);

        int[] vertices = new int[]{ 3, 4, 5, 6 };
        double centerY = 125, centerX = 150;
        double radius = 50;

        for (int i = 0; i < vertices.length; i++)
        {
            PDFPoint[] points = new PDFPoint[vertices[i]];
            double angle = 90;
            double rotation = 360 / vertices[i];

            for (int j = 0; j < vertices[i]; j++)
            {
                points[j] = new PDFPoint();
                points[j].setX(centerX + radius * Math.cos(Math.PI * angle / 180));
                points[j].setY(centerY - radius * Math.sin(Math.PI * angle / 180));
                angle = angle + rotation;
            }

            PDFPolygonAnnotation polygon = new PDFPolygonAnnotation();
            page.addAnnotation(polygon);
            polygon.setAuthor("com.o2sol.pdf4java");
            polygon.setContents("Polygon annotation with " + vertices[i] + " vertices.");
            polygon.setPoints(points);
            polygon.setBorder(new PDFAnnotationBorder());
            polygon.getBorder().setColor(new PDFRgbColor(192, 0, 0));
            polygon.getBorder().setWidth(3);
            polygon.setInteriorColor(new PDFRgbColor(0, 0, 192));

            centerY = centerY + 150;
        }
    }

    public static void createPolylineAnnotations(PDFFixedDocument document, PDFStandardFont font) {
        PDFPage page = document.addPage();

        PDFBrush blackBrush = new PDFBrush();

        page.getCanvas().drawString("Polyline annotations", font, blackBrush, 50, 50);

        int[] vertices = new int[]{ 3, 4, 5, 6 };
        double centerY = 125, centerX = 150;
        double radius = 50;

        for (int i = 0; i < vertices.length; i++)
        {
            PDFPoint[] points = new PDFPoint[vertices[i]];
            double angle = 90;
            double rotation = 360 / vertices[i];

            for (int j = 0; j < vertices[i]; j++)
            {
                points[j] = new PDFPoint();
                points[j].setX(centerX + radius * Math.cos(Math.PI * angle / 180));
                points[j].setY(centerY - radius * Math.sin(Math.PI * angle / 180));
                angle = angle + rotation;
            }

            PDFPolylineAnnotation polyline = new PDFPolylineAnnotation();
            page.addAnnotation(polyline);
            polyline.setAuthor("O2S.Components.PDF4Java");
            polyline.setContents("Polyline annotation with " + vertices[i] + " vertices.");
            polyline.setPoints(points);
            polyline.setBorder(new PDFAnnotationBorder());
            polyline.getBorder().setColor(new PDFRgbColor(192, 0, 0));
            polyline.getBorder().setWidth(3);
            polyline.setInteriorColor(new PDFRgbColor(0, 0, 192));

            centerY = centerY + 150;
        }
    }

    private static void createCloudAnnotations(PDFFixedDocument document, PDFStandardFont font)
    {
        PDFBrush blackBrush = new PDFBrush();

        PDFPage page = document.addPage();

        page.getCanvas().drawString("Cloud square annotations", font, blackBrush, 50, 50);

        PDFCloudSquareAnnotation cloudSquare1 = new PDFCloudSquareAnnotation();
        page.addAnnotation(cloudSquare1);
        cloudSquare1.setAuthor("PDF4Java");
        cloudSquare1.setContents("Cloud square annotation with red border");
        cloudSquare1.setBorder(new PDFAnnotationBorder());
        cloudSquare1.getBorder().setColor(new PDFRgbColor(255, 0, 0));
        cloudSquare1.getBorder().setWidth(1);
        cloudSquare1.setIntensity(1);
        cloudSquare1.setDisplayRectangle(50, 70, 250, 150);

        PDFCloudSquareAnnotation cloudSquare2 = new PDFCloudSquareAnnotation();
        page.addAnnotation(cloudSquare2);
        cloudSquare2.setAuthor("PDF4Java");
        cloudSquare2.setContents("Cloud square annotation with blue interior");
        cloudSquare2.setBorder(new PDFAnnotationBorder());
        cloudSquare2.getBorder().setColor(null);
        cloudSquare2.getBorder().setWidth(0);
        cloudSquare2.setIntensity(2);
        cloudSquare2.setInteriorColor(new PDFRgbColor(0, 0, 192));
        cloudSquare2.setDisplayRectangle(50, 270, 250, 150);

        PDFCloudSquareAnnotation cloudSquare3 = new PDFCloudSquareAnnotation();
        page.addAnnotation(cloudSquare3);
        cloudSquare3.setAuthor("PDF4Java");
        cloudSquare3.setContents("Cloud square annotation with yellow border and green interior");
        cloudSquare3.setBorder(new PDFAnnotationBorder());
        cloudSquare3.getBorder().setColor(new PDFRgbColor(255, 255, 0));
        cloudSquare3.getBorder().setWidth(1);
        cloudSquare3.setInteriorColor(new PDFRgbColor(0, 192, 0));
        cloudSquare3.setDisplayRectangle(50, 470, 250, 150);

        page.getCanvas().drawString("Cloud polygon annotations", font, blackBrush, 350, 50);

        PDFCloudPolygonAnnotation cloudPolygon1 = new PDFCloudPolygonAnnotation();
        page.addAnnotation(cloudPolygon1);
        cloudPolygon1.setAuthor("PDF4Java");
        cloudPolygon1.setContents("Cloud polygon annotation with red border");
        cloudPolygon1.setBorder(new PDFAnnotationBorder());
        cloudPolygon1.getBorder().setColor(new PDFRgbColor(255, 0, 0));
        cloudPolygon1.getBorder().setWidth(3);
        cloudPolygon1.setPoints(new PDFPoint[] { new PDFPoint(350, 145), new PDFPoint(475, 70), new PDFPoint(600, 145), new PDFPoint(475, 220), new PDFPoint(350, 145) });

        PDFCloudPolygonAnnotation cloudPolygon2 = new PDFCloudPolygonAnnotation();
        page.addAnnotation(cloudPolygon2);
        cloudPolygon2.setAuthor("PDF4Java");
        cloudPolygon2.setContents("Cloud polygon annotation with blue interior");
        cloudPolygon2.setBorder(new PDFAnnotationBorder());
        cloudPolygon2.getBorder().setColor(null);
        cloudPolygon2.getBorder().setWidth(0);
        cloudPolygon2.setInteriorColor(new PDFRgbColor(0, 0, 192));
        cloudPolygon2.setIntensity(1);
        cloudPolygon2.setPoints(new PDFPoint[] {
                new PDFPoint(350, 345), new PDFPoint(370, 290), new PDFPoint(475, 270), new PDFPoint(580, 290),
                new PDFPoint(600, 345), new PDFPoint(580, 400), new PDFPoint(475, 420), new PDFPoint(370, 400), new PDFPoint(350, 345)
        });

        PDFCloudPolygonAnnotation cloudPolygon3 = new PDFCloudPolygonAnnotation();
        page.addAnnotation(cloudPolygon3);
        cloudPolygon3.setAuthor("PDF4Java");
        cloudPolygon3.setContents("Cloud polygon annotation with yellow border and green interior");
        cloudPolygon3.setBorder(new PDFAnnotationBorder());
        cloudPolygon3.getBorder().setColor(new PDFRgbColor(255, 255, 0));
        cloudPolygon3.getBorder().setWidth(1);
        cloudPolygon3.setInteriorColor(new PDFRgbColor(0, 192, 0));
        cloudPolygon3.setPoints(new PDFPoint[] {
                new PDFPoint(350, 545), new PDFPoint(455, 525), new PDFPoint(475, 470), new PDFPoint(495, 525),
                new PDFPoint(600, 545), new PDFPoint(495, 565), new PDFPoint(475, 620), new PDFPoint(455, 565), new PDFPoint(350, 545)
        });
    }

    public static void createRubberStampAnnotations(PDFFixedDocument document, PDFStandardFont font) {
        PDFPage page = document.addPage();

        PDFBrush blackBrush = new PDFBrush();

        String[] rubberStampAnnotationNames = new String[] {
                PDFRubberStampAnnotation.APPROVED, PDFRubberStampAnnotation.AS_IS, PDFRubberStampAnnotation.CONFIDENTIAL,
                PDFRubberStampAnnotation.DEPARTMENTAL, PDFRubberStampAnnotation.DRAFT, PDFRubberStampAnnotation.EXPERIMENTAL,
                PDFRubberStampAnnotation.EXPIRED, PDFRubberStampAnnotation.FINAL, PDFRubberStampAnnotation.FOR_COMMENT,
                PDFRubberStampAnnotation.FOR_PUBLIC_RELEASE, PDFRubberStampAnnotation.NOT_APPROVED, PDFRubberStampAnnotation.NOT_FOR_PUBLIC_RELEASE,
                PDFRubberStampAnnotation.SOLD, PDFRubberStampAnnotation.TOP_SECRET
        };

        page.getCanvas().drawString("Rubber stamp annotations", font, blackBrush, 50, 50);
        for (int i = 0; i < rubberStampAnnotationNames.length; i++)
        {
            PDFRubberStampAnnotation rsa = new PDFRubberStampAnnotation();
            rsa.setAuthor("com.o2sol.pdf4java");
            rsa.setContents("I am a " + rubberStampAnnotationNames[i] + " rubber stamp annotation.");
            rsa.setStampType(rubberStampAnnotationNames[i]);
            page.addAnnotation(rsa);
            rsa.setDisplayRectangle(50, 70 + 50 * i, 200, 40);
            page.getCanvas().drawString(rubberStampAnnotationNames[i], font, blackBrush, 270, 85 + 50 * i);
        }

        page.getCanvas().drawString("Stamp annotations with custom appearance", font, blackBrush, 350, 50);
        PDFRubberStampAnnotation customRubberStampAnnotation = new PDFRubberStampAnnotation();
        customRubberStampAnnotation.setContents("Rubber stamp annotation with custom appearance.");
        customRubberStampAnnotation.setStampType("Custom");
        page.addAnnotation(customRubberStampAnnotation);
        customRubberStampAnnotation.setDisplayRectangle(350, 70, 200, 40);

        double appearanceWidth = 50, appearanceHeight = 50;
        PDFAnnotationAppearance customAppearance = new PDFAnnotationAppearance(appearanceWidth, appearanceHeight);
        PDFPen redPen = new PDFPen(new PDFRgbColor(255, 0, 0), 10);
        PDFPen bluePen = new PDFPen(new PDFRgbColor(0, 0, 192), 10);
        customAppearance.getCanvas().drawRectangle(redPen, 5, 5, 40, 40);
        customAppearance.getCanvas().drawLine(bluePen, 0, 0, appearanceWidth, appearanceHeight);
        customAppearance.getCanvas().drawLine(bluePen, 0, appearanceHeight, appearanceWidth, 0);
        customAppearance.getCanvas().compressAndClose();
        customRubberStampAnnotation.setNormalAppearance(customAppearance);
    }

    public static void createTextMarkupAnnotations(PDFFixedDocument document, PDFStandardFont font) {
        PDFPage page = document.addPage();

        PDFBrush blackBrush = new PDFBrush();

        page.getCanvas().drawString("Text markup annotations", font, blackBrush, 50, 50);

        PDFTextMarkupAnnotationType[] tmat = new PDFTextMarkupAnnotationType[] {
                PDFTextMarkupAnnotationType.HIGHLIGHT, PDFTextMarkupAnnotationType.SQUIGGLY, PDFTextMarkupAnnotationType.STRIKEOUT, PDFTextMarkupAnnotationType.UNDERLINE
        };

        PDFStringAppearanceOptions sao = new PDFStringAppearanceOptions();
        sao.setBrush(blackBrush);
        sao.setFont(font);

        PDFStringLayoutOptions slo = new PDFStringLayoutOptions();
        slo.setHorizontalAlign(PDFStringHorizontalAlign.CENTER);
        slo.setVerticalAlign(PDFStringVerticalAlign.BOTTOM);
        for (int i = 0; i < tmat.length; i++)
        {
            PDFTextMarkupAnnotation tma = new PDFTextMarkupAnnotation();
            page.addAnnotation(tma);
            tma.setDisplayRectangle(50, 100 + 50 * i, 200, font.getSize() + 2);
            tma.setTextMarkupType(tmat[i]);

            slo.setX(150);
            slo.setY(100 + 50 * i + font.getSize());

            page.getCanvas().drawString(tmat[i].toString().substring(1) + " annotation.", sao, slo);
        }
    }

    public static void createRichMediaAnnotations(PDFFixedDocument document, PDFStandardFont font) throws IOException {
        PDFPage page = document.addPage();

        PDFBrush blackBrush = new PDFBrush();

        page.getCanvas().drawString("Rich media annotations", font, blackBrush, 50, 50);

        RandomAccessFile flashFile = new RandomAccessFile("..\\..\\SupportFiles\\clock.swf", "rwd");
        byte[] flashContent = new byte[(int)flashFile.length()];
        flashFile.read(flashContent, 0, flashContent.length);
        flashFile.close();

        PDFRichMediaAnnotation rma = new PDFRichMediaAnnotation();
        page.addAnnotation(rma);
        rma.setDisplayRectangle(100, 100, 400, 400);
        rma.setRichMediaPayload(flashContent);
        rma.setRichMediaFile("clock.swf");
        rma.setActivationCondition(PDFRichMediaActivationCondition.PAGE_VISIBLE);
    }

    public static void create3dAnnotations(PDFFixedDocument document, PDFStandardFont font) throws IOException {
        PDFPage page = document.addPage();
        page.setRotation(90);

        PDFBrush blackBrush = new PDFBrush();

        page.getCanvas().drawString("3D annotations", font, blackBrush, 50, 50);

        RandomAccessFile u3dFile = new RandomAccessFile("..\\..\\SupportFiles\\airplane.u3d", "rwd");
        byte[] u3dContent = new byte[(int)u3dFile.length()];
        u3dFile.read(u3dContent, 0, u3dContent.length);
        u3dFile.close();

        PDF3DView view0= new PDF3DView();
        view0.setCameraToWorldMatrix(new double[] { 1, 0, 0, 0, 0, -1, 0, 1, 0, -0.417542, -0.881257, -0.125705 });
        view0.setCenterOfOrbit(0.123106);
        view0.setExternalName("Default");
        view0.setInternalName("Default");
        view0.setProjection(new PDF3DProjection());
        view0.getProjection().setFieldOfView(30);

        PDF3DView view1 = new PDF3DView();
        view1.setCameraToWorldMatrix(new double[] { -0.999888, 0.014949, 0, 0.014949, 0.999887, 0.00157084, 0.0000234825, 0.00157066, -0.999999, -0.416654, -0.761122, -0.00184508 });
        view1.setCenterOfOrbit(0.123106);
        view1.setExternalName("Top");
        view1.setInternalName("Top");
        view1.setProjection(new PDF3DProjection());
        view1.getProjection().setFieldOfView(14.8096);

        PDF3DView view2 = new PDF3DView();
        view2.setCameraToWorldMatrix(new double[] { -1.0, -0.0000411671, 0.0000000000509201, -0.00000101387, 0.0246288, 0.999697, -0.0000411546, 0.999697, -0.0246288, -0.417072, -0.881787, -0.121915 });
        view2.setCenterOfOrbit(0.123106);
        view2.setExternalName("Side");
        view2.setInternalName("Side");
        view2.setProjection(new PDF3DProjection());
        view2.getProjection().setFieldOfView(12.3794);

        PDF3DView view3 = new PDF3DView();
        view3.setCameraToWorldMatrix(new double[] { -0.797002, -0.603977, -0.0000000438577, -0.294384, 0.388467, 0.873173, -0.527376, 0.695921, -0.48741, -0.3518, -0.844506, -0.0675629 });
        view3.setCenterOfOrbit(0.123106);
        view3.setExternalName("Isometric");
        view3.setInternalName("Isometric");
        view3.setProjection(new PDF3DProjection());
        view3.getProjection().setFieldOfView(15.1226);

        PDF3DView view4= new PDF3DView();
        view4.setCameraToWorldMatrix(new double[] { 0.00737633, -0.999973, -0.0000000000147744, -0.0656414, -0.000484206, 0.997843, -0.997816, -0.00736042, -0.0656432, -0.293887, -0.757928, -0.119485 });
        view4.setCenterOfOrbit(0.123106);
        view4.setExternalName("Front");
        view4.setInternalName("Front");
        view4.setProjection(new PDF3DProjection());
        view4.getProjection().setFieldOfView(15.1226);

        PDF3DView view5 = new PDF3DView();
        view5.setCameraToWorldMatrix(new double[] { 0.0151008, 0.999886, 0.0000000000261366, 0.0492408, -0.000743662, 0.998787, 0.998673, -0.0150825, -0.0492464, -0.540019, -0.756862, -0.118884 });
        view5.setCenterOfOrbit(0.123106);
        view5.setExternalName("Back");
        view5.setInternalName("Back");
        view5.setProjection(new PDF3DProjection());
        view5.getProjection().setFieldOfView(12.3794);

        PDF3DStream _3dStream = new PDF3DStream();
        _3dStream.getViews().add(view0);
        _3dStream.getViews().add(view1);
        _3dStream.getViews().add(view2);
        _3dStream.getViews().add(view3);
        _3dStream.getViews().add(view4);
        _3dStream.getViews().add(view5);
        _3dStream.setContent(u3dContent);
        _3dStream.setDefaultViewIndex(0);
        PDF3DAnnotation _3da = new PDF3DAnnotation();
        _3da.setStream(_3dStream);

        PDFAnnotationAppearance appearance = new PDFAnnotationAppearance(200, 200);
        appearance.getCanvas().drawString("Click to activate", font, blackBrush, 50, 50);
        _3da.setNormalAppearance(appearance);

        page.addAnnotation(_3da);
        _3da.setDisplayRectangle(36, 36, 720, 540);

        PDFStringAppearanceOptions sao = new PDFStringAppearanceOptions();
        sao.setFont(font);
        sao.setBrush(blackBrush);
        PDFStringLayoutOptions slo = new PDFStringLayoutOptions();
        slo.setY(585 + 18 / 2);
        slo.setHorizontalAlign(PDFStringHorizontalAlign.CENTER);
        slo.setVerticalAlign(PDFStringVerticalAlign.MIDDLE);

        PDFPen blackPen = new PDFPen(new PDFRgbColor(0, 0, 0), 1);

        page.getCanvas().drawRectangle(blackPen, 50, 585, 120, 18);
        slo.setX(50 + 120 / 2);
        page.getCanvas().drawString("Top", sao, slo);

        PDFGoTo3DViewAction gotoTopView = new PDFGoTo3DViewAction();
        gotoTopView.setViewIndex(1);
        gotoTopView.setTargetAnnotation(_3da);
        PDFLinkAnnotation linkGotoTopView = new PDFLinkAnnotation();
        page.addAnnotation(linkGotoTopView);
        linkGotoTopView.setDisplayRectangle(50, 585, 120, 18);
        linkGotoTopView.setAction(gotoTopView);

        page.getCanvas().drawRectangle(blackPen, 190, 585, 120, 18);
        slo.setX(190 + 120 / 2);
        page.getCanvas().drawString("Side", sao, slo);

        PDFGoTo3DViewAction gotoSideView = new PDFGoTo3DViewAction();
        gotoSideView.setViewIndex(2);
        gotoSideView.setTargetAnnotation(_3da);
        PDFLinkAnnotation linkGotoSideView = new PDFLinkAnnotation();
        page.addAnnotation(linkGotoSideView);
        linkGotoSideView.setDisplayRectangle(190, 585, 120, 18);
        linkGotoSideView.setAction(gotoSideView);

        page.getCanvas().drawRectangle(blackPen, 330, 585, 120, 18);
        slo.setX(330 + 120 / 2);
        page.getCanvas().drawString("Isometric", sao, slo);

        PDFGoTo3DViewAction gotoIsometricView = new PDFGoTo3DViewAction();
        gotoIsometricView.setViewIndex(3);
        gotoIsometricView.setTargetAnnotation(_3da);
        PDFLinkAnnotation linkGotoIsometricView = new PDFLinkAnnotation();
        page.addAnnotation(linkGotoIsometricView);
        linkGotoIsometricView.setDisplayRectangle(330, 585, 120, 18);
        linkGotoIsometricView.setAction(gotoIsometricView);

        page.getCanvas().drawRectangle(blackPen, 470, 585, 120, 18);
        slo.setX(470 + 120 / 2);
        page.getCanvas().drawString("Front", sao, slo);

        PDFGoTo3DViewAction gotoFrontView = new PDFGoTo3DViewAction();
        gotoFrontView.setViewIndex(4);
        gotoFrontView.setTargetAnnotation(_3da);
        PDFLinkAnnotation linkGotoFrontView = new PDFLinkAnnotation();
        page.addAnnotation(linkGotoFrontView);
        linkGotoFrontView.setDisplayRectangle(470, 585, 120, 18);
        linkGotoFrontView.setAction(gotoFrontView);

        page.getCanvas().drawRectangle(blackPen, 610, 585, 120, 18);
        slo.setX(610 + 120 / 2);
        page.getCanvas().drawString("Back", sao, slo);

        PDFGoTo3DViewAction gotoBackView = new PDFGoTo3DViewAction();
        gotoBackView.setViewIndex(5);
        gotoBackView.setTargetAnnotation(_3da);
        PDFLinkAnnotation linkGotoBackView = new PDFLinkAnnotation();
        page.addAnnotation(linkGotoBackView);
        linkGotoBackView.setDisplayRectangle(610, 585, 120, 18);
        linkGotoBackView.setAction(gotoBackView);
    }

    public static void createRedactionAnnotations(PDFFixedDocument document, PDFStandardFont font) {
        PDFPage page = document.addPage();

        PDFBrush blackBrush = new PDFBrush();

        page.getCanvas().drawString("Redaction annotations", font, blackBrush, 50, 50);

        PDFStandardFont helvetica = new PDFStandardFont(PDFStandardFontFace.HELVETICA, 10);
        page.getCanvas().drawString(
                "Open the file with Adobe Acrobat, right click on the annotation and select \"Apply redactions\" The text under the annotation will be redacted.",
                helvetica, blackBrush, 50, 110);

        double w = 250, h = 150;
        PDFFormXObject redactionAppearance = new PDFFormXObject(w, h);
        redactionAppearance.getCanvas().drawRectangle(new PDFBrush(PDFRgbColor.LIGHT_GREEN), 0, 0, w, h);
        PDFStringAppearanceOptions sao = new PDFStringAppearanceOptions();
        sao.setBrush(new PDFBrush(PDFRgbColor.DARK_RED));
        sao.setFont(new PDFStandardFont(PDFStandardFontFace.HELVETICA_BOLD, 32));
        PDFStringLayoutOptions slo = new PDFStringLayoutOptions();
        slo.setWidth(w);
        slo.setHeight(h);
        slo.setX(0);
        slo.setY(0);
        slo.setHorizontalAlign(PDFStringHorizontalAlign.CENTER);
        slo.setVerticalAlign(PDFStringVerticalAlign.MIDDLE);
        redactionAppearance.getCanvas().drawString("This content has been redacted", sao, slo);

        PDFRedactionAnnotation redactionAnnotation = new PDFRedactionAnnotation();
        page.addAnnotation(redactionAnnotation);
        redactionAnnotation.setAuthor("com.o2sol.pdf4java");
        redactionAnnotation.setBorder(new PDFAnnotationBorder());
        redactionAnnotation.getBorder().setColor(new PDFRgbColor(192, 0, 0));
        redactionAnnotation.getBorder().setWidth(1);
        redactionAnnotation.setOverlayAppearance(redactionAppearance);
        redactionAnnotation.setDisplayRectangle(50, 100, 250, 150);
    }
}
