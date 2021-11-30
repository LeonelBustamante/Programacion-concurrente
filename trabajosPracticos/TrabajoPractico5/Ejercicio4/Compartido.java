package TrabajoPractico5.Ejercicio4;

import java.util.concurrent.Semaphore;

public class Compartido {

}

class TorreControl {
    public static void main(String[] args) {
        int cantidadAvionesDespegue = 30;
        int cantidadAvionesAterrizan = 50;

        Aeropuerto aeropuerto = new Aeropuerto(cantidadAvionesAterrizan);

        new Thread(new Torre(aeropuerto, cantidadAvionesDespegue + cantidadAvionesAterrizan), "Torre").start();

        for (int i = 0; i <= cantidadAvionesDespegue; i++) {
            new Thread(new AvionDespegue(aeropuerto), "Avion_Despegando" + i).start();
        }

        for (int i = 0; i <= cantidadAvionesAterrizan; i++) {
            new Thread(new AvionAterrizaje(aeropuerto), "Avion_Aterrizaje" + i).start();
        }
    }
}

class Aeropuerto {
    private int aterrizaron = 0, despegaron = 0, aterrizajesSeguidos = 0, maximoSeguidos = 10, totalAterrizajes;
    private Semaphore controlDeTorre, permisoDespegar, permisoAterrizar;

    public Aeropuerto(int cantidadAterrizajes) {
        this.permisoAterrizar = new Semaphore(0);
        this.permisoDespegar = new Semaphore(0);
        this.controlDeTorre = new Semaphore(1);
        this.totalAterrizajes = cantidadAterrizajes;
    }

    public void aterrizar() throws Exception { // AvionAterrizaje
        this.permisoAterrizar.acquire(); // Se bloquea el permiso de aterrizaje
        this.aterrizaron++;
        this.aterrizajesSeguidos++;
    }

    public void terminarAterrizar() { // AvionAterrizaje
        this.controlDeTorre.release(); // Se libera el centro de control
    }

    public void despegar() throws Exception { // AvionDespegue
        this.permisoDespegar.acquire(); // Se bloquea el permiso de despegue
        this.despegaron++;
    }

    public void terminarDespegar() { // AvionDespegue
        this.controlDeTorre.release(); // Se libera el centro de control
    }

    public void controlDePista() throws Exception {
        this.controlDeTorre.acquire(); // Se bloquea el centro de control
        System.out.println("YA VOLARON ESTOS 👉👉👉👉👉👉 ATERRIZARON: " + aterrizaron + " DESPEGARON:" + despegaron
                + " TOTAL: " + (aterrizaron + despegaron) + "👈👈👈👈👈👈");
        if (this.aterrizajesSeguidos == this.maximoSeguidos) {
            System.out.println(Thread.currentThread().getName() + "🆗 para despegar");
            this.aterrizajesSeguidos = 0;
            this.permisoDespegar.release(); // Se libera un despegue
        } else if (totalAterrizajes == aterrizaron) {
            System.out.println(Thread.currentThread().getName() + "🆗 para despegar, ya llegaron todos");
            this.permisoDespegar.release(); // Se libera un despegue
        } else {
            System.out.println(Thread.currentThread().getName() + "🆗🆗 para aterrizar");
            this.permisoAterrizar.release(); // Se libera un aterrizaje
        }
    }
}