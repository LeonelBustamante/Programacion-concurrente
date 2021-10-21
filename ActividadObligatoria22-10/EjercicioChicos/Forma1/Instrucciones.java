package EjercicioChicos.Forma1;

public class Instrucciones {

    private int a;
    private int b;
    private int x;
    private int y;
    private int z;
    private int c;
    private int w;

    public Instrucciones(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;

    }

    public void s1() {
        a = x + y;
    }

    public void s2() {
        b = z + 1;
    }

    public void s3() {
        c = a - b;
    }

    public void s4() {
        w = c + 1;
    }

    public int getW() {
        return w;
    }
}