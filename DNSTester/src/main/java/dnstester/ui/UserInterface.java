package dnstester.ui;

import javafx.application.Application;
import javafx.stage.Stage;

public class UserInterface extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("DNS Tester");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
