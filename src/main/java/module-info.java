module com.example.javaproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires java.desktop;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires com.github.librepdf.openpdf;

    opens com.example.javaproject to javafx.fxml;
    exports com.example.javaproject;
    opens listProperty to javafx.fxml;
    exports listProperty;
    opens customerMaster to javafx.fxml;
    exports customerMaster;
    opens Deals to  javafx.fxml;
    exports Deals;
    opens browserCustomer to  javafx.fxml;
    exports browserCustomer;
    opens propertyFinder to  javafx.fxml;
    exports propertyFinder;
    opens login to javafx.fxml;
    exports login;
    opens dashboard to  javafx.fxml;
    exports dashboard;
    opens analytics to  javafx.fxml;
    exports analytics;
    opens dealsFinder to  javafx.fxml;
    exports dealsFinder;
}