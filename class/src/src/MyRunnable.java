public class MyRunnable implements Runnable{

    public static void main(String [] args) {
        Thread n = new Thread(new MyRunnable(), "myRunnble");
        n.start();
    }

    @Override
    public void run() {
        System.out.print("Executing Thread: " + Thread.currentThread().getName());
    }
}
