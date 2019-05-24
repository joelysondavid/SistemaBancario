/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Alisson Reis
 */
public class Cartao {
    
    private int numCartao;
    private int codCliente;
    private int numConta;
    private int numAgencia;
    private char nomeCliente;
    private int bandeira;
    private int validade;
    private int clienteDesde;
    private int cvv;
    private char senha;
    
    //Metodo gerar cartão vazio
    public void gerarCartao(){

    }
    
    //Construtor vazio
    public Cartao() {
    }
    
    //Construtor com parâmetros
    public Cartao(int numCartao, int codCliente, int numConta, int numAgencia, char nomeCliente, int bandeira, int validade, int clienteDesde, int cvv, char senha) {
        this.numCartao = numCartao;
        this.codCliente = codCliente;
        this.numConta = numConta;
        this.numAgencia = numAgencia;
        this.nomeCliente = nomeCliente;
        this.bandeira = bandeira;
        this.validade = validade;
        this.clienteDesde = clienteDesde;
        this.cvv = cvv;
        this.senha = senha;
    }
    
    //Getters and Setters

    public int getNumCartao() {
        return numCartao;
    }

    public void setNumCartao(int numCartao) {
        this.numCartao = numCartao;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public int getNumAgencia() {
        return numAgencia;
    }

    public void setNumAgencia(int numAgencia) {
        this.numAgencia = numAgencia;
    }

    public char getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(char nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getBandeira() {
        return bandeira;
    }

    public void setBandeira(int bandeira) {
        this.bandeira = bandeira;
    }

    public int getValidade() {
        return validade;
    }

    public void setValidade(int validade) {
        this.validade = validade;
    }

    public int getClienteDesde() {
        return clienteDesde;
    }

    public void setClienteDesde(int clienteDesde) {
        this.clienteDesde = clienteDesde;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public char getSenha() {
        return senha;
    }

    public void setSenha(char senha) {
        this.senha = senha;
    }
    
    
}

  