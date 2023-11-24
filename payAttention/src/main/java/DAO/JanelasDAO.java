package DAO;
import ConexaoBanco.Conexao;
import Classes.Janelas;
import LogErro.Log;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.Rede;

import java.sql.*;
import java.time.LocalDateTime;

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
                ps.setObject(1, dataHoraAtual);
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
            System.out.println("Ocorreu um erro ao acessar o banco: " + e.getMessage());
            // Capturando informações relevantes para o log
            String mensagemErro = e.getMessage();
            String estadoSQL = e.getSQLState();
            Integer codigoErro = e.getErrorCode();

            // Agora você pode incluir essas informações no log
            Log log = new Log();
            log.exibirLog("""
             Registro das Janelas
             Erro: %s
             Estado SQL: %s
             Código de Erro: %d
                """.formatted(mensagemErro, estadoSQL, codigoErro));
            throw new RuntimeException(e);
        }
        return false;
    }
}
