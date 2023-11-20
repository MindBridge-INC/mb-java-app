package DAO;
import ConexaoBanco.Conexao;
import Classes.CadastroMaquina;

import java.sql.*;
public class MaquinaDAO {
    public static boolean cadastrarMaquina(CadastroMaquina cadastroMaquina) throws SQLException {
        String sql = "INSERT INTO Maquinas (hostname, SistemaOperacional, Processador, RAM, armazenamento, statSist, fkInstituicao) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        String selectNome = String.format("select hostname from Maquinas WHERE hostname = '%s'", cadastroMaquina.getNomeComputador());
        String selectInstituicao = String.format("select id from InstituicaoEnsino WHERE nome = 'Siglan'");
        Connection conn = null;
        Statement stmt = null;
        Statement stmt2 = null;
        conn = Conexao.getConexao();
        stmt = conn.createStatement();
        stmt2 = conn.createStatement();
        try {
            ResultSet rs2 = stmt.executeQuery(selectNome);
            ResultSet rs3 = stmt2.executeQuery(selectInstituicao);
            if (rs2.next()){
                System.out.println("Essa máquina já está no sistema !");
            } else{
                while (rs3.next()) {
                    Integer fkInstituicao = rs3.getInt(1);
                    ps = Conexao.getConexao().prepareStatement(sql);
                    ps.setString(1, cadastroMaquina.getNomeComputador());
                    ps.setString(2, cadastroMaquina.getSistemaOperacional());
                    ps.setString(3, cadastroMaquina.getNomeProcessador());
                    ps.setDouble(4, cadastroMaquina.getQuantidadeRAM() / (Math.pow(1024, 3)));
                    ps.setDouble(5, cadastroMaquina.getArmazenamentoHD() / (Math.pow(1024, 3)));
                    ps.setInt(6, 1);
                    ps.setInt(7, fkInstituicao);
                    ps.execute();
                }
            }
        } catch (
                SQLException ex) {
            System.out.println("Ocorreu um erro ao acessar o banco: " + ex.getMessage());
        }
        return true;
    }
}
