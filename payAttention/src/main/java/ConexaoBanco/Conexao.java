package ConexaoBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import Log.Log;

public class Conexao {
    private static final String url = "jdbc:mysql://localhost:3306/Mindbridge";
    private static final String user = "admin";
    private static final String password = "admin";
    private static Connection conn;

    public static Connection getConexao(){
        try {
            if(conn == null){
                conn = DriverManager.getConnection(url, user, password);
                return conn;
            }else {
                return conn;
            }
        } catch (SQLException e) {
            // Capturando informações relevantes para o log
            String mensagemErro = e.getMessage();
            String estadoSQL = e.getSQLState();
            Integer codigoErro = e.getErrorCode();

            // Agora você pode incluir essas informações no log
            Log log = new Log();
            log.exibirLog("""
             Conexão com Banco de Dados
             URL: %s
             Erro: %s
             Estado SQL: %s
             Código de Erro: %d
                """.formatted(url, mensagemErro, estadoSQL, codigoErro));

            throw new RuntimeException(e);
        }
    }
}