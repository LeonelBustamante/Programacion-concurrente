package TrabajoPractico3;

public class Turno {
    private int posicion = 0;

    public Turno() {
    }

    public synchronized int getTurno() {
        return this.posicion;
    }

    public synchronized void siguienteTurno() {
        this.posicion = (this.posicion + 1) % 3;
    }
}
