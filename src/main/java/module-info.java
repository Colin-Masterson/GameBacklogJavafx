module net.colinmasterson.gamebacklogjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens net.colinmasterson.gamebacklogjavafx to javafx.fxml;
    exports net.colinmasterson.gamebacklogjavafx;
}