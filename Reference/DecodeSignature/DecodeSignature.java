import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.core.io.FileMode;
import com.o2sol.pdf4java.core.io.FileStream;
import com.o2sol.pdf4java.digitalsignatures.PDFComputedDigitalSignature;
import com.o2sol.pdf4java.digitalsignatures.asn1.*;
import com.o2sol.pdf4java.forms.PDFSignatureField;

public class DecodeSignature {
    public static void main(String[] args) {
        try (FileStream signedFile = new FileStream("..\\..\\SupportFiles\\PDF4NET.pdf", FileMode.OPEN_READ)) {
            PDFFixedDocument document = new PDFFixedDocument(signedFile);
            PDFSignatureField signature1Field = (PDFSignatureField) document.getForm().getField("Signature1");

            PDFComputedDigitalSignature signature1 = (PDFComputedDigitalSignature) signature1Field.getSignature();

            Asn1Object[] derSignature = signature1.decodeSignature();
            dumpSignature(derSignature[0], 1);
        }
    }

    private static void dumpSignature(Asn1Object asn1Obj, int level) {
        String padding = level == 0 ? "" : String.format("%" + (level * 2) + "s", "");
        String items = "";
        Asn1ContextSpecificSequence asn1CtxSeq = asn1Obj instanceof Asn1ContextSpecificSequence ? (Asn1ContextSpecificSequence)asn1Obj : null;
        if (asn1CtxSeq != null) {
            items = asn1CtxSeq.size() == 1 ? "item" : "items";
            System.out.println(String.format("%s[%d] (%d %s)", padding, asn1CtxSeq.getTag().getValue() & 0x1F, asn1CtxSeq.size(), items));
            for (int i = 0; i < asn1CtxSeq.size(); i++) {
                dumpSignature(asn1CtxSeq.getItems().get(i), level + 1);
            }
        }
        else {
            switch (asn1Obj.getTag()) {
                case BIT_STRING:
                    System.out.printf("%sBITSTRING %s\r\n", padding, asn1Obj);
                    break;
                case BOOLEAN:
                    System.out.printf("%sBOOLEAN %s\r\n", padding, asn1Obj);
                    break;
                case IA5_STRING:
                    System.out.printf("%sIA5STRING %s\r\n", padding, asn1Obj);
                    break;
                case INTEGER:
                    System.out.printf("%sINTEGER %s\r\n", padding, asn1Obj);
                    break;
                case NULL:
                    System.out.printf("%s %s\r\n", padding, asn1Obj);
                    break;
                case OBJECT_IDENTIFIER:
                    Asn1ObjectIdentifier oid = (Asn1ObjectIdentifier)asn1Obj;
                    System.out.printf("%sOBJECT IDENTIFIER %s %s\r\n", padding, oid, oid.getFriendlyName());
                    break;
                case OCTET_STRING:
                    System.out.printf("%sOCTET STRING %s\r\n", padding, asn1Obj);
                    break;
                case PRINTABLE_STRING:
                    System.out.printf("%sPRINTABLESTRING %s\r\n", padding, asn1Obj);
                    break;
                case SEQUENCE:
                    Asn1Sequence asn1Seq = (Asn1Sequence)asn1Obj;
                    items = asn1Seq.size() == 1 ? "item" : "items";
                    System.out.printf("%sSEQUENCE (%d %s)\r\n", padding, asn1Seq.size(), items);
                    for (int i = 0; i < asn1Seq.size(); i++) {
                        dumpSignature(asn1Seq.getItems().get(i), level + 1);
                    }
                    break;
                case SET:
                    Asn1Set asn1Set = (Asn1Set)asn1Obj;
                    items = asn1Set.size() == 1 ? "item" : "items";
                    System.out.printf("%sSET (%d %s)\r\n", padding, asn1Set.size(), items);
                    for (int i = 0; i < asn1Set.size(); i++) {
                        dumpSignature(asn1Set.getItems().get(i), level + 1);
                    }
                    break;
                case UTC_TIME:
                    System.out.printf("%sUTCTIME %s\r\n", padding, asn1Obj);
                    break;
                case UTF8_STRING:
                    System.out.printf("%sUTF8STRING %s\r\n", padding, asn1Obj);
                    break;
            }
        }
    }
}
