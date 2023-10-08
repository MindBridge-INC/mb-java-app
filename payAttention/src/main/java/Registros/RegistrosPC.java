package Registros;

public class RegistrosPC {
    private int id;
    private String sistemaOperacional;
    private Double usoProcessador;
    private Double temperaturaProcessador;
    private Long memoriaUso;
    private Long discoUso;
    private int dispositivosUSB;
    private Long downloadRede;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getSistemaOperacional() {
        return sistemaOperacional;
    }
    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }
    public Double getUsoProcessador() {
        return usoProcessador;
    }
    public void setUsoProcessador (Double usoProcessador){
        this.usoProcessador = usoProcessador;
    }
    public Double getTemperaturaProcessador() {
        return temperaturaProcessador;
    }
    public void setTemperaturaProcessador(Double temperaturaProcessador){
        this.temperaturaProcessador = temperaturaProcessador;
    }
    public Long getMemoriaUso() {
        return memoriaUso;
    }
    public void setMemoriaUso(Long memoriaUso) {
        this.memoriaUso = memoriaUso;
    }
    public Long getDiscoUso() {
        return discoUso;
    }
    public void setDiscoUso(Long discoUso) {
        this.discoUso = discoUso;
    }
    public int getDispositivosUSB() {
        return dispositivosUSB;
    }
    public void setDispositivosUSB(int dispositivosUSB) {
        this.dispositivosUSB = dispositivosUSB;
    }
    public long getDownloadRede() {
        return downloadRede;
    }
    public void setDownloadRede(Long downloadRede) {
        this.downloadRede = downloadRede;
    }
}
