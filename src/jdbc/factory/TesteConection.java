package jdbc.factory;
import java.sql.Connection;
import java.sql.SQLException;

public class TesteConection {

    public static void main(String[] args) throws SQLException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.retrieveConnection();

        System.out.println("Probando conexion!!");

        connection.close();

        System.out.println("Conexión cerrada!!");
    }

}