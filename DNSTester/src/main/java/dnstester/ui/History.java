/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dnstester.ui;

import dnstester.dao.DBHistoryDAO;
import dnstester.dao.HistoryRow;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Class to display history window
 *
 */
public class History {

    /**
     * Create a new history window
     *
     * @param server Which server to display
     */
    static public void open(String server) {
        final Stage stage = new Stage();
        server = server.trim().toLowerCase();
        stage.setTitle("History of " + server);
        stage.setWidth(300);
        stage.setHeight(500);

        final TableColumn dateCol = new TableColumn("Date");
        dateCol.setPrefWidth(150);
        dateCol.setCellValueFactory(new PropertyValueFactory<HistoryRow, String>("date"));

        final TableColumn timeCol = new TableColumn("Time");
        timeCol.setPrefWidth(80);
        timeCol.setStyle("-fx-alignment: CENTER;");
        timeCol.setCellValueFactory(new PropertyValueFactory<HistoryRow, String>("duration"));

        final TableColumn recursiveCol = new TableColumn("R");
        recursiveCol.setPrefWidth(20);
        recursiveCol.setStyle("-fx-alignment: CENTER;");
        recursiveCol.setCellValueFactory(new PropertyValueFactory<HistoryRow, String>("recursive"));

        final TableView table = new TableView();
        table.setItems(new DBHistoryDAO().list(server));
        table.getColumns().addAll(dateCol, timeCol, recursiveCol);

        final VBox layout = new VBox(5);
        layout.setPadding(new Insets(10));
        layout.getChildren().add(table);

        final Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }
}
