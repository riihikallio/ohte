import dnstester.domain.TestResult;
import java.sql.*;
import java.util.*;

public interface HistoryDAO<TestResult, String> {
    void add(TestResult result) throws SQLException;
    List<TestResult> list(String server) throws SQLException;
}
