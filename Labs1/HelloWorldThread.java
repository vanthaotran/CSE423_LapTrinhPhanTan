public class HelloWorldThread extends Thread {
    String s;
    public HelloWorldThread (String s) {
        this.s = s;
    }
    public void run() {
        System.out.println("Hello world: " + s);
    }

    public static void main(String [] args) {
        HelloWorldThread t = new HelloWorldThread("van");
        t.start();

        HelloWorldThread t1 = new HelloWorldThread("mei");
        t1.start();

        try {
            t.join();
            t1.join();
        } catch (InterruptedException e) {}
    }
}
