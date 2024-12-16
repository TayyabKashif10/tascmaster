module tascmaster {
    requires javafx.fxml;
    requires javafx.web;

//    requires org.controlsfx.controls;
//    requires org.kordamp.ikonli.javafx;
//    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires org.controlsfx.controls;
    opens org.tasc.tascmaster to javafx.fxml;
    exports org.tasc.tascmaster;
    opens org.tasc.tascmaster.Models.Derived to javafx.base;
    opens org.tasc.tascmaster.Controllers to javafx.fxml;
    opens org.tasc.tascmaster.Controllers.OrderProcessing to javafx.fxml;
    opens org.tasc.tascmaster.Models.Core to javafx.base;

}