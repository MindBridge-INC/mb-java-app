package ConexaoBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String url = "jdbc:mysql://localhost:3306/Mindbridge";
    private static final String user = "admin";
    private static final String password = "admin";
    private static final String urlSQLServer = "jdbc:sqlserver://ec2-52-55-115-65.compute-1.amazonaws.com:1433;database=Mindbridge;encrypt=true;trustServerCertificate=true;";
    private static final String userSQLServer = "sa";
    private static final String passwordSQLServer = "a";
    private static Connection conn;

    public static Connection getConexao(){
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
}