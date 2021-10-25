package TrabajoPractico5.Ejercicio1;

import java.util.concurrent.Semaphore;

public class Comedor {
    private Semaphore semGato;// turno del gato
    private Semaphore semPerro; // turno del perro
    private Semaphore mutex = new Semaphore(1); // exclusion mutua
    private int cantPlatos, platosUsados;

    public Comedor(int platos) {
        cantPlatos = platos;
        platosUsados = 0;
        semGato = new Semaphore(0, true);
        semPerro = new Semaphore(cantPlatos, true); // arrancan comiendo los perros
    }

    public void esperarTurnoPerro() throws InterruptedException {
        semPerro.acquire();
    }

    public void esperarTurnoGato() throws InterruptedException {
        semGato.acquire();
    }

    private void pasarTurnoPerro() {
        System.out.println("LE TOCA COMER A LOS PERROS");
        semPerro.release(cantPlatos);
    }

    private void pasarTurnoGato() {
        System.out.println("LE TOCA COMER A LOS GATOS");
        semGato.release(cantPlatos);
    }

    public void entrarPerro() throws InterruptedException {
        mutex.acquire();
        if (platosUsados < cantPlatos) {
            System.out.println("El " + Thread.currentThread().getName() + " esta comiendo");
            platosUsados++;
            System.out.println("platos ocupados " + platosUsados);
        }
        mutex.release();
    }

    public void entrarGato() throws InterruptedException {
        mutex.acquire();
        if (platosUsados < cantPlatos) {
            System.out.println("El " + Thread.currentThread().getName() + " esta comiendo");
            platosUsados++;
            System.out.println("platos ocupados " + platosUsados);
        }
        mutex.release();
    }

    public void salirGato() throws InterruptedException {
        mutex.acquire();
        System.out.println("El " + Thread.currentThread().getName() + " dejo de comer");
        platosUsados--;
        System.out.println("platos ocupados " + platosUsados);
        if (platosUsados == 0) {
            pasarTurnoPerro();
        }
        mutex.release();
    }

    public void salirPerro() throws InterruptedException {
        mutex.acquire();
        System.out.println("El " + Thread.currentThread().getName() + " dejo de comer");
        platosUsados--;
        System.out.println("platos ocupados " + platosUsados);
        if (platosUsados == 0) {
            pasarTurnoGato();
        }
        mutex.release();
    }

}