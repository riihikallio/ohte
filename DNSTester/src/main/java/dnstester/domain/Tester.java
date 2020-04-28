package dnstester.domain;

import dnstester.dao.DBHistoryDAO;
import dnstester.dao.HistoryDAO;
import java.net.*;

/**
 * The actual tester class
 */
public class Tester {

    private InetAddress address;
    private String name;
    private byte[] buf;
    private TestResult result;

    /**
     * The method to run the test
     *
     * @param server Server to test
     * @param recursive Recursive query
     * @param name DNS name to use for testing
     *
     * @return TestResult A record containing the result
     */
    public TestResult sendQuery(String server, boolean recursive, String name) {
        result = new TestResult();
        buf = new byte[512];
        // Fixed field at the start
        buf[1] = 1;  // Query ID
        buf[2] = recursive ? (byte) 1 : (byte) 0;  // Recursion Desired
        buf[5] = 1;  // QDCount

        // Stuff the DNS name into the buffer
        int ptr = 12;
        for (String part : name.split("\\.")) {
            buf[ptr] = (byte) part.length();  // Length first
            if (buf[ptr] == 0 || buf[ptr] > 63) {
                result.fail = true;
                result.error = "Invalid query name";
                return result;
            }
            ptr++;
            for (char chr : part.toCharArray()) {
                buf[ptr] = (byte) chr;  // Then the characters
                ptr++;
                if (ptr >= 506) {  // Do not overrun the buffer
                    result.fail = true;
                    result.error = "Test query name too long";
                    return result;
                }
            }
        }
        if (ptr == 12) {
            result.fail = true;
            result.error = "Empty query name";
            return result;
        }
        // Fields after the queried name
        buf[ptr] = 0;      // End of query string
        buf[ptr + 2] = 1;  // QType A record
        buf[ptr + 4] = 1;  // QClass Internet Address

        try {
            // Send query
            address = InetAddress.getByName(server);
            DatagramSocket socket = new DatagramSocket();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 53);
            long start = System.nanoTime();
            socket.send(packet);

            // Wait for response
            buf = new byte[4096];  // Large buffer for recieving
            packet = new DatagramPacket(buf, buf.length);
            socket.setSoTimeout(3000);
            socket.receive(packet);
            result.time = Math.round((System.nanoTime() - start) / 1e6);
        } catch (SocketTimeoutException e) {
            result.lost = true;
        } catch (Exception e) {
            result.fail = true;
            result.error = e.getMessage();
        }
        
        if (!result.fail) {
            HistoryDAO history = new DBHistoryDAO();
            result.recursive = recursive;
            history.add(server, result);
        }
        return result;
    }

}
