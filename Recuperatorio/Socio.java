package Recuperatorio;

import java.util.Random;

public class Socio implements Runnable {

    private final Filmacion filmacion;
    private final boolean idiomaOriginal;

    public Socio(Filmacion filmacion, boolean idiomaOriginal) {
        this.filmacion = filmacion;
        this.idiomaOriginal = idiomaOriginal;
    }

    public void run() {
        /**
         * La repetitiva intenta buscar cuales capitulos se encuentran
         * disponibles en el idioma que el Socio desea ver para posteriormente
         * seleccionar un capitulo disponible de manera al azar.
         */
        int cantidadCapitulosDisponibles, idCapituloSeleccionado;

        try {
            while (true) {
                //Se busca la cantidad de capitulo disponibles según idioma
                cantidadCapitulosDisponibles = filmacion.consultarCapitulosDisponibles(idiomaOriginal);

                //Se selecciona al azar uno de estos capitulos
                idCapituloSeleccionado = new Random().nextInt(cantidadCapitulosDisponibles);

                //Se realiza la busqueda del capitulo en la biblioteca
                filmacion.mirarCapituloDisponible(idCapituloSeleccionado, idiomaOriginal);

                //Se simula acción de ver el capitulo seleccionado
                System.out.println(Thread.currentThread().getName() + " EMPEZO a ver el capitulo");
                mirandoCapitulo();
                System.out.println(Thread.currentThread().getName() + " TERMINO de ver el capitulo");
            }
        } catch (Exception e) {
        }
    }

    private void mirandoCapitulo() throws Exception {
        Thread.sleep(new Random().nextInt(2000, 3000));
    }

}
