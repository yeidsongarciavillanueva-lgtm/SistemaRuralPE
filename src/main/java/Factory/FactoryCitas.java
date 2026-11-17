package Factory;

import Modelo.Cita;
import Modelo.Medico;
import Modelo.Paciente;
import java.time.LocalDateTime;

public class FactoryCitas {
    public Cita crearCita(Paciente paciente, Medico medico,
            LocalDateTime fecha) {
        return new Cita(paciente, medico, fecha);
    }
}
