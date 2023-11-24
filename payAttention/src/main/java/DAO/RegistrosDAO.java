package DAO;

import Classes.CadastroMaquina;
import ConexaoBanco.Conexao;
import Classes.RegistrosPC;
import LogErro.Log;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.Rede;

import java.sql.*;
import java.time.LocalDateTime;

public class RegistrosDAO {
    public static boolean inserirRegistros(RegistrosPC registros) throws SQLException {
        Looca looca = new Looca();
        CadastroMaquina cadastroMaquina = new CadastroMaquina();
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        Rede rede = looca.getRede();
        String nomeComputador = rede.getParametros().getHostName();
        String sql = "insert into RegistroMaquina (usoRam, usoProcessador, dtRegistro, fkMaquinas) VALUES (?,?,?,?)";
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
                registros.setFkMaquinas(idMaquinas);
                cadastroMaquina.setIdMaquina(idMaquinas);
                ps = Conexao.getConexao().prepareStatement(sql);
                ps.setDouble(1, registros.getMemoriaUso() / (Math.pow(1024, 3)));
                ps.setDouble(2, registros.getUsoProcessador());
                ps.setObject(3, java.sql.Timestamp.valueOf(dataHoraAtual));
                ps.setInt(4, idMaquinas);
                ps.execute();
            }
        } catch (
                SQLException ex) {
            System.out.println("Ocorreu um erro ao acessar o banco: " + ex.getMessage());
            // Capturando informações relevantes para o log
            String mensagemErro = ex.getMessage();
            String estadoSQL = ex.getSQLState();
            Integer codigoErro = ex.getErrorCode();

            // Agora você pode incluir essas informações no log
            Log log = new Log();
            log.exibirLog("""
             Registro dos Registros
             Erro: %s
             Estado SQL: %s
             Código de Erro: %d
                """.formatted(mensagemErro, estadoSQL, codigoErro));
        }
        return false;
    }
}

