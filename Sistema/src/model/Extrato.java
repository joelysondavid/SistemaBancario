/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Joelyson David
 */
public class Extrato {

    // variáveis de instancia
    private int idTransacao;
    private long idConta;
    private double valor;
    private String tipo;
    private String dataTransacao;

    // construtor
    public Extrato() {
    }
    
    // cosntrutor com parametros
    public Extrato(int idTransacao, double valor, String tipo, String dataTransacao) {
        this.idTransacao = idTransacao;
        this.valor = valor;
        this.tipo = tipo;
        this.dataTransacao = dataTransacao;
    }

    // gettters e setters
    public int getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(int idTransacao) {
        this.idTransacao = idTransacao;
    }

    public long getIdConta() {
        return idConta;
    }

    public void setIdConta(long idConta) {
        this.idConta = idConta;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(String dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

}
