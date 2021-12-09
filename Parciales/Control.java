public class Control implements Runnable {
    private Acrobacia a;

    public Control(Acrobacia a) {
        this.a = a;
    }

    public void run() {
        while (true) {
            try {
                a.arrancaClase1();
                a.arrancaClase2();
            } catch (InterruptedException e) {
            }
        }
    }
}
