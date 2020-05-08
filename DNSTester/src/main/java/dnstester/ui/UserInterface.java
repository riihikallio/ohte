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

/**
 * Application user interface class
 */
public class UserInterface extends Application {

    /**
     * start override for JavaFX
     *
     * @param stage JaveFX stage
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("DNS Tester");

        final VBox layout = new VBox(5);
        layout.setPadding(new Insets(10));
        layout.getChildren().add(new Label("DNS Server IP address:"));
        TextField server = new TextField("8.8.8.8");
        layout.getChildren().add(server);
        CheckBox recursive = new CheckBox("Recursive query");
        layout.getChildren().add(recursive);
        layout.getChildren().add(new Label("Domain name to test with:"));
        TextField name = new TextField("www.example.com");
        layout.getChildren().add(name);

        Button tstBtn = new Button("Test");
        tstBtn.setDefaultButton(true);
        Label timeLbl = new Label("Response time: ");
        tstBtn.setOnAction((e) -> buttonPress(server.getText(), recursive.selectedProperty().getValue(), name.getText(), timeLbl));
        layout.getChildren().add(tstBtn);
        layout.getChildren().add(timeLbl);
        
        Button histBtn = new Button("Show history");
        histBtn.setStyle("-fx-font-size: 8pt;");
        histBtn.setOnAction((e) -> History.open(server.getText()));
        layout.getChildren().add(histBtn);
        
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Private function to handle the Test button
     *
     * @param server Server to test
     * @param recursive Boolean option for recursion
     * @param name DNS name to use for test
     * @param label The label to use for the result
     */
    private void buttonPress(String server, boolean recursive, String name, Label label) {
        Tester tester = new Tester();
        if (server.length() > 0 && name.length() > 0) {
            TestResult result = tester.sendQuery(server, recursive, name);
            if (result.fail) {
                Alert a = new Alert(AlertType.ERROR, result.error);
                a.show();
            } else {
                if (result.lost) {
                    label.setText("Response time: LOST");
                } else {
                    label.setText("Response time: " + result.duration + " ms");
                }
            }
        }
    }

    /**
     * Actual Main class to launch the JavaFX application
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
