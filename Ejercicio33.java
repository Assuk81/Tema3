/**
 * @author David Abellán Navarro
 * @author Juan Carlos Corredor Sánchez
 * @course 2º D.A.M.
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

public class Ejercicio33 {

 public static void muestraErrorSQL(SQLException e) {
    System.err.println("SQL ERROR mensaje: " + e.getMessage());
    System.err.println("SQL Estado: " + e.getSQLState());
    System.err.println("SQL codigo especifico: " + e.getErrorCode());
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
            Statement s = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    		//Statement st = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = s.executeQuery("SELECT * FROM CLIENTES")) {

      int i=0;
      while(rs.next()) {
    	  i++;
      }
      rs.afterLast();
      while (rs.previous()) {
        System.out.println("[" + (i--) + "]");        
        System.out.println("DNI: " + rs.getString("DNI"));
        System.out.println("Apellidos: " + rs.getString("APELLIDOS"));
        System.out.println("CP: " + rs.getString("CP"));
      }

    } catch (SQLException e) {
      muestraErrorSQL(e);
    } catch (Exception e) {
      e.printStackTrace(System.err);
    }
  }

}
