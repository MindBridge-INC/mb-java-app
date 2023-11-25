package ConexaoBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String urlSQLServer = "jdbc:sqlserver://ec2-52-55-115-65.compute-1.amazonaws.com:1433;database=Mindbridge;encrypt=true;trustServerCertificate=true;";
    private static final String userSQLServer = "sa";
    private static final String passwordSQLServer = "a";
    private static final String urlDocker = "jdbc:mysql://ec2-54-152-129-192.compute-1.amazonaws.com:3306;database=Mindbridge;encrypt=true;trustServerCertificate=true;";
    private static final String userDocker = "root";
    private static final String passwordDocker = "admin";
    private static Connection conn;

    public static Connection getConexaoMSSQL(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            if(conn == null){
                conn = DriverManager.getConnection(urlSQLServer, userSQLServer, passwordSQLServer);
                return conn;
            }else {
                return conn;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConexaoDocker(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            if(conn == null){
                conn = DriverManager.getConnection(urlDocker, userDocker, passwordDocker);
                return conn;
            }else {
                return conn;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}