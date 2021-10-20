package TrabajoPractico5;

public class Animal implements Runnable {
    private String nombre;
    private boolean esPerro;
    private Comedor comedor;

    public Animal(String nombre, boolean esPerro, Comedor comedor) {
        this.nombre = nombre;
        this.esPerro = esPerro;
        this.comedor = comedor;
    }

    @Override
    public void run() {
        comedor.comer(nombre);
        comedor.dejarDeComer(nombre);

    }

}
