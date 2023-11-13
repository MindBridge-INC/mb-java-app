package Registros;

import Conexao.Conexao;
import Maquina.CadastroMaquina;
import com.github.britooo.looca.api.util.Conversor;

import java.sql.*;

public class RegistrosDAO {
    public static boolean inserirRegistros(RegistrosPC registros) throws SQLException {
        CadastroMaquina cadastroMaquina = new CadastroMaquina();
        String sql = "INSERT INTO RegistroMaquina (usoRam, usoProcessador, usoDisco, dispositivosUSB, fkMaquinas) VALUES (?,?,?,?,?)";
        PreparedStatement ps = null;
        Connection conn = null;
        Statement stmt2 = null;
        conn = Conexao.getConexao();
        String selectIdMaquina = String.format("select id from Maquinas WHERE hostname = '%s'", cadastroMaquina.getNomeComputador());
        stmt2 = conn.createStatement();
        try {
            ResultSet rs1 = stmt2.executeQuery(selectIdMaquina);
            if (rs1.next()) {
                Integer idMaquinas = rs1.getInt(1);
                registros.setFkMaquinas(idMaquinas);
                System.out.println("idMaquina: " + registros.getFkMaquinas());
                System.out.println(cadastroMaquina);

                ps = Conexao.getConexao().prepareStatement(sql);
                ps.setDouble(1, registros.getMemoriaUso() / (Math.pow(1024, 3)));
                ps.setDouble(2, registros.getUsoProcessador());
                ps.setDouble(3, registros.getDiscoUso() / (Math.pow(1024, 3)));
                ps.setInt(4, registros.getDispositivosUSB());
                ps.setInt(5, idMaquinas);
                ps.execute();
            }
        } catch (
                SQLException ex) {
            System.out.println("Ocorreu um erro ao acessar o banco: " + ex.getMessage());
        }
        return false;
    }
}

