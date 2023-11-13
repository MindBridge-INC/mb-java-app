package Maquina;
import Conexao.Conexao;
import java.sql.*;
public class MaquinaDAO {
    public static boolean cadastrarMaquina(CadastroMaquina cadastroMaquina) throws SQLException {
        String sql = "INSERT INTO Maquinas (hostname, SistemaOperacional, Processador, RAM, armazenamento, statSist) VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = null;
        String selectNome = String.format("select hostname from Maquinas WHERE hostname = '%s'", cadastroMaquina.getNomeComputador());
        Connection conn = null;
        Statement stmt = null;
        conn = Conexao.getConexao();
        stmt = conn.createStatement();
        try {
            ResultSet rs2 = stmt.executeQuery(selectNome);
            if (rs2.next()){
                System.out.println("Essa máquina já está no sistema !");
            } else{
                ps = Conexao.getConexao().prepareStatement(sql);
                ps.setString(1, cadastroMaquina.getNomeComputador());
                ps.setString(2, cadastroMaquina.getSistemaOperacional());
                ps.setString(3, cadastroMaquina.getNomeProcessador());
                ps.setDouble(4, cadastroMaquina.getQuantidadeRAM()/(Math.pow(1024,3)));
                ps.setDouble(5, cadastroMaquina.getArmazenamentoHD()/(Math.pow(1024,3)));
                ps.setInt(6,1);
                ps.execute();

            }
        } catch (
                SQLException ex) {
            System.out.println("Ocorreu um erro ao acessar o banco: " + ex.getMessage());
        }
        return false;
    }
}
