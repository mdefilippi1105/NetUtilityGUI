import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


public class CheckHosts {

    public static List<String> checkHosts(String subnet) throws IOException {
        List<String> results = new ArrayList<>();

        int timeout =1000;
        try {
            for (int i = 1; i < 254; i++) {
                String host = subnet + "." + i;
                if (InetAddress.getByName(host).isReachable(timeout)) {
                    System.out.println(host + " is reachable");
                    results.add(host + " is reachable");
                } else {
                    System.out.println(host + " is not reachable");
                    results.add(host + " ✗ not reachable");
                }
            }
        } catch (UnknownHostException e) {
            System.out.println(e);
        }
        return results;
    }
}


