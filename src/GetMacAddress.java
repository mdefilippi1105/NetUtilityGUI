import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class GetMacAddress {

    public static String getMacAddress() throws SocketException, UnknownHostException {

        NetworkInterface networkInterface = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
            byte [] mac = networkInterface.getHardwareAddress();
            if (mac == null) {
                System.out.println("Oh No!");
            } else {

            }
            System.out.println("mac address : " + mac);


         return "";
    }
}
