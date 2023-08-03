import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.document.*;
import com.o2sol.pdf4java.graphics.*;
import com.o2sol.pdf4java.graphics.colors.PDFIccColorSpace;
import com.o2sol.pdf4java.graphics.colors.PDFRgbColor;
import com.o2sol.pdf4java.graphics.fonts.truetype.PDFAnsiTrueTypeFont;
import com.o2sol.pdf4java.graphics.optionalcontent.PDFOptionalContentDisplayTreeNode;
import com.o2sol.pdf4java.graphics.optionalcontent.PDFOptionalContentGroup;
import com.o2sol.pdf4java.graphics.optionalcontent.PDFOptionalContentProperties;
import com.o2sol.pdf4java.standards.PDFAFormat;
import com.o2sol.pdf4java.standards.PDFAFormatter;

import java.io.IOException;
import java.io.RandomAccessFile;

public class PDFA {
    public static void main(String[] args) throws IOException {
        try {
            RandomAccessFile iccFile = new RandomAccessFile("..\\..\\SupportFiles\\rgb.icc", "rwd");
            byte[] iccProfile = new byte[(int)iccFile.length()];
            iccFile.read(iccProfile, 0, iccProfile.length);
            iccFile.close();

            PDFFixedDocument pdfa1bDocument = createPDFA1bDocument(iccProfile);
            PDFAFormatter.save(pdfa1bDocument, "PDFA1B.pdf", PDFAFormat.PDF_A_1_B);

            PDFFixedDocument pdfa2uDocument = createPDFA2uDocument(iccProfile);
            PDFAFormatter.save(pdfa2uDocument, "PDFA2U.pdf", PDFAFormat.PDF_A_2_U);

            PDFFixedDocument pdfa3uDocument = createPDFA3uDocument(iccProfile);
            PDFAFormatter.save(pdfa3uDocument, "PDFA3U.pdf", PDFAFormat.PDF_A_3_U);

            System.out.println("PDF documents have been saved with success");
        } catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }


    private static PDFFixedDocument createPDFA1bDocument(byte[] iccProfile)
    {
        PDFFixedDocument document = new PDFFixedDocument();

        // Setup the document by creating a PDF/A output intent, based on a RGB ICC profile,
        // and document information and metadata

        PDFIccColorSpace icc = new PDFIccColorSpace();
        icc.setIccProfile(iccProfile);
        PDFOutputIntent oi = new PDFOutputIntent();
        oi.setType(PDFOutputIntentType.PDF_A_1);
        oi.setInfo("sRGB IEC61966-2.1");
        oi.setOutputConditionIdentifier("Custom");
        oi.setDestinationOutputProfile(icc);
        document.setOutputIntents(new PDFOutputIntentCollection());
        document.getOutputIntents().add(oi);

        PDFDocumentInformation docInfo = new PDFDocumentInformation();
        document.setDocumentInformation(docInfo);
        docInfo.setAuthor("O2 Solutions");
        docInfo.setTitle("PDF4Java PDF/A-1B Demo");
        docInfo.setCreator("PDF4Java PDF/A-1B Demo Application");
        docInfo.setProducer("PDF4Java");
        docInfo.setKeywords("pdf/a");
        docInfo.setSubject("PDF/A-1B Sample produced by PDF4Java");
        document.setXmpMetadata(new PDFXmpMetadata());

        PDFPage page = document.addPage();
        page.setRotation(90);

        // All fonts must be embedded in a PDF/A document.
        PDFStringAppearanceOptions sao = new PDFStringAppearanceOptions();
        sao.setFont(new PDFAnsiTrueTypeFont("..\\..\\SupportFiles\\verdana.ttf", 24, true));
        sao.setBrush(new PDFBrush(new PDFRgbColor(0, 0, 128)));

        PDFStringLayoutOptions slo = new PDFStringLayoutOptions();
        slo.setHorizontalAlign(PDFStringHorizontalAlign.CENTER);
        slo.setVerticalAlign(PDFStringVerticalAlign.BOTTOM);
        slo.setX(page.getWidth() / 2);
        slo.setY(page.getHeight() / 2 - 10);
        page.getCanvas().drawString("PDF4Java", sao, slo);
        slo.setY(page.getHeight() / 2 + 10);
        slo.setVerticalAlign(PDFStringVerticalAlign.TOP);
        sao.getFont().setSize(16);
        page.getCanvas().drawString("This is a sample PDF/A-1B document that shows the PDF/A-1B capabilities in PDF4Java library", sao, slo);

        return document;
    }

    private static PDFFixedDocument createPDFA2uDocument(byte[] iccProfile)
    {
        PDFFixedDocument document = new PDFFixedDocument();
        document.setOptionalContentProperties(new PDFOptionalContentProperties());

        // Setup the document by creating a PDF/A output intent, based on a RGB ICC profile,
        // and document information and metadata
        PDFIccColorSpace icc = new PDFIccColorSpace();
        icc.setIccProfile(iccProfile);
        PDFOutputIntent oi = new PDFOutputIntent();
        oi.setType(PDFOutputIntentType.PDF_A_1);
        oi.setInfo("sRGB IEC61966-2.1");
        oi.setOutputConditionIdentifier("Custom");
        oi.setDestinationOutputProfile(icc);
        document.setOutputIntents(new PDFOutputIntentCollection());
        document.getOutputIntents().add(oi);

        PDFDocumentInformation docInfo = new PDFDocumentInformation();
        document.setDocumentInformation(docInfo);
        docInfo.setAuthor("O2 Solutions");
        docInfo.setTitle("PDF4Java PDF/A-2B/U Demo");
        docInfo.setCreator("PDF4Java PDF/A-2B/U Demo Application");
        docInfo.setProducer("PDF4Java");
        docInfo.setKeywords("pdf/a");
        docInfo.setSubject("PDF/A-2B/U Sample produced by PDF4Java");
        document.setXmpMetadata(new PDFXmpMetadata());

        PDFPage page = document.addPage();
        page.setRotation(90);
        PDFPageCanvas pageCanvas = page.getCanvas();

        // All fonts must be embedded in a PDF/A document.
        PDFStringAppearanceOptions sao = new PDFStringAppearanceOptions();
        sao.setFont(new PDFAnsiTrueTypeFont("..\\..\\SupportFiles\\verdana.ttf", 24, true));
        sao.setBrush(new PDFBrush(new PDFRgbColor(0, 0, 128)));

        PDFStringLayoutOptions slo = new PDFStringLayoutOptions();
        slo.setHorizontalAlign(PDFStringHorizontalAlign.CENTER);
        slo.setVerticalAlign(PDFStringVerticalAlign.BOTTOM);
        slo.setX(page.getWidth() / 2);
        slo.setY(page.getHeight() / 2 - 10);
        pageCanvas.drawString("PDF4Java", sao, slo);
        slo.setY(page.getHeight() / 2 + 10);
        slo.setVerticalAlign(PDFStringVerticalAlign.TOP);
        sao.getFont().setSize(14);
        pageCanvas.drawString("This is a sample PDF/A-2B/U document that shows the PDF/A-2B/U capabilities in PDF4Java library", sao, slo);

        // PDF/A-2 documents support optional content and transparency.
        PDFOptionalContentGroup ocgPage1 = new PDFOptionalContentGroup();
        ocgPage1.setName("Green Rectangle");
        pageCanvas.beginOptionalContentGroup(ocgPage1);
        pageCanvas.saveGraphicsState();
        PDFExtendedGraphicState gs = new PDFExtendedGraphicState();
        gs.setFillAlpha(0.8);
        gs.setStrokeAlpha(0.2);
        gs.setBlendMode(PDFBlendMode.LUMINOSITY);
        pageCanvas.setExtendedGraphicState(gs);

        PDFBrush greenBrush = new PDFBrush(PDFRgbColor.DARK_GREEN);
        PDFPen bluePen = new PDFPen(PDFRgbColor.DARK_BLUE, 5);
        pageCanvas.drawRectangle(bluePen, greenBrush, 20, 20, page.getWidth() - 40, page.getHeight() - 40);

        pageCanvas.restoreGraphicsState();
        pageCanvas.endOptionalContentGroup();

        // Build the display tree for the optional content,
        // how its structure and relationships between optional content groups are presented to the user.
        PDFOptionalContentDisplayTreeNode ocgPage1Node = new PDFOptionalContentDisplayTreeNode(ocgPage1);
        document.getOptionalContentProperties().getDisplayTree().getNodes().add(ocgPage1Node);

        return document;
    }

    private static PDFFixedDocument createPDFA3uDocument(byte[] iccProfile)
    {
        PDFFixedDocument document = new PDFFixedDocument();
        document.setOptionalContentProperties(new PDFOptionalContentProperties());

        // Setup the document by creating a PDF/A output intent, based on a RGB ICC profile,
        // and document information and metadata
        PDFIccColorSpace icc = new PDFIccColorSpace();
        icc.setIccProfile(iccProfile);
        PDFOutputIntent oi = new PDFOutputIntent();
        oi.setType(PDFOutputIntentType.PDF_A_1);
        oi.setInfo("sRGB IEC61966-2.1");
        oi.setOutputConditionIdentifier("Custom");
        oi.setDestinationOutputProfile(icc);
        document.setOutputIntents(new PDFOutputIntentCollection());
        document.getOutputIntents().add(oi);

        PDFDocumentInformation docInfo = new PDFDocumentInformation();
        document.setDocumentInformation(docInfo);
        docInfo.setAuthor("O2 Solutions");
        docInfo.setTitle("PDF4Java PDF/A-3B/U Demo");
        docInfo.setCreator("PDF4Java PDF/A-3B/U Demo Application");
        docInfo.setProducer("PDF4Java");
        docInfo.setKeywords("pdf/a");
        docInfo.setSubject("PDF/A-3B/U Sample produced by PDF4Java");
        document.setXmpMetadata(new PDFXmpMetadata());

        PDFPage page = document.addPage();
        page.setRotation(90);
        PDFPageCanvas pageCanvas = page.getCanvas();

        // All fonts must be embedded in a PDF/A document.
        PDFStringAppearanceOptions sao = new PDFStringAppearanceOptions();
        sao.setFont(new PDFAnsiTrueTypeFont("..\\..\\SupportFiles\\verdana.ttf", 24, true));
        sao.setBrush(new PDFBrush(new PDFRgbColor(0, 0, 128)));

        PDFStringLayoutOptions slo = new PDFStringLayoutOptions();
        slo.setHorizontalAlign(PDFStringHorizontalAlign.CENTER);
        slo.setVerticalAlign(PDFStringVerticalAlign.BOTTOM);
        slo.setX(page.getWidth() / 2);
        slo.setY(page.getHeight() / 2 - 10);
        pageCanvas.drawString("PDF4Java", sao, slo);
        slo.setY(page.getHeight() / 2 + 10);
        slo.setVerticalAlign(PDFStringVerticalAlign.TOP);
        sao.getFont().setSize(14);
        pageCanvas.drawString("This is a sample PDF/A-3B/U document that shows the PDF/A-3B/U capabilities in PDF4Java library", sao, slo);

        // PDF/A-3 documents support optional content and transparency.
        PDFOptionalContentGroup ocgPage1 = new PDFOptionalContentGroup();
        ocgPage1.setName("Green Rectangle");
        pageCanvas.beginOptionalContentGroup(ocgPage1);
        pageCanvas.saveGraphicsState();
        PDFExtendedGraphicState gs = new PDFExtendedGraphicState();
        gs.setFillAlpha(0.8);
        gs.setStrokeAlpha(0.2);
        gs.setBlendMode(PDFBlendMode.LUMINOSITY);
        pageCanvas.setExtendedGraphicState(gs);

        PDFBrush greenBrush = new PDFBrush(PDFRgbColor.DARK_GREEN);
        PDFPen bluePen = new PDFPen(PDFRgbColor.DARK_BLUE, 5);
        pageCanvas.drawRectangle(bluePen, greenBrush, 20, 20, page.getWidth() - 40, page.getHeight() - 40);

        pageCanvas.restoreGraphicsState();
        pageCanvas.endOptionalContentGroup();

        // Build the display tree for the optional content,
        // how its structure and relationships between optional content groups are presented to the user.
        PDFOptionalContentDisplayTreeNode ocgPage1Node = new PDFOptionalContentDisplayTreeNode(ocgPage1);
        document.getOptionalContentProperties().getDisplayTree().getNodes().add(ocgPage1Node);

        // PDF/A-3 files also support document attachments.
        // Include the RGB ICC profile as an attachment.
        PDFDocumentFileAttachment att = new PDFDocumentFileAttachment(iccProfile);
        att.setFileName("rgb.icc");
        att.setDescription("RGB ICC profile");
        att.setMimeType("application/octet-stream");
        document.getFileAttachments().add(att);

        return document;
    }

}
