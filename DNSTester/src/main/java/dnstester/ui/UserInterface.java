package dnstester.ui;

import dnstester.domain.Tester;
import dnstester.domain.TestResult;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class UserInterface extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("DNS Tester");

        VBox layout = new VBox(5);
        layout.setPadding(new Insets(10));
        layout.getChildren().add(new Label("DNS Server IP address:"));
        TextField server = new TextField("172.16.0.9");
        layout.getChildren().add(server);
        layout.getChildren().add(new Label("Domain name to test with:"));
        TextField name = new TextField("www.example.com");
        layout.getChildren().add(name);

        Button btn = new Button("Test");
        btn.setOnAction((e) -> buttonPress(server.getText(), name.getText()));
        layout.getChildren().add(btn);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }

    private void buttonPress(String server, String name) {
        Tester tester = new Tester();
        TestResult result = new TestResult();
        if (server.length() > 0 && name.length() > 0) {
            result = tester.sendQuery(server, name);
            Alert a = new Alert(AlertType.INFORMATION, "Time: "+result.time+"ms");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
