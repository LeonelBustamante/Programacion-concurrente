package TrabajoPractico3;

import java.util.Random;

public class Orco implements Runnable {
    private Vida vida;

    public Orco(Vida vida) {
        this.vida = vida;
    }

    private void atacar() {
        vida.menosVida();
        try {
            Thread.sleep(new Random().nextInt(2) * 1000);
        } catch (InterruptedException e) {
        }
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            if (vida.getVida() > 0) {
                System.out.println(Thread.currentThread().getName() + " atacara. Vida restante " + vida.getVida());
                this.atacar();
                System.out.println(Thread.currentThread().getName() + " atacara. Vida restante " + vida.getVida());
            } else {
                System.out.println("EL HEROE ESTA MUERTO");
                i = 100;
            }
        }
    }
}
