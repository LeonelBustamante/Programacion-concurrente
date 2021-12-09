public class Alumno implements Runnable {
    private Acrobacia a;
    int act1, act2;

    public Alumno(Acrobacia a, int act1, int act2) {
        this.a = a;
        this.act1 = act1;
        this.act2 = act2;
    }

    public void run() {
        boolean cansado=false;
        while (!cansado) {
            try {
                a.entrarSalon();
                if (a.entrarAct(act1)) {
                    a.hacerActividad(act1);
                    while (!a.entrarAct(act2)) {
                        act2 = act2 + 1 % 3;
                    }
                    cansado = true;
                }
                a.salirSalon();
            } catch (Exception e) {
            }
        }
    }

}