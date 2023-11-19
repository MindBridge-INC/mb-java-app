package Janelas;
import Conexao.Conexao;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.Rede;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Arrays;

public class JanelasDAO {
    public static boolean cadastrarJanelas(Janelas janelas) throws SQLException {
        Looca looca = new Looca();
        Rede rede = looca.getRede();
        String nomeComputador = rede.getParametros().getHostName();
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        String sql = "insert into JanelasAbertas(dtRegistro, PID, idJanela, titulo, comando, localizacao, visivel, fkMaquina) values (?,?,?,?,?,?,?,?)";
        String selectIdMaquina = String.format("select id from Maquinas WHERE hostname = '%s'", nomeComputador);

        PreparedStatement ps = null;
        Connection conn = null;
        Statement stmt = null;
        Statement stmt2 = null;
        conn = Conexao.getConexao();
        stmt2 = conn.createStatement();
        try{
            ResultSet rs = stmt2.executeQuery(selectIdMaquina);
            while(rs.next()) {
                Integer fkMaquina = rs.getInt(1);
                ps = Conexao.getConexao().prepareStatement(sql);
                ps.setString(1, String.valueOf(dataHoraAtual));
                ps.setInt(2, janelas.getPID());
                ps.setInt(3, janelas.getIdJanela());
                ps.setString(4, janelas.getTitulo());
                ps.setString(5, janelas.getComando());
                ps.setString(6, janelas.getLocalizacao());
                ps.setString(7, "true");
                ps.setInt(8, fkMaquina);
                ps.execute();
            }
            }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
