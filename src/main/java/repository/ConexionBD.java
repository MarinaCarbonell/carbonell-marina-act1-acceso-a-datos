package repository;

//prepara la consulta
import java.sql.PreparedStatement;
//guarda la consulta
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class ConexionBD {

    //Dependencias en plano
    // Variables para acceder a la BD:
    // url donde esta guardada la BD, root o usuario, contraseña o un cifrado
    private static final String URL= "jdbc:mysql://localhost:3306/almacen_ue";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    //Patron singleton
    // Creas una unica instancia para acceder a la base de datos
    //Desde otras clases, al ser privado, no se puede instanciar
    //Usamos el método de conectar
    //Datos
    //Instancia unica
    private static Connection conexion;
    //Constructor privado
    private ConexionBD(){}

    //Método para conectar la base de datos
    public static Connection conectar(){
        try {
            if(conexion == null || conexion.isClosed()){
                //DriverManager.getConnection método que conecta la BD
                conexion = DriverManager.getConnection(URL,USER,PASSWORD);
                System.out.println("Conexion Exitosa");
            }
        }catch (SQLException e){
            System.out.println("Error al conectar" + e.getMessage());
        }
        return conexion;
    }

    //Metodo para cerrar la conexion a la BD
    public static void desconectar(){
        try {
            if(conexion != null && !conexion.isClosed()){
                conexion.close();
                System.out.println("Conexion cerrada");
            }
        }catch (SQLException e){
            System.out.println("Error al desconectar" + e.getMessage());
        }
    }


}
