package dashboard;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class dashboardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnlogout;

    @FXML
    private Button btnadddeal;

    @FXML
    private Button btnaddprop;

    @FXML
    private Button btnanalytics;

    @FXML
    private Button btnmanagecustomers;

    @FXML
    private Button btnviewcustomers;

    @FXML
    private Button btnviewdeals;

    @FXML
    private Button btnviewprop;

    @FXML
    void adddeal(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dealsv/dealsview.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("EstateSync");
            stage.setScene(new Scene(root));
            stage.setResizable(true);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logout(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Logout Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");


        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");


        alert.getButtonTypes().setAll(
                yesButton,
                noButton
        );


        Optional<ButtonType> result = alert.showAndWait();


        if(result.isPresent() && result.get() == yesButton)
        {
            try
            {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/loginv/loginView.fxml")));
                Stage stage = (Stage) btnlogout.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("EstateSync");
                stage.show();

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void addprop(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/listPropertyv/listpropertyview.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("EstateSync");
            stage.setScene(new Scene(root));
            stage.setResizable(true);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void analyticsdata(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/analyticsv/analyticsView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("EstateSync");
            stage.setScene(new Scene(root));
            stage.setResizable(true);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void managecustomers(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/customerMasterv/customerMasterview.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("EstateSync");
            stage.setScene(new Scene(root));
            stage.setResizable(true);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void viewcustomers(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/browserCustomerv/browserCustomerView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("EstateSync");
            stage.setScene(new Scene(root));
            stage.setResizable(true);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void viewdeals(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dealsFinderv/DealsFinderView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("EstateSync");
            stage.setScene(new Scene(root));
            stage.setResizable(true);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void viewprop(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/propertyFinderv/propertyFinderview.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("EstateSync");
            stage.setScene(new Scene(root));
            stage.setResizable(true);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert btnadddeal != null : "fx:id=\"btnadddeal\" was not injected: check your FXML file 'dashboardview.fxml'.";
        assert btnaddprop != null : "fx:id=\"btnaddprop\" was not injected: check your FXML file 'dashboardview.fxml'.";
        assert btnanalytics != null : "fx:id=\"btnanalytics\" was not injected: check your FXML file 'dashboardview.fxml'.";
        assert btnmanagecustomers != null : "fx:id=\"btnmanagecustomers\" was not injected: check your FXML file 'dashboardview.fxml'.";
        assert btnviewcustomers != null : "fx:id=\"btnviewcustomers\" was not injected: check your FXML file 'dashboardview.fxml'.";
        assert btnviewdeals != null : "fx:id=\"btnviewdeals\" was not injected: check your FXML file 'dashboardview.fxml'.";
        assert btnviewprop != null : "fx:id=\"btnviewprop\" was not injected: check your FXML file 'dashboardview.fxml'.";

    }

}
