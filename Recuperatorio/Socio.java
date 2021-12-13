package Recuperatorio;

import java.util.Random;

public class Socio implements Runnable {

    private final Filmacion f;
    private final boolean idiomaOriginal;

    public Socio(Filmacion f, boolean idiomaOriginal) {
        this.f = f;
        this.idiomaOriginal = idiomaOriginal;
    }

    public void run() {
        /**
         * La repetitiva intenta buscar cuales capitulos se encuentran
         * disponibles en el idioma que el Socio desea ver para posteriormente
         * seleccionar un capitulo disponible
         *
         * capitulosDisponibles indica el tope de peliculas producidas hasta el
         * momento de que el cliente busca
         */
        int capitulosDisponibles, capituloSeleccionado;
        try {
            while (true) {
                capitulosDisponibles = f.consultarCapitulosDisponibles(idiomaOriginal);
                capituloSeleccionado = new Random().nextInt(capitulosDisponibles);//CHEQUEARLO
                f.mirarCapituloDisponible(capituloSeleccionado, idiomaOriginal);
                mirandoCapitulo();
                System.out.println(Thread.currentThread().getName() + " termino de ver el capitulo " + capituloSeleccionado);
            }
        } catch (Exception e) {
        }
    }

    private void mirandoCapitulo() throws Exception {
        Thread.sleep(new Random().nextInt(200));
    }

}
