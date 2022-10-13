package br.edu.qi.comfiado.modelo;

import java.util.Calendar;

public class Divida {
    private String uid;

    private String codigo;

    private String uidCredor;
    private String uidDevedor;

    private float valor;

    private String descricao;

    private long dataVencimento;
    private long dataAbertura;
    private long dataPagamento;

    public Divida() {};


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getUidCredor() {
        return uidCredor;
    }

    public void setUidCredor(String uidCredor) {
        this.uidCredor = uidCredor;
    }

    public String getUidDevedor() {
        return uidDevedor;
    }

    public void setUidDevedor(String uidDevedor) {
        this.uidDevedor = uidDevedor;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(long dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public long getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(long dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public long getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(long dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    @Override
    public String toString() {
        return "Divida{" +
                "uid=" + uid +
                ", uidCredor=" + uidCredor +
                ", uidDevedor=" + uidDevedor +
                ", valor=" + valor +
                ", descricao='" + descricao + '\'' +
                ", dataVencimento=" + dataVencimento +
                ", dataAbertura=" + dataAbertura +
                ", dataPagamento=" + dataPagamento +
                '}';
    }
}
