import java.util.Random;
import java.util.concurrent.Semaphore;

public class practicando {

}

class Main {
    public static void main(String[] args) {
        int cantidadAlumnos = 100; // cantidad de alumnos que intentan estar en la clase
        Salon s = new Salon(); // Objeto compartido
        for (int i = 0; i < cantidadAlumnos; i++) { // Creacion de hilos
            new Thread(new Alumno(s, new Random().nextInt(3), new Random().nextInt(3)), "ALUMNO - " + i).start();
        }
    }
}

class Alumno implements Runnable {
    private Salon s;
    String a1, a2;
    int preferencia[];

    public Alumno(Salon s, int act1, int act2) {
        this.s = s;
        this.a1 = (act1 == 0 ? "Telas" : ((act1 == 1) ? "Lyra" : "aro"));
        this.a2 = (act2 == 0 ? "Telas" : ((act2 == 1) ? "Lyra" : "aro"));
        this.preferencia = new int[] { (act1 + 1 % 3), (act2 + 2 % 3) };
    }

    public void run() {
        if (s.intentarActividad1(a1)) {
            this.hacerActividad();

        }

    }

    private void hacerActividad() {
        try {
            Thread.sleep(300);
        } catch (Exception e) {
        }
    }

class Salon {
    private Semaphore semTelas = new Semaphore(4);
    private Semaphore semLyra = new Semaphore(4);
    private Semaphore semAro = new Semaphore(4);
    private Semaphore entrada = new Semaphore(12);


    public boolean intentarActividad1(String a1) {
        return false;
    }
}