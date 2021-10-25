package Ejercicio2;

import java.util.concurrent.Semaphore;

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

class Caja2 {
    private int botellasPorCaja;
    private int cantidadActual;
    private Semaphore semBotellas;
    private Semaphore semEmpaquetado;
    private Semaphore mutex;

    public Caja2(int botellasPorCaja) {
        this.botellasPorCaja = botellasPorCaja;
        this.cantidadActual = 0;
        this.semEmpaquetado = new Semaphore(0);
        this.semBotellas = new Semaphore(botellasPorCaja);
        this.mutex = new Semaphore(1);
    }

    public void embotellar() throws InterruptedException {
        semBotellas.acquire();

        this.mutex.acquire();
        System.out.println(Thread.currentThread().getName() + " agrega una botella.");
        this.cantidadActual++;
        System.out.println("----- Cantidad de botellas. " + this.cantidadActual);

        if (this.cantidadActual == this.botellasPorCaja) {
            semEmpaquetado.release();
        }
        this.mutex.release();

    }

    public void empaquetar() throws InterruptedException {
        semEmpaquetado.acquire();

        System.out.println(Thread.currentThread().getName() + " esta sacando la caja y coloca una vacia. SE EMPAQUETO "
                + this.cantidadActual + " BOTELLAS");
        this.cantidadActual = 0;

        semBotellas.release(this.botellasPorCaja);
    }

}