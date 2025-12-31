package NetworkClasses;

import GUI.AlertWindow;
import GUI.FXTable;
import GUI.PingForm;
import javafx.application.Platform;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CheckHosts {

    private static volatile boolean stopRequested = false;
    // all pings
    private static final List<String> results = new ArrayList<>();
    // bad pings
    private static final List<String> noReplyHosts = new ArrayList<>();
    // good pings
    private static final List<String> yesReplyHosts = new ArrayList<>();


    public static List<String> checkHosts(String subnet, int pingCount) throws IOException {

        int timeout = 2700;

        try {
            // get the subnet from text field pingCount
            // then grab value from slider
            // loop ip address using isReachable class + add to table
            //TODO: have a way to clear results
            for (int i = 1; i < pingCount && !stopRequested; i++) {

                String host = subnet + "." + i;

                if (InetAddress.getByName(host).isReachable(timeout)) {
                    System.out.println(host + " is reachable. Current size: " + results.size()); // debug - delete later
                    FXTable.addPingResult(host, "is reachable");
                    yesReplyHosts.add(host);
                    results.add(host + " is reachable");

                } else {
                    System.out.println(host + " is not reachable. Current size: " + results.size()); // debug - delete later
                    FXTable.addPingResult(host, "is not reachable");
                    noReplyHosts.add(host);
                    results.add(host + " ✗ not reachable");
                }
            }
            stopRequested = true;

        } catch (UnknownHostException e) {
            System.out.println(e);
        }
        return results;
    }

    // important: Platform.run-later is the JavaFX application thread.
    // it basically says: please run this code on the UI thread
    public static Thread checkHostInThread(String subnet, int pingCount, Runnable onComplete) {
        Thread thread = new Thread(() -> {
            try {
                //check the for loop
                checkHosts(subnet, pingCount);
                if (onComplete != null) {
                    Platform.runLater(onComplete);
                }
                if (onComplete == null) {
                    Platform.runLater(() -> {
                        AlertWindow alert = new AlertWindow();
                        alert.showPingResults();
                        PingForm.spinner.setVisible(false);
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.setDaemon(true);
        return thread;
    }

    public static void resetStopFlag() {
        stopRequested = false;
    }

    public static void setStopRequested() {
        stopRequested = true;
    }

    public static List<String> getResults() {
        System.out.println(results);
        return results;
    }

    public static String sortResults() {
        Collections.sort(results);
        return results.toString();
    }

    public static void  clearResults() {
        results.clear();
        noReplyHosts.clear();
        yesReplyHosts.clear();
        System.out.println("Current size: " + results.size() + " " + noReplyHosts.size());
    }

    public static int howManyHosts() {
        return yesReplyHosts.size();
    }











    }










//    //this is just to observe thread behavior, debug purposes
//    public static void getThreadState(Thread thread) {
//        if (thread.isAlive()) {
//            Map<Thread, StackTraceElement[]> traceData = Thread.getAllStackTraces();
//            for (Map.Entry<Thread, StackTraceElement[]> entry : traceData.entrySet()) {
//                System.out.println("Thread " + entry.getKey().getName() + ": " + entry.getValue().length);
//            }
//        }
//    }

