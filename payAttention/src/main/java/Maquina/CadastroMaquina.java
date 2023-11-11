package Maquina;

public class CadastroMaquina {
    private String nomeMaquina;
    private String sistemaOperacional;
    private String nomeProcessador;
    private Integer quantidadeRAM;
    private Integer armazenamentoHD;

    public String getNomeMaquina() {
        return nomeMaquina;
    }

    public void setNomeMaquina(String nomeMaquina) {
        this.nomeMaquina = nomeMaquina;
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

    public Integer getQuantidadeRAM() {
        return quantidadeRAM;
    }

    public void setQuantidadeRAM(Integer quantidadeRAM) {
        this.quantidadeRAM = quantidadeRAM;
    }

    public Integer getArmazenamentoHD() {
        return armazenamentoHD;
    }

    public void setArmazenamentoHD(Integer armazenamentoHD) {
        this.armazenamentoHD = armazenamentoHD;
    }
}
