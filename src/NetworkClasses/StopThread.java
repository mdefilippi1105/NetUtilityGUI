package NetworkClasses;

public class StopThread implements Runnable {

    private volatile boolean stopRequested = false;

    public void requestStop() {
        this.stopRequested = true;
    }

    @Override
    public void run() {
        while (!stopRequested) {
            try{
                Thread.sleep(1000);
                System.out.println("Stopping thread");

            } catch (InterruptedException e) {
                return;
            }

        }
        System.out.println("Thread stopped");
    }
}
