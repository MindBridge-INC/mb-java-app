package Registros;

import Conexao.Conexao;
import com.github.britooo.looca.api.util.Conversor;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrosDAO {
    public static boolean cadastrarComputador(RegistrosPC registros) {
        String sql = "INSERT INTO registroMaquina (TemperaturaProcessador, usoRam, usoProcessador, usoDisco) VALUES (?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setDouble(1, registros.getTemperaturaProcessador());
            ps.setString(2, Conversor.formatarBytes(registros.getMemoriaUso()));
            ps.setDouble(3, registros.getUsoProcessador());
            ps.setString(4, Conversor.formatarBytes(registros.getDiscoUso()));
            ps.execute();
        } catch (
                SQLException ex) {
            System.out.println("Ocorreu um erro ao acessar o banco: " + ex.getMessage());
        }
        return false;
    }
}

