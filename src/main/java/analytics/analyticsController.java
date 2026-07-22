package analytics;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import jdbc.jdbcController;

public class analyticsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PieChart customerChart;

    @FXML
    private PieChart dealChart;

    @FXML
    private PieChart propertyTypeChart;

    @FXML
    private PieChart structureChart;

    @FXML
    void initialize() {
        try{
            Connection con= jdbcController.DatabaseConnection.connectToDb();
            PreparedStatement pst=con.prepareStatement("SELECT ctype,count(*) as 'count' from customers group by ctype");
            ResultSet rs= pst.executeQuery();
            ObservableList<PieChart.Data> data= FXCollections.observableArrayList();
            while(rs.next()){
                String ctype=rs.getString("ctype");
                int count=rs.getInt("count");
                data.add(new PieChart.Data(ctype,count));

            }
            customerChart.setData(data);

            pst=con.prepareStatement("SELECT proptype,count(*) as 'count' from properties group by proptype");
            rs= pst.executeQuery();
            ObservableList<PieChart.Data> data1= FXCollections.observableArrayList();
            while(rs.next()){
                String type=rs.getString("proptype");
                int count=rs.getInt("count");
                data1.add(new PieChart.Data(type,count));

            }
            propertyTypeChart.setData(data1);

            pst=con.prepareStatement("SELECT consttype,count(*) as 'count' from properties group by consttype");
            rs= pst.executeQuery();
            ObservableList<PieChart.Data> data2= FXCollections.observableArrayList();
            while(rs.next()){
                String type=rs.getString("consttype");
                int count=rs.getInt("count");
                data2.add(new PieChart.Data(type,count));

            }
            structureChart.setData(data2);

            pst=con.prepareStatement("SELECT status,count(*) as 'count' from deals group by status");
            rs= pst.executeQuery();
            ObservableList<PieChart.Data> data3= FXCollections.observableArrayList();
            while(rs.next()){
                String type=rs.getString("status");
                int count=rs.getInt("count");
                data3.add(new PieChart.Data(type,count));

            }
            dealChart.setData(data3);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
