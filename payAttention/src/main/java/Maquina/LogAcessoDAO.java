package Maquina;
import Conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
public class LogAcessoDAO {
    public static boolean logAcesso(LogAcesso logAcesso) throws SQLException {
        String insertAcesso = "insert into LogAcesso (dtInicializacao, armUsado, fkUsuario, fkMaquina) values (?,?,?,?)";
        PreparedStatement ps = null;
        Connection conn = null;
        Statement stmt = null;
        conn = Conexao.getConexao();
        stmt = conn.createStatement();
        try{
            ps = Conexao.getConexao().prepareStatement(insertAcesso);
            ps.setString(1, logAcesso.getDtInicializacao());
            ps.setDouble(2, logAcesso.getArmUsado());
            ps.setInt(3,1);
            ps.setInt(4,1);
            ps.execute();
        }catch (SQLException ex){
            System.out.println("Ocorreu um erro ao acessar o banco: " + ex.getMessage());
        }
        return true;
    }
}
