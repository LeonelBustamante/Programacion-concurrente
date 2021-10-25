package TrabajoPractico4;

public class TestThread extends Thread {
    private SynchronizedCounter c;

    public TestThread(SynchronizedCounter c, String nombre) {
        super(nombre);
        this.c = c;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            c.increment();
            c.decrement();
            System.out.println("Valor de i=" + i + " en " + Thread.currentThread().getName());
            System.out.println("Valor contador " + c.value());
        }
    }

}
