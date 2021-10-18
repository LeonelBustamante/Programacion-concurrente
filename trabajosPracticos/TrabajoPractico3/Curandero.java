package TrabajoPractico3;

import java.util.Random;

public class Curandero implements Runnable {
    private Vida vida;

    public Curandero(Vida vida) {
        this.vida = vida;
    }

    private void curar() {
        vida.masVida();
        try {
            Thread.sleep(new Random().nextInt(2) * 1000);
        } catch (InterruptedException e) {
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (vida.getVida() > 0) {
                System.out.println(Thread.currentThread().getName() + " curara. Vida restante " + vida.getVida());
                this.curar();
                System.out.println(Thread.currentThread().getName() + " Esta curando. Vida restante " + vida.getVida());
            } else {
                System.out.println("EL HEROE ESTA MUERTO");
                i = 100;
            }
        }
    }
}
