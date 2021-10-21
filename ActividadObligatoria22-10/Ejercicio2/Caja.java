package Ejercicio2;

public class Caja {
    private int botellasPorCaja;
    private int cantidadActual;

    public Caja(int botellasPorCaja) {
        this.botellasPorCaja = botellasPorCaja;
        this.cantidadActual = 0;
    }

    public synchronized void embotellar() throws InterruptedException {
        if (this.cantidadActual < this.botellasPorCaja) {
            System.out.println(Thread.currentThread().getName() + " agrega una botella.");
            this.cantidadActual++;
            System.out.println("----- Cantidad de botellas. " + this.cantidadActual);
        } else {
            this.notify();
        }
    }

    public synchronized void empaquetar() throws InterruptedException {
        this.wait();
        System.out.println(Thread.currentThread().getName() + " esta sacando la caja y coloca una vacia.");
        this.cantidadActual = 0;
    }

}
