module tascmaster {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

//    requires org.controlsfx.controls;
//    requires org.kordamp.ikonli.javafx;
//    requires eu.hansolo.tilesfx;
    requires javafx.graphics;
    requires java.sql;
    requires java.desktop;
    opens org.tasc.tascmaster to javafx.fxml;
    exports org.tasc.tascmaster;
    opens org.tasc.tascmaster.Controllers to javafx.fxml;

}