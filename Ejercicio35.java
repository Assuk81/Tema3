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

// TODO: Auto-generated Javadoc
/**
 * The Class Ejercicio35.
 */
public class Ejercicio35 {

 /**
  * Muestra error SQL.
  *
  * @param e the e
  */
 public static void muestraErrorSQL(SQLException e) {
    System.err.println("SQL ERROR mensaje: " + e.getMessage());
    System.err.println("SQL Estado: " + e.getSQLState());
    System.err.println("SQL codigo especifico: " + e.getErrorCode());
  }
  
 /**
  * Sql.
  *
  * @param s the s
  * @param dni the dni
  * @return the string
  * @throws SQLException the SQL exception
  */
 public static String sql (Statement s, String dni) throws SQLException {
     String result = "";
     ResultSet rs = s.executeQuery("SELECT * FROM CLIENTES WHERE DNI = '" + dni + "';");
     int i = 0;
     while (rs.next()) {
         result = "DNI: " + rs.getString("DNI")+ System.lineSeparator()
             +"Apellidos: " + rs.getString("APELLIDOS")+ System.lineSeparator()
             + "CP: " + rs.getString("CP");
     }
     return result;
 }
  
  /**
   * The main method.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {

    String basedatos = "bd_ejercicios_tema3";
    String host = "localhost";
    String port = "3306";
    String parAdic = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
    String user = "JuanCarlos";
    String pwd = "1234";

    String dni = "89012345E";
    try (
            Connection c = DriverManager.getConnection(urlConnection, user, pwd);
            Statement s = c.createStatement()) {
    	
        String result = sql(s, dni);
        System.out.println(result);
    } catch (SQLException e) {
        muestraErrorSQL(e);
    } catch (Exception e) {
        e.printStackTrace(System.err);
    }
  }

}
