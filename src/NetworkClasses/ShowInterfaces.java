package NetworkClasses;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

public class ShowInterfaces {
    public NetworkInterface NETINT;
    Enumeration<NetworkInterface> ALL_INTERFACES;
    Enumeration<InetAddress> ALL_ADDRESSES;
    public int InterfaceCount = 0;

    public  void showInterfaces(){
        try {
            //get all interfaces in the list and store it
            ALL_INTERFACES = NetworkInterface.getNetworkInterfaces();
            InterfaceCount = Collections.list(ALL_INTERFACES).size();
            System.out.println("Number of interfaces: " + InterfaceCount);


        } catch (SocketException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
