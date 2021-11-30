package TrabajoPractico5.Ejercicio5;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Compartido {

}

class ParqueAcuatico {

    public static void main(String[] args) {
        int cantidadTurista = 30;
        int lugaresMirador = 5;

        Mirador mirador = new Mirador(lugaresMirador);

        new Thread(new Encargado(mirador, cantidadTurista), "ENCARGADO_").start();

        for (int i = 0; i <= cantidadTurista; i++) {
            new Thread(new Turista(mirador), "TURISTA_" + i).start();
        }

    }
}

class Mirador {

    private boolean tobogan1 = true;
    private int cantidadVisitantes = 0;
    private Semaphore semTob1, semTob2, semEscalera, semTurno, semEncargado;

    public Mirador(int lugares) {
        this.tobogan1 = true;
        this.semEscalera = new Semaphore(lugares, true);
        this.semTob1 = new Semaphore(1);
        this.semTob2 = new Semaphore(1);
        this.semEncargado = new Semaphore(1);
    }

    public void decidirLado() { // Encargado
        if (semEncargado.tryAcquire()) {
            // Pueded usarse el tobogan A
            tobogan1 = true;
            this.mutexToboganA.release();
            if (this.hayVisitantes != 0) {
                this.esperaFila.release();
            }
        } else if (mutexToboganB.tryAcquire()) {
            // Puede usarse el tobogan B
            ladoA = false;
            this.mutexToboganB.release();
            if (this.hayVisitantes != 0) {
                this.esperaFila.release();
            }
        }
    }

    public void subirAlMirador() {
        this.semEscalera.acquire();
        this.semTurno.acquire();
    }

    public void tirarseTobogan(boolean ) {
        if (numTobogan == 0) {
            this.semTob1.acquire();
            System.out.println(Thread.currentThread().getName() + " SE TIRO POR EL TOBOGAN 0");
            this.tobogan1 = false;
        } else {
            this.semTob2.acquire();
            System.out.println(Thread.currentThread().getName() + " SE TIRO POR EL TOBOGAN 1");
            this.tobogan2 = false;
        }
        this.semEncargado.release();
    }

    public void bajarseTobogan(int numTobogan) {
        if (numTobogan == 0) {
            this.tobogan1 = true;
            this.semTob1.release();
        } else {
            this.tobogan2 = true;
            this.semTob2.release();
        }
    }

    public int identificarTobogan() {
        return this.cantidadVisitantes;
    }
}