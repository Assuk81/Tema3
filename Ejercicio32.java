/**
 * @author David Abell�n Navarro
 * @author Juan Carlos Corredor S�nchez
 * @course 2� D.A.M.
 * @date 13/10/2021
 * @github 
 * 
 */
package ejerciciosTema3;

//8
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ejercicio32 {

 public static void muestraErrorSQL(SQLException e) {
    System.err.println("SQL ERROR mensaje: " + e.getMessage());
    System.err.println("SQL Estado: " + e.getSQLState());
    System.err.println("SQL código específico: " + e.getErrorCode());
  }
  
  public static void main(String[] args) {

    String basedatos = "bd_ejercicios_tema3";
    String host = "localhost";
    String port = "3306";
    String parAdic = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
    String user = "JuanCarlos";
    String pwd = "1234";

    try (
            Connection c = DriverManager.getConnection(urlConnection, user, pwd);
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM CLIENTES")) {
    	/**
    	 * Respuesta: Si, se puede utilizar el getInt porque se convierte lo que viene a lo indicado en el get, 
    	 * pero en caso que venga  un dato no esperado como una letra rompera el programa, 
    	 * siendo lo mas aconsejable poner el mismo tipo de get a lo indicado en la declaracion de la columna.
    	 */
      int i=1;
      while (rs.next()) {
        System.out.println("[" + (i++) + "]");        
        System.out.println("DNI: " + rs.getString("DNI"));
        System.out.println("Apellidos: " + rs.getString("APELLIDOS"));
        System.out.println("CP: " + rs.getInt("CP"));
      }

    } catch (SQLException e) {
      muestraErrorSQL(e);
    } catch (Exception e) {
      e.printStackTrace(System.err);
    }
  }

}
