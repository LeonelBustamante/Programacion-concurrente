package TrabajoPractico4;

import java.util.concurrent.Semaphore;

public class Turno {

    private Semaphore mutexA = new Semaphore(1);
    private Semaphore mutexB = new Semaphore(0);
    private Semaphore mutexC = new Semaphore(0);

    private int turno = 0;

    public Turno() {
    }

    public void getTurno(int turno) {
        try {
            if (turno == 0) {
                this.mutexA.acquire();
            } else if (turno == 1) {
                this.mutexB.acquire();
            } else {
                this.mutexC.acquire();
            }
        } catch (InterruptedException ex) {
        }

    }

    public void siguienteTurno() {
        this.turno = (this.turno + 1) % 3;
        if (this.turno == 0) {
            this.mutexA.release();
        } else if (this.turno == 1) {
            this.mutexB.release();
        } else {
            this.mutexC.release();
        }

    }
}