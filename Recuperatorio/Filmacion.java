package Recuperatorio;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Filmacion {

    private LinkedList bibliotecaOriginal = new LinkedList();   //Biblioteca de capitulos originales
    private LinkedList bibliotecaTraducidos = new LinkedList(); //Biblioteca de capitulos traducidos
    private LinkedList bufferTraducidos = new LinkedList();     //Buffer utilizado en productor consumidor entre filmador y traductores
    private LinkedList debuggingId = new LinkedList<Integer>(); //Debugger de id

    private Lock socios = new ReentrantLock();      //Lock correspondiente a los socios
    private Lock traductores = new ReentrantLock(); //Lock correspondiente a los traductores
    private Lock lock = new ReentrantLock(); //Lock correspondiente a los traductores

    private Condition capitulosDisponibles = socios.newCondition();     //Condicion para que se avise que hay nuevos capitulos
    private Condition capitulosIngles = socios.newCondition();          //Condicion para que se avise nuevo capitulo en ingles
    private Condition capitulosATraducir = traductores.newCondition();  //Condicion para avisar a traductores nuevo elemento
    private Condition controlOrden = traductores.newCondition(); //Condicion para regular acomodamiento en bibliotecaTraducidos

    private int ultimoOriginal = 0; //Indicador de ultimo elemento de bibliotecaOriginal
    private int ultimoTraducido = 0;//Indicador de ultimo elemento de bibliotecaTraducidos

    public Filmacion() {
    }

    int consultarCapitulosDisponibles(boolean idiomaOriginal) throws InterruptedException {//Metodo para el Socio
        /**
         * Metodo que retorna la cantidad de capitulos disponibles en el idioma
         * que el socio requiere.
         */
        socios.lock();

        try {
            //Espera al primer capitulo de la serie
            while (ultimoOriginal == 0) {
                System.out.println("SISTEMA: " + Thread.currentThread().getName()
                        + " ESPERANDO PRIMER CAPITULO ");
                capitulosDisponibles.await();
            }

            //Espera al primer capitulo traducido de la serie
            while (!idiomaOriginal && ultimoTraducido == 0) {
                System.out.println("SISTEMA: " + Thread.currentThread().getName()
                        + " ESPERANDO PRIMER CAPITULO TRADUCIDO");
                capitulosIngles.await();
            }

            //Se retorna el tamaño que posea la biblioteca segun idioma del socio
            return idiomaOriginal ? ultimoOriginal : ultimoTraducido;
        } finally {
            socios.unlock();
        }
    }

    void mirarCapituloDisponible(int capituloSeleccionado, boolean idiomaOriginal) throws InterruptedException {//Metodo para el Socio
        /**
         * Metodo que indica que el socio se encuentra viendo el capitulo. Este
         * metodo lo realizo para demostrar que se esta buscando en la lista el
         * capitulo pero capituloSeleccionado es identico al ((Capitulo)
         * capitulosIdiomaOriginal.get(capituloSeleccionado)).getId(). y que el
         * idioma para la version en ingles tambien coincide con que esta viendo
         * un capitulo que si esta traducido
         *
         * De quitarse este metodo socio, el programa funciona correctamente
         */
        if (idiomaOriginal) {
            //Se aumenta en 1 capitulo seleccionado ya que este es la posicion en el arreglo y la numeracion de id comienza en 1
            System.out.println("->SISTEMA: " + Thread.currentThread().getName()
                    + "(para corroborar informacion: en original-> " + idiomaOriginal + ", capitulo seleccionado-> " + (capituloSeleccionado + 1) + ")"
                    + " VERA EL CAPITULO " + ((Capitulo) bibliotecaOriginal.get(capituloSeleccionado)).getId()
                    + " en español ");
        } else {
            System.out.println("->SISTEMA: " + Thread.currentThread().getName()
                    + "(para corroborar informacion: en original-> " + idiomaOriginal + ", capitulo seleccionado-> " + (capituloSeleccionado + 1) + ")"
                    + " VERA EL CAPITULO " + ((Capitulo) bibliotecaTraducidos.get(capituloSeleccionado)).getId()
                    + " en ingles. comprobacion de idioma ("
                    + ((Capitulo) bibliotecaTraducidos.get(capituloSeleccionado)).isTraducida() + ")");
        }
    }

    void agregarABiblioteca(Capitulo capitulo) throws InterruptedException {//Metodo para el Filmador
        /**
         * Metodo que indica que el Filmador producio un capitulo y agrego esta
         * a la biblioteca para poder ser vista en idioma original. Tambien se
         * agrega a un buffer para ser traducida
         */

        socios.lock();
        traductores.lock();
        try {
            //Se agrega a la biblioteca y al buffer el capitulo producido
            bibliotecaOriginal.add(capitulo);
            bufferTraducidos.add(capitulo);

            //Se incrementa la version original ya que ya se puede ver
            ultimoOriginal++;

            //Se da aviso a las condiciones en espera
            capitulosDisponibles.signalAll();
            capitulosATraducir.signalAll();
        } finally {
            socios.unlock();
            traductores.unlock();
        }
    }

    Capitulo buscarParaTraducir() throws InterruptedException {//Metodo para el Traductor
        /**
         * Metodo que retorna el ultimo capitulo ingresado al buffer y quita a
         * este del mismo.
         */

        traductores.lock();
        try {

            //Si el buffer esta vacio se debe esperar algun nuevo capitulo
            while (bufferTraducidos.isEmpty()) {
                System.out.println("xxxx SISTEMA: " + Thread.currentThread().getName() + " NO HAY PARA SER TRADUCIDO.");
                capitulosATraducir.await();
            }

            //Se retorna el primer capitulo que se encuentre en el buffer
            return (Capitulo) bufferTraducidos.pollFirst();

        } finally {
            traductores.unlock();
        }
    }

    void agregarABibliotecaTraducidas(Capitulo capitulo) throws InterruptedException {//Metodo para el Traductor
        /**
         * Metodo que indica que el Traductor traducio un capitulo e intenta
         * guardarlo en la biblioteca, se debe verificar que este sea en el
         * orden correcto. Tambien se actualiza la cantidad de capitulos
         * traducidos y se da aviso a los socios
         */

        traductores.lock();

        try {
            //Se verifica que se realice el correcto guardado de los capitulos
            listar();
            while (!(capitulo.getId() == 1)
                    && capitulo.getId() - 1 > ((Capitulo) bibliotecaTraducidos.getLast()).getId()) {
                System.out.println(Thread.currentThread().getName() + "ESPERANDO PARA CARGAR EN ORDEN CORRECTO "
                        + capitulo.getId());
                controlOrden.await();
            }

            //Se agrega el capitulo a la biblioteca y se actualiza el contador
            bibliotecaTraducidos.add(capitulo);
            debuggingId.add(capitulo.getId());
            ultimoTraducido++;

            //Se da aviso al traductor que ya se coloco el capitulo en la posicion correcta
            controlOrden.signalAll();

            //Se da aviso a los socios del nuevo capitulo en ingles
            socios.lock();
            capitulosIngles.signalAll();
            socios.unlock();

        } finally {
            traductores.unlock();
        }
    }

    private void listar() {
        String cadena = "(";
        for (int i = 0; i < debuggingId.size(); i++) {
            cadena += debuggingId.get(i) + ",";
        }
        System.out.println(cadena + ")");
    }

}
