package Maquina;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.rede.Rede;
import loginModel.Login;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.stream.Stream;

public class CapturaMaquina extends javax.swing.JFrame{
    public void capturaMaquina() throws SQLException {
        JOptionPane.showMessageDialog(rootPane, "Inserindo informações da máquina!");
        CadastroMaquina cadastroMaquina = new CadastroMaquina();
        /*--------------------------------------------------------------*/
        /*Inicio Looca para captura de dados registro de maquinas*/
        Looca looca = new Looca();
        Rede rede = looca.getRede();
        String nomeComputador = rede.getParametros().getHostName();
        String sistema = looca.getSistema().getSistemaOperacional();
        String processador = looca.getProcessador().getNome();
        Long qtdRam = looca.getMemoria().getTotal();
        Long qtdDisco = looca.getGrupoDeDiscos().getTamanhoTotal();

        cadastroMaquina.setNomeComputador(nomeComputador);
        cadastroMaquina.setSistemaOperacional(sistema);
        cadastroMaquina.setNomeProcessador(processador);
        cadastroMaquina.setQuantidadeRAM(qtdRam);
        cadastroMaquina.setArmazenamentoHD(qtdDisco);
        MaquinaDAO.cadastrarMaquina(cadastroMaquina);
    }
}

