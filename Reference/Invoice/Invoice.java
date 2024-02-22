import com.o2sol.pdf4java.actions.PDFUriAction;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.flowdocument.*;
import com.o2sol.pdf4java.graphics.PDFBrush;
import com.o2sol.pdf4java.graphics.PDFGraphicAlign;
import com.o2sol.pdf4java.graphics.PDFStringHorizontalAlign;
import com.o2sol.pdf4java.graphics.colors.PDFRgbColor;
import com.o2sol.pdf4java.graphics.fonts.truetype.PDFAnsiTrueTypeFont;
import com.o2sol.pdf4java.graphics.formattedcontent.PDFFormattedContent;
import com.o2sol.pdf4java.graphics.formattedcontent.PDFFormattedTextBlock;
import com.o2sol.pdf4java.graphics.images.PDFImage;
import com.o2sol.pdf4java.graphics.images.PDFPngImage;

public class Invoice {
    public static void main(String[] args) {
        try {
            PDFAnsiTrueTypeFont verdana = new PDFAnsiTrueTypeFont("..\\..\\SupportFiles\\verdana.ttf", 10, true);
            PDFAnsiTrueTypeFont verdanaBold = new PDFAnsiTrueTypeFont("..\\..\\SupportFiles\\verdanab.ttf", 10, true);
            PDFPngImage logoImage = new PDFPngImage("..\\..\\SupportFiles\\logo.png");

            PDFFlowDocument document = new PDFFlowDocument();

            PDFFlowContent header = buildHeader(verdana, logoImage);
            document.addContent(header);

            PDFFlowContent sellerSection = buildSellerSection(verdana, verdanaBold);
            document.addContent(sellerSection);

            PDFFlowContent invoiceInfoSection = buildInvoiceInfoSection(verdana, verdanaBold);
            document.addContent(invoiceInfoSection);

            PDFFlowContent buyerSection = buildBuyerSection(verdana, verdanaBold);
            document.addContent(buyerSection);

            PDFFlowContent invoiceItemsSection = buildInvoiceItemsSection(verdana, verdanaBold);
            document.addContent(invoiceItemsSection);

            PDFFlowContent endSection = buildEndSection(verdana);
            document.addContent(endSection);

            document.save("Invoice.pdf");

            System.out.println("PDF document has been saved with success");
        } catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }

    private static PDFFlowContent buildHeader(PDFAnsiTrueTypeFont verdana, PDFImage logoImage)
    {
        PDFFlowTableContent headerTable = new PDFFlowTableContent(2);
        PDFFlowTableRow row = headerTable.addRowWithCells(new Object[] { "Invoice", logoImage });

        PDFAnsiTrueTypeFont headerFont = new PDFAnsiTrueTypeFont(verdana);
        headerFont.setSize(36);
        ((PDFFlowTableStringCell)row.getCell(0)).setFont(headerFont);
        row.getCell(0).setVerticalAlign(PDFGraphicAlign.CENTER);
        row.getCell(1).setHorizontalAlign(PDFGraphicAlign.FAR);
        ((PDFFlowTableImageCell)row.getCell(1)).setImageWidth(135);

        return headerTable;
    }

    private static PDFFlowContent buildSellerSection(PDFAnsiTrueTypeFont verdana, PDFAnsiTrueTypeFont verdanaBold)
    {
        PDFAnsiTrueTypeFont labelFont = new PDFAnsiTrueTypeFont(verdanaBold);
        labelFont.setSize(12);
        PDFAnsiTrueTypeFont contentFont = new PDFAnsiTrueTypeFont(verdana);
        contentFont.setSize(12);

        PDFFormattedContent sellerInfo = new PDFFormattedContent();
        sellerInfo.addParagraph(new PDFFormattedTextBlock[] {new PDFFormattedTextBlock("DemoCompany LLC", contentFont)});
        sellerInfo.addParagraph(new PDFFormattedTextBlock[] {new PDFFormattedTextBlock("3000 Alandala Road", contentFont)});
        sellerInfo.addParagraph(new PDFFormattedTextBlock[] {new PDFFormattedTextBlock("Beverly Hills", contentFont)});
        sellerInfo.addParagraph(new PDFFormattedTextBlock[] {new PDFFormattedTextBlock("CA 90210", contentFont)});
        sellerInfo.addParagraph(new PDFFormattedTextBlock[] {new PDFFormattedTextBlock("United States", contentFont)});
        sellerInfo.addParagraph(" ");

        PDFFormattedTextBlock labelPhone = new PDFFormattedTextBlock("Phone: ", labelFont);
        PDFFormattedTextBlock phone = new PDFFormattedTextBlock("+1-555-123-4567", contentFont);
        sellerInfo.addParagraph(new PDFFormattedTextBlock[] {labelPhone, phone});

        PDFFormattedTextBlock labelFax = new PDFFormattedTextBlock("Fax: ", labelFont);
        PDFFormattedTextBlock fax = new PDFFormattedTextBlock("+1-555-456-7890", contentFont);
        sellerInfo.addParagraph(new PDFFormattedTextBlock[] {labelFax, fax});

        PDFFormattedTextBlock labelEmail = new PDFFormattedTextBlock("Email: ", labelFont);
        PDFFormattedTextBlock email = new PDFFormattedTextBlock("support@o2sol.com", contentFont);
        email.setTextColor(new PDFBrush(PDFRgbColor.BLUE));
        email.setAction(new PDFUriAction("mailto:support@o2sol.com"));
        sellerInfo.addParagraph(new PDFFormattedTextBlock[] {labelEmail, email});

        PDFFormattedTextBlock labelWeb = new PDFFormattedTextBlock("Web: ", labelFont);
        PDFFormattedTextBlock web = new PDFFormattedTextBlock("www.o2sol.com", contentFont);
        web.setTextColor(new PDFBrush(PDFRgbColor.BLUE));
        web.setAction(new PDFUriAction("http://www.o2sol.com"));
        sellerInfo.addParagraph(new PDFFormattedTextBlock[] {labelWeb, web});

        for (int i = 0; i < sellerInfo.getParagraphs().size(); i++)
        {
            sellerInfo.getParagraphs().get(i).setHorizontalAlign(PDFStringHorizontalAlign.RIGHT);
        }
        PDFFlowTextContent sellerInfoText = new PDFFlowTextContent(sellerInfo);

        return sellerInfoText;
    }

    private static PDFFlowContent buildInvoiceInfoSection(PDFAnsiTrueTypeFont verdana, PDFAnsiTrueTypeFont verdanaBold)
    {
        PDFAnsiTrueTypeFont labelFont = new PDFAnsiTrueTypeFont(verdanaBold);
        labelFont.setSize(12);
        PDFAnsiTrueTypeFont contentFont = new PDFAnsiTrueTypeFont(verdana);
        contentFont.setSize(12);

        PDFFlowTableContent invoiceInfoTable = new PDFFlowTableContent(2);
        invoiceInfoTable.getColumn(0).setWidth(120);
        invoiceInfoTable.getColumn(0).setWidthRelativeToTable(false);
        ((PDFFlowTableStringCell)invoiceInfoTable.getDefaultCell()).setFont(contentFont);
        invoiceInfoTable.addRowWithCells(new Object[] {" ", " "});
        PDFFlowTableRow row = invoiceInfoTable.addRowWithCells(new Object[] {"Date", "15 March 2016"});
        ((PDFFlowTableStringCell)row.getCell(0)).setFont(labelFont);
        row = invoiceInfoTable.addRowWithCells(new Object[] {"Invoice number:", "1234567890"});
        ((PDFFlowTableStringCell)row.getCell(0)).setFont(labelFont);
        row = invoiceInfoTable.addRowWithCells(new Object[] {"Currency:", "USD (US Dollars)"});
        ((PDFFlowTableStringCell)row.getCell(0)).setFont(labelFont);

        return invoiceInfoTable;
    }

    private static PDFFlowContent buildBuyerSection(PDFAnsiTrueTypeFont verdana, PDFAnsiTrueTypeFont verdanaBold)
    {
        PDFAnsiTrueTypeFont headerFont = new PDFAnsiTrueTypeFont(verdana);
        headerFont.setSize(20);
        PDFAnsiTrueTypeFont labelFont = new PDFAnsiTrueTypeFont(verdanaBold);
        labelFont.setSize(12);
        PDFAnsiTrueTypeFont contentFont = new PDFAnsiTrueTypeFont(verdana);
        contentFont.setSize(12);

        PDFFormattedContent buyerInfo = new PDFFormattedContent();
        buyerInfo.addParagraph(new PDFFormattedTextBlock[] {new PDFFormattedTextBlock(" ", headerFont)});
        buyerInfo.addParagraph(new PDFFormattedTextBlock[] {new PDFFormattedTextBlock("Invoice to:", headerFont)});
        buyerInfo.addParagraph(new PDFFormattedTextBlock[] {new PDFFormattedTextBlock(" ", headerFont)});
        buyerInfo.addParagraph(new PDFFormattedTextBlock[] {new PDFFormattedTextBlock("Contoso LLC", contentFont)});
        buyerInfo.addParagraph(new PDFFormattedTextBlock[] {new PDFFormattedTextBlock("1000 Summer Road", contentFont)});
        buyerInfo.addParagraph(new PDFFormattedTextBlock[] {new PDFFormattedTextBlock("London", contentFont)});
        buyerInfo.addParagraph(new PDFFormattedTextBlock[] {new PDFFormattedTextBlock("1A2 3B4", contentFont)});
        buyerInfo.addParagraph(new PDFFormattedTextBlock[] {new PDFFormattedTextBlock("United Kingdom", contentFont)});
        buyerInfo.addParagraph(" ");

        PDFFormattedTextBlock labelVAT = new PDFFormattedTextBlock("Your VAT/Tax number: ", labelFont);
        PDFFormattedTextBlock vat = new PDFFormattedTextBlock("GB1234567890", contentFont);
        buyerInfo.addParagraph(new PDFFormattedTextBlock[] {labelVAT, vat});
        buyerInfo.addParagraph(new PDFFormattedTextBlock[] {new PDFFormattedTextBlock(" ", headerFont)});

        PDFFlowTextContent buyerInfoText = new PDFFlowTextContent(buyerInfo);

        return buyerInfoText;
    }

    private static PDFFlowContent buildInvoiceItemsSection(PDFAnsiTrueTypeFont verdana, PDFAnsiTrueTypeFont verdanaBold)
    {
        PDFAnsiTrueTypeFont labelFont = new PDFAnsiTrueTypeFont(verdanaBold);
        labelFont.setSize(12);
        PDFAnsiTrueTypeFont contentFont = new PDFAnsiTrueTypeFont(verdana);
        contentFont.setSize(12);

        PDFFlowTableContent invoiceInfoTable = new PDFFlowTableContent(5);
        invoiceInfoTable.setMinRowHeight(20);
        PDFFlowTableColumn column = invoiceInfoTable.getColumn(0); 
        column.setVerticalAlign(PDFGraphicAlign.CENTER);
        column.setWidth(250);
        column.setWidthRelativeToTable(false);
        column = invoiceInfoTable.getColumn(1);
        column.setHorizontalAlign(PDFGraphicAlign.FAR);
        column.setVerticalAlign(PDFGraphicAlign.CENTER);
        column.setWidth(50);
        column.setWidthRelativeToTable(false);
        column = invoiceInfoTable.getColumn(2);
        column.setVerticalAlign(PDFGraphicAlign.CENTER);
        column.setHorizontalAlign(PDFGraphicAlign.FAR);
        column.setWidth(80);
        column.setWidthRelativeToTable(false);
        column = invoiceInfoTable.getColumn(3);
        column.setHorizontalAlign(PDFGraphicAlign.FAR);
        column.setVerticalAlign(PDFGraphicAlign.CENTER);
        column.setWidth(80);
        column.setWidthRelativeToTable(false);
        column = invoiceInfoTable.getColumn(4);
        column.setHorizontalAlign(PDFGraphicAlign.FAR);
        column.setVerticalAlign(PDFGraphicAlign.CENTER);
        column.setWidth(80);
        column.setWidthRelativeToTable(false);
        ((PDFFlowTableStringCell)invoiceInfoTable.getDefaultCell()).setFont(contentFont);

        PDFFlowTableRow row = invoiceInfoTable.addRowWithCells(new Object[] {"Description", "Qty", "Price", "Total", "VAT/Tax"});
        for (int i = 0; i < row.getCells().size(); i++)
        {
            ((PDFFlowTableStringCell)row.getCell(i)).setFont(labelFont);
            ((PDFFlowTableStringCell)row.getCell(i)).setColor(new PDFBrush(PDFRgbColor.WHITE));
        }
        row.setBackground(new PDFBrush(PDFRgbColor.BLACK));
        row = invoiceInfoTable.addRowWithCells(new Object[] {"Sample green item", "1", "100.00", "100.00", "20.00"});
        row.setBackground(new PDFBrush(PDFRgbColor.LIGHT_GRAY));
        row = invoiceInfoTable.addRowWithCells(new Object[] {"Big pink box", "3", "250.00", "750.00", "150.00"});
        row = invoiceInfoTable.addRowWithCells(new Object[] {"Yellow glass bowl", "2", "200.00", "400.00", "80.00"});
        row.setBackground(new PDFBrush(PDFRgbColor.LIGHT_GRAY));
        row = invoiceInfoTable.addRowWithCells(new Object[] {"Total", "", "", "1250.00", "250.00"});
        row = invoiceInfoTable.addRowWithCells(new Object[] {"Total (including VAT/Tax)", "", "", "1500.00", ""});
        ((PDFFlowTableStringCell)row.getCell(0)).setFont(labelFont);
        ((PDFFlowTableStringCell)row.getCell(3)).setFont(labelFont);

        return invoiceInfoTable;
    }

    private static PDFFlowContent buildEndSection(PDFAnsiTrueTypeFont verdana)
    {
        PDFAnsiTrueTypeFont headerFont = new PDFAnsiTrueTypeFont(verdana);
        headerFont.setSize(20);
        PDFAnsiTrueTypeFont contentFont = new PDFAnsiTrueTypeFont(verdana);
        contentFont.setSize(12);

        PDFFormattedContent endInfo = new PDFFormattedContent();
        endInfo.addParagraph(new PDFFormattedTextBlock[] {new PDFFormattedTextBlock(" ", headerFont)});
        endInfo.addParagraph(new PDFFormattedTextBlock[] {new PDFFormattedTextBlock("PAID IN FULL by credit card.", headerFont)});
        endInfo.addParagraph(new PDFFormattedTextBlock[] {new PDFFormattedTextBlock(" ", headerFont)});

        PDFFormattedTextBlock text1 = new PDFFormattedTextBlock("If you have any queries regarding this Invoice, please contact ", contentFont);
        PDFFormattedTextBlock email = new PDFFormattedTextBlock("sales@o2sol.com", contentFont);
        PDFFormattedTextBlock text2 = new PDFFormattedTextBlock(" quoting the Invoice Number above.", contentFont);
        email.setTextColor(new PDFBrush(PDFRgbColor.BLUE));
        email.setAction(new PDFUriAction("mailto:sales@o2sol.com"));
        endInfo.addParagraph(new PDFFormattedTextBlock[] {text1, email, text2});

        PDFFlowTextContent endInfoText = new PDFFlowTextContent(endInfo);

        return endInfoText;
    }
}
