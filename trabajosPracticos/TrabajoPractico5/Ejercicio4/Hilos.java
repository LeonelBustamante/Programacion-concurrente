package TrabajoPractico5.Ejercicio4;

import java.util.Random;

public class Hilos {

}

class AvionDespegue implements Runnable {
    private Aeropuerto aeropuerto;

    public AvionDespegue(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }

    public void despegar() throws Exception {
        System.out.println(Thread.currentThread().getName() + " Despegando  -----   🛫");
        Thread.sleep(new Random().nextInt(1000));
    }

    public void run() {
        try {
            aeropuerto.despegar();
            despegar();
            aeropuerto.terminarDespegar();
        } catch (Exception e) {
        }
    }
}

class AvionAterrizaje implements Runnable {
    private Aeropuerto aeropuerto;

    public AvionAterrizaje(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }

    public void aterrizar() throws Exception {
        System.out.println(Thread.currentThread().getName() + " Aterrizando  -----   🛬");
        Thread.sleep(new Random().nextInt(1000));
    }

    public void run() {
        try {
            aeropuerto.aterrizar();
            aterrizar();
            aeropuerto.terminarAterrizar();
        } catch (Exception e) {
        }
    }
}

class Torre implements Runnable {
    private Aeropuerto aeropuerto;
    private int totalAviones;

    public Torre(Aeropuerto aeropuerto, int totalAviones) {
        this.aeropuerto = aeropuerto;
        this.totalAviones = totalAviones;
    }

    public void run() {
        int i = 0;
        try {
            while (i < this.totalAviones) {
                aeropuerto.controlDePista();
                i++;
            }
        } catch (Exception e) {
        }
    }
}
