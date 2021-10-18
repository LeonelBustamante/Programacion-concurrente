package TrabajoPractico4;

public class TestSync extends Thread {
    private SynchronizedObjectCounter c;
    private String nombre;

    public TestSync(SynchronizedObjectCounter c, String nombre) {
        super(nombre);
        this.c = c;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            c.increment();
            c.decrement();
            c.increment();
            c.decrement();
            System.out.println("Valor de i=" + i + " en " + Thread.currentThread().getName());
        }
        System.out.println("Valor contador " + c.value() + " para " + Thread.currentThread().getName());
    }
}