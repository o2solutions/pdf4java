import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.flowdocument.*;
import com.o2sol.pdf4java.graphics.PDFGraphicAlign;
import com.o2sol.pdf4java.graphics.PDFPen;
import com.o2sol.pdf4java.graphics.colors.PDFRgbColor;
import com.o2sol.pdf4java.graphics.fonts.truetype.PDFAnsiTrueTypeFont;

public class TableCellSpans {
    public static void main(String[] args) {
        try {
            PDFAnsiTrueTypeFont textFont = new PDFAnsiTrueTypeFont("..\\..\\SupportFiles\\verdana.ttf", 10, true);
            PDFAnsiTrueTypeFont headerFont = new PDFAnsiTrueTypeFont(textFont);
            headerFont.setSize(16);

            PDFFlowDocument document = new PDFFlowDocument();

            PDFFlowTableContent headerTable = new PDFFlowTableContent(1);
            PDFFlowTableRow row = headerTable.addRowWithCells(new Object[] { "Store sales by year" } );
            ((PDFFlowTableStringCell)row.getCell(0)).setFont(headerFont);
            row.getCell(0).setHorizontalAlign(PDFGraphicAlign.CENTER);
            row.setMinHeight(40);
            document.addContent(headerTable);

            PDFFlowTableContent itemsTable = new PDFFlowTableContent(4);
            ((PDFFlowTableStringCell)itemsTable.getDefaultCell()).setFont(textFont);
            itemsTable.setBorder(new PDFPen(PDFRgbColor.BLACK, 0.5));
            itemsTable.setMinRowHeight(15);
            itemsTable.getColumn(2).setVerticalAlign(PDFGraphicAlign.CENTER);
            itemsTable.getColumn(3).setVerticalAlign(PDFGraphicAlign.CENTER);
            itemsTable.getColumn(3).setHorizontalAlign(PDFGraphicAlign.FAR);

            row = itemsTable.addRowWithCells(new Object[] { "Tablets", "iPad Air 2", "2013", "213,554" } );
            row.getCell(0).setRowSpan(12);
            row.getCell(1).setRowSpan(3);
            itemsTable.addRowWithCells(new Object[] { "2014", "123,443" } );
            itemsTable.addRowWithCells(new Object[] { "2015", "64,443" } );
            row = itemsTable.addRowWithCells(new Object[] { "iPad Pro", "2013", "342,443" } );
            row.getCell(0).setRowSpan(3);
            itemsTable.addRowWithCells(new Object[] { "2014", "56,332" } );
            itemsTable.addRowWithCells(new Object[] { "2015", "765,231" } );
            row = itemsTable.addRowWithCells(new Object[] { "Nexus 7", "2013", "432,541" } );
            row.getCell(0).setRowSpan(3);
            itemsTable.addRowWithCells(new Object[] { "2014", "213,871" } );
            itemsTable.addRowWithCells(new Object[] { "2015", "112,332" } );
            row = itemsTable.addRowWithCells(new Object[] { "Nexus 9", "2013", "342,434" } );
            row.getCell(0).setRowSpan(3);
            itemsTable.addRowWithCells(new Object[] { "2014", "231,778" } );
            itemsTable.addRowWithCells(new Object[] { "2015", "119,324" } );

            row = itemsTable.addRowWithCells(new Object[] { "Smartphones", "Samsung Galaxy S5", "2013", "1,543,321" } );
            row.getCell(0).setRowSpan(12);
            row.getCell(1).setRowSpan(3);
            itemsTable.addRowWithCells(new Object[] { "2014", "1,435,875" } );
            itemsTable.addRowWithCells(new Object[] { "2015", "1,876,324" } );
            row = itemsTable.addRowWithCells(new Object[] { "Samsung Galaxy S6", "2013", "1,432,134" } );
            row.getCell(0).setRowSpan(3);
            itemsTable.addRowWithCells(new Object[] { "2014", "1,232,432" } );
            itemsTable.addRowWithCells(new Object[] { "2015", "1,765,112" } );
            row = itemsTable.addRowWithCells(new Object[] { "iPhone 6", "2013", "1,433,665" } );
            row.getCell(0).setRowSpan(3);
            itemsTable.addRowWithCells(new Object[] { "2014", "2,443,245" } );
            itemsTable.addRowWithCells(new Object[] { "2015", "1,656,334" } );
            row = itemsTable.addRowWithCells(new Object[] { "iPhone 6 Plus", "2013", "994,123" } );
            row.getCell(0).setRowSpan(3);
            itemsTable.addRowWithCells(new Object[] { "2014", "443,546" } );
            itemsTable.addRowWithCells(new Object[] { "2015", "765,342" } );

            document.addContent(itemsTable);

            document.save("TableCellSpans.pdf");

            System.out.println("PDF document has been saved with success");
        }
        catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
