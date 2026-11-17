package Modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Cita {
    private Paciente paciente;
    private Medico medico;
    private LocalDateTime fecha;
    private EstadoCita estado;

    public Cita(Paciente paciente, Medico medico, LocalDateTime fecha) {
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
        this.estado = EstadoCita.PROGRAMADA;
    }

    public Paciente getPaciente() { return paciente; }
    public Medico getMedico() { return medico; }
    public LocalDateTime getFecha() { return fecha; }
    public EstadoCita getEstado() { return estado; }
    public void setEstado(EstadoCita estado) { this.estado = estado; }

    @Override
    public String toString() {
        String fechaTexto = fecha.format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
        );
        return fechaTexto + " | Paciente: " + paciente.getNombre()
                + " | Médico: " + medico.getNombre()
                + " | " + estado;
    }
}
