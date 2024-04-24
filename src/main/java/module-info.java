module pl.gornik.kontrolkifx {
    requires javafx.controls;
    requires javafx.fxml;


    opens pl.gornik.kontrolkifx to javafx.fxml;
    exports pl.gornik.kontrolkifx;
}