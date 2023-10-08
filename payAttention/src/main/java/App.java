import Registros.RegistrosDAO;
import Registros.RegistrosPC;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.util.Conversor;
import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.components.Cpu;
import com.profesorfalken.jsensors.model.sensors.Temperature;


import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class App {
    public static final long TEMPO = (5000);
    static Timer timer = null;

    public static void main(String[] args) {
        RegistrosPC registros = new RegistrosPC();
        /*Pegar temperatura do processadore com JSensors*/
        Components components = JSensors.get.components();
        Cpu cpu = components.cpus.get(0);
        List<Temperature> temps = cpu.sensors.temperatures;
        for (final Temperature temp : temps) {
            System.out.println(temp.name + ": " + temp.value + " C");
        }
        if (registros.getTemperaturaProcessador() == null){
            registros.setTemperaturaProcessador(0.0);
        }
        System.out.println("Temperatura do processador: " + registros.getTemperaturaProcessador());
        /*--------------------------------------------------------------*/

        /*Inicio Looca para captura de dados*/
        Looca looca = new Looca();
        Memoria memoria = looca.getMemoria();
        DiscoGrupo disco = looca.getGrupoDeDiscos();
        Processador processador = looca.getProcessador();
        Long memoriaUso = memoria.getEmUso();
        Long usoDisco = disco.getTamanhoTotal();
        Long redeDownload = looca.getRede().getGrupoDeInterfaces().getInterfaces().get(3).getBytesRecebidos()/5;
        int usbConectados = looca.getDispositivosUsbGrupo().getDispositivosUsbConectados().size()-1;


        registros.setTemperaturaProcessador(registros.getTemperaturaProcessador());
        registros.setMemoriaUso(memoriaUso);
        registros.setUsoProcessador(processador.getUso());
        registros.setDiscoUso(usoDisco);
        registros.setDownloadRede(redeDownload);
        registros.setDispositivosUSB(usbConectados);

        if (timer == null) {
            timer = new Timer();
            TimerTask tarefa = new TimerTask() {
                @Override
                public void run() {
                    try {
                        RegistrosDAO.cadastrarComputador(registros);
                        System.out.println("\n*----------------------------------------------------------*");
                        System.out.println("Temperatura processador: " + registros.getTemperaturaProcessador());
                        System.out.println("Uso processador: " + processador.getUso());
                        System.out.println("Memória RAM em uso: " + Conversor.formatarBytes(memoriaUso));
                        System.out.println("Quantidade de discos: " + Conversor.formatarBytes(usoDisco));
                        System.out.println("Velocidade de download: " + redeDownload);
                        System.out.println("Quantidade dispositivos USB conectados: "+ usbConectados);
                        System.out.println("Inserindo !");
                        System.out.println("*----------------------------------------------------------*");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
        }
    }
}
