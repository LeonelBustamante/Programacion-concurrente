package TrabajoPractico3;

public class Letra implements Runnable {
    private static Turno t = new Turno();
    private int turno;
    private char letra;
    private int cant;

    public Letra(int turno, char letra, int cant) {
        this.turno = turno;
        this.letra = letra;
        this.cant = cant;
    }

    public void imprimirLetra(int cant) {
        for (int i = 0; i < cant; i++) {
            System.out.print(this.letra);
        }
    }

    public void run() {
        int cant = 0;
        do {
            if (t.getTurno() == this.turno) {
                imprimirLetra(this.cant);
                t.siguienteTurno();
                cant++;
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
            }
        } while (cant < 3);

    }
}
