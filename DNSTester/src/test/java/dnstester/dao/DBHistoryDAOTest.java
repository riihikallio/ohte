package dnstester.dao;

import java.sql.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pera
 */
public class DBHistoryDAOTest {

    public DBHistoryDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    // Delete records created in testing
    @AfterClass
    public static void tearDownClass() {
        Connection conn = null;
        Statement stmt;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:history.db");
            stmt = conn.createStatement();
            stmt.execute("DELETE FROM history WHERE Result > 9000;");
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
    }

    @After
    public void tearDown() {
    }

    @Test
    public void history() {
        DBHistoryDAO db = new DBHistoryDAO();
        int count = db.list("Z").size();
        db.add("Z", 9999, true, true);
        assertEquals("Test not recorded in historyDB", count + 1, db.list("Z").size());
    }
}
