module org.tasc.tascmaster {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;

    opens org.tasc.tascmaster to javafx.fxml;
    exports org.tasc.tascmaster;
}