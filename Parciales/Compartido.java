import java.util.concurrent.Semaphore;

public class Compartido {
}

class Acrobacia {
    private Semaphore[] lugares = new Semaphore[] { new Semaphore(4), new Semaphore(4), new Semaphore(4) };
    private Semaphore turno = new Semaphore(12);
    private Semaphore continuar = new Semaphore(0);
    private Semaphore control = new Semaphore(0);
    private Semaphore mutex = new Semaphore(1);
    private int alumnos;

    public Acrobacia() {// CONSTRUCTOR
    }

    public void hacerActividad(int act) throws InterruptedException {
        // Metodo donde se bloquean antes de realizar la actividad
        continuar.acquire();
        lugares[act].release();
        System.out.println(Thread.currentThread().getName() + " termino actividad");
    }

    public void entrarSalon() throws Exception {
        // metodo que verifica que solo 12 alumnos haya en el turno
        turno.acquire();
        System.out.println(Thread.currentThread().getName() + " entro al salon");
    }

    public boolean entrarAct(int act) throws InterruptedException {
        // metodo que agrega un alumno y chequea si estan los 4 en su actividad
        // luego si estan todos los alumnos del turno se libera el control para comenzar
        boolean res = true;
        if (lugares[act].tryAcquire()) {
            mutex.acquire();
            alumnos++;
            mutex.release();
            if (alumnos == 12) {
                System.out.println(Thread.currentThread().getName() + " YO LIBERE A TODOS");
                control.release();
            }
        } else {
            res = false;
        }
        return res;
    }
    
    public void arrancaClase1() throws InterruptedException {
        // metodo que da inicio a la actividad 1 simultaneamente a todos y actualiza
        // variable de control
        control.acquire();
        alumnos = 0;
        System.out.println(Thread.currentThread().getName() + " ARRANCO LA CLASE 1");
        continuar.release(12);
        Thread.sleep(300); // 30 min de actividad
    }
    
    public void arrancaClase2() throws InterruptedException {
        // metodo que da inicio a la actividad 2 simultaneamente a todos y actualiza
        // variable de control
        control.acquire();
        alumnos = 0;
        System.out.println(Thread.currentThread().getName() + " ARRANCO LA CLASE 2");
        continuar.release(12);
        Thread.sleep(300); // 30 min de actividad
    }
    
    public void salirSalon() {
        System.out.println(Thread.currentThread().getName() + " NOS FUIMOS");
        turno.release();// se saca al hilo del turno
    }
}