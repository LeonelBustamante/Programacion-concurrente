package Recuperatorio;

public class Capitulo {

    private int id;
    private boolean traducida;

    public Capitulo(int id) {
        this.id = id;
        this.traducida = false;
    }

    public int getId() {
        return id;
    }

    public boolean isTraducida() {
        return traducida;
    }

    public void traducida() {
        this.traducida = true;
    }

}
