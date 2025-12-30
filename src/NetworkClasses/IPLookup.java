package NetworkClasses;

import GUI.PingForm;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPLookup {
    // get the data from the port form.
    // it doesn't hold a port anymore, but I have to refactor everything.
    static String nameValue = PingForm.getNameField();

    public static void ipLookup() throws UnknownHostException {
        InetAddress ip = InetAddress.getByName(nameValue);
        System.out.println(ip.getHostAddress());
        if (ip == null || !ip.isLoopbackAddress()) {}
    }
    public static void resetIPLookup() {
        nameValue = null;
    }

    public static void main (String[] args) {
        System.out.println(nameValue);

    }
}
