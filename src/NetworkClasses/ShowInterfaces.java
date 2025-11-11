package NetworkClasses;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.List;

public class ShowInterfaces {
    public NetworkInterface NETINT;
    Enumeration<NetworkInterface> ipInfo;
    Enumeration<InetAddress> ALL_ADDRESSES;
    public int InterfaceCount = 0;

    public  void showInterfaces(){
        try {
            //get all interfaces in the list and store it
            ipInfo = NetworkInterface.getNetworkInterfaces();
            List<NetworkInterface> ipList = Collections.list(ipInfo);
            for (NetworkInterface ipInfo : ipList) {
                System.out.println(ipInfo.getName());
            }

        } catch (SocketException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        ShowInterfaces si = new ShowInterfaces();
        si.showInterfaces();
    }
}
