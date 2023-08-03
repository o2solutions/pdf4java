import com.o2sol.pdf4java.PDFFixedDocument;
import com.o2sol.pdf4java.PDFPage;
import com.o2sol.pdf4java.actions.PDFJavaScriptAction;
import com.o2sol.pdf4java.actions.PDFResetFormAction;
import com.o2sol.pdf4java.actions.PDFSubmitFormAction;
import com.o2sol.pdf4java.actions.PDFSubmitFormActionDataFormat;
import com.o2sol.pdf4java.annotations.PDFAnnotationBorder;
import com.o2sol.pdf4java.core.exceptions.PDFException;
import com.o2sol.pdf4java.forms.*;
import com.o2sol.pdf4java.graphics.PDFBrush;
import com.o2sol.pdf4java.graphics.colors.PDFRgbColor;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFont;
import com.o2sol.pdf4java.graphics.fonts.PDFStandardFontFace;

import java.util.ArrayList;

public class FormGenerator {
    public static void main(String[] args) {
        try {
            PDFFixedDocument document = new PDFFixedDocument();
            PDFStandardFont helvetica = new PDFStandardFont(PDFStandardFontFace.HELVETICA, 12);
            PDFBrush brush = new PDFBrush();

            PDFPage page = document.addPage();

            // First name
            page.getCanvas().drawString("First name:", helvetica, brush, 50, 50);
            PDFTextBoxField firstNameTextBox = new PDFTextBoxField("firstname");
            page.addField(firstNameTextBox);
            PDFFieldWidget widget = firstNameTextBox.getWidget(0);
            widget.setFont(helvetica);
            widget.setDisplayRectangle(150, 45, 200, 20);
            widget.setBorder(new PDFAnnotationBorder());
            widget.getBorder().setColor(PDFRgbColor.BLACK);
            widget.getBorder().setWidth(1);

            // Last name
            page.getCanvas().drawString("Last name:", helvetica, brush, 50, 80);
            PDFTextBoxField lastNameTextBox = new PDFTextBoxField("lastname");
            page.addField(lastNameTextBox);
            widget = lastNameTextBox.getWidget(0);
            widget.setFont(helvetica);
            widget.setDisplayRectangle(150, 75, 200, 20);
            widget.setBorder(new PDFAnnotationBorder());
            widget.getBorder().setColor(PDFRgbColor.BLACK);
            widget.getBorder().setWidth(1);

            // Sex
            page.getCanvas().drawString("Sex:", helvetica, brush, 50, 110);
            PDFRadioButtonField sexRadioButton = new PDFRadioButtonField("sex");
            PDFRadioButtonWidget maleRadioItem = new PDFRadioButtonWidget();
            sexRadioButton.addWidget(maleRadioItem);
            PDFRadioButtonWidget femaleRadioItem = new PDFRadioButtonWidget();
            sexRadioButton.addWidget(femaleRadioItem);
            page.addField(sexRadioButton);

            page.getCanvas().drawString("Male", helvetica, brush, 180, 110);
            maleRadioItem.setExportValue("M");
            maleRadioItem.setCheckStyle(PDFCheckStyle.CIRCLE);
            maleRadioItem.setDisplayRectangle(150, 105, 20, 20);
            maleRadioItem.setBorder(new PDFAnnotationBorder());
            maleRadioItem.getBorder().setColor(PDFRgbColor.BLACK);
            maleRadioItem.getBorder().setWidth(1);

            page.getCanvas().drawString("Female", helvetica, brush, 280, 110);
            femaleRadioItem.setExportValue("F");
            femaleRadioItem.setCheckStyle(PDFCheckStyle.CIRCLE);
            femaleRadioItem.setDisplayRectangle(250, 105, 20, 20);
            femaleRadioItem.setBorder(new PDFAnnotationBorder());
            femaleRadioItem.getBorder().setColor(PDFRgbColor.BLACK);
            femaleRadioItem.getBorder().setWidth(1);

            // First car
            page.getCanvas().drawString("First car:", helvetica, brush, 50, 140);
            PDFComboBoxField firstCarList = new PDFComboBoxField("firstcar");
            firstCarList.addItem(new PDFListItem("Mercedes", "Mercedes"));
            firstCarList.addItem(new PDFListItem("BMW", "BMW"));
            firstCarList.addItem(new PDFListItem("Audi", "Audi"));
            firstCarList.addItem(new PDFListItem("Volkswagen", "Volkswagen"));
            firstCarList.addItem(new PDFListItem("Porsche", "Porsche"));
            firstCarList.addItem(new PDFListItem("Honda", "Honda"));
            firstCarList.addItem(new PDFListItem("Toyota", "Toyota"));
            firstCarList.addItem(new PDFListItem("Lexus", "Lexus"));
            firstCarList.addItem(new PDFListItem("Infiniti", "Infiniti"));
            firstCarList.addItem(new PDFListItem("Acura", "Acura"));
            page.addField(firstCarList);
            widget = firstCarList.getWidget(0);
            widget.setFont(helvetica);
            widget.setDisplayRectangle(150, 135, 200, 20);
            widget.setBorder(new PDFAnnotationBorder());
            widget.getBorder().setColor(PDFRgbColor.BLACK);
            widget.getBorder().setWidth(1);

            // Second car
            page.getCanvas().drawString("Second car:", helvetica, brush, 50, 170);
            PDFListBoxField secondCarList = new PDFListBoxField("secondcar");
            secondCarList.addItem(new PDFListItem("Mercedes", "Mercedes"));
            secondCarList.addItem(new PDFListItem("BMW", "BMW"));
            secondCarList.addItem(new PDFListItem("Audi", "Audi"));
            secondCarList.addItem(new PDFListItem("Volkswagen", "Volkswagen"));
            secondCarList.addItem(new PDFListItem("Porsche", "Porsche"));
            secondCarList.addItem(new PDFListItem("Honda", "Honda"));
            secondCarList.addItem(new PDFListItem("Toyota", "Toyota"));
            secondCarList.addItem(new PDFListItem("Lexus", "Lexus"));
            secondCarList.addItem(new PDFListItem("Infiniti", "Infiniti"));
            secondCarList.addItem(new PDFListItem("Acura", "Acura"));
            page.addField(secondCarList);
            widget = secondCarList.getWidget(0);
            widget.setFont(helvetica);
            widget.setDisplayRectangle(150, 165, 200, 60);
            widget.setBorder(new PDFAnnotationBorder());
            widget.getBorder().setColor(PDFRgbColor.BLACK);
            widget.getBorder().setWidth(1);

            // I agree
            page.getCanvas().drawString("I agree:", helvetica, brush, 50, 240);
            PDFCheckBoxField agreeCheckBox = new PDFCheckBoxField("agree");
            page.addField(agreeCheckBox);
            PDFCheckWidget checkWidget = (PDFCheckWidget)agreeCheckBox.getWidget(0);
            checkWidget.setFont(helvetica);
            checkWidget.setExportValue("YES");
            checkWidget.setCheckStyle(PDFCheckStyle.CHECK);
            checkWidget.setDisplayRectangle(150, 235, 20, 20);
            checkWidget.setBorder(new PDFAnnotationBorder());
            checkWidget.getBorder().setColor(PDFRgbColor.BLACK);
            checkWidget.getBorder().setWidth(1);

            // Sign here
            page.getCanvas().drawString("Sign here:", helvetica, brush, 50, 270);
            PDFSignatureField signHereField = new PDFSignatureField("signhere");
            page.addField(signHereField);
            signHereField.getWidget(0).setFont(helvetica);
            signHereField.getWidget(0).setDisplayRectangle(150, 265, 200, 60);

            // Submit form
            PDFPushButtonField submitBtn = new PDFPushButtonField("submit");
            page.addField(submitBtn);
            PDFPushButtonWidget pushButtonWidget = (PDFPushButtonWidget)submitBtn.getWidget(0);
            pushButtonWidget.setDisplayRectangle(450, 45, 150, 30);
            pushButtonWidget.setCaption("Submit form");
            pushButtonWidget.setBackgroundColor (PDFRgbColor.LIGHT_GRAY);
            PDFSubmitFormAction submitFormAction = new PDFSubmitFormAction();
            submitFormAction.setDataFormat(PDFSubmitFormActionDataFormat.FDF);
            ArrayList<String> submittedFields = new ArrayList<String>();
            submittedFields.add("firstname");
            submittedFields.add("lastname");
            submittedFields.add("sex");
            submittedFields.add("firstcar");
            submittedFields.add("secondcar");
            submittedFields.add("agree");
            submittedFields.add("signhere");
            submitFormAction.setFields(submittedFields);
            submitFormAction.setSubmitFields(true);
            submitFormAction.setUrl("http://www.o2sol.com/");
            pushButtonWidget.setMouseUpAction(submitFormAction);

            // Reset form
            PDFPushButtonField resetBtn = new PDFPushButtonField("reset");
            page.addField(resetBtn);
            pushButtonWidget = (PDFPushButtonWidget)resetBtn.getWidget(0);
            pushButtonWidget.setDisplayRectangle(450, 85, 150, 30);
            pushButtonWidget.setCaption("Reset form");
            pushButtonWidget.setBackgroundColor (PDFRgbColor.LIGHT_GRAY);
            PDFResetFormAction resetFormAction = new PDFResetFormAction();
            pushButtonWidget.setMouseUpAction(resetFormAction);

            // Print form
            PDFPushButtonField printBtn = new PDFPushButtonField("print");
            page.addField(printBtn);
            pushButtonWidget = (PDFPushButtonWidget)printBtn.getWidget(0);
            pushButtonWidget.setDisplayRectangle(450, 125, 150, 30);
            pushButtonWidget.setCaption("Print form");
            pushButtonWidget.setBackgroundColor (PDFRgbColor.LIGHT_GRAY);
            PDFJavaScriptAction printAction = new PDFJavaScriptAction();
            printAction.setScript("this.print(true);\n");
            pushButtonWidget.setMouseUpAction(printAction);

            document.save("FormGenerator.pdf");

            System.out.println("PDF document has been saved with success");
        } catch (PDFException ex) {
            System.out.println("Error creating PDF file");
            System.out.println(ex.getMessage());
        }
    }
}
