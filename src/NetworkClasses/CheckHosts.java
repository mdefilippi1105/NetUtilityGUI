package NetworkClasses;

import GUI.FXTable;
import Main.MainView.*;
import javafx.application.Platform;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


public class CheckHosts {

    public static List<String> checkHosts(String subnet) throws IOException  {
        String mac = GetMacAddress.getMacAddress().toString();
        List<String> results = new ArrayList<>();

        int timeout =3500;
        try {
            // get the subnet from text field,
            // then
            for (int i = 1; i < 100; i++) {
                String host = subnet + "." + i;
                if (InetAddress.getByName(host).isReachable(timeout)) {
                    System.out.println(host + " is reachable");
                    FXTable.addPingResult(host, "is reachable", mac);
                    results.add(host + " is reachable");
                } else {
                    System.out.println(host + " is not reachable");
                    FXTable.addPingResult(host, "is not reachable", mac);
                    results.add(host + " ✗ not reachable" + mac);
                }
            }
        } catch (UnknownHostException e) {
            System.out.println(e);
        }
        return results;
    }

    public static Thread checkHostInThread(String subnet, Runnable onComplete) {
        Thread thread = new Thread(() -> {
            try {
                checkHosts(subnet);
                if (onComplete != null) {
                    Platform.runLater(onComplete);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.setDaemon(true);
        return thread;
    }

}


