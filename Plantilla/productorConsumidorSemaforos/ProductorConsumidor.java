package productorConsumidorSemaforos;

import java.util.concurrent.Semaphore;

public class ProductorConsumidor {
}

class Main {
    public static void main(String[] args) {
        int cantidad_maxima_Elementos = 3000;
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

    public Productor(Buffer b) {
        this.b = b;
    }

    public void run() {
        try {
            while (true) {
                b.empezar();
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

// Semaforos
class Buffer {
    private Semaphore semConsumidor;
    private Semaphore semProductor;
    private Semaphore mutex;
    private final int MAXIMO;
    private int cantActual = 0;

    public Buffer(int cantidadMaxima) {
        MAXIMO = cantidadMaxima;
        semConsumidor = new Semaphore(0);
        semProductor = new Semaphore(0);
        mutex = new Semaphore(1);
    }

    public void empezar() throws Exception {
        semProductor.release();
    }

    public void agregarElemento() throws Exception {
        this.semProductor.acquire();

        mutex.acquire();

        System.out.println("🛠  " + Thread.currentThread().getName() + " esta agregando un producto...");
        Thread.sleep(350);
        this.cantActual++;
        System.out.println("✔      " + Thread.currentThread().getName() + " agrego un producto. Capacidad actual: "
                + this.cantActual);
        this.semConsumidor.release(this.MAXIMO);

        mutex.release();
    }

    public void sacarElemento() throws Exception {
        this.semConsumidor.acquire();

        System.out.println("🚚  " + Thread.currentThread().getName() + " esta consumiendo un producto...");
        Thread.sleep(400);

        mutex.acquire();
        this.cantActual--;
        System.out.println("🆗       " + Thread.currentThread().getName() + " termino de consumir. Capacidad actual: "
                + this.cantActual + "🆗🆗🆗🆗");

        if (this.cantActual == 0) {
            this.semProductor.release(this.MAXIMO);
        }

        mutex.release();

    }
}
