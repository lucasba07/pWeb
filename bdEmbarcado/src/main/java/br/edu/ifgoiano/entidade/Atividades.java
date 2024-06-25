package br.edu.ifgoiano.entidade;

import java.sql.Timestamp;

public class Atividades {
    private Integer id;
    private Integer usuario_id;
    private String acao;
    private Timestamp data_hora;
    private String data_hora_formatada; 
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getUsuario_id() {
        return usuario_id;
    }
    public void setUsuario_id(Integer usuario_id) {
        this.usuario_id = usuario_id;
    }
    public String getAcao() {
        return acao;
    }
    public void setAcao(String acao) {
        this.acao = acao;
    }
    public Timestamp getData_hora() {
        return data_hora;
    }
    public void setData_hora(Timestamp data_hora) {
        this.data_hora = data_hora;
    }
    public String getData_hora_formatada() {
        return data_hora_formatada;
    }
    public void setData_hora_formatada(String data_hora_formatada) {
        this.data_hora_formatada = data_hora_formatada;
    }
}
