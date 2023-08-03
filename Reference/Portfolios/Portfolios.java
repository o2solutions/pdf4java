import com.o2sol.pdf4java.core.cos.PDFCosString;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.portfolios.*;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Portfolios {
    public static void main(String[] args) throws IOException {
        try {
            PDFPortfolio portfolio = new PDFPortfolio();

            // Build the structure that describes how to files and folders in the portfolio are presented to the user.
            PDFPortfolioAttributeDefinitions portfolioAttributeDefinitions = new PDFPortfolioAttributeDefinitions();
            PDFPortfolioAttributeDefinition nameAttribute = new PDFPortfolioAttributeDefinition();
            nameAttribute.setName("Name");
            nameAttribute.setType(PDFPortfolioAttributeDefinitionType.STRING);
            portfolioAttributeDefinitions.put("name", nameAttribute);
            PDFPortfolioAttributeDefinition typeAttribute = new PDFPortfolioAttributeDefinition();
            typeAttribute.setName("Type");
            typeAttribute.setType(PDFPortfolioAttributeDefinitionType.STRING);
            portfolioAttributeDefinitions.put("type", typeAttribute);

            portfolio.setAttributeDefinitions(portfolioAttributeDefinitions);

            // Setup the folders structure
            PDFPortfolioFolder root = new PDFPortfolioFolder();
            root.setName("All files");
            root.setPortfolioAttributes(new PDFPortfolioItemAttributes());
            root.getPortfolioAttributes().put("name", new PDFCosString("All files"));

            PDFPortfolioFolder imagesFolder = new PDFPortfolioFolder();
            imagesFolder.setName("Images");
            imagesFolder.setPortfolioAttributes(new PDFPortfolioItemAttributes());
            imagesFolder.getPortfolioAttributes().put("name", new PDFCosString("Images (1)"));
            root.getFolders().add(imagesFolder);

            PDFPortfolioFolder pdfFolder = new PDFPortfolioFolder();
            pdfFolder.setName("PDFs");
            pdfFolder.setPortfolioAttributes(new PDFPortfolioItemAttributes());
            pdfFolder.getPortfolioAttributes().put("name", new PDFCosString("PDFs (1)"));
            root.getFolders().add(pdfFolder);

            PDFPortfolioFolder profileFolder = new PDFPortfolioFolder();
            profileFolder.setName("Color profiles");
            profileFolder.setPortfolioAttributes(new PDFPortfolioItemAttributes());
            profileFolder.getPortfolioAttributes().put("name", new PDFCosString("Color profiles (1)"));
            root.getFolders().add(profileFolder);

            portfolio.getFolders().add(root);

            // Setup the portfolio items
            PDFPortfolioItem imageFile = new PDFPortfolioItem();
            imageFile.setFolder(imagesFolder);
            imageFile.setContent(getFileContent("..\\..\\SupportFiles\\image.jpg"));
            imageFile.setFileName("image.jpg");
            imageFile.setAttributes(new PDFPortfolioItemAttributes());
            imageFile.getAttributes().put("name", new PDFCosString("image.jpg"));
            imageFile.getAttributes().put("type", new PDFCosString("JPEG image"));
            portfolio.getItems().add(imageFile);

            PDFPortfolioItem pdfFile = new PDFPortfolioItem();
            pdfFile.setFolder(pdfFolder);
            pdfFile.setContent(getFileContent("..\\..\\SupportFiles\\content.pdf"));
            pdfFile.setFileName("content.pdf");
            pdfFile.setAttributes(new PDFPortfolioItemAttributes());
            pdfFile.getAttributes().put("name", new PDFCosString("content.pdf"));
            pdfFile.getAttributes().put("type", new PDFCosString("PDF file"));
            portfolio.getItems().add(pdfFile);

            PDFPortfolioItem csFile = new PDFPortfolioItem();
            csFile.setFolder(profileFolder);
            csFile.setContent(getFileContent("..\\..\\SupportFiles\\rgb.icc"));
            csFile.setFileName("rgb.icc");
            csFile.setAttributes(new PDFPortfolioItemAttributes());
            csFile.getAttributes().put("name", new PDFCosString("rgb.icc"));
            csFile.getAttributes().put("type", new PDFCosString("ICC file"));
            portfolio.getItems().add(csFile);

            //portfolio.setStartupDocument(pdfFile);

            portfolio.save("Portfolios.pdf");

            System.out.println("PDF portfolio has been saved with success");
        } catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }

    private static byte[] getFileContent(String fileName) throws IOException {
        RandomAccessFile file = new RandomAccessFile(fileName, "rwd");
        byte[] content = new byte[(int)file.length()];
        file.read(content, 0, content.length);
        file.close();

        return content;
    }
}
