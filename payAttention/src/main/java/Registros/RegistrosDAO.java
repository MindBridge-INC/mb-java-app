package Registros;

import Conexao.Conexao;
import Maquina.CadastroMaquina;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.Rede;
import com.github.britooo.looca.api.util.Conversor;

import java.sql.*;
import java.time.LocalDateTime;

public class RegistrosDAO {
    public static boolean inserirRegistros(RegistrosPC registros) throws SQLException {
        Looca looca = new Looca();
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        Rede rede = looca.getRede();
        String nomeComputador = rede.getParametros().getHostName();
        String sql = "insert into RegistroMaquina (usoRam, usoProcessador, dtHora, fkMaquinas) VALUES (?,?,?,?)";
        PreparedStatement ps = null;
        Connection conn = null;
        Statement stmt2 = null;
        conn = Conexao.getConexao();
        String selectIdMaquina = String.format("select id from Maquinas WHERE hostname = '%s'", nomeComputador);
        stmt2 = conn.createStatement();
        try {
            ResultSet rs1 = stmt2.executeQuery(selectIdMaquina);
            while(rs1.next()) {
                Integer idMaquinas = rs1.getInt(1);
                ps = Conexao.getConexao().prepareStatement(sql);
                ps.setDouble(1, registros.getMemoriaUso() / (Math.pow(1024, 3)));
                ps.setDouble(2, registros.getUsoProcessador());
                ps.setString(3, String.valueOf(dataHoraAtual));
                ps.setInt(4, idMaquinas);
                ps.execute();
            }
        } catch (
                SQLException ex) {
            System.out.println("Ocorreu um erro ao acessar o banco: " + ex.getMessage());
        }
        return false;
    }
}

