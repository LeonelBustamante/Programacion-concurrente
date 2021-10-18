package TrabajoPractico4;

public class SynchronizedObjectCounter {
    private int c = 0;

    public void increment() {
        synchronized ((Integer) c) {
            c++;
        }
    }

    public void decrement() {
        synchronized (this) {
            c--;
        }
    }

    public int value() {
        return c;
    }
}
