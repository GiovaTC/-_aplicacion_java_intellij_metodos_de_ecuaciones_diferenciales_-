# -_aplicacion_java_intellij_metodos_de_ecuaciones_diferenciales_- :.
Aplicacion Java IntelliJ — Métodos de Ecuaciones Diferenciales:

<img width="1254" height="1254" alt="image" src="https://github.com/user-attachments/assets/126eae71-2eb1-4555-b976-f51829adddaf" />  

```
Java + Swing + Oracle 19c + Registro de Resultados

El siguiente proyecto incluye:

Interfaz gráfica en Java Swing
Dos métodos numéricos para ecuaciones diferenciales:
Método de Euler
Método de Runge-Kutta de 4to Orden (RK4)
Visualización paso a paso del procedimiento
Resultado final
Registro automático en Oracle 19c
Arquitectura sencilla para IntelliJ IDEA
JDBC Oracle .

📁 Estructura del Proyecto
ECUACIONES_DIFERENCIALES/
│
├── src/
│   ├── Main.java
│   │
│   ├── vista/
│   │      VentanaPrincipal.java
│   │
│   ├── logica/
│   │      MetodoEuler.java
│   │      MetodoRK4.java
│   │
│   ├── datos/
│   │      ConexionOracle.java
│   │      ResultadoDAO.java
│   │
│   └── modelo/
│          Resultado.java
│
└── ojdbc11.jar

1. Script Oracle 19c
Tabla RESULTADOS_EDO
CREATE TABLE RESULTADOS_EDO (

    ID_RESULTADO NUMBER GENERATED ALWAYS AS IDENTITY,

    METODO VARCHAR2(50),

    ECUACION VARCHAR2(200),

    X0 NUMBER,

    Y0 NUMBER,

    H NUMBER,

    ITERACIONES NUMBER,

    RESULTADO_FINAL NUMBER,

    PROCEDIMIENTO CLOB,

    FECHA_REGISTRO DATE DEFAULT SYSDATE,

    CONSTRAINT PK_RESULTADOS_EDO
    PRIMARY KEY(ID_RESULTADO)
);

COMMIT;

2. Clase de Conexión Oracle
Archivo: ConexionOracle.java
package datos;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionOracle {

    private static final String URL =
            "jdbc:oracle:thin:@localhost:1521:xe";

    private static final String USER = "SYSTEM";

    private static final String PASSWORD = "123456";

    public static Connection conectar() {

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

            return DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

        } catch (Exception e) {

            System.out.println(
                    "Error conexión: " + e.getMessage()
            );

            return null;
        }
    }
}

3. Modelo Resultado
Archivo: Resultado.java
package modelo;

public class Resultado {

    private String metodo;
    private String ecuacion;
    private double x0;
    private double y0;
    private double h;
    private int iteraciones;
    private double resultadoFinal;
    private String procedimiento;

    public Resultado() {
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getEcuacion() {
        return ecuacion;
    }

    public void setEcuacion(String ecuacion) {
        this.ecuacion = ecuacion;
    }

    public double getX0() {
        return x0;
    }

    public void setX0(double x0) {
        this.x0 = x0;
    }

    public double getY0() {
        return y0;
    }

    public void setY0(double y0) {
        this.y0 = y0;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public int getIteraciones() {
        return iteraciones;
    }

    public void setIteraciones(int iteraciones) {
        this.iteraciones = iteraciones;
    }

    public double getResultadoFinal() {
        return resultadoFinal;
    }

    public void setResultadoFinal(double resultadoFinal) {
        this.resultadoFinal = resultadoFinal;
    }

    public String getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(String procedimiento) {
        this.procedimiento = procedimiento;
    }
}

4. Método de Euler
Archivo: MetodoEuler.java
package logica;

public class MetodoEuler {

    public static String resolver(
            double x0,
            double y0,
            double h,
            int n
    ) {

        StringBuilder procedimiento =
                new StringBuilder();

        procedimiento.append(
                "METODO DE EULER\n\n"
        );

        double x = x0;
        double y = y0;

        procedimiento.append(
                "Ecuacion: y' = x + y\n\n"
        );

        for (int i = 1; i <= n; i++) {

            double derivada = x + y;

            double yNuevo =
                    y + h * derivada;

            procedimiento.append(
                    "Iteracion " + i + "\n"
            );

            procedimiento.append(
                    "x = " + x + "\n"
            );

            procedimiento.append(
                    "y = " + y + "\n"
            );

            procedimiento.append(
                    "f(x,y)= x+y = "
                            + derivada + "\n"
            );

            procedimiento.append(
                    "y(n+1)= y + h*f(x,y)\n"
            );

            procedimiento.append(
                    "y(n+1)= "
                            + y
                            + " + "
                            + h
                            + "("
                            + derivada
                            + ")\n"
            );

            procedimiento.append(
                    "Resultado = "
                            + yNuevo
                            + "\n\n"
            );

            y = yNuevo;
            x += h;
        }

        procedimiento.append(
                "RESULTADO FINAL: "
                        + y
        );

        return procedimiento.toString();
    }

    public static double resultadoFinal(
            double x0,
            double y0,
            double h,
            int n
    ) {

        double x = x0;
        double y = y0;

        for (int i = 1; i <= n; i++) {

            y = y + h * (x + y);
            x += h;
        }

        return y;
    }
}

5. Método Runge-Kutta 4
Archivo: MetodoRK4.java
package logica;

public class MetodoRK4 {

    public static String resolver(
            double x0,
            double y0,
            double h,
            int n
    ) {

        StringBuilder sb =
                new StringBuilder();

        sb.append(
                "METODO RUNGE KUTTA 4\n\n"
        );

        double x = x0;
        double y = y0;

        for (int i = 1; i <= n; i++) {

            double k1 =
                    h * (x + y);

            double k2 =
                    h * (
                            (x + h/2)
                            + (y + k1/2)
                    );

            double k3 =
                    h * (
                            (x + h/2)
                            + (y + k2/2)
                    );

            double k4 =
                    h * (
                            (x + h)
                            + (y + k3)
                    );

            double yNuevo =
                    y + (
                            (k1 + 2*k2 + 2*k3 + k4)
                                    / 6
                    );

            sb.append(
                    "Iteracion "
                            + i + "\n"
            );

            sb.append(
                    "k1 = " + k1 + "\n"
            );

            sb.append(
                    "k2 = " + k2 + "\n"
            );

            sb.append(
                    "k3 = " + k3 + "\n"
            );

            sb.append(
                    "k4 = " + k4 + "\n"
            );

            sb.append(
                    "Resultado = "
                            + yNuevo
                            + "\n\n"
            );

            y = yNuevo;
            x += h;
        }

        sb.append(
                "RESULTADO FINAL: "
                        + y
        );

        return sb.toString();
    }

    public static double resultadoFinal(
            double x0,
            double y0,
            double h,
            int n
    ) {

        double x = x0;
        double y = y0;

        for (int i = 1; i <= n; i++) {

            double k1 =
                    h * (x + y);

            double k2 =
                    h * (
                            (x + h/2)
                                    + (y + k1/2)
                    );

            double k3 =
                    h * (
                            (x + h/2)
                                    + (y + k2/2)
                    );

            double k4 =
                    h * (
                            (x + h)
                                    + (y + k3)
                    );

            y = y + (
                    (k1 + 2*k2 + 2*k3 + k4)
                            / 6
            );

            x += h;
        }

        return y;
    }
}

6. DAO Registro Oracle
Archivo: ResultadoDAO.java
package datos;

import modelo.Resultado;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ResultadoDAO {

    public void guardar(Resultado r) {

        String sql =
                "INSERT INTO RESULTADOS_EDO " +
                "(METODO, ECUACION, X0, Y0, H, ITERACIONES, RESULTADO_FINAL, PROCEDIMIENTO) " +
                "VALUES (?,?,?,?,?,?,?,?)";

        try (
                Connection con =
                        ConexionOracle.conectar();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, r.getMetodo());
            ps.setString(2, r.getEcuacion());
            ps.setDouble(3, r.getX0());
            ps.setDouble(4, r.getY0());
            ps.setDouble(5, r.getH());
            ps.setInt(6, r.getIteraciones());
            ps.setDouble(7, r.getResultadoFinal());
            ps.setString(8, r.getProcedimiento());

            ps.executeUpdate();

            System.out.println(
                    "Resultado guardado"
            );

        } catch (Exception e) {

            System.out.println(
                    "Error DAO: "
                            + e.getMessage()
            );
        }
    }
}

7. Interfaz Gráfica Swing
Archivo: VentanaPrincipal.java
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

8. Clase Main
Archivo: Main.java
import vista.VentanaPrincipal;

public class Main {

    public static void main(String[] args) {

        new VentanaPrincipal()
                .setVisible(true);
    }
}

9. Librería Oracle JDBC
Descargar

Oracle JDBC Drivers

Agregar Librería en IntelliJ
Project Structure
→ Libraries
→ Add JAR

Agregar:

ojdbc11.jar

10. Ejemplo de Ejecución
Datos de Entrada
x0 = 0
y0 = 1
h = 0.1
iteraciones = 5
Método de Euler
Iteracion 1

x = 0
y = 1

f(x,y) = 1

y1 = 1 + 0.1(1)

Resultado = 1.1
Método RK4
k1 = 0.1
k2 = 0.11
k3 = 0.1105
k4 = 0.12105
Resultado Final

Los resultados:

Se muestran en pantalla
Se almacenan automáticamente en Oracle 19c
Registran el procedimiento completo
Guardan fecha y método utilizado
:. . / .
