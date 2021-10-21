package Ejercicio2;

public class Empaquetador implements Runnable {

    private Caja2 caja;

    public Empaquetador(Caja2 caja) {
        this.caja = caja;
    }

    @Override
    public void run() {
        while (true) {
            try {
                caja.empaquetar();
            } catch (InterruptedException ex) {
            }
        }

    }
}
