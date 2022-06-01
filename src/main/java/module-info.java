module fiveMens {
    requires transitive javafx.controls;
    requires javafx.fxml;

    opens fiveMens to javafx.fxml;
    
    opens fiveMens.utils to javafx.fxml;
    

    exports fiveMens;
}
