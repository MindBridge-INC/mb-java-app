package Classes;
public class RegistrosPC {
    private Double usoProcessador;
    private Long memoriaUso;
    private Long discoUso;
    private Integer fkMaquinas;
    public Double getUsoProcessador() {
        return usoProcessador;
    }
    public void setUsoProcessador (Double usoProcessador){
        this.usoProcessador = usoProcessador;
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
    public Integer getFkMaquinas() {return fkMaquinas;}
    public void setFkMaquinas(Integer fkMaquinas) {this.fkMaquinas = fkMaquinas;}
    @Override
    public String toString() {
        return "RegistrosPC{" +
                "usoProcessador=" + usoProcessador +
                ", memoriaUso=" + memoriaUso +
                ", discoUso=" + discoUso +
                ", fkMaquinas=" + fkMaquinas +
                '}';
    }
}