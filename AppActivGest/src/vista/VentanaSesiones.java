package vista;

import modelo.Sesion;
import vista.TableModels.SesionesTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VentanaSesiones {
    private JPanel ventanaSesionesJpanel;
    private JLabel fechaJlabel;
    private JLabel horarioJlabel;
    private JLabel respJlabel;
    private JTable FechaHorarioRespTable;
    private JButton atrasButton;

    private List<Sesion> sesiones; // -> ¡CARGAR DESDE BASES DE DATOS!

    public VentanaSesiones(String nombreAcUsEm, String cc) {
        JFrame frame = new JFrame("Sesiones " + nombreAcUsEm + " " + cc);
        frame.setContentPane(ventanaSesionesJpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        cargarDatosEjemplo();

        if (sesiones != null){
            cargarDatosEnTabla();
        }else {
            javax.swing.JOptionPane.showMessageDialog(null, "List sesiones vacío!");
        }


        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
            }
        });
    }
    public void cargarDatosEnTabla(){
        FechaHorarioRespTable.setModel(new SesionesTableModel(sesiones));
    }

    private void cargarDatosEjemplo(){
        sesiones = new ArrayList<>();
        sesiones.add(new Sesion("15:30", "Lunes"));
        sesiones.add(new Sesion("10:00", "Jueves"));
        sesiones.add(new Sesion("18:00", "Miercoles"));
        sesiones.add(new Sesion("9:00", "Sabado"));
    }
}
