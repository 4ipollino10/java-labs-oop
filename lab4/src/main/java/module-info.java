module ru.gulyaev.factory.lab4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.gulyaev.factory.lab4 to javafx.fxml;
    exports ru.gulyaev.factory.lab4;
}