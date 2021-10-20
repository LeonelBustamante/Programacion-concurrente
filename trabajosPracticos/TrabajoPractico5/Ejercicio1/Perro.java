package TrabajoPractico5.Ejercicio1;

import java.util.Random;

public class Perro implements Runnable {
    Comedor comedero;

    public Perro(Comedor c) {
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
            comedero.esperarTurnoPerro();
            comedero.entrarPerro();
            this.comer();
            comedero.salirPerro();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}