package dnstester.domain;

import java.sql.Timestamp;

/**
 * Record object to hold the test result
 */
public class TestResult {

    /**
     * Did the test end in an error
     */
    public boolean fail = false;
    /**
     * In case of error, the error message
     */
    public String error;
    /**
     * Was the packet lost (i.e. no reply)
     */
    public boolean lost = false;
    /**
     * Response time in milliseconds
     */
    public long duration = 0;
}
