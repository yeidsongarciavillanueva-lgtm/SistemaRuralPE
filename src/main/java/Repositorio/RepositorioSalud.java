package Repositorio;

import Modelo.Cita;
import Modelo.Medico;
import Modelo.Paciente;
import java.util.ArrayList;

public class RepositorioSalud {
    private ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
    private ArrayList<Medico> medicos = new ArrayList<Medico>();
    private ArrayList<Cita> citas = new ArrayList<Cita>();

    public void agregarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    public void agregarMedico(Medico medico) {
        medicos.add(medico);
    }

    public void agregarCita(Cita cita) {
        citas.add(cita);
    }

    public ArrayList<Paciente> getPacientes() {
        return new ArrayList<Paciente>(pacientes);
    }

    public ArrayList<Medico> getMedicos() {
        return new ArrayList<Medico>(medicos);
    }

    public ArrayList<Cita> getCitas() {
        return new ArrayList<Cita>(citas);
    }
}
