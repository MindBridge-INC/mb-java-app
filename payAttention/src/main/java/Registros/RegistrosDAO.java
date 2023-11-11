package Registros;

import Conexao.Conexao;
import com.github.britooo.looca.api.util.Conversor;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrosDAO {
    public static boolean inserirRegistros(RegistrosPC registros) {
        String sql = "INSERT INTO registroMaquina (usoRam, usoProcessador, usoDisco, downloadRede, dispositivosUSB) VALUES (?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, Conversor.formatarBytes(registros.getMemoriaUso()));
            ps.setDouble(2, registros.getUsoProcessador());
            ps.setString(3, Conversor.formatarBytes(registros.getDiscoUso()));
            ps.setString(4, Conversor.formatarBytes(registros.getDownloadRede()));
            ps.setInt(5, registros.getDispositivosUSB());
            ps.execute();
        } catch (
                SQLException ex) {
            System.out.println("Ocorreu um erro ao acessar o banco: " + ex.getMessage());
        }
        return false;
    }
}

