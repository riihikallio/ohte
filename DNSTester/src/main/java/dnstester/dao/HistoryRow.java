package dnstester.dao;

import javafx.beans.property.SimpleStringProperty;

/**
 * A record class for an ObservableList in the history TableView
 */
public class HistoryRow {

    private final SimpleStringProperty date;
    private final SimpleStringProperty duration;
    private final SimpleStringProperty recursive;

    /**
     * HistoryRow constuctor
     * Note that the constructor takes three Strings
     * 
     * @param date Date and time of the test
     * @param duration The server response time
     * @param recursive Whether the DNS query was recursive or not
     */
    public HistoryRow(String date, String duration, String recursive) {
        this.date = new SimpleStringProperty(date);
        this.duration = new SimpleStringProperty(duration);
        this.recursive = new SimpleStringProperty(recursive);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String s) {
        date.set(s);
    }

    public String getDuration() {
        return duration.get();
    }

    public void setDuration(String s) {
        duration.set(s);
    }

    public String getRecursive() {
        return recursive.get();
    }

    public void setRecursive(String s) {
        recursive.set(s);
    }
}
