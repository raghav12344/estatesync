package propertyFinder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
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

public class propertyFinderController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnFind;

    @FXML
    private Button btnPdf;

    @FXML
    private ComboBox<String> cmbArea;

    @FXML
    private ComboBox<String> cmbCity;

    @FXML
    private ComboBox<String> cmbStructure;

    @FXML
    private ComboBox<String> cmbType;

    @FXML
    private TableView<propertybean> table;


    @FXML
    private TextField txtMax;

    @FXML
    private TextField txtMin;

    @FXML
    void createPdf(ActionEvent event) {

        try {

            if(table.getItems().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("No properties found");
                alert.show();
                return;
            }


            FileChooser chooser = new FileChooser();
            chooser.setTitle("Save Property Report");

            chooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter(
                            "PDF Files",
                            "*.pdf"
                    )
            );


            File file = chooser.showSaveDialog(null);

            if(file==null)
                return;



            Document document = new Document();


            PdfWriter.getInstance(
                    document,
                    new java.io.FileOutputStream(file)
            );


            document.open();



            document.add(
                    new Paragraph(
                            "PROPERTY FINDER REPORT"
                    )
            );


            document.add(
                    new Paragraph(
                            "City : "+cmbCity.getValue()+
                                    "    Area : "+cmbArea.getValue()
                    )
            );


            document.add(new Paragraph("\n"));



            // 18 columns
            PdfPTable pdfTable =
                    new PdfPTable(18);

            pdfTable.setWidthPercentage(100);



            String headers[] =
                    {
                            "RID",
                            "Mobile",
                            "Location",
                            "Area",
                            "City",
                            "Size",
                            "Front",
                            "Rear",
                            "Left",
                            "Right",
                            "Facing",
                            "Type",
                            "Construction",
                            "Approved",
                            "Price",
                            "Info",
                            "Image1",
                            "Image2"
                    };



            for(String h:headers)
            {
                pdfTable.addCell(
                        new PdfPCell(
                                new Phrase(h)
                        )
                );
            }



            for(propertybean p:table.getItems())
            {

                pdfTable.addCell(
                        String.valueOf(p.getRid())
                );

                pdfTable.addCell(
                        p.getMobile()
                );

                pdfTable.addCell(
                        p.getLocation()
                );

                pdfTable.addCell(
                        p.getArea()
                );

                pdfTable.addCell(
                        p.getCity()
                );


                pdfTable.addCell(
                        String.valueOf(p.getSize())
                );

                pdfTable.addCell(
                        String.valueOf(p.getFront())
                );

                pdfTable.addCell(
                        String.valueOf(p.getRear())
                );

                pdfTable.addCell(
                        String.valueOf(p.getLeftside())
                );

                pdfTable.addCell(
                        String.valueOf(p.getRightside())
                );


                pdfTable.addCell(
                        p.getFacing()
                );

                pdfTable.addCell(
                        p.getProptype()
                );

                pdfTable.addCell(
                        p.getConsttype()
                );

                pdfTable.addCell(
                        p.getApprovedby()
                );

                pdfTable.addCell(
                        p.getPrice()
                );

                pdfTable.addCell(
                        p.getOtherinfo()
                );



                // IMAGE 1

                if(p.getPic1()!=null)
                {

                    com.lowagie.text.Image img =
                            com.lowagie.text.Image.getInstance(p.getPic1());

                    img.scaleToFit(60, 60);

                    PdfPCell cell = new PdfPCell(img, true);

                    pdfTable.addCell(cell);

                }
                else
                {
                    pdfTable.addCell("No Image");
                }




                // IMAGE 2

                if(p.getPic2()!=null)
                {

                    com.lowagie.text.Image img =
                            com.lowagie.text.Image.getInstance(p.getPic2());

                    img.scaleToFit(60, 60);

                    PdfPCell cell = new PdfPCell(img, true);

                    pdfTable.addCell(cell);

                }
                else
                {
                    pdfTable.addCell("No Image");
                }

            }



            document.add(pdfTable);


            document.close();



            Alert alert =
                    new Alert(Alert.AlertType.INFORMATION);

            alert.setContentText(
                    "PDF Created Successfully"
            );

            alert.show();



        }
        catch(Exception e)
        {
            e.printStackTrace();

            Alert alert =
                    new Alert(Alert.AlertType.ERROR);

            alert.setContentText(
                    e.getMessage()
            );

            alert.show();
        }

    }


    @FXML
    void findProperties(ActionEvent event) {
        table.getColumns().clear();

        TableColumn<propertybean,Integer> rid = new TableColumn<>("RID");
        rid.setCellValueFactory(new PropertyValueFactory<>("rid"));
        rid.setMinWidth(70);

        TableColumn<propertybean,String> mobile = new TableColumn<>("Mobile");
        mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        mobile.setMinWidth(120);

        TableColumn<propertybean,String> location = new TableColumn<>("Location");
        location.setCellValueFactory(new PropertyValueFactory<>("location"));
        location.setMinWidth(180);

        TableColumn<propertybean,String> area = new TableColumn<>("Area");
        area.setCellValueFactory(new PropertyValueFactory<>("area"));
        area.setMinWidth(120);

        TableColumn<propertybean,String> city = new TableColumn<>("City");
        city.setCellValueFactory(new PropertyValueFactory<>("city"));
        city.setMinWidth(100);

        TableColumn<propertybean,Float> size = new TableColumn<>("Size");
        size.setCellValueFactory(new PropertyValueFactory<>("size"));

        TableColumn<propertybean,Float> front = new TableColumn<>("Front");
        front.setCellValueFactory(new PropertyValueFactory<>("front"));

        TableColumn<propertybean,Float> rear = new TableColumn<>("Rear");
        rear.setCellValueFactory(new PropertyValueFactory<>("rear"));

        TableColumn<propertybean,Float> left = new TableColumn<>("Left");
        left.setCellValueFactory(new PropertyValueFactory<>("leftside"));

        TableColumn<propertybean,Float> right = new TableColumn<>("Right");
        right.setCellValueFactory(new PropertyValueFactory<>("rightside"));

        TableColumn<propertybean,String> facing = new TableColumn<>("Facing");
        facing.setCellValueFactory(new PropertyValueFactory<>("facing"));

        TableColumn<propertybean,String> type = new TableColumn<>("Property Type");
        type.setCellValueFactory(new PropertyValueFactory<>("proptype"));

        TableColumn<propertybean,String> ctype = new TableColumn<>("Construction");
        ctype.setCellValueFactory(new PropertyValueFactory<>("consttype"));

        TableColumn<propertybean,String> approved = new TableColumn<>("Approved By");
        approved.setCellValueFactory(new PropertyValueFactory<>("approvedby"));

        TableColumn<propertybean,String> price = new TableColumn<>("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<propertybean,String> info = new TableColumn<>("Other Info");
        info.setCellValueFactory(new PropertyValueFactory<>("otherinfo"));

        // Image 1
        TableColumn<propertybean,byte[]> pic1 = new TableColumn<>("Image 1");
        pic1.setCellValueFactory(new PropertyValueFactory<>("pic1"));
        pic1.setCellFactory(col -> new TableCell<>() {

            private final ImageView img = new ImageView();

            {
                img.setFitHeight(120);
                img.setFitWidth(120);
                img.setPreserveRatio(true);
            }

            @Override
            protected void updateItem(byte[] item, boolean empty) {
                super.updateItem(item, empty);

                if(empty || item==null){
                    setGraphic(null);
                }else{
                    img.setImage(new Image(new ByteArrayInputStream(item)));
                    setGraphic(img);
                }
            }
        });

        // Image 2
        TableColumn<propertybean,byte[]> pic2 = new TableColumn<>("Image 2");
        pic2.setCellValueFactory(new PropertyValueFactory<>("pic2"));
        pic2.setCellFactory(col -> new TableCell<>() {

            private final ImageView img = new ImageView();

            {
                img.setFitHeight(120);
                img.setFitWidth(120);
                img.setPreserveRatio(true);
            }

            @Override
            protected void updateItem(byte[] item, boolean empty) {
                super.updateItem(item, empty);

                if(empty || item==null){
                    setGraphic(null);
                }else{
                    img.setImage(new Image(new ByteArrayInputStream(item)));
                    setGraphic(img);
                }
            }
        });

        table.getColumns().addAll(
                rid,
                mobile,
                location,
                area,
                city,
                size,
                front,
                rear,
                left,
                right,
                facing,
                type,
                ctype,
                approved,
                price,
                info,
                pic1,
                pic2
        );

        table.setItems(getRecords());
    }
    ObservableList<propertybean> getRecords() {

        ObservableList<propertybean> list = FXCollections.observableArrayList();

        try {

            PreparedStatement pst = con.prepareStatement("select * from properties where city=? AND area=? AND proptype=? AND consttype=? AND price>=? AND price<=?");
            pst.setString(1,cmbCity.getSelectionModel().getSelectedItem());
            pst.setString(2,cmbArea.getSelectionModel().getSelectedItem());
            pst.setString(3,cmbType.getSelectionModel().getSelectedItem());
            pst.setString(4,cmbStructure.getSelectionModel().getSelectedItem());
            pst.setString(5,txtMin.getText());
            pst.setString(6,txtMax.getText());
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {

                propertybean obj = new propertybean();

                obj.setRid(rs.getInt("rid"));
                obj.setMobile(rs.getString("mobile"));
                obj.setLocation(rs.getString("location"));
                obj.setArea(rs.getString("area"));
                obj.setCity(rs.getString("city"));
                obj.setSize(rs.getFloat("size"));
                obj.setFront(rs.getFloat("front"));
                obj.setRear(rs.getFloat("rear"));
                obj.setLeftside(rs.getFloat("leftside"));
                obj.setRightside(rs.getFloat("rightside"));
                obj.setFacing(rs.getString("facing"));
                obj.setProptype(rs.getString("proptype"));
                obj.setConsttype(rs.getString("consttype"));
                obj.setApprovedby(rs.getString("approvedby"));
                obj.setPrice(rs.getString("price"));
                obj.setOtherinfo(rs.getString("otherinfo"));

                obj.setPic1(rs.getBytes("pic1"));
                obj.setPic2(rs.getBytes("pic2"));

                list.add(obj);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    @FXML
    void initialize() {
        doconnect();
        String porptype[]={"Select","Commercial","Residential","Agricultural"};
        cmbType.getItems().addAll(porptype);
        cmbType.getSelectionModel().select(0);

        String struct[]={"Select","Plot","Constructed"};
        cmbStructure.getItems().addAll(struct);
        cmbStructure.getSelectionModel().select(0);
        try{
            PreparedStatement pst=con.prepareStatement("select distinct city from properties");
            ResultSet rs=pst.executeQuery();
            cmbCity.getItems().add("Select");
            cmbCity.getSelectionModel().select(0);
            while(rs.next()) {
                String city=rs.getString("city");
                cmbCity.getItems().add(city);
            }

            pst=con.prepareStatement("select distinct area from properties");
            rs=pst.executeQuery();
            cmbArea.getItems().add("Select");
            cmbArea.getSelectionModel().select(0);
            while(rs.next()) {
                String area=rs.getString("area");
                cmbArea.getItems().add(area);
            }


        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    Connection con;
    public void doconnect() {
        con= jdbcController.DatabaseConnection.connectToDb();
        if(con==null){
            System.out.println("Connection Failed! Check output console");
        }
        else {
            System.out.println("All is well");
        }
    }
}
