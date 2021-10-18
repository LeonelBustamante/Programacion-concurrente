package TrabajoPractico3;

public class Hamster implements Runnable {

    Rueda r;
    Plato p;
    Hamaca h;

    public Hamster() {
        this.r = new Rueda();
        this.p = new Plato();
        this.h = new Hamaca();
    }

    @Override
    public void run() {
        while (true) {
            r.usarRueda();
            p.usarPlato();
            h.usarHamaca();
        }
    }

}
