package BD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author ING JARC
 */
public class ConexionBD {

public  String puerto="3306";
public  String nomservidor="localhost";
public  String db="servicioing";
public  String user="alexander92";
public  String pass="shark123";
//Connection conn=null;

private Connection conn;

    public Connection getConexion() {
        return conn;
    }

    public void setConexion(Connection conexion) {
        this.conn = conexion;
    }

public Connection conectar(){
    try{
    //String ruta="jdbc:mysql://";
    //String servidor=nomservidor+":"+puerto+"/";
//    String BaseDeDatos = "jdbc:mysql://23.229.190.232/ServicioIng?connectTimeout=3000";
    

    //String BaseDeDatos = "jdbc:mysql://23.229.190.232/ServicioIng";
    String BaseDeDatos = "jdbc:mysql://localhost/servicioing?user=root&password=";
    Class.forName("com.mysql.jdbc.Driver");
    conn = DriverManager.getConnection(BaseDeDatos);
//    setConexion(DriverManager.getConnection(BaseDeDatos));

    if (conn!=null){
    System.out.println("Conexión a base de datos listo...");
    }
    else if (conn==null)
    {
    throw new SQLException();
    }
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null, e.getMessage());
    } catch (ClassNotFoundException e) {
        JOptionPane.showMessageDialog(null, "Se produjo el siguiente error: "+e.getMessage());
    }catch (NullPointerException e){
        JOptionPane.showMessageDialog(null, "Se produjo el siguiente error: "+e.getMessage());
    }finally{
    return conn;
    }
}
public void desconectar(){
    conn = null;
    System.out.println("Desconexion a base de datos listo...");
}
}
