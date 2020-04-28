package dnstester.dao;

import dnstester.domain.TestResult;
import java.sql.*;
import java.util.*;

public interface HistoryDAO<TestResult, String> {
    void add(String server, TestResult result);
    List<TestResult> list(String server);
}
