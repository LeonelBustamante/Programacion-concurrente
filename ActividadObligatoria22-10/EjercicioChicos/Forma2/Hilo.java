package EjercicioChicos.Forma2;

import EjercicioChicos.Forma1.Instrucciones;

public class Hilo implements Runnable {

    private int act;
    private Instrucciones in;

    public Hilo(int act, Instrucciones in) {
        this.in = in;
        this.act = act;
    }

    @Override
    public void run() {
        switch (this.act) {
        case 1:
            in.s1();
            break;
        case 2:
            in.s2();
            break;
        case 3:
            in.s3();
            break;
        case 4:
            in.s4();
            break;
        }
    }

}