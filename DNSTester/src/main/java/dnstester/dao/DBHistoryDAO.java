package dnstester.dao;

import dnstester.domain.TestResult;
import java.sql.*;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;

/**
 * A SQLite implementation of HistoryDAO
 */
public class DBHistoryDAO implements HistoryDAO<TestResult, String> {

    private Connection conn;

    /**
     * Create a new database with a table if needed
     */
    public DBHistoryDAO() {
        Statement stmt;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:history.db");
            stmt = conn.createStatement();
            try {
                stmt.execute("CREATE TABLE IF NOT EXISTS history ("
                        + "Server TEXT, "
                        + "Result INTEGER, "
                        + "Lost INTEGER, "
                        + "Timestamp TEXT DEFAULT (datetime('now','localtime')), "
                        + "Recursive INTEGER);");
            } catch (SQLException e) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Error creating table: " + e.getMessage());
                a.show();
            } finally {
                stmt.close();
            }
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Error opening database: " + e.getMessage());
            a.show();
        }
    }

    /**
     * Log a test result in history database
     *
     * @param server The server that was tested
     * @param duration The response time in milliseconds
     * @param lost Was the query lost?
     * @param recursive Was the query recursive?
     */
    @Override
    public void add(String server, long duration, boolean lost, boolean recursive) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO history"
                    + " (Server, Result, Lost, Recursive)"
                    + " VALUES (?, ?, ?, ?)");
            stmt.setString(1, server.trim().toLowerCase());
            stmt.setLong(2, duration);
            stmt.setInt(3, lost ? 1 : 0);
            stmt.setInt(4, recursive ? 1 : 0);

            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Error writing database: " + e.getMessage());
            a.show();
        }
    }

    /**
     * Retrieve the test history for a DNS server
     *
     * @param server The server to query
     * @return An Observable List ready for JavaFX TableView
     */
    @Override
    public ObservableList<HistoryRow> list(String server) {
        ObservableList<HistoryRow> result = FXCollections.observableArrayList();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT Timestamp,Result,Lost,Recursive "
                    + "FROM history WHERE Server=? ORDER BY Timestamp DESC");
            stmt.setString(1, server.trim().toLowerCase());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                result.add(new HistoryRow(
                        rs.getString(1),
                        rs.getBoolean(3) ? "❌" : rs.getString(2) + " ms",
                        rs.getBoolean(4) ? "✔" : ""));
            }
            stmt.close();
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Error reading database: " + e.getMessage());
            a.show();
        }
        return result;
    }
}
