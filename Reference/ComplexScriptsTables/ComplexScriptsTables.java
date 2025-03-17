import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.flowdocument.*;
import com.o2sol.pdf4java.graphics.*;
import com.o2sol.pdf4java.graphics.colors.PDFRgbColor;
import com.o2sol.pdf4java.graphics.fonts.truetype.*;
import com.o2sol.pdf4java.graphics.text.PDFTextDirection;

public class ComplexScriptsTables {
    public static void main(String[] args) {

        try {
            PDFFlowDocument document = new PDFFlowDocument();

            arabicFlowTable(document);
            bengaliFlowTable(document);
            devanagariFlowTable(document);
            gujaratiFlowTable(document);
            gurmukhiFlowTable(document);
            hebrewFlowTable(document);
            kannadaFlowTable(document);
            kashmiriFlowTable(document);
            khmerFlowTable(document);
            kurdishSoraniFlowTable(document);
            laoFlowTable(document);
            malayalamFlowTable(document);
            myanmarBurmeseFlowTable(document);
            oriyaFlowTable(document);
            persianFlowTable(document);
            sindhiFlowTable(document);
            tamilFlowTable(document);
            teluguFlowTable(document);
            thaiFlowTable(document);
            urduNastaliqFlowTable(document);
            uyghurFlowTable(document);

            document.save("ComplexScriptsTables.pdf");

            System.out.println("PDF document has been saved with success");
        } catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }
    
    public static void arabicFlowTable(PDFFlowDocument document) {
        PDFBrush lightGrayBrush = new PDFBrush(PDFRgbColor.LIGHT_GRAY);
        PDFTrueTypeFontFeatures fontFeatures = new PDFTrueTypeFontFeatures();
        fontFeatures.setEnableComplexScripts(true);
        PDFUnicodeTrueTypeFont arialFont = new PDFUnicodeTrueTypeFont("..\\..\\SupportFiles\\arial.ttf", 12, true, fontFeatures);

        PDFFlowTextContent textContent = new PDFFlowTextContent("Arabic", arialFont);
        document.addContent(textContent);

        PDFFlowTableContent table = new PDFFlowTableContent(3);
        table.setMinRowHeight(20);
        PDFFlowTableStringCell defaultCell = new PDFFlowTableStringCell();
        defaultCell.setFont(arialFont);
        defaultCell.setTextDirection(PDFTextDirection.RIGHT_TO_LEFT);
        defaultCell.setHorizontalAlign(PDFGraphicAlign.FAR);
        defaultCell.setVerticalAlign(PDFGraphicAlign.CENTER);
        defaultCell.setInnerMargins(new PDFFlowContentMargins(2));
        table.setDefaultCell(defaultCell);

        PDFFlowTableRow row = table.getRows().addRowWithCells(new String[] { "ألأبجدية ٱلعربية", "سَأَلَ", "مأمور" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "معنى", "حَتَّى", "قلوب" });
        row = table.getRows().addRowWithCells(new String[] { "تاريخ", "رَسْمِيًا", "العَرَبِيَّة" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "أَفْعًى", "عَادَةً", "عَيْن" });
        row = table.getRows().addRowWithCells(new String[] { "اِنْتِبَاه", "اَلْمُدِير", "مَا ٱسْمُكَ" });
        row.setBackground(lightGrayBrush);
        document.addContent(table);
    }

    public static void bengaliFlowTable(PDFFlowDocument document) {
        document.startNewPage();

        PDFBrush lightGrayBrush = new PDFBrush(PDFRgbColor.LIGHT_GRAY);
        PDFTrueTypeFontFeatures fontFeatures = new PDFTrueTypeFontFeatures();
        fontFeatures.setEnableComplexScripts(true);
        PDFUnicodeTrueTypeFont bengaliFont = new PDFUnicodeTrueTypeFont("..\\..\\SupportFiles\\NotoSansBengali-Regular.ttf", 12, true, fontFeatures);

        PDFFlowTextContent textContent = new PDFFlowTextContent("Bengali", bengaliFont);
        document.addContent(textContent);

        PDFFlowTableContent table = new PDFFlowTableContent(3);
        table.setMinRowHeight(20);
        PDFFlowTableStringCell defaultCell = new PDFFlowTableStringCell();
        defaultCell.setFont(bengaliFont);
        defaultCell.setVerticalAlign(PDFGraphicAlign.CENTER);
        defaultCell.setInnerMargins(new PDFFlowContentMargins(2));
        table.setDefaultCell(defaultCell);

        PDFFlowTableRow row = table.getRows().addRowWithCells(new String[] { "বাংলা বর্ণমালা", "বাংলা লিপি", "করা" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "কড়া", "করতাল", "গরম" });
        row = table.getRows().addRowWithCells(new String[] { "ব্যাখ্যা", "সন্ধ্যা", "ব্যথা" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "অ্যাটর্নি", "এ্যাডভোকেট", "বাকি" });
        row = table.getRows().addRowWithCells(new String[] { "শুনঽঽঽ", "হাঁপান", "কেউ" });
        row.setBackground(lightGrayBrush);
        document.addContent(table);
    }

    public static void devanagariFlowTable(PDFFlowDocument document) {
        document.startNewPage();

        PDFBrush lightGrayBrush = new PDFBrush(PDFRgbColor.LIGHT_GRAY);
        PDFTrueTypeFontFeatures fontFeatures = new PDFTrueTypeFontFeatures();
        fontFeatures.setEnableComplexScripts(true);
        PDFUnicodeTrueTypeFont devanagariFont = new PDFUnicodeTrueTypeFont("..\\..\\SupportFiles\\NotoSansDevanagari-Regular.ttf", 12, true, fontFeatures);

        PDFFlowTextContent textContent = new PDFFlowTextContent("Devanagari", devanagariFont);
        document.addContent(textContent);

        PDFFlowTableContent table = new PDFFlowTableContent(3);
        table.setMinRowHeight(20);
        PDFFlowTableStringCell defaultCell = new PDFFlowTableStringCell();
        defaultCell.setFont(devanagariFont);
        defaultCell.setVerticalAlign(PDFGraphicAlign.CENTER);
        defaultCell.setInnerMargins(new PDFFlowContentMargins(2));
        table.setDefaultCell(defaultCell);

        PDFFlowTableRow row = table.getRows().addRowWithCells(new String[] { "देवनागरी", "शक्ति", "वस्तु" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "कहना", "पकवान", "मुँह" });
        row = table.getRows().addRowWithCells(new String[] { "साँप", "ऑफ़िस", "अँगरेज़ी" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "योग्य", "राष्ट्र", "ख़ारीदारी" });
        row = table.getRows().addRowWithCells(new String[] { "हिंदी", "पंजाब", "दुःखी" });
        row.setBackground(lightGrayBrush);
        document.addContent(table);
    }

    public static void gujaratiFlowTable(PDFFlowDocument document) {
        document.startNewPage();

        PDFBrush lightGrayBrush = new PDFBrush(PDFRgbColor.LIGHT_GRAY);
        PDFTrueTypeFontFeatures fontFeatures = new PDFTrueTypeFontFeatures();
        fontFeatures.setEnableComplexScripts(true);
        PDFUnicodeTrueTypeFont gujaratiFont = new PDFUnicodeTrueTypeFont("..\\..\\SupportFiles\\NotoSansGujarati-Regular.ttf", 12, true, fontFeatures);

        PDFFlowTextContent textContent = new PDFFlowTextContent("Gujarati", gujaratiFont);
        document.addContent(textContent);

        PDFFlowTableContent table = new PDFFlowTableContent(3);
        table.setMinRowHeight(20);
        PDFFlowTableStringCell defaultCell = new PDFFlowTableStringCell();
        defaultCell.setFont(gujaratiFont);
        defaultCell.setVerticalAlign(PDFGraphicAlign.CENTER);
        defaultCell.setInnerMargins(new PDFFlowContentMargins(2));
        table.setDefaultCell(defaultCell);

        PDFFlowTableRow row = table.getRows().addRowWithCells(new String[] { "ગુજરાતી લિપિ", "ઘર", "ઘરપર" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "ઘરકામ", "પકડ઼ે", "વરસાદ" });
        row = table.getRows().addRowWithCells(new String[] { "ટિકિટ", "રૂપિયો", "ધૂય" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "કૉફી", "થાળી", "પપૈયું" });
        row = table.getRows().addRowWithCells(new String[] { "ચૌદ", "ઓક્ટોપસ", "ઓળો" });
        row.setBackground(lightGrayBrush);
        document.addContent(table);
    }

    public static void gurmukhiFlowTable(PDFFlowDocument document) {
        document.startNewPage();

        PDFBrush lightGrayBrush = new PDFBrush(PDFRgbColor.LIGHT_GRAY);
        PDFTrueTypeFontFeatures fontFeatures = new PDFTrueTypeFontFeatures();
        fontFeatures.setEnableComplexScripts(true);
        PDFUnicodeTrueTypeFont gurmukhiFont = new PDFUnicodeTrueTypeFont("..\\..\\SupportFiles\\NotoSansGurmukhi-Regular.ttf", 12, true, fontFeatures);

        PDFFlowTextContent textContent = new PDFFlowTextContent("Gurmukhi", gurmukhiFont);
        document.addContent(textContent);

        PDFFlowTableContent table = new PDFFlowTableContent(3);
        table.setMinRowHeight(20);
        PDFFlowTableStringCell defaultCell = new PDFFlowTableStringCell();
        defaultCell.setFont(gurmukhiFont);
        defaultCell.setVerticalAlign(PDFGraphicAlign.CENTER);
        defaultCell.setInnerMargins(new PDFFlowContentMargins(2));
        table.setDefaultCell(defaultCell);

        PDFFlowTableRow row = table.getRows().addRowWithCells(new String[] { "ਗੁਰਮੁਖੀ", "ਜਾਲ਼", "ਓਹਾਇਓ" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "ਉਤਸੁਕ", "ਕਿਹੜਾ", "ਕੁਹੜਾ" });
        row = table.getRows().addRowWithCells(new String[] { "ਕਹਿਣਾ", "ਵਹੁਟੀ", "ਮੂੰਡਾ" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "ਸ਼ਾਂਤ", "ਲੰਮੀ", "ਬਤਾਊਂ" });
        row = table.getRows().addRowWithCells(new String[] { "ਐਨਕ", "ਵੀਕਐਂਡ", "ਆਦਮੀ" });
        row.setBackground(lightGrayBrush);
        document.addContent(table);
    }

    public static void hebrewFlowTable(PDFFlowDocument document) {
        document.startNewPage();

        PDFBrush lightGrayBrush = new PDFBrush(PDFRgbColor.LIGHT_GRAY);
        PDFTrueTypeFontFeatures fontFeatures = new PDFTrueTypeFontFeatures();
        fontFeatures.setEnableComplexScripts(true);
        PDFUnicodeTrueTypeFont hebrewFont = new PDFUnicodeTrueTypeFont("..\\..\\SupportFiles\\arial.ttf", 12, true, fontFeatures);

        PDFFlowTextContent textContent = new PDFFlowTextContent("Hebrew", hebrewFont);
        document.addContent(textContent);

        PDFFlowTableContent table = new PDFFlowTableContent(3);
        table.setMinRowHeight(20);
        PDFFlowTableStringCell defaultCell = new PDFFlowTableStringCell();
        defaultCell.setFont(hebrewFont);
        defaultCell.setTextDirection(PDFTextDirection.RIGHT_TO_LEFT);
        defaultCell.setHorizontalAlign(PDFGraphicAlign.FAR);
        defaultCell.setVerticalAlign(PDFGraphicAlign.CENTER);
        defaultCell.setInnerMargins(new PDFFlowContentMargins(2));
        table.setDefaultCell(defaultCell);

        PDFFlowTableRow row = table.getRows().addRowWithCells(new String[] { "אָלֶף־בֵּית עִבְרִי", "לִסְגֹּר", "אַבְטָחָה" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "אמץ", "אויר", "חלקה" });
        row = table.getRows().addRowWithCells(new String[] { "אישה", "אלוה", "אֵזוֹר" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "אוֹתְיוֹם", "עֵץ", "עוֹלָם" });
        row = table.getRows().addRowWithCells(new String[] { "בעיה", "גַּבְרִיאֵל", "אַנְגְּלִית" });
        row.setBackground(lightGrayBrush);
        document.addContent(table);
    }

    public static void kannadaFlowTable(PDFFlowDocument document) {
        document.startNewPage();

        PDFBrush lightGrayBrush = new PDFBrush(PDFRgbColor.LIGHT_GRAY);
        PDFTrueTypeFontFeatures fontFeatures = new PDFTrueTypeFontFeatures();
        fontFeatures.setEnableComplexScripts(true);
        PDFUnicodeTrueTypeFont kannadaFont = new PDFUnicodeTrueTypeFont("..\\..\\SupportFiles\\NotoSansKannada-Regular.ttf", 12, true, fontFeatures);

        PDFFlowTextContent textContent = new PDFFlowTextContent("Kannada", kannadaFont);
        document.addContent(textContent);

        PDFFlowTableContent table = new PDFFlowTableContent(3);
        table.setMinRowHeight(20);
        PDFFlowTableStringCell defaultCell = new PDFFlowTableStringCell();
        defaultCell.setFont(kannadaFont);
        defaultCell.setVerticalAlign(PDFGraphicAlign.CENTER);
        defaultCell.setInnerMargins(new PDFFlowContentMargins(2));
        table.setDefaultCell(defaultCell);

        PDFFlowTableRow row = table.getRows().addRowWithCells(new String[] { "ಎಲ್ಲಾ", "ಮಾನವರೂ", "ಸ್ವತಂತ್ರರಾಗಿಯೇ" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "ಜನಿಸಿದ್ದಾರೆ", "ಹಾಗೂ", "ಘನತೆ ಮತ್ತು" });
        row = table.getRows().addRowWithCells(new String[] { "ಹಕ್ಕುಗಳಲ್ಲಿ", "ಸಮಾನರಾಗಿದ್ದಾರೆ", "ವಿವೇಕ ಮತ್ತು" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "ಅಂತಃಕರಣಗಳನ್ನು", "ಪಡೆದವರಾದ್ದರಿಂದ", "ಅವರು" });
        row = table.getRows().addRowWithCells(new String[] { "ಪರಸ್ಪರ", "ಸಹೋದರ", "ಭಾವದಿಂದ" });
        row.setBackground(lightGrayBrush);
        document.addContent(table);
    }

    public static void kashmiriFlowTable(PDFFlowDocument document) {
        document.startNewPage();

        PDFBrush lightGrayBrush = new PDFBrush(PDFRgbColor.LIGHT_GRAY);
        PDFTrueTypeFontFeatures fontFeatures = new PDFTrueTypeFontFeatures();
        fontFeatures.setEnableComplexScripts(true);
        PDFUnicodeTrueTypeFont kashmiriFont = new PDFUnicodeTrueTypeFont("..\\..\\SupportFiles\\NotoNastaliqUrdu-Regular.ttf", 12, true, fontFeatures);

        PDFFlowTextContent textContent = new PDFFlowTextContent("Kashmiri", kashmiriFont);
        document.addContent(textContent);

        PDFFlowTableContent table = new PDFFlowTableContent(3);
        table.setMinRowHeight(40);
        PDFFlowTableStringCell defaultCell = new PDFFlowTableStringCell();
        defaultCell.setFont(kashmiriFont);
        defaultCell.setTextDirection(PDFTextDirection.RIGHT_TO_LEFT);
        defaultCell.setHorizontalAlign(PDFGraphicAlign.FAR);
        defaultCell.setVerticalAlign(PDFGraphicAlign.CENTER);
        defaultCell.setInnerMargins(new PDFFlowContentMargins(2));
        table.setDefaultCell(defaultCell);

        PDFFlowTableRow row = table.getRows().addRowWithCells(new String[] { "کٲشُر", "اَنْگریٖزی", "پَنہٕ پونْپُر " });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "اوٚنْگٕج", "اِنسان", "آتھوار" });
        row = table.getRows().addRowWithCells(new String[] { "عَکٕس", "عٲقٟل", "کرْٕم" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "وانْدُر", "کانْتُر", "بؠنتھٕر" });
        row = table.getRows().addRowWithCells(new String[] { "پونؠ", "بادَم", "کیْوٚم" });
        row.setBackground(lightGrayBrush);
        document.addContent(table);
    }

    public static void khmerFlowTable(PDFFlowDocument document) {
        document.startNewPage();

        PDFBrush lightGrayBrush = new PDFBrush(PDFRgbColor.LIGHT_GRAY);
        PDFTrueTypeFontFeatures fontFeatures = new PDFTrueTypeFontFeatures();
        fontFeatures.setEnableComplexScripts(true);
        PDFUnicodeTrueTypeFont khmerFont = new PDFUnicodeTrueTypeFont("..\\..\\SupportFiles\\NotoSansKhmer-Regular.ttf", 12, true, fontFeatures);

        PDFFlowTextContent textContent = new PDFFlowTextContent("Khmer", khmerFont);
        document.addContent(textContent);

        PDFFlowTableContent table = new PDFFlowTableContent(3);
        table.setMinRowHeight(30);
        PDFFlowTableStringCell defaultCell = new PDFFlowTableStringCell();
        defaultCell.setFont(khmerFont);
        defaultCell.setVerticalAlign(PDFGraphicAlign.CENTER);
        defaultCell.setInnerMargins(new PDFFlowContentMargins(2));
        table.setDefaultCell(defaultCell);

        PDFFlowTableRow row = table.getRows().addRowWithCells(new String[] { "អក្សរខ្មែរ", "ប្រយ័ត្ន", "សប្ដាហ៍" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "រេហ៍ពល", "កេរ្តិ៍ ", "បរិបូណ៌" });
        row = table.getRows().addRowWithCells(new String[] { "អាត្មន៑", "កិរិយា", "ក៏" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "ដម", "ពាម", "ខាត់" });
        row = table.getRows().addRowWithCells(new String[] { "ហ្វ៊ីស៉ិក", "ចង្អៀត", "ក្អាត់" });
        row.setBackground(lightGrayBrush);
        document.addContent(table);
    }

    public static void kurdishSoraniFlowTable(PDFFlowDocument document) {
        document.startNewPage();

        PDFBrush lightGrayBrush = new PDFBrush(PDFRgbColor.LIGHT_GRAY);
        PDFTrueTypeFontFeatures fontFeatures = new PDFTrueTypeFontFeatures();
        fontFeatures.setEnableComplexScripts(true);
        PDFUnicodeTrueTypeFont kurdishSoraniFont = new PDFUnicodeTrueTypeFont("..\\..\\SupportFiles\\arial.ttf", 12, true, fontFeatures);

        PDFFlowTextContent textContent = new PDFFlowTextContent("KurdishSorani", kurdishSoraniFont);
        document.addContent(textContent);

        PDFFlowTableContent table = new PDFFlowTableContent(3);
        table.setMinRowHeight(20);
        PDFFlowTableStringCell defaultCell = new PDFFlowTableStringCell();
        defaultCell.setFont(kurdishSoraniFont);
        defaultCell.setTextDirection(PDFTextDirection.RIGHT_TO_LEFT);
        defaultCell.setHorizontalAlign(PDFGraphicAlign.FAR);
        defaultCell.setVerticalAlign(PDFGraphicAlign.CENTER);
        defaultCell.setInnerMargins(new PDFFlowContentMargins(2));
        table.setDefaultCell(defaultCell);

        PDFFlowTableRow row = table.getRows().addRowWithCells(new String[] { "زمانێ سۆرانی", "مرۆڤ", "سفر" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "وشک", "مامر", "چووی" });
        row = table.getRows().addRowWithCells(new String[] { "بنەوشە", "قەیچی", "شەو" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "ئافرەت", "ئەمڕۆ", "ئێرانی" });
        row = table.getRows().addRowWithCells(new String[] { "زانستگە", "ئەمڕۆ", "ئاشتی" });
        row.setBackground(lightGrayBrush);
        document.addContent(table);
    }

    public static void laoFlowTable(PDFFlowDocument document) {
        document.startNewPage();

        PDFBrush lightGrayBrush = new PDFBrush(PDFRgbColor.LIGHT_GRAY);
        PDFTrueTypeFontFeatures fontFeatures = new PDFTrueTypeFontFeatures();
        fontFeatures.setEnableComplexScripts(true);
        PDFUnicodeTrueTypeFont laoFont = new PDFUnicodeTrueTypeFont("..\\..\\SupportFiles\\NotoSerifLao-Regular.ttf", 12, true, fontFeatures);

        PDFFlowTextContent textContent = new PDFFlowTextContent("Lao", laoFont);
        document.addContent(textContent);

        PDFFlowTableContent table = new PDFFlowTableContent(3);
        table.setMinRowHeight(20);
        PDFFlowTableStringCell defaultCell = new PDFFlowTableStringCell();
        defaultCell.setFont(laoFont);
        defaultCell.setVerticalAlign(PDFGraphicAlign.CENTER);
        defaultCell.setInnerMargins(new PDFFlowContentMargins(2));
        table.setDefaultCell(defaultCell);

        PDFFlowTableRow row = table.getRows().addRowWithCells(new String[] { "ອັກສອນລາວ", "ລະດັບ", "ໂຕະ" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "ໂຕ", "ຫິມະ", "ເຜິ້ງ" });
        row = table.getRows().addRowWithCells(new String[] { "ທະເລ", "ແມັດ", "ເພາະ" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "ເຄີຍ", "ໂພ່ນ", "ເຮືອນ" });
        row = table.getRows().addRowWithCells(new String[] { "ຄວາຍ", "ຊາຽ", "ຈອກ" });
        row.setBackground(lightGrayBrush);
        document.addContent(table);
    }

    public static void malayalamFlowTable(PDFFlowDocument document) {
        document.startNewPage();

        PDFBrush lightGrayBrush = new PDFBrush(PDFRgbColor.LIGHT_GRAY);
        PDFTrueTypeFontFeatures fontFeatures = new PDFTrueTypeFontFeatures();
        fontFeatures.setEnableComplexScripts(true);
        PDFUnicodeTrueTypeFont malayalamFont = new PDFUnicodeTrueTypeFont("..\\..\\SupportFiles\\NotoSerifMalayalam-Regular.ttf", 12, true, fontFeatures);

        PDFFlowTextContent textContent = new PDFFlowTextContent("Malayalam", malayalamFont);
        document.addContent(textContent);

        PDFFlowTableContent table = new PDFFlowTableContent(3);
        table.setMinRowHeight(20);
        PDFFlowTableStringCell defaultCell = new PDFFlowTableStringCell();
        defaultCell.setFont(malayalamFont);
        defaultCell.setVerticalAlign(PDFGraphicAlign.CENTER);
        defaultCell.setInnerMargins(new PDFFlowContentMargins(2));
        table.setDefaultCell(defaultCell);

        PDFFlowTableRow row = table.getRows().addRowWithCells(new String[] { "മലയാളലിപി", "പാലു്", "കട്ടയാക്" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "ഐശീല്ം", "എ്ന്നാ", "എങ്ങനെ" });
        row = table.getRows().addRowWithCells(new String[] { "നേരെ", "ടൗൺ", "ചൈന" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "സൗന്ദര്യം", "എല്ലാ", "ഓടുക" });
        row = table.getRows().addRowWithCells(new String[] { "സിമേഈ", "ഋഷി", "വില്ലൻ" });
        row.setBackground(lightGrayBrush);
        document.addContent(table);
    }

    public static void myanmarBurmeseFlowTable(PDFFlowDocument document) {
        document.startNewPage();

        PDFBrush lightGrayBrush = new PDFBrush(PDFRgbColor.LIGHT_GRAY);
        PDFTrueTypeFontFeatures fontFeatures = new PDFTrueTypeFontFeatures();
        fontFeatures.setEnableComplexScripts(true);
        PDFUnicodeTrueTypeFont myanmarBurmeseFont = new PDFUnicodeTrueTypeFont("..\\..\\SupportFiles\\NotoSansMyanmarLatin-Regular.ttf", 12, true, fontFeatures);

        PDFFlowTextContent textContent = new PDFFlowTextContent("Myanmar Burmese", myanmarBurmeseFont);
        document.addContent(textContent);

        PDFFlowTableContent table = new PDFFlowTableContent(3);
        table.setMinRowHeight(40);
        PDFFlowTableStringCell defaultCell = new PDFFlowTableStringCell();
        defaultCell.setFont(myanmarBurmeseFont);
        defaultCell.setVerticalAlign(PDFGraphicAlign.CENTER);
        defaultCell.setInnerMargins(new PDFFlowContentMargins(2));
        table.setDefaultCell(defaultCell);

        PDFFlowTableRow row = table.getRows().addRowWithCells(new String[] { "မြန်မာအက္ခရာ", "နှစ်", "ဆိုး" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "ဆိုင်", "နွမ်း", "စံပယ်" });
        row = table.getRows().addRowWithCells(new String[] { "မြေပုံ", "ထောပတ်သီး", "ပျော့တယ်" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "ကြောင်", "ပျော်တယ်", "ဩဂုတ်" });
        row = table.getRows().addRowWithCells(new String[] { "ပေါ့တယ်", "ပြတင်းပေါက်", "သန်းခေါင်" });
        row.setBackground(lightGrayBrush);
        document.addContent(table);
    }

    public static void oriyaFlowTable(PDFFlowDocument document) {
        document.startNewPage();

        PDFBrush lightGrayBrush = new PDFBrush(PDFRgbColor.LIGHT_GRAY);
        PDFTrueTypeFontFeatures fontFeatures = new PDFTrueTypeFontFeatures();
        fontFeatures.setEnableComplexScripts(true);
        PDFUnicodeTrueTypeFont oriyaFont = new PDFUnicodeTrueTypeFont("..\\..\\SupportFiles\\NotoSansOriya-Regular.ttf", 12, true, fontFeatures);

        PDFFlowTextContent textContent = new PDFFlowTextContent("Oriya", oriyaFont);
        document.addContent(textContent);

        PDFFlowTableContent table = new PDFFlowTableContent(3);
        table.setMinRowHeight(20);
        PDFFlowTableStringCell defaultCell = new PDFFlowTableStringCell();
        defaultCell.setFont(oriyaFont);
        defaultCell.setVerticalAlign(PDFGraphicAlign.CENTER);
        defaultCell.setInnerMargins(new PDFFlowContentMargins(2));
        table.setDefaultCell(defaultCell);

        PDFFlowTableRow row = table.getRows().addRowWithCells(new String[] { "ଓଡ଼ିଆ ଅକ୍ଷର", "ଅଣ୍ଡା", "ଆଠ" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "ଇଟା", "ଗାଈ", "ଚଉଡା" });
        row = table.getRows().addRowWithCells(new String[] { "ଐରାଵତ", "ଔଷଧ", "ଖାଇବା" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "ଶୁଙ୍ଘିବା", "ରାଜ୍ଞୀ", "ଠିକ" });
        row = table.getRows().addRowWithCells(new String[] { "କାରଣ", "ବାନ୍ଧିବା", "ବଲ୍ଲରି" });
        row.setBackground(lightGrayBrush);
        document.addContent(table);
    }

    public static void persianFlowTable(PDFFlowDocument document) {
        document.startNewPage();

        PDFBrush lightGrayBrush = new PDFBrush(PDFRgbColor.LIGHT_GRAY);
        PDFTrueTypeFontFeatures fontFeatures = new PDFTrueTypeFontFeatures();
        fontFeatures.setEnableComplexScripts(true);
        PDFUnicodeTrueTypeFont persianFont = new PDFUnicodeTrueTypeFont("..\\..\\SupportFiles\\arial.ttf", 12, true, fontFeatures);

        PDFFlowTextContent textContent = new PDFFlowTextContent("Persian", persianFont);
        document.addContent(textContent);

        PDFFlowTableContent table = new PDFFlowTableContent(3);
        table.setMinRowHeight(20);
        PDFFlowTableStringCell defaultCell = new PDFFlowTableStringCell();
        defaultCell.setFont(persianFont);
        defaultCell.setTextDirection(PDFTextDirection.RIGHT_TO_LEFT);
        defaultCell.setHorizontalAlign(PDFGraphicAlign.FAR);
        defaultCell.setVerticalAlign(PDFGraphicAlign.CENTER);
        defaultCell.setInnerMargins(new PDFFlowContentMargins(2));
        table.setDefaultCell(defaultCell);

        PDFFlowTableRow row = table.getRows().addRowWithCells(new String[] { "الفبای فارسی", "لزوماً", "اصلاً" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "گازِ طبیعی", "نشانهِٔ سَجاوَندی", "زئوس" });
        row = table.getRows().addRowWithCells(new String[] { "سؤال", "اومدن", "ایرانی" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "معنوی", "عربی", "فائده" });
        row = table.getRows().addRowWithCells(new String[] { "مؤثر", "مسئول", "سوئیس" });
        row.setBackground(lightGrayBrush);
        document.addContent(table);
    }

    public static void sindhiFlowTable(PDFFlowDocument document) {
        document.startNewPage();

        PDFBrush lightGrayBrush = new PDFBrush(PDFRgbColor.LIGHT_GRAY);
        PDFTrueTypeFontFeatures fontFeatures = new PDFTrueTypeFontFeatures();
        fontFeatures.setEnableComplexScripts(true);
        PDFUnicodeTrueTypeFont sindhiFont = new PDFUnicodeTrueTypeFont("..\\..\\SupportFiles\\arial.ttf", 12, true, fontFeatures);

        PDFFlowTextContent textContent = new PDFFlowTextContent("Sindhi", sindhiFont);
        document.addContent(textContent);

        PDFFlowTableContent table = new PDFFlowTableContent(3);
        table.setMinRowHeight(20);
        PDFFlowTableStringCell defaultCell = new PDFFlowTableStringCell();
        defaultCell.setFont(sindhiFont);
        defaultCell.setTextDirection(PDFTextDirection.RIGHT_TO_LEFT);
        defaultCell.setHorizontalAlign(PDFGraphicAlign.FAR);
        defaultCell.setVerticalAlign(PDFGraphicAlign.CENTER);
        defaultCell.setInnerMargins(new PDFFlowContentMargins(2));
        table.setDefaultCell(defaultCell);

        PDFFlowTableRow row = table.getRows().addRowWithCells(new String[] { "سنڌي", "لوڻ", "قلعو" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "برابر", "متان", "شينهن" });
        row = table.getRows().addRowWithCells(new String[] { "شَڪَرِ", "شُڪْرُ", "شُڪُرُ" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "پَنئُن", "پڙھڻ", "لاهور" });
        row = table.getRows().addRowWithCells(new String[] { "ورسپت", "ڇٽڻ", "ڳئون" });
        row.setBackground(lightGrayBrush);
        document.addContent(table);
    }

    public static void tamilFlowTable(PDFFlowDocument document) {
        document.startNewPage();

        PDFBrush lightGrayBrush = new PDFBrush(PDFRgbColor.LIGHT_GRAY);
        PDFTrueTypeFontFeatures fontFeatures = new PDFTrueTypeFontFeatures();
        fontFeatures.setEnableComplexScripts(true);
        PDFUnicodeTrueTypeFont tamilFont = new PDFUnicodeTrueTypeFont("..\\..\\SupportFiles\\NotoSansTamil-Regular.ttf", 12, true, fontFeatures);

        PDFFlowTextContent textContent = new PDFFlowTextContent("Tamil", tamilFont);
        document.addContent(textContent);

        PDFFlowTableContent table = new PDFFlowTableContent(3);
        table.setMinRowHeight(20);
        PDFFlowTableStringCell defaultCell = new PDFFlowTableStringCell();
        defaultCell.setFont(tamilFont);
        defaultCell.setVerticalAlign(PDFGraphicAlign.CENTER);
        defaultCell.setInnerMargins(new PDFFlowContentMargins(2));
        table.setDefaultCell(defaultCell);

        PDFFlowTableRow row = table.getRows().addRowWithCells(new String[] { "தமிழ்", "மனிதப்", "கிரி" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "கீரி", "குடம்", "அழகு" });
        row = table.getRows().addRowWithCells(new String[] { "கூடம்", "கெடு", "கொடு" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "கௌதாரி", "ஊது", "ஔகாரம்" });
        row = table.getRows().addRowWithCells(new String[] { "ஆண்", "ஒன்று", "பெரீஇஇய" });
        row.setBackground(lightGrayBrush);
        document.addContent(table);
    }

    public static void teluguFlowTable(PDFFlowDocument document) {
        document.startNewPage();

        PDFBrush lightGrayBrush = new PDFBrush(PDFRgbColor.LIGHT_GRAY);
        PDFTrueTypeFontFeatures fontFeatures = new PDFTrueTypeFontFeatures();
        fontFeatures.setEnableComplexScripts(true);
        PDFUnicodeTrueTypeFont teluguFont = new PDFUnicodeTrueTypeFont("..\\..\\SupportFiles\\NotoSansTelugu-Regular.ttf", 12, true, fontFeatures);

        PDFFlowTextContent textContent = new PDFFlowTextContent("Telugu", teluguFont);
        document.addContent(textContent);

        PDFFlowTableContent table = new PDFFlowTableContent(3);
        table.setMinRowHeight(20);
        PDFFlowTableStringCell defaultCell = new PDFFlowTableStringCell();
        defaultCell.setFont(teluguFont);
        defaultCell.setVerticalAlign(PDFGraphicAlign.CENTER);
        defaultCell.setInnerMargins(new PDFFlowContentMargins(2));
        table.setDefaultCell(defaultCell);

        PDFFlowTableRow row = table.getRows().addRowWithCells(new String[] { "తెలుగు లిపి", "గద్ది", "గోరు" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "బూడిద", "గొంతునొప్పి", "గాలి" });
        row = table.getRows().addRowWithCells(new String[] { "పిట్ట", "నైఋతి", "ఔషధము" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "ఊదా", "ఈక", "ఝంఝ" });
        row = table.getRows().addRowWithCells(new String[] { "రేణువు", "అంగము", "సింహ" });
        row.setBackground(lightGrayBrush);
        document.addContent(table);
    }

    public static void thaiFlowTable(PDFFlowDocument document) {
        document.startNewPage();

        PDFBrush lightGrayBrush = new PDFBrush(PDFRgbColor.LIGHT_GRAY);
        PDFTrueTypeFontFeatures fontFeatures = new PDFTrueTypeFontFeatures();
        fontFeatures.setEnableComplexScripts(true);
        PDFUnicodeTrueTypeFont thaiFont = new PDFUnicodeTrueTypeFont("..\\..\\SupportFiles\\NotoSerifThai-Regular.ttf", 12, true, fontFeatures);

        PDFFlowTextContent textContent = new PDFFlowTextContent("Thai", thaiFont);
        document.addContent(textContent);

        PDFFlowTableContent table = new PDFFlowTableContent(3);
        table.setMinRowHeight(20);
        PDFFlowTableStringCell defaultCell = new PDFFlowTableStringCell();
        defaultCell.setFont(thaiFont);
        defaultCell.setVerticalAlign(PDFGraphicAlign.CENTER);
        defaultCell.setInnerMargins(new PDFFlowContentMargins(2));
        table.setDefaultCell(defaultCell);

        PDFFlowTableRow row = table.getRows().addRowWithCells(new String[] { "อักษรไทย", "ถนน", "นคร" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "เมฆ", "เกอะ", "เมีย" });
        row = table.getRows().addRowWithCells(new String[] { "เขียว", "เรื่อย", "จั่วโมง" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "ควาย", "แพง", "น้ำแข็ง" });
        row = table.getRows().addRowWithCells(new String[] { "ซ็อกเก็ต", "เจี๊ยะ", "เฌอ" });
        row.setBackground(lightGrayBrush);
        document.addContent(table);
    }

    public static void urduNastaliqFlowTable(PDFFlowDocument document) {
        document.startNewPage();

        PDFBrush lightGrayBrush = new PDFBrush(PDFRgbColor.LIGHT_GRAY);
        PDFTrueTypeFontFeatures fontFeatures = new PDFTrueTypeFontFeatures();
        fontFeatures.setEnableComplexScripts(true);
        PDFUnicodeTrueTypeFont urduNastaliqFont = new PDFUnicodeTrueTypeFont("..\\..\\SupportFiles\\NotoNastaliqUrdu-Regular.ttf", 12, true, fontFeatures);

        PDFFlowTextContent textContent = new PDFFlowTextContent("Urdu Nastaliq", urduNastaliqFont);
        document.addContent(textContent);

        PDFFlowTableContent table = new PDFFlowTableContent(3);
        table.setMinRowHeight(40);
        PDFFlowTableStringCell defaultCell = new PDFFlowTableStringCell();
        defaultCell.setFont(urduNastaliqFont);
        defaultCell.setTextDirection(PDFTextDirection.RIGHT_TO_LEFT);
        defaultCell.setHorizontalAlign(PDFGraphicAlign.FAR);
        defaultCell.setVerticalAlign(PDFGraphicAlign.CENTER);
        defaultCell.setInnerMargins(new PDFFlowContentMargins(2));
        table.setDefaultCell(defaultCell);

        PDFFlowTableRow row = table.getRows().addRowWithCells(new String[] { "اُردُو حُرُوفِ تَہَجِّی", "شَکتی", "وَستُو" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "کَہنا", "پکوان", "ورت" });
        row = table.getRows().addRowWithCells(new String[] { "نہایت", "کھدیوت", "چای" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "روئے زمین", "گائے", "کیسا" });
        row = table.getRows().addRowWithCells(new String[] { "ایک", "شِعر", "شُعلہ" });
        row.setBackground(lightGrayBrush);
        document.addContent(table);
    }

    public static void uyghurFlowTable(PDFFlowDocument document) {
        document.startNewPage();

        PDFBrush lightGrayBrush = new PDFBrush(PDFRgbColor.LIGHT_GRAY);
        PDFTrueTypeFontFeatures fontFeatures = new PDFTrueTypeFontFeatures();
        fontFeatures.setEnableComplexScripts(true);
        PDFUnicodeTrueTypeFont uyghurFont = new PDFUnicodeTrueTypeFont("..\\..\\SupportFiles\\NotoNaskhArabic-Regular.ttf", 12, true, fontFeatures);

        PDFFlowTextContent textContent = new PDFFlowTextContent("Uyghur", uyghurFont);
        document.addContent(textContent);

        PDFFlowTableContent table = new PDFFlowTableContent(3);
        table.setMinRowHeight(25);
        PDFFlowTableStringCell defaultCell = new PDFFlowTableStringCell();
        defaultCell.setFont(uyghurFont);
        defaultCell.setTextDirection(PDFTextDirection.RIGHT_TO_LEFT);
        defaultCell.setHorizontalAlign(PDFGraphicAlign.FAR);
        defaultCell.setVerticalAlign(PDFGraphicAlign.CENTER);
        defaultCell.setInnerMargins(new PDFFlowContentMargins(2));
        table.setDefaultCell(defaultCell);

        PDFFlowTableRow row = table.getRows().addRowWithCells(new String[] { "ئۇيغۇر ئەرەب يېزىقى", "يېڭىسار", "بەختىيار" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "بېنزىن", "ئىرادە", "دېڭىز" });
        row = table.getRows().addRowWithCells(new String[] { "گۆش   ", "تۆت", "بۇرۇن" });
        row.setBackground(lightGrayBrush);
        row = table.getRows().addRowWithCells(new String[] { "جاڭيۇ", "ئۇلۇغ", "مەسئۇل" });
        row = table.getRows().addRowWithCells(new String[] { "نائۈمىت", "داشۈئې", "ئۆيمۇئۆي" });
        row.setBackground(lightGrayBrush);
        document.addContent(table);
    }
    
}
