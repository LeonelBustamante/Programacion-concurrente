package productorConsumidorLocksConConditions;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProductorConsumidor {
}

class Main {
    public static void main(String[] args) {
        int cantidad_maxima_Elementos = 3;
        int cantidad_productores = 3;
        int cantidad_consumidores = 3;

        Buffer b = new Buffer(cantidad_maxima_Elementos);

        for (int i = 1; i <= cantidad_productores; i++) {
            (new Thread(new Productor(b), "PRODUCTORES_" + i)).start();
        }
        for (int i = 1; i <= cantidad_consumidores; i++) {
            (new Thread(new Consumidor(b), "CONSUMIDORES_" + i)).start();
        }

    }
}

class Productor implements Runnable {
    private Buffer b;

    private BlockingQueue

    public Productor(Buffer b) {
        this.b = b;
    }

    public void run() {
        try {
            while (true) {
                b.agregarElemento();
            }
        } catch (Exception e) {
        }
    }
}

class Consumidor implements Runnable {
    private Buffer b;

    public Consumidor(Buffer b) {
        this.b = b;
    }

    public void run() {
        try {
            while (true) {
                b.sacarElemento();
            }
        } catch (Exception e) {
        }
    }
}

// Locks
class Buffer {

    private final int MAXIMO;
    private int cantActual;
    private Lock mutex;
    private Condition productor;
    private Condition consumidor;

    public Buffer(int cantidadMaxima) {
        this.MAXIMO = cantidadMaxima;
        this.cantActual = 0;
        this.mutex = new ReentrantLock();
        this.productor = mutex.newCondition();
        this.consumidor = mutex.newCondition();
    }

    public void agregarElemento() throws Exception {
        mutex.lock();
        while (cantActual == MAXIMO) {
            System.out.println(Thread.currentThread().getName() + " esperando, buffer lleno ❎");
            productor.await();
        }
        cantActual++;
        Thread.sleep(new Random().nextInt(500));
        System.out.println(
                "✔     " + Thread.currentThread().getName() + " produjo un elemento, capacidad actual: " + cantActual);
        consumidor.signal();
        mutex.unlock();
    }

    public void sacarElemento() throws Exception {
        mutex.lock();
        while (cantActual == 0) {
            System.out.println(Thread.currentThread().getName() + " tiene que esperar, buffer vacío ❌");
            consumidor.await(); // Espera bloqueado
        }
        Thread.sleep(new Random().nextInt(500));
        cantActual--;
        System.out.println(
                "❗    " + Thread.currentThread().getName() + " consumió un producto, capacidad actual: " + cantActual);
        productor.signal();
        mutex.unlock();
    }
}
