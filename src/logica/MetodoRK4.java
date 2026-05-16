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
                    h * (x+y);

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
                    "ITERACION "
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
                            (x + h / 2)
                                    + (y + k1 / 2)
                    );

            double k3 =
                    h * (
                            (x + h / 2)
                                    + (y + k2 / 2)
                    );

            double k4 =
                    h * (
                            (x + h)
                                    + (y + k3)
                    );

            y = y + (
                    (k1 + 2 * k2 + 2 * k3 + k4)
                            / 6
            );

            x += h;
        }

        return y;
    }
}   
