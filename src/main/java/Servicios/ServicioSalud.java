package Servicios;

import Excepciones.DatoInvalidoException;
import Factory.FactoryCitas;
import Modelo.Cita;
import Modelo.Medico;
import Modelo.Paciente;
import Repositorio.RepositorioSalud;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ServicioSalud {
    private RepositorioSalud repositorio = new RepositorioSalud();
    private FactoryCitas factory = new FactoryCitas();

    public ServicioSalud() {
        repositorio.agregarMedico(new Medico(
                "Dra. Ana Torres", "11111111", "Medicina general"));
        repositorio.agregarMedico(new Medico(
                "Dr. Luis Rojas", "22222222", "Enfermería"));
    }

    public void registrarPaciente(String nombre, String documento, int edad)
            throws DatoInvalidoException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new DatoInvalidoException("Escribe el nombre.");
        }
        if (documento == null || !documento.matches("[0-9]{8}")) {
            throw new DatoInvalidoException(
                    "El DNI debe tener 8 números.");
        }
        if (edad < 0 || edad > 120) {
            throw new DatoInvalidoException("Revisa la edad.");
        }
        repositorio.agregarPaciente(
                new Paciente(nombre.trim(), documento, edad));
    }

    public void programarCita(int indicePaciente, int indiceMedico,
            LocalDateTime fecha) throws DatoInvalidoException {
        ArrayList<Paciente> pacientes = repositorio.getPacientes();
        ArrayList<Medico> medicos = repositorio.getMedicos();

        if (indicePaciente < 0 || indicePaciente >= pacientes.size()) {
            throw new DatoInvalidoException("Selecciona un paciente.");
        }
        if (indiceMedico < 0 || indiceMedico >= medicos.size()) {
            throw new DatoInvalidoException("Selecciona un profesional.");
        }
        if (fecha == null || fecha.isBefore(LocalDateTime.now())) {
            throw new DatoInvalidoException(
                    "La fecha debe ser actual o futura.");
        }

        Cita cita = factory.crearCita(
                pacientes.get(indicePaciente),
                medicos.get(indiceMedico), fecha);
        repositorio.agregarCita(cita);
    }

    public ArrayList<Paciente> getPacientes() {
        return repositorio.getPacientes();
    }

    public ArrayList<Medico> getMedicos() {
        return repositorio.getMedicos();
    }
    
    public ArrayList<Cita> getCitasProgramadas() {
        return repositorio.getCitas().stream()
                .filter(cita -> cita.getEstado().toString()
                        .equals("PROGRAMADA"))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
