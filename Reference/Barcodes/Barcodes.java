import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.graphics.*;
import com.o2sol.pdf4java.graphics.barcodes.bidimensional.*;
import com.o2sol.pdf4java.graphics.barcodes.unidimensional.*;
import com.o2sol.pdf4java.graphics.colors.PDFRgbColor;
import com.o2sol.pdf4java.graphics.fonts.PDFFont;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFont;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFontFace;

public class Barcodes {
    public static void main(String[] args) {
        try {
            PDFFixedDocument document = new PDFFixedDocument();

            PDFStandardFont titleFont = new PDFStandardFont(PDFStandardFontFace.HELVETICABOLD, 16);
            PDFStandardFont barcodeFont = new PDFStandardFont(PDFStandardFontFace.HELVETICA, 12);

            PDFPage page = document.addPage();
            drawGenericBarcodes(page, titleFont, barcodeFont);

            page = document.addPage();
            drawPharmaBarcodes(page, titleFont, barcodeFont);

            page = document.addPage();
            drawEanUpcBarcodes(page, titleFont, barcodeFont);

            page = document.addPage();
            drawPostAndTransportantionBarcodes(page, titleFont, barcodeFont);

            page = document.addPage();
            draw2DBarcodes(page, titleFont, barcodeFont);

            document.save("Barcodes.pdf");

            System.out.println("PDF document has been saved with success");
        }
        catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }
    private static void drawGenericBarcodes(PDFPage page, PDFFont titleFont, PDFFont barcodeFont)
    {

        PDFBrush brush = new PDFBrush();
        PDFPen lightGrayPen = new PDFPen(PDFRgbColor.LIGHT_GRAY, 0.5);

        page.getCanvas().drawString("Generic barcodes", titleFont, brush, 40, 20);
        for (int i = 0; i < 7; i++)
        {
            page.getCanvas().drawLine(lightGrayPen, 40, 50 + 100 * i, 570, 50 + 100 * i);
        }
        page.getCanvas().drawLine(lightGrayPen, 306, 50, 306, 750);

        String[] barcodes = new String[] {
                "Codabar", "Code 11", "Code 25", "Code 25 Interleaved", "Code 39", "Code 39 Extended",
                "Code 93", "Code 93 Extended", "Code 128 A", "Code 128 B", "Code 128 C", "COOP 25", "Matrix 25", "MSI/Plessey"
        };
        PDFStringAppearanceOptions sao = new PDFStringAppearanceOptions();
        sao.setBrush(brush);
        sao.setFont(barcodeFont);
        PDFStringLayoutOptions slo = new PDFStringLayoutOptions();
        slo.setHorizontalAlign(PDFStringHorizontalAlign.CENTER);
        slo.setVerticalAlign(PDFStringVerticalAlign.TOP);

        slo.setX(173);
        int sign = 1;
        for (int i = 0; i < barcodes.length; i++)
        {
            slo.setY(55 + 100 * (i / 2));

            page.getCanvas().drawString(barcodes[i], sao, slo);

            slo.setX(slo.getX() + sign * 266);
            sign = -sign;
        }

        // Codabar
        PDFCodabarBarcode codabarBarcode = new PDFCodabarBarcode();
        codabarBarcode.setData("523408943724");
        codabarBarcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(codabarBarcode, 173 - codabarBarcode.getWidth() / 2, 70);

        // Code 11
        PDFCode11Barcode code11Barcode = new PDFCode11Barcode();
        code11Barcode.setData("42376524534");
        code11Barcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(code11Barcode, 173 + 266 - code11Barcode.getWidth() / 2, 70);

        // Code 25
        PDFCode25Barcode code25Barcode = new PDFCode25Barcode();
        code25Barcode.setData("857621354312");
        code25Barcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(code25Barcode, 173 - code25Barcode.getWidth() / 2, 170);

        // Code 25 Interleaved
        PDFCode25InterleavedBarcode code25InterleavedBarcode = new PDFCode25InterleavedBarcode();
        code25InterleavedBarcode.setData("42376524534");
        code25InterleavedBarcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(code25InterleavedBarcode, 173 + 266 - code25InterleavedBarcode.getWidth() / 2, 170);

        // Code 39
        PDFCode39Barcode code39Barcode = new PDFCode39Barcode();
        code39Barcode.setData("6430784327");
        code39Barcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(code39Barcode, 173 - code39Barcode.getWidth() / 2, 270);

        // Code 39 Extended
        PDFCode39ExtendedBarcode code39ExtendedBarcode = new PDFCode39ExtendedBarcode();
        code39ExtendedBarcode.setData("8990436322");
        code39ExtendedBarcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(code39ExtendedBarcode, 173 + 266 - code39ExtendedBarcode.getWidth() / 2, 270);

        // Code 93
        PDFCode93Barcode code93Barcode = new PDFCode93Barcode();
        code93Barcode.setData("6345212344");
        code93Barcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(code93Barcode, 173 - code93Barcode.getWidth() / 2, 370);

        // Code 39 Extended
        PDFCode93ExtendedBarcode code93ExtendedBarcode = new PDFCode93ExtendedBarcode();
        code93ExtendedBarcode.setData("125436732");
        code93ExtendedBarcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(code93ExtendedBarcode, 173 + 266 - code93ExtendedBarcode.getWidth() / 2, 370);

        // Code 128 A
        PDFCode128ABarcode code128ABarcode = new PDFCode128ABarcode();
        code128ABarcode.setData("PDF4NET");
        code128ABarcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(code128ABarcode, 173 - code128ABarcode.getWidth() / 2, 470);

        // Code 128 B
        PDFCode128BBarcode code128BBarcode = new PDFCode128BBarcode();
        code128BBarcode.setData("pdf4net");
        code128BBarcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(code128BBarcode, 173 + 266 - code128BBarcode.getWidth() / 2, 470);

        // Code 128 C
        PDFCode128CBarcode code128CBarcode = new PDFCode128CBarcode();
        code128CBarcode.setData("423409865432");
        code128CBarcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(code128CBarcode, 173 - code128CBarcode.getWidth() / 2, 570);

        // COOP 25
        PDFCoop25Barcode coop25Barcode = new PDFCoop25Barcode();
        coop25Barcode.setData("43256565543");
        coop25Barcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(coop25Barcode, 173 + 266 - coop25Barcode.getWidth() / 2, 570);

        // Matrix 25
        PDFMatrix25Barcode matrix25Barcode = new PDFMatrix25Barcode();
        matrix25Barcode.setData("500540024300");
        matrix25Barcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(matrix25Barcode, 173 - matrix25Barcode.getWidth() / 2, 670);

        // MSI/Plessey
        PDFMsiPlesseyBarcode msiPlesseyBarcode = new PDFMsiPlesseyBarcode();
        msiPlesseyBarcode.setData("1124332556");
        msiPlesseyBarcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(msiPlesseyBarcode, 173 + 266 - msiPlesseyBarcode.getWidth() / 2, 670);

        page.getCanvas().compressAndClose();
    }
    private static void drawPharmaBarcodes(PDFPage page, PDFFont titleFont, PDFFont barcodeFont)
    {
        PDFBrush brush = new PDFBrush();
        PDFPen lightGrayPen = new PDFPen(PDFRgbColor.LIGHT_GRAY, 0.5);

        page.getCanvas().drawString("Pharma barcodes (barcodes used in the pharmaceutical industry)", titleFont, brush, 40, 20);
        for (int i = 0; i < 2; i++)
        {
            page.getCanvas().drawLine(lightGrayPen, 40, 50 + 100 * i, 570, 50 + 100 * i);
        }
        page.getCanvas().drawLine(lightGrayPen, 306, 50, 306, 250);

        String[] barcodes = new String[] { "Code 32", "Pharmacode", "PZN (Pharma-Zentral-Nummer)" };
        PDFStringAppearanceOptions sao = new PDFStringAppearanceOptions();
        sao.setBrush(brush);
        sao.setFont(barcodeFont);
        PDFStringLayoutOptions slo = new PDFStringLayoutOptions();
        slo.setHorizontalAlign(PDFStringHorizontalAlign.CENTER);
        slo.setVerticalAlign(PDFStringVerticalAlign.TOP);

        slo.setX(173);
        int sign = 1;
        for (int i = 0; i < barcodes.length; i++)
        {
            slo.setY(55 + 100 * (i / 2));

            page.getCanvas().drawString(barcodes[i], sao, slo);

            slo.setX(slo.getX() + sign * 266);
            sign = -sign;
        }

        // Code 32
        PDFCode32Barcode code32Barcode = new PDFCode32Barcode();
        code32Barcode.setData("54925174");
        code32Barcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(code32Barcode, 173 - code32Barcode.getWidth() / 2, 70);

        // Pharmacode
        PDFPharmacodeBarcode pharmacodeBarcode = new PDFPharmacodeBarcode();
        pharmacodeBarcode.setData("128128");
        pharmacodeBarcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(pharmacodeBarcode, 173 + 266 - pharmacodeBarcode.getWidth() / 2, 70);

        // PZN 
        PDFPznBarcode pznBarcode = new PDFPznBarcode();
        pznBarcode.setData("903271");
        pznBarcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(pznBarcode, 173 - pznBarcode.getWidth() / 2, 170);

        page.getCanvas().compressAndClose();
    }

    private static void drawEanUpcBarcodes(PDFPage page, PDFFont titleFont, PDFFont barcodeFont)
    {

        PDFBrush brush = new PDFBrush();
        PDFPen lightGrayPen = new PDFPen(PDFRgbColor.LIGHT_GRAY, 0.5);

        page.getCanvas().drawString("EAN/UPC barcodes", titleFont, brush, 40, 20);
        for (int i = 0; i < 7; i++)
        {
            page.getCanvas().drawLine(lightGrayPen, 40, 50 + 100 * i, 570, 50 + 100 * i);
        }
        page.getCanvas().drawLine(lightGrayPen, 306, 50, 306, 750);

        String[] barcodes = new String[] { "EAN 128", "EAN-13", "EAN-8", "ISBN", "ISMN", "ISSN", "JAN-13", "UPC-A", "UPC-E" };
        PDFStringAppearanceOptions sao = new PDFStringAppearanceOptions();
        sao.setBrush(brush);
        sao.setFont(barcodeFont);
        PDFStringLayoutOptions slo = new PDFStringLayoutOptions();
        slo.setHorizontalAlign(PDFStringHorizontalAlign.CENTER);
        slo.setVerticalAlign(PDFStringVerticalAlign.TOP);

        slo.setX(173);
        int sign = 1;
        for (int i = 0; i < barcodes.length; i++)
        {
            slo.setY(55 + 100 * (i / 2));

            page.getCanvas().drawString(barcodes[i], sao, slo);

            slo.setX(slo.getX() + sign * 266);
            sign = -sign;
        }

        // EAN 128
        PDFEan128Barcode ean128Barcode = new PDFEan128Barcode();
        ean128Barcode.setData("WWW.O2SOL.COM");
        ean128Barcode.getQuietZones().setLeft(0);
        ean128Barcode.getQuietZones().setRight(0);
        ean128Barcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        ean128Barcode.setApplicationIdentifier("URL");
        page.getCanvas().drawBarcode(ean128Barcode, 173 - ean128Barcode.getWidth() / 2, 70);

        // EAN-13
        PDFEan13Barcode ean13Barcode = new PDFEan13Barcode();
        ean13Barcode.setData("437612735617");
        ean13Barcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(ean13Barcode, 173 + 266 - ean13Barcode.getWidth() / 2, 70);

        // EAN-8
        PDFEan8Barcode ean8Barcode = new PDFEan8Barcode();
        ean8Barcode.setData("5423731");
        ean8Barcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(ean8Barcode, 173 - ean8Barcode.getWidth() / 2, 170);

        // ISBN
        PDFIsbnBarcode isbnBarcode = new PDFIsbnBarcode();
        isbnBarcode.setData("436314378");
        isbnBarcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(isbnBarcode, 173 + 266 - isbnBarcode.getWidth() / 2, 170);

        // ISMN
        PDFIsmnBarcode ismnBarcode = new PDFIsmnBarcode();
        ismnBarcode.setData("437612489");
        ismnBarcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(ismnBarcode, 173 - ismnBarcode.getWidth() / 2, 270);

        // ISSN
        PDFIssnBarcode issnBarcode = new PDFIssnBarcode();
        issnBarcode.setData("546712341");
        issnBarcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(issnBarcode, 173 + 266 - issnBarcode.getWidth() / 2, 270);

        // JAN-13
        PDFJan13Barcode jan13Barcode = new PDFJan13Barcode();
        jan13Barcode.setData("1256127634");
        jan13Barcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(jan13Barcode, 173 - jan13Barcode.getWidth() / 2, 370);

        // UPC-A
        PDFUpcaBarcode upcaBarcode = new PDFUpcaBarcode();
        upcaBarcode.setData("12543267841");
        upcaBarcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(upcaBarcode, 173 + 266 - upcaBarcode.getWidth() / 2, 370);

        // UPC-E
        PDFUpceBarcode upceBarcode = new PDFUpceBarcode();
        upceBarcode.setData("1234532");
        upceBarcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(upceBarcode, 173 - upceBarcode.getWidth() / 2, 470);

        page.getCanvas().compressAndClose();
    }

    private static void drawPostAndTransportantionBarcodes(PDFPage page, PDFFont titleFont, PDFFont barcodeFont)
    {

        PDFBrush brush = new PDFBrush();
        PDFPen lightGrayPen = new PDFPen(PDFRgbColor.LIGHT_GRAY, 0.5);

        page.getCanvas().drawString("Post and transportation barcodes", titleFont, brush, 40, 20);
        for (int i = 0; i < 7; i++)
        {
            page.getCanvas().drawLine(lightGrayPen, 40, 50 + 100 * i, 570, 50 + 100 * i);
        }
        page.getCanvas().drawLine(lightGrayPen, 306, 50, 306, 750);

        String[] barcodes = new String[] {
                "FedEx Ground 96", "IATA 25", "Identcode", "Leitcode", "KIX", "Planet",
                "PostNet", "RM4SCC", "SCC-14", "SingaporePost", "SSCC-18", "USPS FIM", "USPS Horizontal", "USPS PIC"
        };
        PDFStringAppearanceOptions sao = new PDFStringAppearanceOptions();
        sao.setBrush(brush);
        sao.setFont(barcodeFont);
        PDFStringLayoutOptions slo = new PDFStringLayoutOptions();
        slo.setHorizontalAlign(PDFStringHorizontalAlign.CENTER);
        slo.setVerticalAlign(PDFStringVerticalAlign.TOP);

        slo.setX(173);
        int sign = 1;
        for (int i = 0; i < barcodes.length; i++)
        {
            slo.setY(55 + 100 * (i / 2));

            page.getCanvas().drawString(barcodes[i], sao, slo);

            slo.setX(slo.getX() + sign * 266);
            sign = -sign;
        }

        // FedEx Ground 96
        PDFFedExGround96Barcode fedexGround96Barcode = new PDFFedExGround96Barcode();
        fedexGround96Barcode.setData("962343237687543423123");
        fedexGround96Barcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(fedexGround96Barcode, 173 - fedexGround96Barcode.getWidth() / 2, 70);

        // IATA 25
        PDFIata25Barcode iata25Barcode = new PDFIata25Barcode();
        iata25Barcode.setData("54366436563");
        iata25Barcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(iata25Barcode, 173 + 266 - iata25Barcode.getWidth() / 2, 70);

        // Identcode
        PDFIdentcodeBarcode identcodeBarcode = new PDFIdentcodeBarcode();
        identcodeBarcode.setData("12435678214");
        identcodeBarcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(identcodeBarcode, 173 - identcodeBarcode.getWidth() / 2, 170);

        // Leitcode
        PDFLeitcodeBarcode leitcodeBarcode = new PDFLeitcodeBarcode();
        leitcodeBarcode.setData("1243657687321");
        leitcodeBarcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(leitcodeBarcode, 173 + 266 - leitcodeBarcode.getWidth() / 2, 170);

        // KIX
        PDFKixBarcode kixBarcode = new PDFKixBarcode();
        kixBarcode.setData("PDF4NET");
        kixBarcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(kixBarcode, 173 - kixBarcode.getWidth() / 2, 270);

        // Planet
        PDFPlanetBarcode planetBarcode = new PDFPlanetBarcode();
        planetBarcode.setData("645316643300");
        planetBarcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(planetBarcode, 173 + 266 - planetBarcode.getWidth() / 2, 270);

        // PostNet
        PDFPostNetBarcode postNetBarcode = new PDFPostNetBarcode();
        postNetBarcode.setData("04231454322");
        postNetBarcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(postNetBarcode, 173 - postNetBarcode.getWidth() / 2, 370);

        // RM4SCC
        PDFRm4sccBarcode rm4sccBarcode = new PDFRm4sccBarcode();
        rm4sccBarcode.setData("PDF4NET");
        rm4sccBarcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(rm4sccBarcode, 173 + 266 - rm4sccBarcode.getWidth() / 2, 370);

        // SCC-14
        PDFScc14Barcode scc14Barcode = new PDFScc14Barcode();
        scc14Barcode.setData("3255091205412");
        scc14Barcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(scc14Barcode, 173 - scc14Barcode.getWidth() / 2, 470);

        // Singapore Post
        PDFSingaporePostBarcode singaporePostBarcode = new PDFSingaporePostBarcode();
        singaporePostBarcode.setData("PDF4NET");
        singaporePostBarcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(singaporePostBarcode, 173 + 266 - singaporePostBarcode.getWidth() / 2, 470);

        // SSCC-18
        PDFSscc18Barcode sscc18Barcode = new PDFSscc18Barcode();
        sscc18Barcode.setData("09876543219832435");
        sscc18Barcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(sscc18Barcode, 173 - sscc18Barcode.getWidth() / 2, 570);

        // USPS FIM
        PDFUspsFimBarcode uspsFimBarcode = new PDFUspsFimBarcode();
        uspsFimBarcode.setData("A");
        uspsFimBarcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(uspsFimBarcode, 173 + 266 - uspsFimBarcode.getWidth() / 2, 570);

        // USPS Horizontal
        PDFUspsHorizontalBarcode uspsHorizontalBarcode = new PDFUspsHorizontalBarcode();
        uspsHorizontalBarcode.setData("1111");
        uspsHorizontalBarcode.setBarcodeTextPosition(PDFBarcodeTextPosition.NONE);
        page.getCanvas().drawBarcode(uspsHorizontalBarcode, 173 - uspsHorizontalBarcode.getWidth() / 2, 670);

        // USPS PIC
        PDFUspsPicBarcode uspsPicBarcode = new PDFUspsPicBarcode();
        uspsPicBarcode.setData("914354657901234354019");
        uspsPicBarcode.setBarcodeTextPosition(PDFBarcodeTextPosition.BOTTOM);
        page.getCanvas().drawBarcode(uspsPicBarcode, 173 + 266 - uspsPicBarcode.getWidth() / 2, 670);

        page.getCanvas().compressAndClose();
    }

    private static void draw2DBarcodes(PDFPage page, PDFFont titleFont, PDFFont barcodeFont)
    {
        PDFBrush brush = new PDFBrush();
        PDFPen lightGrayPen = new PDFPen(PDFRgbColor.LIGHT_GRAY, 0.5);

        page.getCanvas().drawString("2D barcodes", titleFont, brush, 40, 20);
        for (int i = 0; i < 3; i++)
        {
            page.getCanvas().drawLine(lightGrayPen, 40, 50 + 150 * i, 570, 50 + 150 * i);
        }
        page.getCanvas().drawLine(lightGrayPen, 306, 50, 306, 500);

        String[] barcodes = new String[] { "Codablock F", "Code 16K", "PDF417", "Micro PDF417", "DataMatrix", "QR" };
        PDFStringAppearanceOptions sao = new PDFStringAppearanceOptions();
        sao.setBrush(brush);
        sao.setFont(barcodeFont);
        PDFStringLayoutOptions slo = new PDFStringLayoutOptions();
        slo.setHorizontalAlign(PDFStringHorizontalAlign.CENTER);
        slo.setVerticalAlign(PDFStringVerticalAlign.TOP);

        slo.setX(173);
        int sign = 1;
        for (int i = 0; i < barcodes.length; i++)
        {
            slo.setY(55 + 150 * (i / 2));

            page.getCanvas().drawString(barcodes[i], sao, slo);

            slo.setX(slo.getX() + sign * 266);
            sign = -sign;
        }

        // Codablock F
        PDFCodablockFBarcode codablockFBarcode = new PDFCodablockFBarcode();
        codablockFBarcode.setData("*** com.o2sol.pdf4java ***");
        codablockFBarcode.setColumns(10);
        codablockFBarcode.setRows(5);
        page.getCanvas().drawBarcode(codablockFBarcode, 173 - codablockFBarcode.getWidth() / 2, 70);

        // Code 16K
        PDFCode16KBarcode code16KBarcode = new PDFCode16KBarcode();
        code16KBarcode.setData("*** com.o2sol.pdf4java ***");
        code16KBarcode.setRows(7);
        page.getCanvas().drawBarcode(code16KBarcode, 173 + 266 - code16KBarcode.getWidth() / 2, 70);

        // PDF 417
        PDF417StandardBarcode pdf417Barcode = new PDF417StandardBarcode();
        pdf417Barcode.setData("*** com.o2sol.pdf4java ***");
        pdf417Barcode.setColumns(10);
        pdf417Barcode.setRows(0);
        page.getCanvas().drawBarcode(pdf417Barcode, 173 - pdf417Barcode.getWidth() / 2, 220);

        // MicroPDF 417
        PDF417MicroBarcode microPDF417Barcode = new PDF417MicroBarcode();
        microPDF417Barcode.setData("* com.o2sol.pdf4java *");
        microPDF417Barcode.setBarcodeSize(PDF417MicroBarcodeSize.ROWS8COLUMNS4);
        page.getCanvas().drawBarcode(microPDF417Barcode, 173 + 266 - microPDF417Barcode.getWidth() / 2, 220);

        // DataMatrix
        PDFDataMatrixBarcode datamatrixBarcode = new PDFDataMatrixBarcode();
        datamatrixBarcode.setData("*** com.o2sol.pdf4java ***");
        datamatrixBarcode.setXDimension(2);
        datamatrixBarcode.setBarcodeSize(PDFDataMatrixBarcodeSize.AUTO);
        page.getCanvas().drawBarcode(datamatrixBarcode, 173 - datamatrixBarcode.getWidth() / 2, 370);

        // QR Barcode
        PDFQrBarcode qrBarcode = new PDFQrBarcode();
        qrBarcode.setXDimension(2);
        qrBarcode.setData("PDF4Java: http://www.o2sol.com/");
        qrBarcode.setCharacterSet(PDFQrBarcodeCharacterSet.ISO88591);
        page.getCanvas().drawBarcode(qrBarcode, 173 + 266 - qrBarcode.getWidth() / 2, 370);

        page.getCanvas().compressAndClose();
    }

}
