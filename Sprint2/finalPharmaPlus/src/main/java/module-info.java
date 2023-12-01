module com.mycompany.finalpharmaplus {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    
    opens modelo to javafx.base;
    opens com.mycompany.finalpharmaplus to javafx.fxml;
    exports com.mycompany.finalpharmaplus;
}
