module com.shreyas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires org.apache.logging.log4j;
    requires org.json;


    opens com.shreyas to javafx.fxml;
    exports com.shreyas;
}