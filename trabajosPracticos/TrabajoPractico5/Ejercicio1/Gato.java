package TrabajoPractico5.Ejercicio1;

import java.util.Random;

public class Gato implements Runnable {
    Comedor comedero;

    public Gato(Comedor c) {
        comedero = c;
    }

    private void comer() {
        try {
            Thread.sleep(new Random().nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            comedero.esperarTurnoGato();
            comedero.entrarGato();
            this.comer();
            comedero.salirGato();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}