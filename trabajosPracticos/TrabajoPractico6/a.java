package TrabajoPractico6;

import java.util.Random;

class Fumador implements Runnable {
    private int id;
    private SalaFumadores sala;

    public Fumador(int id, SalaFumadores sala) {
        this.id = id;
        this.sala = sala;
    }// constructor

    public void run() {
        while (true) {
            try {
                sala.entrafumar(id);
                System.out.println("Fumador " + id + " está fumando");
                Thread.sleep(new Random().nextInt(1000));
                sala.terminafumar();
            } catch (InterruptedException e) {
            }
        } // while
    }// run
}// clase

class Agente implements Runnable {
    private SalaFumadores sala;

    public Agente(SalaFumadores sala) {
        this.sala = sala;
    }

    public void run() {
        while (true) {
            sala.colocar(new Random().nextInt(3) + 1);
        } // while
    } // run

} // clase

class DisparaSala {
    public static void main(String[] args) {
        SalaFumadores sala = new SalaFumadores();
        Fumador f1 = new Fumador(1, sala);
        Fumador f2 = new Fumador(2, sala);
        Fumador f3 = new Fumador(3, sala);
        Agente ag = new Agente(sala);
        Thread fumador1 = new Thread(f1);
        Thread fumador2 = new Thread(f2);
        Thread fumador3 = new Thread(f3);
        Thread agente = new Thread(ag);
        fumador1.start();
        fumador2.start();
        fumador3.start();
        agente.start();
    }// main
}// clase

class SalaFumadores {

    public void colocar(int i) {

    }

    public void terminafumar() {
    }

    public void entrafumar(int id) {
        switch (id) {
        case 1:

            break;

        case 2:

            break;

        case 3:

            break;

        default:
            break;
        }
    }
}