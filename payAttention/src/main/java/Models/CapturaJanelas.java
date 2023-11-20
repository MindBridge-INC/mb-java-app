package Models;
import DAO.JanelasDAO;
import Classes.Janelas;
import com.github.britooo.looca.api.core.Looca;

import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

public class CapturaJanelas {
    public static final long TEMPO = (5000);
    static Timer timer = null;
    public void capturaJanelas() throws SQLException {
        Looca looca = new Looca();
        Janelas janelas = new Janelas();


        if (timer == null) {
            timer = new Timer();
            TimerTask tarefa = new TimerTask() {
                @Override
                public void run() {
                    try {
                        Integer PID = Math.toIntExact(looca.getGrupoDeJanelas().getJanelasVisiveis().get(5).getPid());
                        Integer idJanela = Math.toIntExact(looca.getGrupoDeJanelas().getJanelasVisiveis().get(5).getJanelaId());
                        String tituloJanela = looca.getGrupoDeJanelas().getJanelasVisiveis().get(5).getTitulo();
                        String comandoJanela = looca.getGrupoDeJanelas().getJanelasVisiveis().get(5).getComando();
                        String localizacaoJanela = String.valueOf(looca.getGrupoDeJanelas().getJanelasVisiveis().get(5).getLocalizacaoETamanho());
                        janelas.setPID(PID);
                        janelas.setIdJanela(idJanela);
                        janelas.setTitulo(tituloJanela);
                        janelas.setComando(comandoJanela);
                        janelas.setLocalizacao(localizacaoJanela);
                        JanelasDAO.cadastrarJanelas(janelas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
        }

    }
}
