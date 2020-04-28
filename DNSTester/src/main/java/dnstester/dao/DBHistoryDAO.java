/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dnstester.dao;

import dnstester.domain.TestResult;
import java.sql.*;
import java.util.List;

/**
 *
 * @author pera
 */
public class DBHistoryDAO implements HistoryDAO<TestResult, String> {

    Connection conn;

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
                System.out.println("Error creating table: " + e.getMessage());
            } finally {
                stmt.close();
            }
        } catch (Exception e) {
            System.out.println("Error opening database: " + e.getMessage());
        }
    }

    @Override
    public void add(String server, TestResult result) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO history"
                    + " (Server, Result, Lost, Recursive)"
                    + " VALUES (?, ?, ?, ?)");
            stmt.setString(1, server);
            stmt.setLong(2, result.time);
            stmt.setInt(3, result.lost ? 1 : 0);
            stmt.setInt(4, result.recursive ? 1 : 0);

            stmt.executeUpdate();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error writing database: " + e.getMessage());
        }
    }

    @Override
    public List<TestResult> list(String server) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
