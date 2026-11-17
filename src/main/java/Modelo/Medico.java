package Modelo;

public class Medico extends Persona {
    private String especialidad;

    public Medico(String nombre, String documento, String especialidad) {
        super(nombre, documento);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    @Override
    public String toString() {
        return getNombre() + " - " + especialidad;
    }
}
