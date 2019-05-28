/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author RAFAEL ROCHA
 */
public class Boleto {
    private int codigoBoleto;
    private double valor;
    private int codigoPagador;
    private int dataVencimento;
    private char codigoBeneficiario;
    
    public void gerarBoleto(){

    }
    
    public void pagarBoleto(int codigoBoleto){
    
    }

    public Boleto() {
    }

    public Boleto(int codigoBoleto, double valor, int codigoPagador, int dataVencimento, char codigoBeneficiario) {
        this.codigoBoleto = codigoBoleto;
        this.valor = valor;
        this.codigoPagador = codigoPagador;
        this.dataVencimento = dataVencimento;
        this.codigoBeneficiario = codigoBeneficiario;
    }

    public int getCodigoBoleto() {
        return codigoBoleto;
    }

    public void setCodigoBoleto(int codigoBoleto) {
        this.codigoBoleto = codigoBoleto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getCodigoPagador() {
        return codigoPagador;
    }

    public void setCodigoPagador(int codigoPagador) {
        this.codigoPagador = codigoPagador;
    }

    public int getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(int dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public char getCodigoBeneficiario() {
        return codigoBeneficiario;
    }

    public void setCodigoBeneficiario(char codigoBeneficiario) {
        this.codigoBeneficiario = codigoBeneficiario;
    }
    
    
    
}
