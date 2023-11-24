package DAO;
import Classes.CadastroMaquina;
import Classes.RegistrosPC;
import Classes.UsuarioLogin;
import ConexaoBanco.Conexao;
import Classes.LogAcesso;
import LogErro.Log;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.Rede;

import java.sql.*;
import java.time.LocalDateTime;

public class LogAcessoDAO {
    UsuarioLogin usuarioLogin;
    public boolean logAcesso(LogAcesso logAcesso) throws SQLException {
        usuarioLogin = new UsuarioLogin();
        Looca looca = new Looca();
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        Rede rede = looca.getRede();
        String nomeComputador = rede.getParametros().getHostName();
        String selectIdMaquina = String.format("select id from Maquinas WHERE hostname = '%s'", nomeComputador);
        String selectIdUsuario = String.format("select id from UsuarioAluno WHERE email = '%s'", usuarioLogin.getEmail());
        String insertAcesso = "insert into LogAcesso (dtInicializacao, armUsado, fkUsuario, fkMaquina) values (?,?,?,?)";
        PreparedStatement ps = null;
        Connection conn = null;
        Statement stmt = null;
        Statement stmt2 = null;
        conn = Conexao.getConexao();
        stmt = conn.createStatement();
        stmt2 = conn.createStatement();
        try{
            ResultSet rs = stmt.executeQuery(selectIdMaquina);
            ResultSet rs2 = stmt2.executeQuery(selectIdUsuario);
            if (rs2.next()) {
                Integer fkAluno = rs2.getInt(1);
                while (rs.next()) {
                    Integer idMaquinas = rs.getInt(1);
                    ps = Conexao.getConexao().prepareStatement(insertAcesso);
                    ps.setObject(1, java.sql.Timestamp.valueOf(dataHoraAtual));
                    ps.setDouble(2, logAcesso.getArmUsado());
                    ps.setInt(3, fkAluno);
                    ps.setInt(4, idMaquinas);
                    ps.execute();
                }
            }
        }catch (SQLException ex){
            System.out.println("Ocorreu um erro ao acessar o banco: " + ex.getMessage());
            // Capturando informações relevantes para o log
            String mensagemErro = ex.getMessage();
            String estadoSQL = ex.getSQLState();
            Integer codigoErro = ex.getErrorCode();

            // Agora você pode incluir essas informações no log
            Log log = new Log();
            log.exibirLog("""
             Registro dos Acessos
             Erro: %s
             Estado SQL: %s
             Código de Erro: %d
                """.formatted(mensagemErro, estadoSQL, codigoErro));
        }
        return true;
    }
}
