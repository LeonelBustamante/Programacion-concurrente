package Ejercicio2;

public class Embotellador implements Runnable {
    private Caja2 caja;

    public Embotellador(Caja2 caja) {
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
