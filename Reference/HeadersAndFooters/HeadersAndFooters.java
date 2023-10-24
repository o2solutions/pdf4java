import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.flowdocument.*;
import com.o2sol.pdf4java.graphics.PDFPen;
import com.o2sol.pdf4java.graphics.PDFGraphicAlign;
import com.o2sol.pdf4java.graphics.PDFStringHorizontalAlign;
import com.o2sol.pdf4java.graphics.colors.PDFRgbColor;
import com.o2sol.pdf4java.graphics.fonts.truetype.PDFAnsiTrueTypeFont;
import com.o2sol.pdf4java.graphics.formattedcontent.*;

public class HeadersAndFooters implements PDFFlowDocumentCompletePageListener {
    
    private int currentPage;
    
    public static void main(String[] args) {
        try {
            HeadersAndFooters hf = new HeadersAndFooters();
            PDFFlowDocument document = hf.CreateDocument("..\\..\\SupportFiles\\verdana.ttf", "..\\..\\SupportFiles\\verdanab.ttf");

            document.save("HeadersAndFooters.pdf");

            System.out.println("PDF document has been saved with success");
        } catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }
    public PDFFlowDocument CreateDocument(String verdanaPath, String verdanaBoldPath)
    {
        PDFAnsiTrueTypeFont verdana = new PDFAnsiTrueTypeFont(verdanaPath, 10, true);
        PDFAnsiTrueTypeFont verdanaBold = new PDFAnsiTrueTypeFont(verdanaBoldPath, 10, true);

        PDFFlowDocumentDefaults documentDefaults = new PDFFlowDocumentDefaults();
        documentDefaults.getPageDefaults().getMargins().setTop(72);
        documentDefaults.getPageDefaults().getMargins().setBottom(72);
        PDFFlowDocument document = new PDFFlowDocument(documentDefaults);
        document.addCompletePageListener(this);

        CreateHeadersAndFooters(document, verdana);

        CreateIntroPage(document, verdana, verdanaBold);

        for (int i = 0; i < 5; i++)
        {
            CreateContentPage(document, verdana, i);
        }

        return document;
    }

    @Override
    public void completePage(PDFFlowDocument document, PDFFlowDocumentPageEvent pdfFlowPageEventArgs) {
        currentPage++;

        PDFFlowTableContent mainHeaderTable = (PDFFlowTableContent)document.getHeadersFooters().getMainHeader().getContent().get(0);
        ((PDFFlowTableStringCell)mainHeaderTable.getRow(0).getCell(0)).setContent(String.format("Page %d", currentPage));
        PDFFlowTableContent evenPagesHeaderTable = (PDFFlowTableContent)document.getHeadersFooters().getEvenPagesHeader().getContent().get(0);
        ((PDFFlowTableStringCell)evenPagesHeaderTable.getRow(0).getCell(0)).setContent(String.format("Page %d", currentPage));

        PDFFlowTableContent mainFooterTable = (PDFFlowTableContent)document.getHeadersFooters().getMainFooter().getContent().get(0);
        ((PDFFlowTableStringCell)mainFooterTable.getRow(0).getCell(0)).setContent(String.format("Page %d", currentPage));
        PDFFlowTableContent evenPagesFooterTable = (PDFFlowTableContent)document.getHeadersFooters().getEvenPagesFooter().getContent().get(0);
        ((PDFFlowTableStringCell)evenPagesFooterTable.getRow(0).getCell(0)).setContent(String.format("Page %d", currentPage));
    }

    private void CreateHeadersAndFooters(PDFFlowDocument document, PDFAnsiTrueTypeFont verdana)
    {
        PDFFlowDocumentHeader mainHeader = new PDFFlowDocumentHeader();
        mainHeader.setTopMargin(24);
        PDFFlowTableContent mainHeaderTable = new PDFFlowTableContent(1);
        mainHeaderTable.addRowWithCells(new Object[] { "Page" });
        mainHeaderTable.getRow(0).setMinHeight(15);
        PDFFlowTableStringCell mainHeaderTableCell = (PDFFlowTableStringCell)mainHeaderTable.getRow(0).getCell(0);
        mainHeaderTableCell.setFont(new PDFAnsiTrueTypeFont(verdana));
        mainHeaderTableCell.getFont().setSize(8);
        mainHeaderTableCell.setHorizontalAlign(PDFGraphicAlign.FAR);
        mainHeaderTableCell.setVerticalAlign(PDFGraphicAlign.CENTER);
        mainHeaderTableCell.setBorders(new PDFFlowContentBorders());
        mainHeaderTableCell.getBorders().setBottom(new PDFPen(PDFRgbColor.BLACK, 1));
        mainHeader.getContent().add(mainHeaderTable);
        document.getHeadersFooters().setMainHeader(mainHeader);

        PDFFlowDocumentHeader evenPagesHeader = new PDFFlowDocumentHeader();
        evenPagesHeader.setTopMargin(24);
        PDFFlowTableContent evenPagesHeaderTable = new PDFFlowTableContent(1);
        evenPagesHeaderTable.addRowWithCells(new Object[] { "Page" });
        evenPagesHeaderTable.getRow(0).setMinHeight(15);
        PDFFlowTableStringCell evenPagesHeaderTableCell = (PDFFlowTableStringCell)evenPagesHeaderTable.getRow(0).getCell(0);
        evenPagesHeaderTableCell.setFont(new PDFAnsiTrueTypeFont(verdana));
        evenPagesHeaderTableCell.getFont().setSize(8);
        evenPagesHeaderTableCell.setHorizontalAlign(PDFGraphicAlign.NEAR);
        evenPagesHeaderTableCell.setVerticalAlign(PDFGraphicAlign.CENTER);
        evenPagesHeaderTableCell.setBorders(new PDFFlowContentBorders());
        evenPagesHeaderTableCell.getBorders().setBottom(new PDFPen(PDFRgbColor.BLACK, 1));
        evenPagesHeader.getContent().add(evenPagesHeaderTable);
        document.getHeadersFooters().setEvenPagesHeader(evenPagesHeader);

        document.getHeadersFooters().setFirstPageHeader(null);

        PDFFlowDocumentFooter mainFooter = new PDFFlowDocumentFooter();
        mainFooter.setBottomMargin(24);
        PDFFlowTableContent mainFooterTable = new PDFFlowTableContent(1);
        mainFooterTable.addRowWithCells(new Object[] { "Page" });
        mainFooterTable.getRow(0).setMinHeight(15);
        PDFFlowTableStringCell mainFooterTableCell = (PDFFlowTableStringCell)mainFooterTable.getRow(0).getCell(0);
        mainFooterTableCell.setFont(new PDFAnsiTrueTypeFont(verdana));
        mainFooterTableCell.getFont().setSize(8);
        mainFooterTableCell.setHorizontalAlign(PDFGraphicAlign.FAR);
        mainFooterTableCell.setVerticalAlign(PDFGraphicAlign.CENTER);
        mainFooterTableCell.setBorders(new PDFFlowContentBorders());
        mainFooterTableCell.getBorders().setTop(new PDFPen(PDFRgbColor.BLACK, 1));
        mainFooter.getContent().add(mainFooterTable);
        document.getHeadersFooters().setMainFooter(mainFooter);

        PDFFlowDocumentFooter evenPagesFooter = new PDFFlowDocumentFooter();
        evenPagesFooter.setBottomMargin(24);
        PDFFlowTableContent evenPagesFooterTable = new PDFFlowTableContent(1);
        evenPagesFooterTable.addRowWithCells(new Object[] { "Page" });
        evenPagesFooterTable.getRow(0).setMinHeight(15);
        PDFFlowTableStringCell evenPagesFooterTableCell = (PDFFlowTableStringCell)evenPagesFooterTable.getRow(0).getCell(0);
        evenPagesFooterTableCell.setFont(new PDFAnsiTrueTypeFont(verdana));
        evenPagesFooterTableCell.getFont().setSize(8);
        evenPagesFooterTableCell.setHorizontalAlign(PDFGraphicAlign.NEAR);
        evenPagesFooterTableCell.setVerticalAlign(PDFGraphicAlign.CENTER);
        evenPagesFooterTableCell.setBorders(new PDFFlowContentBorders());
        evenPagesFooterTableCell.getBorders().setTop(new PDFPen(PDFRgbColor.BLACK, 1));
        evenPagesFooter.getContent().add(evenPagesFooterTable);
        document.getHeadersFooters().setEvenPagesFooter(evenPagesFooter);

        document.getHeadersFooters().setFirstPageFooter(null);
    }

    private void CreateIntroPage(PDFFlowDocument document, PDFAnsiTrueTypeFont verdana, PDFAnsiTrueTypeFont verdanaBold)
    {
        PDFFormattedParagraph titleParagraph = new PDFFormattedParagraph("DOCUMENT INTRO PAGE");
        titleParagraph.setSpacingBefore(300);
        ((PDFFormattedTextBlock)titleParagraph.getBlock(0)).setFont(new PDFAnsiTrueTypeFont(verdanaBold));
        ((PDFFormattedTextBlock)titleParagraph.getBlock(0)).getFont().setSize(36);
        titleParagraph.setHorizontalAlign(PDFStringHorizontalAlign.CENTER);
        PDFFormattedParagraph subtitleParagraph = new PDFFormattedParagraph("no headers and footers");
        ((PDFFormattedTextBlock)subtitleParagraph.getBlock(0)).setFont(new PDFAnsiTrueTypeFont(verdana));
        ((PDFFormattedTextBlock)subtitleParagraph.getBlock(0)).getFont().setSize(10);
        subtitleParagraph.setHorizontalAlign(PDFStringHorizontalAlign.CENTER);

        PDFFormattedContent fc = new PDFFormattedContent();
        fc.addParagraph(titleParagraph);
        fc.addParagraph(subtitleParagraph);

        document.addContent(new PDFFlowTextContent(fc));
    }

    private void CreateContentPage(PDFFlowDocument document, PDFAnsiTrueTypeFont verdana, int textIndex)
    {
        String[] text = new String[] {
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras commodo elementum odio, non venenatis risus efficitur a. " +
                        "Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce sit amet purus eget sem tristique faucibus. " +
                        "Nam ut felis vel ex ullamcorper pretium at quis nisl. Nam ac lacus tincidunt, vulputate sapien vel, tempus sem. Aliquam vel ligula dui. " +
                        "Vivamus porttitor nunc vitae mi interdum, vitae ullamcorper turpis bibendum. Vestibulum sagittis lorem ante, at tincidunt arcu suscipit eu. " +
                        "Morbi augue eros, tristique a consectetur ac, egestas nec turpis. Praesent non purus quis sem consequat tempor sed ac augue. Integer at mauris ac ipsum bibendum aliquam vitae id mi. " +
                        "Praesent efficitur tortor in ligula mattis scelerisque. Fusce in placerat augue. Mauris pretium, dui ac accumsan aliquet, justo sem posuere purus, " +
                        "sit amet tristique mi tortor malesuada lorem. Sed congue sem a neque tristique tristique et a odio. Curabitur quis aliquam turpis, tincidunt ullamcorper velit. " +
                        "Phasellus posuere, justo auctor convallis luctus, mi tortor interdum lorem, ac tempor nisi lorem in erat. Maecenas dapibus tristique lacus id egestas. " +
                        "Vivamus id risus vitae velit porta lacinia. Aliquam erat volutpat. Nulla facilisi. Donec tempor arcu eu rhoncus fringilla.",
                "Vivamus ullamcorper ligula sit amet interdum imperdiet. Nulla facilisi. Suspendisse et euismod elit. Quisque vitae magna nunc. " +
                        "Mauris condimentum at magna blandit semper. Pellentesque in lacus odio. Sed nec molestie lacus, eget scelerisque lorem. " +
                        "Etiam rutrum tellus at auctor vehicula. Mauris consequat, tortor vitae finibus efficitur, tellus arcu feugiat leo, in condimentum elit felis ut risus. " +
                        "Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Fusce tincidunt libero sem, ut hendrerit turpis dapibus ut. " +
                        "Donec lacinia luctus scelerisque. Sed vulputate egestas accumsan. Cras volutpat enim neque, malesuada varius lorem volutpat eget. Mauris lobortis risus ut leo malesuada, " +
                        "in volutpat felis finibus. Proin a gravida velit. Ut lorem urna, sollicitudin sit amet nibh at, vestibulum pharetra mauris. Maecenas metus mi, dapibus in erat at, " +
                        "congue tincidunt sapien. Vestibulum in pellentesque risus, id accumsan mauris. Donec non ex consequat, pretium ante at, suscipit lacus. Fusce ac consectetur erat. " +
                        "Pellentesque maximus justo quis ante ornare condimentum. ",
                "Proin accumsan orci a nulla gravida tincidunt. Nulla et nisl eget diam rhoncus euismod. Maecenas tellus eros, semper vitae pharetra a, tincidunt ut dolor. " +
                        "Nullam tempor at sapien vel efficitur. Duis vel aliquet felis, vitae tincidunt dolor. Sed tortor urna, dictum eu leo quis, feugiat eleifend ligula. Quisque vitae nisi venenatis, " +
                        "pretium augue id, consequat velit. Sed dignissim justo velit, id faucibus leo scelerisque sed. In vestibulum blandit ipsum et rhoncus. Aliquam erat lorem, interdum vitae ligula at, " +
                        "vulputate feugiat nunc. Fusce condimentum quis ligula ac dictum. Aliquam et viverra purus. Duis sollicitudin dolor eget diam pretium tempus. Nullam in magna eu tortor facilisis placerat non " +
                        "vitae eros. Ut vitae magna dictum felis lacinia aliquam facilisis nec sem. ",
                "Suspendisse potenti. Vivamus maximus mi consequat lectus tincidunt consectetur. Proin vulputate velit lectus, eu lobortis quam lobortis congue. Sed gravida magna non " +
                        "eleifend malesuada. Donec tincidunt lorem et semper dignissim. Fusce ut ex vestibulum urna lobortis aliquet in lobortis ex. Integer vehicula erat sed quam dictum varius id sed magna. " +
                        "Cras maximus lacus est, ut elementum neque faucibus et. Praesent malesuada egestas scelerisque. Donec interdum ex maximus, auctor nisl non, ornare enim. " +
                        "Nulla finibus quis felis non iaculis. Maecenas fringilla placerat enim non pellentesque. Proin justo orci, elementum ut porttitor in, scelerisque vel nisl. " +
                        "Nam sit amet pellentesque justo, et molestie dolor. Cras ipsum justo, facilisis eget diam non, lacinia iaculis libero. Curabitur convallis, velit nec finibus mattis, " +
                        "tellus erat elementum ligula, quis viverra quam sapien et enim. Morbi tempor fringilla mattis. ",
                "Aenean porttitor, augue pretium semper tincidunt, justo orci volutpat odio, malesuada convallis lacus lorem at nisi. Nulla at dolor tincidunt, tempor orci et, blandit metus. " +
                        "Pellentesque malesuada augue et odio interdum, sit amet laoreet odio sagittis. Maecenas porttitor consectetur eros nec tempor. Morbi ut pharetra nunc. Phasellus non massa congue, varius tortor nec, " +
                        "maximus massa. Cras erat mauris, pulvinar eu nibh ac, scelerisque maximus sem. Nam sed fringilla dolor, finibus tincidunt purus. Sed in dui ut enim interdum sagittis. Cras neque quam, " +
                        "ultricies euismod dignissim sit amet, elementum eget eros. Donec in sem vel nunc vulputate pharetra.Fusce rhoncus turpis id turpis aliquet pharetra. Vivamus tristique eros lectus, eget venenatis nulla dictum et. " +
                        "Vestibulum volutpat mi eu consequat blandit. Quisque ornare pellentesque tellus, in congue turpis viverra vel. Donec quis velit non nulla aliquet maximus. Maecenas ultricies nisi dui, non dapibus libero tincidunt ut. " +
                        "Quisque vel interdum diam, ultricies aliquet nisi. Vivamus dui erat, tincidunt quis nibh et, aliquet ultricies erat. Nullam sit amet sodales nibh, a gravida diam. Fusce efficitur ultrices pellentesque. " +
                        "Pellentesque a suscipit justo. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nam lectus tellus, semper ut vehicula vitae, ullamcorper id sem. " +
                        "Cras quis ipsum et ligula rutrum dignissim at sit amet leo. Etiam ut tortor in velit feugiat commodo a ut nunc. Proin nec efficitur augue. Phasellus non erat non dolor bibendum viverra in ut nunc. " +
                        "Maecenas et sollicitudin nisi, in imperdiet mi. Sed luctus quam felis, a efficitur eros pellentesque ut. Integer suscipit dignissim quam sit amet feugiat. Morbi in odio quis ligula ultrices convallis. " +
                        "Duis a dui tristique, pulvinar tellus vel, tristique ex. Sed metus velit, ornare sit amet felis at, finibus gravida ante. Nunc felis massa, viverra eget pellentesque ac, sollicitudin non odio. " +
                        "Aliquam dictum nulla mauris, finibus venenatis dolor suscipit at. Donec sagittis consequat diam, non varius dolor ullamcorper et. Proin dictum magna eget massa posuere tincidunt. " +
                        "Vivamus ut libero vel diam maximus posuere sagittis maximus mauris. Donec luctus, diam in porttitor interdum, nibh elit ultricies sem, eu porta nulla sapien id nisi. Mauris odio odio, " +
                        "mattis quis enim et, laoreet consequat ligula. Praesent feugiat lacus sagittis, laoreet massa sit amet, luctus ipsum. Suspendisse id malesuada velit. Etiam sit amet risus diam. " +
                        "Vestibulum non ligula vitae nunc bibendum ornare id ut ante. Morbi at orci mollis, commodo dui et, bibendum augue. Curabitur nibh arcu, vulputate eu sollicitudin et, egestas sed dui. " +
                        "Mauris ante enim, cursus et scelerisque eu, vestibulum et neque. Sed gravida ultricies ante sit amet efficitur. Cras est augue, auctor vel commodo sit amet, consectetur at quam. " +
                        "Phasellus vulputate convallis neque, vitae bibendum mi rutrum in. Pellentesque pharetra ultricies urna, vitae semper nulla congue non. Praesent venenatis ullamcorper risus, non luctus purus. " +
                        "Integer rutrum magna id pulvinar viverra. Aliquam et scelerisque turpis. Quisque sed nisl eu orci congue tempus nec in tellus. Nam eget magna lacus. Nunc eu sapien in velit ultrices tincidunt. " +
                        "Curabitur purus libero, viverra non mollis non, hendrerit sit amet tellus. Maecenas congue ut lectus et gravida. Nam commodo lacus at leo sollicitudin gravida. " +
                        "Nullam felis ante, dapibus a laoreet a, sagittis id dolor. Sed eget risus id eros faucibus aliquet. Sed rhoncus nibh quam, at congue massa convallis ac. Nullam lobortis ex mauris, " +
                        "nec viverra lacus fringilla sed.Pellentesque at sapien quis lectus ultrices fringilla a eu dolor. Pellentesque imperdiet ipsum a odio laoreet vehicula. Aenean vestibulum in dolor non suscipit. " +
                        "Curabitur sed felis non mi malesuada mattis quis quis est. Maecenas finibus dolor et libero semper, et posuere sem interdum. Aenean posuere eleifend sapien, ac bibendum lorem egestas id. " +
                        "Integer vehicula, quam in laoreet feugiat, neque elit scelerisque dui, eget facilisis dui turpis vel sapien. "
        };

        PDFFormattedContent fc = new PDFFormattedContent(text[textIndex]);
        ((PDFFormattedTextBlock)fc.getParagraph(0).getBlock(0)).setFont(new PDFAnsiTrueTypeFont(verdana));
        ((PDFFormattedTextBlock)fc.getParagraph(0).getBlock(0)).getFont().setSize(10);

        document.startNewPage();
        document.addContent(new PDFFlowTextContent(fc));
    }
}
