package ProductorConsumidor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

    private int productoMaximo; // Cantidad maxima de productos.
    private int productoActual; // Cantidad de producto actual.
    private Lock llave = new ReentrantLock(true);
    private Condition productores;
    private Condition consumidores;

    public Buffer(int productoMax) {
        this.productoMaximo = productoMax;
        this.productoActual = 0;
        this.productores = llave.newCondition();
        this.consumidores = llave.newCondition();
    }

    /**
     * Éste método pregunta si la cantidad actual de productos es menor a la
     * cantidad máxima y agrega productos.
     */
    public void producir() throws InterruptedException {

        llave.lock();
        while (this.productoActual >= this.productoMaximo) {
            System.out.println(Thread.currentThread().getName() + " debe esperar.");
            productores.await();
        }
        System.out.println(Thread.currentThread().getName() + " agrega un producto.");
        this.productoActual++;
        consumidores.signal();
        llave.unlock();
    }

    /**
     * Éste método pregunta si existe algun producto y consume los mismos.
     */
    public void consumir() throws InterruptedException {
        llave.lock();
        while (this.productoActual == 0) {
            System.out.println("El consumidor debe esperar");
            consumidores.await();
        }
        System.out.println("El consumidor consume un producto.");
        this.productoActual--;
        this.productores.signal();
        llave.unlock();
    }

}
