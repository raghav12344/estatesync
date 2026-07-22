package listProperty;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import jdbc.jdbcController;

public class listPropertyController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private RadioButton radioagriculture;

    @FXML
    private RadioButton radiocommercial;

    @FXML
    private RadioButton radioconstructed;

    @FXML
    private RadioButton radioplot;

    @FXML
    private RadioButton radioresidential;

    @FXML
    private Button btnpic2;

    @FXML
    private ComboBox<String> comboapproved;

    @FXML
    private ComboBox<Integer> comboprop;

    @FXML
    private ToggleGroup condition;

    @FXML
    private ImageView pic1;

    @FXML
    private ImageView pic2;

    @FXML
    private TextField txtadd;

    @FXML
    private TextField txtarea;

    @FXML
    private TextField txtcity;

    @FXML
    private TextField txtdirection;

    @FXML
    private TextField txtfront;

    @FXML
    private TextField txtleft;

    @FXML
    private TextField txtmobileno;

    @FXML
    private TextField txtother;

    @FXML
    private TextField txtprice;

    @FXML
    private TextField txtrear;

    @FXML
    private TextField txtright;

    @FXML
    private TextField txtsize;

    @FXML
    private ToggleGroup type;

    @FXML
    void deleterecord(ActionEvent event) {
        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Delete Customer");
            alert.setContentText("Are you sure you want to delete this customer?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                try{
                    PreparedStatement pst=con.prepareStatement("delete from properties where rid=?");
                    pst.setInt(1,comboprop.getSelectionModel().getSelectedItem());
                    pst.execute();
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Operation Performed");
                    alert1.setContentText("Record deleted successfully");
                    alert1.showAndWait();
                    {
                        clearform();
                        comboprop.getItems().clear();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            } else {
                System.out.println("User clicked Cancel");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void dofetch(ActionEvent event) {
        comboprop.getItems().clear();
        try{
            PreparedStatement pst=con.prepareStatement("select * from properties where mobile=?");
            pst.setString(1,txtmobileno.getText());
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                Integer id=rs.getInt("rid");
//                System.out.println(id);
                comboprop.getItems().add(id);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Operation Complete");
            alert.setContentText("Details fetched successfully");
            alert.showAndWait();


        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Fill Valid Data");
            alert.showAndWait();
        }
    }
    void clearform(){
        String []types={"Select","PUDA","DDA","DTCP","Municipal Corporation","Gram Panchayat","Other"};
        Integer []ids={};
        comboapproved.getItems().addAll(types);
        comboapproved.getSelectionModel().select(0);
//        comboprop.getItems().addAll(ids);
        txtmobileno.clear();
        txtcity.clear();
        txtsize.clear();
        txtfront.clear();
        txtadd.clear();
        txtarea.clear();
        txtrear.clear();
        txtright.clear();
        txtleft.clear();
        txtdirection.clear();
        radioagriculture.setSelected(false);
        radiocommercial.setSelected(false);
        radioconstructed.setSelected(false);
        radioplot.setSelected(false);
        radioresidential.setSelected(false);
        txtprice.clear();
        txtother.clear();
        pic1.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/listPropertyv/img.png"))));
        pic2.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/listPropertyv/img_1.png"))));
    }
    @FXML
    void doupdateform(ActionEvent event) {
        clearform();
            Integer rid=comboprop.getSelectionModel().getSelectedItem();
            try{
                PreparedStatement pst=con.prepareStatement("select * from properties where rid=?");
                pst.setInt(1, rid);
                ResultSet rs=pst.executeQuery();
                rs.next();
                {
                    txtmobileno.setText(rs.getString(2));
                    txtadd.setText(rs.getString(3));
                    txtarea.setText(rs.getString(4));
                    txtcity.setText(rs.getString(5));
                    txtsize.setText(String.valueOf(rs.getFloat(6)));
                    txtfront.setText(String.valueOf(rs.getFloat(7)));
                    txtrear.setText(String.valueOf(rs.getFloat(8)));
                    txtleft.setText(String.valueOf(rs.getFloat(9)));
                    txtright.setText(String.valueOf(rs.getFloat(10)));
                    txtdirection.setText(rs.getString(11));
                    String propType=rs.getString(12);
                    if(propType.equals("Commercial"))
                        radiocommercial.setSelected(true);
                    else if (propType.equals("Residential"))
                        radioresidential.setSelected(true);
                    else if(propType.equals("Agriculture"))
                        radioagriculture.setSelected(true);
                    String constype=rs.getString(13);
                    if(constype.equals("Plot"))
                        radioplot.setSelected(true);
                    else if(constype.equals("Constructed"))
                        radioconstructed.setSelected(true);
                    comboapproved.getSelectionModel().select(rs.getString(14));
                    txtprice.setText(rs.getString(15));
                    txtother.setText(rs.getString(16));

                    InputStream strm = rs.getBinaryStream(17);
                    pic1.setImage(new Image(strm));
                    strm = rs.getBinaryStream(18);
                    if(strm!=null)
                        pic2.setImage(new Image(strm));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Operation Performed");
                    alert.setContentText("Data Fetched Successfully");
                    alert.showAndWait();
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }

    }
    File picref1;
    @FXML
    void savepic1(ActionEvent event) {
        picref1=chooseImage(pic1);
        if(picref1==null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select image 1.");
            alert.showAndWait();
        }
    }
    File picref2;
    @FXML
    void savepic2(ActionEvent event){
        picref2=chooseImage(pic2);
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
    void doclearform(ActionEvent event) {
        clearform();
        comboprop.getItems().clear();
    }
    @FXML
    void saverecord(ActionEvent event) {
        if(picref1==null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Pic1 is not optional");
            alert.showAndWait();
        }
        try{
            PreparedStatement pst=con.prepareStatement("insert into properties values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setNull(1, Types.INTEGER);
            pst.setString(2,txtmobileno.getText());
            pst.setString(3,txtadd.getText());
            pst.setString(4,txtarea.getText());
            pst.setString(5,txtcity.getText());
            pst.setFloat(6,Float.parseFloat(txtsize.getText()));
            pst.setFloat(7,Float.parseFloat(txtfront.getText()));
            pst.setFloat(8,Float.parseFloat(txtrear.getText()));
            pst.setFloat(9,Float.parseFloat(txtleft.getText()));
            pst.setFloat(10,Float.parseFloat(txtright.getText()));
            pst.setString(11,txtdirection.getText());
            String type="";
            if(radiocommercial.isSelected())
                type="Commercial";
            else if (radioresidential.isSelected()) {
                type="Residential";
            }
            else if  (radioagriculture.isSelected()) {
                type="Agriculture";
            }
            pst.setString(12,type);

            String constype="";
            if(radioplot.isSelected()){
                constype="Plot";
            }
            else if(radioconstructed.isSelected()){
                constype="Constructed";
            }
            pst.setString(13,constype);
            pst.setString(14,comboapproved.getSelectionModel().getSelectedItem());
            pst.setFloat(15,Float.parseFloat(txtprice.getText()));
            pst.setString(16,txtother.getText());
            FileInputStream stream=new FileInputStream(picref1);
            pst.setBinaryStream(17,(InputStream) stream,(int)picref1.length());
            if(picref2==null)
            {
                pst.setNull(18,Types.BLOB);
            }
            else {
                stream = new FileInputStream(picref2);
                pst.setBinaryStream(18, (InputStream) stream, (int) picref2.length());
            }
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Operation Complete");
            alert.setContentText("Record Successfully Saved!");
            alert.showAndWait();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Fill Valid Data");
            alert.showAndWait();
        }
    }
    @FXML
    public void updaterecord(ActionEvent event) {

        try {

            Integer rid = comboprop.getSelectionModel().getSelectedItem();

            if (rid == null) {
                new Alert(Alert.AlertType.ERROR, "Please select a property.").showAndWait();
                return;
            }

            PreparedStatement pst = con.prepareStatement(
                    "UPDATE properties SET "
                            + "location=?,"
                            + "area=?,"
                            + "city=?,"
                            + "size=?,"
                            + "front=?,"
                            + "rear=?,"
                            + "leftside=?,"
                            + "rightside=?,"
                            + "facing=?,"
                            + "proptype=?,"
                            + "consttype=?,"
                            + "approvedby=?,"
                            + "price=?,"
                            + "otherinfo=?,"
                            + "pic1=?,"
                            + "pic2=? "
                            + "WHERE rid=?");

            // Basic Details
            pst.setString(1, txtadd.getText());
            pst.setString(2, txtarea.getText());
            pst.setString(3, txtcity.getText());

            pst.setFloat(4, Float.parseFloat(txtsize.getText()));
            pst.setFloat(5, Float.parseFloat(txtfront.getText()));
            pst.setFloat(6, Float.parseFloat(txtrear.getText()));
            pst.setFloat(7, Float.parseFloat(txtleft.getText()));
            pst.setFloat(8, Float.parseFloat(txtright.getText()));

            pst.setString(9, txtdirection.getText());

            // Property Type
            String type = "";

            if (radiocommercial.isSelected()) {
                type = "Commercial";
            } else if (radioresidential.isSelected()) {
                type = "Residential";
            } else if (radioagriculture.isSelected()) {
                type = "Agriculture";
            }

            pst.setString(10, type);

            // Construction Type
            String constype = "";

            if (radioplot.isSelected()) {
                constype = "Plot";
            } else if (radioconstructed.isSelected()) {
                constype = "Constructed";
            }

            pst.setString(11, constype);

            // Approved By
            String approved = comboapproved.getSelectionModel().getSelectedItem();

            if (approved == null)
                approved = "";

            pst.setString(12, approved);

            pst.setFloat(13, Float.parseFloat(txtprice.getText()));

            pst.setString(14, txtother.getText());

            // Picture 1
            if (picref1 != null && picref1.exists()) {
                FileInputStream fis1 = new FileInputStream(picref1);
                pst.setBinaryStream(15, fis1, (int) picref1.length());
            } else {
                pst.setNull(15, Types.BLOB);
            }

            // Picture 2
            if (picref2 != null && picref2.exists()) {
                FileInputStream fis2 = new FileInputStream(picref2);
                pst.setBinaryStream(16, fis2, (int) picref2.length());
            } else {
                pst.setNull(16, Types.BLOB);
            }

            // Record ID
            pst.setInt(17, rid);

            int result = pst.executeUpdate();

            if (result > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Record Updated Successfully.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Update");
                alert.setHeaderText(null);
                alert.setContentText("No record updated.");
                alert.showAndWait();
            }

            pst.close();

        } catch (NumberFormatException e) {

            new Alert(Alert.AlertType.ERROR,
                    "Please enter valid numeric values.").showAndWait();

        } catch (Exception e) {

            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Update Failed");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
    void initialize() {
        doconnect();
        String []types={"Select","PUDA","DDA","DTCP","Municipal Corporation","Gram Panchayat","Other"};
        Integer []ids={};
        comboapproved.getItems().addAll(types);
        comboapproved.getSelectionModel().select(0);
        comboprop.getItems().addAll(ids);
    }
    Connection con;
    public void doconnect(){
        con=jdbcController.DatabaseConnection.connectToDb();
        if(con==null){
            System.out.println("Connection Failed! Check output console");
        }
        else {
            System.out.println("All is well");
        }
    }

}
