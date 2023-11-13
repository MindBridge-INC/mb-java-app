package Registros;

import Conexao.Conexao;
import Maquina.CadastroMaquina;
import com.github.britooo.looca.api.util.Conversor;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrosDAO {
    public static boolean inserirRegistros(RegistrosPC registros, CadastroMaquina cadastroMaquina) {
        String sql = "INSERT INTO RegistroMaquina (usoRam, usoProcessador, usoDisco, dispositivosUSB, fkMaquinas) VALUES (?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setDouble(1, registros.getMemoriaUso()/(Math.pow(1024,3)));
            ps.setDouble(2, registros.getUsoProcessador());
            ps.setDouble(3, registros.getDiscoUso()/(Math.pow(1024,3)));
            ps.setInt(4, registros.getDispositivosUSB());
            ps.setInt(5, cadastroMaquina.getIdMaquina());
            ps.execute();
        } catch (
                SQLException ex) {
            System.out.println("Ocorreu um erro ao acessar o banco: " + ex.getMessage());
        }
        return false;
    }
}

