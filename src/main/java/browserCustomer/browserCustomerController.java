package browserCustomer;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLOutput;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import jdbc.jdbcController;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.ImageIO;
import javax.swing.*;

public class browserCustomerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combocategory;

    @FXML
    private TableView<customerBean> tableview;

    private byte[] resizeImage(byte[] imageBytes, int width, int height) throws Exception {

        BufferedImage original = ImageIO.read(new ByteArrayInputStream(imageBytes));

        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = resized.createGraphics();

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);

        g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(original, 0, 0, width, height, null);

        g.dispose();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(resized, "png", baos);

        return baos.toByteArray();
    }
    @FXML
    void exportasexcel(ActionEvent event) {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Customers");

            // Header
            Row header = sheet.createRow(0);

            for (int i = 0; i < tableview.getColumns().size(); i++) {
                header.createCell(i).setCellValue(tableview.getColumns().get(i).getText());
            }

            Drawing<?> drawing = sheet.createDrawingPatriarch();

            for (int i = 0; i < tableview.getItems().size(); i++) {

                customerBean c = tableview.getItems().get(i);

                Row row = sheet.createRow(i + 1);
                row.setHeightInPoints(65);

                sheet.setColumnWidth(6, 12 * 256);
                sheet.setColumnWidth(7, 12 * 256);

                row.createCell(0).setCellValue(c.getMobile());
                row.createCell(1).setCellValue(c.getCname());
                row.createCell(2).setCellValue(c.getAddress());
                row.createCell(3).setCellValue(c.getCity());
                row.createCell(4).setCellValue(c.getEmail());
                row.createCell(5).setCellValue(c.getCtype());

                // Profile Picture
                if (c.getPic() != null) {

                    byte[] img = resizeImage(c.getPic(), 100, 100);

                    int pictureIdx = workbook.addPicture(img, Workbook.PICTURE_TYPE_PNG);

                    CreationHelper helper = workbook.getCreationHelper();

                    ClientAnchor anchor = helper.createClientAnchor();
                    anchor.setCol1(6);
                    anchor.setRow1(i + 1);

                    Picture pict = drawing.createPicture(anchor, pictureIdx);
                    pict.resize();
                }

                // Aadhaar Image
                if (c.getAcard() != null) {

                    byte[] img = resizeImage(c.getAcard(), 100, 100);
                    int pictureIdx = workbook.addPicture(img, Workbook.PICTURE_TYPE_PNG);

                    CreationHelper helper = workbook.getCreationHelper();

                    ClientAnchor anchor = helper.createClientAnchor();
                    anchor.setCol1(7);
                    anchor.setRow1(i + 1);

                    Picture pict = drawing.createPicture(anchor, pictureIdx);
                    pict.resize(0.8);
                }

                row.createCell(8).setCellValue(c.getDoe());
            }

            // Widths
            sheet.setColumnWidth(6, 20 * 256);
            sheet.setColumnWidth(7, 20 * 256);

            for (int i = 0; i < 6; i++)
                sheet.autoSizeColumn(i);

            sheet.autoSizeColumn(8);

            FileChooser chooser = new FileChooser();
            chooser.setTitle("Save Excel");
            chooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));

            File file = chooser.showSaveDialog(null);

            if (file != null) {
                FileOutputStream fos = new FileOutputStream(file);
                workbook.write(fos);
                fos.close();
            }

            workbook.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Excel Exported Successfully");
            alert.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void genpdf(ActionEvent event) {
        try {

            FileChooser chooser = new FileChooser();
            chooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

            File file = chooser.showSaveDialog(null);

            if (file == null)
                return;

            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream(file));

            document.open();

            document.add(new Paragraph("Customer Report\n\n"));

            PdfPTable table = new PdfPTable(9);

            table.addCell("Mobile");
            table.addCell("Name");
            table.addCell("Address");
            table.addCell("City");
            table.addCell("Email");
            table.addCell("Type");
            table.addCell("Profile");
            table.addCell("Aadhaar");
            table.addCell("Date");

            for (customerBean c : tableview.getItems()) {

                table.addCell(c.getMobile());
                table.addCell(c.getCname());
                table.addCell(c.getAddress());
                table.addCell(c.getCity());
                table.addCell(c.getEmail());
                table.addCell(c.getCtype());

                if (c.getPic() != null) {
                    com.lowagie.text.Image img =
                            com.lowagie.text.Image.getInstance(c.getPic());

                    img.scaleToFit(60, 60);

                    PdfPCell cell = new PdfPCell(img, true);

                    table.addCell(cell);
                } else
                    table.addCell("");

                if (c.getAcard() != null) {
                    com.lowagie.text.Image img =
                            com.lowagie.text.Image.getInstance(c.getAcard());

                    img.scaleToFit(60, 60);

                    PdfPCell cell = new PdfPCell(img, true);

                    table.addCell(cell);
                } else
                    table.addCell("");

                table.addCell(c.getDoe());
            }

            document.add(table);

            document.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("PDF Exported Successfully");
            alert.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showdata(ActionEvent event) {
        tableview.getColumns().clear();
        TableColumn<customerBean,String> mobile=new TableColumn<customerBean,String>("Mobile");
        mobile.setCellValueFactory(new PropertyValueFactory<customerBean,String>("mobile"));
        mobile.setMinWidth(100);

        TableColumn<customerBean,String> name=new TableColumn<customerBean,String>("Name");
        name.setCellValueFactory(new PropertyValueFactory<customerBean,String>("cname"));
        name.setMinWidth(100);

        TableColumn<customerBean,String> address=new TableColumn<customerBean,String>("Address");
        address.setCellValueFactory(new PropertyValueFactory<customerBean,String>("address"));
        address.setMinWidth(100);

        TableColumn<customerBean,String> city=new TableColumn<customerBean,String>("City");
        city.setCellValueFactory(new PropertyValueFactory<customerBean,String>("city"));
        city.setMinWidth(100);

        TableColumn<customerBean,String> email=new TableColumn<customerBean,String>("Email");
        email.setCellValueFactory(new PropertyValueFactory<customerBean,String>("email"));
        email.setMinWidth(100);

        TableColumn<customerBean,String>ctype=new TableColumn<customerBean,String>("Customer Type");
        ctype.setCellValueFactory(new PropertyValueFactory<customerBean,String>("ctype"));
        ctype.setMinWidth(100);

        TableColumn<customerBean,byte[]>pic=new TableColumn<customerBean,byte[]>("Profile Pic");
        pic.setCellValueFactory(new PropertyValueFactory<customerBean,byte[]>("pic"));
        pic.setMinWidth(100);
        pic.setCellFactory(col-> new TableCell<customerBean,byte[]>(){
            private final ImageView imageView = new ImageView();
            {
                imageView.setFitHeight(150);
                imageView.setFitWidth(150);
                imageView.setPreserveRatio(true);
            }
            @Override
            public void updateItem(byte[] item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                }
                else {
                    Image image=new Image(new ByteArrayInputStream(item));
                    imageView.setImage(image);
                    setGraphic(imageView);
                }
            }

        });

        TableColumn<customerBean,byte[]>acard=new TableColumn<customerBean,byte[]>("Aadhar Card");
        acard.setCellValueFactory(new PropertyValueFactory<customerBean,byte[]>("acard"));
        acard.setMinWidth(100);
        acard.setCellFactory(col-> new TableCell<customerBean,byte[]>(){
            private final ImageView imageView = new ImageView();
            {
                imageView.setFitHeight(150);
                imageView.setFitWidth(150);
                imageView.setPreserveRatio(true);
            }
            @Override
            public void updateItem(byte[] item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                }
                else {
                    Image image=new Image(new ByteArrayInputStream(item));
                    imageView.setImage(image);
                    setGraphic(imageView);
                }
            }

        });

        TableColumn<customerBean,String>doe=new TableColumn<customerBean,String>("Date of Joining");
        doe.setCellValueFactory(new PropertyValueFactory<customerBean,String>("doe"));
        doe.setMinWidth(100);

        tableview.getColumns().addAll(
                mobile,
                name,
                address,
                city,
                email,
                ctype,
                pic,
                acard,
                doe
        );
        tableview.setItems(getRecords());

    }
    ObservableList<customerBean> getRecords(){
        ObservableList<customerBean> list = FXCollections.observableArrayList();
        try{
            PreparedStatement pst=con.prepareStatement("SELECT * from customers where ctype=?");;
            if((combocategory.getSelectionModel().getSelectedItem().equals("Buyer")))
                pst.setString(1,"Buyer");

            else if ((combocategory.getSelectionModel().getSelectedItem().equals("Seller")))
                pst.setString(1,"Seller");

            else if((combocategory.getSelectionModel().getSelectedItem().equals("Both")))
                pst.setString(1,"Both");
            else if((combocategory.getSelectionModel().getSelectedItem().equals("All")))
                pst=con.prepareStatement("SELECT * from customers");
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Selection");
                alert.showAndWait();
            }


            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                String mobile=rs.getString("mobile");
                String cname=rs.getString("cname");
                String address=rs.getString("address");
                String city=rs.getString("city");
                String email=rs.getString("email");
                String ctype=rs.getString("ctype");
                byte[] pic=rs.getBytes("pic");
                byte[] acard=rs.getBytes("acard");
                java.sql.Date doe=rs.getDate("doe");

                customerBean obj=new customerBean(mobile,cname,address,city,email,ctype,pic,acard,doe.toString());
                list.add(obj);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @FXML
    void initialize() {
        String arr[]={"Select Customer Type","Buyer","Seller","Both","All"};
        combocategory.getItems().addAll(arr);
        combocategory.getSelectionModel().select(0);
        doconnect();
    }
    Connection con;
    public void doconnect(){
        con= jdbcController.DatabaseConnection.connectToDb();
        if(con==null){
            System.out.println("Connection Failed! Check output console");
        }
        else {
            System.out.println("All is well");
        }
    }


}
