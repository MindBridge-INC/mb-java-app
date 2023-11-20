package Registros;

import Conexao.Conexao;

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
        }
        return true;
    }
}
