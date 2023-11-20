package Registros;

public class Alertas {
    public String dtRegistro;
    public String tipo;
    public Integer fkLogAcesso;

    public String getDtRegistro() {
        return dtRegistro;
    }

    public void setDtRegistro(String dtRegistro) {
        this.dtRegistro = dtRegistro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getFkLogAcesso() {
        return fkLogAcesso;
    }

    public void setFkLogAcesso(Integer fkLogAcesso) {
        this.fkLogAcesso = fkLogAcesso;
    }
}
