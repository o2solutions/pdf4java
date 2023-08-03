import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.flowdocument.PDFFlowDocument;
import com.o2sol.pdf4java.flowdocument.PDFFlowDocumentToCSettings;
import com.o2sol.pdf4java.flowdocument.PDFFlowHeadingContent;
import com.o2sol.pdf4java.flowdocument.PDFFlowTextContent;
import com.o2sol.pdf4java.graphics.PDFBrush;
import com.o2sol.pdf4java.graphics.PDFStringHorizontalAlign;
import com.o2sol.pdf4java.graphics.colors.PDFRgbColor;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFont;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFontFace;
import com.o2sol.pdf4java.graphics.formattedcontent.PDFFormattedContent;
import com.o2sol.pdf4java.graphics.formattedcontent.PDFFormattedParagraph;
import com.o2sol.pdf4java.graphics.formattedcontent.PDFFormattedTextBlock;

public class TableOfContents {
    public static void main(String[] args) {
        try {
            PDFStandardFont heading1Font = new PDFStandardFont(PDFStandardFontFace.HELVETICABOLD, 16);
            PDFStandardFont heading2Font = new PDFStandardFont(heading1Font);
            heading2Font.setSize(14);
            PDFStandardFont heading3Font = new PDFStandardFont(heading1Font);
            heading3Font.setSize(12);

            PDFFlowDocument doc = new PDFFlowDocument();

            // Add an intro page to the document.
            PDFFormattedContent fc = new PDFFormattedContent("DEMO DOCUMENT\r\nwith automatically generated\r\nTable of Contents");
            ((PDFFormattedTextBlock)fc.getParagraph(0).getBlock(0)).setFont(new PDFStandardFont(heading1Font));
            ((PDFFormattedTextBlock)fc.getParagraph(0).getBlock(0)).getFont().setSize(24);
            fc.getParagraph(0).setHorizontalAlign(PDFStringHorizontalAlign.CENTER);
            PDFFlowTextContent ftc = new PDFFlowTextContent(fc);
            doc.addContent(ftc);
            doc.startNewPage();

            // Enable/disable the autonumbering of document headings
            boolean autoNumber = true;

            // Setup the flow document content.
            // Heading content objects are used for the generation of table of contents.
            fc = new PDFFormattedContent("Chapter One");
            ((PDFFormattedTextBlock)fc.getParagraph(0).getBlock(0)).setFont(heading1Font);
            fc.getParagraph(0).setSpacingBefore(12); 
            fc.getParagraph(0).setSpacingAfter(12);
            PDFFlowHeadingContent fhc = new PDFFlowHeadingContent(fc);
            fhc.setAutoNumber(autoNumber);
            doc.addContent(fhc);

            fc = new PDFFormattedContent();
            PDFFormattedParagraph fp = new PDFFormattedParagraph(text1);
            fp.setHorizontalAlign(PDFStringHorizontalAlign.JUSTIFIED);
            fp.setFirstLineIndent(18);
            fc.addParagraph(fp);
            ftc = new PDFFlowTextContent(fc);
            doc.addContent(ftc);

            fc = new PDFFormattedContent("Section One");
            ((PDFFormattedTextBlock)fc.getParagraph(0).getBlock(0)).setFont(heading2Font);
            fc.getParagraph(0).setSpacingBefore(12);
            fc.getParagraph(0).setSpacingAfter(12);
            fhc = new PDFFlowHeadingContent(fc);
            fhc.setLevel(2);
            fhc.setAutoNumber(autoNumber);
            doc.addContent(fhc);

            fc = new PDFFormattedContent();
            fp = new PDFFormattedParagraph(text2);
            fp.setHorizontalAlign(PDFStringHorizontalAlign.JUSTIFIED);
            fp.setFirstLineIndent(18);
            fc.addParagraph(fp);
            ftc = new PDFFlowTextContent(fc);
            doc.addContent(ftc);

            fc = new PDFFormattedContent("Module One");
            ((PDFFormattedTextBlock)fc.getParagraph(0).getBlock(0)).setFont(heading3Font);
            fc.getParagraph(0).setSpacingBefore(12);
            fc.getParagraph(0).setSpacingAfter(12);
            fhc = new PDFFlowHeadingContent(fc);
            fhc.setLevel(3);
            fhc.setAutoNumber(autoNumber);
            doc.addContent(fhc);

            fc = new PDFFormattedContent();
            fp = new PDFFormattedParagraph(text3);
            fp.setHorizontalAlign(PDFStringHorizontalAlign.JUSTIFIED);
            fp.setFirstLineIndent(18);
            fc.addParagraph(fp);
            ftc = new PDFFlowTextContent(fc);
            doc.addContent(ftc);

            fc = new PDFFormattedContent("Module Two");
            ((PDFFormattedTextBlock)fc.getParagraph(0).getBlock(0)).setFont(heading3Font);
            fc.getParagraph(0).setSpacingBefore(12);
            fc.getParagraph(0).setSpacingAfter(12);
            fhc = new PDFFlowHeadingContent(fc);
            fhc.setLevel(3);
            fhc.setAutoNumber(autoNumber);
            doc.addContent(fhc);

            fc = new PDFFormattedContent();
            fp = new PDFFormattedParagraph(text4);
            fp.setHorizontalAlign(PDFStringHorizontalAlign.JUSTIFIED);
            fp.setFirstLineIndent(18);
            fc.addParagraph(fp);
            ftc = new PDFFlowTextContent(fc);
            doc.addContent(ftc);

            fc = new PDFFormattedContent("Section Two");
            ((PDFFormattedTextBlock)fc.getParagraph(0).getBlock(0)).setFont(heading2Font);
            fc.getParagraph(0).setSpacingBefore(12);
            fc.getParagraph(0).setSpacingAfter(12);
            fhc = new PDFFlowHeadingContent(fc);
            fhc.setLevel(2);
            fhc.setAutoNumber(autoNumber);
            doc.addContent(fhc);

            fc = new PDFFormattedContent();
            fp = new PDFFormattedParagraph(text5);
            fp.setHorizontalAlign(PDFStringHorizontalAlign.JUSTIFIED);
            fp.setFirstLineIndent(18);
            fc.addParagraph(fp);
            ftc = new PDFFlowTextContent(fc);
            doc.addContent(ftc);

            fc = new PDFFormattedContent("Module Three");
            ((PDFFormattedTextBlock)fc.getParagraph(0).getBlock(0)).setFont(heading3Font);
            fc.getParagraph(0).setSpacingBefore(12);
            fc.getParagraph(0).setSpacingAfter(12);
            fhc = new PDFFlowHeadingContent(fc);
            fhc.setLevel(3);
            fhc.setAutoNumber(autoNumber);
            doc.addContent(fhc);

            fc = new PDFFormattedContent();
            fp = new PDFFormattedParagraph(text6);
            fp.setHorizontalAlign(PDFStringHorizontalAlign.JUSTIFIED);
            fp.setFirstLineIndent(18);
            fc.addParagraph(fp);
            ftc = new PDFFlowTextContent(fc);
            doc.addContent(ftc);

            fc = new PDFFormattedContent("Module Four");
            ((PDFFormattedTextBlock)fc.getParagraph(0).getBlock(0)).setFont(heading3Font);
            fc.getParagraph(0).setSpacingBefore(12);
            fc.getParagraph(0).setSpacingAfter(12);
            fhc = new PDFFlowHeadingContent(fc);
            fhc.setLevel(3);
            fhc.setAutoNumber(autoNumber);
            doc.addContent(fhc);

            fc = new PDFFormattedContent();
            fp = new PDFFormattedParagraph(text7);
            fp.setHorizontalAlign(PDFStringHorizontalAlign.JUSTIFIED);
            fp.setFirstLineIndent(18);
            fc.addParagraph(fp);
            ftc = new PDFFlowTextContent(fc);
            doc.addContent(ftc);

            fc = new PDFFormattedContent("Chapter Two");
            ((PDFFormattedTextBlock)fc.getParagraph(0).getBlock(0)).setFont(heading1Font);
            fc.getParagraph(0).setSpacingBefore(12);
            fc.getParagraph(0).setSpacingAfter(12);
            fhc = new PDFFlowHeadingContent(fc);
            fhc.setAutoNumber(autoNumber);
            doc.addContent(fhc);

            fc = new PDFFormattedContent();
            fp = new PDFFormattedParagraph(text8);
            fp.setHorizontalAlign(PDFStringHorizontalAlign.JUSTIFIED);
            fp.setFirstLineIndent(18);
            fc.addParagraph(fp);
            ftc = new PDFFlowTextContent(fc);
            doc.addContent(ftc);

            fc = new PDFFormattedContent("Section Three");
            ((PDFFormattedTextBlock)fc.getParagraph(0).getBlock(0)).setFont(heading2Font);
            fc.getParagraph(0).setSpacingBefore(12);
            fc.getParagraph(0).setSpacingAfter(12);
            fhc = new PDFFlowHeadingContent(fc);
            fhc.setLevel(2);
            fhc.setAutoNumber(autoNumber);
            doc.addContent(fhc);

            fc = new PDFFormattedContent();
            fp = new PDFFormattedParagraph(text9);
            fp.setHorizontalAlign(PDFStringHorizontalAlign.JUSTIFIED);
            fp.setFirstLineIndent(18);
            fc.addParagraph(fp);
            ftc = new PDFFlowTextContent(fc);
            doc.addContent(ftc);

            fc = new PDFFormattedContent("Module Five");
            ((PDFFormattedTextBlock)fc.getParagraph(0).getBlock(0)).setFont(heading3Font);
            fc.getParagraph(0).setSpacingBefore(12);
            fc.getParagraph(0).setSpacingAfter(12);
            fhc = new PDFFlowHeadingContent(fc);
            fhc.setLevel(3);
            fhc.setAutoNumber(autoNumber);
            doc.addContent(fhc);

            fc = new PDFFormattedContent();
            fp = new PDFFormattedParagraph(text10);
            fp.setHorizontalAlign(PDFStringHorizontalAlign.JUSTIFIED);
            fp.setFirstLineIndent(18);
            fc.addParagraph(fp);
            ftc = new PDFFlowTextContent(fc);
            doc.addContent(ftc);

            fc = new PDFFormattedContent("Module Six");
            ((PDFFormattedTextBlock)fc.getParagraph(0).getBlock(0)).setFont(heading3Font);
            fc.getParagraph(0).setSpacingBefore(12);
            fc.getParagraph(0).setSpacingAfter(12);
            fhc = new PDFFlowHeadingContent(fc);
            fhc.setLevel(3);
            fhc.setAutoNumber(autoNumber);
            doc.addContent(fhc);

            fc = new PDFFormattedContent();
            fp = new PDFFormattedParagraph(text1);
            fp.setHorizontalAlign(PDFStringHorizontalAlign.JUSTIFIED);
            fp.setFirstLineIndent(18);
            fc.addParagraph(fp);
            ftc = new PDFFlowTextContent(fc);
            doc.addContent(ftc);

            fc = new PDFFormattedContent("Section Four");
            ((PDFFormattedTextBlock)fc.getParagraph(0).getBlock(0)).setFont(heading2Font);
            fc.getParagraph(0).setSpacingBefore(12);
            fc.getParagraph(0).setSpacingAfter(12);
            fhc = new PDFFlowHeadingContent(fc);
            fhc.setLevel(2);
            fhc.setAutoNumber(autoNumber);
            doc.addContent(fhc);

            fc = new PDFFormattedContent();
            fp = new PDFFormattedParagraph(text2);
            fp.setHorizontalAlign(PDFStringHorizontalAlign.JUSTIFIED);
            fp.setFirstLineIndent(18);
            fc.addParagraph(fp);
            ftc = new PDFFlowTextContent(fc);
            doc.addContent(ftc);

            fc = new PDFFormattedContent("Module Seven");
            ((PDFFormattedTextBlock)fc.getParagraph(0).getBlock(0)).setFont(heading3Font);
            fc.getParagraph(0).setSpacingBefore(12);
            fc.getParagraph(0).setSpacingAfter(12);
            fhc = new PDFFlowHeadingContent(fc);
            fhc.setLevel(3);
            fhc.setAutoNumber(autoNumber);
            doc.addContent(fhc);

            fc = new PDFFormattedContent();
            fp = new PDFFormattedParagraph(text3);
            fp.setHorizontalAlign(PDFStringHorizontalAlign.JUSTIFIED);
            fp.setFirstLineIndent(18);
            fc.addParagraph(fp);
            ftc = new PDFFlowTextContent(fc);
            doc.addContent(ftc);

            fc = new PDFFormattedContent("Module Eight");
            ((PDFFormattedTextBlock)fc.getParagraph(0).getBlock(0)).setFont(heading3Font);
            fc.getParagraph(0).setSpacingBefore(12);
            fc.getParagraph(0).setSpacingAfter(12);
            fhc = new PDFFlowHeadingContent(fc);
            fhc.setLevel(3);
            fhc.setAutoNumber(autoNumber);
            doc.addContent(fhc);

            fc = new PDFFormattedContent();
            fp = new PDFFormattedParagraph(text4);
            fp.setHorizontalAlign(PDFStringHorizontalAlign.JUSTIFIED);
            fp.setFirstLineIndent(18);
            fc.addParagraph(fp);
            ftc = new PDFFlowTextContent(fc);
            doc.addContent(ftc);

            // Setup the document's table of contents.
            PDFFlowDocumentToCSettings tocSettings = new PDFFlowDocumentToCSettings();
            // Generate the table of contents as document outline.
            tocSettings.setGenerateDocumentOutline(true);
            // Generate the table of contents as a separate page in the document.
            tocSettings.setGenerateContentsPage(true);
            tocSettings.setContentsTextFont(new PDFStandardFont(PDFStandardFontFace.HELVETICA, 10));
            tocSettings.setContentsTextColor(new PDFBrush(PDFRgbColor.BLACK));
            // Insert the TOC page at position 1 (after the first page of the document).
            tocSettings.setContentsPagePosition(1);
            // Visually connect the TOC entries with the page numbers using dots
            tocSettings.setContentsEntryFiller('.');
            // Create a title for the TOC
            fc = new PDFFormattedContent("TABLE OF CONTENTS");
            ((PDFFormattedTextBlock)fc.getParagraph(0).getBlock(0)).setFont(new PDFStandardFont(heading1Font));
            ((PDFFormattedTextBlock)fc.getParagraph(0).getBlock(0)).getFont().setSize(24);
            fc.getParagraph(0).setHorizontalAlign(PDFStringHorizontalAlign.CENTER);
            fc.getParagraph(0).setSpacingAfter(24);
            tocSettings.setContentsTitle(new PDFFlowTextContent(fc));
            // Indent the entries in the TOC
            if (autoNumber)
            {
                tocSettings.setContentsHeadingIndent(10);
            }
            doc.generateTableOfContents(tocSettings);

            doc.save("TableOfContents.pdf");

            System.out.println("PDF document has been saved with success");
        }
        catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }

    private static String text1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras commodo elementum odio, non venenatis risus efficitur a. " +
            "Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce sit amet purus eget sem tristique faucibus. " +
            "Nam ut felis vel ex ullamcorper pretium at quis nisl. Nam ac lacus tincidunt, vulputate sapien vel, tempus sem. Aliquam vel ligula dui. " +
            "Vivamus porttitor nunc vitae mi interdum, vitae ullamcorper turpis bibendum. Vestibulum sagittis lorem ante, at tincidunt arcu suscipit eu. " +
            "Morbi augue eros, tristique a consectetur ac, egestas nec turpis. Praesent non purus quis sem consequat tempor sed ac augue. Integer at mauris ac ipsum bibendum aliquam vitae id mi.";
    private static String text2 = "Praesent efficitur tortor in ligula mattis scelerisque. Fusce in placerat augue. Mauris pretium, dui ac accumsan aliquet, justo sem posuere purus, " +
            "sit amet tristique mi tortor malesuada lorem. Sed congue sem a neque tristique tristique et a odio. Curabitur quis aliquam turpis, tincidunt ullamcorper velit. " +
            "Phasellus posuere, justo auctor convallis luctus, mi tortor interdum lorem, ac tempor nisi lorem in erat. Maecenas dapibus tristique lacus id egestas. " +
            "Vivamus id risus vitae velit porta lacinia. Aliquam erat volutpat. Nulla facilisi. Donec tempor arcu eu rhoncus fringilla.";
    private static String text3 = "Vivamus ullamcorper ligula sit amet interdum imperdiet. Nulla facilisi. Suspendisse et euismod elit. Quisque vitae magna nunc. " +
            "Mauris condimentum at magna blandit semper. Pellentesque in lacus odio. Sed nec molestie lacus, eget scelerisque lorem.";
    private static String text4 = "Etiam rutrum tellus at auctor vehicula. Mauris consequat, tortor vitae finibus efficitur, tellus arcu feugiat leo, in condimentum elit felis ut risus. " +
            "Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Fusce tincidunt libero sem, ut hendrerit turpis dapibus ut. " +
            "Donec lacinia luctus scelerisque. Sed vulputate egestas accumsan. Cras volutpat enim neque, malesuada varius lorem volutpat eget. Mauris lobortis risus ut leo malesuada, " +
            "in volutpat felis finibus. Proin a gravida velit. Ut lorem urna, sollicitudin sit amet nibh at, vestibulum pharetra mauris. Maecenas metus mi, dapibus in erat at, " +
            "congue tincidunt sapien. Vestibulum in pellentesque risus, id accumsan mauris. Donec non ex consequat, pretium ante at, suscipit lacus. Fusce ac consectetur erat. " +
            "Pellentesque maximus justo quis ante ornare condimentum. ";
    private static String text5 = "Mauris quis ante ut sem euismod venenatis at ut massa. Donec gravida velit mauris. Duis suscipit tortor a leo ultricies vehicula. " +
            "Nam condimentum porttitor finibus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Fusce euismod maximus faucibus. Cras venenatis ante mi, nec ultricies tellus elementum in. ";
    private static String text6 = "Duis aliquam ultricies felis ut pharetra. Aliquam vulputate, elit placerat feugiat finibus, metus odio feugiat elit, ut egestas purus nisl vel nulla. " +
            "Nullam vel laoreet purus, in accumsan justo. Morbi rhoncus tellus sem, sed dapibus lacus varius et. In convallis arcu non orci efficitur, varius consequat ipsum eleifend. " +
            "In dapibus mattis maximus. Maecenas quis mi nec mauris maximus scelerisque vel non odio. ";
    private static String text7 = "Proin accumsan orci a nulla gravida tincidunt. Nulla et nisl eget diam rhoncus euismod. Maecenas tellus eros, semper vitae pharetra a, tincidunt ut dolor. " +
            "Nullam tempor at sapien vel efficitur. Duis vel aliquet felis, vitae tincidunt dolor. Sed tortor urna, dictum eu leo quis, feugiat eleifend ligula. Quisque vitae nisi venenatis, " +
            "pretium augue id, consequat velit. Sed dignissim justo velit, id faucibus leo scelerisque sed. In vestibulum blandit ipsum et rhoncus. Aliquam erat lorem, interdum vitae ligula at, " +
            "vulputate feugiat nunc. Fusce condimentum quis ligula ac dictum. Aliquam et viverra purus. Duis sollicitudin dolor eget diam pretium tempus. Nullam in magna eu tortor facilisis placerat non " +
            "vitae eros. Ut vitae magna dictum felis lacinia aliquam facilisis nec sem. ";
    private static String text8 = "Suspendisse potenti. Vivamus maximus mi consequat lectus tincidunt consectetur. Proin vulputate velit lectus, eu lobortis quam lobortis congue. Sed gravida magna non " +
            "eleifend malesuada. Donec tincidunt lorem et semper dignissim. Fusce ut ex vestibulum urna lobortis aliquet in lobortis ex. Integer vehicula erat sed quam dictum varius id sed magna. " +
            "Cras maximus lacus est, ut elementum neque faucibus et. Praesent malesuada egestas scelerisque. Donec interdum ex maximus, auctor nisl non, ornare enim. ";
    private static String text9 = "Nulla finibus quis felis non iaculis. Maecenas fringilla placerat enim non pellentesque. Proin justo orci, elementum ut porttitor in, scelerisque vel nisl. " +
            "Nam sit amet pellentesque justo, et molestie dolor. Cras ipsum justo, facilisis eget diam non, lacinia iaculis libero. Curabitur convallis, velit nec finibus mattis, " +
            "tellus erat elementum ligula, quis viverra quam sapien et enim. Morbi tempor fringilla mattis. ";
    private static String text10 = "Aenean porttitor, augue pretium semper tincidunt, justo orci volutpat odio, malesuada convallis lacus lorem at nisi. Nulla at dolor tincidunt, tempor orci et, blandit metus. " +
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
            "Integer vehicula, quam in laoreet feugiat, neque elit scelerisque dui, eget facilisis dui turpis vel sapien. ";

}
