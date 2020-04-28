package dnstester.domain;

import java.util.Date;

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
    public long time = 0;
    /**
     * Was it a recursive query
     */
    public boolean recursive = false;
    /**
     * Date and time of the test run
     */
    public Date timestamp = null;
}
