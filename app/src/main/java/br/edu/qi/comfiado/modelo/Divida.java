package br.edu.qi.comfiado.modelo;

import java.util.Calendar;

public class Divida {
    private int uid;

    private int uidCredor;
    private int uidDevedor;

    private float valor;

    private String descricao;

    private Calendar dataVencimento;
    private Calendar dataAbertura;
    private Calendar dataPagamento;

    public Divida() {};

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUidCredor() {
        return uidCredor;
    }

    public void setUidCredor(int uidCredor) {
        this.uidCredor = uidCredor;
    }

    public int getUidDevedor() {
        return uidDevedor;
    }

    public void setUidDevedor(int uidDevedor) {
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

    public Calendar getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Calendar dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Calendar getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Calendar dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Calendar getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Calendar dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
