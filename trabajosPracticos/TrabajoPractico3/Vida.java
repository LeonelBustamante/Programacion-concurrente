package TrabajoPractico3;

public class Vida {
    private int vida;

    public Vida() {
        vida = 10;
    }

    public synchronized int getVida() {
        return this.vida;
    }

    public synchronized void menosVida() {
        this.vida -= 3;
    }

    public synchronized void masVida() {
        this.vida += 3;
    }

}
