package DAO;

import Classes.CadastroMaquina;
import Classes.LogAcesso;
import Classes.Limites;
import ConexaoBanco.Conexao;
import java.sql.*;

public class AlertaLogAcessoDAO {
    Limites limites;
    CadastroMaquina maquina;
    LogAcesso logAcessoClass;
    public boolean registrarAlertaMaquina() throws SQLException {
        limites = new Limites();
        maquina = new CadastroMaquina();
        logAcessoClass = new LogAcesso();

        Double discoLimite = limites.getDiscoPorcent();
        Double discoTotal = maquina.getArmazenamentoHD()/Math.pow(1024,3);
        Double discoUsado = logAcessoClass.getArmUsado();

        Connection conn = null;
        PreparedStatement ps = null;
        Statement stmtLog = null;

        conn = Conexao.getConexao();
        stmtLog = conn.createStatement();

        String selectLog = "SELECT * FROM LogAcesso WHERE fkUsuario = %d AND fkMaquina = %d ORDER BY dtRegistro DESC LIMIT 1".formatted(logAcessoClass.getFkUsuario(), logAcessoClass.getFkMaquina());

        String insertAlertaLog = "INSERT INTO AlertasLog(componente,tipo,fkLogAcesso) VALUES (?,?,?)";

        try {
            ResultSet rsSelectLog = stmtLog.executeQuery(selectLog);
            if (rsSelectLog.next()) {
                Integer idRegistro = rsSelectLog.getInt("id");

                Double porcentUsado = (100*discoUsado)/discoTotal;

                if (porcentUsado >= (discoLimite - (discoLimite*0.1)) && porcentUsado <= discoLimite) {
                    // System.out.println("test ATENÇÃO");
                    ps = Conexao.getConexao().prepareStatement(insertAlertaLog);
                    ps.setString(1,"Armazenamento");
                    ps.setString(2,"Atenção");
                    ps.setDouble(3, idRegistro);
                    ps.execute();
                } else if (porcentUsado > discoLimite) {
                    // System.out.println("test CRITICO");
                    ps = Conexao.getConexao().prepareStatement(insertAlertaLog);
                    ps.setString(1,"Armazenamento");
                    ps.setString(2,"Crítico");
                    ps.setDouble(3, idRegistro);
                    ps.execute();
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return false;
    }
}
