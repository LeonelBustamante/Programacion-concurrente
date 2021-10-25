package ActividadObligatoria.Ejercicio2;

public class Empaquetador implements Runnable {

    private Caja caja;

    public Empaquetador(Caja caja) {
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
