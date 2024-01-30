import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.actions.PDFUriAction;
import com.o2sol.pdf4java.annotations.PDFAnnotationBorder;
import com.o2sol.pdf4java.annotations.PDFLinkAnnotation;
import com.o2sol.pdf4java.annotations.PDFLinkAnnotationHighlightMode;
import com.o2sol.pdf4java.core.cos.PDFCosDictionaryContainer;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.document.*;
import com.o2sol.pdf4java.forms.PDFTextBoxField;
import com.o2sol.pdf4java.forms.PDFTextBoxWidget;
import com.o2sol.pdf4java.graphics.*;
import com.o2sol.pdf4java.graphics.colors.PDFRgbColor;
import com.o2sol.pdf4java.graphics.fonts.truetype.PDFAnsiTrueTypeFont;
import com.o2sol.pdf4java.graphics.formattedcontent.*;
import com.o2sol.pdf4java.graphics.images.PDFPngImage;
import com.o2sol.pdf4java.logicalstructure.*;
import com.o2sol.pdf4java.standards.PDFUAFormat;
import com.o2sol.pdf4java.standards.PDFUAFormatter;

import java.io.IOException;

public class PDFUA {
    public static void main(String[] args) throws IOException {
        try {
            PDFAnsiTrueTypeFont verdana = new PDFAnsiTrueTypeFont("..\\..\\SupportFiles\\verdana.ttf", 10, true);
            PDFAnsiTrueTypeFont verdanaBold = new PDFAnsiTrueTypeFont("..\\..\\SupportFiles\\verdanab.ttf", 10, true);

            PDFFixedDocument document = new PDFFixedDocument();
            document.setLanguage("en-US");

            PDFDocumentInformation docInfo = new PDFDocumentInformation();
            document.setDocumentInformation(docInfo);
            docInfo.setAuthor("O2 Solutions");
            docInfo.setTitle("PDF4Java PDF/UA-1 Demo");
            docInfo.setCreator("PDF4Java PDF/UA-1 Demo Application");
            docInfo.setProducer("PDF4Java");
            docInfo.setKeywords("pdf/ua");
            docInfo.setSubject("PDF/UA-1 Sample produced by PDF4Java");
            document.setXmpMetadata(new PDFXmpMetadata());

            document.setViewerPreferences(new PDFViewerPreferences());
            document.getViewerPreferences().setIsDocumentTitleDisplayed(true);

            document.setMarkInformation(new PDFMarkInformation());
            document.getMarkInformation().setIsTaggedPDF(true);

            document.setStructureTree(new PDFStructureTree());

            PDFStructureElement seDocument = new PDFStructureElement(PDFStandardStructureTypes.DOCUMENT);
            seDocument.setTitle("PDF4NET PDF/UA-1 Demo");
            document.getStructureTree().setStructureElements(seDocument);

            SimpleText(document, seDocument, verdana);

            FormattedContent(document, seDocument, verdana);

            AnnotationsAndFormFields(document, seDocument, verdana);

            PDFUAFormatter.save(document, "PDFUA1.pdf", PDFUAFormat.PDF_UA_1);

            System.out.println("PDF documents have been saved with success");
        } catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }


    private static void SimpleText(PDFFixedDocument document, PDFStructureElement seParent, PDFAnsiTrueTypeFont font)
    {
        PDFAnsiTrueTypeFont headingFont = new PDFAnsiTrueTypeFont(font, 16);
        PDFAnsiTrueTypeFont textFont = new PDFAnsiTrueTypeFont(font, 10);
        PDFBrush blackBrush = new PDFBrush(PDFRgbColor.BLACK);
        PDFPen blackPen = new PDFPen(PDFRgbColor.BLACK, 1);
        PDFPage page = document.addPage();
        PDFPageCanvas pageCanvas = page.getCanvas();

        PDFStructureElement seSection = new PDFStructureElement(PDFStandardStructureTypes.SECTION);
        seSection.setTitle("Simple text");
        seParent.appendChild(seSection);

        PDFStructureElement seHeading = new PDFStructureElement(PDFStandardStructureTypes.HEADING);
        seSection.appendChild(seHeading);

        pageCanvas.beginStructureElement(seHeading);
        pageCanvas.drawString("Page heading", headingFont, blackBrush, 30, 50);
        pageCanvas.endStructureElement();

        PDFStructureElement seParagraph1 = new PDFStructureElement(PDFStandardStructureTypes.PARAGRAPH);
        seSection.appendChild(seParagraph1);

        pageCanvas.beginStructureElement(seParagraph1);
        pageCanvas.drawString("Sample paragraph. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris in sodales ligula at lobortis.", textFont, blackBrush, 30, 70);
        pageCanvas.endStructureElement();

        PDFStructureElement seFigure = new PDFStructureElement(PDFStandardStructureTypes.FIGURE);
        seFigure.setActualText("PDF4Java");
        seFigure.setAlternateDescription("PDF4Java Logo");
        seSection.appendChild(seFigure);

        pageCanvas.beginStructureElement(seFigure);
        PDFPngImage logoImage = new PDFPngImage("..\\..\\SupportFiles\\logo.png");
        pageCanvas.drawImage(logoImage, 30, 90, 256, 128);
        pageCanvas.endStructureElement();

        // A structure element with 2 content items and an artifact between them.
        PDFStructureElement seParagraph2 = new PDFStructureElement(PDFStandardStructureTypes.PARAGRAPH);
        seSection.appendChild(seParagraph2);

        pageCanvas.beginStructureElement(seParagraph2);
        pageCanvas.drawString("First line of text.", textFont, blackBrush, 30, 230);
        pageCanvas.endStructureElement();

        pageCanvas.beginArtifactMarkedContent();
        pageCanvas.drawLine(blackPen, 30, 242, 100, 242);
        pageCanvas.endMarkedContent();

        pageCanvas.beginStructureElement(seParagraph2);
        pageCanvas.drawString("Second line of text.", textFont, blackBrush, 30, 245);
        pageCanvas.endStructureElement();

        PDFStructureElement seParagraph3 = new PDFStructureElement(PDFStandardStructureTypes.PARAGRAPH);
        seSection.appendChild(seParagraph3);

        String text = "Paragraph with underlined text. The structure element is passed as parameter to DrawString method in order to properly tag graphic artifacts such as underlines. " +
                "Morbi pulvinar eros sit amet diam lacinia, ut feugiat ligula bibendum. Suspendisse ultrices cursus condimentum. " +
                "Pellentesque semper iaculis luctus. Sed ac maximus urna. Aliquam erat volutpat. Vivamus vel sollicitudin dui. Aenean efficitur " +
                "fringilla dui, at varius lacus luctus ac. Quisque tellus dui, lacinia non lectus nec, semper faucibus erat.";
        PDFStringAppearanceOptions sao = new PDFStringAppearanceOptions();
        sao.setBrush(blackBrush);
        sao.setFont(textFont);
        sao.getFont().setUnderline(true);
        PDFStringLayoutOptions slo = new PDFStringLayoutOptions();
        slo.setX(30);
        slo.setY(260);
        slo.setWidth(550);
        pageCanvas.drawString(text, sao, slo, seParagraph3);

        // A custom structure element that will be mapped to standard Pargraph structure.
        PDFStructureElement seSpecialParagraph = new PDFStructureElement("SpecialParagraph");
        // The structure element specifies an ID that needs to be added to document's IDMap.
        seSpecialParagraph.setID("specialpara");
        seSection.appendChild(seSpecialParagraph);

        pageCanvas.beginStructureElement(seSpecialParagraph);
        textFont.setUnderline(false);
        textFont.setSize(18);
        pageCanvas.drawString("A special paragraph with custom structure element type.", textFont, blackBrush, 30, 350);
        pageCanvas.endStructureElement();

        // Map the custom structure type to a known structure type.
        document.getStructureTree().setRoleMap(new PDFRoleMap());
        document.getStructureTree().getRoleMap().put("SpecialParagraph", PDFStandardStructureTypes.PARAGRAPH);

        // Include the ID of the structure element in the document's identifiers map.
        document.getStructureTree().setIdentifierMap(new PDFIdMap());
        document.getStructureTree().getIdentifierMap().put("specialpara", seSpecialParagraph);
    }

    private static void FormattedContent(PDFFixedDocument document, PDFStructureElement seParent, PDFAnsiTrueTypeFont font)
    {
        String text1 = "Morbi pulvinar eros sit amet diam lacinia, ut feugiat ligula bibendum. Suspendisse ultrices cursus condimentum. " +
                "Pellentesque semper iaculis luctus. Sed ac maximus urna. Aliquam erat volutpat. ";
        String text2 = "Vivamus vel sollicitudin dui. Aenean efficitur " +
                "fringilla dui, at varius lacus luctus ac. Quisque tellus dui, lacinia non lectus nec, semper faucibus erat.";

        PDFAnsiTrueTypeFont headingFont = new PDFAnsiTrueTypeFont(font, 16);
        PDFAnsiTrueTypeFont textFont = new PDFAnsiTrueTypeFont(font, 10);
        PDFBrush blackBrush = new PDFBrush(PDFRgbColor.BLACK);
        PDFPen blackPen = new PDFPen(PDFRgbColor.BLACK, 1);
        PDFPage page = document.addPage();
        PDFPageCanvas pageCanvas = page.getCanvas();
        page.setTabOrder(PDFPageTabOrder.STRUCTURE);

        PDFStructureElement seSection = new PDFStructureElement(PDFStandardStructureTypes.SECTION);
        seSection.setTitle("Formatted content");
        seParent.appendChild(seSection);

        PDFStructureElement seHeading = new PDFStructureElement(PDFStandardStructureTypes.HEADING);
        seSection.appendChild(seHeading);

        pageCanvas.beginStructureElement(seHeading);
        pageCanvas.drawString("Another heading", headingFont, blackBrush, 30, 50);
        pageCanvas.endStructureElement();

        PDFFormattedContent fc = new PDFFormattedContent();

        PDFFormattedParagraph paragraph1 = new PDFFormattedParagraph(text1, textFont);
        paragraph1.setLineSpacing(1.2);
        paragraph1.setLineSpacingMode(PDFFormattedParagraphLineSpacing.MULTIPLE);
        paragraph1.setStructureElement(new PDFStructureElement(PDFStandardStructureTypes.PARAGRAPH));
        seSection.appendChild(paragraph1.getStructureElement());
        fc.addParagraph(paragraph1);

        PDFFormattedTextBlock block1 = new PDFFormattedTextBlock("Sample paragraph with a link.\r\n", textFont);
        block1.setStructureElement(new PDFStructureElement(PDFStandardStructureTypes.SPAN));
        PDFFormattedTextBlock block2 = new PDFFormattedTextBlock("http://www.o2sol.com/\r\n", textFont);
        block2.setTextColor(new PDFBrush(PDFRgbColor.BLUE));
        block2.setAction(new PDFUriAction("http://www.o2sol.com/"));
        block2.setStructureElement(new PDFStructureElement(PDFStandardStructureTypes.LINK));
        PDFFormattedTextBlock block3 = new PDFFormattedTextBlock("http://www.pdf4net.com/", textFont);
        block3.setTextColor(new PDFBrush(PDFRgbColor.BLUE));
        block3.setAction(new PDFUriAction("http://www.pdf4net.com/"));
        block3.setStructureElement(new PDFStructureElement(PDFStandardStructureTypes.LINK));
        PDFFormattedParagraph paragraph2 = new PDFFormattedParagraph(new PDFFormattedBlock[]{ block1, block2, block3 });
        paragraph2.setLineSpacing(1.2);
        paragraph2.setLineSpacingMode(PDFFormattedParagraphLineSpacing.MULTIPLE);
        paragraph2.setStructureElement(new PDFStructureElement(PDFStandardStructureTypes.PARAGRAPH));
        // Do not mark the paragraph in the content stream, instead its blocks will be marked.
        paragraph2.getStructureElement().setGenerateMarkedContentSequence(false);
        paragraph2.getStructureElement().appendChild(block1.getStructureElement());
        paragraph2.getStructureElement().appendChild(block2.getStructureElement());
        paragraph2.getStructureElement().appendChild(block3.getStructureElement());
        seSection.appendChild(paragraph2.getStructureElement());
        fc.addParagraph(paragraph2);

        PDFFormattedParagraph paragraph3 = new PDFFormattedParagraph(text2, textFont);
        paragraph3.setLineSpacing(1.2);
        paragraph3.setLineSpacingMode(PDFFormattedParagraphLineSpacing.MULTIPLE);
        paragraph3.setStructureElement(new PDFStructureElement(PDFStandardStructureTypes.PARAGRAPH));
        seSection.appendChild(paragraph3.getStructureElement());
        fc.addParagraph(paragraph3);

        pageCanvas.drawFormattedContent(fc, 30, 70, 550, 0);
    }

    private static void AnnotationsAndFormFields(PDFFixedDocument document, PDFStructureElement seParent, PDFAnsiTrueTypeFont font)
    {
        PDFAnsiTrueTypeFont headingFont = new PDFAnsiTrueTypeFont(font, 16);
        PDFAnsiTrueTypeFont textFont = new PDFAnsiTrueTypeFont(font, 10);
        PDFBrush blackBrush = new PDFBrush(PDFRgbColor.BLACK);
        PDFPen blackPen = new PDFPen(PDFRgbColor.BLACK, 1);
        PDFPage page = document.addPage();
        PDFPageCanvas pageCanvas = page.getCanvas();
        page.setTabOrder(PDFPageTabOrder.STRUCTURE);

        PDFStructureElement seSection = new PDFStructureElement(PDFStandardStructureTypes.SECTION);
        seSection.setTitle("Annotations and form fields");
        seParent.appendChild(seSection);

        PDFStructureElement seHeading = new PDFStructureElement(PDFStandardStructureTypes.HEADING);
        seSection.appendChild(seHeading);

        pageCanvas.beginStructureElement(seHeading);
        pageCanvas.drawString("Annotations and form fields", headingFont, blackBrush, 30, 50);
        pageCanvas.endStructureElement();

        PDFStructureElement seParagraph1 = new PDFStructureElement(PDFStandardStructureTypes.PARAGRAPH);
        seSection.appendChild(seParagraph1);

        pageCanvas.beginStructureElement(seParagraph1);
        pageCanvas.drawString("Our website:", textFont, blackBrush, 30, 70);
        pageCanvas.endStructureElement();

        PDFStructureElement seLink = new PDFStructureElement(PDFStandardStructureTypes.LINK);
        seParagraph1.appendChild(seLink);

        pageCanvas.beginStructureElement(seLink);
        pageCanvas.drawString("http://www.o2sol.com/", textFont, blackBrush, 100, 70);
        pageCanvas.endStructureElement();

        PDFLinkAnnotation link = new PDFLinkAnnotation();
        page.addAnnotation(link);
        link.setDisplayRectangle(95, 70, 130, 10);
        link.setHighlightStyle(PDFLinkAnnotationHighlightMode.INVERT);
        link.setContents("http://www.o2sol.com/");

        PDFObjectReference linkRef = new PDFObjectReference();
        linkRef.setPage(page);
        linkRef.setStream((PDFCosDictionaryContainer)link.getCosAnnotation());
        seLink.appendChild(linkRef);
        document.getStructureTree().mapObjectReference(link, seLink);

        PDFStructureElement seParagraph2 = new PDFStructureElement(PDFStandardStructureTypes.PARAGRAPH);
        seSection.appendChild(seParagraph2);

        pageCanvas.beginStructureElement(seParagraph2);
        pageCanvas.drawString("Enter your name:", textFont, blackBrush, 30, 100);
        pageCanvas.endStructureElement();

        PDFStructureElement seForm = new PDFStructureElement(PDFStandardStructureTypes.FORM);
        seParagraph2.appendChild(seForm);

        PDFTextBoxField fldName = new PDFTextBoxField("name");
        page.addField(fldName);
        fldName.setAlternateName("Enter your name");
        PDFTextBoxWidget tbxWidget = (PDFTextBoxWidget)fldName.getWidget(0);
        tbxWidget.setDisplayRectangle(120, 95, 130, 20);
        tbxWidget.setBorder(new PDFAnnotationBorder());
        tbxWidget.getBorder().setColor(PDFRgbColor.BLUE);
        tbxWidget.getBorder().setWidth(1);
        tbxWidget.setBackgroundColor(PDFRgbColor.LIGHT_BLUE);
        tbxWidget.setFont(textFont);

        PDFObjectReference fieldRef = new PDFObjectReference();
        fieldRef.setPage(page);
        fieldRef.setStream((PDFCosDictionaryContainer)tbxWidget.getCosAnnotation());
        seForm.appendChild(fieldRef);
        document.getStructureTree().mapObjectReference(tbxWidget, seForm);
    }

}
