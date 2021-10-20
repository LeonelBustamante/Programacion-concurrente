package TrabajoPractico3;

public class MiHilo2 implements Runnable {

    Thread hilo;
    static sumaMatriz2 sumaM = new sumaMatriz2();
    int arr[];
    int sumaParcial;
    int posInicial;
    int posFinal;

    // Construye un nuevo hilo.
    public MiHilo2(String nombre, int nums[], int inicial, int finals) {
        hilo = new Thread(this, nombre);
        arr = nums;
        this.posFinal = finals;
        this.posInicial = inicial;
    }

    public int getParcial() {
        return this.sumaParcial;
    }

    // Punto de entrada del hilo
    public void run() {
        System.out.println(hilo.getName() + " iniciando.");
        sumaParcial = sumaM.sumMatriz(arr, this.posInicial, this.posFinal);
        System.out.println("Suma para " + hilo.getName() + " es " + sumaParcial);
        System.out.println(hilo.getName() + " terminado.");
    }
}