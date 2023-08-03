import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.actions.*;
import com.o2sol.pdf4java.annotations.PDFLinkAnnotation;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.destinations.PDFPageDirectDestination;
import com.o2sol.pdf4java.destinations.PDFPageNumberDestination;
import com.o2sol.pdf4java.graphics.*;
import com.o2sol.pdf4java.graphics.colors.PDFRgbColor;
import com.o2sol.pdf4java.graphics.fonts.PDFFont;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFont;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFontFace;

import java.util.Random;

public class Actions {
    public static void main(String[] args) {

        try {
            // Create a PDF document with 10 pages.
            PDFFixedDocument document = new PDFFixedDocument();
            PDFStandardFont helvetica = new PDFStandardFont(PDFStandardFontFace.HELVETICA, 216);
            PDFBrush blackBrush = new PDFBrush();
            for (int i = 0; i < 10; i++) {
                PDFPage page = document.addPage();
                page.getCanvas().drawString(String.format("%d", i + 1), helvetica, blackBrush, 5, 5);
            }

            createNamedActions(document, helvetica);

            createGoToActions(document, helvetica);

            createRemoteGoToActions(document, helvetica);

            createLaunchActions(document, helvetica);

            createUriActions(document, helvetica);

            createJavaScriptActions(document, helvetica);

            createDocumentActions(document);

            // Compress the page graphic content.
            for (int i = 0; i < document.getPages().size(); i++) {
                //document.getPage(i).getCanvas().compressAndClose();
            }

            document.save("Actions.pdf");

            System.out.println("PDF document has been saved with success");
        }
        catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }

    }

    private static void createNamedActions(PDFFixedDocument document, PDFFont font)
    {
        PDFPen blackPen = new PDFPen(new PDFRgbColor(0, 0, 0), 1);
        PDFBrush blackBrush = new PDFBrush();

        font.setSize(12);
        PDFStringAppearanceOptions sao = new PDFStringAppearanceOptions();
        sao.setBrush(blackBrush);
        sao.setFont(font);
        PDFStringLayoutOptions slo = new PDFStringLayoutOptions();
        slo.setHorizontalAlign(PDFStringHorizontalAlign.CENTER);
        slo.setVerticalAlign(PDFStringVerticalAlign.MIDDLE);

        for (int i = 0; i < document.getPages().size(); i++)
        {
            document.getPage(i).getCanvas().drawString("Named actions:", font, blackBrush, 400, 20);

            /////////////
            // First page
            /////////////
            document.getPage(i).getCanvas().drawRectangle(blackPen, 400, 40, 200, 20);
            slo.setX(500);
            slo.setY(50);
            document.getPage(i).getCanvas().drawString("Go To First Page", sao, slo);

            // Create a link annotation on top of the widget.
            PDFLinkAnnotation link = new PDFLinkAnnotation();
            document.getPage(i).addAnnotation(link);
            link.setDisplayRectangle(400, 40, 200, 20);

            // Create a named action and attach it to the link.
            PDFNamedAction namedAction = new PDFNamedAction();
            namedAction.setType(PDFNamedActionType.FIRST_PAGE);
            link.setAction(namedAction);

            /////////////
            // Prev page
            /////////////
            document.getPage(i).getCanvas().drawRectangle(blackPen, 400, 80, 200, 20);
            slo.setY(90);
            document.getPage(i).getCanvas().drawString("Go To Previous Page", sao, slo);

            // Create a link annotation on top of the widget.
            link = new PDFLinkAnnotation();
            document.getPage(i).addAnnotation(link);
            link.setDisplayRectangle(400, 80, 200, 20);

            // Create a named action and attach it to the link.
            namedAction = new PDFNamedAction();
            namedAction.setType(PDFNamedActionType.PREV_PAGE);
            link.setAction(namedAction);

            /////////////
            // Next page
            /////////////
            document.getPage(i).getCanvas().drawRectangle(blackPen, 400, 120, 200, 20);
            slo.setY(130);
            document.getPage(i).getCanvas().drawString("Go To Next Page", sao, slo);

            // Create a link annotation on top of the widget.
            link = new PDFLinkAnnotation();
            document.getPage(i).addAnnotation(link);
            link.setDisplayRectangle(400, 120, 200, 20);

            // Create a named action and attach it to the link.
            namedAction = new PDFNamedAction();
            namedAction.setType(PDFNamedActionType.NEXT_PAGE);
            link.setAction(namedAction);

            /////////////
            // Last page
            /////////////
            document.getPage(i).getCanvas().drawRectangle(blackPen, 400, 160, 200, 20);
            slo.setY(170);
            document.getPage(i).getCanvas().drawString("Go To Last Page", sao, slo);

            // Create a link annotation on top of the widget.
            link = new PDFLinkAnnotation();
            document.getPage(i).addAnnotation(link);
            link.setDisplayRectangle(400, 160, 200, 20);

            // Create a named action and attach it to the link.
            namedAction = new PDFNamedAction();
            namedAction.setType(PDFNamedActionType.LAST_PAGE);
            link.setAction(namedAction);

            /////////////
            // Print document
            /////////////
            document.getPage(i).getCanvas().drawRectangle(blackPen, 400, 200, 200, 20);
            slo.setY(210);
            document.getPage(i).getCanvas().drawString("Print Document", sao, slo);

            // Create a link annotation on top of the widget.
            link = new PDFLinkAnnotation();
            document.getPage(i).addAnnotation(link);
            link.setDisplayRectangle(400, 200, 200, 20);

            // Create a named action and attach it to the link.
            namedAction = new PDFNamedAction();
            namedAction.setType(PDFNamedActionType.PRINT);
            link.setAction(namedAction);
        }
    }

    private static void createGoToActions(PDFFixedDocument document, PDFFont font)
    {
        PDFPen blackPen = new PDFPen(new PDFRgbColor(0, 0, 0), 1);
        PDFBrush blackBrush = new PDFBrush();

        font.setSize(12);
        PDFStringAppearanceOptions sao = new PDFStringAppearanceOptions();
        sao.setBrush(blackBrush);
        sao.setFont(font);
        PDFStringLayoutOptions slo = new PDFStringLayoutOptions();
        slo.setHorizontalAlign(PDFStringHorizontalAlign.CENTER);
        slo.setVerticalAlign(PDFStringVerticalAlign.MIDDLE);

        Random rnd = new Random(0);
        for (int i = 0; i < document.getPages().size(); i++)
        {
            int destinationPage = rnd.nextInt(document.getPages().size());

            document.getPage(i).getCanvas().drawString("Go To actions:", font, blackBrush, 400, 240);

            document.getPage(i).getCanvas().drawRectangle(blackPen, 400, 260, 200, 20);
            slo.setX(500);
            slo.setY(270);
            document.getPage(i).getCanvas().drawString("Go To page: " + (destinationPage + 1), sao, slo);

            // Create a link annotation on top of the widget.
            PDFLinkAnnotation link = new PDFLinkAnnotation();
            document.getPage(i).addAnnotation(link);
            link.setDisplayRectangle(400, 260, 200, 20);

            // Create a GoTo action and attach it to the link.
            PDFPageDirectDestination pageDestination = new PDFPageDirectDestination();
            pageDestination.setPage(document.getPage(destinationPage));
            pageDestination.setLeft(0);
            pageDestination.setTop(0);
            pageDestination.setZoom(0); // Keep current zoom
            PDFGoToAction gotoPageAction = new PDFGoToAction();
            gotoPageAction.setDestination(pageDestination);
            link.setAction(gotoPageAction);
        }
    }

    private static void createRemoteGoToActions(PDFFixedDocument document, PDFFont font)
    {
        PDFPen blackPen = new PDFPen(new PDFRgbColor(0, 0, 0), 1);
        PDFBrush blackBrush = new PDFBrush();

        font.setSize(12);
        PDFStringAppearanceOptions sao = new PDFStringAppearanceOptions();
        sao.setBrush(blackBrush);
        sao.setFont(font);
        PDFStringLayoutOptions slo = new PDFStringLayoutOptions();
        slo.setHorizontalAlign(PDFStringHorizontalAlign.CENTER);
        slo.setVerticalAlign(PDFStringVerticalAlign.MIDDLE);

        Random rnd = new Random(0);
        for (int i = 0; i < document.getPages().size(); i++)
        {
            int destinationPage = rnd.nextInt(document.getPages().size());

            document.getPage(i).getCanvas().drawString("Go To Remote actions:", font, blackBrush, 400, 300);

            document.getPage(i).getCanvas().drawRectangle(blackPen, 400, 320, 200, 20);
            slo.setX(500);
            slo.setY(330);
            document.getPage(i).getCanvas().drawString("Go To page " + (destinationPage + 1) + " in sample.pdf", sao, slo);

            // Create a link annotation on top of the widget.
            PDFLinkAnnotation link = new PDFLinkAnnotation();
            document.getPage(i).addAnnotation(link);
            link.setDisplayRectangle(400, 320, 200, 20);

            // Create a GoToR action and attach it to the link.
            PDFPageNumberDestination pageDestination = new PDFPageNumberDestination();
            pageDestination.setPageNumber(destinationPage);
            pageDestination.setLeft(0);
            pageDestination.setTop(792);
            pageDestination.setZoom(0); // Keep current zoom
            PDFRemoteGoToAction remoteGoToAction = new PDFRemoteGoToAction();
            remoteGoToAction.setFileName("sample.pdf");
            remoteGoToAction.setDestination(pageDestination);
            link.setAction(remoteGoToAction);
        }
    }

    private static void createLaunchActions(PDFFixedDocument document, PDFFont font)
    {
        PDFPen blackPen = new PDFPen(new PDFRgbColor(0, 0, 0), 1);
        PDFBrush blackBrush = new PDFBrush();

        font.setSize(12);
        PDFStringAppearanceOptions sao = new PDFStringAppearanceOptions();
        sao.setBrush(blackBrush);
        sao.setFont(font);
        PDFStringLayoutOptions slo = new PDFStringLayoutOptions();
        slo.setHorizontalAlign(PDFStringHorizontalAlign.CENTER);
        slo.setVerticalAlign(PDFStringVerticalAlign.MIDDLE);

        for (int i = 0; i < document.getPages().size(); i++)
        {
            document.getPage(i).getCanvas().drawString("Launch actions:", font, blackBrush, 400, 360);

            document.getPage(i).getCanvas().drawRectangle(blackPen, 400, 380, 200, 20);
            slo.setX(500);
            slo.setY(390);
            document.getPage(i).getCanvas().drawString("Launch samples explorer", sao, slo);

            // Create a link annotation on top of the widget.
            PDFLinkAnnotation link = new PDFLinkAnnotation();
            document.getPage(i).addAnnotation(link);
            link.setDisplayRectangle(400, 380, 200, 20);

            // Create a launch action and attach it to the link.
            PDFLaunchAction launchAction = new PDFLaunchAction();
            launchAction.setFileName("com.o2sol.pdf4java.exe");
            link.setAction(launchAction);
        }
    }

    private static void createUriActions(PDFFixedDocument document, PDFFont font)
    {
        PDFPen blackPen = new PDFPen(new PDFRgbColor(0, 0, 0), 1);
        PDFBrush blackBrush = new PDFBrush();

        font.setSize(12);
        PDFStringAppearanceOptions sao = new PDFStringAppearanceOptions();
        sao.setBrush(blackBrush);
        sao.setFont(font);
        PDFStringLayoutOptions slo = new PDFStringLayoutOptions();
        slo.setHorizontalAlign(PDFStringHorizontalAlign.CENTER);
        slo.setVerticalAlign(PDFStringVerticalAlign.MIDDLE);

        for (int i = 0; i < document.getPages().size(); i++)
        {
            document.getPage(i).getCanvas().drawString("Uri actions:", font, blackBrush, 400, 420);

            document.getPage(i).getCanvas().drawRectangle(blackPen, 400, 440, 200, 20);
            slo.setX(500);
            slo.setY(450);
            document.getPage(i).getCanvas().drawString("Go to o2sol.com", sao, slo);

            // Create a link annotation on top of the widget.
            PDFLinkAnnotation link = new PDFLinkAnnotation();
            document.getPage(i).addAnnotation(link);
            link.setDisplayRectangle(400, 440, 200, 20);

            // Create an uri action and attach it to the link.
            PDFUriAction uriAction = new PDFUriAction();
            uriAction.setURI("http://www.o2sol.com");
            link.setAction(uriAction);
        }
    }

    private static void createJavaScriptActions(PDFFixedDocument document, PDFFont font)
    {
        PDFPen blackPen = new PDFPen(new PDFRgbColor(0, 0, 0), 1);
        PDFBrush blackBrush = new PDFBrush();

        font.setSize(12);
        PDFStringAppearanceOptions sao = new PDFStringAppearanceOptions();
        sao.setBrush(blackBrush);
        sao.setFont(font);
        PDFStringLayoutOptions slo = new PDFStringLayoutOptions();
        slo.setHorizontalAlign(PDFStringHorizontalAlign.CENTER);
        slo.setVerticalAlign(PDFStringVerticalAlign.MIDDLE);

        for (int i = 0; i < document.getPages().size(); i++)
        {
            document.getPage(i).getCanvas().drawString("JavaScript actions:", font, blackBrush, 400, 480);

            document.getPage(i).getCanvas().drawRectangle(blackPen, 400, 500, 200, 20);
            slo.setX(500);
            slo.setY(510);
            document.getPage(i).getCanvas().drawString("Click me", sao, slo);

            // Create a link annotation on top of the widget.
            PDFLinkAnnotation link = new PDFLinkAnnotation();
            document.getPage(i).addAnnotation(link);
            link.setDisplayRectangle(400, 500, 200, 20);

            // Create a Javascript action and attach it to the link.
            PDFJavaScriptAction jsAction = new PDFJavaScriptAction();
            jsAction.setScript("app.alert({cMsg: \"JavaScript action: you are now on page " + (i + 1) + "\", cTitle: \"com.o2sol.pdf4java Actions Sample\"});");
            link.setAction(jsAction);
        }
    }

    private static void createDocumentActions(PDFFixedDocument document)
    {
        // Create an action that will open the document at the last page with 200% zoom.
        PDFPageDirectDestination pageDestination = new PDFPageDirectDestination();
        pageDestination.setPage(document.getPages().get(document.getPages().size() - 1));
        pageDestination.setZoom(200);
        pageDestination.setTop(0);
        pageDestination.setLeft(0);
        PDFGoToAction openAction = new PDFGoToAction();
        openAction.setDestination(pageDestination);
        document.setOpenAction(openAction);

        // Create an action that is executed when the document is closed.
        PDFJavaScriptAction jsCloseAction = new PDFJavaScriptAction();
        jsCloseAction.setScript("app.alert({cMsg: \"The document will close now.\", cTitle: \"com.o2sol.pdf4java Actions Sample\"});");
        document.setBeforeCloseAction(jsCloseAction);

        // Create an action that is executed before the document is printed.
        PDFJavaScriptAction jsBeforePrintAction = new PDFJavaScriptAction();
        jsBeforePrintAction.setScript("app.alert({cMsg: \"The document will be printed.\", cTitle: \"com.o2sol.pdf4java Actions Sample\"});");
        document.setBeforePrintAction(jsBeforePrintAction);

        // Create an action that is executed after the document is printed.
        PDFJavaScriptAction jsAfterPrintAction = new PDFJavaScriptAction();
        jsAfterPrintAction.setScript("app.alert({cMsg: \"The document has been printed.\", cTitle: \"com.o2sol.pdf4java Actions Sample\"});");
        document.setAfterPrintAction(jsAfterPrintAction);
    }
}
