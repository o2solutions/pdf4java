import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.flowdocument.*;
import com.o2sol.pdf4java.graphics.PDFBrush;
import com.o2sol.pdf4java.graphics.PDFGraphicAlign;
import com.o2sol.pdf4java.graphics.PDFPen;
import com.o2sol.pdf4java.graphics.colors.PDFRgbColor;
import com.o2sol.pdf4java.graphics.fonts.truetype.PDFAnsiTrueTypeFont;

import java.io.IOException;
import java.io.RandomAccessFile;

public class SimpleTable {
    public static void main(String[] args)  {
        try {
            PDFAnsiTrueTypeFont verdana = new PDFAnsiTrueTypeFont("..\\..\\SupportFiles\\verdana.ttf", 1, true);
            PDFAnsiTrueTypeFont verdanaBold = new PDFAnsiTrueTypeFont("..\\..\\SupportFiles\\verdanab.ttf", 1, true);

            PDFFlowDocumentDefaults fdd = new PDFFlowDocumentDefaults();
            fdd.getPageDefaults().getMargins().setLeft(18);
            fdd.getPageDefaults().getMargins().setRight(18);
            fdd.getPageDefaults().setRotation(90);

            PDFFlowDocument document = new PDFFlowDocument(fdd);

            PDFFlowContent header = buildHeader(verdanaBold);
            document.addContent(header);

            PDFFlowContent attendantsSection = buildAttendantsList(verdana, verdanaBold);
            document.addContent(attendantsSection);


            document.save("SimpleTable.pdf");

            System.out.println("PDF document has been saved with success");
        }
        catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }

    private static PDFFlowContent buildHeader(PDFAnsiTrueTypeFont verdanaBold) {
        PDFAnsiTrueTypeFont headerFont = new PDFAnsiTrueTypeFont(verdanaBold);
        headerFont.setSize(36);

        PDFFlowTableContent headerTable = new PDFFlowTableContent(1);
        PDFFlowTableRow row = headerTable.addRowWithCells(new Object[] { "Conference attendants" });

        ((PDFFlowTableStringCell)row.getCell(0)).setFont(headerFont);
        row.getCell(0).setHorizontalAlign(PDFGraphicAlign.CENTER);
        row.setMinHeight(40);

        return headerTable;
    }

    private static PDFFlowContent buildAttendantsList(PDFAnsiTrueTypeFont verdana, PDFAnsiTrueTypeFont verdanaBold) {
        PDFAnsiTrueTypeFont textFont = new PDFAnsiTrueTypeFont(verdana);
        textFont.setSize(10);

        PDFAnsiTrueTypeFont headerFont = new PDFAnsiTrueTypeFont(verdanaBold);
        headerFont.setSize(12);

        PDFFlowTableContent attendantsTable = new PDFFlowTableContent(5);
        attendantsTable.setBorder(new PDFPen(PDFRgbColor.BLACK, 0.5));
        attendantsTable.setMinRowHeight(15);
        ((PDFFlowTableStringCell)attendantsTable.getDefaultCell()).setFont(textFont);
        PDFFlowTableColumn column = attendantsTable.getColumn(0);
        column.setVerticalAlign(PDFGraphicAlign.CENTER);
        column.setWidth(120);
        column.setWidthIsRelativeToTable(false);
        column = attendantsTable.getColumn(1);
        column.setVerticalAlign(PDFGraphicAlign.CENTER);
        column.setWidth(210);
        column.setWidthIsRelativeToTable(false);
        column = attendantsTable.getColumn(2);
        column.setVerticalAlign(PDFGraphicAlign.CENTER);
        column.setWidth(100);
        column.setWidthIsRelativeToTable(false);
        column = attendantsTable.getColumn(3);
        column.setVerticalAlign(PDFGraphicAlign.CENTER);
        column.setWidth(190);
        column.setWidthIsRelativeToTable(false);
        column = attendantsTable.getColumn(4);
        column.setVerticalAlign(PDFGraphicAlign.CENTER);
        column.setWidth(130);
        column.setWidthIsRelativeToTable(false);

        PDFFlowTableRow row = attendantsTable.getHeaderRows().addRowWithCells(new Object[] { "Name", "Email", "Phone", "Company", "Country" });
        for (int i = 0; i < row.getCells().size(); i++) {
            PDFFlowTableCell cell = row.getCell(i);
            ((PDFFlowTableStringCell)cell).setFont(headerFont);
            ((PDFFlowTableStringCell)cell).setColor(new PDFBrush(PDFRgbColor.WHITE));
            cell.setHorizontalAlign(PDFGraphicAlign.CENTER);
        }
        row.setBackground(new PDFBrush(PDFRgbColor.BLACK));

        int counter = 0;
        PDFBrush alternateBackground = new PDFBrush(PDFRgbColor.LIGHT_GRAY);

        try (RandomAccessFile file = new RandomAccessFile("..\\..\\SupportFiles\\people1.dat", "r")) {
            String line;

            while ((line = file.readLine()) != null) {
                String[] items = line.split("\\|");

                row = attendantsTable.addRowWithCells(items);

                if (counter % 2 == 0) {
                    row.setBackground(alternateBackground);
                }
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return attendantsTable;
    }

}
