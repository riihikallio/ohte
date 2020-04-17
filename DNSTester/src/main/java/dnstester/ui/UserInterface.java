package dnstester.ui;

import dnstester.domain.Tester;
import dnstester.domain.TestResult;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
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
        TextField server = new TextField("8.8.8.8");
        layout.getChildren().add(server);
        CheckBox recursive = new CheckBox("Recursive query");
        layout.getChildren().add(recursive);
        layout.getChildren().add(new Label("Domain name to test with:"));
        TextField name = new TextField("www.example.com");
        layout.getChildren().add(name);

        Button btn = new Button("Test");
        Label timeLbl = new Label("Response time: ");
        btn.setOnAction((e) -> buttonPress(server.getText(), recursive.selectedProperty().getValue(), name.getText(), timeLbl));
        layout.getChildren().add(btn);
        layout.getChildren().add(timeLbl);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }

    private void buttonPress(String server, boolean recursive, String name, Label label) {
        Tester tester = new Tester();
        TestResult result = new TestResult();
        if (server.length() > 0 && name.length() > 0) {
            result = tester.sendQuery(server, recursive, name);
            if (result.fail) {
                Alert a = new Alert(AlertType.ERROR, result.error);
                a.show();
            } else {
                if (result.lost) {
                    label.setText("Response time: LOST");
                } else {
                    label.setText("Response time: " + result.time + " ms");
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
