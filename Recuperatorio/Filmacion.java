package Recuperatorio;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Filmacion {

    private final LinkedList capitulosIdiomaOriginal, bufferTraducidos, capitulosIdiomaTraducidoDisponibles;
    private final Lock capitulos, traductores;
    private int ultimoOriginal, ultimoTraducido;

    public Filmacion() {
        this.capitulosIdiomaOriginal = new LinkedList();
        this.bufferTraducidos = new LinkedList();
        this.capitulosIdiomaTraducidoDisponibles = new LinkedList();
        this.capitulos = new ReentrantLock();
        this.traductores = new ReentrantLock();
        this.ultimoOriginal = 0;
        this.ultimoTraducido = 0;
    }

    synchronized int consultarCapitulosDisponibles(boolean idiomaOriginal) {
        /**
         * Metodo que retorna la cantidad de capitulos disponibles en el idioma
         * que el socio requiere. idiomaOriginal indica si se desea en el idioma
         * original
         */
        while (ultimoOriginal == 0) {
            System.out.println("**SISTEMA: " + Thread.currentThread().getName() + " PRIMER CAPITULO EN PRODUCCION ");
            capitulos.lock();
        }
        while (idiomaOriginal && ultimoTraducido == 0) {
            //En caso de verla en idioma original no entra aca
            System.out.println("**SISTEMA: " + Thread.currentThread().getName() + " PRIMER CAPITULO EN ETAPA DE TRADUCCION ");
            capitulos.lock();
        }
        return idiomaOriginal ? ultimoOriginal : ultimoTraducido;
    }

    void mirarCapituloDisponible(int capituloSeleccionado) {
        /**
         * Metodo que indica que el socio se encuentra viendo el capitulo.
         * seleccionado capituloSeleccionado no deberia ser mayor a ultimo en
         * ningun momento
         */
        System.out.println("**SISTEMA: " + Thread.currentThread().getName() + " ESTA VIENDO EL CAPITULO " + capituloSeleccionado);
    }

    synchronized void agregarABiblioteca(Capitulo capitulo, int ultimo) {
        /**
         * Metodo que indica que el Filmador producio una pelicula y agrego esta
         * a la biblioteca para poder ser vista en idioma original. Tambien se
         * agrega a una lista para que sea traducida
         */
        System.out.println("**SISTEMA: " + Thread.currentThread().getName() + " AGREGO UN NUEVO CAPITULO");
        capitulosIdiomaOriginal.add(capitulo);
        bufferTraducidos.add(capitulo);
        ultimoOriginal++;
        capitulos.notifyAll();
        traductores.notifyAll();
    }

    synchronized Capitulo comenzarATraducir() {
        /**
         * Metodo que toma el ultimo capitulo a ser traducido y quita a este del
         * buffer. Se actualiza la cantidad de ultimo traducido para que los
         * socio
         */
        while (bufferTraducidos.isEmpty()) {
            System.out.println("**SISTEMA: " + Thread.currentThread().getName() + " NO HAY PARA SER TRADUCIDO.");
            traductores.lock();
        }
        Capitulo capituloATraducir = (Capitulo) bufferTraducidos.pollFirst();
        System.out.println("**SISTEMA: " + Thread.currentThread().getName() + " TRADUCIRA." + capituloATraducir.getId());
        return capituloATraducir;
    }

    synchronized void agregarABibliotecaTraducidas(Capitulo capitulo) {
        /**
         * Metodo que indica que el Filmador producio una pelicula y agrego esta
         * a la biblioteca para poder ser vista en idioma original. Tambien se
         * agrega a una lista para que sea traducida
         */
        System.out.println("**SISTEMA: " + Thread.currentThread().getName() + " TRADUCIO UN NUEVO CAPITULO");
        ultimoTraducido++;
        capitulosIdiomaTraducidoDisponibles.add(capitulo);
        capitulos.notifyAll();
    }

}
