package dnstester.dao;

import javafx.collections.ObservableList;
/**
 * A DAO interface for adding and retrieving test history
 * 
 * @param <TestResult> Test result to store
 * @param <String> Server to fetch history for
 */
public interface HistoryDAO<TestResult, String> {
    void add(String server, long duration, boolean lost, boolean recursive);
    ObservableList<HistoryRow> list(String server);
}
