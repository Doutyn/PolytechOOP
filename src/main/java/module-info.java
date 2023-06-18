module com.example.finaltask {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.example.finaltask to javafx.fxml;
    exports com.example.finaltask;
    opens com.example.finaltask.lab1.src to javafx.fxml;
    exports com.example.finaltask.lab1.src;
    opens com.example.finaltask.lab2.src to javafx.fxml;
    exports com.example.finaltask.lab2.src;
    opens com.example.finaltask.lab3.src to javafx.fxml;
    exports com.example.finaltask.lab3.src;
    opens com.example.finaltask.lab4.src to javafx.fxml;
    exports com.example.finaltask.lab4.src;
    opens com.example.finaltask.lab5.src to javafx.fxml;
    exports com.example.finaltask.lab5.src;
    opens com.example.finaltask.lab6.src to javafx.fxml;
    exports com.example.finaltask.lab6.src;
}