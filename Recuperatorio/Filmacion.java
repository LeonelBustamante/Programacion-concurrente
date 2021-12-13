package Recuperatorio;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Filmacion {

    private LinkedList capitulosIdiomaOriginal, bufferTraducidos, capitulosIdiomaTraducidoDisponibles;
    private Lock capitulos, traductores, lock;
    private Condition capitulosDisponibles, capitulosATraducir;
    private int ultimoOriginal, ultimoTraducido;

    public Filmacion() {
        this.capitulosIdiomaOriginal = new LinkedList();
        this.bufferTraducidos = new LinkedList();
        this.capitulosIdiomaTraducidoDisponibles = new LinkedList();
        this.capitulos = new ReentrantLock();
        this.traductores = new ReentrantLock();
        this.lock = new ReentrantLock();
        this.capitulosDisponibles = capitulos.newCondition();
        this.capitulosATraducir = traductores.newCondition();
        this.ultimoOriginal = 0;
        this.ultimoTraducido = 0;
    }

    int consultarCapitulosDisponibles(boolean idiomaOriginal) throws InterruptedException {//Metodo para el Socio
        /**
         * Metodo que retorna la cantidad de capitulos disponibles en el idioma
         * que el socio requiere. idiomaOriginal indica si se desea en el idioma
         * original
         */

        capitulos.lock();

        try {
            //Metodo que espera al primer capitulo que se genere
            while (ultimoOriginal == 0) {
                System.out.println("SISTEMA: " + Thread.currentThread().getName() + " ESPERANDO PRIMER CAPITULO ");
                capitulosDisponibles.await();
            }

            //Metodo que espera al primer capitulo traducido que se genere
            while (!idiomaOriginal && ultimoTraducido == 0) {
                //En caso de verla en idioma original no entra aca
                System.out.println("SISTEMA: " + Thread.currentThread().getName() + " ESPERANDO PRIMER CAPITULO TRADUCIDO");
                capitulosDisponibles.await();
            }

            return idiomaOriginal ? ultimoOriginal : ultimoTraducido;

        } finally {
            capitulos.unlock();
        }
    }

    void mirarCapituloDisponible(int capituloSeleccionado, boolean idiomaOriginal) throws InterruptedException {
        //Metodo para el Socio
        /**
         * Metodo que indica que el socio se encuentra viendo el capitulo.
         * seleccionado capituloSeleccionado no deberia ser mayor a ultimo en
         * ningun momento
         */

        lock.lock();

        try {
            if (idiomaOriginal) {
                System.out.println("->SISTEMA: " + Thread.currentThread().getName()
                        + " VERA EL CAPITULO " + ((Capitulo) capitulosIdiomaOriginal.get(capituloSeleccionado)).getId()
                        + " en español ");

            } else {
                System.out.println("->SISTEMA: " + Thread.currentThread().getName()
                        + " VERA EL CAPITULO " + ((Capitulo) capitulosIdiomaOriginal.get(capituloSeleccionado)).getId()
                        + " en ingles ");
            }

        } finally {
            lock.unlock();
        }
    }

    void agregarABiblioteca(Capitulo capitulo) throws InterruptedException {//Metodo para el Filmador
        /**
         * Metodo que indica que el Filmador producio una pelicula y agrego esta
         * a la biblioteca para poder ser vista en idioma original. Tambien se
         * agrega a una lista para que sea traducida
         */

        capitulos.lock();
        traductores.lock();

        try {

            capitulosIdiomaOriginal.add(capitulo);
            bufferTraducidos.add(capitulo);

            ultimoOriginal++;

            capitulosDisponibles.signalAll();
            capitulosATraducir.signalAll();

        } finally {
            capitulos.unlock();
            traductores.unlock();
        }
    }

    Capitulo comenzarATraducir() throws InterruptedException {//Metodo para el Traductor
        /**
         * Metodo que toma el ultimo capitulo a ser traducido y quita a este del
         * buffer. Se actualiza la cantidad de ultimo traducido para que los
         * socio
         */

        traductores.lock();

        try {

            while (bufferTraducidos.isEmpty()) {
                System.out.println("xxxx SISTEMA: " + Thread.currentThread().getName() + " NO HAY PARA SER TRADUCIDO.");
                capitulosATraducir.await();
            }

            return (Capitulo) bufferTraducidos.pollFirst();

        } finally {
            traductores.unlock();
        }
    }

    void agregarABibliotecaTraducidas(Capitulo capitulo) throws InterruptedException {//Metodo para el Traductor
        /**
         * Metodo que indica que el fue traducido un capitulo y se actualiza la
         * variable para poder ser seleccionado este capitulo.
         *
         * @ultimoTraducido: indica el ultimo numero disponible de capitulo
         * traducido
         */

        traductores.lock();
        capitulos.lock();

        try {

            capitulosIdiomaTraducidoDisponibles.add(capitulo);
            ultimoTraducido++;

            capitulosDisponibles.signalAll();

        } finally {
            traductores.unlock();
            capitulos.unlock();
        }
    }

}
