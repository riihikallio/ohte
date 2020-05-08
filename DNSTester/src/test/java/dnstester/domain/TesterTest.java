/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dnstester.domain;

import dnstester.dao.DBHistoryDAO;
import java.sql.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pera
 */
public class TesterTest {

    private Tester tester;
    static private String startTime;

    public TesterTest() {
    }

    // Record the time test run was started
    @BeforeClass
    public static void setUpClass() {
        Connection conn = null;
        Statement stmt;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:history.db");
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT datetime('now','localtime');");
            rs.next();
            startTime = rs.getString(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    // Delete records created during testing
    @AfterClass
    public static void tearDownClass() {
        Connection conn = null;
        Statement stmt;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:history.db");
            stmt = conn.createStatement();

            // Record the test run end time
            ResultSet rs = stmt.executeQuery("SELECT datetime('now','localtime');");
            rs.next();
            String endTime = rs.getString(1);

            // Delete records
            PreparedStatement pStmt = conn.prepareStatement("DELETE FROM history "
                    + "WHERE Timestamp BETWEEN ? AND ?");
            pStmt.setString(1, startTime);
            pStmt.setString(2, endTime);
            pStmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Before
    public void setUp() {
        tester = new Tester();
    }

    @After
    public void tearDown() {
    }

    @Test(timeout = 1000)
    public void testerCreated() {
        assertNotNull("Tester object not created", tester);
    }

    @Test(timeout = 5000)
    public void testerSuccess() {
        TestResult result = tester.sendQuery("8.8.8.8", false, "www.example.com");
        assertTrue("Zero time elapsed", result.duration > 0);
        assertFalse("Error flag set", result.fail);
        assertFalse("Lost flag set", result.lost);
    }

    @Test(timeout = 5000)
    public void recursiveSuccess() {
        TestResult result = tester.sendQuery("8.8.8.8", true, "www.example.com");
        assertTrue("Zero time elapsed", result.duration > 0);
        assertFalse("Error flag set", result.fail);
        assertFalse("Lost flag set", result.lost);
    }

    @Test
    public void historyGrows() {
        DBHistoryDAO db = new DBHistoryDAO();
        int count = db.list("8.8.4.4").size();
        TestResult result = tester.sendQuery("8.8.4.4", false, "www.example.com");
        assertEquals("Test not recorded in historyDB", count + 1, db.list("8.8.4.4").size());
    }

    @Test(timeout = 1000)
    public void queryNameMissing() {
        TestResult result = tester.sendQuery("8.8.8.8", false, ".");
        assertTrue("Error flag not set", result.fail);
        assertEquals("Empty name not detected", "Empty query name", result.error);
        assertFalse("Lost flag set", result.lost);
    }

    @Test(timeout = 1000)
    public void queryNamePartCheck() {
        TestResult result = tester.sendQuery("8.8.8.8", false, "www..com");
        assertTrue("Error flag not set", result.fail);
        assertEquals("Double dots not detected", "Invalid query name", result.error);
        assertFalse("Lost flag set", result.lost);
    }

    @Test(timeout = 1000)
    public void queryNamePartTooLong() {
        final String name = "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww.example.com";
        TestResult result = tester.sendQuery("8.8.8.8", false, name);
        assertTrue("Error flag not set", result.fail);
        assertEquals("Name part exceeding 63 chars not detected", "Invalid query name", result.error);
        assertFalse("Lost flag set", result.lost);
    }

    @Test(timeout = 1000)
    public void queryNameTooLong() {
        String name = "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww.example.com";
        name = name + "." + name + "." + name + "." + name + "." + name + "." + name + "." + name;
        TestResult result = tester.sendQuery("8.8.8.8", false, name);
        assertTrue("Error flag not set", result.fail);
        assertEquals("Name too long not detected", "Test query name too long", result.error);
        assertFalse("Lost flag set", result.lost);
    }

    @Test(timeout = 1000)
    public void queryServerNotFound() {
        TestResult result = tester.sendQuery("nonexisting", false, "www.example.com");
        assertTrue("Error flag not set", result.fail);
        assertFalse("Lost flag set", result.lost);
    }

    @Test(timeout = 5000)
    public void packetLost() {
        TestResult result = tester.sendQuery("8.8.8.1", false, "www.example.com");
        assertFalse("Error flag set", result.fail);
        assertTrue("Lost flag not set", result.lost);
    }

}
