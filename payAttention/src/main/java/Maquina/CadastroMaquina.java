package Maquina;

public class CadastroMaquina {
    private String nomeComputador;
    private String sistemaOperacional;
    private String nomeProcessador;
    private Long quantidadeRAM;
    private Long armazenamentoHD;

    public String getNomeComputador() {
        return nomeComputador;
    }

    public void setNomeComputador(String nomeComputador) {
        this.nomeComputador = nomeComputador;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public String getNomeProcessador() {
        return nomeProcessador;
    }

    public void setNomeProcessador(String nomeProcessador) {
        this.nomeProcessador = nomeProcessador;
    }

    public Long getQuantidadeRAM() {
        return quantidadeRAM;
    }

    public void setQuantidadeRAM(Long quantidadeRAM) {
        this.quantidadeRAM = quantidadeRAM;
    }

    public Long getArmazenamentoHD() {
        return armazenamentoHD;
    }

    public void setArmazenamentoHD(Long armazenamentoHD) {this.armazenamentoHD = armazenamentoHD;}
}
