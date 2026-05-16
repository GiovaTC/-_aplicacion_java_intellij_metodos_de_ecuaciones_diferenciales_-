package datos;

import modelo.Resultado;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ResultadoDAO {

    public void guardar(Resultado r) {

        String sql =
                "INSERT INTO RESULTADOS_EDO " +
                "METODO, ECUACION, X0, Y0, H , ITERACIONES, RESULTADO_FINAL, PROCEDIMIENTO)" +
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

            System.out.println("error DAO: " + e.getMessage()
            );
        }
    }
}   
