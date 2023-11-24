package ConexaoBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String url = "jdbc:mysql://localhost:3306/Mindbridge";
    private static final String user = "admin";
    private static final String password = "admin";
    private static final String urlSQlServer = "jdbc:sqlserver://ec2-52-55-115-65.compute-1.amazonaws.com:1433;database=Mindbridge;encrypt=false;trustServerCertificate=true";
    private static final String userSQLServer = "sa";
    private static final String senhaSQLServer = "a";

    private static Connection conn;

    public static Connection getConexao(){
        try {
            if(conn == null){
                conn = DriverManager.getConnection(urlSQlServer, userSQLServer, senhaSQLServer);
                return conn;
            }else {
                return conn;
            }
        } catch (SQLException e) {


            throw new RuntimeException(e);
        }
    }
}