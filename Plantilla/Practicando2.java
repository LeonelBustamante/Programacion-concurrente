import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Practicando2 {

}

class Main {
    public static void main(String[] args) {

        int cantidadMaximaEnMostrador = 10;
        Mostrador m = new Mostrador(cantidadMaximaEnMostrador);
        for (int i = 0; i < 5; i++) {
            new Thread(new Pizzero(m, i % 2), "PIZZERO-" + i).start();
        }
    }

}

class Pizzero implements Runnable {
    Mostrador m;
    int tipo;

    public Pizzero(Mostrador m, int i) {
        this.m = m;
        this.tipo = i;
    }

    public void run() {
        m.tomarPedido();
        this.haciendoPizza();
    }// fin run

    private void haciendoPizza() {
        System.out.println(Thread.currentThread().getName() + " esta haciendo la pizza 🍕");
        try {
            Thread.sleep(new Random().nextInt(500));
        } catch (Exception e) {
        }
        System.out.println(Thread.currentThread().getName() + " entrego el pedido. 👌");
    }// fin entregando
}// fin Pizzero

class Repartidor implements Runnable {
    Mostrador c;
    int cantidadRepartida = 0;

    public void run() {
        this.entregando();
        cantidadRepartida++;
        if (cantidadRepartida == 10) {
            this.descanso();
        }

    }// fin run

    private void descanso() {
        try {
            Thread.sleep(new Random().nextInt(500));
        } catch (Exception e) {
        }
        this.cantidadRepartida = 0;
    }

    private void entregando() {
        System.out.println("Se esta entregando el pedido. 🛵");
        try {
            Thread.sleep(new Random().nextInt(500));
        } catch (Exception e) {
        }
        System.out.println(Thread.currentThread().getName() + " entrego el pedido. 🆗");
    }// fin entregando
}// fin repartidor

class Mostrador {
    private int MAX;
    private int cantActual = 0;
    private Queue<Integer> pedidosEnEspera, mostrador;

    public Mostrador(int cantidadMaximaEnMostrador) {
        this.MAX = cantidadMaximaEnMostrador;
        this.pedidosEnEspera = new LinkedList();
        this.mostrador = new LinkedList();
    }

    public void tomarPedido() {
    }

    public void nuevoPedido(String string, int i) {
        try {
            while (cantActual == MAX) { // Se averigua si se pueden crear nuevos pedidos
                System.out.println(Thread.currentThread().getName() + " esperando, pedidos lleno ❎");
                this.wait();
            }

            // Se toma pedido
            pedidosEnEspera.add(i);
            System.out.println("✔" + Thread.currentThread().getName() + " agrego un pedido, cantidad pedidos actual: "
                    + cantActual);
            this.notifyAll();
        } catch (Exception e) {
        } // fin nuevoPedido
    }

    public synchronized void sacarElemento() {
        try {
            while (cantActual == 0) {
                System.out.println(Thread.currentThread().getName() + " tiene que esperar, mostrador vacio ❌");
                this.wait();
            }
            Thread.sleep(new Random().nextInt(500));
            cantActual--;
            this.notifyAll();

            System.out.println(
                    "❗    " + Thread.currentThread().getName() + " consumió un producto, capacidad actual: "
                            + cantActual);
        } catch (Exception e) {
        }
    }// fin sacarElemento
}// fin mostrador

class generadorDePedidos implements Runnable {
    private Mostrador m;

    public generadorDePedidos(Mostrador m) {
        this.m = m;
    }

    public void run() {
        while (true) {
            this.tomandoPedido();
            m.nuevoPedido(Nombres.getNombre(new Random().nextInt(10)), new Random().nextInt(2));
        }
    }

    private void tomandoPedido() {
        System.out.println("Se esta tomando el pedido");
        try {
            Thread.sleep(new Random().nextInt(500));
        } catch (Exception e) {
        }
    }

}

class Nombres {
    static String[] nombres = new String[] {
            "Tatiana",
            "Leonel",
            "Antonela",
            "Martin",
            "Mauricio",
            "Franco",
            "Adriano",
            "Gustavo",
            "Nicolas",
            "Juan",
    };

    public static String getNombre(int x) {
        return nombres[x];
    }
}