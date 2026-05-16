package vista;

import datos.ResultadoDAO;
import logica.MetodoEuler;
import logica.MetodoRK4;
import modelo.Resultado;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private JTextField txtX0;
    private JTextField txtY0;
    private JTextField txtH;
    private JTextField txtIteraciones;

    private JTextArea areaResultado;

    private JButton btnEuler;
    private JButton btnRK4;

    public VentanaPrincipal() {

        setTitle(
                "Ecuaciones Diferenciales"
        );

        setSize(800, 600);

        setDefaultCloseOperation(
                EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        iniciarComponentes();
    }

    private void iniciarComponentes() {

        JPanel panel =
                new JPanel(new GridLayout(5,2));

        panel.add(new JLabel("X0"));
        txtX0 = new JTextField();
        panel.add(txtX0);

        panel.add(new JLabel("Y0"));
        txtY0 = new JTextField();
        panel.add(txtY0);

        panel.add(new JLabel("H"));
        txtH = new JTextField();
        panel.add(txtH);

        panel.add(new JLabel("Iteraciones"));
        txtIteraciones =
                new JTextField();
        panel.add(txtIteraciones);

        btnEuler =
                new JButton("Metodo Euler");

        btnRK4 =
                new JButton("Metodo RK4");

        panel.add(btnEuler);
        panel.add(btnRK4);

        add(panel, BorderLayout.NORTH);

        areaResultado =
                new JTextArea();

        JScrollPane scroll =
                new JScrollPane(areaResultado);

        add(scroll, BorderLayout.CENTER);

        btnEuler.addActionListener(e -> {

            ejecutarEuler();
        });

        btnRK4.addActionListener(e -> {

            ejecutarRK4();
        });
    }

    private void ejecutarEuler() {

        double x0 =
                Double.parseDouble(
                        txtX0.getText()
                );

        double y0 =
                Double.parseDouble(
                        txtY0.getText()
                );

        double h =
                Double.parseDouble(
                        txtH.getText()
                );

        int n =
                Integer.parseInt(
                        txtIteraciones.getText()
                );

        String procedimiento =
                MetodoEuler.resolver(
                        x0,
                        y0,
                        h,
                        n
                );

        areaResultado.setText(
                procedimiento
        );

        Resultado r = new Resultado();

        r.setMetodo("EULER");
        r.setEcuacion("y' = x + y");
        r.setX0(x0);
        r.setY0(y0);
        r.setH(h);
        r.setIteraciones(n);

        r.setResultadoFinal(
                MetodoEuler.resultadoFinal(
                        x0,
                        y0,
                        h,
                        n
                )
        );

        r.setProcedimiento(
                procedimiento
        );

        new ResultadoDAO().guardar(r);
    }

    private void ejecutarRK4() {

        double x0 =
                Double.parseDouble(
                        txtX0.getText()
                );

        double y0 =
                Double.parseDouble(
                        txtY0.getText()
                );

        double h =
                Double.parseDouble(
                        txtH.getText()
                );

        int n =
                Integer.parseInt(
                        txtIteraciones.getText()
                );

        String procedimiento =
                MetodoRK4.resolver(
                        x0,
                        y0,
                        h,
                        n
                );

        areaResultado.setText(
                procedimiento
        );

        Resultado r = new Resultado();

        r.setMetodo("RUNGE KUTTA 4");
        r.setEcuacion("y' = x + y");

        r.setX0(x0);
        r.setY0(y0);
        r.setH(h);
        r.setIteraciones(n);

        r.setResultadoFinal(
                MetodoRK4.resultadoFinal(
                        x0,
                        y0,
                        h,
                        n
                )
        );

        r.setProcedimiento(
                procedimiento
        );

        new ResultadoDAO().guardar(r);
    }
}
