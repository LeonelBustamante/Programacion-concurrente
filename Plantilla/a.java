import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class a {
    public static void main(String[] args) {
        int tam = 10;
        BlockingQueue q = new ArrayBlockingQueue(tam);

        for (int i = 0; i < 1; i++) {
            new Thread(new Embotellador(q, tam), "EMBOTELLADOR").start();
        }

        new Thread(new Empaquetador(q, tam), "EMPAQUETADOR").start();
    }

}

class Embotellador implements Runnable {

    private BlockingQueue q;
    private int max;

    public Embotellador(BlockingQueue q, int tam) {
        this.q = q;
        this.max = tam;
    }

    public void run() {
        while (true) {
            q.put(1);
            System.out.println(Thread.currentThread().getName() + "puse un elemento " + q.size());
            if (q.size() == max) {
                consumidor.limpiar(); // EN REALIDAD DEBERIA LLAMAR AL RUN DE CONSUMIDOR
            }
        } // fin while
    }// fin run
}

// ACLARACION: El problema de este sistema seria que si el Empaquetador empieza
// genial por que se bloquea automaticamente por el take el Queue
// pero se mantiene el problema que si el que empieza es el Embotellador cuando
// le toque la cpu a Empaquetador va sacar por que tiene elementos

class Empaquetador implements Runnable {

    private BlockingQueue q;
    private int max;

    public Empaquetador(BlockingQueue q, int tam) {
        this.q = q;
        this.max = tam;
    }

    public void run() {
        while (true) {
            limpiar(); // Por que aca estaria la situacion
        } // fin while
    }// fin run

    private void limpiar() {
        for (int i = 0; i <= max; i++) {
            try {
                q.take();
                System.out.println(Thread.currentThread().getName() + "Saque un elemento " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}