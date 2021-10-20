package Ejercicio1;

import java.util.concurrent.Semaphore;

public class Matematica {

    private double w;
    private double x;
    private double y;
    private double z;
    private double a;
    private double b;
    private double c;
    Semaphore sem1;
    Semaphore sem2;
    Semaphore sem3;
    Semaphore sem4;

    public Matematica(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.w = 0;
        this.sem1 = new Semaphore(2);
        this.sem3 = new Semaphore(0);
        this.sem4 = new Semaphore(0);
    }

    public void proceso1() throws InterruptedException {
        sem1.acquire();
        a = x + y;
        System.out.println(Thread.currentThread().getName() + " P1 El valor de c despues de a = x + y es: " + a);
        sem3.release();
    }

    public void proceso2() throws InterruptedException {
        sem1.acquire();
        b = z - 1;
        System.out.println(Thread.currentThread().getName() + " P2 El valor de c despues de b = z - 1 es: " + b);
        sem3.release();
    }

    public void proceso3() throws InterruptedException {
        sem3.acquire(2);
        c = a - b;
        System.out.println(Thread.currentThread().getName() + " P3 El valor de c despues de c = a - b es: " + c);
        sem4.release();
        sem3.release(2);
    }

    public void proceso4() throws InterruptedException {
        sem4.acquire();
        w = c + 1;
        System.out.println(Thread.currentThread().getName() + " P4 El valor de w despues de w = c + 1 es: " + w);
        sem4.release();
    }

}
