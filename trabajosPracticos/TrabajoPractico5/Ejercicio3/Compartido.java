package TrabajoPractico5.Ejercicio3;

import java.util.Random;
import java.util.concurrent.Semaphore;

class Tren {

    public static void main(String[] args) {
        // Objeto compartido
        int maximoVagon = 10;
        int cantidadPasajeros = 50;

        Compartido estacion = new Compartido(maximoVagon);

        new Thread(new VendedorTickets(estacion), "VENDEDOR").start();
        new Thread(new ControlTren(estacion), "CONTROL-TREN").start();

        for (int i = 0; i < cantidadPasajeros; i++) {
            new Thread(new Pasajeros(estacion), "PASAJERO_ " + i).start();
        }
    }
}

class Compartido {

    private final int MAXIMO;
    private int cantAsientosOcupados;
    private Semaphore semTicket, semPasajero, estanTodos, semViajar, semSalidaTren, mutex;

    public Compartido(int maximo) {
        this.MAXIMO = maximo;
        this.cantAsientosOcupados = 0;
        this.mutex = new Semaphore(1);
        this.semTicket = new Semaphore(0);
        this.semPasajero = new Semaphore(0);
        this.estanTodos = new Semaphore(this.MAXIMO);
        this.semViajar = new Semaphore(0);
        this.semSalidaTren = new Semaphore(0);

    }

    public void comprarTicket() throws Exception { // Pasajero
        System.out.println(Thread.currentThread().getName() + " compra un ticket    --- 💲");
        Thread.sleep(new Random().nextInt(300));
        semTicket.release(); // libero un ticket
        semPasajero.acquire(); // bloqueo el pasajero
    }

    public void venderTicket() throws Exception { // Vendedor
        semTicket.acquire(); // Se bloquea la entrega de ticket
        System.out.println(Thread.currentThread().getName() + " entrego un ticket   --  🧾");
        Thread.sleep(new Random().nextInt(300));
        semPasajero.release(); // Se entrego ticket, se libera la accion
    }

    public void puedeViajar() throws Exception { // ControlTren
        this.semSalidaTren.acquire();
        System.out.println(Thread.currentThread().getName() + " esta viajando 🚝");
    }

    public void viajar() throws Exception { // Pasajero
        this.estanTodos.acquire(); // Bloqueo para saber si estan todos los lugares ocupados
        this.mutex.acquire(); // Bloqueo del viaje
        this.cantAsientosOcupados++;
        System.out.println("Asientos ✔=> " + this.cantAsientosOcupados);

        if (this.cantAsientosOcupados == this.MAXIMO) {// Cuando se hayan ocupado los suficientes asientos se permite la
                                                       // salida del tren
            this.semSalidaTren.release(); // Se libera el viaje
        }
        mutex.release(); // Se libera el bloqueo de contar pasajeros
        semViajar.acquire(); // Se bloquea al pasajero para viajar
    }

    public void descender() { // ControlTren
        System.out.println("Descendiendo pasajeros...🏁");
        this.cantAsientosOcupados = 0; // Se actualiza el contador de asientos
        this.semViajar.release(this.MAXIMO); // Se habilita a los pasajeros a viajar

    }

    public void ingresarPasajeros() { // ControlTren
        System.out.println("Comienza un nuevo viaje...➡");
        this.estanTodos.release(this.MAXIMO); // Se liberan permisos para que ingresen nuevamente los pasajeros
    }
}