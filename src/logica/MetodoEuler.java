package logica;
// metodo Euler.
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
                    "y = " +  y + "\n"
            );

            procedimiento.append(
            "f(x,y)= x+y = "
                            + derivada + "\n"
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
