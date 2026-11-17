package Interfaz;

import Excepciones.DatoInvalidoException;
import Modelo.Medico;
import Modelo.Paciente;
import Servicios.ServicioSalud;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class VentanaPrincipal extends JFrame {

    private ServicioSalud servicio = new ServicioSalud();

    private JTextField nombre = new JTextField();
    private JTextField dni = new JTextField();
    private JTextField edad = new JTextField();

    private JTextField fecha = new JTextField(
            LocalDateTime.now().plusDays(1)
                    .withMinute(0).withSecond(0).withNano(0)
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
    );

    private JComboBox<Paciente> pacientes =
            new JComboBox<Paciente>();

    private JComboBox<Medico> medicos =
            new JComboBox<Medico>();

    public VentanaPrincipal() {
        setTitle("Sistema Rural PE - avance EP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 350);
        setLocationRelativeTo(null);

        JPanel formulario = new JPanel(
                new GridLayout(0, 2, 5, 5)
        );

        formulario.add(new JLabel("Nombre Y Apellidos:"));
        formulario.add(nombre);

        formulario.add(new JLabel("DNI (8 números):"));
        formulario.add(dni);

        formulario.add(new JLabel("Edad:"));
        formulario.add(edad);

        JButton registrar = new JButton("Registrar paciente");
        formulario.add(registrar);
        formulario.add(pacientes);

        formulario.add(new JLabel("Profesional:"));
        formulario.add(medicos);

        formulario.add(new JLabel("Fecha (dd/MM/yyyy HH:mm):"));
        formulario.add(fecha);

        JButton programar = new JButton("Programar cita");
        formulario.add(programar);
        formulario.add(new JLabel(""));

        add(formulario, BorderLayout.CENTER);

        registrar.addActionListener(e -> registrarPaciente());
        programar.addActionListener(e -> programarCita());

        cargarMedicos();
    }

    private void cargarMedicos() {
        for (Medico medico : servicio.getMedicos()) {
            medicos.addItem(medico);
        }
    }

    private void registrarPaciente() {
        try {
            int edadPaciente = Integer.parseInt(edad.getText());

            servicio.registrarPaciente(
                    nombre.getText(),
                    dni.getText(),
                    edadPaciente
            );

            Paciente paciente = servicio.getPacientes().get(
                    servicio.getPacientes().size() - 1
            );

            pacientes.addItem(paciente);

            nombre.setText("");
            dni.setText("");
            edad.setText("");

            JOptionPane.showMessageDialog(
                    this,
                    "Paciente registrado."
            );

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Escribe una edad numérica."
            );

        } catch (DatoInvalidoException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage()
            );
        }
    }

    private void programarCita() {
        try {
            LocalDateTime fechaCita = LocalDateTime.parse(
                    fecha.getText(),
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
            );

            servicio.programarCita(
                    pacientes.getSelectedIndex(),
                    medicos.getSelectedIndex(),
                    fechaCita
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Cita programada."
            );

        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Usa el formato dd/MM/yyyy HH:mm."
            );

        } catch (DatoInvalidoException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage()
            );
        }
    }

    public static void iniciar() {
        SwingUtilities.invokeLater(() ->
                new VentanaPrincipal().setVisible(true)
        );
    }
}