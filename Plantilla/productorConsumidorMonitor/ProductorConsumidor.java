package productorConsumidorMonitor;

import java.util.Random;

public class ProductorConsumidor {
}

class Main {
    public static void main(String[] args) {
        int cantidad_maxima_Elementos = 5;
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

    public Buffer(int cantidadMaxima) {
        this.MAXIMO = cantidadMaxima;
        this.cantActual = 0;
    }

    public synchronized void agregarElemento() throws Exception {
        while (cantActual == MAXIMO) {
            System.out.println(Thread.currentThread().getName() + " esperando, buffer lleno ❎");
            this.wait();
        }

        cantActual++;
        Thread.sleep(new Random().nextInt(500));
        System.out.println(
                "✔     " + Thread.currentThread().getName() + " produjo un elemento, capacidad actual: " + cantActual);
        this.notifyAll();

    }

    public synchronized void sacarElemento() throws Exception {
        while (cantActual == 0) {
            System.out.println(Thread.currentThread().getName() + " tiene que esperar, buffer vacío ❌");
            this.wait();
        }
        Thread.sleep(new Random().nextInt(500));
        cantActual--;
        this.notifyAll();

        System.out.println(
                "❗    " + Thread.currentThread().getName() + " consumió un producto, capacidad actual: " + cantActual);
    }
}
