package NetworkClasses;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class GetMacAddress {

    /*
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * how this works :
    * start with empty string, loop through each byte in the MAC
    * two hex digits = 1 byte
    * AA - BB - CC - DD - EE - FF
    * ↑    ↑    ↑    ↑    ↑    ↑
    * byte byte byte byte byte byte
    * 1    2    3    4    5    6
    * if it's not the last byte, add a " : "
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    */

    public static String getMacAddress() throws SocketException, UnknownHostException {
        NetworkInterface networkInterface = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
            // create a byte array called mac
            byte [] mac = networkInterface.getHardwareAddress();

            if (mac != null) {
                StringBuilder macAddress = new StringBuilder();
                for (int i = 0; i < mac.length; i++) {
                    macAddress.append(String.format("%02X", mac[i]));
                    if (i < mac.length - 1) {
                        macAddress.append(":");
                    }
                }
                System.out.println(macAddress);

            } else {
                System.out.println("Oh No!");

            }



         return "";
    }

    public static void main(String[] args) throws SocketException, UnknownHostException {
        getMacAddress();
    }
}
