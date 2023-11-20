package DAO;

import ConexaoBanco.Conexao;
import LogErro.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class AlertasDAO {
    public static boolean alertasLog() throws SQLException {
        String sql = "insert into AlertasLog(dtRegistro, tipo, fkLogAcesso) values (?,?,?)";
        PreparedStatement ps = null;
        Connection conn = null;
        Statement stmt = null;
        conn = Conexao.getConexao();
        stmt = conn.createStatement();
        try{
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.execute();
        }catch (SQLException ex){
            System.out.println("Ocorreu um erro ao acessar o banco: " + ex.getMessage());
            // Capturando informações relevantes para o log
            String mensagemErro = ex.getMessage();
            String estadoSQL = ex.getSQLState();
            Integer codigoErro = ex.getErrorCode();

            // Agora você pode incluir essas informações no log
            Log log = new Log();
            log.exibirLog("""
             Registro dos Alertas
             Erro: %s
             Estado SQL: %s
             Código de Erro: %d
                """.formatted(mensagemErro, estadoSQL, codigoErro));
        }
        return true;
    }
}
