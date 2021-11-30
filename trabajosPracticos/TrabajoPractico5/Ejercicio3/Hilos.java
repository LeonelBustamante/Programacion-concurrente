package TrabajoPractico5.Ejercicio3;

import java.util.Random;

public class Hilos {

}

class VendedorTickets implements Runnable {
    private Compartido compartido;

    public VendedorTickets(Compartido compartido) {
        this.compartido = compartido;
    }

    public void run() {
        try {
            while (true) {
                compartido.venderTicket();
            }
        } catch (Exception e) {
        }

    }
}

class Pasajeros implements Runnable {

    private Compartido compartido;

    public Pasajeros(Compartido compartido) {
        this.compartido = compartido;
    }

    public void run() {
        try {
            while (true) {
                compartido.comprarTicket();
                compartido.viajar();
            }
        } catch (Exception e) {
        }
    }
}

class ControlTren implements Runnable {

    private Compartido compartido;

    public ControlTren(Compartido compartido) {
        this.compartido = compartido;
    }

    public void bajandoPasajeros() throws Exception {
        Thread.sleep(new Random().nextInt(1000));
    }

    public void viajar() throws Exception {
        Thread.sleep(new Random().nextInt(1000));
    }

    public void run() {
        try {
            while (true) {
                compartido.puedeViajar();
                viajar();
                compartido.descender();
                bajandoPasajeros();
                compartido.ingresarPasajeros();
            }
        } catch (Exception e) {
        }
    }
}