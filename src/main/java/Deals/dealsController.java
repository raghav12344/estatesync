package Deals;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import jdbc.jdbcController;

public class dealsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField buyname;

    @FXML
    private TextField buynumber;

    @FXML
    private DatePicker datedeal;

    @FXML
    private DatePicker datereg;

    @FXML
    private TextField propid;

    @FXML
    private RadioButton radiocancelled;

    @FXML
    private RadioButton radiocompleted;

    @FXML
    private RadioButton radioongoing;

    @FXML
    private TextField selname;

    @FXML
    private TextField selnumber;

    @FXML
    private ToggleGroup status;

    @FXML
    private TextField txtbal;

    @FXML
    private TextField txtcom;

    @FXML
    private TextField txtdown;

    @FXML
    private TextField txtfinal;

    @FXML
    private TextField txtrec;

    @FXML
    void dofetch(ActionEvent event) {
        try{
            PreparedStatement pst=con.prepareStatement("select customers.cname,properties.mobile" +
                    " from properties INNER JOIN customers ON  properties.mobile=customers.mobile" +
                    " where properties.rid=?");
            pst.setInt(1, Integer.parseInt(propid.getText()));
            ResultSet rs= pst.executeQuery();
            while(rs.next()){
                selname.setText(rs.getString(1));
                selnumber.setText(rs.getString(2));
            }
            {
                buyname.clear();
                buynumber.clear();
                txtfinal.clear();
                txtdown.clear();
                datedeal.getEditor().clear();
                datereg.getEditor().clear();
                txtcom.clear();
                txtrec.clear();
                txtbal.clear();
                radiocompleted.setSelected(false);
                radioongoing.setSelected(false);
                radiocancelled.setSelected(false);
            }
            pst= con.prepareStatement("select * from deals where pid=?");
            pst.setInt(1, Integer.parseInt(propid.getText()));
            rs=pst.executeQuery();
            while(rs.next()){
                selname.setText(rs.getString(2));
                selnumber.setText(rs.getString(3));
                buyname.setText(rs.getString(4));
                buynumber.setText(rs.getString(5));
                txtfinal.setText(rs.getString(6));
                txtdown.setText(rs.getString(7));
                datedeal.setValue(rs.getDate(8).toLocalDate());
                datereg.setValue(rs.getDate(9).toLocalDate());
                txtcom.setText(rs.getString(10));
                txtrec.setText(rs.getString(11));
                txtbal.setText(rs.getString(12));
                String status=rs.getString(13);
                if(status.equals("Completed")){
                    radiocompleted.setSelected(true);
                }
                else if(status.equals("Pending")){
                    radioongoing.setSelected(true);
                }
                else if(status.equals("Cancelled")){
                    radiocancelled.setSelected(true);
                }
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Operation Complete");
            alert.setContentText("Seller details fetched successfully");
            alert.showAndWait();
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Incorrect property id. ");
            alert.showAndWait();
        }
    }

    @FXML
    void domodify(ActionEvent event) {
        try{
            PreparedStatement pst=con.prepareStatement("update deals set seller=?,sellercontact=?,buyer=?,buyercontact=?,finalamount=?,dowmpmt=?,dtofdeal=?,dtofreg=?,totalcom=?,adv=?,bal=?,status=? where pid=?");
            pst.setString(1,selname.getText());
            pst.setString(2,selnumber.getText());
            pst.setString(3,buyname.getText());
            pst.setString(4,buynumber.getText());
            pst.setString(5,txtfinal.getText());
            pst.setString(6,txtdown.getText());
            java.sql.Date date=java.sql.Date.valueOf(datedeal.getValue());
            pst.setDate(7,date);
            pst.setDate(8,java.sql.Date.valueOf(datereg.getValue()));
            pst.setString(9,txtcom.getText());
            pst.setString(10,txtrec.getText());
            pst.setString(11,txtbal.getText());
            String Status="";
            if(radiocancelled.isSelected()) {
                Status = "Cancelled";
                deleteoncancel();
            }
            else if(radiocompleted.isSelected()) {
                Status = "Completed";
                deleteoncomplete();
            }
            else if(radioongoing.isSelected())
                Status="Pending";
            pst.setString(12,Status);
            pst.setInt(13,Integer.parseInt(propid.getText()));
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Operation Complete");
            alert.setContentText("Record Modified successfully");
            alert.showAndWait();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void dosave(ActionEvent event) {
        try{
            PreparedStatement pst=con.prepareStatement("insert into deals values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setInt(1,Integer.parseInt(propid.getText()));
            pst.setString(2,selname.getText());
            pst.setString(3,selnumber.getText());
            pst.setString(4,buyname.getText());
            pst.setString(5,buynumber.getText());
            pst.setString(6,txtfinal.getText());
            pst.setString(7,txtdown.getText());
            java.sql.Date date=java.sql.Date.valueOf(datedeal.getValue());
            pst.setDate(8,date);
            pst.setDate(9,java.sql.Date.valueOf(datereg.getValue()));
            pst.setString(10,txtcom.getText());
            pst.setString(11,txtrec.getText());
            pst.setString(12,txtbal.getText());
            String Status="";
            if(radiocancelled.isSelected()) {
                Status = "Cancelled";
                deleteoncancel();
            }
            else if(radiocompleted.isSelected()) {
                Status = "Completed";
                deleteoncomplete();
            }
            else if(radioongoing.isSelected()) {
                Status = "Pending";
            }
            pst.setString(13,Status);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Operation Complete");
            alert.setContentText("Record Saved successfully");
            alert.showAndWait();
        }
        catch(Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setContentText("Oops! Something went wrong");
            alert.showAndWait();
        }
    }
    void deleteoncancel(){
        try{
            PreparedStatement pst=con.prepareStatement("delete from deals where pid=?");
            pst.setInt(1, Integer.parseInt(propid.getText()));
            pst.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    void deleteoncomplete(){
        try{
            PreparedStatement pst=con.prepareStatement("delete from properties where rid=?");
            pst.setInt(1, Integer.parseInt(propid.getText()));
            pst.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
@FXML
    void initialize() {
        doconnect();
    }
    Connection con=null;
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
