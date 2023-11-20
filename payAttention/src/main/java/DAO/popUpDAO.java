package DAO;
import Classes.UsuarioLogin;
import ConexaoBanco.Conexao;

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
        }
        return false;
    }
}
