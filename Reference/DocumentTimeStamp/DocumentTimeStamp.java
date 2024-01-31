import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.digitalsignatures.*;
import com.o2sol.pdf4java.forms.PDFSignatureField;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DocumentTimeStamp implements PDFTsaClient {
    public static void main(String[] args) {
        try {
            PDFFixedDocument document = new PDFFixedDocument("..\\..\\SupportFiles\\formfill.pdf");

            PDFSignatureField signField = (PDFSignatureField) document.getForm().getField("signhere");
            signField.setSignature(new PDFDocumentTimeStamp());
            ((PDFDigitalSignature) signField.getSignature()).setTimestampDigestAlgorithm(PDFDigitalSignatureDigestAlgorithm.SHA256);
            ((PDFDigitalSignature) signField.getSignature()).setTsaClient(new DocumentTimeStamp());

            document.save("DocumentTimeStamp.pdf");

            System.out.println("PDF document has been saved with success");
        }
        catch(PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Retrieves the timestamp response from the Time Stamp Authority server.
     *
     * @param data Data to be passed to TSA server.
     */
    @Override
    public void retrieveTimeStampInfo(PDFTsaClientEvent data) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("https://freetsa.org/tsr").openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/timestamp-query");

            try (OutputStream output = connection.getOutputStream()) {
                output.write(data.getTimeStampRequest());
            }

            InputStream response = connection.getInputStream();

            ByteArrayOutputStream tsrStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[65536];
            int readSize;
            while ((readSize = response.read(buffer, 0, buffer.length)) > 0) {
                tsrStream.write(buffer, 0, readSize);
            }

            data.setTimeStampResponse(tsrStream.toByteArray());
        }
        catch(Exception ignored) {

        }
    }

}
