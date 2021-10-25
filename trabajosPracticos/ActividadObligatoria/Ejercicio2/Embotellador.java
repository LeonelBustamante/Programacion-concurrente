package Ejercicio2;

public class Embotellador implements Runnable {
    private Caja caja;

    public Embotellador(Caja caja) {
        this.caja = caja;
    }

    @Override
    public void run() {
        while (true) {
            try {
                caja.embotellar();
            } catch (InterruptedException ex) {
            }
        }
    }
}
