package ActividadObligatoria.Ejercicio2;

public class Main {

    public static void main(String[] args) {
        int cantidadBotellasPorCaja = 10;
        Caja caja = new Caja(cantidadBotellasPorCaja);

        int cantEmpaquetadores = 5, cantEmbotelladores = 3;
        for (int i = 0; i < cantEmpaquetadores; i++) {
            new Thread(new Empaquetador(caja), "Empaquetador " + i).start();
        }

        for (int i = 0; i < cantEmbotelladores; i++) {
            new Thread(new Embotellador(caja), "Embotellador " + i).start();
        }
    }
}
