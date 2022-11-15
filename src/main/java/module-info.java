module org.openjfx.hellofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    
    
    opens org.openjfx.hellofx to javafx.fxml;
    exports org.openjfx.hellofx;
}
