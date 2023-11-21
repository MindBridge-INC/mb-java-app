package DAO;
import Classes.UsuarioLogin;
import ConexaoBanco.Conexao;
import LogErro.Log;

import java.sql.*;
import java.time.LocalDateTime;
public class popUpDAO{
    UsuarioLogin usuarioLogin;
    public boolean popUpTrue() throws SQLException {
        usuarioLogin = new UsuarioLogin();
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        PreparedStatement ps = null;
        Connection conn = null;
        Statement stmt = null;
        System.out.println(usuarioLogin.getEmail());
        String selectIdAluno = String.format("select id from UsuarioAluno where email = '%s'",usuarioLogin.getEmail());
        String insertPontos = "insert into Pontuacao (pontos, dtRegistro, fkAluno) VALUES(?,?,?)";
        conn = Conexao.getConexao();
        stmt = conn.createStatement();
        try {
            ResultSet rs = stmt.executeQuery(selectIdAluno);
            if (rs.next()){
                System.out.println("Inserindo pontuação.");
                Integer fkAluno = rs.getInt(1);
                ps = Conexao.getConexao().prepareStatement(insertPontos);
                ps.setInt(1,10);
                ps.setString(2, String.valueOf(dataHoraAtual));
                ps.setInt(3, fkAluno);
                ps.execute();
            }
        }catch (SQLException ex){
            System.out.println("Ocorreu um erro ao acessar o banco: " + ex.getMessage());
        }
        return true;
    }
    public boolean popUpFalse() throws SQLException {
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        PreparedStatement ps = null;
        Connection conn = null;
        Statement stmt = null;
        System.out.println(usuarioLogin.getEmail());
        String selectIdAluno = String.format("select id from UsuarioAluno where email = '%s'", usuarioLogin.getEmail());
        String insertPontos = "insert into Pontuacao (pontos, dtRegistro, fkAluno) VALUES(?,?,?)";

        conn = Conexao.getConexao();
        stmt = conn.createStatement();

        try {
            ResultSet rs = stmt.executeQuery(selectIdAluno);
            if (rs.next()){
                System.out.println("Inserindo pontuação.");
                Integer fkAluno = rs.getInt(1);
                ps = Conexao.getConexao().prepareStatement(insertPontos);
                ps.setInt(1,0);
                ps.setString(2, String.valueOf(dataHoraAtual));
                ps.setInt(3, fkAluno);
                ps.execute();
            }
        }catch (SQLException ex){
            System.out.println("Ocorreu um erro ao acessar o banco: " + ex.getMessage());
            // Capturando informações relevantes para o log
            String mensagemErro = ex.getMessage();
            String estadoSQL = ex.getSQLState();
            Integer codigoErro = ex.getErrorCode();

            // Agora você pode incluir essas informações no log
            Log log = new Log();
            log.exibirLog("""
             Registro dos PopUp
             Erro: %s
             Estado SQL: %s
             Código de Erro: %d
                """.formatted(mensagemErro, estadoSQL, codigoErro));
        }
        return false;
    }
}
