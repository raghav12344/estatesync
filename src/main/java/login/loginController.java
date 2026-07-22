package login;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import jdbc.jdbcController;

public class loginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField txtPass;

    @FXML
    private TextField txtUser;

    @FXML
    void doLogin(ActionEvent event) {
        try {
            String user = txtUser.getText();
            String pass = txtPass.getText();

            PreparedStatement pst= con.prepareStatement("SELECT * from user where uname=? AND pass=?");
            pst.setString(1, user);
            pst.setString(2, pass);
            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                loaddashboard();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Invalid Username or Password");
                alert.show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    void loaddashboard() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/dashboardv/dashboardview.fxml")));

            Stage stage = (Stage) btnLogin.getScene().getWindow();

            stage.setScene(new Scene(root));

            stage.setTitle("EstateSync");

            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void initialize() {
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'loginView.fxml'.";
        assert txtPass != null : "fx:id=\"txtPass\" was not injected: check your FXML file 'loginView.fxml'.";
        assert txtUser != null : "fx:id=\"txtUser\" was not injected: check your FXML file 'loginView.fxml'.";
        doconnect();
    }
    Connection con=null;
    void doconnect(){
        try {
            con = jdbcController.DatabaseConnection.connectToDb();
            if (con != null) {
                System.out.println("all is well ");
            } else {
                System.out.println("nothing is well ");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

