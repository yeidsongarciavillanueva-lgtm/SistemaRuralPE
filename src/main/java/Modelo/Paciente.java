package Modelo;

public class Paciente extends Persona {
    private int edad;

    public Paciente(String nombre, String documento, int edad) {
        super(nombre, documento);
        this.edad = edad;
    }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    @Override
    public String toString() {
        return getNombre() + " - DNI: " + getDocumento();
    }
}
