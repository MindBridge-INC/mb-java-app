package DAO;

import Classes.CadastroMaquina;
import Classes.Limites;
import ConexaoBanco.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlertaRegMaquinaDAO {
    Limites limites;
    CadastroMaquina maquina;
    public boolean registrarAlertaMaquina() throws SQLException {

        limites = new Limites();
        maquina = new CadastroMaquina();

        Double ramMaquina = Double.valueOf(maquina.getQuantidadeRAM())/Math.pow(1024,3);
        Double ramLimite = limites.getRamPorcent();
        Double cpuLimite = limites.getCpuPorcent();
        Double porcentRamUsada;
        List<Double> ramAtual = new ArrayList<>();
        List<Double> cpuAtual = new ArrayList<>();
        List<Integer> idReg = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        Statement stmtRegistro = null;
        Statement stmtIdMaquina = null;

        conn = Conexao.getConexao();
        stmtRegistro = conn.createStatement();
        stmtIdMaquina = conn.createStatement();

        String insertAlerta = "insert into Alertas(componente, tipo, fkRegistro) values (?,?,?)";

        String selectIdMaquina = "SELECT id FROM Maquinas WHERE hostname = '%s'".formatted(maquina.getNomeComputador());

        try{
            ResultSet rsMaquina = stmtIdMaquina.executeQuery(selectIdMaquina);
            if (rsMaquina.next()) {
                Integer idMaquina = rsMaquina.getInt("id");
                String selectReg = "SELECT TOP 3 * FROM RegistroMaquina WHERE fkMaquinas = %d ORDER BY dtRegistro DESC;".formatted(idMaquina);
                ResultSet rsRegistro = stmtRegistro.executeQuery(selectReg);

                while (rsRegistro.next()) {
                    porcentRamUsada = (100*rsRegistro.getDouble("usoRam"))/ramMaquina;
                    ramAtual.add(porcentRamUsada);
                    cpuAtual.add((rsRegistro.getDouble("usoProcessador")));
                    idReg.add(rsRegistro.getInt("id"));
                }
            }

            if (ramAtual.get(2) >= (ramLimite - (ramLimite*0.1)) && ramAtual.get(1) >= (ramLimite - (ramLimite*0.1))) {
                if (ramAtual.get(0) >= (ramLimite - (ramLimite * 0.1))) {
                    if (ramAtual.get(0) >= (ramLimite - (ramLimite * 0.1)) && ramAtual.get(0) < ramLimite) {
                        /*inserir com atenção*/
                        try {
                            ps = Conexao.getConexao().prepareStatement(insertAlerta);
                            ps.setString(1, "RAM");
                            ps.setString(2, "Atenção");
                            ps.setInt(3, idReg.get(0));
                            ps.execute();
                        } catch (SQLException ex) {
                            System.out.println(ex);
                        }
                    } else {
                        /*inserir com critico*/
                        try {
                            ps = Conexao.getConexao().prepareStatement(insertAlerta);
                            ps.setString(1, "RAM");
                            ps.setString(2, "Crítico");
                            ps.setInt(3, idReg.get(0));
                            ps.execute();

                        } catch (SQLException ex) {
                            System.out.println(ex);
                        }
                    }
                }
            }

            if (cpuAtual.get(2) >= (cpuLimite - (cpuLimite*0.1)) && cpuAtual.get(1) >= (cpuLimite - (cpuLimite*0.1))) {
                if (cpuAtual.get(0) >= (cpuLimite - (cpuLimite*0.1))) {
                    if (cpuAtual.get(0) >= (cpuLimite - (cpuLimite*0.1)) && cpuAtual.get(0) < cpuLimite) {
                        /*inserir com atenção*/
                        try{
                            ps = Conexao.getConexao().prepareStatement(insertAlerta);
                            ps.setString(1,"CPU");
                            ps.setString(2,"Atenção");
                            ps.setInt(3,idReg.get(0));
                            ps.execute();
                        } catch ( SQLException ex ) {
                            System.out.println(ex);
                        }
                    } else {
                        /*inserir com critico*/
                        try {
                            ps = Conexao.getConexao().prepareStatement(insertAlerta);
                            ps.setString(1,"CPU");
                            ps.setString(2,"Crítico");
                            ps.setInt(3,idReg.get(0));
                            ps.execute();
                        } catch (SQLException ex) {
                            System.out.println(ex);
                        }
                    }
                }
            }
        }catch (SQLException ex){
            System.out.println("Ocorreu um erro ao acessar o banco: " + ex.getMessage());
        }
        return false;
    }
}
