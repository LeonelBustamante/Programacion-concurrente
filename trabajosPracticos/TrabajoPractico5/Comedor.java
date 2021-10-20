package TrabajoPractico5;

import java.util.concurrent.Semaphore;

public class Comedor {
    private int cantidad;
    private int comiendo;
    private Semaphore sem1;

    public Comedor(int maximo) {
        cantidad = maximo;
        comiendo = 0;
        sem1 = new Semaphore(1);
    }

    public synchronized void comer(String nombre, boolean esPerro) {
        try {
            while (comiendo >= cantidad) {
                System.out.println(nombre + "debe esperar para comer");
                this.wait();
            }
        } catch (InterruptedException ex) {
        }
        System.out.println(nombre + " empieza a comer");
        comiendo++;
    }

    public synchronized void dejarDeComer(String nombre) {
        System.out.println(nombre + " termino de comer");
        comiendo--;
        this.notify();
    }
}
