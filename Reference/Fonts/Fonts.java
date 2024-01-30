import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.graphics.PDFBrush;
import com.o2sol.pdf4java.graphics.PDFPageCanvas;
import com.o2sol.pdf4java.graphics.colors.PDFRgbColor;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFont;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFontFace;
import com.o2sol.pdf4java.graphics.fonts.truetype.PDFAnsiTrueTypeFont;
import com.o2sol.pdf4java.graphics.fonts.truetype.PDFUnicodeTrueTypeFont;

public class Fonts {
    public static void main(String[] args) {
        try {
            PDFFixedDocument document = new PDFFixedDocument();

            drawAnsiStandardFonts(document.addPage());

            drawCJKStandardFonts(document.addPage());

            drawTrueTypeFonts(document.addPage());

            disableTextCopy(document.addPage());

            document.save("Fonts.pdf");

            System.out.println("PDF document has been saved with success");
        } catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }

    public static void drawAnsiStandardFonts(PDFPage page) {

        PDFStandardFont titleFont = new PDFStandardFont(PDFStandardFontFace.HELVETICA_BOLD, 22);
        PDFBrush blackBrush = new PDFBrush(new PDFRgbColor());
        PDFPageCanvas pageCanvas = page.getCanvas();

        pageCanvas.drawString("Standard fonts", titleFont, blackBrush, 20, 50);
        pageCanvas.drawString("(Base 14 PDF fonts supported by any PDF viewer)", titleFont, blackBrush, 20, 75);

        PDFStandardFont helvetica = new PDFStandardFont(PDFStandardFontFace.HELVETICA, 16);
        pageCanvas.drawString("Helvetica - Lorem ipsum dolor sit amet, consectetur adipiscing elit.", helvetica, blackBrush, 20, 125);

        PDFStandardFont helveticaBold = new PDFStandardFont(PDFStandardFontFace.HELVETICA_BOLD, 16);
        pageCanvas.drawString("Helvetica Bold - Lorem ipsum dolor sit amet, consectetur adipiscing elit.", helveticaBold, blackBrush, 20, 150);

        PDFStandardFont helveticaItalic = new PDFStandardFont(PDFStandardFontFace.HELVETICA_ITALIC, 16);
        pageCanvas.drawString("Helvetica Italic - Lorem ipsum dolor sit amet, consectetur adipiscing elit.", helveticaItalic, blackBrush, 20, 175);

        PDFStandardFont helveticaBoldItalic = new PDFStandardFont(PDFStandardFontFace.HELVETICA_BOLD_ITALIC, 16);
        pageCanvas.drawString("Helvetica Bold Italic - Lorem ipsum dolor sit amet, consectetur adipiscing elit.", helveticaBoldItalic, blackBrush, 20, 200);

        PDFStandardFont timesRoman = new PDFStandardFont(PDFStandardFontFace.TIMES_ROMAN, 16);
        pageCanvas.drawString("Times Roman - Lorem ipsum dolor sit amet, consectetur adipiscing elit.", timesRoman, blackBrush, 20, 225);

        PDFStandardFont timesRomanBold = new PDFStandardFont(PDFStandardFontFace.TIMES_ROMAN_BOLD, 16);
        pageCanvas.drawString("Times Roman Bold - Lorem ipsum dolor sit amet, consectetur adipiscing elit.", timesRomanBold, blackBrush, 20, 250);

        PDFStandardFont timesRomanItalic = new PDFStandardFont(PDFStandardFontFace.TIMES_ROMAN_ITALIC, 16);
        pageCanvas.drawString("Times Roman Italic - Lorem ipsum dolor sit amet, consectetur adipiscing elit.", timesRomanItalic, blackBrush, 20, 275);

        PDFStandardFont timesRomanBoldItalic = new PDFStandardFont(PDFStandardFontFace.TIMES_ROMAN_BOLD_ITALIC, 16);
        pageCanvas.drawString("Times Roman Bold Italic - Lorem ipsum dolor sit amet, consectetur adipiscing elit.", timesRomanBoldItalic, blackBrush, 20, 300);

        PDFStandardFont courier = new PDFStandardFont(PDFStandardFontFace.COURIER, 16);
        pageCanvas.drawString("Courier - Lorem ipsum dolor sit amet, consectetur adipiscing elit.", courier, blackBrush, 20, 325);

        PDFStandardFont courierBold = new PDFStandardFont(PDFStandardFontFace.COURIER_BOLD, 16);
        pageCanvas.drawString("Courier Bold - Lorem ipsum dolor sit amet, consectetur adipiscing elit.", courierBold, blackBrush, 20, 350);

        PDFStandardFont courierItalic = new PDFStandardFont(PDFStandardFontFace.COURIER_ITALIC, 16);
        pageCanvas.drawString("Courier Italic - Lorem ipsum dolor sit amet, consectetur adipiscing elit.", courierItalic, blackBrush, 20, 375);

        PDFStandardFont courierBoldItalic = new PDFStandardFont(PDFStandardFontFace.COURIER_BOLD_ITALIC, 16);
        pageCanvas.drawString("Courier Bold Italic - Lorem ipsum dolor sit amet, consectetur adipiscing elit.", courierBoldItalic, blackBrush, 20, 400);
    }

    public static void drawCJKStandardFonts(PDFPage page) {
        PDFStandardFont titleFont = new PDFStandardFont(PDFStandardFontFace.HELVETICA_BOLD, 22);
        PDFBrush blackBrush = new PDFBrush(new PDFRgbColor());
        PDFPageCanvas pageCanvas = page.getCanvas();

        pageCanvas.drawString("Standard CJK fonts", titleFont, blackBrush, 20, 50);
        pageCanvas.drawString("(CJK fonts supported by Adobe Reader", titleFont, blackBrush, 20, 75);
        pageCanvas.drawString(" using CJK language packs)", titleFont, blackBrush, 20, 100);

        PDFStandardFont heiseiKakuGothic = new PDFStandardFont(PDFStandardFontFace.HEISEI_KAKU_GOTHIC_W5, 16);
        pageCanvas.drawString("Heisei Kaku Gothic - サンプル日本語フォントデモテキスト.", heiseiKakuGothic, blackBrush, 20, 150);

        PDFStandardFont heiseiKakuGothicBold = new PDFStandardFont(PDFStandardFontFace.HEISEI_KAKU_GOTHIC_W5_BOLD, 16);
        pageCanvas.drawString("Heisei Kaku Gothic Bold - サンプル日本語フォントデモテキスト.", heiseiKakuGothicBold, blackBrush, 20, 175);

        PDFStandardFont heiseiKakuGothicItalic = new PDFStandardFont(PDFStandardFontFace.HEISEI_KAKU_GOTHIC_W5_ITALIC, 16);
        pageCanvas.drawString("Heisei Kaku Gothic Italic - サンプル日本語フォントデモテキスト.", heiseiKakuGothicItalic, blackBrush, 20, 200);

        PDFStandardFont heiseiKakuGothicBoldItalic = new PDFStandardFont(PDFStandardFontFace.HEISEI_KAKU_GOTHIC_W5_BOLD_ITALIC, 16);
        pageCanvas.drawString("Heisei Kaku Gothic Bold Italic - サンプル日本語フォントデモテキスト.", heiseiKakuGothicBoldItalic, blackBrush, 20, 225);

        PDFStandardFont heiseiMincho = new PDFStandardFont(PDFStandardFontFace.HEISEI_MINCHO_W3, 16);
        pageCanvas.drawString("Heisei Mincho - サンプル日本語フォントデモテキスト.", heiseiMincho, blackBrush, 20, 250);

        PDFStandardFont heiseiMinchoBold = new PDFStandardFont(PDFStandardFontFace.HEISEI_MINCHO_W3_BOLD, 16);
        pageCanvas.drawString("Heisei Mincho Bold - サンプル日本語フォントデモテキスト.", heiseiMinchoBold, blackBrush, 20, 275);

        PDFStandardFont heiseiMinchoItalic = new PDFStandardFont(PDFStandardFontFace.HEISEI_MINCHO_W3_ITALIC, 16);
        pageCanvas.drawString("Heisei Mincho Italic - サンプル日本語フォントデモテキスト.", heiseiMinchoItalic, blackBrush, 20, 300);

        PDFStandardFont heiseiMinchoBoldItalic = new PDFStandardFont(PDFStandardFontFace.HEISEI_MINCHO_W3_BOLD_ITALIC, 16);
        pageCanvas.drawString("Heisei Mincho Bold Italic - サンプル日本語フォントデモテキスト.", heiseiMinchoBoldItalic, blackBrush, 20, 325);

        PDFStandardFont hanyangSystemsGothicMedium = new PDFStandardFont(PDFStandardFontFace.HANYANG_SYSTEMS_GOTHIC_MEDIUM, 16);
        pageCanvas.drawString("Hanyang Systems Gothic Medium - 샘플 한국어 글꼴 데모 텍스트.", hanyangSystemsGothicMedium, blackBrush, 20, 350);

        PDFStandardFont hanyangSystemsGothicMediumBold = new PDFStandardFont(PDFStandardFontFace.HANYANG_SYSTEMS_GOTHIC_MEDIUM_BOLD, 16);
        pageCanvas.drawString("Hanyang Systems Gothic Medium Bold - 샘플 한국어 글꼴 데모 텍스트.", hanyangSystemsGothicMediumBold, blackBrush, 20, 375);

        PDFStandardFont hanyangSystemsGothicMediumItalic = new PDFStandardFont(PDFStandardFontFace.HANYANG_SYSTEMS_GOTHIC_MEDIUM_ITALIC, 16);
        pageCanvas.drawString("Hanyang Systems Gothic Medium Italic - 샘플 한국어 글꼴 데모 텍스트.", hanyangSystemsGothicMediumItalic, blackBrush, 20, 400);

        PDFStandardFont hanyangSystemsGothicMediumBoldItalic = new PDFStandardFont(PDFStandardFontFace.HANYANG_SYSTEMS_GOTHIC_MEDIUM_BOLD_ITALIC, 16);
        pageCanvas.drawString("Hanyang Systems Gothic Medium Bold Italic - 샘플 한국어 글꼴 데모 텍스트.", hanyangSystemsGothicMediumBoldItalic, blackBrush, 20, 425);

        PDFStandardFont hanyangSystemsShinMyeongJoMedium = new PDFStandardFont(PDFStandardFontFace.HANYANG_SYSTEMS_SHIN_MYEONGJO_MEDIUM, 16);
        pageCanvas.drawString("Hanyang Systems Shin Myeong Jo Medium - 샘플 한국어 글꼴 데모 텍스트.", hanyangSystemsShinMyeongJoMedium, blackBrush, 20, 450);

        PDFStandardFont hanyangSystemsShinMyeongJoMediumBold = new PDFStandardFont(PDFStandardFontFace.HANYANG_SYSTEMS_SHIN_MYEONGJO_MEDIUM_BOLD, 16);
        pageCanvas.drawString("Hanyang Systems Shin Myeong Jo Medium Bold - 샘플 한국어 글꼴 데모 텍스트.", hanyangSystemsShinMyeongJoMediumBold, blackBrush, 20, 475);

        PDFStandardFont hanyangSystemsShinMyeongJoMediumItalic = new PDFStandardFont(PDFStandardFontFace.HANYANG_SYSTEMS_SHIN_MYEONGJO_MEDIUM_ITALIC, 16);
        pageCanvas.drawString("Hanyang Systems Shin Myeong Jo Medium Italic - 샘플 한국어 글꼴 데모 텍스트.", hanyangSystemsShinMyeongJoMediumItalic, blackBrush, 20, 500);

        PDFStandardFont hanyangSystemsShinMyeongJoMediumBoldItalic = new PDFStandardFont(PDFStandardFontFace.HANYANG_SYSTEMS_SHIN_MYEONGJO_MEDIUM_BOLD_ITALIC, 16);
        pageCanvas.drawString("Hanyang Systems Shin Myeong Jo Medium Bold Italic - 샘플 한국어 글꼴 데모 텍스트.", hanyangSystemsShinMyeongJoMediumBoldItalic, blackBrush, 20, 525);

        PDFStandardFont monotypeSungLight = new PDFStandardFont(PDFStandardFontFace.MONOTYPE_SUNGLIGHT, 16);
        pageCanvas.drawString("Monotype Sung Light - 中國字體樣本示範文本.", monotypeSungLight, blackBrush, 20, 550);

        PDFStandardFont monotypeSungLightBold = new PDFStandardFont(PDFStandardFontFace.MONOTYPE_SUNGLIGH_TBOLD, 16);
        pageCanvas.drawString("Monotype Sung Light Bold - 中國字體樣本示範文本.", monotypeSungLightBold, blackBrush, 20, 575);

        PDFStandardFont monotypeSungLightItalic = new PDFStandardFont(PDFStandardFontFace.MONOTYPE_SUNGLIGHT_ITALIC, 16);
        pageCanvas.drawString("Monotype Sung Light Italic - 中國字體樣本示範文本.", monotypeSungLightItalic, blackBrush, 20, 600);

        PDFStandardFont monotypeSungLightBoldItalic = new PDFStandardFont(PDFStandardFontFace.MONOTYPE_SUNGLIGHT_BOLD_ITALIC, 16);
        pageCanvas.drawString("Monotype Sung Light Bold Italic - 中國字體樣本示範文本.", monotypeSungLightBoldItalic, blackBrush, 20, 625);

        PDFStandardFont sinoTypeSongLight = new PDFStandardFont(PDFStandardFontFace.SINOTYPE_SONGLIGHT, 16);
        pageCanvas.drawString("Sino Type Song Light - 中国字体样本示范文本.", sinoTypeSongLight, blackBrush, 20, 650);

        PDFStandardFont sinoTypeSongLightBold = new PDFStandardFont(PDFStandardFontFace.SINOTYPE_SONGLIGHT_BOLD, 16);
        pageCanvas.drawString("Sino Type Song Light Bold - 中国字体样本示范文本.", sinoTypeSongLightBold, blackBrush, 20, 675);

        PDFStandardFont sinoTypeSongLightItalic = new PDFStandardFont(PDFStandardFontFace.SINOTYPE_SONGLIGHT_ITALIC, 16);
        pageCanvas.drawString("Sino Type Song Light Italic - 中国字体样本示范文本.", sinoTypeSongLightItalic, blackBrush, 20, 700);

        PDFStandardFont sinoTypeSongLightBoldItalic = new PDFStandardFont(PDFStandardFontFace.SINOTYPE_SONGLIGHT_BOLD_ITALIC, 16);
        pageCanvas.drawString("Sino Type Song Light Bold Italic - 中国字体样本示范文本.", sinoTypeSongLightBoldItalic, blackBrush, 20, 725);
    }
    
    public static void drawTrueTypeFonts(PDFPage page) {
        PDFStandardFont titleFont = new PDFStandardFont(PDFStandardFontFace.HELVETICA_BOLD, 22);
        PDFBrush blackBrush = new PDFBrush(new PDFRgbColor());
        PDFPageCanvas pageCanvas = page.getCanvas();

        pageCanvas.drawString("TrueType fonts", titleFont, blackBrush, 20, 50);
        pageCanvas.drawString("(when embedded they should be supported", titleFont, blackBrush, 20, 75);
        pageCanvas.drawString(" by any PDF viewer)", titleFont, blackBrush, 20, 100);

        PDFAnsiTrueTypeFont ansiVerdana = new PDFAnsiTrueTypeFont("..\\..\\SupportFiles\\verdana.ttf", 16, true);
        pageCanvas.drawString("Verdana - Ansi TrueType font", ansiVerdana, blackBrush, 20, 150);
        pageCanvas.drawString("Lorem ipsum dolor sit amet, consectetur adipiscing elit.", ansiVerdana, blackBrush, 20, 175);

        PDFUnicodeTrueTypeFont unicodeVerdana = new PDFUnicodeTrueTypeFont("..\\..\\SupportFiles\\verdana.ttf", 16, true);
        pageCanvas.drawString("Verdana - Unicode TrueType font", unicodeVerdana, blackBrush, 20, 225);

        pageCanvas.drawString("Russian - Пример русский текст демо шрифт.", unicodeVerdana, blackBrush, 20, 250);
        pageCanvas.drawString("Greek - Δείγμα ελληνικό κείμενο demo γραμματοσειράς.", unicodeVerdana, blackBrush, 20, 275);
    }

    public static void disableTextCopy(PDFPage page) {
        PDFStandardFont titleFont = new PDFStandardFont(PDFStandardFontFace.HELVETICA_BOLD, 22);
        PDFBrush blackBrush = new PDFBrush(new PDFRgbColor());
        PDFPageCanvas pageCanvas = page.getCanvas();

        pageCanvas.drawString("Draw text that cannot be copied and", titleFont, blackBrush, 20, 50);
        pageCanvas.drawString("pasted in another applications", titleFont, blackBrush, 20, 75);

        PDFUnicodeTrueTypeFont f1 = new PDFUnicodeTrueTypeFont("..\\..\\SupportFiles\\verdana.ttf", 16, true);
        pageCanvas.drawString("This text can be copied and pasted", f1, blackBrush, 20, 150);
        pageCanvas.drawString("Lorem ipsum dolor sit amet, consectetur adipiscing elit.", f1, blackBrush, 20, 175);

        PDFUnicodeTrueTypeFont f2 = new PDFUnicodeTrueTypeFont("..\\..\\SupportFiles\\verdana.ttf", 16, true);
        f2.setEnableTextCopy(false);
        pageCanvas.drawString("This text cannot be copied and pasted.", f2, blackBrush, 20, 225);
        pageCanvas.drawString("Praesent sed massa a est fringilla mattis. Aenean sit amet odio ac nunc.", f2, blackBrush, 20, 250);
    }

}
