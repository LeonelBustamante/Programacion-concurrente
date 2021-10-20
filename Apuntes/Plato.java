package Apuntes;

public class Plato {
    private int cantidad;
    private int comiendo;

    public Plato(int maximo) {
        cantidad = maximo;
        comiendo = 0;
    }

    public synchronized void empezarAComer(String nombre) {
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

    public synchronized void terminarDeComer(String nombre) {
        System.out.println(nombre + " termino de comer");
        comiendo--;
        this.notify();
    }
}