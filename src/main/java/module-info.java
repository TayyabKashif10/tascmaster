module org.tasc.tascmaster {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.net.http;
    requires org.json;
    requires java.prefs;

    opens org.tasc.tascmaster to javafx.fxml;
    exports org.tasc.tascmaster;
    exports org.tasc.tascmaster.Controllers;
    exports org.tasc.tascmaster.Models;
}