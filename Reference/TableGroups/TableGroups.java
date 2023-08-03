import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.flowdocument.*;
import com.o2sol.pdf4java.graphics.PDFBrush;
import com.o2sol.pdf4java.graphics.PDFGraphicAlign;
import com.o2sol.pdf4java.graphics.PDFPen;
import com.o2sol.pdf4java.graphics.colors.PDFRgbColor;
import com.o2sol.pdf4java.graphics.fonts.truetype.PDFAnsiTrueTypeFont;

import java.io.IOException;
import java.io.RandomAccessFile;

public class TableGroups {
    public static void main(String[] args) {
        try {
            PDFAnsiTrueTypeFont verdana = new PDFAnsiTrueTypeFont("..\\..\\SupportFiles\\verdana.ttf", 1, true);
            PDFAnsiTrueTypeFont verdanaBold = new PDFAnsiTrueTypeFont("..\\..\\SupportFiles\\verdanab.ttf", 1, true);

            PDFFlowDocument document = new PDFFlowDocument();

            PDFFlowContent header = buildHeader(verdanaBold);
            document.addContent(header);

            PDFFlowContent attendantsSection = buildCountriesList(verdana, verdanaBold);
            document.addContent(attendantsSection);

            document.save("TableGroups.pdf");

            System.out.println("PDF document has been saved with success");
        }
        catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }

    private static PDFFlowContent buildHeader(PDFAnsiTrueTypeFont verdanaBold)
    {
        PDFAnsiTrueTypeFont headerFont = new PDFAnsiTrueTypeFont(verdanaBold);
        headerFont.setSize(16);

        PDFFlowTableContent headerTable = new PDFFlowTableContent(1);
        PDFFlowTableRow row = headerTable.addRowWithCells(new Object[] { "Continents, countries and populations" });

        ((PDFFlowTableStringCell)row.getCell(0)).setFont(headerFont);
        row.getCell(0).setHorizontalAlign(PDFGraphicAlign.CENTER);
        row.setMinHeight(40);

        return headerTable;
    }

    private static PDFFlowContent buildCountriesList(PDFAnsiTrueTypeFont verdana, PDFAnsiTrueTypeFont verdanaBold)
    {
        PDFAnsiTrueTypeFont textFont = new PDFAnsiTrueTypeFont(verdana);
        textFont.setSize(10);

        PDFAnsiTrueTypeFont headerFont = new PDFAnsiTrueTypeFont(verdanaBold);
        headerFont.setSize(12);

        PDFFlowTableContent countriesTable = new PDFFlowTableContent(2);
        countriesTable.setBorder(new PDFPen(PDFRgbColor.BLACK, 0.5));
        countriesTable.setMinRowHeight(16);
        ((PDFFlowTableStringCell)countriesTable.getDefaultCell()).setFont(textFont);
        countriesTable.getColumn(0).setVerticalAlign(PDFGraphicAlign.CENTER);
        countriesTable.getColumn(1).setVerticalAlign(PDFGraphicAlign.CENTER);
        countriesTable.getColumn(1).setHorizontalAlign(PDFGraphicAlign.FAR);

        PDFFlowTableRow headerRow = countriesTable.getHeaderRows().addRowWithCells(new Object[] { "Country", "Population" });
        headerRow.setBackground(new PDFBrush(PDFRgbColor.LIGHT_GRAY));
        headerRow.getCell(0).setHorizontalAlign(PDFGraphicAlign.CENTER);
        ((PDFFlowTableStringCell)headerRow.getCell(0)).setFont(headerFont);
        headerRow.getCell(1).setHorizontalAlign(PDFGraphicAlign.CENTER);
        ((PDFFlowTableStringCell)headerRow.getCell(1)).setFont(headerFont);

        String continent = "";
        long total = 0;
        PDFFlowTableRow row = null;

        try (RandomAccessFile file = new RandomAccessFile("..\\..\\SupportFiles\\population.dat", "r")) {
            String line;

            while ((line = file.readLine()) != null) {
                String[] items = line.split("\\|");

                long pop = Long.parseLong(items[2]);
                total = total + pop;

                if (!continent.equals(items[0])) {
                    // Add group footer
                    if (!continent.equals("")) {
                        row = countriesTable.addRowWithCells(new Object[]{String.format("Total population for %s: %d", continent, total)});
                        row.getCell(0).setColSpan(2);
                        row.getCell(0).setHorizontalAlign(PDFGraphicAlign.FAR);
                        ((PDFFlowTableStringCell)row.getCell(0)).setFont(headerFont);
                    }
                    continent = items[0];
                    total = 0;

                    // Add group header
                    row = countriesTable.addRowWithCells(new Object[]{ continent });
                    row.getCell(0).setColSpan(2);
                    row.setBackground(new PDFBrush(PDFRgbColor.LIGHT_GRAY));
                    ((PDFFlowTableStringCell)row.getCell(0)).setFont(headerFont);
                }
                row = countriesTable.addRowWithCells(new Object[]{ items[1], String.format("%d", pop) });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        row = countriesTable.addRowWithCells(new Object[]{String.format("Total population for %s: %d", continent, total)});
        row.getCell(0).setColSpan(2);
        row.getCell(0).setHorizontalAlign(PDFGraphicAlign.FAR);
        ((PDFFlowTableStringCell)row.getCell(0)).setFont(headerFont);

        return countriesTable;
    }

}
