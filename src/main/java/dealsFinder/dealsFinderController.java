package dealsFinder;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import org.apache.poi.ss.usermodel.Row;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import jdbc.jdbcController;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dealsFinderController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnExcel;

    @FXML
    private Button btnFetch;

    @FXML
    private Button btnPdf;

    @FXML
    private TableView<dealsBean> dealTable;

    @FXML
    private RadioButton finalized;

    @FXML
    private RadioButton pending;

    @FXML
    private DatePicker regDate;

    @FXML
    void createPdf(ActionEvent event) {

        try {

            FileChooser chooser = new FileChooser();

            chooser.setTitle("Save Deals PDF");

            chooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter(
                            "PDF Files",
                            "*.pdf"
                    )
            );


            File file = chooser.showSaveDialog(null);


            if(file==null)
                return;



            Document document = new Document(PageSize.A4.rotate());


            PdfWriter.getInstance(
                    document,
                    new FileOutputStream(file)
            );


            document.open();


            Font font =
                    FontFactory.getFont(
                            FontFactory.HELVETICA_BOLD,
                            18
                    );


            Paragraph title =
                    new Paragraph(
                            "Deals Report",
                            font
                    );


            title.setAlignment(
                    Element.ALIGN_CENTER
            );


            document.add(title);

            document.add(
                    new Paragraph("\n")
            );



            PdfPTable table =
                    new PdfPTable(
                            dealTable.getColumns().size()
                    );


            // Table Header

            for(TableColumn column : dealTable.getColumns())
            {

                PdfPCell cell =
                        new PdfPCell(
                                new Phrase(
                                        column.getText()
                                )
                        );


                table.addCell(cell);
            }



            // Table Data

            for(dealsBean d : dealTable.getItems())
            {

                table.addCell(
                        String.valueOf(d.getPid())
                );

                table.addCell(
                        d.getSeller()
                );

                table.addCell(
                        d.getSellercontact()
                );

                table.addCell(
                        d.getBuyer()
                );

                table.addCell(
                        d.getBuyercontact()
                );

                table.addCell(
                        d.getFinalamount()
                );

                table.addCell(
                        d.getDowmpmt()
                );

                table.addCell(
                        String.valueOf(
                                d.getDtofdeal()
                        )
                );

                table.addCell(
                        String.valueOf(
                                d.getDtofreg()
                        )
                );

                table.addCell(
                        d.getTotalcom()
                );

                table.addCell(
                        d.getAdv()
                );

                table.addCell(
                        d.getBal()
                );

                table.addCell(
                        d.getStatus()
                );

            }



            document.add(table);


            document.close();



            Alert alert =
                    new Alert(
                            Alert.AlertType.INFORMATION
                    );

            alert.setContentText(
                    "PDF Created Successfully"
            );

            alert.show();



        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @FXML
    void fetchDeals(ActionEvent event) {
        String s="";
        if(finalized.isSelected()){
            s="Completed";
        }
        else if(pending.isSelected()){
            s="Pending";
        }
        try{
            PreparedStatement pst=con.prepareStatement("select * from deals where status=? AND dtofreg>=?");
            pst.setString(1,s);
            pst.setDate(2,java.sql.Date.valueOf(regDate.getValue()));
            ResultSet rs=pst.executeQuery();
            {
                dealTable.getItems().clear();

                TableColumn<dealsBean,String> pid=new TableColumn<>("PId");
                pid.setCellValueFactory(new PropertyValueFactory<>("pid"));
                pid.setMinWidth(70);

                TableColumn<dealsBean,String> seller=new TableColumn<>("Seller");
                seller.setCellValueFactory(new PropertyValueFactory<>("seller"));
                seller.setMinWidth(120);

                TableColumn<dealsBean,String> sellercontact=new TableColumn<>("Seller Contact");
                sellercontact.setCellValueFactory(new PropertyValueFactory<>("sellercontact"));
                sellercontact.setMinWidth(120);

                TableColumn<dealsBean,String> buyer=new TableColumn<>("Buyer");
                buyer.setCellValueFactory(new PropertyValueFactory<>("buyer"));
                buyer.setMinWidth(120);

                TableColumn<dealsBean,String> buyercontact=new TableColumn<>("Buyer Contact");
                buyercontact.setCellValueFactory(new PropertyValueFactory<>("buyercontact"));
                buyercontact.setMinWidth(120);

                TableColumn<dealsBean,String> finalamount=new TableColumn<>("Final Amount");
                finalamount.setCellValueFactory(new PropertyValueFactory<>("finalamount"));
                finalamount.setMinWidth(120);

                TableColumn<dealsBean,String> dowmpmt=new TableColumn<>("Down Payment");
                dowmpmt.setCellValueFactory(new PropertyValueFactory<>("dowmpmt"));
                dowmpmt.setMinWidth(120);

                TableColumn<dealsBean, Date> dtofdeal=new TableColumn<>("Date of Deal");
                dtofdeal.setCellValueFactory(new PropertyValueFactory<>("dtofdeal"));
                dtofdeal.setMinWidth(120);

                TableColumn<dealsBean,Date> dtofreg=new TableColumn<>("Date of Registry");
                dtofreg.setCellValueFactory(new PropertyValueFactory<>("dtofreg"));
                dtofreg.setMinWidth(120);

                TableColumn<dealsBean,String> totalcom=new TableColumn<>("Total Commission");
                totalcom.setCellValueFactory(new PropertyValueFactory<>("totalcom"));
                totalcom.setMinWidth(120);

                TableColumn<dealsBean,String> adv=new TableColumn<>("Advance");
                adv.setCellValueFactory(new PropertyValueFactory<>("adv"));
                adv.setMinWidth(120);

                TableColumn<dealsBean,String> bal=new TableColumn<>("Remaining Balance");
                bal.setCellValueFactory(new PropertyValueFactory<>("bal"));
                bal.setMinWidth(120);

                TableColumn<dealsBean,String> status=new TableColumn<>("Status");
                status.setCellValueFactory(new PropertyValueFactory<>("status"));
                status.setMinWidth(120);

                dealTable.getColumns().addAll(pid,seller,sellercontact,buyer,buyercontact,finalamount,dowmpmt,dtofdeal,dtofreg,totalcom,adv,bal,status);

            }
            ObservableList<dealsBean> list= FXCollections.observableArrayList();
            {
                while(rs.next()){
                    dealsBean obj=new dealsBean();
                    obj.setPid(rs.getInt("pid"));
                    obj.setSeller(rs.getString("seller"));
                    obj.setSellercontact(rs.getString("sellercontact"));
                    obj.setBuyer(rs.getString("buyer"));
                    obj.setBuyercontact(rs.getString("buyercontact"));
                    obj.setFinalamount(rs.getString("finalamount"));
                    obj.setDowmpmt(rs.getString("dowmpmt"));
                    obj.setDtofdeal(rs.getDate("dtofdeal").toLocalDate());
                    obj.setDtofreg(rs.getDate("dtofreg").toLocalDate());
                    obj.setTotalcom(rs.getString("totalcom"));
                    obj.setAdv(rs.getString("adv"));
                    obj.setBal(rs.getString("bal"));
                    obj.setStatus(rs.getString("status"));

                    list.add(obj);
                }
            }
            dealTable.setItems(list);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void exporttoexcel(ActionEvent event) {
        try{

            FileChooser chooser=new FileChooser();

            chooser.setTitle("Save Excel");

            chooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter(
                            "Excel Files","*.xlsx")
            );


            File file=chooser.showSaveDialog(null);


            if(file==null)
                return;


            Workbook workbook=new XSSFWorkbook();

            Sheet sheet=workbook.createSheet("Deals");


            // Header Row

            Row header=sheet.createRow(0);


            String[] heads={
                    "PID",
                    "Seller",
                    "Seller Contact",
                    "Buyer",
                    "Buyer Contact",
                    "Final Amount",
                    "Down Payment",
                    "Deal Date",
                    "Registry Date",
                    "Commission",
                    "Advance",
                    "Balance",
                    "Status"
            };


            for(int i=0;i<heads.length;i++)
            {
                Cell cell=header.createCell(i);
                cell.setCellValue(heads[i]);
            }



            int row=1;


            for(dealsBean d:dealTable.getItems())
            {

                Row r=sheet.createRow(row++);


                r.createCell(0).setCellValue(d.getPid());
                r.createCell(1).setCellValue(d.getSeller());
                r.createCell(2).setCellValue(d.getSellercontact());
                r.createCell(3).setCellValue(d.getBuyer());
                r.createCell(4).setCellValue(d.getBuyercontact());
                r.createCell(5).setCellValue(d.getFinalamount());
                r.createCell(6).setCellValue(d.getDowmpmt());
                r.createCell(7).setCellValue(String.valueOf(d.getDtofdeal()));
                r.createCell(8).setCellValue(String.valueOf(d.getDtofreg()));
                r.createCell(9).setCellValue(d.getTotalcom());
                r.createCell(10).setCellValue(d.getAdv());
                r.createCell(11).setCellValue(d.getBal());
                r.createCell(12).setCellValue(d.getStatus());

            }



            for(int i=0;i<heads.length;i++)
            {
                sheet.autoSizeColumn(i);
            }


            FileOutputStream fos=
                    new FileOutputStream(file);

            workbook.write(fos);

            fos.close();

            workbook.close();



            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Excel Exported Successfully");
            a.show();


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        doconnect();
    }
    Connection con=null;
    void doconnect(){
        try{
            con= jdbcController.DatabaseConnection.connectToDb();
            if(con!=null){
                System.out.println("Connected to database successfully");
            }
            else {
                System.out.println("Failed to connect to database");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
