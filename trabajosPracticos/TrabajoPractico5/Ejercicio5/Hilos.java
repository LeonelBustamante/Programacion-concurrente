package TrabajoPractico5.Ejercicio5;

public class Hilos {

}

class Encargado implements Runnable {

    private Mirador mirador;

    public Encargado(Mirador mirador, int cantidadTurista) {
        this.mirador = mirador;
    }

    public void run() {
        try {
            while (true) {
                mirador.decidirLado();
            }
        } catch (Exception e) {
        }
    }

class Turista implements Runnable {
    private int tobganAsignado;
    private Mirador mirador;

    public Turista(Mirador mirador) {
        this.mirador = mirador;
    }

    public void descender() throws Exception {
        System.out.println(Thread.currentThread().getName() + " DESCENDIENDO");
        Thread.sleep(100);
    }

    public void run() {
        try {
            mirador.subirAlMirador();
            if(mirador.usarLadoA()){
                mirador.usarToboganA();
            descender();
                mirador.liberarToboganA();
            }else{
                mirador.usarToboganB();
descender();
                mirador.liberarToboganB();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}