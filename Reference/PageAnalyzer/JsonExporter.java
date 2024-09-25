import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.content.analyzers.PDFPageContentAnalysis;
import com.o2sol.pdf4java.content.visualobjects.*;
import com.o2sol.pdf4java.graphics.PDFStandardRectangle;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/***
 * Defines a custom page content analysis that exports the vector graphics from a page content stream as JSON.
 */
public class JsonExporter extends PDFPageContentAnalysis {
    public JsonExporter(String jsonFileName) {
        this.jsonFileName = jsonFileName;
        indentLevel = 4;
        isPathStarted = false;

        pathBuilder = new StringBuilder();
        jsonBuilder = new StringBuilder();
    }

    private StringBuilder pathBuilder;

    private StringBuilder jsonBuilder;

    private String jsonFileName;

    private int indentLevel;

    private boolean isPathStarted;

    private String expandSpace(int count) {
        return String.format("%1$" + count + "s", " ");
    }

    private void WriteString(String id, String value, int indentLevel, StringBuilder sb, boolean isLastEntry) {
        sb.append(expandSpace(indentLevel) + "\"" + id + "\" : \"" + value + "\"");
        if (!isLastEntry) {
            sb.append(",");
        }
        sb.append("\r\n");
    }

    private void WriteNumber(String id, double value, int indentLevel, StringBuilder sb, boolean isLastEntry) {
        sb.append(expandSpace(indentLevel) + "\"" + id + "\" : " + value);
        if (!isLastEntry) {
            sb.append(",");
        }
        sb.append("\r\n");
    }

    private void WriteArray(String id, double[] values, int indentLevel, StringBuilder sb, boolean isLastEntry) {
        sb.append(expandSpace(indentLevel) + "\"" + id + "\" : [");
        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(values[i]);
        }
        sb.append(" ]");
        if (!isLastEntry) {
            sb.append(",");
        }
        sb.append("\r\n");
    }

    private void WriteObjectStart(String id, int indentLevel, StringBuilder sb) {
        if ((id != null) && !id.isEmpty()) {
            sb.append(expandSpace(indentLevel) + "\"" + id + "\" : ").append("\r\n");
        }
        sb.append(expandSpace(indentLevel) + "{");
        sb.append("\r\n");
    }

    private void WriteObjectEnd(int indentLevel, StringBuilder sb, boolean isLastEntry) {
        sb.append(expandSpace(indentLevel) + "}");
        if (!isLastEntry) {
            sb.append(",");
        }
        sb.append("\r\n");
    }

    private void WriteArrayStart(String id, int indentLevel, StringBuilder sb) {
        if ((id != null) && !id.isEmpty()) {
            sb.append(expandSpace(indentLevel) + "\"" + id + "\" : ").append("\r\n");
        }
        sb.append(expandSpace(indentLevel) + "[");
        sb.append("\r\n");
    }

    private void WriteArrayEnd(int indentLevel, StringBuilder sb, boolean isLastEntry) {
        sb.append(expandSpace(indentLevel) + "]");
        if (!isLastEntry) {
            sb.append(",");
        }
        sb.append("\r\n");
    }

    private void StartPathIfNotStarted() {
        if (!isPathStarted) {
            isPathStarted = true;
            WriteObjectStart("", indentLevel, pathBuilder);
            indentLevel += 4;
            WriteArrayStart("pathData", indentLevel, pathBuilder);
            indentLevel += 4;
        }
    }

    private void FinishPath(String op) {
        if (isPathStarted) {
            // Remove the last ','
            pathBuilder.delete(pathBuilder.length() - 3, pathBuilder.length() - 2); // 3 = , \r \n
            isPathStarted = false;
            indentLevel -= 4;
            WriteArrayEnd(indentLevel, pathBuilder, false);
            WriteString("op", op, indentLevel, pathBuilder, true);
            indentLevel -= 4;
            WriteObjectEnd(indentLevel, pathBuilder, false);

            jsonBuilder.append(pathBuilder.toString());
        }

        if (!op.equals("W") && !op.equals("W*")) {
            pathBuilder.delete(0, pathBuilder.length());
        }
    }

    public void setup() {
        if (context instanceof PDFPage) {
            PDFPage page = (PDFPage)context;
            jsonBuilder.append("{").append("\r\n");

            PDFStandardRectangle mediaBox = page.getMediaBox();
            WriteArray("MediaBox", new double[] { mediaBox.getLLX(), mediaBox.getLLY(), mediaBox.getURX(), mediaBox.getURY() }, indentLevel, jsonBuilder, false);
            PDFStandardRectangle cropBox = page.getCropBox();
            if (cropBox != null)
            {
                WriteArray("CropBox", new double[] { cropBox.getLLX(), cropBox.getLLY(), cropBox.getURX(), cropBox.getURY() }, indentLevel, jsonBuilder, false);
            }
            WriteNumber("Rotate", page.getRotation(), indentLevel, jsonBuilder, false);
            WriteArrayStart("Contents", indentLevel, jsonBuilder);
            indentLevel += 4;
        }
    }

    public void cleanUp() {
        if (context instanceof PDFPage) {
            // Dummy object to compensate the last ','
            jsonBuilder.append(expandSpace(indentLevel) + "{}").append("\r\n");
            indentLevel -= 4;
            WriteArrayEnd(indentLevel, jsonBuilder, true);
            jsonBuilder.append("}").append("\r\n");

            try {
                Files.write(Paths.get(jsonFileName), jsonBuilder.toString().getBytes());
            }
            catch (Exception ex) {
                System.out.println("Cannot save json file.\r\n" + ex.getMessage());
            }
        }
    }

    public void analyzeConcatenateMatrixOperator(double m11, double m12, double m21, double m22, double tx, double ty) {
        WriteObjectStart("", indentLevel, pathBuilder);
        indentLevel += 4;
        WriteString("op", "cm", indentLevel, pathBuilder, false);
        WriteArray("matrix", new double[] { m11, m12, m21, m22, tx, ty }, indentLevel, pathBuilder, true);
        indentLevel -= 4;
        WriteObjectEnd(indentLevel, pathBuilder, false);
    }

    public void analyzeMoveToOperator(double x, double y) {
        StartPathIfNotStarted();

        WriteObjectStart("", indentLevel, pathBuilder);
        indentLevel += 4;
        WriteString("op", "m", indentLevel, pathBuilder, false);
        WriteArray("pts", new double[] { x, y }, indentLevel, pathBuilder, true);
        indentLevel -= 4;
        WriteObjectEnd(indentLevel, pathBuilder, false);
    }

    public void analyzeLineToOperator(double x, double y) {
        StartPathIfNotStarted();

        WriteObjectStart("", indentLevel, pathBuilder);
        indentLevel += 4;
        WriteString("op", "l", indentLevel, pathBuilder, false);
        WriteArray("pts", new double[] { x, y }, indentLevel, pathBuilder, true);
        indentLevel -= 4;
        WriteObjectEnd(indentLevel, pathBuilder, false);
    }

    public void analyzeRectangleOperator(double x, double y, double width, double height) {
        StartPathIfNotStarted();

        WriteObjectStart("", indentLevel, pathBuilder);
        indentLevel += 4;
        WriteString("op", "re", indentLevel, pathBuilder, false);
        WriteArray("pts", new double[] { x, y, width, height }, indentLevel, pathBuilder, true);
        indentLevel -= 4;
        WriteObjectEnd(indentLevel, pathBuilder, false);
    }

    public void analyzeCCurveToOperator(double x1, double y1, double x2, double y2, double x3, double y3) {
        StartPathIfNotStarted();

        WriteObjectStart("", indentLevel, pathBuilder);
        indentLevel += 4;
        WriteString("op", "c", indentLevel, pathBuilder, false);
        WriteArray("pts", new double[] { x1, y1, x2, y2, x3, y3 }, indentLevel, pathBuilder, true);
        indentLevel -= 4;
        WriteObjectEnd(indentLevel, pathBuilder, false);
    }

    public void analyzeYCurveToOperator(double x1, double y1, double x2, double y2) {
        StartPathIfNotStarted();

        WriteObjectStart("", indentLevel, pathBuilder);
        indentLevel += 4;
        WriteString("op", "y", indentLevel, pathBuilder, false);
        WriteArray("pts", new double[] { x1, y1, x2, y2 }, indentLevel, pathBuilder, true);
        indentLevel -= 4;
        WriteObjectEnd(indentLevel, pathBuilder, false);
    }

    public void analyzeVCurveToOperator(double x1, double y1, double x2, double y2) {
        StartPathIfNotStarted();

        WriteObjectStart("", indentLevel, pathBuilder);
        indentLevel += 4;
        WriteString("op", "v", indentLevel, pathBuilder, false);
        WriteArray("pts", new double[] { x1, y1, x2, y2 }, indentLevel, pathBuilder, true);
        indentLevel -= 4;
        WriteObjectEnd(indentLevel, pathBuilder, false);
    }

    /***
     * Called when b operator is found.
     * The pathVisualObject parameter is set only when the {@PDFPageContentAnalysis#enableExtendedOperatorInformation()} property is set to true on the analyzer that processes this operator.
     * @param pathVisualObject The path painted by this operator.
     */
    public void analyzeCloseFillNonZeroStrokeOperator(PDFPathVisualObject pathVisualObject) {
        FinishPath("b");
    }

    /***
     * Called when b* operator is found.
     * The pathVisualObject parameter is set only when the {@PDFPageContentAnalysis#enableExtendedOperatorInformation()} property is set to true on the analyzer that processes this operator.
     * @param pathVisualObject The path painted by this operator.
     */
    public void analyzeCloseFillEvenOddStrokeOperator(PDFPathVisualObject pathVisualObject) {
        FinishPath("b*");
    }

    /***
     * Called when B operator is found.
     * The pathVisualObject parameter is set only when the {@PDFPageContentAnalysis#enableExtendedOperatorInformation()} property is set to true on the analyzer that processes this operator.
     * @param pathVisualObject The path painted by this operator.
     */
    public void analyzeFillNonZeroStrokeOperator(PDFPathVisualObject pathVisualObject) {
        FinishPath("B");
    }

    /***
     * Called when B* operator is found.
     * The pathVisualObject parameter is set only when the {@PDFPageContentAnalysis#enableExtendedOperatorInformation()} property is set to true on the analyzer that processes this operator.
     * @param pathVisualObject The path painted by this operator.
     */
    public void analyzeFillEvenOddStrokeOperator(PDFPathVisualObject pathVisualObject) {
        FinishPath("B*");
    }

    /***
     * Called when f operator is found.
     * The pathVisualObject parameter is set only when the {@PDFPageContentAnalysis#enableExtendedOperatorInformation()} property is set to true on the analyzer that processes this operator.
     * @param pathVisualObject The path painted by this operator.
     */
    public void analyzeFillNonZeroOperator(PDFPathVisualObject pathVisualObject) {
        FinishPath("f");
    }

    /***
     * Called when F operator is found.
     * The pathVisualObject parameter is set only when the {@PDFPageContentAnalysis#enableExtendedOperatorInformation()} property is set to true on the analyzer that processes this operator.
     * @param pathVisualObject The path painted by this operator.
     */
    public void analyzeFillNonZero2Operator(PDFPathVisualObject pathVisualObject) {
        FinishPath("F");
    }

    /***
     * Called when f* operator is found.
     * The pathVisualObject parameter is set only when the {@PDFPageContentAnalysis#enableExtendedOperatorInformation()} property is set to true on the analyzer that processes this operator.
     * @param pathVisualObject The path painted by this operator.
     */
    public void analyzeFillOddEvenOperator(PDFPathVisualObject pathVisualObject) {
        FinishPath("f*");
    }

    /***
     * Called when n operator is found.
     * The pathVisualObject parameter is set only when the {@PDFPageContentAnalysis#enableExtendedOperatorInformation()} property is set to true on the analyzer that processes this operator.
     * @param pathVisualObject The path painted by this operator.
     */
    public void analyzeEndPathOperator(PDFPathVisualObject pathVisualObject) {
        isPathStarted = false;
        pathBuilder.delete(0, pathBuilder.length());
    }

    /***
     * Called when S operator is found.
     * The pathVisualObject parameter is set only when the {@PDFPageContentAnalysis#enableExtendedOperatorInformation()} property is set to true on the analyzer that processes this operator.
     * @param pathVisualObject The path painted by this operator.
     */
    public void analyzeStrokeOperator(PDFPathVisualObject pathVisualObject) {
        FinishPath("S");
    }

    /***
     * Called when s operator is found.
     * The pathVisualObject parameter is set only when the {@PDFPageContentAnalysis#enableExtendedOperatorInformation()} property is set to true on the analyzer that processes this operator.
     * @param pathVisualObject The path painted by this operator.
     */
    public void analyzeCloseStrokeOperator(PDFPathVisualObject pathVisualObject) {
        FinishPath("s");
    }

    /***
     * Called when W operator is found.
     * The pathVisualObject parameter is set only when the {@PDFPageContentAnalysis#enableExtendedOperatorInformation()} property is set to true on the analyzer that processes this operator.
     * @param pathVisualObject The path painted by this operator.
     */
    public void analyzeSetClipNonZeroOperator(PDFPathVisualObject pathVisualObject) {
        FinishPath("W");
    }

    /***
     * Called when W* operator is found.
     * The pathVisualObject parameter is set only when the {@PDFPageContentAnalysis#enableExtendedOperatorInformation()} property is set to true on the analyzer that processes this operator.
     * @param pathVisualObject The path painted by this operator.
     */
    public void analyzeSetClipEvenOddOperator(PDFPathVisualObject pathVisualObject) {
        FinishPath("W*");
    }

}
