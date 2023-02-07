import jdk.jshell.execution.Util;

import javax.swing.text.Utilities;

public class BinarySemaphore {
    
    boolean value;
    BinarySemaphore (boolean initValue) {
        value = initValue;
    }
    public synchronized void P() {
       // if(value == false) Util.myWait(this);
        value = false;
    }
    
    public synchronized void V() {
        value = true;
        notify();
    }
    public static void main() {
        BinarySemaphore mutex = new BinarySemaphore(true);
        mutex.P();

       // criticalSection();

        mutex.V();
    }
}
