package Maquina;
import Conexao.Conexao;
import com.github.britooo.looca.api.util.Conversor;
import loginView.LoginView;

import java.sql.*;

public class MaquinaDAO {
    public static boolean cadastrarMaquina(CadastroMaquina cadastroMaquina) throws SQLException {
        String sql = "INSERT INTO Maquinas (NomeMaquina, SistemaOperacional, Processador, RAM, Disco) VALUES (?,?,?,?,?)";
        PreparedStatement ps = null;
        String selectNome = String.format("select NomeMaquina from Maquinas WHERE NomeMaquina = '%s'", cadastroMaquina.getNomeComputador());
        Connection conn = null;
        Statement stmt = null;
        conn = Conexao.getConexao();
        stmt = conn.createStatement();
        try {
            ResultSet rs = stmt.executeQuery(selectNome);
            if (rs.next()){
                System.out.println("Essa máquina já está no sistema !");
            }else{
                ps = Conexao.getConexao().prepareStatement(sql);
                ps.setString(1, cadastroMaquina.getNomeComputador());
                ps.setString(2, cadastroMaquina.getSistemaOperacional());
                ps.setString(3, cadastroMaquina.getNomeProcessador());
                ps.setString(4, Conversor.formatarBytes(cadastroMaquina.getQuantidadeRAM()));
                ps.setString(5, Conversor.formatarBytes(cadastroMaquina.getArmazenamentoHD()));
                ps.execute();
            }
        } catch (
                SQLException ex) {
            System.out.println("Ocorreu um erro ao acessar o banco: " + ex.getMessage());
        }
        return false;
    }
}
