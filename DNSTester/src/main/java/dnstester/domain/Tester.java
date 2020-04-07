package dnstester.domain;

import java.net.*;

public class Tester {

    private InetAddress address;
    private String name;
    private byte[] buf;
    private TestResult result;

    public TestResult sendQuery(String server, String name) {
        result = new TestResult();
        buf = new byte[508];
        buf[1] = 1; // Query ID
        buf[5] = 1; // QDCount

        // Stuff the DNS name into the buffer
        int ptr = 12;
        for (String part : name.split("\\.")) {
            buf[ptr] = (byte) part.length();
            ptr++;
            for (char chr : part.toCharArray()) {
                buf[ptr] = (byte) chr;
                ptr++;
            }
        }
        buf[ptr] = 0;

        try {

            address = InetAddress.getByName(server);
            DatagramSocket socket = new DatagramSocket();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 53);
            long start = System.nanoTime();
            socket.send(packet);

            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            result.time = Math.round((System.nanoTime() - start) / 1e6);
            result.answer = new String(packet.getData(), 0, packet.getLength());
        } catch (Exception e) {
            result.error = true;
            result.message = e.getMessage();
            return result;
        }

        return result;
    }

}
