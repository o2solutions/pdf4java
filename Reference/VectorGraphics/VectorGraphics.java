import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.graphics.*;
import com.o2sol.pdf4java.graphics.colors.*;
import com.o2sol.pdf4java.graphics.fonts.PDFFont;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFont;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFontFace;
import com.o2sol.pdf4java.graphics.patterns.PDFColoredTilingPattern;
import com.o2sol.pdf4java.graphics.patterns.PDFShadingPattern;
import com.o2sol.pdf4java.graphics.patterns.PDFUncoloredTilingPattern;
import com.o2sol.pdf4java.graphics.shadings.PDFAxialShading;
import com.o2sol.pdf4java.graphics.shadings.PDFRadialShading;
import com.o2sol.pdf4java.pdffunctions.PDFExponentialFunction;
import com.o2sol.pdf4java.pdffunctions.PDFStitchingFunction;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class VectorGraphics {
    public static void main(String[] args) throws IOException {
        try {
            PDFFixedDocument document = new PDFFixedDocument();
            PDFStandardFont helveticaBoldTitle = new PDFStandardFont(PDFStandardFontFace.HELVETICABOLD, 16);
            PDFStandardFont helveticaSection = new PDFStandardFont(PDFStandardFontFace.HELVETICA, 10);

            RandomAccessFile iccFile = new RandomAccessFile("..\\..\\SupportFiles\\rgb.icc", "rwd");
            byte[] iccProfile = new byte[(int)iccFile.length()];
            iccFile.read(iccProfile, 0, iccProfile.length);
            iccFile.close();

            PDFPage page = document.addPage();
            drawLines(page, helveticaBoldTitle, helveticaSection);

            page = document.addPage();
            drawRectangles(page, helveticaBoldTitle, helveticaSection);

            page = document.addPage();
            drawRoundRectangles(page, helveticaBoldTitle, helveticaSection);

            page = document.addPage();
            drawEllipses(page, helveticaBoldTitle, helveticaSection);

            page = document.addPage();
            drawArcsAndPies(page, helveticaBoldTitle, helveticaSection);

            page = document.addPage();
            drawBezierCurves(page, helveticaBoldTitle, helveticaSection);

            page = document.addPage();
            drawAffineTransformations(page, helveticaBoldTitle, helveticaSection);

            page = document.addPage();
            drawColorsAndColorSpaces(page, helveticaBoldTitle, helveticaSection, iccProfile);

            page = document.addPage();
            drawShadings(page, helveticaBoldTitle, helveticaSection);

            page = document.addPage();
            drawPatterns(page, helveticaBoldTitle, helveticaSection);

            page = document.addPage();
            drawFormXObjects(page, helveticaBoldTitle, helveticaSection);

            document.save("VectorGraphics.pdf");

            System.out.println("PDF document has been saved with success");
        }
        catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }

    private static void drawLines(PDFPage page, PDFFont titleFont, PDFFont sectionFont)
    {
        PDFBrush brush = new PDFBrush();
        PDFPen blackPen = new PDFPen(PDFRgbColor.BLACK, 1);
        PDFPen bluePen = new PDFPen(PDFRgbColor.LIGHT_BLUE, 16);
        PDFPageCanvas pageCanvas = page.getCanvas();

        pageCanvas.drawString("Lines", titleFont, brush, 20, 50);

        pageCanvas.drawString("Line styles:", sectionFont, brush, 20, 70);
        pageCanvas.drawString("Solid", sectionFont, brush, 20, 90);
        pageCanvas.drawLine(blackPen, 100, 95, 400, 95);
        pageCanvas.drawString("Dashed", sectionFont, brush, 20, 110);
        blackPen.setDashPattern(new double[] { 3, 3 });
        pageCanvas.drawLine(blackPen, 100, 115, 400, 115);

        pageCanvas.drawString("Line cap styles:", sectionFont, brush, 20, 150);
        pageCanvas.drawString("Flat", sectionFont, brush, 20, 175);
        pageCanvas.drawLine(bluePen, 100, 180, 400, 180);
        blackPen.setDashPattern(null);
        pageCanvas.drawLine(blackPen, 100, 180, 400, 180);
        pageCanvas.drawString("Square", sectionFont, brush, 20, 195);
        bluePen.setLineCap(PDFLineCap.SQUARE);
        pageCanvas.drawLine(bluePen, 100, 200, 400, 200);
        blackPen.setDashPattern(null);
        pageCanvas.drawLine(blackPen, 100, 200, 400, 200);
        pageCanvas.drawString("Round", sectionFont, brush, 20, 215);
        bluePen.setLineCap(PDFLineCap.ROUND);
        pageCanvas.drawLine(bluePen, 100, 220, 400, 220);
        blackPen.setDashPattern(null);
        pageCanvas.drawLine(blackPen, 100, 220, 400, 220);

        pageCanvas.drawString("Line join styles:", sectionFont, brush, 20, 250);
        pageCanvas.drawString("Miter", sectionFont, brush, 20, 280);
        PDFPath miterPath = new PDFPath();
        miterPath.startSubpath(150, 320);
        miterPath.addLineTo(250, 260);
        miterPath.addLineTo(350, 320);
        bluePen.setLineCap(PDFLineCap.FLAT);
        bluePen.setLineJoin(PDFLineJoin.MITER);
        pageCanvas.drawPath(bluePen, miterPath);

        pageCanvas.drawString("Bevel", sectionFont, brush, 20, 360);
        PDFPath bevelPath = new PDFPath();
        bevelPath.startSubpath(150, 400);
        bevelPath.addLineTo(250, 340);
        bevelPath.addLineTo(350, 400);
        bluePen.setLineCap(PDFLineCap.FLAT);
        bluePen.setLineJoin(PDFLineJoin.BEVEL);
        pageCanvas.drawPath(bluePen, bevelPath);

        pageCanvas.drawString("Round", sectionFont, brush, 20, 440);
        PDFPath roundPath = new PDFPath();
        roundPath.startSubpath(150, 480);
        roundPath.addLineTo(250, 420);
        roundPath.addLineTo(350, 480);
        bluePen.setLineCap(PDFLineCap.FLAT);
        bluePen.setLineJoin(PDFLineJoin.ROUND);
        pageCanvas.drawPath(bluePen, roundPath);

        pageCanvas.drawString("Random lines clipped to rectangle", sectionFont, brush, 20, 520);
        PDFPath clipPath = new PDFPath();
        clipPath.addRectangle(20, 550, 570, 230);

        pageCanvas.saveGraphicsState();
        pageCanvas.setClip(clipPath);

        PDFRgbColor randomColor = new PDFRgbColor();
        PDFPen randomPen = new PDFPen(randomColor, 1);
        Random rnd = new Random();
        for (int i = 0; i < 100; i++)
        {
            randomColor.setR(rnd.nextInt(256));
            randomColor.setG(rnd.nextInt(256));
            randomColor.setB(rnd.nextInt(256));

            pageCanvas.drawLine(randomPen, rnd.nextDouble() * page.getWidth(), 550 + rnd.nextDouble() * 250, rnd.nextDouble() * page.getWidth(), 550 + rnd.nextDouble() * 250);
        }

        pageCanvas.restoreGraphicsState();

        pageCanvas.drawPath(blackPen, clipPath);

        pageCanvas.compressAndClose();
    }

    private static void drawRectangles(PDFPage page, PDFFont titleFont, PDFFont sectionFont)
    {
        PDFBrush brush = new PDFBrush();
        PDFPen blackPen = new PDFPen(PDFRgbColor.BLACK, 1);
        PDFPen redPen = new PDFPen(PDFRgbColor.RED, 1);

        PDFRgbColor randomPenColor = new PDFRgbColor();
        PDFPen randomPen = new PDFPen(randomPenColor, 1);
        PDFRgbColor randomBrushColor = new PDFRgbColor();
        PDFBrush randomBrush = new PDFBrush(randomBrushColor);
        PDFPageCanvas pageCanvas = page.getCanvas();

        pageCanvas.drawString("Rectangles", titleFont, brush, 20, 50);

        pageCanvas.drawLine(blackPen, 20, 150, 300, 150);
        pageCanvas.drawLine(blackPen, 80, 70, 80, 350);
        pageCanvas.drawRectangle(redPen, 80, 150, 180, 100);

        pageCanvas.drawLine(blackPen, 320, 150, 600, 150);
        pageCanvas.drawLine(blackPen, 380, 70, 380, 350);
        pageCanvas.drawRectangle(redPen, 380, 150, 180, 100, 30);

        pageCanvas.drawString("Random rectangles clipped to view", sectionFont, brush, 20, 385);
        PDFPath rectPath = new PDFPath();
        rectPath.addRectangle(20, 400, 570, 300);

        pageCanvas.saveGraphicsState();
        pageCanvas.setClip(rectPath);

        Random rnd = new Random();
        for (int i = 0; i < 100; i++)
        {
            randomPenColor.setR(rnd.nextInt(256));
            randomPenColor.setG(rnd.nextInt(256));
            randomPenColor.setB(rnd.nextInt(256));

            randomBrushColor.setR(rnd.nextInt(256));
            randomBrushColor.setG(rnd.nextInt(256));
            randomBrushColor.setB(rnd.nextInt(256));

            int mode = rnd.nextInt(3);
            double left = rnd.nextDouble() * page.getWidth();
            double top = 380 + rnd.nextDouble() * 350;
            double width = rnd.nextDouble() * page.getWidth();
            double height = rnd.nextDouble() * 250;
            double orientation = rnd.nextInt(360);
            switch (mode)
            {
                case 0:
                    // Stroke rectangle outline
                    pageCanvas.drawRectangle(randomPen, left, top, width, height, orientation);
                    break;
                case 1:
                    // Fill rectangle interior
                    pageCanvas.drawRectangle(randomBrush, left, top, width, height, orientation);
                    break;
                case 2:
                    // Stroke and fill rectangle
                    pageCanvas.drawRectangle(randomPen, randomBrush, left, top, width, height, orientation);
                    break;
            }
        }

        pageCanvas.restoreGraphicsState();

        pageCanvas.drawPath(blackPen, rectPath);

        pageCanvas.compressAndClose();
    }

    private static void drawRoundRectangles(PDFPage page, PDFFont titleFont, PDFFont sectionFont)
    {
        PDFBrush brush = new PDFBrush();
        PDFPen blackPen = new PDFPen(PDFRgbColor.BLACK, 1);
        PDFPen redPen = new PDFPen(PDFRgbColor.RED, 1);
        PDFPageCanvas pageCanvas = page.getCanvas();

        PDFRgbColor randomPenColor = new PDFRgbColor();
        PDFPen randomPen = new PDFPen(randomPenColor, 1);
        PDFRgbColor randomBrushColor = new PDFRgbColor();
        PDFBrush randomBrush = new PDFBrush(randomBrushColor);

        pageCanvas.drawString("Round rectangles", titleFont, brush, 20, 50);

        pageCanvas.drawLine(blackPen, 20, 150, 300, 150);
        pageCanvas.drawLine(blackPen, 80, 70, 80, 350);
        pageCanvas.drawRoundRectangle(redPen, 80, 150, 180, 100, 20, 20);

        pageCanvas.drawLine(blackPen, 320, 150, 600, 150);
        pageCanvas.drawLine(blackPen, 380, 70, 380, 350);
        pageCanvas.drawRoundRectangle(redPen, 380, 150, 180, 100, 20, 20, 30);

        pageCanvas.drawString("Random round rectangles clipped to view", sectionFont, brush, 20, 385);
        PDFPath roundRectPath = new PDFPath();
        roundRectPath.addRoundRectangle(20, 400, 570, 300, 20, 20);

        pageCanvas.saveGraphicsState();
        pageCanvas.setClip(roundRectPath);

        Random rnd = new Random();
        for (int i = 0; i < 100; i++)
        {
            randomPenColor.setR(rnd.nextInt(256));
            randomPenColor.setG(rnd.nextInt(256));
            randomPenColor.setB(rnd.nextInt(256));

            randomBrushColor.setR(rnd.nextInt(256));
            randomBrushColor.setG(rnd.nextInt(256));
            randomBrushColor.setB(rnd.nextInt(256));

            int mode = rnd.nextInt(3);
            double left = rnd.nextDouble() * page.getWidth();
            double top = 380 + rnd.nextDouble() * 350;
            double width = rnd.nextDouble() * page.getWidth();
            double height = rnd.nextDouble() * 250;
            double orientation = rnd.nextInt(360);
            switch (mode)
            {
                case 0:
                    // Stroke rectangle outline
                    pageCanvas.drawRoundRectangle(randomPen, left, top, width, height, width * 0.1, height * 0.1, orientation);
                    break;
                case 1:
                    // Fill rectangle interior
                    pageCanvas.drawRoundRectangle(randomBrush, left, top, width, height, width * 0.1, height * 0.1, orientation);
                    break;
                case 2:
                    // Stroke and fill rectangle
                    pageCanvas.drawRoundRectangle(randomPen, randomBrush, left, top, width, height, width * 0.1, height * 0.1, orientation);
                    break;
            }
        }

        pageCanvas.restoreGraphicsState();

        pageCanvas.drawPath(blackPen, roundRectPath);

        pageCanvas.compressAndClose();
    }

    private static void drawEllipses(PDFPage page, PDFFont titleFont, PDFFont sectionFont)
    {
        PDFBrush brush = new PDFBrush();
        PDFPen blackPen = new PDFPen(PDFRgbColor.BLACK, 1);
        PDFPen redPen = new PDFPen(PDFRgbColor.RED, 1);
        PDFPageCanvas pageCanvas = page.getCanvas();

        PDFRgbColor randomPenColor = new PDFRgbColor();
        PDFPen randomPen = new PDFPen(randomPenColor, 1);
        PDFRgbColor randomBrushColor = new PDFRgbColor();
        PDFBrush randomBrush = new PDFBrush(randomBrushColor);

        pageCanvas.drawString("Ellipses", titleFont, brush, 20, 50);

        pageCanvas.drawLine(blackPen, 20, 150, 300, 150);
        pageCanvas.drawLine(blackPen, 80, 70, 80, 350);
        pageCanvas.drawEllipse(redPen, 80, 150, 180, 100);

        pageCanvas.drawLine(blackPen, 320, 150, 600, 150);
        pageCanvas.drawLine(blackPen, 380, 70, 380, 350);
        pageCanvas.drawEllipse(redPen, 380, 150, 180, 100, 30);

        pageCanvas.drawString("Random ellipses clipped to view", sectionFont, brush, 20, 385);
        PDFPath ellipsePath = new PDFPath();
        ellipsePath.addEllipse(20, 400, 570, 300);

        pageCanvas.saveGraphicsState();
        pageCanvas.setClip(ellipsePath);

        Random rnd = new Random();
        for (int i = 0; i < 100; i++)
        {
            randomPenColor.setR(rnd.nextInt(256));
            randomPenColor.setG(rnd.nextInt(256));
            randomPenColor.setB(rnd.nextInt(256));

            randomBrushColor.setR(rnd.nextInt(256));
            randomBrushColor.setG(rnd.nextInt(256));
            randomBrushColor.setB(rnd.nextInt(256));

            int mode = rnd.nextInt(3);
            double left = rnd.nextDouble() * page.getWidth();
            double top = 380 + rnd.nextDouble() * 350;
            double width = rnd.nextDouble() * page.getWidth();
            double height = rnd.nextDouble() * 250;
            double orientation = rnd.nextInt(360);
            switch (mode)
            {
                case 0:
                    // Stroke ellipse outline
                    pageCanvas.drawEllipse(randomPen, left, top, width, height, orientation);
                    break;
                case 1:
                    // Fill ellipse interior
                    pageCanvas.drawEllipse(randomBrush, left, top, width, height, orientation);
                    break;
                case 2:
                    // Stroke and fill ellipse
                    pageCanvas.drawEllipse(randomPen, randomBrush, left, top, width, height, orientation);
                    break;
            }
        }

        pageCanvas.restoreGraphicsState();

        pageCanvas.drawPath(blackPen, ellipsePath);

        pageCanvas.compressAndClose();
    }

    private static void drawArcsAndPies(PDFPage page, PDFFont titleFont, PDFFont sectionFont)
    {
        PDFBrush brush = new PDFBrush();
        PDFPen blackPen = new PDFPen(PDFRgbColor.BLACK, 1);
        PDFPen redPen = new PDFPen(PDFRgbColor.RED, 1);
        PDFPageCanvas pageCanvas = page.getCanvas();

        PDFRgbColor randomPenColor = new PDFRgbColor();
        PDFPen randomPen = new PDFPen(randomPenColor, 1);
        PDFRgbColor randomBrushColor = new PDFRgbColor();
        PDFBrush randomBrush = new PDFBrush(randomBrushColor);

        pageCanvas.drawString("Arcs", titleFont, brush, 20, 50);
        pageCanvas.drawString("Pies", titleFont, brush, 310, 50);

        pageCanvas.drawLine(blackPen, 20, 210, 300, 210);
        pageCanvas.drawLine(blackPen, 160, 70, 160, 350);
        pageCanvas.drawLine(blackPen, 310, 210, 590, 210);
        pageCanvas.drawLine(blackPen, 450, 70, 450, 350);

        blackPen.setDashPattern(new double[] { 2, 2 });
        pageCanvas.drawLine(blackPen, 20, 70, 300, 350);
        pageCanvas.drawLine(blackPen, 20, 350, 300, 70);
        pageCanvas.drawLine(blackPen, 310, 70, 590, 350);
        pageCanvas.drawLine(blackPen, 310, 350, 590, 70);

        pageCanvas.drawArc(redPen, 30, 80, 260, 260, 0, 135);
        pageCanvas.drawPie(redPen, 320, 80, 260, 260, 45, 270);

        pageCanvas.drawString("Random arcs and pies clipped to view", sectionFont, brush, 20, 385);
        PDFPath rectPath = new PDFPath();
        rectPath.addRectangle(20, 400, 570, 300);

        pageCanvas.saveGraphicsState();
        pageCanvas.setClip(rectPath);

        Random rnd = new Random();
        for (int i = 0; i < 100; i++)
        {
            randomPenColor.setR(rnd.nextInt(256));
            randomPenColor.setG(rnd.nextInt(256));
            randomPenColor.setB(rnd.nextInt(256));

            randomBrushColor.setR(rnd.nextInt(256));
            randomBrushColor.setG(rnd.nextInt(256));
            randomBrushColor.setB(rnd.nextInt(256));

            int mode = rnd.nextInt(4);
            double left = rnd.nextDouble() * page.getWidth();
            double top = 380 + rnd.nextDouble() * 350;
            double width = rnd.nextDouble() * page.getWidth();
            double height = rnd.nextDouble() * 250;
            double startAngle = rnd.nextInt(360);
            double sweepAngle = rnd.nextInt(360);
            switch (mode)
            {
                case 0:
                    // Stroke arc outline
                    pageCanvas.drawArc(randomPen, left, top, width, height, startAngle, sweepAngle);
                    break;
                case 1:
                    // Stroke pie outline
                    pageCanvas.drawPie(randomPen, left, top, width, height, startAngle, sweepAngle);
                    break;
                case 2:
                    // Fill pie interior
                    pageCanvas.drawPie(randomBrush, left, top, width, height, startAngle, sweepAngle);
                    break;
                case 3:
                    // Stroke and fill pie
                    pageCanvas.drawPie(randomPen, randomBrush, left, top, width, height, startAngle, sweepAngle);
                    break;
            }
        }

        pageCanvas.restoreGraphicsState();

        blackPen.setDashPattern(null);
        pageCanvas.drawPath(blackPen, rectPath);

        pageCanvas.compressAndClose();
    }

    private static void drawBezierCurves(PDFPage page, PDFFont titleFont, PDFFont sectionFont)
    {
        PDFBrush brush = new PDFBrush();
        PDFPen blackPen = new PDFPen(PDFRgbColor.BLACK, 1);
        PDFPen redPen = new PDFPen(PDFRgbColor.RED, 1);
        PDFBrush blueBrush = new PDFBrush(PDFRgbColor.DARK_BLUE);
        PDFPageCanvas pageCanvas = page.getCanvas();

        PDFRgbColor randomPenColor = new PDFRgbColor();
        PDFPen randomPen = new PDFPen(randomPenColor, 1);

        pageCanvas.drawString("Bezier curves", titleFont, brush, 20, 50);

        pageCanvas.drawLine(blackPen, 20, 210, 600, 210);
        pageCanvas.drawLine(blackPen, 306, 70, 306, 350);
        pageCanvas.drawRectangle(blueBrush, 39, 339, 2, 2);
        pageCanvas.drawRectangle(blueBrush, 279, 79, 2, 2);
        pageCanvas.drawRectangle(blueBrush, 499, 299, 2, 2);
        pageCanvas.drawRectangle(blueBrush, 589, 69, 2, 2);
        pageCanvas.drawBezier(redPen, 40, 340, 280, 80, 500, 300, 590, 70);

        pageCanvas.drawString("Random bezier curves clipped to view", sectionFont, brush, 20, 385);
        PDFPath rectPath = new PDFPath();
        rectPath.addRectangle(20, 400, 570, 300);

        pageCanvas.saveGraphicsState();
        pageCanvas.setClip(rectPath);

        Random rnd = new Random();
        for (int i = 0; i < 100; i++)
        {
            randomPenColor.setR(rnd.nextInt(256));
            randomPenColor.setG(rnd.nextInt(256));
            randomPenColor.setB(rnd.nextInt(256));

            double x1 = rnd.nextDouble() * page.getWidth();
            double y1 = 380 + rnd.nextDouble() * 350;
            double x2 = rnd.nextDouble() * page.getWidth();
            double y2 = 380 + rnd.nextDouble() * 350;
            double x3 = rnd.nextDouble() * page.getWidth();
            double y3 = 380 + rnd.nextDouble() * 350;
            double x4 = rnd.nextDouble() * page.getWidth();
            double y4 = 380 + rnd.nextDouble() * 350;

            pageCanvas.drawBezier(randomPen, x1, y1, x2, y2, x3, y3, x4, y4);
        }

        pageCanvas.restoreGraphicsState();

        blackPen.setDashPattern(null);
        pageCanvas.drawPath(blackPen, rectPath);

        pageCanvas.compressAndClose();
    }

    private static void drawAffineTransformations(PDFPage page, PDFFont titleFont, PDFFont sectionFont)
    {
        PDFBrush brush = new PDFBrush();
        PDFPen blackPen = new PDFPen(PDFRgbColor.BLACK, 1);
        PDFPen redPen = new PDFPen(PDFRgbColor.RED, 1);
        PDFPen bluePen = new PDFPen(PDFRgbColor.BLUE, 1);
        PDFPen greenPen = new PDFPen(PDFRgbColor.GREEN, 1);
        PDFPageCanvas pageCanvas = page.getCanvas();

        pageCanvas.drawString("Affine transformations", titleFont, brush, 20, 50);

        pageCanvas.drawLine(blackPen, 0, page.getHeight() / 2, page.getWidth(), page.getHeight() / 2);
        pageCanvas.drawLine(blackPen, page.getWidth() / 2, 0, page.getWidth() / 2, page.getHeight());

        pageCanvas.saveGraphicsState();

        // Move the coordinate system in the center of the page.
        pageCanvas.translateTransform(page.getWidth() / 2, page.getHeight() / 2);

        // Draw a rectangle with the center at (0, 0)
        pageCanvas.drawRectangle(redPen, -page.getWidth() / 4, -page.getHeight() / 8, page.getWidth() / 2, page.getHeight() / 4);

        // Rotate the coordinate system with 30 degrees.
        pageCanvas.rotateTransform(30);

        // Draw the same rectangle with the center at (0, 0)
        pageCanvas.drawRectangle(greenPen, -page.getWidth() / 4, -page.getHeight() / 8, page.getWidth() / 2, page.getHeight() / 4);

        // Scale the coordinate system with 1.5
        pageCanvas.scaleTransform(1.5, 1.5);

        // Draw the same rectangle with the center at (0, 0)
        pageCanvas.drawRectangle(bluePen, -page.getWidth() / 4, -page.getHeight() / 8, page.getWidth() / 2, page.getHeight() / 4);

        pageCanvas.restoreGraphicsState();

        pageCanvas.compressAndClose();
    }

    private static void drawColorsAndColorSpaces(PDFPage page, PDFFont titleFont, PDFFont sectionFont, byte[] iccProfile)
    {
        PDFBrush brush = new PDFBrush();
        PDFPageCanvas pageCanvas = page.getCanvas();

        pageCanvas.drawString("Colors and colorspaces", titleFont, brush, 20, 50);

        pageCanvas.drawString("DeviceRGB", sectionFont, brush, 20, 70);
        PDFPen rgbPen = new PDFPen(PDFRgbColor.DARK_RED, 4);
        PDFBrush rgbBrush = new PDFBrush(PDFRgbColor.LIGHT_GOLDEN_ROD_YELLOW);
        pageCanvas.drawRectangle(rgbPen, rgbBrush, 20, 85, 250, 100);

        pageCanvas.drawString("DeviceCMYK", sectionFont, brush, 340, 70);
        PDFPen cmykPen = new PDFPen(new PDFCmykColor(1, 0.5, 0, 0.1), 4);
        PDFBrush cmykBrush = new PDFBrush(new PDFCmykColor(0, 0.5, 0.43, 0));
        pageCanvas.drawRectangle(cmykPen, cmykBrush, 340, 85, 250, 100);

        pageCanvas.drawString("DeviceGray", sectionFont, brush, 20, 200);
        PDFPen grayPen = new PDFPen(new PDFGrayColor(0.1), 4);
        PDFBrush grayBrush = new PDFBrush(new PDFGrayColor(0.75));
        pageCanvas.drawRectangle(grayPen, grayBrush, 20, 215, 250, 100);

        pageCanvas.drawString("Indexed", sectionFont, brush, 340, 200);
        PDFIndexedColorSpace indexedColorSpace = new PDFIndexedColorSpace();
        indexedColorSpace.setColorCount(2);
        indexedColorSpace.setBaseColorSpace(new PDFRgbColorSpace());
        indexedColorSpace.setColorTable(new byte[] { (byte)192, 0, 0, 0, 0, (byte)128 });
        PDFIndexedColor indexedColor0 = new PDFIndexedColor(indexedColorSpace);
        indexedColor0.setColorIndex(0);
        PDFIndexedColor indexedColor1 = new PDFIndexedColor(indexedColorSpace);
        indexedColor1.setColorIndex(1);
        PDFPen indexedPen = new PDFPen(indexedColor0, 4);
        PDFBrush indexedBrush = new PDFBrush(indexedColor1);
        pageCanvas.drawRectangle(indexedPen, indexedBrush, 340, 215, 250, 100);

        pageCanvas.drawString("CalGray", sectionFont, brush, 20, 330);
        PDFCalGrayColorSpace calGrayColorSpace = new PDFCalGrayColorSpace();
        PDFCalGrayColor calGrayColor1 = new PDFCalGrayColor(calGrayColorSpace);
        calGrayColor1.setGray(0.6);
        PDFCalGrayColor calGrayColor2 = new PDFCalGrayColor(calGrayColorSpace);
        calGrayColor2.setGray(0.2);
        PDFPen calGrayPen = new PDFPen(calGrayColor1, 4);
        PDFBrush calGrayBrush = new PDFBrush(calGrayColor2);
        pageCanvas.drawRectangle(calGrayPen, calGrayBrush, 20, 345, 250, 100);

        pageCanvas.drawString("CalRGB", sectionFont, brush, 340, 330);
        PDFCalRgbColorSpace calRgbColorSpace = new PDFCalRgbColorSpace();
        PDFCalRgbColor calRgbColor1 = new PDFCalRgbColor(calRgbColorSpace);
        calRgbColor1.setRed(0.1);
        calRgbColor1.setGreen(0.5);
        calRgbColor1.setBlue(0.25);
        PDFCalRgbColor calRgbColor2 = new PDFCalRgbColor(calRgbColorSpace);
        calRgbColor2.setRed(0.6);
        calRgbColor2.setGreen(0.1);
        calRgbColor2.setBlue(0.9);
        PDFPen calRgbPen = new PDFPen(calRgbColor1, 4);
        PDFBrush calRgbBrush = new PDFBrush(calRgbColor2);
        pageCanvas.drawRectangle(calRgbPen, calRgbBrush, 340, 345, 250, 100);

        pageCanvas.drawString("L*a*b", sectionFont, brush, 20, 460);
        PDFLabColorSpace labColorSpace = new PDFLabColorSpace();
        PDFLabColor labColor1 = new PDFLabColor(labColorSpace);
        labColor1.setL(90);
        labColor1.setA(-40);
        labColor1.setB(120);
        PDFLabColor labColor2 = new PDFLabColor(labColorSpace);
        labColor2.setL(45);
        labColor2.setA(90);
        labColor2.setB(-34);
        PDFPen labPen = new PDFPen(labColor1, 4);
        PDFBrush labBrush = new PDFBrush(labColor2);
        pageCanvas.drawRectangle(labPen, labBrush, 20, 475, 250, 100);

        pageCanvas.drawString("Icc", sectionFont, brush, 340, 460);
        PDFIccColorSpace iccColorSpace = new PDFIccColorSpace();
        iccColorSpace.setIccProfile(iccProfile);
        iccColorSpace.setAlternateColorSpace(new PDFRgbColorSpace());
        iccColorSpace.setColorComponents(3);
        PDFIccColor iccColor1 = new PDFIccColor(iccColorSpace);
        iccColor1.setColorComponents(new double[] { 0.45, 0.1, 0.22 });
        PDFIccColor iccColor2 = new PDFIccColor(iccColorSpace);
        iccColor2.setColorComponents(new double[] { 0.21, 0.76, 0.31 });
        PDFPen iccPen = new PDFPen(iccColor1, 4);
        PDFBrush iccBrush = new PDFBrush(iccColor2);
        pageCanvas.drawRectangle(iccPen, iccBrush, 340, 475, 250, 100);

        pageCanvas.drawString("Separation", sectionFont, brush, 20, 590);
        PDFExponentialFunction tintTransform = new PDFExponentialFunction();
        tintTransform.setDomain(new double[] { 0, 1 });
        tintTransform.setRange(new double[] { 0, 1, 0, 1, 0, 1, 0, 1 });
        tintTransform.setExponent(1);
        tintTransform.setC0(new double[] { 0, 0, 0, 0 });
        tintTransform.setC1(new double[] { 1, 0.5, 0, 0.1 });

        PDFSeparationColorSpace separationColorSpace = new PDFSeparationColorSpace();
        separationColorSpace.setAlternateColorSpace(new PDFCmykColorSpace());
        separationColorSpace.setColorant("Custom Blue");
        separationColorSpace.setTintTransform(tintTransform);

        PDFSeparationColor separationColor1 = new PDFSeparationColor(separationColorSpace);
        separationColor1.setTint(0.23);
        PDFSeparationColor separationColor2 = new PDFSeparationColor(separationColorSpace);
        separationColor2.setTint(0.74);

        PDFPen separationPen = new PDFPen(separationColor1, 4);
        PDFBrush separationBrush = new PDFBrush(separationColor2);
        pageCanvas.drawRectangle(separationPen, separationBrush, 20, 605, 250, 100);

        pageCanvas.drawString("Pantone", sectionFont, brush, 340, 590);
        PDFPen pantonePen = new PDFPen(PDFPantoneColor.REFLEX_BLUE, 4);
        PDFBrush pantoneBrush = new PDFBrush(PDFPantoneColor.RHODAMINE_RED);
        pageCanvas.drawRectangle(pantonePen, pantoneBrush, 340, 605, 250, 100);

        pageCanvas.compressAndClose();
    }

    private static void drawShadings(PDFPage page, PDFFont titleFont, PDFFont sectionFont)
    {
        PDFBrush brush = new PDFBrush();
        PDFPen blackPen = new PDFPen(PDFRgbColor.BLACK, 1);
        PDFPageCanvas pageCanvas = page.getCanvas();

        PDFRgbColor randomPenColor = new PDFRgbColor();
        PDFPen randomPen = new PDFPen(randomPenColor, 1);
        PDFRgbColor randomBrushColor = new PDFRgbColor();
        PDFBrush randomBrush = new PDFBrush(randomBrushColor);

        pageCanvas.drawString("Shadings", titleFont, brush, 20, 50);

        pageCanvas.drawString("Horizontal", sectionFont, brush, 25, 70);

        PDFAxialShading horizontalShading = new PDFAxialShading();
        horizontalShading.setStartColor(new PDFRgbColor(255, 0, 0));
        horizontalShading.setEndColor(new PDFRgbColor(0, 0, 255));
        horizontalShading.setStartPoint(new PDFPoint(25, 90));
        horizontalShading.setEndPoint(new PDFPoint(175, 90));

        // Clip the shading to desired area.
        PDFPath hsArea = new PDFPath();
        hsArea.addRectangle(25, 90, 150, 150);
        pageCanvas.saveGraphicsState();
        pageCanvas.setClip(hsArea);
        pageCanvas.drawShading(horizontalShading);
        pageCanvas.restoreGraphicsState();

        pageCanvas.drawString("Vertical", sectionFont, brush, 225, 70);

        PDFAxialShading verticalShading = new PDFAxialShading();
        verticalShading.setStartColor(new PDFRgbColor(255, 0, 0));
        verticalShading.setEndColor(new PDFRgbColor(0, 0, 255));
        verticalShading.setStartPoint(new PDFPoint(225, 90));
        verticalShading.setEndPoint(new PDFPoint(225, 240));

        // Clip the shading to desired area.
        PDFPath vsArea = new PDFPath();
        vsArea.addRectangle(225, 90, 150, 150);
        pageCanvas.saveGraphicsState();
        pageCanvas.setClip(vsArea);
        pageCanvas.drawShading(verticalShading);
        pageCanvas.restoreGraphicsState();

        pageCanvas.drawString("Diagonal", sectionFont, brush, 425, 70);

        PDFAxialShading diagonalShading = new PDFAxialShading();
        diagonalShading.setStartColor(new PDFRgbColor(255, 0, 0));
        diagonalShading.setEndColor(new PDFRgbColor(0, 0, 255));
        diagonalShading.setStartPoint(new PDFPoint(425, 90));
        diagonalShading.setEndPoint(new PDFPoint(575, 240));

        // Clip the shading to desired area.
        PDFPath dsArea = new PDFPath();
        dsArea.addRectangle(425, 90, 150, 150);
        pageCanvas.saveGraphicsState();
        pageCanvas.setClip(dsArea);
        pageCanvas.drawShading(diagonalShading);
        pageCanvas.restoreGraphicsState();

        pageCanvas.drawString("Extended shading", sectionFont, brush, 25, 260);

        PDFAxialShading extendedShading = new PDFAxialShading();
        extendedShading.setStartColor(new PDFRgbColor(255, 0, 0));
        extendedShading.setEndColor(new PDFRgbColor(0, 0, 255));
        extendedShading.setStartPoint(new PDFPoint(225, 280));
        extendedShading.setEndPoint(new PDFPoint(375, 280));
        extendedShading.setExtendStart(true);
        extendedShading.setExtendEnd(true);

        // Clip the shading to desired area.
        PDFPath esArea = new PDFPath();
        esArea.addRectangle(25, 280, 550, 30);
        pageCanvas.saveGraphicsState();
        pageCanvas.setClip(esArea);
        pageCanvas.drawShading(extendedShading);
        pageCanvas.restoreGraphicsState();
        pageCanvas.drawPath(blackPen, esArea);

        pageCanvas.drawString("Limited shading", sectionFont, brush, 25, 330);

        PDFAxialShading limitedShading = new PDFAxialShading();
        limitedShading.setStartColor(new PDFRgbColor(255, 0, 0));
        limitedShading.setEndColor(new PDFRgbColor(0, 0, 255));
        limitedShading.setStartPoint(new PDFPoint(225, 350));
        limitedShading.setEndPoint(new PDFPoint(375, 350));
        limitedShading.setExtendStart(false);
        limitedShading.setExtendEnd(false);

        // Clip the shading to desired area.
        PDFPath lsArea = new PDFPath();
        lsArea.addRectangle(25, 350, 550, 30);
        pageCanvas.saveGraphicsState();
        pageCanvas.setClip(lsArea);
        pageCanvas.drawShading(limitedShading);
        pageCanvas.restoreGraphicsState();
        pageCanvas.drawPath(blackPen, lsArea);

        pageCanvas.drawString("Multi-stop shading", sectionFont, brush, 25, 400);
        // Multi-stop shadings use a stitching function to combine the functions that define each gradient part.
        // Function for red to blue shading.
        PDFExponentialFunction redToBlueFunc = new PDFExponentialFunction();
        // Linear function
        redToBlueFunc.setExponent(1);
        redToBlueFunc.setDomain(new double[] { 0, 1 });
        // Red color for start
        redToBlueFunc.setC0(new double[] { 1, 0, 0 });
        // Blue color for start
        redToBlueFunc.setC1(new double[] { 0, 0, 1 });
        // Function for blue to green shading.
        PDFExponentialFunction blueToGreenFunc = new PDFExponentialFunction();
        // Linear function
        blueToGreenFunc.setExponent(1);
        blueToGreenFunc.setDomain(new double[] { 0, 1 });
        // Blue color for start
        blueToGreenFunc.setC0(new double[] { 0, 0, 1 });
        // Green color for start
        blueToGreenFunc.setC1(new double[] { 0, 1, 0 });

        //Stitching function for the shading.
        PDFStitchingFunction shadingFunction = new PDFStitchingFunction();
        shadingFunction.getFunctions().add(redToBlueFunc);
        shadingFunction.getFunctions().add(blueToGreenFunc);
        shadingFunction.setDomain(new double[] { 0, 1 });
        shadingFunction.setEncode(new double[] { 0, 1, 0, 1 });

        // Entire shading goes from 0 to 1 (100%).
        // We set the first shading (red->blue) to cover 30% (0 - 0.3) and
        // the second shading to cover 70% (0.3 - 1).
        shadingFunction.setBounds(new double[] { 0.3 });
        // The multistop shading
        PDFAxialShading multiStopShading = new PDFAxialShading();
        multiStopShading.setStartPoint(new PDFPoint(25, 420));
        multiStopShading.setEndPoint(new PDFPoint(575, 420));
        // The colorspace must match the colors specified in C0 & C1
        multiStopShading.setColorSpace(new PDFRgbColorSpace());
        multiStopShading.setFunction(shadingFunction);

        // Clip the shading to desired area.
        PDFPath mssArea = new PDFPath();
        mssArea.addRectangle(25, 420, 550, 30);
        pageCanvas.saveGraphicsState();
        pageCanvas.setClip(mssArea);
        pageCanvas.drawShading(multiStopShading);
        pageCanvas.restoreGraphicsState();
        pageCanvas.drawPath(blackPen, lsArea);

        pageCanvas.drawString("Radial shading", sectionFont, brush, 25, 470);

        PDFRadialShading rs1 = new PDFRadialShading();
        rs1.setStartColor(new PDFRgbColor(0, 255, 0));
        rs1.setEndColor(new PDFRgbColor(255, 0, 255));
        rs1.setStartCircleCenter(new PDFPoint(50, 500));
        rs1.setStartCircleRadius(10);
        rs1.setEndCircleCenter(new PDFPoint(500, 570));
        rs1.setEndCircleRadius(100);

        pageCanvas.drawShading(rs1);

        PDFRadialShading rs2 = new PDFRadialShading();
        rs2.setStartColor(new PDFRgbColor(0, 255, 0));
        rs2.setEndColor(new PDFRgbColor(255, 0, 255));
        rs2.setStartCircleCenter(new PDFPoint(80, 600));
        rs2.setStartCircleRadius(10);
        rs2.setEndCircleCenter(new PDFPoint(110, 690));
        rs2.setEndCircleRadius(100);

        pageCanvas.drawShading(rs2);

        pageCanvas.compressAndClose();
    }

    private static void drawPatterns(PDFPage page, PDFFont titleFont, PDFFont sectionFont)
    {
        PDFBrush brush = new PDFBrush();
        PDFPen blackPen = new PDFPen(PDFRgbColor.BLACK, 1);
        PDFPageCanvas pageCanvas = page.getCanvas();

        PDFPen darkRedPen = new PDFPen(new PDFRgbColor(0xFF, 0x40, 0x40), 0.8);
        PDFPen darkOrangePen = new PDFPen(new PDFRgbColor(0xA6, 0x4B, 0x00), 0.8);
        PDFPen darkCyanPen = new PDFPen(new PDFRgbColor(0x00, 0x63, 0x63), 0.8);
        PDFPen darkGreenPen = new PDFPen(new PDFRgbColor(0x00, 0x85, 0x00), 0.8);
        PDFBrush lightRedBrush = new PDFBrush(new PDFRgbColor(0xFF, 0x73, 0x73));
        PDFBrush lightOrangeBrush = new PDFBrush(new PDFRgbColor(0xFF, 0x96, 0x40));
        PDFBrush lightCyanBrush = new PDFBrush(new PDFRgbColor(0x33, 0xCC, 0xCC));
        PDFBrush lightGreenBrush = new PDFBrush(new PDFRgbColor(0x67, 0xE6, 0x67));

        pageCanvas.drawString("Patterns", titleFont, brush, 20, 50);

        pageCanvas.drawString("Colored patterns", sectionFont, brush, 25, 70);

        // Create the pattern visual appearance.
        PDFColoredTilingPattern ctp = new PDFColoredTilingPattern(20, 20);
        // Red circle
        ctp.getCanvas().drawEllipse(darkRedPen, lightRedBrush, 1, 1, 8, 8);
        // Cyan square
        ctp.getCanvas().drawRectangle(darkCyanPen, lightCyanBrush, 11, 1, 8, 8);
        // Green diamond
        PDFPath diamondPath = new PDFPath();
        diamondPath.startSubpath(1, 15);
        diamondPath.addPolyLineTo(new PDFPoint[] { new PDFPoint(5, 11), new PDFPoint(9, 15), new PDFPoint(5, 19) });
        diamondPath.closeSubpath();
        ctp.getCanvas().drawPath(darkGreenPen, lightGreenBrush, diamondPath);
        // Orange triangle
        PDFPath trianglePath = new PDFPath();
        trianglePath.startSubpath(11, 19);
        trianglePath.addPolyLineTo(new PDFPoint[] { new PDFPoint(15, 11), new PDFPoint(19, 19) });
        trianglePath.closeSubpath();
        ctp.getCanvas().drawPath(darkOrangePen, lightOrangeBrush, trianglePath);

        // Create a pattern colorspace from the pattern object.
        PDFPatternColorSpace coloredPatternColorSpace = new PDFPatternColorSpace(ctp);
        // Create a color based on the pattern colorspace.
        PDFPatternColor coloredPatternColor = new PDFPatternColor(coloredPatternColorSpace);
        // The pen and brush use the pattern color like any other color.
        PDFPatternBrush patternBrush = new PDFPatternBrush(coloredPatternColor);
        PDFPatternPen patternPen = new PDFPatternPen(coloredPatternColor, 40);

        pageCanvas.drawEllipse(patternBrush, 25, 90, 250, 200);
        pageCanvas.drawRoundRectangle(patternPen, 310, 110, 250, 160, 100, 100);

        pageCanvas.drawString("Uncolored patterns", sectionFont, brush, 25, 300);

        // Create the pattern visual appearance.
        PDFUncoloredTilingPattern uctp = new PDFUncoloredTilingPattern(20, 20);
        // A pen without color is used to create the pattern content.
        PDFPen noColorPen = new PDFPen(null, 0.8);
        // Circle
        uctp.getCanvas().drawEllipse(noColorPen, 1, 1, 8, 8);
        // Square
        uctp.getCanvas().drawRectangle(noColorPen, 11, 1, 8, 8);
        // Diamond
        diamondPath = new PDFPath();
        diamondPath.startSubpath(1, 15);
        diamondPath.addPolyLineTo(new PDFPoint[] { new PDFPoint(5, 11), new PDFPoint(9, 15), new PDFPoint(5, 19) });
        diamondPath.closeSubpath();
        uctp.getCanvas().drawPath(noColorPen, diamondPath);
        // Triangle
        trianglePath = new PDFPath();
        trianglePath.startSubpath(11, 19);
        trianglePath.addPolyLineTo(new PDFPoint[] { new PDFPoint(15, 11), new PDFPoint(19, 19) });
        trianglePath.closeSubpath();
        uctp.getCanvas().drawPath(noColorPen, trianglePath);

        // Create a pattern colorspace from the pattern object.
        PDFPatternColorSpace uncoloredPatternColorSpace = new PDFPatternColorSpace(uctp);
        // Create a color based on the pattern colorspace.
        PDFPatternColor uncoloredPatternColor = new PDFPatternColor(uncoloredPatternColorSpace);
        // The pen and brush use the pattern color like any other color.
        patternBrush = new PDFPatternBrush(uncoloredPatternColor);

        // Before using the uncolored pattern set the color that will be used to paint the pattern.
        patternBrush.setUncoloredPatternPaintColor(new PDFRgbColor(0xFF, 0x40, 0x40));
        pageCanvas.drawEllipse(patternBrush, 25, 320, 125, 200);
        patternBrush.setUncoloredPatternPaintColor(new PDFRgbColor(0xA6, 0x4B, 0x00));
        pageCanvas.drawEllipse(patternBrush, 175, 320, 125, 200);
        patternBrush.setUncoloredPatternPaintColor(new PDFRgbColor(0x00, 0x63, 0x63));
        pageCanvas.drawEllipse(patternBrush, 325, 320, 125, 200);
        patternBrush.setUncoloredPatternPaintColor(new PDFRgbColor(0x00, 0x85, 0x00));
        pageCanvas.drawEllipse(patternBrush, 475, 320, 125, 200);

        pageCanvas.drawString("Shading patterns", sectionFont, brush, 25, 550);

        // Create the pattern visual appearance.
        PDFAxialShading horizontalShading = new PDFAxialShading();
        horizontalShading.setStartColor(new PDFRgbColor(255, 0, 0));
        horizontalShading.setEndColor(new PDFRgbColor(0, 0, 255));
        horizontalShading.setStartPoint(new PDFPoint(25, 600));
        horizontalShading.setEndPoint(new PDFPoint(575, 600));
        PDFShadingPattern sp = new PDFShadingPattern(horizontalShading);

        // Create a pattern colorspace from the pattern object.
        PDFPatternColorSpace shadingPatternColorSpace = new PDFPatternColorSpace(sp);
        // Create a color based on the pattern colorspace.
        PDFPatternColor shadingPatternColor = new PDFPatternColor(shadingPatternColorSpace);
        // The pen and brush use the pattern color like any other color.
        patternPen = new PDFPatternPen(shadingPatternColor, 40);

        pageCanvas.drawEllipse(patternPen, 50, 600, 500, 150);

        pageCanvas.compressAndClose();
    }

    private static void drawFormXObjects(PDFPage page, PDFFont titleFont, PDFFont sectionFont)
    {
        PDFBrush brush = new PDFBrush();
        PDFPen blackPen = new PDFPen(PDFRgbColor.BLACK, 1);
        PDFPageCanvas pageCanvas = page.getCanvas();

        PDFRgbColor randomPenColor = new PDFRgbColor();
        PDFPen randomPen = new PDFPen(randomPenColor, 1);
        PDFRgbColor randomBrushColor = new PDFRgbColor();
        PDFBrush randomBrush = new PDFBrush(randomBrushColor);

        pageCanvas.drawString("Form XObjects", titleFont, brush, 20, 50);
        pageCanvas.drawString("Scaling", sectionFont, brush, 20, 70);

        // Create the XObject content - random rectangles
        PDFFormXObject xobject = new PDFFormXObject(300, 300);
        Random rnd = new Random();
        for (int i = 0; i < 100; i++)
        {
            randomPenColor.setR(rnd.nextInt(256));
            randomPenColor.setG(rnd.nextInt(256));
            randomPenColor.setB(rnd.nextInt(256));

            randomBrushColor.setR(rnd.nextInt(256));
            randomBrushColor.setG(rnd.nextInt(256));
            randomBrushColor.setB(rnd.nextInt(256));

            double left = rnd.nextDouble() * xobject.getWidth();
            double top = rnd.nextDouble() * xobject.getHeight();
            double width = rnd.nextDouble() * xobject.getWidth();
            double height = rnd.nextDouble() * xobject.getHeight();
            double orientation = rnd.nextInt(360);
            xobject.getCanvas().drawRectangle(randomPen, randomBrush, left, top, width, height, orientation);
        }

        xobject.getCanvas().drawRectangle(blackPen, 0, 0, xobject.getWidth(), xobject.getHeight());
        xobject.getCanvas().compressAndClose();

        // Draw the form XObject 3 times on the page at different sizes.
        pageCanvas.drawFormXObject(xobject, 3, 90, 100, 100);
        pageCanvas.drawFormXObject(xobject, 106, 90, 200, 200);
        pageCanvas.drawFormXObject(xobject, 309, 90, 300, 300);

        pageCanvas.drawString("Flipping", sectionFont, brush, 20, 420);
        pageCanvas.drawFormXObject(xobject, 20, 440, 150, 150);
        pageCanvas.drawFormXObject(xobject, 200, 440, 150, 150, 0, PDFFlipDirection.VERTICAL_FLIP);
        pageCanvas.drawFormXObject(xobject, 20, 620, 150, 150, 0, PDFFlipDirection.HORIZONTAL_FLIP);
        pageCanvas.drawFormXObject(xobject, 200, 620, 150, 150, 0, PDFFlipDirection.HORIZONTAL_AND_VERTICAL_FLIP);

        pageCanvas.compressAndClose();
    }

}
