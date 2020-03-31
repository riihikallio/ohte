package dnstester.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class UserInterface extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("DNS Tester");
        
        VBox layout = new VBox(5);
        layout.setPadding(new Insets(10));
        layout.getChildren().add(new Label("DNS Server IP address:"));
        layout.getChildren().add(new TextField("8.8.8.8"));
        layout.getChildren().add(new Label("Domain name to test with:"));
        layout.getChildren().add(new TextField("www.example.com"));
        layout.getChildren().add(new Button("Test"));
        
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
