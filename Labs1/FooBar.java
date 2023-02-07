class Foo {
    String name;
    public Foo (String s) {
        name = s;
    }
    public String getName() {
        return name;
    }
}

class FooBar extends Foo implements Runnable{
    public FooBar (String s) {
        super(s);
    }

    public static void main (String [] args) {
        FooBar f1 = new FooBar("Romeo");
        Thread t1 = new Thread(f1);
        t1.start();

        FooBar f2 = new FooBar("Juliet");
        Thread t2 = new Thread(f2);
        t2.start();

        try {
            t2.join();
            t1.join();
        } catch (InterruptedException e) {}
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }
            System.out.println(getName() + ": Hello world " + Thread.currentThread().getName());
        }
    }
}
