package customerMaster;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import jdbc.jdbcController;
public class customerMasterController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView apic;

    @FXML
    private RadioButton bothtype;

    @FXML
    private RadioButton buytype;

    @FXML
    private ImageView ppic;

    @FXML
    private RadioButton seltype;

    @FXML
    private TextField txtaddress;

    @FXML
    private TextField txtcity;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtmobile;

    @FXML
    private TextField txtname;

    @FXML
    private ToggleGroup type;

    @FXML
    void clear(ActionEvent event) {
        txtmobile.clear();
        txtname.clear();
        txtaddress.clear();
        txtemail.clear();
        txtcity.clear();
        buytype.setSelected(false);
        seltype.setSelected(false);
        bothtype.setSelected(false);
        ppic.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/customerMasterv/img.png"))));
        apic.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/customerMasterv/img.png"))));
    }

    @FXML
    void delete(ActionEvent event) {
        if(txtmobile!=null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Delete Customer");
            alert.setContentText("Are you sure you want to delete this customer?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                try{
                    PreparedStatement pst=con.prepareStatement("delete from customers where mobile=?");
                    pst.setString(1, txtmobile.getText());
                    pst.execute();
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Operation Performed");
                    alert1.setContentText("Record deleted successfully");
                    alert1.showAndWait();
                    {
                        txtmobile.clear();
                        txtname.clear();
                        txtaddress.clear();
                        txtemail.clear();
                        txtcity.clear();
                        buytype.setSelected(false);
                        seltype.setSelected(false);
                        bothtype.setSelected(false);
                        ppic.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/customerMasterv/img.png"))));
                        apic.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/customerMasterv/img.png"))));
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            } else {
                System.out.println("User clicked Cancel");
            }
        }

    }

    @FXML
    void fetchdetails(ActionEvent event) {
        if(txtmobile.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please Enter Mobile Number");
            alert.showAndWait();
        }
        else {
            try {
                PreparedStatement pst = con.prepareStatement("select * from customers where mobile=?");
                pst.setString(1, txtmobile.getText());
                ResultSet rs = pst.executeQuery();
                if(rs.next()) {
                    txtname.setText(rs.getString(2));
                    txtaddress.setText(rs.getString(3));
                    txtcity.setText(rs.getString(4));
                    txtemail.setText(rs.getString(5));
                    String type = rs.getString(6);
                    if (type.equals("Both")) {
                        bothtype.setSelected(true);
                    } else if (type.equals("Buyer")) {
                        buytype.setSelected(true);
                    } else if (type.equals("Seller")) {
                        seltype.setSelected(true);
                    }
                    InputStream strm = rs.getBinaryStream(7);
                    ppic.setImage(new Image(strm));
                    strm = rs.getBinaryStream(8);
                    apic.setImage(new Image(strm));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Operation Performed");
                    alert.setContentText("Data Fetched Successfully");
                    alert.showAndWait();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Data not found make a new record");
                    alert.showAndWait();
                    {
                        txtmobile.clear();
                        txtname.clear();
                        txtaddress.clear();
                        txtemail.clear();
                        txtcity.clear();
                        buytype.setSelected(false);
                        seltype.setSelected(false);
                        bothtype.setSelected(false);
                        ppic.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/customerMasterv/img.png"))));
                        apic.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/customerMasterv/img.png"))));
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @FXML
    void save(ActionEvent event) {
        try{
            PreparedStatement pst=con.prepareStatement("insert into customers values(?,?,?,?,?,?,?,?,?)");
            pst.setString(1,txtmobile.getText());
            pst.setString(2,txtname.getText());
            pst.setString(3,txtaddress.getText());
            pst.setString(4,txtcity.getText());
            pst.setString(5,txtemail.getText());
            String ctype="";
            if(seltype.isSelected())
                ctype="Seller";
            else if(buytype.isSelected())
                ctype="Buyer";
            else if(bothtype.isSelected())
                ctype="Both";
            pst.setString(6,ctype);
            File fileimg=new File(pref.getAbsolutePath());
            FileInputStream stream=new FileInputStream(fileimg);
            pst.setBinaryStream(7,(InputStream) stream,(int)fileimg.length() );
            fileimg=new File(aref.getAbsolutePath());
            stream=new FileInputStream(fileimg);
            pst.setBinaryStream(8,(InputStream) stream,(int)fileimg.length() );
            LocalDate local=LocalDate.now();
            java.sql.Date date=java.sql.Date.valueOf(local);
            pst.setDate(9,date);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Operation Performed");
            alert.setContentText("Record saved successfully");
            alert.showAndWait();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }
    boolean profileChanged=false;
    boolean aadhaarChanged=false;
    File aref=null;
    @FXML
    void saveapic(ActionEvent event) {
        aref=chooseImage(apic);
        if(aref==null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select image again to avoid error.");
            alert.showAndWait();
        }
        else
        {
            aadhaarChanged=true;
        }
    }
    File pref;
    @FXML
    void saveppic(ActionEvent event) {
        pref=chooseImage(ppic);
        if(pref==null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select image again to avoid error.");
            alert.showAndWait();
        }
        else{
            profileChanged=true;
        }
    }
    private File chooseImage(ImageView imageView) {

        FileChooser chooser = new FileChooser();

        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Images",
                        "*.png",
                        "*.jpg",
                        "*.jpeg"
                )
        );
        File file = null;
        file = chooser.showOpenDialog(imageView.getScene().getWindow());
        if (file != null) {
            imageView.setImage(new Image(file.toURI().toString()));
        }
        return file;
    }
    @FXML
    void update(ActionEvent event) {

        try {

            String ctype = "";

            if(seltype.isSelected())
                ctype = "Seller";
            else if(buytype.isSelected())
                ctype = "Buyer";
            else if(bothtype.isSelected())
                ctype = "Both";

            PreparedStatement pst;

            // BOTH images changed
            if(profileChanged && aadhaarChanged){

                pst = con.prepareStatement(
                        "update customers set cname=?,address=?,city=?,email=?,ctype=?,pic=?,acard=? where mobile=?");

                pst.setString(1, txtname.getText());
                pst.setString(2, txtaddress.getText());
                pst.setString(3, txtcity.getText());
                pst.setString(4, txtemail.getText());
                pst.setString(5, ctype);

                FileInputStream fis = new FileInputStream(pref);
                pst.setBinaryStream(6, fis, (int)pref.length());

                fis = new FileInputStream(aref);
                pst.setBinaryStream(7, fis, (int)aref.length());

                pst.setString(8, txtmobile.getText());
            }

            // Only profile image changed
            else if(profileChanged){

                pst = con.prepareStatement(
                        "update customers set cname=?,address=?,city=?,email=?,ctype=?,pic=? where mobile=?");

                pst.setString(1, txtname.getText());
                pst.setString(2, txtaddress.getText());
                pst.setString(3, txtcity.getText());
                pst.setString(4, txtemail.getText());
                pst.setString(5, ctype);

                FileInputStream fis = new FileInputStream(pref);
                pst.setBinaryStream(6, fis, (int)pref.length());

                pst.setString(7, txtmobile.getText());
            }

            // Only Aadhaar image changed
            else if(aadhaarChanged){

                pst = con.prepareStatement(
                        "update customers set cname=?,address=?,city=?,email=?,ctype=?,acard=? where mobile=?");

                pst.setString(1, txtname.getText());
                pst.setString(2, txtaddress.getText());
                pst.setString(3, txtcity.getText());
                pst.setString(4, txtemail.getText());
                pst.setString(5, ctype);

                FileInputStream fis = new FileInputStream(aref);
                pst.setBinaryStream(6, fis, (int)aref.length());

                pst.setString(7, txtmobile.getText());
            }

            // No image changed
            else{

                pst = con.prepareStatement(
                        "update customers set cname=?,address=?,city=?,email=?,ctype=? where mobile=?");

                pst.setString(1, txtname.getText());
                pst.setString(2, txtaddress.getText());
                pst.setString(3, txtcity.getText());
                pst.setString(4, txtemail.getText());
                pst.setString(5, ctype);
                pst.setString(6, txtmobile.getText());
            }

            int x = pst.executeUpdate();

            if(x > 0){
                new Alert(Alert.AlertType.INFORMATION,
                        "Record Updated Successfully").showAndWait();

                profileChanged = false;
                aadhaarChanged = false;
            }
            else{
                new Alert(Alert.AlertType.ERROR,
                        "Customer Not Found").showAndWait();
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
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
    @FXML
    void initialize() {
        doconnect();
    }

}
